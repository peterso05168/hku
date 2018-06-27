/*
 * $Id: DbUpgrader.java 24972 2017-11-01 10:24:52Z jyang $
 * 
 * Copyright (c) 2001-2008 Accentrix Company Limited. All Rights Reserved.
 * 
 * Accentrix Company Limited. ("Accentrix") retains copyright on all text, source
 * and binary code contained in this software and documentation. Accentrix grants
 * Licensee a limited license to use this software, provided that this copyright
 * notice and license appear on all copies of the software. The software source
 * code is provided for reference purposes only and may not be copied, modified 
 * or distributed.
 * 
 * THIS SOFTWARE AND DOCUMENTATION ARE PROVIDED "AS IS," WITHOUT ANY WARRANTY OF
 * ANY KIND UNLESS A SEPARATE WARRANTIES IS PURCHASED FROM ACCENTRIX AND REMAINS
 * VALID.  ALL EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES,
 * INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE OR NON-INFRINGEMENT, ARE HEREBY EXCLUDED. ACCENTRIX SHALL NOT BE LIABLE
 * FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING OR MODIFYING THE
 * SOFTWARE OR ITS DERIVATIVES.
 * 
 * IN NO EVENT WILL ACCENTRIX BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR
 * FOR DIRECT, INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES,
 * HOWEVER CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE
 * USE OF OR INABILITY TO USE SOFTWARE, EVEN IF ACCENTRIX HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 */
package com.accentrix.hku.upgrade;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import com.accentrix.hku.util.SpringContextHolder;

public abstract class DbUpgrader {

    private static final Logger LOG = LoggerFactory.getLogger(DbUpgrader.class);
    private static final String DEFAULT_TABLE_NAME = "schema_version";

    public void upgrade(String _schemaVersionTableName, String _initVersion, String sqlDir) throws Exception {

        String schemaVersionTableName = _schemaVersionTableName;

        if (StringUtils.isBlank(schemaVersionTableName)) {
            schemaVersionTableName = DEFAULT_TABLE_NAME;
        }

        Integer initVersion = null;
        if (StringUtils.isNotBlank(_initVersion)) {
            initVersion = Integer.valueOf(_initVersion);
        }

        Connection con = getConnection();

        con.setAutoCommit(true);
        checkVersionTableExist(con, schemaVersionTableName, initVersion);

        int lastVersion = getLastVersion(con, schemaVersionTableName);
        LOG.info("lastVersion: {}", lastVersion);

        con.setAutoCommit(false);
        upgrade(con, schemaVersionTableName, lastVersion, sqlDir);
    }

    private void upgrade(Connection con, String schemaVersionTableName, int lastVersion, String sqlDir)
            throws Exception {
        try {
            for (;;) {
                lastVersion++;

                boolean hasMore = upgradeNext(con, schemaVersionTableName, lastVersion, sqlDir);
                if (!hasMore) {
                    break;
                }
            }
        } finally {
            close(con);
        }
    }

    private boolean upgradeNext(Connection con, String schemaVersionTableName, int lastVersion, String sqlDir)
            throws Exception {
        try {
            String sqlScriptFile = sqlDir + File.separator + "upgrade-" + String.format("%06d", lastVersion) + ".sql";

            InputStream is = null;

            File file = new File(sqlDir);
            if (file.exists()) {

                File cpr = new File(sqlScriptFile);

                if (cpr.exists() == false) {
                    LOG.info("{} not exist, quit.", sqlScriptFile);
                    return false;
                }
                LOG.info("{} exist, try to update the database...", sqlScriptFile);

                is = new FileInputStream(cpr);
            } else {

                ClassPathResource cpr = new ClassPathResource(sqlScriptFile);

                if (cpr.exists() == false) {
                    LOG.info("{} not exist, quit.", sqlScriptFile);
                    return false;
                }
                LOG.info("{} exist, try to update the database...", sqlScriptFile);

                is = cpr.getInputStream();
            }

            String comments = runSQLFile(con, is, sqlDir);
            updateLastVersion(con, schemaVersionTableName, lastVersion, comments);
            con.commit();

            LOG.info("{} upgrade done.", sqlScriptFile);
            return true;
        } catch (Exception e) {
            con.rollback();
            throw e;
        }
    }

