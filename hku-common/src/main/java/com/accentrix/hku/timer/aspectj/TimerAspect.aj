/*
 * $Id: ExceptionAspect.aj 9055 2014-06-22 09:22:39Z jierong $
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
package com.accentrix.hku.timer.aspectj;

import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.exception.handler.ExceptionHandler;

public aspect TimerAspect {
    private final static Logger log = LoggerFactory.getLogger(TimerAspect.class);

    /**
     * Matches the execution of any public method in a type with the
     * Timer annotation, or any subtype of a type with the
     * Timer annotation.
     */
    private pointcut executionOfAnyPublicMethodInAtTimerType() :
        execution(public * ((@Timer *)+).*(..)) && within(@Timer *);

    /**
     * Matches the execution of any method with the Timer annotation.
     */
    private pointcut executionOfTimerMethod() :
        execution(@Timer * *(..));

    /**
     * Definition of pointcut from super aspect - matched join points will have
     * Spring transaction management applied.
     */
    protected pointcut timerMethodExecution(Object managedBean) :
        (executionOfAnyPublicMethodInAtTimerType()
         || executionOfTimerMethod() )
         && this(managedBean);

    Object around(final Object managedBean): timerMethodExecution(managedBean) {
        if (log.isInfoEnabled()==false){
            return proceed(managedBean);
        }
        
        MethodSignature methodSignature = (MethodSignature) thisJoinPoint.getSignature();
        long start = System.currentTimeMillis();
        Object obj = proceed(managedBean);
        log.info(managedBean.getClass().getSimpleName() + "." + methodSignature.getName()
                + " took " + (System.currentTimeMillis() - start) + " ms.");
        return obj;
    }

}
