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
package com.accentrix.hku.wait.aspectj;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.aspectj.lang.reflect.CodeSignature;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

import org.springframework.core.task.TaskExecutor;

import com.accentrix.hku.wait.annotation.ExecuteAndWait;
import com.accentrix.hku.util.AspectUtils;
import com.accentrix.hku.util.SpringContextHolder;
import com.accentrix.hku.wait.ExecuteAndWaitHandler;
import com.accentrix.hku.wait.ExecuteAndWaitUtil;
import com.accentrix.hku.interceptor.AuditorAware;
import com.accentrix.hku.jpa.OpenEntityManagerExecutor;
import com.accentrix.hku.wait.ExecuteAndWaitException;

public aspect WaitAspect {
    private final static Logger log = LoggerFactory.getLogger(WaitAspect.class);

    pointcut wait(): execution(@ExecuteAndWait * *.*(..) throws ExecuteAndWaitException);

    Object around() throws ExecuteAndWaitException: wait() {

        Class declaringType = thisJoinPoint.getSignature().getDeclaringType();

        final Object[] paramValues = thisJoinPoint.getArgs();
        String[] paramNames = ((CodeSignature) thisJoinPointStaticPart.getSignature())
                .getParameterNames();

        MethodSignature ms = (MethodSignature) thisJoinPointStaticPart.getSignature();
        Method m = ms.getMethod();
        boolean isWait = AspectUtils.isExecuteAndWait(m);
        String taskExecutorBeanName = AspectUtils.getTaskExecutor(m);

        if (isWait) {
            final ExecuteAndWaitHandler executeAndWaitHandler = SpringContextHolder.getBean(ExecuteAndWaitHandler.class);
            final String taskId = executeAndWaitHandler.createTask();

            final Callable proceedCallable =  new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    try {
                        Object result = proceed();
                        executeAndWaitHandler.setResult(taskId, result);
                        return result;
                    } catch (Throwable thr) {
                        executeAndWaitHandler.setThrowable(taskId, thr);
                    }
                    return null;
                }
            };

            final AuditorAware auditorAware = SpringContextHolder.getBean(AuditorAware.class);
            final String userId = auditorAware.getCurrentAuditor();
            
            final Callable futureTaskCallable =  new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    ExecuteAndWaitUtil.setCurrentTaskIs(taskId);// set taskId in new thread
                    auditorAware.setCurrentAuditor(userId);// set userId in new thread
                    final OpenEntityManagerExecutor openEntityManagerExecutor = SpringContextHolder.getBean(OpenEntityManagerExecutor.class);
                    Object result = openEntityManagerExecutor.execute(proceedCallable);
                    ExecuteAndWaitUtil.removeCurrentTaskIs();
                    return result;
                }
            };
            
            FutureTask<Object> task = new FutureTask<Object>(futureTaskCallable);
            
            TaskExecutor taskExecutor = (TaskExecutor)SpringContextHolder.getBean(taskExecutorBeanName);
            taskExecutor.execute(task);

            throw new ExecuteAndWaitException(taskId);

        } else {
            return proceed();
        }
    }
}