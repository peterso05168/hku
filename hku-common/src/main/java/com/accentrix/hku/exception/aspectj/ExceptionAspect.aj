/*
 * $Id: ExceptionAspect.aj 12740 2015-02-12 06:25:28Z jierong $
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
package com.accentrix.hku.exception.aspectj;

import org.aspectj.lang.reflect.MethodSignature;
import com.accentrix.hku.exception.annotation.WatchException;
import com.accentrix.hku.exception.annotation.WatchException4Popup;
import com.accentrix.hku.exception.handler.ExceptionHandler;
import com.accentrix.hku.exception.BaseExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public aspect ExceptionAspect {
    private final static Logger log = LoggerFactory.getLogger(ExceptionAspect.class);

    /**
     * Matches the execution of any public method in a type with the
     * WatchException annotation, or any subtype of a type with the
     * WatchException annotation.
     */
    private pointcut executionOfAnyPublicMethodInAtWatchExceptionType() :
		execution(public * ((@WatchException *)+).*(..)) && within(@WatchException *);

    private pointcut executionOfAnyTopPublicMethodInAtWatchExceptionType() :
        executionOfAnyPublicMethodInAtWatchExceptionType() && !cflowbelow(executionOfAnyPublicMethodInAtWatchExceptionType());

    /**
     * Matches the execution of any method with the WatchException annotation.
     */
    private pointcut executionOfWatchExceptionMethod() :
		execution(@WatchException * *(..));

    private pointcut executionOfWatchExceptionTopMethod() :
        executionOfWatchExceptionMethod() && !cflowbelow(executionOfWatchExceptionMethod());

    // ------------------

    /**
     * Matches the execution of any public method in a type with the
     * WatchException4Popup annotation, or any subtype of a type with the
     * WatchException4Popup annotation.
     */
    private pointcut executionOfAnyPublicMethodInAtWatchException4PopupType() :
        execution(public * ((@WatchException4Popup *)+).*(..)) && within(@WatchException4Popup *);

    private pointcut executionOfAnyTopPublicMethodInAtWatchException4PopupType() :
        executionOfAnyPublicMethodInAtWatchException4PopupType() && !cflowbelow(executionOfAnyPublicMethodInAtWatchException4PopupType());

    /**
     * Matches the execution of any method with the WatchException4Popup
     * annotation.
     */
    private pointcut executionOfWatchException4PopupMethod() :
        execution(@WatchException4Popup * *(..));

    private pointcut executionOfWatchException4PopupTopMethod() :
        executionOfWatchException4PopupMethod() && !cflowbelow(executionOfWatchException4PopupMethod());

    // ---------------------

    /**
     * Definition of pointcut from super aspect - matched join points will have
     * Spring transaction management applied.
     */
    protected pointcut watchExceptionMethodExecution(Object managedBean) :
		(executionOfAnyTopPublicMethodInAtWatchExceptionType() || executionOfWatchExceptionTopMethod() ||
		 executionOfAnyTopPublicMethodInAtWatchException4PopupType() || executionOfWatchException4PopupTopMethod())
		 && this(managedBean);

    Object around(final Object managedBean): watchExceptionMethodExecution(managedBean) {
        MethodSignature methodSignature = (MethodSignature) thisJoinPoint.getSignature();
        
        WatchException watchException = methodSignature.getMethod().getAnnotation(WatchException.class);
        if (watchException == null) {
            watchException = managedBean.getClass().getAnnotation(WatchException.class);
        }

        WatchException4Popup watchException4Popup = methodSignature.getMethod().getAnnotation(WatchException4Popup.class);
        if (watchException4Popup == null) {
            watchException4Popup = managedBean.getClass().getAnnotation(WatchException4Popup.class);
        }
        
        if (watchException4Popup == null && watchException != null && !watchException.isCatch()) {
            return proceed(managedBean);
        }
        
        try {
            return proceed(managedBean);
        } catch (Exception ex) {
            log.debug("catch RuntimeException: {}", ex.getMessage());
            BaseExceptionHandler.handleException(watchException, watchException4Popup, ex);
            return null;
            // exception had been handled, DO NOT throw ex again;
        }
    }
}
