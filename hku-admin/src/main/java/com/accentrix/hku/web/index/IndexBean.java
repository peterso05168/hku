package com.accentrix.hku.web.index;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.accentrix.hku.service.staff.PrivilegeService;
import com.accentrix.hku.service.staff.StaffInformationService;
import com.accentrix.hku.vo.staff.StaffInformationVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年5月14日 下午4:02:57
 * @version 1.0
 */

@ManagedBean
@ApplicationScoped
@Configurable(preConstruction = true)
public class IndexBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private StaffInformationService staffInformationService;
    @Autowired
    private PrivilegeService privilegeService;

    public boolean findModule(String module, String moduleCode) {
        // HttpServletRequest request = ((ServletRequestAttributes)
        // RequestContextHolder.getRequestAttributes())
        // .getRequest();
        // HttpSession session = request.getSession();
        // if (session.getAttribute("email") != null &&
        // session.getAttribute("hkuno") != null) {
        // String email = session.getAttribute("email").toString();
        // String hkuno = session.getAttribute("hkuno").toString();
        String email = "222@qq.com";
        String hkuno = "001";
        StaffInformationVo vo = staffInformationService.findByEmailAndHkuNo(email, hkuno);
        if (vo != null && StringUtils.isNotBlank(vo.getId())) {
            return privilegeService.findModule(vo.getId(), module, moduleCode);
        }
        // }
        return false;
    }

}
