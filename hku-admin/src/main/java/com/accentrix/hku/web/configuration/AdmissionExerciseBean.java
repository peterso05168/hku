package com.accentrix.hku.web.configuration;

import java.io.Serializable;
import java.util.Date;
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
import com.accentrix.hku.service.adm.ExeService;
import com.accentrix.hku.service.adm.FormService;
import com.accentrix.hku.util.JSFUtil;
import com.accentrix.hku.util.UIUtil;
import com.accentrix.hku.vo.adm.ExeVo;
import com.accentrix.hku.vo.adm.FormVo;
import com.accentrix.hku.web.common.InternationalizationBean;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年3月19日 上午10:07:48
 */
@ManagedBean
@ViewScoped
@Configurable(preConstruction = true)
public class AdmissionExerciseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(AdmissionExerciseBean.class);

    @Autowired
    private ExeService exeService;
    @Autowired
    private FormService formService;

    private List<ExeVo> exes;
    private ExeVo exeVo;
    private FormVo formVo;
    private String admissionYear;

    public AdmissionExerciseBean() {
        init();
    }

    public void init() {
        LOG.debug("init admissionExerciseBean...");
        exes = exeService.findList();
    }

    public void createExe() {
        admissionYear = "";
        exeVo = new ExeVo();
        exeVo.setDisplaySixthChoice(false);
        formVo = new FormVo();
    }

    public String saveExe() {
        if (validateExe()) {
            // save
            exeVo.setAdmissionYear(Integer.valueOf(admissionYear));
            exeVo = exeService.save(exeVo);

            formVo.setAdmExeId(exeVo.getId());
            formVo.setDescription(Constants.ADMISSION_FORM + exeVo.getAdmissionYear());
            formVo.setDescChi(Constants.ADMISSION_FORM + exeVo.getAdmissionYear());
            formVo = formService.save(formVo);
            exes = exeService.findList();

            JSFUtil.getSessionMap().put("exeId", exeVo.getId());
            return "edit.xhtml?faces-redirect=true";
        } else {
            return "";
        }
    }

    public void formEdit(String id) {
        JSFUtil.getSessionMap().put("exeId", id);
    }

    @SuppressWarnings("deprecation")
    public boolean validateExe() {
        boolean valid = true;
        boolean existAdmissionYear = false;
        boolean wrongDateFormat = false;
        if (StringUtils.isBlank(admissionYear)) {
            UIUtil.setInvalid(":formAdmissionYear:admissionYear");
            valid = false;
        } else {
            // 验证 admissionYear 是否已经存在
            exes = exeService.findList();
            for (ExeVo exeVo : exes) {
                if (Integer.valueOf(admissionYear).equals(exeVo.getAdmissionYear())) {
                    existAdmissionYear = true;
                    UIUtil.setInvalid(":formAdmissionYear:admissionYear");
                    break;
                }
            }
            // 小于今年
            if (Integer.valueOf(admissionYear) < new Date().getYear() + 1900) {
                wrongDateFormat = true;
                UIUtil.setInvalid(":formAdmissionYear:admissionYear");
            }
        }

        if (exeVo.getApplicationStartDate() == null) {
            valid = false;
            UIUtil.setInvalid(":formAdmissionYear:applicationStartDate");
        } else {
            // 小于今年
            if (exeVo.getApplicationStartDate().getYear() < new Date().getYear()) {
                wrongDateFormat = true;
                UIUtil.setInvalid(":formAdmissionYear:applicationStartDate");
            }
        }

        if (exeVo.getApplicationEndDate() == null) {
            valid = false;
            UIUtil.setInvalid(":formAdmissionYear:applicationEndtDate");
        } else {
            if (exeVo.getApplicationEndDate().getYear() < new Date().getYear()) {
                wrongDateFormat = true;
                UIUtil.setInvalid(":formAdmissionYear:applicationEndtDate");
            }
        }

        if (exeVo.getProgrammeChoiceEndDate() == null) {
            valid = false;
            UIUtil.setInvalid(":formAdmissionYear:programmeChoiceEndDate");
        } else {
            if (exeVo.getProgrammeChoiceEndDate().getYear() < new Date().getYear()) {
                wrongDateFormat = true;
                UIUtil.setInvalid(":formAdmissionYear:programmeChoiceEndDate");
            }
        }

        if (exeVo.getMfExcellentScheEndDate() == null) {
            valid = false;
            UIUtil.setInvalid(":formAdmissionYear:mfExcellentScheEndDate");
        } else {
            if (exeVo.getMfExcellentScheEndDate().getYear() < new Date().getYear()) {
                wrongDateFormat = true;
                UIUtil.setInvalid(":formAdmissionYear:mfExcellentScheEndDate");
            }
        }

        if (exeVo.getAdmCycleEndDate() == null) {
            valid = false;
            UIUtil.setInvalid(":formAdmissionYear:cycleEndDate");
        } else {
            if (exeVo.getAdmCycleEndDate().getYear() < new Date().getYear()) {
                wrongDateFormat = true;
                UIUtil.setInvalid(":formAdmissionYear:cycleEndDate");
            }
        }

        if (!valid && (wrongDateFormat && existAdmissionYear)) {
            UIUtil.displayErrorDialog(InternationalizationBean.locale, ConstantCommon.MADATORY_FIELD_MISSING,
                    ConstantCommon.MADATORY_FIELD_MISSING_CHI);
            UIUtil.displayErrorDialog(InternationalizationBean.locale, ConstantCommon.WRONG_DATE_FORMAT,
                    ConstantCommon.WRONG_DATE_FORMAT_CHI);
            UIUtil.displayErrorDialog(InternationalizationBean.locale, Constants.ADMISSION_YEAR_EXIST,
                    Constants.ADMISSION_YEAR_EXIST_CHI);
            return false;
        } else if (!valid && existAdmissionYear) {
            UIUtil.displayErrorDialog(InternationalizationBean.locale, ConstantCommon.MADATORY_FIELD_MISSING,
                    ConstantCommon.MADATORY_FIELD_MISSING_CHI);
            UIUtil.displayErrorDialog(InternationalizationBean.locale, Constants.ADMISSION_YEAR_EXIST,
                    Constants.ADMISSION_YEAR_EXIST_CHI);
            return false;
        } else if (!valid && wrongDateFormat) {
            UIUtil.displayErrorDialog(InternationalizationBean.locale, ConstantCommon.MADATORY_FIELD_MISSING,
                    ConstantCommon.MADATORY_FIELD_MISSING_CHI);
            UIUtil.displayErrorDialog(InternationalizationBean.locale, ConstantCommon.WRONG_DATE_FORMAT,
                    ConstantCommon.WRONG_DATE_FORMAT_CHI);
            return false;
        } else if (existAdmissionYear && wrongDateFormat) {
            UIUtil.displayErrorDialog(InternationalizationBean.locale, Constants.ADMISSION_YEAR_EXIST,
                    Constants.ADMISSION_YEAR_EXIST_CHI);
            UIUtil.displayErrorDialog(InternationalizationBean.locale, ConstantCommon.WRONG_DATE_FORMAT,
                    ConstantCommon.WRONG_DATE_FORMAT_CHI);
            return false;
        } else if (!valid) {
            UIUtil.displayErrorDialog(InternationalizationBean.locale, ConstantCommon.MADATORY_FIELD_MISSING,
                    ConstantCommon.MADATORY_FIELD_MISSING_CHI);
            return false;
        } else if (wrongDateFormat) {
            UIUtil.displayErrorDialog(InternationalizationBean.locale, ConstantCommon.WRONG_DATE_FORMAT,
                    ConstantCommon.WRONG_DATE_FORMAT_CHI);
            return false;
        } else if (existAdmissionYear) {
            UIUtil.displayErrorDialog(InternationalizationBean.locale, Constants.ADMISSION_YEAR_EXIST,
                    Constants.ADMISSION_YEAR_EXIST_CHI);
            return false;
        } else {
            return true;
        }
    }

    public List<ExeVo> getExes() {
        return exes;
    }

    public void setExes(List<ExeVo> exes) {
        this.exes = exes;
    }

    public ExeVo getExeVo() {
        return exeVo;
    }

    public void setExeVo(ExeVo exeVo) {
        this.exeVo = exeVo;
    }

    public FormVo getFormVo() {
        return formVo;
    }

    public void setFormVo(FormVo formVo) {
        this.formVo = formVo;
    }

    public String getAdmissionYear() {
        return admissionYear;
    }

    public void setAdmissionYear(String admissionYear) {
        this.admissionYear = admissionYear;
    }
}
