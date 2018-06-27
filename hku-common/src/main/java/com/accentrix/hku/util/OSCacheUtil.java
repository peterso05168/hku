/*
 * $Id: OSCacheUtil.java 12293 2015-01-21 09:25:46Z jierong $
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

import java.util.Date;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import com.accentrix.hku.cache.CacheScope;
import com.accentrix.hku.cache.ThreadCacheAdministrator;
import com.opensymphony.oscache.base.Cache;
import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;
import com.opensymphony.oscache.web.filter.ExpiresRefreshPolicy;

/**
 * osCache Utile.
 * <p>
 * Copyright: Copyright (c) 2005
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author JR
 * @version 1.0
 */
public class OSCacheUtil {

    private static final Logger LOG = Logger.getLogger(OSCacheUtil.class);

    public static final int ALL_SCOPE = 0;

    private static GeneralCacheAdministrator applicationCacheAdmin = null;
    private static Cache applicationCache = null;

    private static ThreadCacheAdministrator threadCacheAdmin = ThreadCacheAdministrator.getInstance();

    private OSCacheUtil() {
    }

    public static Object getFromCache(CacheScope cacheScope, String key) throws NeedsRefreshException {
        if (CacheScope.application == cacheScope) {
            initCacheAdmin();

            return applicationCache.getFromCache(key);
        } else {
            return threadCacheAdmin.currentThreadCache().getFromCache(key);
        }
    }

    /**
     * 
     * @param key
     * @param content
     * @param group
     * @param refreshPeriod
     *            the refresh period in seconds, it will never refresh if less
     *            than 1
     */
    public static void cache(CacheScope cacheScope, String key, Object content, String group, int refreshPeriod) {

        cache(cacheScope, key, content, new String[] { group }, refreshPeriod);
    }

    /**
     * 
     * @param key
     * @param content
     * @param groups
     * @param refreshPeriod
     *            the refresh period in seconds, it will never refresh if less
     *            than 1
     */
    public static void cache(CacheScope cacheScope, String key, Object content, String[] groups, int refreshPeriod) {
        if (CacheScope.application == cacheScope) {
            initCacheAdmin();

            if (refreshPeriod > 0) {
                applicationCache.putInCache(key, content, groups, new ExpiresRefreshPolicy(refreshPeriod), null);
            } else {
                applicationCache.putInCache(key, content, groups);
            }
        } else {
            threadCacheAdmin.currentThreadCache().putInCache(key, content, groups);
        }
    }

    // just a string
    private static final Object NULL_CACHE = "";

    public static <T> T cache(CacheScope cacheScope, String key, Callable<T> function, String group, int refreshPeriod)
            throws Exception {

        return cache(cacheScope, key, function, new String[] { group }, refreshPeriod);
    }

    /**
     * cache the return object of function
     * 
     * @param key
     * @param function
     * @param group
     * @param refreshPeriod
     *            the refresh period in seconds, it will never refresh if less
     *            than 1
     * @return
     */
    public static <T> T cache(String key, Callable<T> function, String group, int refreshPeriod) throws Exception {

        return cache(CacheScope.application, key, function, new String[] { group }, refreshPeriod);
    }

    @SuppressWarnings("unchecked")
    public static <T> T cache(CacheScope cacheScope, String key, Callable<T> function, String[] groups,
            int refreshPeriod) throws Exception {

        try {
            T cache = (T) getFromCache(cacheScope, key);
            if (LOG.isDebugEnabled()) {
                LOG.debug("<cache>: Using cached entry : " + key);
            }
            if (NULL_CACHE.equals(cache))
                cache = null;
            return cache;
        } catch (NeedsRefreshException e) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("<cache>: Needs Refresh entry : " + key);
            }
            cancelUpdate(cacheScope, key);
        }

        T result = function.call();
        if (LOG.isDebugEnabled()) {
            LOG.debug("<cache>: function return : " + result);
        }

        Object cache = result;
        if (cache == null) {
            cache = NULL_CACHE;
        }
        cache(cacheScope, key, cache, groups, refreshPeriod);

        return result;
    }

    /**
     * flush osCache group
     * 
     * @param group
     *            Cache group
     */
    public static void flushGroup(CacheScope cacheScope, String group) {
        if (CacheScope.application == cacheScope) {
            initCacheAdmin();

            if (LOG.isDebugEnabled()) {
                LOG.debug("<cache>: flushing cache group : " + group);
            }

            applicationCache.flushGroup(group);
        } else {
            threadCacheAdmin.currentThreadCache().flushGroup(group);
        }
    }

    /**
     * flush cache by key
     * 
     * @param key
     *            String
     */
    public static void flushKey(CacheScope cacheScope, String key) {
        if (CacheScope.application == cacheScope) {
            initCacheAdmin();

            applicationCache.flushEntry(key);
        } else {
            threadCacheAdmin.currentThreadCache().flushEntry(key);
        }
    }

    /**
     * flush all Cache
     * 
     */
    public static void flushAll(CacheScope cacheScope) {
        if (CacheScope.application == cacheScope) {
            initCacheAdmin();

            applicationCacheAdmin.flushAll();
        } else {
            threadCacheAdmin.currentThreadCache().flushAll(new Date());
        }
    }

    private static void initCacheAdmin() {
        if (applicationCacheAdmin != null) {
            return;
        }

        GeneralCacheAdministrator generalCacheAdministrator = new GeneralCacheAdministrator();
        Cache adminCache = generalCacheAdministrator.getCache();

        applicationCacheAdmin = generalCacheAdministrator;
        applicationCache = adminCache;
    }

    public static void cancelUpdate(CacheScope cacheScope, String actualKey) {
        if (CacheScope.application == cacheScope) {
            initCacheAdmin();
            applicationCache.cancelUpdate(actualKey);
        } else {
            threadCacheAdmin.currentThreadCache().cancelUpdate(actualKey);
        }
    }

}
