package com.accentrix.hku.util.sys;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.accentrix.hku.service.staff.StaffInformationService;
import com.accentrix.hku.util.SpringContextHolder;
import com.accentrix.hku.util.UserUtils;
import com.accentrix.hku.vo.staff.StaffInformationVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年3月6日 上午11:17:47
 * @version 1.0
 */

public class StaffInfoUtils {

    private static final Logger LOG = LoggerFactory.getLogger(StaffInfoUtils.class);

    private static StaffInformationService staffInformationService = SpringContextHolder
            .getBean(StaffInformationService.class);

    /**
     * 獲取當前登錄對象
     * 
     * @return
     */
    public static StaffInformationVo getStaffInfoVo() {
        StaffInformationVo vo = new StaffInformationVo();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(UserUtils.UUID)) {
                if (StringUtils.isNotBlank(cookie.getValue())) {
                    vo = staffInformationService.get(cookie.getValue());
                }
            }
        }
        LOG.debug("staffInformationVo: {}", vo);
        return vo;
    }

    /**
     * 獲取當前登錄對象id
     * 
     * @return
     */
    public static String getLoginId() {
        String loginId = "";
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(UserUtils.UUID)) {
                loginId = cookie.getValue();
            }
        }
        LOG.debug("loginId: ", loginId);
        return loginId;
    }
}
