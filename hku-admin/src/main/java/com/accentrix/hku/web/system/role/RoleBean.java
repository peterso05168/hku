package com.accentrix.hku.web.system.role;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang.StringUtils;
import org.primefaces.model.LazyDataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.accentrix.hku.service.staff.RoleService;
import com.accentrix.hku.util.JSFUtil;
import com.accentrix.hku.util.UIUtil;
import com.accentrix.hku.vo.staff.RoleVo;
import com.accentrix.hku.web.common.InternationalizationBean;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年3月15日 下午3:00:41
 */
@ManagedBean
@ViewScoped
@Configurable(preConstruction = true)
public class RoleBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(RoleBean.class);

    @Autowired
    private RoleService roleService;

    private LazyDataModel<RoleVo> lazyDataModel;
    private RoleVo roleVo;
    private RoleVo saveRole;
    private boolean status;
    private boolean divStatus;

    public RoleBean() {
        init();
    }

    public void init() {
        LOG.debug("init roleBean...");
        status = false;
        roleVo = new RoleVo();
        saveRole = new RoleVo();
        lazyDataModel = new LazyRoleDataModel(roleVo);
    }

    public void search() {
        LOG.debug("search role...");
        status = true;
        lazyDataModel = new LazyRoleDataModel(roleVo);
    }

    public void reset() {
        init();
    }

    public void save() {
        if (!validateContent()) {
            UIUtil.displayManFieldMissingDialog(InternationalizationBean.locale);
        } else {
            saveRole.setIsActive(true);
            roleService.save(saveRole);
            status = true;
            saveRole = new RoleVo();
            UIUtil.hide("createDialog");
        }
    }

    public void delete(String id) {
        roleService.delete(id);
    }

    private boolean validateContent() {
        boolean value = true;
        if (StringUtils.isEmpty(saveRole.getRoleName())) {
            UIUtil.setInvalid("saveForm:roleName");
            value = false;
        }
        return value;
    }

    public String formEdit(String id) {
        JSFUtil.getSessionMap().put("roleId", id);
        return "edit.xhtml?faces-redirect=true";
    }

    public void createNewRole() {
        saveRole = new RoleVo();
    }

    public void clickSearch(String value) {
        divStatus = Boolean.valueOf(value);
        roleVo = new RoleVo();
    }

    public LazyDataModel<RoleVo> getLazyDataModel() {
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<RoleVo> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public RoleVo getRoleVo() {
        return roleVo;
    }

    public void setRoleVo(RoleVo roleVo) {
        this.roleVo = roleVo;
    }

    public RoleVo getSaveRole() {
        return saveRole;
    }

    public void setSaveRole(RoleVo saveRole) {
        this.saveRole = saveRole;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getDivStatus() {
        return divStatus;
    }

    public void setDivStatus(boolean divStatus) {
        this.divStatus = divStatus;
    }

}
