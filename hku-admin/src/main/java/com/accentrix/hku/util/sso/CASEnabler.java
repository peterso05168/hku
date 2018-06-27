package com.accentrix.hku.util.sso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.accentrix.hku.util.ConstantsUtils;

import edu.yale.its.tp.cas.servlet.EncodeQS;

public final class CASEnabler {

    private static final Logger LOG = LoggerFactory.getLogger(CASEnabler.class);

    private static final String EQUAL_SYMBOL = "|||";
    private static final String AND_SYMBOL = "^^^";

    @SuppressWarnings("deprecation")
    public static void checkAuth(HttpServletRequest request, HttpServletResponse response) {
        try {
            String code = request.getQueryString();
            if (code == null) {
                response.sendRedirect(ConstantsUtils.AUTHENTICATE_URL + "?service=" + ConstantsUtils.HKU_LOGIN_URL);
                return;
            } else {
                code = URLDecoder.decode(code);
                code = replace(code, "=", EQUAL_SYMBOL);
                code = replace(code, "&", AND_SYMBOL);
                code = EncodeQS.URLencode(code);
                response.sendRedirect(
                        ConstantsUtils.AUTHENTICATE_URL + "?service=" + ConstantsUtils.HKU_LOGIN_URL + "?" + code);
                return;
            }
        } catch (Exception e) {
            LOG.debug(e.getMessage(), e);
        }

    }

    @SuppressWarnings({ "unused" })
    public static CASAuth validate(HttpServletRequest request, String ticket) throws IOException, Exception {
        String code = request.getQueryString();
        String arg4 = "";
        String arg5 = "";
        String arg6 = "";
        String arg7 = "";
        URL arg8 = null;
        if (code.indexOf("ticket") == 0) {
            arg8 = new URL(
                    ConstantsUtils.VALIDATE_URL + "?ticket=" + ticket + "&service=" + ConstantsUtils.HKU_LOGIN_URL);
        } else {
            code = code.substring(0, code.indexOf("ticket") - 1);
            code = replace(code, "=", EQUAL_SYMBOL);
            code = replace(code, "&", AND_SYMBOL);
            code = EncodeQS.URLencode(code);
            arg8 = new URL(ConstantsUtils.VALIDATE_URL + "?ticket=" + ticket + "&service="
                    + ConstantsUtils.HKU_LOGIN_URL + "?" + code);
        }

        BufferedReader arg9 = new BufferedReader(new InputStreamReader(arg8.openStream()));
        if (arg9 == null) {
            return null;
        } else {
            String arg10 = "";
            String arg11 = arg9.readLine();

            for (arg10 = arg10 + arg11; !arg11.equals(ConstantsUtils.SERVICE_RESPONSE); arg10 = arg10 + arg11) {
                arg11 = arg9.readLine();
            }

            if (authOK(arg10)) {
                arg4 = getTabValue(arg10, ConstantsUtils.CAS_USER);
                arg5 = getTabValue(arg10, ConstantsUtils.CAS_USER_EMAIL);
                arg6 = getTabValue(arg10, ConstantsUtils.CAS_USER_NAME);
                arg7 = getTabValue(arg10, ConstantsUtils.CAS_USER_NUMBER);
                return new CASAuth(arg4, arg5, arg7, arg6);
            } else {
                return null;
            }
        }
    }

    public static void endSession(PrintWriter arg) {
        arg.println("<html><body>Logout<form name=\"form\"></form></body></html>");
    }

    private static String getTabValue(String arg, String arg0) {
        String arg3 = "<" + arg0 + ">";
        String arg4 = "</" + arg0 + ">";
        int arg5 = arg.indexOf(arg3);
        int arg6 = arg.indexOf(arg4);
        return arg.substring(arg5 + arg3.length(), arg6);
    }

    private static boolean authOK(String arg) {
        return arg.indexOf(ConstantsUtils.AUTH_SUCCESS) != -1;
    }

    private static String replace(String arg, String arg0, String arg1) {
        byte arg2 = -1;
        if (arg == null) {
            return arg;
        } else if (arg.indexOf(arg0) == arg2) {
            return arg;
        } else {
            int arg3 = arg.length();
            int arg5 = 0;
            String arg6 = new String();

            try {
                int arg9;
                while ((arg9 = arg.substring(arg5, arg3).indexOf(arg0)) != arg2) {
                    arg6 = arg6 + arg.substring(arg5, arg3).substring(0, arg9) + arg1;
                    arg5 = arg5 + arg9 + arg0.length();
                }

                return arg6 + arg.substring(arg5, arg3);
            } catch (Exception arg8) {
                return "";
            }
        }
    }
}