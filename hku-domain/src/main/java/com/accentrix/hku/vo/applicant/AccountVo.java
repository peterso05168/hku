package com.accentrix.hku.vo.applicant;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年2月1日 下午3:52:54
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class AccountVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String personEmail;

    private String password;

    private String verifyPassword;

    private String userInfoId;

    private String loginToken;

    private Date timeOutDatetime;

    private Date regDate;

    private Date activateDate;

    private String activateCode;

    private Long expTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(String userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    public Date getTimeOutDatetime() {
        return timeOutDatetime;
    }

    public void setTimeOutDatetime(Date timeOutDatetime) {
        this.timeOutDatetime = timeOutDatetime;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Date getActivateDate() {
        return activateDate;
    }

    public void setActivateDate(Date activateDate) {
        this.activateDate = activateDate;
    }

    public String getActivateCode() {
        return activateCode;
    }

    public void setActivateCode(String activateCode) {
        this.activateCode = activateCode;
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }

    public Long getExpTime() {
        return expTime;
    }

    public void setExpTime(Long expTime) {
        this.expTime = expTime;
    }
}
