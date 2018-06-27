/*
 * $Id: UpgradeProcess.java 12777 2015-02-13 05:17:08Z jierong $
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class UpgradeProcess {

    private static final Logger LOG = LoggerFactory.getLogger(UpgradeProcess.class);

    protected abstract void upgrade(Connection con) throws SQLException;

    public final static void execute(final Connection con, final String script) throws SQLException {
        UpgradeProcess upgradeProcess = extractScriptToClass(script);
        upgradeProcess.upgrade(con);
    }

    private static final UpgradeProcess extractScriptToClass(final String script) {
        LOG.debug("incoming script is --> {}", script);
        if (StringUtils.isBlank(script) || !StringUtils.startsWith(script, "java")) {
            throw new IllegalArgumentException("wrong incoming parameter : script formatter error");
        }

        String finalScript = StringUtils.remove(script, ";");
        String[] splitScript = finalScript.trim().split(" ");

        if (splitScript.length != 2) {
            throw new IllegalArgumentException("wrong incoming parameter : script formatter error");
        }

        LOG.debug("class full path --> {}", splitScript[1]);

        try {
            Object obj = Class.forName(splitScript[1]).newInstance();
            if (obj instanceof UpgradeProcess) {
                return (UpgradeProcess) obj;
            } else {
                throw new RuntimeException(splitScript[1] + " is not an instance of UpgradeProcess");
            }
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    protected final void close(final Connection connection, final PreparedStatement ps, final Statement statement,
            final ResultSet result) {
        if (result != null) {
            try {
                result.close();
            } catch (SQLException ex) {
                LOG.error(ex.getMessage(), ex);
            }
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                LOG.error(ex.getMessage(), ex);
            }
        }

        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException ex) {
                LOG.error(ex.getMessage(), ex);
            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                LOG.error(ex.getMessage(), ex);
            }
        }

    }

    protected final void close(final ResultSet result) {
        close(null, null, null, result);
    }
}
