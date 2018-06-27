/*
 * $Id: GroovyUtil.java 18074 2016-01-22 09:34:07Z jyang $
 * 
 * Copyright (c) 2001-2015 Accentrix Company Limited. All Rights Reserved.
 */
package com.accentrix.hku.groovy;

import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import groovy.lang.Binding;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceConnector;
import groovy.util.ResourceException;
import groovy.util.ScriptException;

public class GroovyUtil {
    private static final Logger LOG = LoggerFactory.getLogger(GroovyUtil.class);

    private GroovyUtil() {
    }

    public static void runGroovy(String rootFolder, String groovyFile, Map<String, Object> variables) {
        GroovyScriptEngine gse = null;

        File file = new File(rootFolder);
        if (file.exists()) {
            LOG.info("sqlDir exist: {}", rootFolder);
            try {
                gse = new GroovyScriptEngine(rootFolder);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        } else {
            gse = new GroovyScriptEngine(new ResourceConnectorExt(rootFolder));
        }

        Binding binding = new Binding();

        if (variables != null) {
            Set<Map.Entry<String, Object>> entrySet = variables.entrySet();
            for (Map.Entry<String, Object> entry : entrySet) {
                binding.setVariable(entry.getKey(), entry.getValue());
            }
        }

        LOG.info("groovyFile: {}", groovyFile);
        try {
            gse.run(groovyFile, binding);
        } catch (ResourceException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (ScriptException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private static class ResourceConnectorExt implements ResourceConnector {

        private String root;

        public ResourceConnectorExt(String root) {
            this.root = root;
        }

        @Override
        public URLConnection getResourceConnection(String name) throws ResourceException {

            URLConnection connection = null;

            ClassPathResource cpr = new ClassPathResource(root + File.separator + name);
            try {
                connection = cpr.getURL().openConnection();
            } catch (IOException e) {
                throw new ResourceException(e.getMessage(), e);
            }

            return connection;
        }

    }
}
