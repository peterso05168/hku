package com.accentrix.hku.web.system;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.accentrix.hku.constant.Constants;
import com.accentrix.hku.domain.common.ResponseData;
import com.accentrix.hku.service.applicant.AccountService;
import com.accentrix.hku.service.applicant.AnncmntService;
import com.accentrix.hku.util.UIUtil;
import com.accentrix.hku.util.UserUtils;
import com.accentrix.hku.util.jwt.JWT;
import com.accentrix.hku.vo.applicant.AccountVo;
import com.accentrix.hku.vo.applicant.AnncmntVo;
import com.accentrix.hku.web.common.InternationalizationBean;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年3月1日 下午3:35:24
 * @version 1.0
 */

@ManagedBean
@ViewScoped
@Configurable(preConstruction = true)
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(LoginBean.class);

    private AccountVo accountVo;

    private String loginId;

    @Autowired
    private AccountService accountService;
    @Autowired
    private AnncmntService anncmntService;

    private ResponseData responseData;

    public LoginBean() {
        LOG.info("login loading...");
        accountVo = new AccountVo();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        loginId = request.getParameter("id");
        if (StringUtils.isNotBlank(loginId)) {
            boolean status = accountService.activationAccount(loginId);
            if (status) {
                sendAnnoucement(loginId);
                UIUtil.displayErrorDialog(InternationalizationBean.locale, Constants.ACTIVATION_ACCOUNT,
                        Constants.ACTIVATION_ACCOUNT_CHI);
            } else {
                UIUtil.displayErrorDialog(InternationalizationBean.locale, Constants.ACTIVATION_ACCOUNT_FAULURE,
                        Constants.ACTIVATION_ACCOUNT_FAULURE_CHI);
            }
        }
        String email = request.getParameter("email");
        if (StringUtils.isNotBlank(email) && email.equals("fail")) {
            UIUtil.displayErrorDialog(InternationalizationBean.locale, Constants.RESET_PASSWORD_FAULURE,
                    Constants.RESET_PASSWORD_FAULURE_CHI);
        }
    }

    public String login() {
        LOG.info("Login .....");
        if (!validateContent()) {
            return "";
        }
        responseData = ResponseData.ok();
        // 先到数据库验证
        AccountVo vo = accountService.checkLogin(accountVo);
        if (null != vo && StringUtils.isNotBlank(vo.getId())) {
            // 给用户jwt加密生成token
            String token = JWT.sign(vo);
            token = vo.getId() + token;

            // 封装成对象返回给客户端
            responseData.putDataValue(UserUtils.TOKEN, token);
            responseData.putDataValue(UserUtils.USER, vo);

            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

            Cookie cookie = new Cookie(UserUtils.TOKEN, token);
            cookie.setPath(FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath() + "/");
            response.addCookie(cookie);

            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                    .getRequest();
            HttpSession session = request.getSession();
            session.setAttribute("email", vo.getPersonEmail());
            return "/hku/public/my-inbox/edit.xhtml?faces-redirect=true";
        } else {
            UIUtil.displayErrorDialog(InternationalizationBean.locale, Constants.LOGIN_ACCOUNT,
                    Constants.LOGIN_ACCOUNT_CHI);
            responseData = ResponseData.customerError();
            return "";
        }
    }

    private boolean validateContent() {
        boolean value = true;
        if (StringUtils.isNotBlank(accountVo.getPersonEmail())) {
            String personEmailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            if (!accountVo.getPersonEmail().matches(personEmailRegex)) {
                UIUtil.displayErrorDialog(InternationalizationBean.locale, Constants.ACCOUNT_EMAIL_FORMAT,
                        Constants.ACCOUNT_EMAIL_FORMAT_CHI);
                value = false;
            }
        } else {
            UIUtil.displayErrorDialog(InternationalizationBean.locale, Constants.LOGIN_ACCOUNT_NOTNULL,
                    Constants.LOGIN_ACCOUNT_NOTNULL_CHI);
            value = false;
        }
        if (StringUtils.isBlank(accountVo.getPassword())) {
            UIUtil.displayErrorDialog(InternationalizationBean.locale, Constants.LOGIN_PASSWORD_NOTNULL,
                    Constants.LOGIN_PASSWORD_NOTNULL_CHI);
            value = false;
        } else {
            if (accountVo.getPassword().length() < 6) {
                UIUtil.displayErrorDialog(InternationalizationBean.locale, Constants.PASSWORD_FORMAT,
                        Constants.PASSWORD_FORMAT_CHI);
                value = false;
            }
        }
        return value;
    }

    public String logout() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(UserUtils.TOKEN)) {
                LOG.info("clean out token cookie...");
                cookie = new Cookie(UserUtils.TOKEN, null);
                cookie.setPath(
                        FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath() + "/");
                response.addCookie(cookie);
            }
        }
        request.getSession().removeAttribute("email");
        return "/hku/index/login.xhtml?faces-redirect=true";
    }

    public void sendAnnoucement(String id) {
        id = UserUtils.decodeId(id);
        AccountVo account = accountService.get(id);
        if (account != null) {
            AnncmntVo anncmntVo = new AnncmntVo();
            anncmntVo.setTypeCd(Constants.WELCOME_CD);
            anncmntVo.setValue(Constants.WELCOME_VALUE);
            anncmntVo.setStatusCd(Constants.ANNCMNT_STATUS_NEW);
            anncmntVo.setIssueDate(new Date());
            anncmntVo.setApplicantAccountId(account.getId());
            anncmntVo.setApplicationNo("");
            anncmntVo.setIsRead(false);
            anncmntService.save(anncmntVo);
        }
    }

    public AccountVo getAccountVo() {
        return accountVo;
    }

    public void setAccountVo(AccountVo accountVo) {
        this.accountVo = accountVo;
    }

    public ResponseData getResponseData() {
        return responseData;
    }

    public void setResponseData(ResponseData responseData) {
        this.responseData = responseData;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
}
