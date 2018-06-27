package com.accentrix.hku.web.management.application;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.accentrix.hku.constant.Constants;
import com.accentrix.hku.service.app.OthersService;
import com.accentrix.hku.service.app.ProgressService;
import com.accentrix.hku.service.app.TransCreditsService;
import com.accentrix.hku.service.applicant.ApplicationService;
import com.accentrix.hku.service.general.RefCdService;
import com.accentrix.hku.util.JSFUtil;
import com.accentrix.hku.util.UIUtil;
import com.accentrix.hku.vo.app.OthersVo;
import com.accentrix.hku.vo.app.ProgressVo;
import com.accentrix.hku.vo.app.TransCreditsVo;
import com.accentrix.hku.vo.applicant.ApplicationVo;
import com.accentrix.hku.vo.general.RefCdVo;
import com.accentrix.hku.web.common.InternationalizationBean;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年5月31日 上午10:51:53
 */
@ManagedBean
@ViewScoped
@Configurable(preConstruction = true)
public class EditOtherBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(EditOtherBean.class);

    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private RefCdService refCdService;
    @Autowired
    private OthersService othersService;
    @Autowired
    private ProgressService progressService;
    @Autowired
    private TransCreditsService transCreditsService;

    private ApplicationVo applicationVo;
    private OthersVo othersVo;
    private ProgressVo progressVo;
    private List<RefCdVo> disabiliTypes;
    private TransCreditsVo transCreditsVo;
    private String applicationId;

    public EditOtherBean() {
        init();
    }

    public void init() {
        LOG.debug("others");
        transCreditsVo = new TransCreditsVo();
        applicationId = (String) JSFUtil.getSessionMap().get("applicationId");
        retrieveData(applicationId);
    }

    /**
     * 
     * @param applicationId
     */
    private void retrieveData(String applicationId) {
        if (StringUtils.isNotBlank(applicationId)) {
            applicationVo = applicationService.get(applicationId);
        }
        String othersId = "";
        if (applicationVo != null) {
            othersId = applicationVo.getOthersId();
            transCreditsVo = transCreditsService.getByApplicationId(applicationVo.getId());
        }
        if (StringUtils.isNotBlank(othersId)) {
            othersVo = othersService.get(othersId);
            if (othersVo.getDeRegYear() != null) {
                othersVo.setInputDeRegYear(othersVo.getDeRegYear().toString());
            }
            if (othersVo.getDisconYear() != null) {
                othersVo.setInputDisconYear(othersVo.getDisconYear().toString());
            }
            if (othersVo.getNotSuccessAppYear() != null) {
                othersVo.setInputNotSuccessAppYear(othersVo.getNotSuccessAppYear().toString());
            }
        } else {
            othersVo = new OthersVo();
        }
        othersVo.setIsSubmitted(false);
        disabiliTypes = refCdService.findListByType(Constants.DISABILITYTYPE);
    }

    public boolean save() {
        if (!validataOthers()) {
            return false;
        }
        if (StringUtils.isNotEmpty(othersVo.getInputDeRegYear())) {
            othersVo.setDeRegYear(Integer.valueOf(othersVo.getInputDeRegYear()));
        } else {
            othersVo.setDeRegYear(null);
        }
        if (StringUtils.isNotEmpty(othersVo.getInputDisconYear())) {
            othersVo.setDisconYear(Integer.valueOf(othersVo.getInputDisconYear()));
        } else {
            othersVo.setDisconYear(null);
        }
        if (StringUtils.isNotEmpty(othersVo.getInputNotSuccessAppYear())) {
            othersVo.setNotSuccessAppYear(Integer.valueOf(othersVo.getInputNotSuccessAppYear()));
        } else {
            othersVo.setNotSuccessAppYear(null);
        }
        if (validataTransCredits(transCreditsVo)) {
            transCreditsVo.setApplicationId(applicationVo.getId());
            transCreditsService.save(transCreditsVo);
        } else {
            if (StringUtils.isNotBlank(transCreditsVo.getId())) {
                transCreditsService.delete(transCreditsVo.getId());
            }
        }
        othersVo = othersService.save(othersVo);
        if (othersVo != null) {
            if (StringUtils.isNotBlank(applicationId)) {
                progressVo = progressService.findByApplicationId(applicationId);
                progressVo.setOthers(true);
                progressService.save(progressVo);
            }
            if (!othersVo.getIsSubmitted()) {
                applicationVo = applicationService.get(applicationId);
                applicationVo.setStatus(Constants.STATUS_SUBMITTED);
                applicationVo = applicationService.save(applicationVo);
            }
        }

        UIUtil.displaySaveSuccessDialog(InternationalizationBean.locale);
        return true;
    }

    public boolean validataTransCredits(TransCreditsVo transCredits) {
        if (StringUtils.isEmpty(transCredits.getYearOfStudy()) && StringUtils.isEmpty(transCredits.getCurriculum())
                && !transCredits.getIsApplyDirEntry() && !transCredits.getIsApplyTransCredits()) {
            return false;
        }
        return true;
    }

    public boolean validataOthers() {
        boolean valid = true;
        progressVo = progressService.findByApplicationId(applicationId);
        if (!othersVo.getIsSubmitted()) {
            if (!progressVo.getPrsnalPart()) {
                UIUtil.displayErrorDialog(InternationalizationBean.locale, Constants.PERSONAL_PARTICULARS_WRONG,
                        Constants.PERIOD_FROM_WRONG_CHI);
                valid = false;
            }
            if (!progressVo.getAcadBg()) {
                UIUtil.displayErrorDialog(InternationalizationBean.locale, Constants.ACADEMIC_PROFILE_WRONG,
                        Constants.ACADEMIC_PROFILE_WRONG_CHI);
                valid = false;
            }
            if (!progressVo.getChoiceOfCurri()) {
                UIUtil.displayErrorDialog(InternationalizationBean.locale, Constants.CHOICE_OF_PROGRAMME_WRONG,
                        Constants.CHOICE_OF_PROGRAMME_WRONG_CHI);
                valid = false;
            }
        }
        if (!valid) {
            return false;
        } else {
            return true;
        }
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public List<RefCdVo> getDisabiliTypes() {
        return disabiliTypes;
    }

    public void setDisabiliTypes(List<RefCdVo> disabiliTypes) {
        this.disabiliTypes = disabiliTypes;
    }

    public ApplicationVo getApplicationVo() {
        return applicationVo;
    }

    public void setApplicationVo(ApplicationVo applicationVo) {
        this.applicationVo = applicationVo;
    }

    public OthersVo getOthersVo() {
        return othersVo;
    }

    public void setOthersVo(OthersVo othersVo) {
        this.othersVo = othersVo;
    }

    public ProgressVo getProgressVo() {
        return progressVo;
    }

    public void setProgressVo(ProgressVo progressVo) {
        this.progressVo = progressVo;
    }

    public TransCreditsVo getTransCreditsVo() {
        return transCreditsVo;
    }

    public void setTransCreditsVo(TransCreditsVo transCreditsVo) {
        this.transCreditsVo = transCreditsVo;
    }
}
