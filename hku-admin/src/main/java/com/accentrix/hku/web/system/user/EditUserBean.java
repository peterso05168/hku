package com.accentrix.hku.web.system.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.accentrix.hku.constant.ConstantCommon;
import com.accentrix.hku.constant.Constants;
import com.accentrix.hku.service.app.HkuProgrammeService;
import com.accentrix.hku.service.staff.ProgService;
import com.accentrix.hku.service.staff.RoleRelService;
import com.accentrix.hku.service.staff.RoleService;
import com.accentrix.hku.service.staff.StaffInformationService;
import com.accentrix.hku.util.JSFUtil;
import com.accentrix.hku.util.UIUtil;
import com.accentrix.hku.vo.app.HkuProgrammeVo;
import com.accentrix.hku.vo.staff.ProgVo;
import com.accentrix.hku.vo.staff.RoleRelVo;
import com.accentrix.hku.vo.staff.RoleVo;
import com.accentrix.hku.vo.staff.StaffInformationVo;
import com.accentrix.hku.web.common.InternationalizationBean;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年3月15日 下午1:20:14
 */
@ManagedBean
@ViewScoped
@Configurable(preConstruction = true)
public class EditUserBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(UserBean.class);

    @Autowired
    private RoleService roleService;
    @Autowired
    private HkuProgrammeService hkuProgrammeService;
    @Autowired
    private StaffInformationService staffInformationService;
    @Autowired
    private RoleRelService roleRelService;
    @Autowired
    private ProgService progService;

    private StaffInformationVo staffInformationVo;
    private List<RoleVo> roles;
    private List<HkuProgrammeVo> hkuProgrammes;
    private String staffInformationId;

    public EditUserBean() {
        LOG.debug("init editUserBean...");
        roles = new ArrayList<RoleVo>();
        hkuProgrammes = new ArrayList<HkuProgrammeVo>();
        staffInformationId = (String) JSFUtil.getSessionMap().get("staffInformationId");
        if (StringUtils.isNotBlank(staffInformationId)) {
            staffInformationVo = staffInformationService.get(staffInformationId);
            roles = roleService.findVos(staffInformationId);
            hkuProgrammes = hkuProgrammeService.findVos(staffInformationId);
        }
    }

    public String save() {
        if (!validateContent()) {
            UIUtil.displayManFieldMissingDialog(InternationalizationBean.locale);
            return null;
        }
        staffInformationService.save(staffInformationVo);
        return "search.xhtml?faces-redirect=true";
    }

    public void saveRoleRel() {
        roleRelService.deleteByStaffId(staffInformationId);
        List<RoleRelVo> roleRelVos = new ArrayList<RoleRelVo>();
        for (RoleVo roleVo : roles) {
            if (roleVo.getIsRoleId()) {
                RoleRelVo roleRelVo = new RoleRelVo();
                roleRelVo.setStaffInfoId(staffInformationId);
                roleRelVo.setStaffRoleId(roleVo.getId());
                roleRelVos.add(roleRelVo);
            }
        }
        if (roleRelVos.size() > 0) {
            roleRelService.save(roleRelVos);
        }
        UIUtil.displayErrorDialog(InternationalizationBean.locale, ConstantCommon.SAVE_SUCCESS,
                ConstantCommon.SAVE_SUCCESS_CHI);
    }

    public void saveProg() {
        progService.deleteByStaffId(staffInformationId);
        List<ProgVo> progVos = new ArrayList<ProgVo>();
        for (HkuProgrammeVo vo : hkuProgrammes) {
            if (vo.getIsHkuProgrammeId()) {
                ProgVo progVo = new ProgVo();
                progVo.setHkuProgrammeId(vo.getId());
                progVo.setStaffId(staffInformationId);
                progVos.add(progVo);
            }
        }
        if (progVos.size() > 0) {
            progService.save(progVos);
        }
        UIUtil.displayErrorDialog(InternationalizationBean.locale, ConstantCommon.SAVE_SUCCESS,
                ConstantCommon.SAVE_SUCCESS_CHI);
    }

    private boolean validateContent() {
        boolean value = true;
        if (StringUtils.isEmpty(staffInformationVo.getUsername())) {
            UIUtil.setInvalid("userTabView:formStaffInfo:username");
            value = false;
        }
        if (StringUtils.isEmpty(staffInformationVo.getEmail())) {
            UIUtil.setInvalid("userTabView:formStaffInfo:email");
            value = false;
        } else {
            String emailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            if (!staffInformationVo.getEmail().matches(emailRegex)) {
                UIUtil.setInvalid("userTabView:formStaffInfo:email");
                UIUtil.displayErrorDialog(InternationalizationBean.locale, Constants.ACCOUNT_EMAIL_FORMAT,
                        Constants.ACCOUNT_EMAIL_FORMAT_CHI);
                value = false;
            }
        }
        if (StringUtils.isEmpty(staffInformationVo.getStaffHkuNo())) {
            UIUtil.setInvalid("userTabView:formStaffInfo:staffNo");
            value = false;
        }
        if (staffInformationVo.getIsActive() == null) {
            UIUtil.setInvalid("userTabView:formStaffInfo:status");
            value = false;
        }
        return value;
    }

    public List<RoleVo> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleVo> roles) {
        this.roles = roles;
    }

    public List<HkuProgrammeVo> getHkuProgrammes() {
        return hkuProgrammes;
    }

    public void setHkuProgrammes(List<HkuProgrammeVo> hkuProgrammes) {
        this.hkuProgrammes = hkuProgrammes;
    }

    public StaffInformationVo getStaffInformationVo() {
        return staffInformationVo;
    }

    public void setStaffInformationVo(StaffInformationVo staffInformationVo) {
        this.staffInformationVo = staffInformationVo;
    }

    public String getStaffInformationId() {
        return staffInformationId;
    }

    public void setStaffInformationId(String staffInformationId) {
        this.staffInformationId = staffInformationId;
    }
}
