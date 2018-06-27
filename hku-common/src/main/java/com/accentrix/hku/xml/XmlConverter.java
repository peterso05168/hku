package com.accentrix.hku.xml;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Lonny Wei
 * @date 2018年3月23日 下午4:03:53
 * @version 1.0
 */
public class XmlConverter {
    private XmlConverter() {
    }

    public static String toXml(Object obj) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XMLEncoder encoder = new XMLEncoder(out);
        encoder.writeObject(obj);
        encoder.close();
        String xml = out.toString().trim();
        return xml;
    }

    @SuppressWarnings("resource")
    public static Object fromXml(String xml) {
        if (StringUtils.isNotBlank(xml)) {
            ByteArrayInputStream in;
            try {
                in = new ByteArrayInputStream(xml.trim().getBytes("UTF-8"));
                XMLDecoder decoder = new XMLDecoder(in);
                Object obj = decoder.readObject();
                return obj;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
