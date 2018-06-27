package com.accentrix.hku.web.index;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.accentrix.hku.constant.Constants;
import com.accentrix.hku.service.applicant.AccountService;
import com.accentrix.hku.util.UIUtil;
import com.accentrix.hku.web.common.InternationalizationBean;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年3月9日 下午6:20:45
 * @version 1.0
 */

@ManagedBean
@ViewScoped
@Configurable(preConstruction = true)
public class ResetPasswordBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(ResetPasswordBean.class);

    @Autowired
    private AccountService accountService;

    private String email;

    private String newPassword;
    private String confirmNewPassword;

    public ResetPasswordBean() {
        LOG.info("Reset Password Loading......");
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                    .getRequest();
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
            email = request.getParameter("email");
            if (StringUtils.isNotBlank(email)) {
                boolean status = accountService.checkResetPasswordEmail(email);
                if (!status) {
                    response.sendRedirect(
                            FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath()
                                    + "/hku/index/login.xhtml?email=fail");
                }
            } else {
                response.sendRedirect(FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath()
                        + "/hku/index/login.xhtml?email=fail");
            }
        } catch (Exception e) {
            LOG.debug(e.getMessage(), e);
        }
    }

    public String resetPassword() {
        if (StringUtils.isNotBlank(newPassword)) {
            if (newPassword.length() < 6) {
                UIUtil.displayErrorDialog(InternationalizationBean.locale, Constants.PASSWORD_FORMAT,
                        Constants.PASSWORD_FORMAT_CHI);
                return "";
            } else {
                String letterRegex = "^[a-zA-Z0-9_.+-]*(?:[a-zA-Z][a-zA-Z0-9_.+-]*){2,}$";
                String numRegex = "^(?=(?:\\D*\\d){2,})[a-zA-Z0-9_.+-]*$";
                if (!newPassword.matches(letterRegex) || !newPassword.matches(numRegex)) {
                    UIUtil.displayErrorDialog(InternationalizationBean.locale, Constants.PASSWORD_FORMAT,
                            Constants.PASSWORD_FORMAT_CHI);
                    return "";
                }
            }
        } else {
            UIUtil.displayErrorDialog(InternationalizationBean.locale, Constants.LOGIN_PASSWORD_NOTNULL,
                    Constants.LOGIN_PASSWORD_NOTNULL_CHI);
            return "";
        }
        if (StringUtils.isNotBlank(confirmNewPassword)) {
            if (!newPassword.equals(confirmNewPassword)) {
                UIUtil.displayErrorDialog(InternationalizationBean.locale, Constants.RESET_PASSWORD_EQUALS,
                        Constants.RESET_PASSWORD_EQUALS_CHI);
                return "";
            }
        } else {
            UIUtil.displayErrorDialog(InternationalizationBean.locale, Constants.LOGIN_CONFIRM_PASSWORD_NOTNULL,
                    Constants.LOGIN_CONFIRM_PASSWORD_NOTNULL_CHI);
            return "";
        }
        accountService.resetPassword(email, newPassword);
        return "login.xhtml?faces-redirect=true";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }
}
