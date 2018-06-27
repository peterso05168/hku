/*
 * $Id: LeftLessThanRightValidator.java 12009 2014-12-16 03:48:59Z jierong $
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
package com.accentrix.hku.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.accentrix.hku.util.BeanUtil;
import com.accentrix.hku.validation.LeftLessThanRight;

public class LeftLessThanRightValidator implements ConstraintValidator<LeftLessThanRight, Object> {

    private String left;
    private String right;

    @Override
    public void initialize(LeftLessThanRight constraintAnnotation) {
        this.left = constraintAnnotation.left();
        this.right = constraintAnnotation.right();
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {

        if (obj == null) {
            return true;
        }

        Object leftValue = BeanUtil.getPropertyValue(obj, left);
        if (leftValue == null) {
            return true;
        }

        Object rightValue = BeanUtil.getPropertyValue(obj, right);
        if (rightValue == null) {
            return true;
        }

        if (!leftValue.getClass().equals(rightValue.getClass())) {
            return true;
        }

        if (leftValue instanceof Comparable<?>) {
            @SuppressWarnings("unchecked")
            Comparable<Object> lc = (Comparable<Object>) leftValue;
            return (lc.compareTo(rightValue) < 0);
        }

        return true;
    }

}
