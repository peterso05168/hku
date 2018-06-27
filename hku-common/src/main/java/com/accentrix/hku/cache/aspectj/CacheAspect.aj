/*
 * $Id: Cacheable.java 1852 2013-07-02 01:07:01Z vendor.accentrix $
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
package com.accentrix.hku.cache.aspectj;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.aspectj.lang.reflect.CodeSignature;
import java.util.concurrent.Callable;
import org.aspectj.lang.reflect.MethodSignature;
import java.lang.reflect.Method;

import com.accentrix.hku.cache.annotation.Cacheable;
import com.accentrix.hku.cache.annotation.FlushCache;
import com.accentrix.hku.cache.annotation.CleanCache;
import com.accentrix.hku.cache.CacheScope;
import com.accentrix.hku.cache.ThreadCacheAdministrator;
import com.accentrix.hku.util.AspectUtils;
import com.accentrix.hku.util.OSCacheUtil;

public aspect CacheAspect {
    private final static Logger log = LoggerFactory.getLogger(CacheAspect.class);

    pointcut cache(): execution(@Cacheable * *.*(..));

    pointcut flushCache(): execution(@FlushCache * *.*(..));

    pointcut cleanCache(): execution(@CleanCache * *.*(..));

    Object around(): cache(){

        Class declaringType = thisJoinPoint.getSignature().getDeclaringType();

        final Object[] paramValues = thisJoinPoint.getArgs();
        String[] paramNames = ((CodeSignature) thisJoinPointStaticPart.getSignature())
                .getParameterNames();

        MethodSignature ms = (MethodSignature) thisJoinPointStaticPart.getSignature();
        Method m = ms.getMethod();
        String group = AspectUtils.getCacheGroup(m);
        CacheScope cacheScope = AspectUtils.getCacheScope(m);

        StringBuilder cacheKey = new StringBuilder(declaringType.getSimpleName()).append("_")
                .append(thisJoinPointStaticPart.getSignature().getName());

        if (paramNames.length != 0)
            AspectUtils.logParamValues(cacheKey, paramNames, paramValues);

        AspectUtils.getLogger(thisJoinPoint).info("cache group: " + cacheKey.toString());

        Callable<Object> function = new Callable<Object>() {

            public Object call() throws Exception {
                return proceed();
            }

        };

        try{
            return OSCacheUtil.cache(cacheScope, cacheKey.toString(), function, group, -1);
        }catch(RuntimeException re){
            throw re;
        }catch (Throwable thr) {
            throw new RuntimeException(thr);
        }
    }

    after(): flushCache(){

        MethodSignature ms = (MethodSignature) thisJoinPointStaticPart.getSignature();
        Method m = ms.getMethod();
        String group = AspectUtils.getCacheGroup(m);
        CacheScope cacheScope = AspectUtils.getCacheScope(m);

        OSCacheUtil.flushGroup(cacheScope, group);
    }

    after(): cleanCache(){
        ThreadCacheAdministrator.getInstance().destroyCurrentThreadCache();
    }

}