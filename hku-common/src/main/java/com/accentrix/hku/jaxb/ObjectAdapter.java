/*
 * $Id: ObjectAdapter.java 27180 2018-02-11 11:56:16Z jyang $
 * 
 * Copyright (c) 2001-2017 Accentrix Company Limited. All Rights Reserved.
 */
package com.accentrix.hku.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.thoughtworks.xstream.XStream;

public class ObjectAdapter extends XmlAdapter<JaxbObject, Object> {

    @Override
    public Object unmarshal(JaxbObject jo) throws Exception {

        XStream xstream = new XStream();
        return xstream.fromXML(jo.getXml());
    }

    @Override
    public JaxbObject marshal(Object obj) throws Exception {
        JaxbObject jo = new JaxbObject();

        XStream xstream = new XStream();
        jo.setXml(xstream.toXML(obj));
        return jo;
    }

}
