/*
 * $Id: DateAdapter.java 13515 2015-04-27 08:25:25Z jierong $
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
package com.accentrix.hku.jaxb;

import java.util.Date;
import java.util.TimeZone;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.apache.commons.lang.time.DateFormatUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateAdapter extends XmlAdapter<String, Date> {
    private static final Logger LOG = LoggerFactory.getLogger(DateAdapter.class);

    private static final String PATTERN = "yyyy-MM-dd'T'HH:mm:ssZZ";

    @SuppressWarnings("deprecation")
    @Override
    public Date unmarshal(String v) throws Exception {
        if (v == null || v.length() == 0) {
            return null;
        }

        DateTimeFormatter parser = ISODateTimeFormat.dateTimeParser();
        // DateTimeFormatter dtf = DateTimeFormat.forPattern(PATTERN);

        try {
            DateTime jodatime = parser.parseDateTime(v);
            return jodatime.toDate();
        } catch (Exception e) {
            LOG.warn(e.getMessage());
            return new Date(v);
        }

    }

    @Override
    public String marshal(Date v) throws Exception {
        if (v == null) {
            return null;
        }

        return DateFormatUtils.format(v, PATTERN, TimeZone.getDefault());
    }
}
