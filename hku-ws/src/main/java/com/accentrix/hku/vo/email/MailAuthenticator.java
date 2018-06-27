package com.accentrix.hku.vo.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年2月5日 上午11:26:08
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class MailAuthenticator extends Authenticator {

    /** 用户账号 */
    private String userName;
    /** 用户口令 */
    private String password;

    public MailAuthenticator() {
    }

    /**
     * @param userName
     * @param password
     */
    public MailAuthenticator(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    /**
     * 身份验证
     * 
     * @return
     */
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, password);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
