/*
 * $Id: DateParameterConverter.java 24105 2017-08-01 03:22:57Z winson.zheng $
 *
 * Copyright (c) 2001-2015 Accentrix Company Limited. All Rights Reserved.
 */
package com.accentrix.hku.jaxrs;

import java.util.Date;
import java.util.TimeZone;

import javax.ws.rs.ext.ParamConverter;

import org.apache.commons.lang.time.DateFormatUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateParameterConverter implements ParamConverter<Date> {

    private static final Logger LOG = LoggerFactory.getLogger(DateParameterConverter.class);

    private static final String PATTERN = "yyyy-MM-dd'T'HH:mm:ssZZ";

    @Override
    public Date fromString(String v) {
        if (v == null || v.length() == 0) {
            return null;
        }

        DateTimeFormatter parser = ISODateTimeFormat.dateTimeParser();

        try {
            DateTime jodatime = parser.parseDateTime(v);
            return jodatime.toDate();
        } catch (Exception e) {
            LOG.warn(e.getMessage());
            return new Date(v);
        }

    }

    @Override
    public String toString(Date v) {
        if (v == null) {
            return null;
        }

        return DateFormatUtils.format(v, PATTERN, TimeZone.getDefault());
    }

}
