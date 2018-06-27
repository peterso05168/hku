/*
 * $Id: Validatable.java 26964 2018-02-05 05:59:57Z jyang $
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

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Validatable implements Serializable {
    private static final Logger LOG = LoggerFactory.getLogger(Validatable.class);

    private static final long serialVersionUID = 1L;

    public static final String SUCCESS_CREATED = "Created";

    public static final String SUCCESS_UPDATED = "Updated";

    private List<String> validationMsgList = new ArrayList<String>();

    private List<String> warningMsgList = new ArrayList<String>();

    private String successMsg;

    public abstract String getKeyString();

    public static String[] convertHeaderToColumns(String header) {
        return header.trim().replaceAll("\"", "").split(",");
    }

    public Object getPropertyValue(String property) {
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equals(property)) {
                try {
                    field.setAccessible(true);
                    return field.get(this);
                } catch (IllegalArgumentException e) {
                    LOG.error(e.getMessage(), e);
                } catch (IllegalAccessException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }

        LOG.warn("property not found: {}", property);

        return null;
    }

    public void addValidationMsg(String validationMsg) {
        this.validationMsgList.add(validationMsg);
    }

    public List<String> getValidationMsgList() {
        return validationMsgList;
    }

    public void setValidationMsgList(List<String> validationMsgList) {
        this.validationMsgList = validationMsgList;
    }

    public String getSuccessMsg() {
        return successMsg;
    }

    public void setSuccessMsg(String successMsg) {
        this.successMsg = successMsg;
    }

    public void addWarningMsg(String warningMsg) {
        this.warningMsgList.add(warningMsg);
    }

    public List<String> getWarningMsgList() {
        return warningMsgList;
    }

    public void setWarningMsgList(List<String> warningMsgList) {
        this.warningMsgList = warningMsgList;
    }

}
