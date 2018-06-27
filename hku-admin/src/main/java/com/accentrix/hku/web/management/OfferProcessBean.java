package com.accentrix.hku.web.management;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.model.LazyDataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.accentrix.hku.constant.ConstantCommon;
import com.accentrix.hku.constant.Constants;
import com.accentrix.hku.service.adm.FormProgService;
import com.accentrix.hku.service.app.HkuProgrammeService;
import com.accentrix.hku.service.app.ProgrammeChoiceService;
import com.accentrix.hku.service.applicant.ApplicationService;
import com.accentrix.hku.service.general.RefCdService;
import com.accentrix.hku.util.UIUtil;
import com.accentrix.hku.vo.adm.FormProgVo;
import com.accentrix.hku.vo.app.HkuProgrammeVo;
import com.accentrix.hku.vo.app.ProgrammeChoiceVo;
import com.accentrix.hku.vo.applicant.ApplicationVo;
import com.accentrix.hku.vo.general.RefCdVo;
import com.accentrix.hku.web.common.InternationalizationBean;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年6月6日 下午1:37:18
 */
@ManagedBean
@ViewScoped
@Configurable(preConstruction = true)
public class OfferProcessBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(OfferProcessBean.class);

    @Autowired
    private HkuProgrammeService hkuProgrammeService;
    @Autowired
    private RefCdService refCdService;
    @Autowired
    private FormProgService formProgService;
    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private ProgrammeChoiceService programmeChoiceService;

    private List<String> facultyCodes;
    private String facultyCode;
    private List<HkuProgrammeVo> hkuProgrammeVos;
    private ProgrammeChoiceVo programmeChoiceVo;
    private List<RefCdVo> offerStatus;
    private LazyDataModel<ProgrammeChoiceVo> lazyDataModel;
    private boolean status;
    private String hkuProgrammeCdAndDesc;
    private List<ProgrammeChoiceVo> selectProgrammeChoiceVos;
    private Integer totalQuota;
    private Integer localQuota;
    private Integer nonLocalQuota;
    private boolean disableAssignCond;
    private boolean disableApproveCond;
    private boolean disableConfirmOffer;
    private List<RefCdVo> conditionTypes;
    private String conditionType;
    private String offerType;
    private List<RefCdVo> opQualifications;
    private List<String> selectQualifications;
    private boolean offerTypeStatus;
    private boolean conditionaOfferStatus;
    private String predictedActual;

    public OfferProcessBean() {
        init();
    }

    public void init() {
        LOG.debug("offerProcessBean init...");
        status = false;
        totalQuota = 0;
        localQuota = 0;
        nonLocalQuota = 0;
        facultyCode = "";
        predictedActual = "A";
        disableAssignCond = false;
        disableApproveCond = false;
        disableConfirmOffer = false;
        facultyCodes = hkuProgrammeService.findFacultyCd();
        offerStatus = refCdService.findListByType(Constants.CHOICESTATUS);
        hkuProgrammeVos = new ArrayList<HkuProgrammeVo>();
        programmeChoiceVo = new ProgrammeChoiceVo();
        selectProgrammeChoiceVos = new ArrayList<ProgrammeChoiceVo>();
        selectQualifications = new ArrayList<String>();
        programmeChoiceVo.setOfferStatusCd(Constants.READY_FOR_OFFER_CODE);
        lazyDataModel = new LazyOfferProcessDataModel(programmeChoiceVo);
        conditionTypes = refCdService.findListByType(Constants.CONDITION_TYPE);
        opQualifications = refCdService.findListByType(Constants.OP_QUALIFICATION);
    }

    public void loadProgrammes(AjaxBehaviorEvent event) {
        SelectOneMenu menu = (SelectOneMenu) event.getSource();
        String facultyCode = menu != null && menu.getValue() != null ? menu.getValue().toString() : "";
        if (StringUtils.isNotBlank(facultyCode)) {
            hkuProgrammeVos = hkuProgrammeService.findByFacultyCd(facultyCode);
        } else {
            hkuProgrammeVos = new ArrayList<HkuProgrammeVo>();
            programmeChoiceVo.setHkuProgrammeId("");
        }
    }

    public void search() {
        if (validateOfferProcess()) {
            status = false;
            UIUtil.displayManFieldMissingDialog(InternationalizationBean.locale);
            return;
        }
        status = true;
        totalQuota = 0;
        localQuota = 0;
        nonLocalQuota = 0;
        HkuProgrammeVo hkuProgrammeVo = hkuProgrammeService.get(programmeChoiceVo.getHkuProgrammeId());
        hkuProgrammeCdAndDesc = hkuProgrammeVo.getHkuProgrammeCd() + " " + hkuProgrammeVo.getHkuProgrammeDesc();
        lazyDataModel = new LazyOfferProcessDataModel(programmeChoiceVo);
        List<FormProgVo> formProgVos = formProgService.findByHkuProgrammeId(programmeChoiceVo.getHkuProgrammeId());
        if (CollectionUtils.isNotEmpty(formProgVos)) {
            for (FormProgVo formProgVo : formProgVos) {
                totalQuota = totalQuota + formProgVo.getQuotaLocal() + formProgVo.getQuotaMainland()
                        + formProgVo.getQuotaOverseas();
                localQuota = localQuota + formProgVo.getQuotaLocal();
                nonLocalQuota = nonLocalQuota + formProgVo.getQuotaMainland() + formProgVo.getQuotaOverseas();
            }
        }
    }

    private boolean validateOfferProcess() {
        boolean value = false;
        if (StringUtils.isEmpty(facultyCode)) {
            UIUtil.setInvalid("offerProcessForm:faculty");
            value = true;
        }
        if (StringUtils.isEmpty(programmeChoiceVo.getHkuProgrammeId())) {
            UIUtil.setInvalid("offerProcessForm:programme");
            value = true;
        }
        return value;
    }

    public void reset() {
        LOG.debug("OfferProcessBean reset...");
        init();
    }

    public void rowSelectCheckbox() {
        disableAssignCond = false;
        disableApproveCond = false;
        disableConfirmOffer = false;
        if (CollectionUtils.isNotEmpty(selectProgrammeChoiceVos)) {
            for (ProgrammeChoiceVo vo : selectProgrammeChoiceVos) {
                if (!Constants.CHOICE_SUBMITTED.equals(vo.getOfferStatusCd())
                        && !Constants.CHOICE_SHORTLISTED.equals(vo.getOfferStatusCd())
                        && !Constants.READY_FOR_OFFER_CODE.equals(vo.getOfferStatusCd())) {
                    disableAssignCond = true;
                }
                if (!Constants.PROG_CHOICE_OFFERASS_CD.equals(vo.getOfferStatusCd())) {
                    disableConfirmOffer = true;
                }
                if (!Constants.OFFER_ASSIGNED_FOR_APPROVAL_CODE.equals(vo.getOfferStatusCd())) {
                    disableApproveCond = true;
                }
            }
        }
    }

    public void getApplicationNoForProgrammeChoices(String type) {
        if (CollectionUtils.isNotEmpty(selectProgrammeChoiceVos)) {
            for (ProgrammeChoiceVo vo : selectProgrammeChoiceVos) {
                ApplicationVo applicationVo = applicationService.get(vo.getApplicationId());
                vo.setApplicationNo(applicationVo.getApplicationNo());
            }
            UIUtil.show("assignOfferDialog");
        } else {
            UIUtil.displayErrorDialog(ConstantCommon.LOCALE_UK, Constants.NO_RECORD_SELECTED,
                    Constants.NO_RECORD_SELECTED_CHI);
        }
        offerType = type;
    }

    public void removeProgrammeChoice(String id) {
        if (CollectionUtils.isNotEmpty(selectProgrammeChoiceVos)) {
            for (ProgrammeChoiceVo vo : selectProgrammeChoiceVos) {
                if (id.equals(vo.getId())) {
                    selectProgrammeChoiceVos.remove(vo);
                    break;
                }
            }
        }
    }

    public void assignOffer() {
        if (CollectionUtils.isNotEmpty(selectProgrammeChoiceVos)) {
            for (ProgrammeChoiceVo vo : selectProgrammeChoiceVos) {
                vo.setOfferStatusCd(Constants.PROG_CHOICE_OFFERASS_CD);
                vo.setOfferType(conditionType);
            }
            programmeChoiceService.save(selectProgrammeChoiceVos);
        }
        resetData();
    }

    public void updateProgrammeChoiceStatus(String status) {
        if (CollectionUtils.isNotEmpty(selectProgrammeChoiceVos)) {
            for (ProgrammeChoiceVo vo : selectProgrammeChoiceVos) {
                vo.setOfferStatusCd(status);
            }
            programmeChoiceService.save(selectProgrammeChoiceVos);
        } else {
            UIUtil.displayErrorDialog(ConstantCommon.LOCALE_UK, Constants.NO_RECORD_SELECTED,
                    Constants.NO_RECORD_SELECTED_CHI);
        }
        resetData();
    }

    public void changeOfferType() {
        if (StringUtils.isNotBlank(conditionType) && conditionType.equals(Constants.CONDITIONAL_OFFER)) {
            offerTypeStatus = true;
        } else {
            offerTypeStatus = false;
            conditionaOfferStatus = false;
        }
    }

    public void changeConditionaOfferStatus() {
        conditionaOfferStatus = true;
    }

    public void resetData() {
        disableAssignCond = false;
        disableApproveCond = false;
        disableConfirmOffer = false;
        selectProgrammeChoiceVos = new ArrayList<ProgrammeChoiceVo>();
    }

    public List<String> getFacultyCodes() {
        return facultyCodes;
    }

    public void setFacultyCodes(List<String> facultyCodes) {
        this.facultyCodes = facultyCodes;
    }

    public String getFacultyCode() {
        return facultyCode;
    }

    public void setFacultyCode(String facultyCode) {
        this.facultyCode = facultyCode;
    }

    public List<HkuProgrammeVo> getHkuProgrammeVos() {
        return hkuProgrammeVos;
    }

    public void setHkuProgrammeVos(List<HkuProgrammeVo> hkuProgrammeVos) {
        this.hkuProgrammeVos = hkuProgrammeVos;
    }

    public ProgrammeChoiceVo getProgrammeChoiceVo() {
        return programmeChoiceVo;
    }

    public void setProgrammeChoiceVo(ProgrammeChoiceVo programmeChoiceVo) {
        this.programmeChoiceVo = programmeChoiceVo;
    }

    public List<RefCdVo> getOfferStatus() {
        return offerStatus;
    }

    public void setOfferStatus(List<RefCdVo> offerStatus) {
        this.offerStatus = offerStatus;
    }

    public LazyDataModel<ProgrammeChoiceVo> getLazyDataModel() {
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<ProgrammeChoiceVo> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getHkuProgrammeCdAndDesc() {
        return hkuProgrammeCdAndDesc;
    }

    public void setHkuProgrammeCdAndDesc(String hkuProgrammeCdAndDesc) {
        this.hkuProgrammeCdAndDesc = hkuProgrammeCdAndDesc;
    }

    public List<ProgrammeChoiceVo> getSelectProgrammeChoiceVos() {
        return selectProgrammeChoiceVos;
    }

    public void setSelectProgrammeChoiceVos(List<ProgrammeChoiceVo> selectProgrammeChoiceVos) {
        this.selectProgrammeChoiceVos = selectProgrammeChoiceVos;
    }

    public Integer getTotalQuota() {
        return totalQuota;
    }

    public void setTotalQuota(Integer totalQuota) {
        this.totalQuota = totalQuota;
    }

    public Integer getLocalQuota() {
        return localQuota;
    }

    public void setLocalQuota(Integer localQuota) {
        this.localQuota = localQuota;
    }

    public Integer getNonLocalQuota() {
        return nonLocalQuota;
    }

    public void setNonLocalQuota(Integer nonLocalQuota) {
        this.nonLocalQuota = nonLocalQuota;
    }

    public boolean getDisableAssignCond() {
        return disableAssignCond;
    }

    public void setDisableAssignCond(boolean disableAssignCond) {
        this.disableAssignCond = disableAssignCond;
    }

    public List<RefCdVo> getConditionTypes() {
        return conditionTypes;
    }

    public void setConditionTypes(List<RefCdVo> conditionTypes) {
        this.conditionTypes = conditionTypes;
    }

    public String getConditionType() {
        return conditionType;
    }

    public void setConditionType(String conditionType) {
        this.conditionType = conditionType;
    }

    public boolean getDisableApproveCond() {
        return disableApproveCond;
    }

    public void setDisableApproveCond(boolean disableApproveCond) {
        this.disableApproveCond = disableApproveCond;
    }

    public String getOfferType() {
        return offerType;
    }

    public void setOfferType(String offerType) {
        this.offerType = offerType;
    }

    public boolean getDisableConfirmOffer() {
        return disableConfirmOffer;
    }

    public void setDisableConfirmOffer(boolean disableConfirmOffer) {
        this.disableConfirmOffer = disableConfirmOffer;
    }

    public List<RefCdVo> getOpQualifications() {
        return opQualifications;
    }

    public void setOpQualifications(List<RefCdVo> opQualifications) {
        this.opQualifications = opQualifications;
    }

    public List<String> getSelectQualifications() {
        return selectQualifications;
    }

    public void setSelectQualifications(List<String> selectQualifications) {
        this.selectQualifications = selectQualifications;
    }

    public boolean getOfferTypeStatus() {
        return offerTypeStatus;
    }

    public void setOfferTypeStatus(boolean offerTypeStatus) {
        this.offerTypeStatus = offerTypeStatus;
    }

    public boolean getConditionaOfferStatus() {
        return conditionaOfferStatus;
    }

    public void setConditionaOfferStatus(boolean conditionaOfferStatus) {
        this.conditionaOfferStatus = conditionaOfferStatus;
    }

    public String getPredictedActual() {
        return predictedActual;
    }

    public void setPredictedActual(String predictedActual) {
        this.predictedActual = predictedActual;
    }
}
