package com.accentrix.hku.web.myApplication;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.accentrix.hku.constant.Constants;
import com.accentrix.hku.service.adm.ExeService;
import com.accentrix.hku.service.adm.FormService;
import com.accentrix.hku.service.app.OthersService;
import com.accentrix.hku.service.app.ProgressService;
import com.accentrix.hku.service.app.ReqDocConfService;
import com.accentrix.hku.service.app.ReqDocService;
import com.accentrix.hku.service.app.TransCreditsService;
import com.accentrix.hku.service.applicant.ApplicationService;
import com.accentrix.hku.service.general.RefCdService;
import com.accentrix.hku.util.ConstantsUtils;
import com.accentrix.hku.util.JSFUtil;
import com.accentrix.hku.util.UIUtil;
import com.accentrix.hku.util.UserUtils;
import com.accentrix.hku.util.app.AuditLogUtil;
import com.accentrix.hku.util.sys.AccountUtils;
import com.accentrix.hku.vo.adm.ExeVo;
import com.accentrix.hku.vo.adm.FormVo;
import com.accentrix.hku.vo.app.OthersVo;
import com.accentrix.hku.vo.app.ProgressVo;
import com.accentrix.hku.vo.app.ReqDocConfVo;
import com.accentrix.hku.vo.app.ReqDocVo;
import com.accentrix.hku.vo.app.TransCreditsVo;
import com.accentrix.hku.vo.applicant.AccountVo;
import com.accentrix.hku.vo.applicant.ApplicationVo;
import com.accentrix.hku.vo.general.RefCdVo;
import com.accentrix.hku.web.common.InternationalizationBean;

@ManagedBean
@ViewScoped
@Configurable(preConstruction = true)
public class OthersBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(OthersBean.class);

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
    @Autowired
    private FormService formService;
    @Autowired
    private ExeService exeService;
    @Autowired
    private ReqDocConfService reqDocConfService;
    @Autowired
    private ReqDocService reqDocService;

    private ApplicationVo applicationVo;
    private OthersVo othersVo;
    private ProgressVo progressVo;
    private List<RefCdVo> disabiliTypes;
    private TransCreditsVo transCreditsVo;
    private String applicationId;

    public OthersBean() {
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

    public void toPayment() {
        try {
            return;
        } catch (Exception e) {
            LOG.debug("跳转失败！", e);
        }
    }

    public boolean save() {
        boolean i = save_others();
        if (StringUtils.isNoneBlank(applicationVo.getStatus())
                && applicationVo.getStatus().equals(Constants.STATUS_IN_PROCESS)) {
            // 支付前修改转态为支付中
            applicationVo.setStatus(Constants.STATUS_IN_PAYMENT);
            applicationVo = applicationService.save(applicationVo);
            try {
                FacesContext context = FacesContext.getCurrentInstance();
                HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
                Cookie cookie = new Cookie(UserUtils.APPLICATION_ID, applicationId);
                cookie.setPath(
                        FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath() + "/");
                response.addCookie(cookie);
                ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                externalContext.redirect(
                        ConstantsUtils.SERVER_URL + "PG/pg/pg_redirect?templatesPath=" + ConstantsUtils.FILE_PATH);
            } catch (IOException e) {
                LOG.debug("to payment page fail", e);
            }
        }
        return i;
    }

    private boolean save_others() {
        applicationVo = applicationService.get(applicationId);
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
            transCreditsVo.setApplicationId(applicationId);
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
            AccountVo accountVo = AccountUtils.getAccountVo();
            AuditLogUtil.saveAuditLog(accountVo.getId(), Constants.SAVED_OTHERS);
        }

        addDocumentRequirement(Constants.DOC_TYPE_PIS, Constants.DOC_CD_PI, "Personal Identification", applicationId);
        addDocumentRequirement(Constants.DOC_TYPE_PIS, Constants.DOC_CD_PS, "Personal Statement", applicationId);

        UIUtil.displaySaveSuccessDialog(InternationalizationBean.locale);

        return true;
    }

    private void addDocumentRequirement(String reqDocType, String reqDocCd, String reqDocName, String applicationId) {
        ReqDocConfVo reqDocConfVo = reqDocConfService.getByTypeAndCdAndName(reqDocType, reqDocCd, reqDocName);
        if (reqDocConfVo == null) {
            reqDocConfVo = new ReqDocConfVo();
            reqDocConfVo.setReqDocType(reqDocType);
            reqDocConfVo.setReqDocCd(reqDocCd);
            reqDocConfVo.setReqDocName(reqDocName);
            reqDocConfVo = reqDocConfService.save(reqDocConfVo);
        }
        ReqDocVo reqDocVo = reqDocService.getByApplicationIdAndReqDocConfIdAndQualificationId(applicationId,
                reqDocConfVo.getId(), null);
        if (reqDocVo == null) {
            reqDocVo = new ReqDocVo();
            reqDocVo.setApplicationId(applicationId);
            reqDocVo.setStatusCd(Constants.WAITING_FOR_UPLOAD);
            ApplicationVo applicationVo = applicationService.get(applicationId);
            FormVo formVo = formService.get(applicationVo.getAdmFormId());
            ExeVo exeVo = exeService.get(formVo.getAdmExeId());
            reqDocVo.setSubmissionDueDate(exeVo.getApplicationEndDate());
            reqDocVo.setReqDocConfId(reqDocConfVo.getId());
            reqDocService.save(reqDocVo);
        }
    }

    public void prePage(String index) {
        othersVo.setIsSubmitted(true);
        if (save_others()) {
            ApplicationBean applicationBean = JSFUtil.getCurrentInstance("applicationBean", ApplicationBean.class);
            applicationBean.prePage(index);
        }
        othersVo.setIsSubmitted(false);
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
