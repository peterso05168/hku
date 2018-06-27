package com.accentrix.hku.vo.email;

import java.io.Serializable;
import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.accentrix.hku.utils.ConstantsUtils;
import com.sun.mail.util.MailSSLSocketFactory;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年2月5日 上午11:24:48
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class MailInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 发送邮件的服务器的IP */
    private String mailHost;
    /** 发送邮件的服务器的端口 */
    private String mailPort;
    /** 发送邮件的用户名（邮箱全名称） */
    private String username;
    /** 发送邮件的密码 */
    private String password;

    /** 错误信息发送地址（多个邮件地址以";"分隔） */
    private String errorTo;
    /** 错误信息抄送地址（多个邮件地址以";"分隔） */
    private String errorCc;
    /** 警告信息发送地址（多个邮件地址以";"分隔） */
    private String warningTo;
    /** 警告信息抄送地址（多个邮件地址以";"分隔） */
    private String warningCc;
    /** 通知信息发送地址（多个邮件地址以";"分隔） */
    private String notifyTo;
    /** 通知信息抄送地址（多个邮件地址以";"分隔） */
    private String notifyCc;

    /** 邮件主题 */
    private String subject;
    /** 邮件内容 */
    private String content;
    /** 邮件附件的文件名 */
    private String[] attachFileNames;

    public MailInfo getInstance(MailInfo mailInfo) {
        mailInfo.setMailHost(ConstantsUtils.MAIL_HOST);
        mailInfo.setMailPort(ConstantsUtils.MAIL_PORT);
        mailInfo.setUsername(ConstantsUtils.MAIL_USERNAME);
        mailInfo.setPassword(ConstantsUtils.MAIL_PASSWORD);
        return mailInfo;
    }

    /**
     * 获取邮件参数
     * 
     * @return
     * @throws GeneralSecurityException
     */
    public Properties getProperties() throws GeneralSecurityException {
        Properties props = new Properties();
        props.put("mail.smtp.host", getMailHost());
        props.put("mail.smtp.port", getMailPort());
        props.put("mail.smtp.auth", ConstantsUtils.MSA);
        props.put("mail.smtp.starttls.enable", ConstantsUtils.MSSE);

        MailSSLSocketFactory sslSF = new MailSSLSocketFactory();
        sslSF.setTrustAllHosts(ConstantsUtils.MSSSLS);
        props.put("mail.smtp.ssl.enable", ConstantsUtils.MSSSLE);
        props.put("mail.smtp.ssl.socketFactory", sslSF);
        props.put("mail.transport.protocol", ConstantsUtils.SMTP);

        props.put("mail.user", getUsername());
        props.put("mail.password", getPassword());
        return props;
    }

    /**
     * @return the mailHost
     */
    public String getMailHost() {
        return mailHost;
    }

    /**
     * @param mailHost
     * 
     */
    public void setMailHost(String mailHost) {
        this.mailHost = mailHost;
    }

    /**
     * @return the mailPort
     */
    public String getMailPort() {
        return mailPort;
    }

    /**
     * @param mailPort
     * 
     */
    public void setMailPort(String mailPort) {
        this.mailPort = mailPort;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     * 
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     * 
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the errorTo
     */
    public String getErrorTo() {
        return errorTo;
    }

    /**
     * @param errorTo
     * 
     */
    public void setErrorTo(String errorTo) {
        this.errorTo = errorTo;
    }

    /**
     * @return the errorCc
     */
    public String getErrorCc() {
        return errorCc;
    }

    /**
     * @param errorCc
     * 
     */
    public void setErrorCc(String errorCc) {
        this.errorCc = errorCc;
    }

    /**
     * @return the warningTo
     */
    public String getWarningTo() {
        return warningTo;
    }

    /**
     * @param warningTo
     * 
     */
    public void setWarningTo(String warningTo) {
        this.warningTo = warningTo;
    }

    /**
     * @return the warningCc
     */
    public String getWarningCc() {
        return warningCc;
    }

    /**
     * @param warningCc
     * 
     */
    public void setWarningCc(String warningCc) {
        this.warningCc = warningCc;
    }

    /**
     * @return the notifyTo
     */
    public String getNotifyTo() {
        return notifyTo;
    }

    /**
     * @param notifyTo
     * 
     */
    public void setNotifyTo(String notifyTo) {
        this.notifyTo = notifyTo;
    }

    /**
     * @return the notifyCc
     */
    public String getNotifyCc() {
        return notifyCc;
    }

    /**
     * @param notifyCc
     * 
     */
    public void setNotifyCc(String notifyCc) {
        this.notifyCc = notifyCc;
    }

    /**
     * @return Returns the subject.
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject
     * 
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return Returns the content.
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     * 
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return Returns the attachFileNames.
     */
    public String[] getAttachFileNames() {
        return attachFileNames;
    }

    /**
     * @param attachFileNames
     * 
     */
    public void setAttachFileNames(String[] attachFileNames) {
        this.attachFileNames = attachFileNames;
    }
}
