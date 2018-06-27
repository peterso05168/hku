/*
 * $Id: DateParameterConverterProvider.java 24157 2017-08-03 10:54:25Z winson.zheng $
 *
 * Copyright (c) 2001-2015 Accentrix Company Limited. All Rights Reserved.
 */
package com.accentrix.hku.jaxrs;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Date;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

@Provider
public class DateParameterConverterProvider implements ParamConverterProvider {

    @SuppressWarnings("unchecked")
    @Override
    public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {
        if (Date.class.isAssignableFrom(rawType)) {
            final DateParameterConverter dateParameterConverter = new DateParameterConverter();
            return (ParamConverter<T>) dateParameterConverter;
        }
        return null;
    }

}
