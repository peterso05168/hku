/*
 * $Id: OracleUpgrader.java 14677 2015-07-29 05:49:05Z jyang $
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

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OracleUpgrader extends DbUpgrader {
    private static final Logger LOG = LoggerFactory.getLogger(MysqlUpgrader.class);

    public int getLastVersion(Connection con, String schemaVersionTableName) throws SQLException {

        ResultSet rs = null;
        Statement s = null;

        try {
            s = con.createStatement();

            rs = s.executeQuery("SELECT * FROM (SELECT * FROM " + schemaVersionTableName
                    + " order by versionNumber desc ) where ROWNUM <= 1");
            if (rs.next()) {
                return rs.getInt("versionNumber");
            }
        } finally {
            close(rs);
            close(s);
        }

        return -1;
    }

    public void checkVersionTableExist(Connection con, String schemaVersionTableName, Integer initVersion)
            throws SQLException {

        DatabaseMetaData meta = con.getMetaData();

        ResultSet res = meta.getTables(null, null, schemaVersionTableName, new String[] { "TABLE" });
        if (!res.next()) {
            LOG.info("table " + schemaVersionTableName + " not exist");

            String CREATE_SQL = "CREATE TABLE " + schemaVersionTableName + " (" + "id number(19,0) not null,"
                    + "modifiedDate timestamp default NULL," + "versionNumber number(19,0) not null,"
                    + "remarks varchar2(4000 char)," + "PRIMARY KEY (id)" + ");";

            runSQL(con, CREATE_SQL);

            if (initVersion != null) {
                String INSERT_SQL = "INSERT INTO " + schemaVersionTableName
                        + " (id,modifiedDate,versionNumber) VALUES (?,?,?);";
                runSQL(con, INSERT_SQL, initVersion, new Date(), initVersion);
            }
        }
    }

    public void updateLastVersion(Connection con, String schemaVersionTableName, int lastVersion, String comments)
            throws SQLException {

        String sql = "INSERT INTO " + schemaVersionTableName
                + " (id,modifiedDate,versionNumber, remarks) VALUES (?,?,?,?);";

        runSQL(con, sql, lastVersion, new Date(), lastVersion, comments);
    }
}
