package com.accentrix.hku.web.index;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.accentrix.hku.constant.ConstantCommon;
import com.accentrix.hku.constant.Constants;
import com.accentrix.hku.service.applicant.AccountService;
import com.accentrix.hku.service.email.MailSenderService;
import com.accentrix.hku.util.ConstantsUtils;
import com.accentrix.hku.util.UIUtil;
import com.accentrix.hku.util.UserUtils;
import com.accentrix.hku.vo.email.MailInfo;
import com.accentrix.hku.web.common.InternationalizationBean;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年3月9日 下午4:20:22
 * @version 1.0
 */

@ManagedBean
@ViewScoped
@Configurable(preConstruction = true)
public class ForgotPasswordBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(ForgotPasswordBean.class);

    @Autowired
    private AccountService accountService;
    @Autowired
    private MailSenderService mailSenderService;

    private String email;

    public ForgotPasswordBean() {

    }

    public String forgotPasswordSendEmail() {
        LOG.info("forgot password send main!");
        if (StringUtils.isNotEmpty(email)) {
            String emailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            if (!email.matches(emailRegex)) {
                UIUtil.displayErrorDialog(InternationalizationBean.locale, Constants.ACCOUNT_EMAIL_FORMAT,
                        Constants.ACCOUNT_EMAIL_FORMAT_CHI);
                return "";
            }
        } else {
            UIUtil.displayErrorDialog(InternationalizationBean.locale, Constants.LOGIN_ACCOUNT_NOTNULL,
                    Constants.LOGIN_ACCOUNT_NOTNULL_CHI);
            return "";
        }
        boolean status = accountService.checkEmailForGotPassword(email);
        if (!status) {
            UIUtil.displayErrorDialog(InternationalizationBean.locale, Constants.FORGOT_PASSWORD_EMAIL,
                    Constants.FORGOT_PASSWORD_EMAIL_CHI);
            return "";
        }
        try {
            MailInfo mailInfo = new MailInfo();
            mailInfo.setNotifyTo(email);
            mailInfo.setNotifyCc(email);
            String emailSubject = "";
            String emailContent = "";
            if (InternationalizationBean.locale.equals(ConstantCommon.LOCALE_UK)) {
                emailSubject = Constants.RESET_PASSWORD;
                emailContent = Constants.RESET_PASSWORD_CONT;
            } else {
                emailSubject = Constants.RESET_PASSWORD_CHI;
                emailContent = Constants.RESET_PASSWORD_CONT_CHI;
            }
            mailInfo.setSubject(emailSubject);
            mailInfo.setContent(emailContent + "<br/>" + ConstantsUtils.SERVER_URL
                    + FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath()
                    + "/hku/index/reset-password.xhtml?email=" + UserUtils.transcodingId(email));
            mailSenderService.sendHtmlMail(mailInfo, 3);
        } catch (Exception e) {
            LOG.debug(e.getMessage(), e);
        }
        return "forgot-password-ack.xhtml?faces-redirect=true";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
