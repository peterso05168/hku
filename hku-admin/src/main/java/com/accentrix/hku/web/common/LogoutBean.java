package com.accentrix.hku.web.common;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.accentrix.hku.util.ConstantsUtils;
import com.accentrix.hku.util.UserUtils;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年3月29日 上午10:09:48
 * @version 1.0
 */

@ManagedBean
@ViewScoped
@Configurable(preConstruction = true)
public class LogoutBean implements Serializable {
    private static final Logger LOG = LoggerFactory.getLogger(LogoutBean.class);

    private static final long serialVersionUID = 1L;

    public LogoutBean() {
    }

    public void logout() {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                    .getRequest();
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(UserUtils.UUID)) {
                    cookie = new Cookie(UserUtils.UUID, null);
                    cookie.setPath(request.getContextPath() + "/");
                    response.addCookie(cookie);
                    request.getSession().removeAttribute(cookie.getValue());
                    request.getSession().removeAttribute("email");
                }
            }
            response.sendRedirect(ConstantsUtils.SERVER_URL + request.getContextPath());
            return;
        } catch (IOException e) {
            LOG.debug(e.getMessage(), e);
        }
    }
}
