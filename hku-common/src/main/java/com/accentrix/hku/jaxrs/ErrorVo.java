/*
 * $Id: ErrorVo.java 21774 2016-12-21 03:12:27Z jyang $
 * 
 * Copyright (c) 2001-2017 Accentrix Company Limited. All Rights Reserved.
 */
package com.accentrix.hku.jaxrs;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ErrorVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private int causeCode;
    private String message;

    public int getCauseCode() {
        return causeCode;
    }

    public void setCauseCode(int causeCode) {
        this.causeCode = causeCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
