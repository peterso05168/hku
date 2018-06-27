/*
 * $Id: SystemExceptionMapper.java 12294 2015-01-21 09:36:33Z jierong $
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
package com.accentrix.hku.exception;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.ExceptionMapper;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.jaxrs.client.ResponseExceptionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SystemExceptionMapper implements ExceptionMapper<Throwable>, ResponseExceptionMapper<Throwable> {
    private static final Logger LOG = LoggerFactory.getLogger(SystemExceptionMapper.class);

    private static final String EXCEPTION_CLASS_NAME = "exceptionClassName";
    private static final String EXCEPTION_MESSAGE = "exceptionMessage";
    private static final String ERROR_CODE = "exceptionErrorCode";

    private static final String CAUSE_EXCEPTION_CLASS_NAME = "causeExceptionClassName";
    private static final String CAUSE_EXCEPTION_MESSAGE = "causeExceptionMessage";
    private static final String CAUSE_COUNT = "causeCount";

    @Override
    public Response toResponse(Throwable exception) {
        Response.Status status = Response.Status.EXPECTATION_FAILED;

        ResponseBuilder rb = Response.status(status);
        rb = rb.header(EXCEPTION_CLASS_NAME, exception.getClass().getName());
        rb = rb.header(EXCEPTION_MESSAGE, exception.getMessage());

        LOG.debug(exception.getMessage(), exception);

        if (exception instanceof SystemException) {
            SystemException systemException = (SystemException) exception;
            rb = rb.header(ERROR_CODE, systemException.getErrorCode());
        }

        Throwable cause = exception;
        LOG.debug("class: {}, message: {}", cause.getClass().getName(), cause.getMessage());

        int causeCount = 0;
        while (cause.getCause() != null) {
            causeCount++;
            cause = cause.getCause();
            LOG.debug("class: {}, message: {}", cause.getClass().getName(), cause.getMessage());

            rb = rb.header(CAUSE_EXCEPTION_CLASS_NAME + causeCount, cause.getClass().getName());
            rb = rb.header(CAUSE_EXCEPTION_MESSAGE + causeCount, cause.getMessage());
        }
        rb = rb.header(CAUSE_COUNT, causeCount);

        return rb.build();
    }

    @Override
    public Throwable fromResponse(Response response) {
        String className = response.getHeaderString(EXCEPTION_CLASS_NAME);
        String message = response.getHeaderString(EXCEPTION_MESSAGE);
        String errorCode = response.getHeaderString(ERROR_CODE);

        if (LOG.isDebugEnabled()) {
            MultivaluedMap<String, Object> map = response.getHeaders();
            for (Map.Entry<String, List<Object>> entry : map.entrySet()) {
                String values = "";
                for (Object value : entry.getValue()) {
                    values += value + ", ";
                }
                LOG.debug("{}: {}", entry.getKey(), values);
            }
        }

        int causeCount = 0;
        try {
            causeCount = Integer.valueOf(response.getHeaderString(CAUSE_COUNT));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        if (causeCount > 0) {
            className = response.getHeaderString(CAUSE_EXCEPTION_CLASS_NAME + causeCount);
            message = response.getHeaderString(CAUSE_EXCEPTION_MESSAGE + causeCount);
            errorCode = null;
        }

        return createThrowableObject(className, errorCode, message);
    }

    @SuppressWarnings("unchecked")
    private static Throwable createThrowableObject(String className, String errorCode, String message) {

        if (StringUtils.isBlank(className)) {
            return null;
        }

        try {
            Class<?> clazz = Class.forName(className);

            if (errorCode == null) {
                return createThrowable(className, message, clazz);
            }

            try {
                Constructor<Throwable> constructor = (Constructor<Throwable>) clazz.getConstructor(String.class,
                        String.class);
                Throwable thr = constructor.newInstance(errorCode, message);
                return thr;
            } catch (Exception e) {
                LOG.info("constructor not found: " + e.getMessage());
                return createThrowable(className, message, clazz);
            }
        } catch (ClassNotFoundException e) {
            LOG.info("Class Not Found: {}", className);
            return new WsRuntimeException(className, message);
        }
    }

    @SuppressWarnings("unchecked")
    private static Throwable createThrowable(String className, String message, Class<?> clazz) {
        try {
            Constructor<Throwable> constructor = (Constructor<Throwable>) clazz.getConstructor(String.class);

            return constructor.newInstance(message);
        } catch (NoSuchMethodException ex) {
            LOG.info("NoSuchMethodException: " + ex.getMessage());
            return new WsRuntimeException(className, message);
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            return new WsRuntimeException(className, message);
        }
    }
}
