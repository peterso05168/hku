/*
 * $Id: DefaultExceptionHandler4Popup.java 12740 2015-02-12 06:25:28Z jierong $
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
package com.accentrix.hku.exception.handler;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.accentrix.hku.exception.WsRuntimeException;
import com.accentrix.hku.util.MessageUtil;
import com.accentrix.hku.util.UIUtil;

public class DefaultExceptionHandler4Popup implements ExceptionHandler4Popup {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultExceptionHandler4Popup.class);

    @Override
    public void handle(Throwable t, String popup, String message, String messageKeyPrefix) {
        LOG.info("handling Throwable: {}, popup: {}", t.getMessage(), popup);

        String exClassName = t.getClass().getName();

        if (t instanceof WsRuntimeException) {
            LOG.debug("it is WsRuntimeException");
            WsRuntimeException wsEx = (WsRuntimeException) t;
            exClassName = wsEx.getCauseClassName();
        }

        LOG.debug("exClassName: {}", exClassName);
        LOG.debug("exception msg: {}", t.getMessage());

        if (StringUtils.isNotBlank(message)) {
            UIUtil.addErrorMessage(null, message);
        } else {
            String detail = getDetail(t, exClassName);

            String localizedMessage = getLocalizedMessage(exClassName, messageKeyPrefix, detail);

            UIUtil.addErrorMessage(null, localizedMessage);
        }

        UIUtil.show(popup);
    }

    private String getDetail(Throwable t, String exClassName) {
        return t.getMessage();
    }

    private String getLocalizedMessage(String exClassName, String messageKeyPrefix, String detail) {
        String msg = MessageUtil.getMessage(messageKeyPrefix + exClassName, exClassName, detail);

        if (!msg.equals(messageKeyPrefix + exClassName)) {
            return msg;
        }

        if (StringUtils.isNotBlank(messageKeyPrefix)) {
            msg = MessageUtil.getMessage(exClassName, exClassName, detail);

            if (!msg.equals(exClassName)) {
                return msg;
            }
        }

        msg = MessageUtil.getMessage("default_exception_msg", exClassName, detail);

        if (msg.equals("default_exception_msg")) {
            msg = detail;
        }

        return msg;
    }
}
