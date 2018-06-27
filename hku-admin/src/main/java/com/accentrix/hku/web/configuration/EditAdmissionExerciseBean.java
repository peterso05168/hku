package com.accentrix.hku.web.configuration;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.accentrix.hku.constant.ConstantCommon;
import com.accentrix.hku.service.adm.ExeService;
import com.accentrix.hku.service.adm.FormProgService;
import com.accentrix.hku.service.adm.FormService;
import com.accentrix.hku.service.app.HkuProgrammeService;
import com.accentrix.hku.util.JSFUtil;
import com.accentrix.hku.util.UIUtil;
import com.accentrix.hku.vo.adm.ExeVo;
import com.accentrix.hku.vo.adm.FormProgVo;
import com.accentrix.hku.vo.adm.FormVo;
import com.accentrix.hku.vo.app.HkuProgrammeVo;
import com.accentrix.hku.web.common.InternationalizationBean;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年3月28日 上午10:17:35
 */
@ManagedBean
@ViewScoped
@Configurable(preConstruction = true)
public class EditAdmissionExerciseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(EditAdmissionExerciseBean.class);

    @Autowired
    private ExeService exeService;
    @Autowired
    private FormProgService formProgService;
    @Autowired
    private FormService formService;
    @Autowired
    private HkuProgrammeService hkuProgrammeService;

    private ExeVo exeVo;
    private List<FormProgVo> formProgVos;
    private List<HkuProgrammeVo> hkuProgrammeVos;
    private FormProgVo formProgVo;
    private FormVo formVo;

    public EditAdmissionExerciseBean() {
        init();
    }

    public void init() {
        LOG.debug("init editAdmissionExerciseBean....");

        String exeId = (String) JSFUtil.getSessionMap().get("exeId");
        retrieveData(exeId);
    }

    private void retrieveData(String exeId) {
        if (StringUtils.isNotBlank(exeId)) {
            exeVo = exeService.get(exeId);
            formVo = formService.getByAdmExeId(exeVo.getId());
            formProgVos = formProgService.getByAdmFormId(formVo.getId());
        } else {
            exeVo = new ExeVo();
        }
        hkuProgrammeVos = hkuProgrammeService.findList();
    }

    public void editFormProgramme(String formProgId) {
        if (StringUtils.isNotBlank(formProgId)) {
            formProgVo = formProgService.get(formProgId);
        } else {
            formProgVo = new FormProgVo();
        }
        for (HkuProgrammeVo hkuProgrammeVo : hkuProgrammeVos) {
            hkuProgrammeVo.setHkuProgDisabled(false);
        }
        for (FormProgVo formProg : formProgVos) {
            for (HkuProgrammeVo hkuProgrammeVo : hkuProgrammeVos) {
                if (formProg.getAppHkuProgrammeId().equals(hkuProgrammeVo.getId())
                        && !formProg.getAppHkuProgrammeId().equals(formProgVo.getAppHkuProgrammeId())) {
                    hkuProgrammeVo.setHkuProgDisabled(true);
                    break;
                }
            }
        }
    }

    public void saveFormProgramme() {
        if (validFormProgramme()) {
            // save
            formProgVo.setAdmFormId(formVo.getId());
            formProgVo.setQuotaLocal(Integer.valueOf(formProgVo.getLocal()));
            formProgVo.setQuotaOverseas(Integer.valueOf(formProgVo.getOverseas()));
            formProgVo.setQuotaMainland(Integer.valueOf(formProgVo.getMainland()));
            formProgService.save(formProgVo);
            formProgVos = formProgService.getByAdmFormId(formVo.getId());
            UIUtil.execute("PF('createDialog').hide()");
            UIUtil.displaySaveSuccessDialog(InternationalizationBean.locale);
        } else {
            UIUtil.displayErrorDialog(InternationalizationBean.locale, ConstantCommon.MADATORY_FIELD_MISSING,
                    ConstantCommon.MADATORY_FIELD_MISSING_CHI);
        }
    }

    public boolean validFormProgramme() {
        boolean valid = true;
        if (StringUtils.isBlank(formProgVo.getAppHkuProgrammeId())) {
            UIUtil.setInvalid(":formEditProgramme:programme");
            valid = false;
        }
        if (StringUtils.isBlank(formProgVo.getLocal())) {
            UIUtil.setInvalid(":formEditProgramme:offerQuotaLocal");
            valid = false;
        }
        if (StringUtils.isBlank(formProgVo.getOverseas())) {
            UIUtil.setInvalid(":formEditProgramme:offerQuotaOverseas");
            valid = false;
        }
        if (StringUtils.isBlank(formProgVo.getMainland())) {
            UIUtil.setInvalid(":formEditProgramme:offerQuotaMainland");
            valid = false;
        }
        if (!valid) {
            return false;
        } else {
            return true;
        }
    }

    public ExeVo getExeVo() {
        return exeVo;
    }

    public void setExeVo(ExeVo exeVo) {
        this.exeVo = exeVo;
    }

    public List<FormProgVo> getFormProgVos() {
        return formProgVos;
    }

    public void setFormProgVos(List<FormProgVo> formProgVos) {
        this.formProgVos = formProgVos;
    }

    public List<HkuProgrammeVo> getHkuProgrammeVos() {
        return hkuProgrammeVos;
    }

    public void setHkuProgrammeVos(List<HkuProgrammeVo> hkuProgrammeVos) {
        this.hkuProgrammeVos = hkuProgrammeVos;
    }

    public FormProgVo getFormProgVo() {
        return formProgVo;
    }

    public void setFormProgVo(FormProgVo formProgVo) {
        this.formProgVo = formProgVo;
    }
}
