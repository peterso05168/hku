package com.accentrix.hku.util.sys;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.accentrix.hku.service.applicant.AccountService;
import com.accentrix.hku.util.SpringContextHolder;
import com.accentrix.hku.util.UserUtils;
import com.accentrix.hku.vo.applicant.AccountVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年3月6日 上午11:17:47
 * @version 1.0
 */

public class AccountUtils {

    private static final Logger LOG = LoggerFactory.getLogger(AccountUtils.class);

    private static AccountService accountService = SpringContextHolder.getBean(AccountService.class);

    /**
     * 獲取當前登錄對象
     * @return
     */
    public static AccountVo getAccountVo() {
        AccountVo vo = new AccountVo();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(UserUtils.TOKEN)) {
                if (StringUtils.isNotBlank(cookie.getValue())) {
                    String loginId = cookie.getValue();
                    loginId = loginId.substring(0, 32);
                    vo = accountService.get(loginId);
                }
            }
        }
        LOG.debug("accountVo: {}", vo);
        return vo;
    }

    /**
     * 獲取當前登錄對象id
     * @return
     */
    public static String getLoginId() {
        String loginId = "";
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(UserUtils.TOKEN)) {
                loginId = cookie.getValue();
                loginId = loginId.substring(0, 32);
            }
        }
        LOG.debug("loginId: ", loginId);
        return loginId;
    }
}
