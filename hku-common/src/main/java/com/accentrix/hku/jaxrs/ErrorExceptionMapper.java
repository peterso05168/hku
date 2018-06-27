/*
 * $Id: ErrorExceptionMapper.java 21774 2016-12-21 03:12:27Z jyang $
 * 
 * Copyright (c) 2001-2015 Accentrix Company Limited. All Rights Reserved.
 */
package com.accentrix.hku.jaxrs;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class ErrorExceptionMapper implements ExceptionMapper<Throwable> {
    private static final Logger LOG = LoggerFactory.getLogger(ErrorExceptionMapper.class);

    @Context
    private HttpHeaders headers;

    @Override
    public Response toResponse(Throwable th) {
        LOG.debug("toResponse, {}, {}", th.getClass(), th.getMessage());

        if (th instanceof ErrorException) {
            ErrorException exception = (ErrorException) th;

            Response.Status status = exception.getStatus();

            ResponseObject response = new ResponseObject(exception.getRequestId(), status);

            response.setResponseContent(exception.getErrorVo());

            return Response.status(status).entity(response).type("application/json").build();
        } else {

            ResponseObject response = new ResponseObject(getRequestId(), Response.Status.INTERNAL_SERVER_ERROR);

            String errorMessage = th.getClass().getSimpleName() + ": " + th.getMessage();
            if (th instanceof ClassCastException) {
                if (errorMessage.contains("Cannot cast java.lang.String to java.util.Date")) {
                    errorMessage = "Invalid date format.";
                }
            }

            ErrorVo errorVo = new ErrorVo();
            errorVo.setCauseCode(3005);
            errorVo.setMessage(errorMessage);

            response.setResponseContent(errorVo);

            return Response.status(response.getStatus()).entity(response).type("application/json").build();
        }

    }

    private String getRequestId() {
        if (headers == null) {
            return "JunitTest";
        }
        if (headers.getRequestHeaders().get(ResponseObject.X_REQUEST_ID) == null) {
            return "test";
        }

        return headers.getRequestHeaders().get(ResponseObject.X_REQUEST_ID).get(0);
    }

}
