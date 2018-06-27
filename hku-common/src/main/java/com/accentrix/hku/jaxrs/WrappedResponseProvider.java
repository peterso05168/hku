/*
 * $Id: WrappedResponseProvider.java 23775 2017-06-22 12:47:57Z jyang $
 * 
 * Copyright (c) 2001-2015 Accentrix Company Limited. All Rights Reserved.
 */
package com.accentrix.hku.jaxrs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.reflect.TypeParameter;
import com.google.common.reflect.TypeToken;

@Provider
@Consumes({ MediaType.APPLICATION_JSON, "text/json" })
@Produces({ MediaType.APPLICATION_JSON, "text/json" })
public class WrappedResponseProvider extends JacksonJaxbJsonProvider {
    private static final Logger LOG = LoggerFactory.getLogger(WrappedResponseProvider.class);

    @Context
    private HttpHeaders headers;

    @Override
    public void writeTo(Object value, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
            MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException {

        LOG.debug("type: {}", type);
        LOG.debug("genericType:{}", genericType);

        if (ResponseObject.class.equals(type)) {
            LOG.debug("no need to wrap with ResponseObject");

            super.writeTo(value, type, genericType, annotations, mediaType, httpHeaders, entityStream);
        } else {
            LOG.debug("wrap with ResponseObject");

            ResponseObject response = new ResponseObject(getRequestId(), Response.Status.OK);
            response.setResponseContent(value);

            super.writeTo(response, response.getClass(), response.getClass(), annotations, mediaType, httpHeaders,
                    entityStream);
        }
    }

    @Override
    public Object readFrom(Class<Object> type, Type genericType, Annotation[] annotations, MediaType mediaType,
            MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException {

        LOG.debug("type: {}", type);
        LOG.debug("genericType:{}", genericType);

        if (ResponseObject.class.equals(type)) {
            return super.readFrom(type, genericType, annotations, mediaType, httpHeaders, entityStream);
        } else {

            Type t = responseObjectOf(TypeToken.of(genericType)).getType();

            ResponseObject response = (ResponseObject) super.readFrom(Object.class, t, annotations, mediaType,
                    httpHeaders, entityStream);

            Object content = response.getResponseContent();
            return content;

        }
    }

    static <T> TypeToken<ResponseObject<T>> responseObjectOf(TypeToken<T> type) {
        return new TypeToken<ResponseObject<T>>() {
            private static final long serialVersionUID = 1L;
        }.where(new TypeParameter<T>() {
        }, type);
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
