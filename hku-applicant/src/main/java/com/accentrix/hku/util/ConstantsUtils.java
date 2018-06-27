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
    public final static String FILE_PATH = Global.getConfig("file.path");

    /**
     * login
     */
    public final static long LOGIN_TIME = Long.parseLong(Global.getConfig("login.time"));
    public final static long MIN_TIME = Long.parseLong(Global.getConfig("min.time"));
    public final static long EXTEND_TIME = Long.parseLong(Global.getConfig("extend.time"));
}