/*
 * $Id: ImportObject.java 26937 2018-02-03 05:56:33Z jyang $
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
package com.accentrix.hku.io;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.accentrix.hku.exception.CsvNotMatchException;
import com.accentrix.hku.jaxb.StringMapAdapter;
import com.accentrix.hku.util.CsvUtil;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportObject implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(ImportObject.class);

    private static final long serialVersionUID = 1L;

    private String header;
    private String csv;

    @XmlJavaTypeAdapter(StringMapAdapter.class)
    private Map<String, String> context = new HashMap<String, String>();

    public ImportObject() {
    }

    public ImportObject(String header, String csv) {
        this.header = header;
        this.csv = csv;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getCsv() {
        return csv;
    }

    public void setCsv(String csv) {
        this.csv = csv;
    }

    public Map<String, String> getContext() {
        return context;
    }

    public void setContext(Map<String, String> context) {
        this.context = context;
    }

    private List<String> loadProperties(Class<?> cls) throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(cls);
        PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();

        List<String> props = new ArrayList<String>();

        for (PropertyDescriptor descriptor : descriptors) {
            log.info("bean Property: {}", descriptor.getName());
            String prop = descriptor.getName().toUpperCase().trim().replaceAll("\"", "");
            props.add(prop);
        }

        return props;
    }

    public void validateHeader(Class<?> cls) {

        if (StringUtils.isBlank(header)) {
            throw new CsvNotMatchException("header is blank");
        }

        try {
            List<String> props = loadProperties(cls);

            String[] hp = CsvUtil.replaceHeader(header).split(",");

            for (String prop : hp) {
                if (!props.contains(prop.toUpperCase().trim().replaceAll("\"", ""))) {
                    throw new CsvNotMatchException("property not found: " + prop);
                }
            }

        } catch (IntrospectionException e) {
            log.error(e.getMessage(), e);
            throw new CsvNotMatchException(e);
        }

    }

    public static ImportObject readFrom(InputStream in) throws IOException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));

            String header = null;
            StringBuilder csv = new StringBuilder();

            String line = null;
            for (;;) {
                line = reader.readLine();
                log.info(line);

                if (line == null) {
                    break;
                }

                if (header == null) {
                    line = CsvUtil.replaceHeader(line);
                    header = line;
                }

                csv.append(line).append("\n");
            }

            ImportObject io = new ImportObject();
            io.setCsv(csv.toString());
            io.setHeader(header);
            return io;
        } finally {
            if (reader != null) {
                reader.close();
            }

            in.close();
        }
    }

}
