package com.accentrix.hku.jaxrs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.cxf.jaxrs.provider.json.JSONProvider;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonProvider<T> extends JSONProvider<T> {
    private static final Logger LOG = LoggerFactory.getLogger(JsonProvider.class);

    private JacksonJaxbJsonProvider jacksonProvider = new JacksonJaxbJsonProvider();

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {

        LOG.debug("type: {}", type);
        LOG.debug("genericType: {}", genericType);

        boolean isWriteable = super.isWriteable(type, genericType, annotations, mediaType);

        LOG.debug("JSONProvider.isWriteable: {}", isWriteable);
        if (!isWriteable) {
            isWriteable = jacksonProvider.isWriteable(type, genericType, annotations, mediaType);
            LOG.debug("JacksonJaxbJsonProvider.isWriteable: {}", isWriteable);
        }

        return isWriteable;
    }

    @Override
    public void writeTo(T t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
            MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
            throws IOException, WebApplicationException {
        LOG.debug("type: {}", type);
        LOG.debug("genericType: {}", genericType);

        if (super.isWriteable(type, genericType, annotations, mediaType)) {
            super.writeTo(t, type, genericType, annotations, mediaType, httpHeaders, entityStream);
        } else {
            jacksonProvider.writeTo(t, type, genericType, annotations, mediaType, httpHeaders, entityStream);
        }
    }

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        LOG.debug("type: {}", type);
        LOG.debug("genericType: {}", genericType);

        boolean isReadable = super.isReadable(type, genericType, annotations, mediaType);
        LOG.debug("JSONProvider.isReadable: {}", isReadable);

        if (!isReadable) {
            isReadable = jacksonProvider.isReadable(type, genericType, annotations, mediaType);
        }

        return isReadable;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T readFrom(Class<T> type, Type genericType, Annotation[] annotations, MediaType mediaType,
            MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
            throws IOException, WebApplicationException {
        if (super.isReadable(type, genericType, annotations, mediaType)) {
            return super.readFrom(type, genericType, annotations, mediaType, httpHeaders, entityStream);
        } else {
            return (T) jacksonProvider.readFrom((Class<Object>) type, genericType, annotations, mediaType, httpHeaders,
                    entityStream);
        }
    }
}
