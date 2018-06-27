package com.accentrix.hku.utils;

/**
 * 常量类
 * 
 * @author Jaye.Lin
 * @date 创建时间：2018年2月5日 上午11:27:00
 * @version 1.0
 */

public class ConstantsUtils {

    public final static String MAIL_HOST = Global.getConfig("mail.host");
    public final static String MAIL_PORT = Global.getConfig("mail.port");
    public final static String MAIL_USERNAME = Global.getConfig("mail.username");
    public final static String MAIL_PASSWORD = Global.getConfig("mail.password");
    public final static String SERVER_URL = Global.getConfig("server.url");

    /**
     * mail
     */
    public final static String MSA = Global.getConfig("mail.smtp.auth");
    public final static String MSSE = Global.getConfig("mail.smtp.starttls.enable");
    public final static String MSSSLE = Global.getConfig("mail.smtp.ssl.enable");
    public final static boolean MSSSLS = Boolean.valueOf(Global.getConfig("mail.smtp.ssl.socketFactory"));
    public final static String SMTP = Global.getConfig("mail.transport.protocol");
}