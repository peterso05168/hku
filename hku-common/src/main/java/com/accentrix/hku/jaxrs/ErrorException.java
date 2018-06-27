/*
 * $Id: ErrorException.java 21774 2016-12-21 03:12:27Z jyang $
 * 
 * Copyright (c) 2001-2015 Accentrix Company Limited. All Rights Reserved.
 */
package com.accentrix.hku.jaxrs;

import javax.ws.rs.core.Response;

public class ErrorException extends Exception {

    private static final long serialVersionUID = 1L;

    private String requestId;

    private Response.Status status;

    private ErrorVo errorVo;

    public ErrorException() {

    }

    public ErrorException(String requestId, Response.Status status, ErrorVo errorVo) {
        super(errorVo.getMessage());
        this.errorVo = errorVo;
        this.requestId = requestId;
        this.status = status;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Response.Status getStatus() {
        return status;
    }

    public void setStatus(Response.Status status) {
        this.status = status;
    }

    public ErrorVo getErrorVo() {
        return errorVo;
    }

    public void setErrorVo(ErrorVo errorVo) {
        this.errorVo = errorVo;
    }

}
