/*
 * $Id: CsvValidator.java 26964 2018-02-05 05:59:57Z jyang $
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

import java.util.List;
import java.util.Map;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.accentrix.hku.cache.ThreadCacheAdministrator;
import com.accentrix.hku.io.Validatable;
import com.accentrix.hku.wait.ExecuteAndWaitHandler;
import com.accentrix.hku.wait.ExecuteAndWaitUtil;

public class CsvValidator {

    private ExecuteAndWaitHandler executeAndWaitHandler;

    public <T extends Validatable> boolean validate(List<T> list) {
        return validate(null, list);
    }

    public <T extends Validatable> boolean validate(Map<String, String> context, List<T> list) {
        boolean validateError = false;
        try {
            String taskId = ExecuteAndWaitUtil.getCurrentTaskIs();
            ValidationUtil.validatingList.set(list);
            if (context != null) {
                ValidationUtil.validatingContext.set(context);
            }
            ValidatorFactory vf = Validation.buildDefaultValidatorFactory();

            Validator validator = vf.getValidator();

            for (int i = 0; i < list.size(); i++) {
                T validatable = list.get(i);

                if (taskId != null) {
                    String processInfo = "validating import object: " + (i + 1) + "/" + list.size();
                    executeAndWaitHandler.setProcessInfo(taskId, processInfo);
                }
                validateError |= ValidationUtil.validate(validator, validatable);
            }
        } finally {
            ValidationUtil.validatingList.remove();
            ValidationUtil.validatingContext.remove();

            ThreadCacheAdministrator.getInstance().destroyCurrentThreadCache();
        }
        return validateError;
    }

    public void setExecuteAndWaitHandler(ExecuteAndWaitHandler executeAndWaitHandler) {
        this.executeAndWaitHandler = executeAndWaitHandler;
    }
}
