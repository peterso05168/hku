package com.accentrix.hku.web.system.role;

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
import com.accentrix.hku.service.staff.PrivilegeService;
import com.accentrix.hku.service.staff.RolePrivilegeService;
import com.accentrix.hku.service.staff.RoleService;
import com.accentrix.hku.util.JSFUtil;
import com.accentrix.hku.util.UIUtil;
import com.accentrix.hku.vo.staff.PrivilegeVo;
import com.accentrix.hku.vo.staff.RoleVo;
import com.accentrix.hku.web.common.InternationalizationBean;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年3月15日 下午5:04:01
 */
@ManagedBean
@ViewScoped
@Configurable(preConstruction = true)
public class EditRoleBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(EditRoleBean.class);

    @Autowired
    private PrivilegeService privilegeService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RolePrivilegeService rolePrivilegeService;

    private List<PrivilegeVo> privileges;
    private RoleVo roleVo;
    private String roleId;

    public EditRoleBean() {
        LOG.debug("init editRoleBean...");
        roleVo = new RoleVo();
        privileges = new ArrayList<PrivilegeVo>();
        roleId = (String) JSFUtil.getSessionMap().get("roleId");
        if (StringUtils.isNotBlank(roleId)) {
            roleVo = roleService.get(roleId);
            privileges = privilegeService.findVos(roleId);
        }
    }

    public String save() {
        if (!validateContent()) {
            UIUtil.displayManFieldMissingDialog(InternationalizationBean.locale);
            return null;
        }
        roleService.save(roleVo);
        return "search.xhtml?faces-redirect=true";
    }

    public void savePrivilege() {
        List<String> ids = new ArrayList<String>();
        for (PrivilegeVo vo : privileges) {
            ids.addAll(vo.getPrivilegeIds());
        }
        if (ids.size() > 0) {
            rolePrivilegeService.saveRolePrivilege(roleId, ids);
        }
        UIUtil.displayErrorDialog(InternationalizationBean.locale, ConstantCommon.SAVE_SUCCESS,
                ConstantCommon.SAVE_SUCCESS_CHI);
    }

    private boolean validateContent() {
        boolean value = true;
        if (StringUtils.isEmpty(roleVo.getRoleName())) {
            UIUtil.setInvalid("roleTabView:formRole:roleName");
            value = false;
        }
        return value;
    }

    public List<PrivilegeVo> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<PrivilegeVo> privileges) {
        this.privileges = privileges;
    }

    public RoleVo getRoleVo() {
        return roleVo;
    }

    public void setRoleVo(RoleVo roleVo) {
        this.roleVo = roleVo;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

}
