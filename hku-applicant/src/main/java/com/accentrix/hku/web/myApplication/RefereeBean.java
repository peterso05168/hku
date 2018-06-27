package com.accentrix.hku.web.myApplication;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.accentrix.hku.constant.ConstantCommon;
import com.accentrix.hku.constant.Constants;
import com.accentrix.hku.service.app.AcadBgService;
import com.accentrix.hku.service.app.CounselorService;
import com.accentrix.hku.service.app.ProgressService;
import com.accentrix.hku.service.app.QualificationService;
import com.accentrix.hku.service.app.RefereeService;
import com.accentrix.hku.service.applicant.ApplicationService;
import com.accentrix.hku.service.general.RefCdService;
import com.accentrix.hku.util.JSFUtil;
import com.accentrix.hku.util.UIUtil;
import com.accentrix.hku.util.app.AuditLogUtil;
import com.accentrix.hku.util.sys.AccountUtils;
import com.accentrix.hku.vo.app.AcadBgVo;
import com.accentrix.hku.vo.app.CounselorVo;
import com.accentrix.hku.vo.app.ProgressVo;
import com.accentrix.hku.vo.app.QualificationVo;
import com.accentrix.hku.vo.app.RefereeVo;
import com.accentrix.hku.vo.app.ReferenceVo;
import com.accentrix.hku.vo.applicant.AccountVo;
import com.accentrix.hku.vo.applicant.ApplicationVo;
import com.accentrix.hku.vo.general.RefCdVo;
import com.accentrix.hku.web.common.InternationalizationBean;

