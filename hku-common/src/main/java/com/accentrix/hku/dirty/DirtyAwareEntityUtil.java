/*
 * $Id: DirtyAwareEntityUtil.java 12290 2015-01-21 09:14:50Z jierong $
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
package com.accentrix.hku.dirty;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class DirtyAwareEntityUtil {
    private static final Logger LOG = LoggerFactory.getLogger(DirtyAwareEntityUtil.class);

    @SuppressWarnings("unchecked")
    public static <T extends DirtyAware> T createProxyEntity(T t) {
        LOG.debug("createProxyEntity: {}", t);
        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(t.getClass());

        enhancer.setCallback(new DirtyInterceptor<T>(t));
        return (T) enhancer.create();
    }

    private static final class DirtyInterceptor<T extends DirtyAware> implements MethodInterceptor {

        private T t;

        public DirtyInterceptor(T t) {
            this.t = t;
        }

        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {

            String methodName = method.getName();
            LOG.debug("method: {}", methodName);

            if (!t.isDirty()) {
                if (methodName.startsWith("set")) {
                    String getterMethodName = "get" + methodName.substring(3);
                    Method getterMethod = t.getClass().getDeclaredMethod(getterMethodName);
                    Object old = getterMethod.invoke(t);
                    Assert.isTrue(1 == args.length);
                    if (old == null) {
                        if (args[0] != null) {
                            t.setDirty(true);
                        }
                    } else {
                        t.setDirty(!old.equals(args[0]));
                    }
                }

                LOG.debug("isDirty: {}", t.isDirty());
            }

            return method.invoke(t, args);
        }
    }
}
