package com.accentrix.hku.util;

/**
 * 常量类
 * 
 * @author Jaye.Lin
 * @date 创建时间：2018年2月5日 上午11:27:00
 * @version 1.0
 */

public class ConstantsUtils {

    public final static String SERVER_URL = Global.getConfig("server.url");

    /**
     * sso
     */
    public final static String CAS_HOST = Global.getConfig("cas.host");
    public final static String CAS_ROOT = Global.getConfig("cas.root");
    public final static String CAS_LOGOUT = Global.getConfig("cas.logout");
    public final static String AUTHENTICATE_URL = Global.getConfig("authenticate.url");
    public final static String VALIDATE_URL = Global.getConfig("validate.url");
    public final static String LOGOUT_URL = Global.getConfig("logout.url");
    public final static String AUTH_SUCCESS = Global.getConfig("auth.success");
    public final static String SERVICE_RESPONSE = Global.getConfig("service.response");
    public final static String CAS_USER = Global.getConfig("cas.user");
    public final static String CAS_USER_EMAIL = Global.getConfig("cas.user.email");
    public final static String CAS_USER_NAME = Global.getConfig("cas.user.name");
    public final static String CAS_USER_NUMBER = Global.getConfig("cas.user.number");
    public final static String HKU_LOGIN_URL = Global.getConfig("hku.login.url");

    public final static String SIS_NUMBER_PROPERTIES_URL = Global.getConfig("sis.number.properties.url");
}