@ManagedBean
@ViewScoped
@Configurable(preConstruction = true)
public class RefereeBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(RefereeBean.class);

    @Autowired
    private RefereeService refereeService;
    @Autowired
    private RefCdService refCdService;
    @Autowired
    private CounselorService counselorService;
    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private ProgressService progressService;
    @Autowired
    private AcadBgService acadBgService;
    @Autowired
    private QualificationService qualificationService;

    private List<RefCdVo> relationships;
    private List<CounselorVo> counselors;
    private List<RefereeVo> refereeVos;
    private CounselorVo counselorVo;
    private RefereeVo refereeVo;
    private ReferenceVo referenceVo;
    private ApplicationVo applicationVo;
    private Boolean isActiveRefName;
    private Boolean isActiveOthersRefName;
    private String applicationId;
    private ProgressVo progressVo;
    private boolean isActiceRefree;

    public RefereeBean() {
        init();
    }

    public void init() {
        LOG.debug("init");
        applicationId = (String) JSFUtil.getSessionMap().get("applicationId");
        retrieveData(applicationId);
        activeReference();
    }

    /**
     * 
     * @param applicationId
     */
    private void retrieveData(String applicationId) {
        refereeVo = new RefereeVo();
        if (StringUtils.isNotBlank(applicationId)) {
            applicationVo = applicationService.get(applicationId);
            refereeVos = refereeService.findListByReferenceId(applicationVo.getReferenceId());
        }
        relationships = refCdService.findListByType(Constants.REFEREE_RELATIONSHIP);
        isActiveRefName = true;
        isActiveOthersRefName = false;
        counselors = counselorService.findList();
    }

    public void loadName(AjaxBehaviorEvent event) {
        SelectOneMenu menu = (SelectOneMenu) event.getSource();
        String value = menu.getValue().toString();
        if (value != null && value.equals(Constants.COUNSELOR)) {
            isActiveRefName = true;
            isActiveOthersRefName = false;
            counselors = counselorService.findList();
        } else if (value.equals("select")) {
            isActiveRefName = true;
            isActiveOthersRefName = false;
            counselors = null;
        } else {
            isActiveRefName = false;
            isActiveOthersRefName = true;
        }
    }

    public void loadEmail(AjaxBehaviorEvent event) {
        SelectOneMenu menu = (SelectOneMenu) event.getSource();
        String value = menu != null && menu.getValue() != null ? menu.getValue().toString() : "";
        if (StringUtils.isNotBlank(value)) {
            counselorVo = counselorService.findByFullName(value);
            refereeVo.setEmail(counselorVo.getEmail());
        } else {
            refereeVo.setEmail(null);
        }
    }

    public boolean save() {
        if (!validateReferee()) {
            return false;
        }
        try {
            counselorVo = counselorService.findByFullName(refereeVo.getName());
            if (counselorVo != null) {
                refereeVo.setReferenceId(applicationVo.getReferenceId());
                refereeVo.setCounselorId(counselorVo.getId());
                refereeVo.setIsDeleted(false);
                refereeVo = refereeService.save(refereeVo);
                if (refereeVo != null) {
                    UIUtil.execute("PF('refDialog').hide();");
                    refereeVos = refereeService.findListByReferenceId(applicationVo.getReferenceId());
                    if (StringUtils.isNotBlank(applicationId)) {
                        progressVo = progressService.findByApplicationId(applicationId);
                        progressVo.setReference(true);
                        progressService.save(progressVo);

                    }
                    AccountVo accountVo = AccountUtils.getAccountVo();
                    AuditLogUtil.saveAuditLog(accountVo.getId(), Constants.SAVED_REFERENCE);
                }
            }
        } catch (Exception e) {

        }
        UIUtil.displaySaveSuccessDialog(InternationalizationBean.locale);
        return true;
    }

    // 验证
    public boolean validateReferee() {
        boolean valid = true;

        if (!StringUtils.isNotEmpty(refereeVo.getRefereeRelationshipCd())
                || "select".equals(refereeVo.getRefereeRelationshipCd())) {
            UIUtil.setInvalid(":mainTab:refDialogForm:refereeRelationshipCd");
            valid = false;
        }
        if (!StringUtils.isNotEmpty(refereeVo.getName())) {
            UIUtil.setInvalid(":mainTab:refDialogForm:selectName");
            UIUtil.setInvalid(":mainTab:refDialogForm:othersName");
            valid = false;
        }
        if (!StringUtils.isNotEmpty(refereeVo.getEmail())) {
            UIUtil.setInvalid(":mainTab:refDialogForm:email");
            valid = false;
        }
        if (!valid) {
            UIUtil.displayErrorDialog(InternationalizationBean.locale, ConstantCommon.MADATORY_FIELD_MISSING,
                    ConstantCommon.MADATORY_FIELD_MISSING_CHI);
            return false;
        } else {
            return true;
        }
    }

    public void edit(String id) {
        if (StringUtils.isNotEmpty(id)) {
            refereeVo = refereeService.get(id);
        } else {
            refereeVo = new RefereeVo();
        }
    }

    public void delete(String id) {
        refereeService.delete(id);
        refereeVos = refereeService.findListByReferenceId(applicationVo.getReferenceId());
    }

    public void activeReference() {
        LOG.info("activeReference() start");
        isActiceRefree = false;
        String applicationId = (String) JSFUtil.getSessionMap().get("applicationId");
        ApplicationVo application = new ApplicationVo();
        if (StringUtils.isNotBlank(applicationId)) {
            application = applicationService.get(applicationId);
        }
        if (StringUtils.isNotBlank(application.getId())) {
            AcadBgVo acadBgVo = acadBgService.get(application.getAcadBgId());
            if (acadBgVo.getIsPartcpNextNjcee()) {
                List<QualificationVo> qualifications = qualificationService.getByApplicationId(application.getId());
                if (CollectionUtils.isNotEmpty(qualifications)) {
                    for (QualificationVo qualificationVo : qualifications) {
                        if (Constants.EXAM_NOT_IT_CODES.contains(qualificationVo.getType().getExamCd())) {
                            isActiceRefree = true;
                            break;
                        }
                    }
                }
            } else {
                isActiceRefree = true;
            }
        }
        JSFUtil.getSessionMap().put("isActiceRefree", isActiceRefree);
        LOG.info("activeReference() end");
    }

    public List<RefCdVo> getRelationships() {
        return relationships;
    }

    public void setRelationships(List<RefCdVo> relationships) {
        this.relationships = relationships;
    }

    public RefereeVo getRefereeVo() {
        return refereeVo;
    }

    public void setRefereeVo(RefereeVo refereeVo) {
        this.refereeVo = refereeVo;
    }

    public Boolean getIsActiveRefName() {
        return isActiveRefName;
    }

    public void setIsActiveRefName(Boolean isActiveRefName) {
        this.isActiveRefName = isActiveRefName;
    }

    public Boolean getIsActiveOthersRefName() {
        return isActiveOthersRefName;
    }

    public void setIsActiveOthersRefName(Boolean isActiveOthersRefName) {
        this.isActiveOthersRefName = isActiveOthersRefName;
    }

    public List<CounselorVo> getCounselors() {
        return counselors;
    }

    public void setCounselors(List<CounselorVo> counselors) {
        this.counselors = counselors;
    }

    public CounselorVo getCounselorVo() {
        return counselorVo;
    }

    public void setCounselorVo(CounselorVo counselorVo) {
        this.counselorVo = counselorVo;
    }

    public ReferenceVo getReferenceVo() {
        return referenceVo;
    }

    public void setReferenceVo(ReferenceVo referenceVo) {
        this.referenceVo = referenceVo;
    }

    public List<RefereeVo> getRefereeVos() {
        return refereeVos;
    }

    public void setRefereeVos(List<RefereeVo> refereeVos) {
        this.refereeVos = refereeVos;
    }

    public ApplicationVo getApplicationVo() {
        return applicationVo;
    }

    public void setApplicationVo(ApplicationVo applicationVo) {
        this.applicationVo = applicationVo;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public ProgressVo getProgressVo() {
        return progressVo;
    }

    public void setProgressVo(ProgressVo progressVo) {
        this.progressVo = progressVo;
    }

    public boolean getIsActiceRefree() {
        return isActiceRefree;
    }

    public void setIsActiceRefree(boolean isActiceRefree) {
        this.isActiceRefree = isActiceRefree;
    }
}
