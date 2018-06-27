/*
 * $Id: Servlets.java 9909 2017-06-13 06:32:27Z jyang $
 * 
 * Copyright (c) 2001-2017 Accentrix Company Limited. All Rights Reserved.
 */
package com.accentrix.hku.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class Servlets {

    public static HttpServletRequest getRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception e) {
            return null;
        }
    }

    public static HttpServletResponse getResponse() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        } catch (Exception e) {
            return null;
        }
    }

    private static final String staticFiles = ".css,.js,.png,.jpg,.gif,.jpeg,.bmp,.ico,.swf,.psd,.htc,.htm,.html,.crx,.xpi,.exe,.ipa,.apk";

    public static boolean isStaticFile(String uri) {
        if (uri.startsWith("/resources") || uri.contains("javax.faces.resource")
                || StringUtils.endsWithAny(uri, staticFiles)) {
            return true;
        }
        return false;
    }
}
