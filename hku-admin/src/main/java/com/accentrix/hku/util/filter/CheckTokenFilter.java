package com.accentrix.hku.util.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.accentrix.hku.service.staff.StaffInformationService;
import com.accentrix.hku.util.ConstantsUtils;
import com.accentrix.hku.util.SpringContextHolder;
import com.accentrix.hku.util.UserUtils;
import com.accentrix.hku.util.sso.CASAuth;
import com.accentrix.hku.util.sso.CASEnabler;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年3月2日 上午10:22:53
 * @version 1.0
 */

public class CheckTokenFilter implements Filter {

    private static final Logger LOG = LoggerFactory.getLogger(CheckTokenFilter.class);

    private static StaffInformationService staffInformationService = SpringContextHolder
            .getBean(StaffInformationService.class);

    public static final String INDEX_URL = "/hku/admin/dashboard/dashboard.xhtml";
    public static final String LOGIN_URL = "/hku/index/login.xhtml";
    public static final String TICKET = "ticket";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();

        Cookie[] cookies = httpServletRequest.getCookies();
        String uuid = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(UserUtils.UUID)) {
                    uuid = cookie.getValue();
                }
            }
        }
        if (StringUtils.isNotBlank(uuid)) {
            String sessionId = null;
            if (session.getAttribute(uuid) != null) {
                sessionId = session.getAttribute(uuid).toString();
            }
            if (StringUtils.isNotBlank(sessionId) && uuid.equals(sessionId)) {
                chain.doFilter(httpServletRequest, httpServletResponse);
                return;
            } else {
                httpServletResponse
                        .sendRedirect(ConstantsUtils.SERVER_URL + httpServletRequest.getContextPath() + LOGIN_URL);
                return;
            }
        } else {
            String ticket = httpServletRequest.getParameter(TICKET);
            CASAuth CASAuthObj = null;
            if (ticket == null) {
                httpServletResponse
                        .sendRedirect(ConstantsUtils.SERVER_URL + httpServletRequest.getContextPath() + LOGIN_URL);
                return;
            } else {
                try {
                    CASAuthObj = CASEnabler.validate(httpServletRequest, ticket);
                    if (CASAuthObj != null) {
                        boolean status = staffInformationService.checkInformation(CASAuthObj.getUserEmail(),
                                CASAuthObj.getHKUNO());
                        if (status) {
                            session.setAttribute(CASAuthObj.getUID(), CASAuthObj.getUID());
                            session.setAttribute("email", CASAuthObj.getUserEmail());
                            session.setAttribute("hkuno", CASAuthObj.getHKUNO());
                            Cookie cookie = new Cookie(UserUtils.UUID, CASAuthObj.getUID());
                            cookie.setPath(httpServletRequest.getContextPath() + "/");
                            httpServletResponse.addCookie(cookie);
                            httpServletResponse.sendRedirect(
                                    ConstantsUtils.SERVER_URL + httpServletRequest.getContextPath() + INDEX_URL);
                            return;
                        } else {
                            LOG.debug("Without this user!");
                            httpServletResponse.sendRedirect(
                                    ConstantsUtils.SERVER_URL + httpServletRequest.getContextPath() + LOGIN_URL);
                            return;
                        }
                    } else {
                        LOG.debug("Authentication Failure!");
                        httpServletResponse.sendRedirect(
                                ConstantsUtils.SERVER_URL + httpServletRequest.getContextPath() + LOGIN_URL);
                        return;
                    }
                } catch (Exception e) {
                    LOG.debug("fail to validate", e);
                }
            }
        }

    }

    @Override
    public void destroy() {

    }

}
