/*
 * $Id: GroovyUpgradeProcess.java 18074 2016-01-22 09:34:07Z jyang $
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
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.accentrix.hku.groovy.GroovyUtil;

public class GroovyUpgradeProcess {
    private static final Logger LOG = LoggerFactory.getLogger(GroovyUpgradeProcess.class);

    private GroovyUpgradeProcess() {
    }

    public static void runGroovy(Connection con, String sql, String sqlDir) {
        String groovyFile = sql.substring("groovy".length()).replace(";", "").trim();
        LOG.info("groovyFile: {}", groovyFile);

        Map<String, Object> variables = new HashMap<String, Object>(1);
        variables.put("connection", con);

        GroovyUtil.runGroovy(sqlDir, groovyFile, variables);
    }
}
