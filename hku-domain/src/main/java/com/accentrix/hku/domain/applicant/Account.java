package com.accentrix.hku.domain.applicant;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午6:11:28
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "applicant_account")
@ApiObject(name = "Account")
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "applicant_account_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "person_email")
    @ApiObjectField(description = "personEmail")
    private String personEmail;

    @Column(name = "password")
    @ApiObjectField(description = "password")
    private String password;

    @Column(name = "user_info_id")
    @ApiObjectField(description = "userInfoId")
    private String userInfoId;

    @Column(name = "login_token")
    @ApiObjectField(description = "loginToken")
    private String loginToken;

    @Column(name = "time_out_datetime")
    @ApiObjectField(description = "timeOutDatetime")
    private Date timeOutDatetime;

    @Column(name = "reg_date")
    @ApiObjectField(description = "regDate")
    private Date regDate;

    @Column(name = "activate_date")
    @ApiObjectField(description = "activateDate")
    private Date activateDate;

    @Column(name = "activate_code")
    @ApiObjectField(description = "activateCode")
    private String activateCode;

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
}