    public String runSQLFile(Connection con, InputStream is, String sqlDir)
            throws IOException, NamingException, SQLException {
        BufferedReader reader = null;
        String comments = "";
        try {
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            String line = null;
            StringBuilder sb = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("#")) {
                    LOG.info(line);
                    comments += line.substring(1) + "\r";
                    continue;
                }

                line = line.trim();
                if (sb.length() > 0) {
                    sb.append(" ");
                }
                sb.append(line);

                if (line.endsWith(";")) {
                    String sql = sb.toString();

                    sb = new StringBuilder();

                    if (StringUtils.startsWith(sql, "java")) {
                        runJava(con, sql);
                    } else if (StringUtils.startsWith(sql, "groovy")) {
                        runGroovy(con, sql, sqlDir);
                    } else {
                        runSQL(con, sql);
                    }
                }

            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

        return comments;
    }

    private void runJava(Connection con, String sql) throws SQLException {
        if (LOG.isDebugEnabled()) {
            LOG.debug("execute UpgradeProcess");
        }
        UpgradeProcess.execute(con, sql);
    }

    private void runGroovy(Connection con, String sql, String sqlDir) {
        GroovyUpgradeProcess.runGroovy(con, sql, sqlDir);
    }

    protected Connection getConnection() throws SQLException {
        DataSource dataSource = (DataSource) SpringContextHolder.getBean("dataSource");
        return dataSource.getConnection();
    }

    public void runSQL(final Connection con, final String sql) throws SQLException {

        Statement s = null;

        try {

            String finalSql = getFinalSql(sql);

            s = con.createStatement();
            s.executeUpdate(finalSql);

        } finally {
            close(s);
        }
    }

    private String getFinalSql(final String sql) {
        String finalSql = sql;

        if (finalSql.endsWith(";")) {
            finalSql = finalSql.substring(0, finalSql.length() - 1);
        }

        if (finalSql.endsWith("go")) {
            finalSql = finalSql.substring(0, finalSql.length() - 2);
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug(finalSql);
        }
        return finalSql;
    }

    public void runSQL(Connection con, final String sql, Object... paramValues) throws SQLException {

        PreparedStatement s = null;

        try {

            String finalSql = getFinalSql(sql);

            s = con.prepareStatement(finalSql);
            if (paramValues != null) {
                int index = 0;
                for (Object object : paramValues) {
                    s.setObject(++index, object);
                }
            }
            try {
                s.execute();
            } catch (SQLException sqle) {
                throw sqle;
            }

        } finally {
            close(s);
        }
    }

    /**
     * if this table not exist, create a new table
     * 
     * @param con
     * @param schemaVersionTableName
     * @param initVersion
     * @throws SQLException
     */
    public abstract void checkVersionTableExist(Connection con, String schemaVersionTableName, Integer initVersion)
            throws SQLException;

    /**
     * get last upgrade version from table
     * 
     * @param con
     * @param schemaVersionTableName
     * @return
     * @throws SQLException
     */
    public abstract int getLastVersion(Connection con, String schemaVersionTableName) throws SQLException;

    /**
     * update the last version into table
     * 
     * @param con
     * @param lastVersion
     * @param comments
     * @throws SQLException
     */
    public abstract void updateLastVersion(Connection con, String schemaVersionTableName, int lastVersion,
            String comments) throws SQLException;

    protected void close(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    protected void close(Statement s) {
        try {
            if (s != null) {
                s.close();
            }
        } catch (SQLException ex) {
            LOG.error(ex.getMessage(), ex);
        }
    }

    protected void close(PreparedStatement s) throws SQLException {
        try {
            if (s != null) {
                s.close();
            }
        } catch (SQLException ex) {
            LOG.error(ex.getMessage(), ex);
        }
    }

    protected void close(ResultSet rs) throws SQLException {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            LOG.error(ex.getMessage(), ex);
        }
    }

}
