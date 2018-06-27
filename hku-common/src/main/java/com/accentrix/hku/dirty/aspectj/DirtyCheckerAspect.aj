/*
 * $Id: ExceptionAspect.aj 10414 2014-08-08 12:19:00Z joe $
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
package com.accentrix.hku.dirty.aspectj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.aspectj.lang.reflect.CodeSignature;
import java.util.concurrent.Callable;
import org.aspectj.lang.reflect.MethodSignature;
import java.lang.reflect.Method;

import org.springframework.util.Assert;

public aspect DirtyCheckerAspect {
    private final static Logger log = LoggerFactory.getLogger(DirtyCheckerAspect.class);

    private pointcut executionOfAnyPublicSetMethod() :
		execution(public * com.accentrix.hku.dirty.DirtyAware+.set*(..));

    private pointcut executionOfAnyPublicSetCreatedBy() :
		execution(public * com.accentrix.hku.dirty.DirtyAware+.setCreatedBy(..));

    private pointcut executionOfAnyPublicSetCreatedDate() :
		execution(public * com.accentrix.hku.dirty.DirtyAware+.setCreatedDate(..));

    private pointcut executionOfAnyPublicSetModifiedBy() :
		execution(public * com.accentrix.hku.dirty.DirtyAware+.setModifiedBy(..));

    private pointcut executionOfAnyPublicSetModifiedDate() :
		execution(public * com.accentrix.hku.dirty.DirtyAware+.setModifiedDate(..));

    private pointcut executionOfAnyPublicSetVersion() :
		execution(public * com.accentrix.hku.dirty.DirtyAware+.setVersion(..));

    private pointcut executionOfAnyPublicSetDirty() :
		execution(public * com.accentrix.hku.dirty.DirtyAware+.setDirty(..));

    protected pointcut executionOfSomePublicSetMethod(
            com.accentrix.hku.dirty.DirtyAware managedBean) :
        (executionOfAnyPublicSetMethod()
			&& !executionOfAnyPublicSetCreatedBy() 
			&& !executionOfAnyPublicSetCreatedDate() 
			&& !executionOfAnyPublicSetModifiedBy() 
			&& !executionOfAnyPublicSetModifiedDate() 
			&& !executionOfAnyPublicSetVersion() 
			&& !executionOfAnyPublicSetDirty() 
		 )
         && this(managedBean);

    Object around(final com.accentrix.hku.dirty.DirtyAware managedBean): executionOfSomePublicSetMethod(managedBean) {
        MethodSignature ms = (MethodSignature) thisJoinPointStaticPart.getSignature();
        Method m = ms.getMethod();

        if (!managedBean.isDirty() && !"setDirty".equals(m.getName())) {

            final Object[] paramValues = thisJoinPoint.getArgs();
            Assert.isTrue(1 == paramValues.length);

            try {
                Class[] parameterTypes = m.getParameterTypes();
                String methodPrefix = "get";
                Assert.isTrue(1 == parameterTypes.length);
                if(parameterTypes[0].isAssignableFrom(boolean.class)){
                    methodPrefix = "is";
                }
                String getterMethodName = methodPrefix + m.getName().substring(3);
                log.debug("ParameterTypes: {}, getterMethodName: {}", parameterTypes, getterMethodName);
                Class declaringType = thisJoinPoint.getSignature().getDeclaringType();
                Method getterMethod = declaringType.getDeclaredMethod(getterMethodName);
                Object old = getterMethod.invoke(managedBean);
                log.debug("{}.{}: {}  old: {} ", declaringType.getSimpleName(), m.getName(),
                        paramValues[0], old);

                if (old == null) {
                    if (paramValues[0] != null) {
                        if (paramValues[0] instanceof String) {
                            String str = (String) paramValues[0];
                            if (str.trim().length() > 0) {
                                managedBean.setDirty(true);
                            }
                        } else {
                            managedBean.setDirty(true);
                        }
                    }
                } else {
                    managedBean.setDirty(!old.equals(paramValues[0]));
                }
            } catch (Exception ex) {
                log.error(ex.getMessage(), ex);
            }
        }

        return proceed(managedBean);
    }
}
