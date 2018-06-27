/*
 * $Id: ValidationUtil.java 26949 2018-02-03 10:03:18Z jyang $
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
package com.accentrix.hku.validation.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.accentrix.hku.io.Validatable;

public class ValidationUtil {
    private static final Logger log = LoggerFactory.getLogger(ValidationUtil.class);

    public static final ThreadLocal<List<?>> validatingList = new ThreadLocal<List<?>>();

    public static final ThreadLocal<Map<String, String>> validatingContext = new ThreadLocal<Map<String, String>>();

    private static StringComparator comparator = new StringComparator();

    @SuppressWarnings("unchecked")
    public static <T extends Validatable> List<T> getValidatingList() {
        return (List<T>) validatingList.get();
    }

    public static Map<String, String> getValidatingContext() {
        return validatingContext.get();
    }

    public static boolean validate(Validator validator, Validatable validatable) {
        Set<ConstraintViolation<Validatable>> set = validator.validate(validatable);
        return handleConstraintViolation(validatable, set);
    }

    private static boolean handleConstraintViolation(Validatable validatable,
            Set<ConstraintViolation<Validatable>> set) {
        boolean validateError = false;

        List<String> propMessages = new ArrayList<String>();

        for (ConstraintViolation<Validatable> constraintViolation : set) {
            validateError = true;
            String msg = constraintViolation.getMessage();

            log.warn("code: {}, validation msg: {}", validatable.getKeyString(), msg);

            Object invalidValue = constraintViolation.getInvalidValue();
            if (StringUtils.isNotBlank(msg)) {
                if (invalidValue instanceof Validatable) {
                    validatable.addValidationMsg(msg + ".");
                } else {
                    // it's property validation message
                    String path = constraintViolation.getPropertyPath().toString();
                    if (invalidValue != null && !"".equals(invalidValue)) {
                        propMessages.add(path + ": " + invalidValue + ", " + msg + ".");
                    } else {
                        propMessages.add(path + ": " + msg + ".");
                    }
                }
            }
        }

        // sort the class validation message first
        Collections.sort(validatable.getValidationMsgList(), comparator);
        // then sort the property validation message
        Collections.sort(propMessages, comparator);

        validatable.getValidationMsgList().addAll(propMessages);

        return validateError;
    }

    private static class StringComparator implements Comparator<String>, Serializable {

        private static final long serialVersionUID = 1L;

        public StringComparator() {
            this(true, false);
        }

        public StringComparator(boolean ascending, boolean caseSensitive) {
            _ascending = ascending;
            _caseSensitive = caseSensitive;
        }

        @Override
        public int compare(String s1, String s2) {
            if (s1 == null) {
                s1 = "";
            }

            if (s2 == null) {
                s2 = "";
            }

            if (!_ascending) {
                String temp = s1;

                s1 = s2;
                s2 = temp;
            }

            if (_caseSensitive) {
                return s1.compareTo(s2);
            } else {
                return s1.compareToIgnoreCase(s2);
            }
        }

        private boolean _ascending;
        private boolean _caseSensitive;

    }
}
