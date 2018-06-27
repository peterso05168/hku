/*
 * $Id: DbUpgradeListener.java 15360 2015-08-27 07:26:30Z jyang $
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

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * auto execute the SQL script in META-INF/db/ according to last version of
 * table schema_version
 * 
 * @author jyang
 * 
 */
public class DbUpgradeListener implements ServletContextListener {
    private static final Logger LOG = LoggerFactory.getLogger(DbUpgradeListener.class);

    private static final String DB_UPGRADER_CLASS = "dbUpgraderClass";
    private static final String TABLE_NAME_PARAM = "schemaVersionTableName";
    private static final String INIT_VERSION = "initVersion";
    private static final String UPGRADE_IN_NEW_THREAD = "upgradeInNewThread";

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext sc = sce.getServletContext();

        String _dbUpgraderClass = sc.getInitParameter(DB_UPGRADER_CLASS);
        final String _schemaVersionTableName = sc.getInitParameter(TABLE_NAME_PARAM);
        final String _initVersion = sc.getInitParameter(INIT_VERSION);
        String _upgradeInNewThread = sc.getInitParameter(UPGRADE_IN_NEW_THREAD);

        try {

            if (StringUtils.isBlank(_dbUpgraderClass)) {
                _dbUpgraderClass = "com.accentrix.hku.upgrade.MysqlUpgrader";
            }

            final String dbUpgraderClass = _dbUpgraderClass;

            if ("true".equals(_upgradeInNewThread)) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            LOG.info("upgrade DB in 20 sec.");
                            Thread.sleep(20000L);

                            upgrade(dbUpgraderClass, _schemaVersionTableName, _initVersion);
                        } catch (InstantiationException e) {
                            LOG.error(e.getMessage(), e);
                        } catch (IllegalAccessException e) {
                            LOG.error(e.getMessage(), e);
                        } catch (ClassNotFoundException e) {
                            LOG.error(e.getMessage(), e);
                        } catch (Exception e) {
                            LOG.error(e.getMessage(), e);
                        }
                    }
                }).start();

            } else {
                upgrade(dbUpgraderClass, _schemaVersionTableName, _initVersion);
            }

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private void upgrade(String _dbUpgraderClass, String _schemaVersionTableName, String _initVersion)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException, Exception {
        DbUpgrader du = (DbUpgrader) Class.forName(_dbUpgraderClass).newInstance();

        du.upgrade(_schemaVersionTableName, _initVersion, "META-INF" + File.separator + "db");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
