/*
 * $Id: AspectUtils.java 15851 2015-09-15 04:17:27Z jyang $
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
package com.accentrix.hku.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.accentrix.hku.cache.CacheScope;
import com.accentrix.hku.cache.annotation.Cacheable;
import com.accentrix.hku.cache.annotation.FlushCache;
import com.accentrix.hku.wait.annotation.ExecuteAndWait;

public final class AspectUtils {

    private static final Logger LOG = LoggerFactory.getLogger(AspectUtils.class);

    private AspectUtils() {
    }

    @SuppressWarnings("rawtypes")
    public static Logger getLogger(JoinPoint joinPoint) {
        Class declaringType = joinPoint.getSignature().getDeclaringType();
        return getLogger(declaringType);
    }

    @SuppressWarnings("rawtypes")
    public static Logger getLogger(final Class declaringType) {
        try {
            Field loggerField = declaringType.getDeclaredField("log");
            loggerField.setAccessible(true);
            return (Logger) loggerField.get(null);
        } catch (NoSuchFieldException e) {
            try {
                Field loggerField = declaringType.getDeclaredField("LOG");
                loggerField.setAccessible(true);
                return (Logger) loggerField.get(null);
            } catch (NoSuchFieldException ex) {
                LOG.info("'LOG' field not found in " + declaringType);
            } catch (Exception ex) {
                // do nothing
            }
        } catch (Exception e) {
            // do nothing
        }
        return LOG;
    }

    public static void logParamValues(StringBuilder logLine, String[] paramNames, Object[] paramValues) {
        for (int i = 0; i < paramValues.length; i++) {
            logLine.append(paramNames[i]).append("=").append(toString(paramValues[i]));
            if (i < paramValues.length - 1) {
                logLine.append(", ");
            }
        }
    }

    public static String getCacheGroup(Method method) {

        if (method.isAnnotationPresent(Cacheable.class)) {

            Cacheable cacheable = method.getAnnotation(Cacheable.class);

            return cacheable.group();
        }

        if (method.isAnnotationPresent(FlushCache.class)) {

            FlushCache flushCache = method.getAnnotation(FlushCache.class);

            return flushCache.group();
        }

        return "default";
    }

    public static CacheScope getCacheScope(Method method) {

        if (method.isAnnotationPresent(Cacheable.class)) {

            Cacheable cacheable = method.getAnnotation(Cacheable.class);

            return cacheable.cacheScope();
        }

        if (method.isAnnotationPresent(FlushCache.class)) {

            FlushCache flushCache = method.getAnnotation(FlushCache.class);

            return flushCache.cacheScope();
        }

        return CacheScope.application;
    }

    public static boolean isExecuteAndWait(Method method) {

        if (method.isAnnotationPresent(ExecuteAndWait.class)) {

            ExecuteAndWait ew = method.getAnnotation(ExecuteAndWait.class);

            return ew.executeAndWait();
        }

        return false;
    }

    public static String getTaskExecutor(Method method) {

        if (method.isAnnotationPresent(ExecuteAndWait.class)) {

            ExecuteAndWait ew = method.getAnnotation(ExecuteAndWait.class);

            return ew.taskExecutor();
        }

        return "";
    }

    @SuppressWarnings("rawtypes")
    public static String toString(Object object) {
        if (object == null) {
            return "<null>";
        } else if (object instanceof String) {
            if (((String) object).length() > 100) {
                return ((String) object).substring(0, 100) + "...[more]";
            } else {
                return (String) object;
            }
        } else if (object instanceof Long) {
            return ((Long) object).toString();
        } else if (object instanceof Boolean) {
            return ((Boolean) object).toString();
        } else if (object instanceof Double) {
            return ((Double) object).toString();
        } else if (object instanceof Integer) {
            return ((Integer) object).toString();
        } else if (object instanceof Collection) {
            return StringUtils.join((Collection) object, ",");
        } else if (object.getClass().isArray()) {
            return StringUtils.join((Object[]) object, ",");
        } else {
            return object.toString();
        }
    }
}
