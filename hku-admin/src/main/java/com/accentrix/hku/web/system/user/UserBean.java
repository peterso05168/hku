package com.accentrix.hku.web.system.user;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang.StringUtils;
import org.primefaces.model.LazyDataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.accentrix.hku.constant.Constants;
import com.accentrix.hku.service.staff.StaffInformationService;
import com.accentrix.hku.util.JSFUtil;
import com.accentrix.hku.util.UIUtil;
import com.accentrix.hku.vo.staff.StaffInformationVo;
import com.accentrix.hku.web.common.InternationalizationBean;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年3月15日 上午9:54:04
 */
@ManagedBean
@ViewScoped
@Configurable(preConstruction = true)
public class UserBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(UserBean.class);

    @Autowired
    private StaffInformationService staffInformationService;

    private StaffInformationVo staffInformationVo;
    private StaffInformationVo saveStaffVo;
    private boolean status;
    private LazyDataModel<StaffInformationVo> lazyDataModel;
    private boolean divStatus;

    public UserBean() {
        LOG.debug("init userbean.......");
        init();
    }

    public void init() {
        staffInformationVo = new StaffInformationVo();
        saveStaffVo = new StaffInformationVo();
        status = false;
        lazyDataModel = new LazyUserDataModel(staffInformationVo);
    }

    public void search() {
        LOG.debug("search staffInformation.......");
        lazyDataModel = new LazyUserDataModel(staffInformationVo);
        status = true;
    }

    public void reset() {
        LOG.debug("reset staffInformation.......");
        init();
    }

    public void save() {
        LOG.debug("save staffInformation.......");
        if (validateContent()) {
            saveStaffVo.setIsActive(true);
            staffInformationService.save(saveStaffVo);
            status = true;
            saveStaffVo = new StaffInformationVo();
            UIUtil.hide("createDialog");
        }
    }

    public void delete(String id) {
        staffInformationService.delete(id);
    }

    public void formEdit(String id) {
        JSFUtil.getSessionMap().put("staffInformationId", id);
    }

    public void createNewUser() {
        saveStaffVo = new StaffInformationVo();
    }

    public void clickSearch(String value) {
        divStatus = Boolean.valueOf(value);
        staffInformationVo = new StaffInformationVo();
    }

    private boolean validateContent() {
        boolean value = true;
        boolean emailMessage = true;
        if (StringUtils.isEmpty(saveStaffVo.getEmail())) {
            UIUtil.setInvalid("saveUser:staffEmail");
            value = false;
        } else {
            String emailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            if (!saveStaffVo.getEmail().matches(emailRegex)) {
                UIUtil.setInvalid("saveUser:staffEmail");
                emailMessage = false;
            }
        }
        if (StringUtils.isEmpty(saveStaffVo.getUsername())) {
            UIUtil.setInvalid("saveUser:userName");
            value = false;
        }
        if (StringUtils.isEmpty(saveStaffVo.getStaffHkuNo())) {
            UIUtil.setInvalid("saveUser:staffNo");
            value = false;
        }
        if (!value) {
            UIUtil.displayManFieldMissingDialog(InternationalizationBean.locale);
            return value;
        } else if (!emailMessage) {
            UIUtil.displayErrorDialog(InternationalizationBean.locale, Constants.ACCOUNT_EMAIL_FORMAT,
                    Constants.ACCOUNT_EMAIL_FORMAT_CHI);
            return emailMessage;
        } else {
            return true;
        }
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public StaffInformationVo getStaffInformationVo() {
        return staffInformationVo;
    }

    public void setStaffInformationVo(StaffInformationVo staffInformationVo) {
        this.staffInformationVo = staffInformationVo;
    }

    public StaffInformationVo getSaveStaffVo() {
        return saveStaffVo;
    }

    public void setSaveStaffVo(StaffInformationVo saveStaffVo) {
        this.saveStaffVo = saveStaffVo;
    }

    public LazyDataModel<StaffInformationVo> getLazyDataModel() {
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<StaffInformationVo> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public boolean getDivStatus() {
        return divStatus;
    }

    public void setDivStatus(boolean divStatus) {
        this.divStatus = divStatus;
    }
}
