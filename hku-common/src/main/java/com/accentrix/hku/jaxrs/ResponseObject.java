/*
 * $Id: ResponseObject.java 23775 2017-06-22 12:47:57Z jyang $
 * 
 * Copyright (c) 2001-2017 Accentrix Company Limited. All Rights Reserved.
 */
package com.accentrix.hku.jaxrs;

import java.io.Serializable;

import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlTransient;

public class ResponseObject<T> implements Serializable {

    private static final long serialVersionUID = 6077124272387744623L;

    public static final String X_REQUEST_ID = "X-Request-ID";

    protected String requestId;

    protected Response.Status status;

    private int responseCode = 200;

    private String responseMsg = "OK";

    private T responseContent;

    public ResponseObject() {
        status = Response.Status.OK;
    }

    public ResponseObject(String requestId, Response.Status status) {
        this.requestId = requestId;
        this.status = status;

        this.responseCode = status.getStatusCode();
        this.responseMsg = status.getReasonPhrase();
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @XmlTransient
    public Response.Status getStatus() {
        return status;
    }

    public void setStatus(Response.Status status) {
        this.status = status;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public T getResponseContent() {
        return responseContent;
    }

    public void setResponseContent(T responseContent) {
        this.responseContent = responseContent;
    }

}
