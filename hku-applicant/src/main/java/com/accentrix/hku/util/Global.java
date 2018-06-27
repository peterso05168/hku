/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.accentrix.hku.util;

import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import com.accentrix.hku.util.SpringContextHolder;
import com.google.common.collect.Maps;

/**
 * 全局配置类
 * 
 * @author ThinkGem
 * @version 2014-06-25
 */
public class Global {

    /**
     * 当前对象实例
     */
    private static Global global = new Global();

    /**
     * 保存全局属性值
     */
    private static Map<String, String> map = Maps.newHashMap();

    /**
     * 上传文件基础虚拟路径
     */
    public static final String USERFILES_BASE_URL = "/pic/";

    /**
     * 获取当前对象实例
     */
    public static Global getInstance() {
        return global;
    }

    /**
     * 获取配置
     * 
     * @see ${fns:getConfig('adminPath')}
     */
    public static String getConfig(String key) {
        String value = map.get(key);
        if (value == null) {
            Properties p = (Properties) SpringContextHolder.getBean("hkuApplicantProperties");
            value = p.getProperty(key);
            map.put(key, value != null ? value : StringUtils.EMPTY);
        }
        return value;
    }

}
