/*
 * $Id: ThreadCacheAdministrator.java 12290 2015-01-21 09:14:50Z jierong $
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
package com.accentrix.hku.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.oscache.base.AbstractCacheAdministrator;
import com.opensymphony.oscache.base.Cache;

public class ThreadCacheAdministrator extends AbstractCacheAdministrator {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(ThreadCacheAdministrator.class);

    private static final ThreadLocal<Cache> caches = new ThreadLocal<Cache>();

    private static final ThreadCacheAdministrator instance = new ThreadCacheAdministrator();

    private ThreadCacheAdministrator() {
        super(null);
    }

    public static ThreadCacheAdministrator getInstance() {
        return instance;
    }

    public Cache currentThreadCache() {
        Cache cache = caches.get();
        if (cache == null) {
            cache = createCache();
            caches.set(cache);
        }
        return cache;
    }

    public void destroyCurrentThreadCache() {
        // Finalize the cache
        Cache cache = caches.get();
        if (cache != null) {
            finalizeListeners(cache);
            caches.remove();
            if (LOG.isInfoEnabled()) {
                LOG.info("Shut down the ThreadCache {}", Thread.currentThread().getName());
            }
        }
    }

    private Cache createCache() {
        LOG.info("Creating new cache");

        Cache cache = new Cache(isMemoryCaching(), isUnlimitedDiskCache(), isOverflowPersistence(), isBlocking(),
                algorithmClass, cacheCapacity);

        configureStandardListeners(cache);

        return cache;
    }
}
