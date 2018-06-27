package com.accentrix.hku.web.myApplication;

import java.io.File;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.component.tabview.Tab;
import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.accentrix.hku.constant.ConstantCommon;
import com.accentrix.hku.constant.Constants;
import com.accentrix.hku.service.adm.ExeService;
import com.accentrix.hku.service.adm.FormService;
import com.accentrix.hku.service.app.AcadBgService;
import com.accentrix.hku.service.app.InstitutionService;
import com.accentrix.hku.service.app.PrevStudiesService;
import com.accentrix.hku.service.app.ProgrammeChoiceService;
import com.accentrix.hku.service.app.ProgressService;
import com.accentrix.hku.service.app.ReqDocConfService;
import com.accentrix.hku.service.app.ReqDocService;
import com.accentrix.hku.service.app.UploadedDocService;
import com.accentrix.hku.service.applicant.ApplicationService;
import com.accentrix.hku.service.cpc.CityService;
import com.accentrix.hku.service.cpc.CountryService;
import com.accentrix.hku.service.cpc.ProvinceService;
import com.accentrix.hku.service.general.RefCdService;
import com.accentrix.hku.util.JSFUtil;
import com.accentrix.hku.util.UIUtil;
import com.accentrix.hku.util.app.AuditLogUtil;
import com.accentrix.hku.util.sys.AccountUtils;
import com.accentrix.hku.vo.adm.ExeVo;
import com.accentrix.hku.vo.adm.FormVo;
import com.accentrix.hku.vo.app.AcadBgVo;
import com.accentrix.hku.vo.app.InstitutionVo;
import com.accentrix.hku.vo.app.PrevStudiesVo;
import com.accentrix.hku.vo.app.ProgrammeChoiceVo;
import com.accentrix.hku.vo.app.ProgressVo;
import com.accentrix.hku.vo.app.ReqDocConfVo;
import com.accentrix.hku.vo.app.ReqDocVo;
import com.accentrix.hku.vo.app.UploadedDocVo;
import com.accentrix.hku.vo.applicant.AccountVo;
import com.accentrix.hku.vo.applicant.ApplicationVo;
import com.accentrix.hku.vo.cpc.CityVo;
import com.accentrix.hku.vo.cpc.CountryVo;
import com.accentrix.hku.vo.cpc.ProvinceVo;
import com.accentrix.hku.vo.general.RefCdVo;
import com.accentrix.hku.web.common.CommonBean;
import com.accentrix.hku.web.common.InternationalizationBean;

@ManagedBean
@ViewScoped
@Configurable(preConstruction = true)
public class AcadBgBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(AcadBgBean.class);

    CommonBean commonBean = JSFUtil.getCurrentInstance("commonBean", CommonBean.class);

    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private AcadBgService acadBgService;
    @Autowired
    private PrevStudiesService prevStudiesService;
    @Autowired
    private RefCdService refCdService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private InstitutionService institutionService;
    @Autowired
    private CityService cityService;
    @Autowired
    private ProgressService progressService;
    @Autowired
    private ProgrammeChoiceService programmeChoiceService;
    @Autowired
    private ReqDocConfService reqDocConfService;
    @Autowired
    private FormService formService;
    @Autowired
    private ExeService exeService;
    @Autowired
    private ReqDocService reqDocService;
    @Autowired
    private UploadedDocService uploadedDocService;

    private CountryVo country;
    private AcadBgVo acadBgVo;
    private PrevStudiesVo prevStudiesVo;
    private List<RefCdVo> hkuProgrammes;
    private List<RefCdVo> studyModeCds;
    private List<RefCdVo> levelAttaineds;
    private List<PrevStudiesVo> prevStudies;
    private ProgressVo progressVo;
    private List<ProvinceVo> studyProvinceLst;
    private List<CityVo> studyCityLst;
    private List<InstitutionVo> institutions;
    private String applicationId;
    private Boolean isActiveOtherInstitution;
    private Integer currTabIndex;
    private PrevStudiesVo studiesVo;
    private Date date;
    private Integer admissionYear;
    private List<RefCdVo> highestQualifications;

    public AcadBgBean() {
        init();
    }

    public void init() {
        LOG.debug("acadBg");
        currTabIndex = 0;
        prevStudiesVo = new PrevStudiesVo();
        studiesVo = new PrevStudiesVo();
        highestQualifications = new ArrayList<RefCdVo>();
        applicationId = (String) JSFUtil.getSessionMap().get("applicationId");
        admissionYear = (Integer) JSFUtil.getSessionMap().get("admissionYear");
        retrieveData(applicationId);
        acadBgVo.setAdminssionFormat(Constants.ADMISSION_WRONG);
        acadBgVo.setExpectedFormat(Constants.EXPECTED_WRONG);
    }

    /**
     * 
     * @param applicationId
     */
    private void retrieveData(String applicationId) {
        hkuProgrammes = refCdService.findListByType(Constants.PROGRAMME_TYPE);
        studyModeCds = refCdService.findListByType(Constants.STUDY_MODE);
        levelAttaineds = refCdService.findListByType(Constants.LEVEL_ATTAINED);

        ApplicationVo applicationVo = null;
        if (StringUtils.isNotBlank(applicationId)) {
            applicationVo = applicationService.get(applicationId);
        }
        String acadBgId = "";
        if (applicationVo != null) {
            acadBgId = applicationVo.getAcadBgId();
        }
        retrieveAcadBg(acadBgId);
        if (StringUtils.isNotEmpty(applicationId)) {
            prevStudies = prevStudiesService.findListByApplicationId(applicationId);
        } else {
            prevStudies = new ArrayList<PrevStudiesVo>();
        }
        changeProgType();
        getHighestQualificationCds();
    }

    @SuppressWarnings("deprecation")
    public void retrieveAcadBg(String acadBgId) {
        if (StringUtils.isNotBlank(acadBgId)) {
            acadBgVo = acadBgService.get(acadBgId);
            if (StringUtils.isNotBlank(acadBgVo.getCountryId())) {
                CountryVo countryVo = countryService.get(acadBgVo.getCountryId());
                commonBean.setCountry(countryVo);
                if (countryVo.getCode().equals("CHN")) {
                    studyProvinceLst = provinceService.findByCountryId(acadBgVo.getCountryId());
                    commonBean.setProvinceOfStudys(studyProvinceLst);
                    commonBean.setIsActiveProvince(true);

                    if (StringUtils.isNotBlank(acadBgVo.getProvinceId())) {
                        studyCityLst = cityService.findByCountryIdOrProvinceId(acadBgVo.getCountryId(),
                                acadBgVo.getProvinceId());
                        commonBean.setCityOfStudys(studyCityLst);
                        commonBean.setIsActiveCity(true);

                        if (StringUtils.isNotBlank(acadBgVo.getCityId())) {
                            institutions = institutionService.findInstitutions(acadBgVo.getCountryId(),
                                    acadBgVo.getProvinceId(), acadBgVo.getCityId());
                            commonBean.setInstitutions(institutions);
                        }
                    }
                } else {
                    studyCityLst = cityService.findByCountryIdOrProvinceId(acadBgVo.getCountryId(), null);
                    commonBean.setCityOfStudys(studyCityLst);
                    commonBean.setIsActiveCity(true);
                    if (StringUtils.isNotBlank(acadBgVo.getCityId())) {
                        institutions = institutionService.findInstitutions(acadBgVo.getCountryId(), null,
                                acadBgVo.getCityId());
                        commonBean.setInstitutions(institutions);
                    }
                }
            } else {
                commonBean.setCountry(null);
                commonBean.setProvinceOfStudys(null);
                commonBean.setIsActiveProvince(false);
                commonBean.setCityOfStudys(null);
                commonBean.setIsActiveCity(false);
                commonBean.setInstitutions(null);
            }
            if (StringUtils.isBlank(acadBgVo.getCountryId())
                    && StringUtils.isNotBlank(acadBgVo.getStudyCountryOthers())) {
                acadBgVo.setCountryId(Constants.OTHERS);
                commonBean.setIsActiveStudyCountry(true);
            }
            if (StringUtils.isBlank(acadBgVo.getInstitutionId())
                    && StringUtils.isNotBlank(acadBgVo.getInstitutionOthers())) {
                acadBgVo.setInstitutionId(Constants.OTHERS);
                isActiveOtherInstitution = true;
            }

            if (acadBgVo.getPrimaryEduYrs() != null) {
                acadBgVo.setPrimaryYrs(acadBgVo.getPrimaryEduYrs().toString());
            }

            if (acadBgVo.getSecondaryEduYrs() != null) {
                acadBgVo.setSecondaryYrs(acadBgVo.getSecondaryEduYrs().toString());
            }
            if (acadBgVo.getPostEduYrs() != null) {
                acadBgVo.setPostYrs(acadBgVo.getPostEduYrs().toString());
            }
            if (acadBgVo.getDateOfAdmissionToProg() != null) {
                if (acadBgVo.getDateOfAdmissionToProg().getYear() <= 0) {
                    acadBgVo.setDateOfAdmissionToProg(null);
                }
            }
            if (acadBgVo.getExpectedDateOfGrad() != null) {
                if (acadBgVo.getExpectedDateOfGrad().getYear() <= 0) {
                    acadBgVo.setExpectedDateOfGrad(null);
                }
            }
        } else {
            // FIXME: HANDLE IT LATER, SHOULD THROW EXCEPTION
            acadBgVo = new AcadBgVo();
        }
    }

    public void changeProvince() {
        if (StringUtils.isNotBlank(acadBgVo.getProvinceId())) {
            acadBgVo.setProvinceId("");
        }
        if (StringUtils.isNotBlank(prevStudiesVo.getProvinceId())) {
            prevStudiesVo.setProvinceId("");
        }
    }

    public void changeCity() {
        if (StringUtils.isNotBlank(acadBgVo.getCityId())) {
            acadBgVo.setCityId("");
        }
        if (StringUtils.isNotBlank(prevStudiesVo.getCityId())) {
            prevStudiesVo.setCityId("");
        }
    }

    public void saveAcademicProfile() {
        try {
            saveAcadBgVo();
            if (acadBgVo != null) {
                if (StringUtils.isNotBlank(applicationId)) {
                    progressVo = progressService.findByApplicationId(applicationId);
                    progressVo.setAcadBg(true);
                    progressService.save(progressVo);
                }
                initChoiceOfCurriculumFormPorgDisable(acadBgVo.getHighestQualificationCd());
            }
            AccountVo accountVo = AccountUtils.getAccountVo();
            AuditLogUtil.saveAuditLog(accountVo.getId(), Constants.SAVED_ACADEMIC_PROFILE);
            UIUtil.displaySaveSuccessDialog(InternationalizationBean.locale);
        } catch (Exception e) {
            UIUtil.displaySaveFailedDialog(InternationalizationBean.locale);
        }
    }

    public void saveAcadBgVo() {
        if (Constants.SELECT.equals(acadBgVo.getCountryId())) {
            acadBgVo.setCountryId(null);
        }
        if (StringUtils.isBlank(acadBgVo.getCityId()) || Constants.SELECT.equals(acadBgVo.getCityId())) {
            acadBgVo.setCityId(null);
        }
        if (StringUtils.isEmpty(acadBgVo.getProvinceId()) || Constants.SELECT.equals(acadBgVo.getProvinceId())) {
            acadBgVo.setProvinceId(null);
        }
        if (StringUtils.isNotBlank(acadBgVo.getPrimaryYrs())) {
            acadBgVo.setPrimaryEduYrs(Integer.parseInt(acadBgVo.getPrimaryYrs()));
        }
        if (StringUtils.isNotBlank(acadBgVo.getSecondaryYrs())) {
            acadBgVo.setSecondaryEduYrs(Integer.parseInt(acadBgVo.getSecondaryYrs()));
        }
        if (StringUtils.isNotBlank(acadBgVo.getPostYrs())) {
            acadBgVo.setPostEduYrs(Integer.parseInt(acadBgVo.getPostYrs()));
        }
        if (Constants.OTHERS.equals(acadBgVo.getInstitutionId())) {
            acadBgVo.setInstitutionId(null);
        } else {
            acadBgVo.setInstitutionOthers(null);
        }
        if (Constants.OTHERS.equals(acadBgVo.getCountryId()) || StringUtils.isBlank(acadBgVo.getCountryId())) {
            acadBgVo.setCountryId(null);
            acadBgVo.setProvinceId(null);
            acadBgVo.setCityId(null);
        } else {
            acadBgVo.setStudyCountryOthers(null);
        }
        if (Constants.OTHERS.equals(acadBgVo.getProgrammeTypeCd())) {
            acadBgVo.setLatestCumulativeGpa(null);
            acadBgVo.setMaxGpa(null);
            acadBgVo.setNotGpa(false);
            acadBgVo.setFinalRslt(null);
        } else {
            acadBgVo.setProgTypeOthers(null);
        }
        if (acadBgVo.getIsNotStuding()) {
            String time = "1900-01-01";
            dateFormat(time);
            acadBgVo.setCountryId(null);
            acadBgVo.setProvinceId(null);
            acadBgVo.setCityId(null);
            acadBgVo.setStudyCountryOthers(null);
            acadBgVo.setInstitutionId(null);
            acadBgVo.setInstitutionOthers(null);
            acadBgVo.setProgrammeTypeCd(null);
            acadBgVo.setProgTypeOthers(null);
            acadBgVo.setProgrammeTitle(null);
            acadBgVo.setStudyModeCd(null);
            acadBgVo.setLatestCumulativeGpa(null);
            acadBgVo.setMaxGpa(null);
            acadBgVo.setFinalRslt(null);
            acadBgVo.setDateOfAdmissionToProg(date);
            acadBgVo.setCurrentYrOfStudy(null);
            acadBgVo.setExpectedDateOfGrad(date);
            acadBgVo.setTypeOfEducation(null);
            isActiveOtherInstitution = false;
            commonBean.init();
        }
        if (StringUtils.isNotBlank(acadBgVo.getHighestQualificationCd())
                && !getHighestQualificationCds().contains(acadBgVo.getHighestQualificationCd())) {
            acadBgVo.setHighestQualificationCd("");
        }
        acadBgVo = acadBgService.save(acadBgVo);
        RefereeBean refereeBean = JSFUtil.getCurrentInstance("refereeBean", RefereeBean.class);
        refereeBean.activeReference();
    }

    // valid tab Academic History
    @SuppressWarnings("deprecation")
    public Boolean validAcademicHistory() {
        boolean valid = true;
        boolean wrongDateFormat = false;
        if (!acadBgVo.getIsNotStuding()) {
            if (Constants.OTHERS.equals(acadBgVo.getCountryId())
                    && StringUtils.isBlank(acadBgVo.getStudyCountryOthers())) {
                UIUtil.setInvalid(":mainTab:academicForm:abTabView:othersCountryId");
                valid = false;
            }
            if (StringUtils.isBlank(acadBgVo.getInstitutionId())) {
                UIUtil.setInvalid(":mainTab:academicForm:abTabView:institutionId");
                valid = false;
            }
            if (Constants.OTHERS.equals(acadBgVo.getInstitutionId())
                    && StringUtils.isBlank(acadBgVo.getInstitutionOthers())) {
                UIUtil.setInvalid(":mainTab:academicForm:abTabView:otherInstitutionId");
                valid = false;
            }
            if (StringUtils.isBlank(acadBgVo.getProgrammeTypeCd())) {
                UIUtil.setInvalid(":mainTab:academicForm:abTabView:programmeTypeCd");
                UIUtil.setInvalid(":mainTab:academicForm:abTabView:otherProgrammeCd");
                valid = false;
            } else {
                if (Constants.OTHERS.equals(acadBgVo.getProgrammeTypeCd())
                        && StringUtils.isBlank(acadBgVo.getProgTypeOthers())) {
                    UIUtil.setInvalid(":mainTab:academicForm:abTabView:otherProgrammeCd");
                    valid = false;
                } else {
                    RefCdVo refCdVo = refCdService.getByTypeAndCd(Constants.PROGRAMME_TYPE,
                            acadBgVo.getProgrammeTypeCd());
                    if (Constants.ACAD_TYPE_PROGS.contains(refCdVo.getValue())) {
                        if (!acadBgVo.getNotGpa()) {
                            if (StringUtils.isBlank(acadBgVo.getLatestCumulativeGpa())) {
                                UIUtil.setInvalid(":mainTab:academicForm:abTabView:latestCumulativeGpa");
                                valid = false;
                            }
                            if (StringUtils.isBlank(acadBgVo.getMaxGpa())) {
                                UIUtil.setInvalid(":mainTab:academicForm:abTabView:maxGpa");
                                valid = false;
                            }
                        } else {
                            if (StringUtils.isBlank(acadBgVo.getFinalRslt())) {
                                UIUtil.setInvalid(":mainTab:academicForm:abTabView:mylatest");
                                valid = false;
                            }
                        }
                    }
                }
            }

            if (StringUtils.isBlank(acadBgVo.getProgrammeTitle())) {
                UIUtil.setInvalid(":mainTab:academicForm:abTabView:programmeTitle");
                valid = false;
            }
            if (acadBgVo.getDateOfAdmissionToProg() == null) {
                UIUtil.setInvalid(":mainTab:academicForm:abTabView:dateOfAdmissionToProg");
                valid = false;
            } else {
                if (acadBgVo.getDateOfAdmissionToProg().getYear() <= 0) {
                    wrongDateFormat = true;
                    UIUtil.setInvalid(":mainTab:academicForm:abTabView:dateOfAdmissionToProg");
                }
            }
            if (StringUtils.isBlank(acadBgVo.getCurrentYrOfStudy())) {
                UIUtil.setInvalid(":mainTab:academicForm:abTabView:currentYrOfStudy");
                valid = false;
            }
            if (acadBgVo.getExpectedDateOfGrad() == null) {
                UIUtil.setInvalid(":mainTab:academicForm:abTabView:expectedDateOfGrad");
                valid = false;
            } else {
                if (acadBgVo.getExpectedDateOfGrad().getYear() <= 0) {
                    wrongDateFormat = true;
                    UIUtil.setInvalid(":mainTab:academicForm:abTabView:expectedDateOfGrad");
                }
            }
            if (StringUtils.isBlank(acadBgVo.getTypeOfEducation())) {
                UIUtil.setInvalid(":mainTab:academicForm:abTabView:typeOfEducation");
                valid = false;
            }
        } else {
            if (prevStudies.size() <= 0) {
                UIUtil.displayErrorDialog(InternationalizationBean.locale, Constants.PREVIOUS_WRONG,
                        Constants.PREVIOUS_WRONG_CHI);
                return false;
            }
        }
        if (acadBgVo.getIsPartcpNextNjcee() == null) {
            UIUtil.setInvalid(":mainTab:academicForm:abTabView:isPartcpNextNjcee");
            valid = false;
        }
        if (!valid && wrongDateFormat) {
            UIUtil.displayErrorDialog(InternationalizationBean.locale, ConstantCommon.MADATORY_FIELD_MISSING,
                    ConstantCommon.MADATORY_FIELD_MISSING_CHI);
            UIUtil.displayErrorDialog(InternationalizationBean.locale, ConstantCommon.WRONG_DATE_FORMAT,
                    ConstantCommon.WRONG_DATE_FORMAT_CHI);
            currTabIndex = 0;
            return false;
        } else if (!valid) {
            UIUtil.displayErrorDialog(InternationalizationBean.locale, ConstantCommon.MADATORY_FIELD_MISSING,
                    ConstantCommon.MADATORY_FIELD_MISSING_CHI);
            currTabIndex = 0;
            return false;
        } else if (wrongDateFormat) {
            UIUtil.displayErrorDialog(InternationalizationBean.locale, ConstantCommon.WRONG_DATE_FORMAT,
                    ConstantCommon.WRONG_DATE_FORMAT_CHI);
            currTabIndex = 0;
            return false;
        } else {
            return true;
        }
    }

    // valid tab Year of Schooling
    public Boolean validYearOfSchooling() {
        boolean valid = true;

        if (StringUtils.isBlank(acadBgVo.getHighestQualificationCd())) {
            UIUtil.setInvalid(":mainTab:academicForm:abTabView:highestQualificationCd");
            valid = false;
        }
        if (StringUtils.isBlank(acadBgVo.getPrimaryYrs())) {
            UIUtil.setInvalid(":mainTab:academicForm:abTabView:primaryYrs");
            valid = false;
        }
        if (StringUtils.isBlank(acadBgVo.getSecondaryYrs())) {
            UIUtil.setInvalid(":mainTab:academicForm:abTabView:secondaryYrs");
            valid = false;
        }
        if (StringUtils.isBlank(acadBgVo.getPostYrs())) {
            UIUtil.setInvalid(":mainTab:academicForm:abTabView:postYrs");
            valid = false;
        }

        if (!valid) {
            UIUtil.displayErrorDialog(InternationalizationBean.locale, ConstantCommon.MADATORY_FIELD_MISSING,
                    ConstantCommon.MADATORY_FIELD_MISSING_CHI);
            currTabIndex = 1;
            return false;
        } else {
            return true;
        }
    }

    public void savePrePage(String index) {
        if (validAcademicHistory() && validYearOfSchooling()) {
            saveAcademicProfile();
            processDocumentRequirements();
            ApplicationBean applicationBean = JSFUtil.getCurrentInstance("applicationBean", ApplicationBean.class);
            applicationBean.prePage(index);
        }
    }

    public void saveNextPage(String index) {
        if (validYearOfSchooling() && validAcademicHistory()) {
            saveAcademicProfile();
            processDocumentRequirements();
            ApplicationBean applicationBean = JSFUtil.getCurrentInstance("applicationBean", ApplicationBean.class);
            applicationBean.nextPage(index);
        }
    }

    public void saveAcademicHistory() {
        if (validAcademicHistory()) {
            calculationYear();
            saveAcadBgVo();
            retrieveData(applicationId);
            currTabIndex = 1;
            UIUtil.displaySaveSuccessDialog(InternationalizationBean.locale);
        }
    }

    public void saveYearOfSchooling() {
        if (validYearOfSchooling()) {
            if (StringUtils.isBlank(acadBgVo.getInstitutionId())) {
                acadBgVo.setInstitutionId(null);
            }
            if (Constants.SELECT.equals(acadBgVo.getCountryId())) {
                acadBgVo.setCountryId(null);
                acadBgVo.setProvinceId(null);
                acadBgVo.setCityId(null);
            }
            saveAcadBgVo();
            retrieveData(applicationId);
            currTabIndex = 0;
            UIUtil.displaySaveSuccessDialog(InternationalizationBean.locale);
        }
    }

    public void onTabChange(TabChangeEvent event) {
        Tab activeTab = event.getTab();
        currTabIndex = ((TabView) event.getSource()).getChildren().indexOf(activeTab);
    }

    private void processDocumentRequirements() {
        ApplicationVo applicationVo = applicationService.get(applicationId);
        if (applicationVo.getScholarVtp() != null && applicationVo.getScholarVtp()) {
            if (!Constants.NOT_POST_SECONDARY_STUDIES_QUALIFICATION.contains(acadBgVo.getHighestQualificationCd()))
                addDocumentRequirement(Constants.DOC_TYPE_OTHERS, "PVUEER",
                        "Past Vietnamese University Entrance Exam Results", applicationId);
            else if (Constants.NOT_POST_SECONDARY_STUDIES_QUALIFICATION.contains(acadBgVo.getHighestQualificationCd()))
                deleteDocumentRequirement(Constants.DOC_TYPE_OTHERS, "PVUEER",
                        "Past Vietnamese University Entrance Exam Results", applicationId);
        }
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

    private void deleteDocumentRequirement(String reqDocType, String reqDocCd, String reqDocName,
            String applicationId) {
        ReqDocConfVo reqDocConfVo = reqDocConfService.getByTypeAndCdAndName(reqDocType, reqDocCd, reqDocName);
        if (reqDocConfVo != null) {
            ReqDocVo reqDocVo = reqDocService.getByApplicationIdAndReqDocConfIdAndQualificationId(applicationId,
                    reqDocConfVo.getId(), null);
            if (reqDocVo != null) {
                List<UploadedDocVo> uploadedDocVos = uploadedDocService.findByReqDocId(reqDocVo.getId());
                if (CollectionUtils.isNotEmpty(uploadedDocVos)) {
                    for (UploadedDocVo uploadedDocVo : uploadedDocVos) {
                        File file = new File(uploadedDocVo.getFilePath());
                        file.delete();
                        uploadedDocService.delete(uploadedDocVo.getId());
                    }
                }
                reqDocService.delete(reqDocVo.getId());
            }
        }
    }

    public boolean savePrevStudies() {
        if (!validatePrevStudies()) {
            return false;
        }
        prevStudiesVo.setApplicationId(applicationId);
        prevStudiesVo.setIsDeleted(false);
        if (StringUtils.isEmpty(prevStudiesVo.getProvinceId())
                || Constants.SELECT.equals(prevStudiesVo.getProvinceId())) {
            prevStudiesVo.setProvinceId(null);
        }
        if (StringUtils.isEmpty(prevStudiesVo.getCityId()) || Constants.SELECT.equals(prevStudiesVo.getCityId())) {
            prevStudiesVo.setCityId(null);
        }
        if (Constants.OTHERS.equals(prevStudiesVo.getInstitutionId())) {
            prevStudiesVo.setInstitutionId(null);
        } else {
            prevStudiesVo.setInstitutionOthers(null);
        }
        if (Constants.OTHERS.equals(prevStudiesVo.getCountryId())) {
            prevStudiesVo.setCountryId(null);
            prevStudiesVo.setProvinceId(null);
            prevStudiesVo.setCityId(null);
        } else {
            prevStudiesVo.setStudyCountryOthers(null);
        }
        if (Constants.OTHERS.equals(prevStudiesVo.getProgrammeTypeCd())) {
            prevStudiesVo.setFinalCumulativeGpa(null);
            prevStudiesVo.setMaxGpa(null);
            prevStudiesVo.setNotGpa(false);
            prevStudiesVo.setFinalRslt(null);
        } else {
            prevStudiesVo.setProgTypeOthers(null);
        }
        try {
            prevStudiesVo = prevStudiesService.save(prevStudiesVo);
            if (prevStudiesVo != null) {
                UIUtil.execute("PF('preDialog').hide()");
                prevStudies = prevStudiesService.findListByApplicationId(applicationId);
                saveAcadBgVo();
                retrieveAcadBg(acadBgVo.getId());
            }
        } catch (Exception e) {

        }
        UIUtil.displaySaveSuccessDialog(InternationalizationBean.locale);
        return true;
    }

    // 验证PrevStudies
    public boolean validatePrevStudies() {
        boolean valid = true;

        if (Constants.SELECT.equals(prevStudiesVo.getCountryId())) {
            UIUtil.setInvalid(":mainTab:preDialogForm:preCountryId");
            valid = false;
        }
        if (Constants.OTHERS.equals(prevStudiesVo.getCountryId())
                && StringUtils.isBlank(prevStudiesVo.getStudyCountryOthers())) {
            UIUtil.setInvalid(":mainTab:preDialogForm:preOthersCountryId");
            valid = false;
        }
        if (StringUtils.isBlank(prevStudiesVo.getInstitutionId())) {
            UIUtil.setInvalid(":mainTab:preDialogForm:preInstitutionId");
            valid = false;
        }
        if (Constants.OTHERS.equals(prevStudiesVo.getInstitutionId())
                && StringUtils.isBlank(prevStudiesVo.getInstitutionOthers())) {
            UIUtil.setInvalid(":mainTab:preDialogForm:preOtherInstitutionId");
            valid = false;
        }
        if (StringUtils.isBlank(prevStudiesVo.getProgrammeTypeCd())) {
            UIUtil.setInvalid(":mainTab:preDialogForm:preProgrammeTypeCd");
            valid = false;
        } else {
            if (Constants.OTHERS.equals(prevStudiesVo.getProgrammeTypeCd())
                    && StringUtils.isBlank(prevStudiesVo.getProgTypeOthers())) {
                UIUtil.setInvalid(":mainTab:preDialogForm:preOtherProgrammeCd");
                valid = false;
            } else {
                RefCdVo refCdVo = refCdService.getByTypeAndCd(Constants.PROGRAMME_TYPE,
                        prevStudiesVo.getProgrammeTypeCd());
                if (Constants.ACAD_TYPE_PROGS.contains(refCdVo.getValue())) {
                    if (!prevStudiesVo.getNotGpa()) {
                        if (StringUtils.isBlank(prevStudiesVo.getFinalCumulativeGpa())) {
                            UIUtil.setInvalid(":mainTab:preDialogForm:preLatestCumulativeGpa");
                            valid = false;
                        }
                        if (StringUtils.isBlank(prevStudiesVo.getMaxGpa())) {
                            UIUtil.setInvalid(":mainTab:preDialogForm:preMaxGpa");
                            valid = false;
                        }
                    } else {
                        if (StringUtils.isBlank(prevStudiesVo.getFinalRslt())) {
                            UIUtil.setInvalid(":mainTab:preDialogForm:preMylatest");
                            valid = false;
                        }
                    }
                }
            }
        }

        if (StringUtils.isBlank(prevStudiesVo.getProgrammeTitle())) {
            UIUtil.setInvalid(":mainTab:preDialogForm:preProgrammeTitle");
            valid = false;
        }
        if (prevStudiesVo.getStudyFrom() == null) {
            UIUtil.setInvalid(":mainTab:preDialogForm:studyDateFrom");
            valid = false;
        }
        if (prevStudiesVo.getStudyTo() == null) {
            UIUtil.setInvalid(":mainTab:preDialogForm:studyDateTo");
            valid = false;
        }
        if (StringUtils.isBlank(prevStudiesVo.getTypeOfEducation())) {
            UIUtil.setInvalid(":mainTab:preDialogForm:preTypeOfEducation");
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

    public void editPrevStudies(String previousStudyId) {
        initCommonBean();
        if (StringUtils.isBlank(previousStudyId)) {
            prevStudiesVo = new PrevStudiesVo();
        } else {
            retrievePrevStudies(previousStudyId);
        }
    }

    public void retrievePrevStudies(String id) {
        prevStudiesVo = prevStudiesService.get(id);
        if (StringUtils.isNotBlank(prevStudiesVo.getCountryId())) {
            CountryVo countryVo = countryService.get(prevStudiesVo.getCountryId());
            commonBean.setCountry(countryVo);
            if (countryVo.getCode().equals("CHN")) {
                studyProvinceLst = provinceService.findByCountryId(prevStudiesVo.getCountryId());
                commonBean.setProvinceOfStudys(studyProvinceLst);
                commonBean.setIsActiveProvince(true);

                if (StringUtils.isNotBlank(prevStudiesVo.getProvinceId())) {
                    studyCityLst = cityService.findByCountryIdOrProvinceId(prevStudiesVo.getCountryId(),
                            prevStudiesVo.getProvinceId());
                    commonBean.setCityOfStudys(studyCityLst);
                    commonBean.setIsActiveCity(true);

                    if (StringUtils.isNotBlank(prevStudiesVo.getCityId())) {
                        institutions = institutionService.findInstitutions(prevStudiesVo.getCountryId(),
                                prevStudiesVo.getProvinceId(), prevStudiesVo.getCityId());
                        commonBean.setInstitutions(institutions);
                    }
                }
            } else {
                studyCityLst = cityService.findByCountryIdOrProvinceId(prevStudiesVo.getCountryId(), null);
                commonBean.setCityOfStudys(studyCityLst);
                commonBean.setIsActiveCity(true);
                if (StringUtils.isNotBlank(prevStudiesVo.getCityId())) {
                    institutions = institutionService.findInstitutions(prevStudiesVo.getCountryId(), null,
                            prevStudiesVo.getCityId());
                    commonBean.setInstitutions(institutions);
                }
            }
        }
        if (StringUtils.isBlank(prevStudiesVo.getCountryId())
                && StringUtils.isNotBlank(prevStudiesVo.getStudyCountryOthers())) {
            prevStudiesVo.setCountryId(Constants.OTHERS);
            commonBean.setIsActiveStudyCountry(true);
        }
        if (StringUtils.isBlank(prevStudiesVo.getInstitutionId())
                && StringUtils.isNotBlank(prevStudiesVo.getInstitutionOthers())) {
            prevStudiesVo.setInstitutionId(Constants.OTHERS);
            isActiveOtherInstitution = true;
        }
        changeProgType();
    }

    public void deletePrevStudies(String previousStudyId) {
        prevStudiesService.delete(previousStudyId);
        prevStudies = prevStudiesService.findListByApplicationId(applicationId);
        saveAcadBgVo();
        retrieveAcadBg(acadBgVo.getId());
    }

    public void loadOthersInstitution(AjaxBehaviorEvent event) {
        SelectOneMenu menu = (SelectOneMenu) event.getSource();
        String institutionMenuVal = menu != null && menu.getValue() != null ? menu.getValue().toString() : "";
        if (institutionMenuVal != null && institutionMenuVal.equals(Constants.OTHERS)) {
            isActiveOtherInstitution = true;
        } else {
            isActiveOtherInstitution = false;
        }
    }

    public void initCommonBean() {
        studiesVo.setCountryVo(commonBean.getCountry());
        studiesVo.setIsActiveStudyCountry(commonBean.getIsActiveStudyCountry());
        studiesVo.setIsActiveProvince(commonBean.getIsActiveProvince());
        studiesVo.setIsActiveCity(commonBean.getIsActiveCity());
        studiesVo.setProvinceOfStudys(commonBean.getProvinceOfStudys());
        studiesVo.setCityOfStudys(commonBean.getCityOfStudys());
        studiesVo.setInstitutions(commonBean.getInstitutions());
        studiesVo.setIsActiveOtherInstitution(isActiveOtherInstitution);
        studiesVo.setProvinceId(acadBgVo.getProvinceId());
        studiesVo.setCityId(acadBgVo.getCityId());
        studiesVo.setStudyCountryOthers(acadBgVo.getStudyCountryOthers());
        studiesVo.setInstitutionId(acadBgVo.getInstitutionId());
        studiesVo.setInstitutionOthers(acadBgVo.getInstitutionOthers());
        isActiveOtherInstitution = false;
        commonBean.init();
    }

    public void recoverCommonBean() {
        commonBean.setCountry(studiesVo.getCountryVo());
        commonBean.setIsActiveStudyCountry(studiesVo.getIsActiveStudyCountry());
        commonBean.setIsActiveProvince(studiesVo.getIsActiveProvince());
        commonBean.setIsActiveCity(studiesVo.getIsActiveCity());
        commonBean.setProvinceOfStudys(studiesVo.getProvinceOfStudys());
        commonBean.setCityOfStudys(studiesVo.getCityOfStudys());
        commonBean.setInstitutions(studiesVo.getInstitutions());
        acadBgVo.setProvinceId(studiesVo.getProvinceId());
        acadBgVo.setCityId(studiesVo.getCityId());
        acadBgVo.setStudyCountryOthers(studiesVo.getStudyCountryOthers());
        acadBgVo.setInstitutionId(studiesVo.getInstitutionId());
        acadBgVo.setInstitutionOthers(studiesVo.getInstitutionOthers());
        isActiveOtherInstitution = studiesVo.getIsActiveOtherInstitution();
    }

    public void changeArea() {
        if (StringUtils.isBlank(acadBgVo.getInstitutionId())) {
            acadBgVo.setInstitutionOthers("");
        }
        if (StringUtils.isBlank(prevStudiesVo.getInstitutionId())) {
            prevStudiesVo.setInstitutionOthers("");
        }
    }

    public void changeProgType() {
        if (StringUtils.isNotBlank(acadBgVo.getProgrammeTypeCd())) {
            if (Constants.OTHERS.equals(acadBgVo.getProgrammeTypeCd())) {
                acadBgVo.setIsActiveProgTypeCd("2");
            } else {
                RefCdVo refCdVo = refCdService.getByTypeAndCd(Constants.PROGRAMME_TYPE, acadBgVo.getProgrammeTypeCd());
                if (Constants.ACAD_TYPE_PROGS.contains(refCdVo.getValue())) {
                    acadBgVo.setIsActiveProgTypeCd("1");
                } else {
                    acadBgVo.setIsActiveProgTypeCd("0");
                }
            }
        } else {
            acadBgVo.setProgTypeOthers("");
            acadBgVo.setProgrammeTypeCd("");
            acadBgVo.setIsActiveProgTypeCd("0");
        }
        if (StringUtils.isNotBlank(prevStudiesVo.getProgrammeTypeCd())) {
            if (Constants.OTHERS.equals(prevStudiesVo.getProgrammeTypeCd())) {
                prevStudiesVo.setIsActiveProgTypeCd("2");
            } else {
                RefCdVo refCdVo = refCdService.getByTypeAndCd(Constants.PROGRAMME_TYPE,
                        prevStudiesVo.getProgrammeTypeCd());
                if (Constants.ACAD_TYPE_PROGS.contains(refCdVo.getValue())) {
                    prevStudiesVo.setIsActiveProgTypeCd("1");
                } else {
                    prevStudiesVo.setIsActiveProgTypeCd("0");
                }
            }
        } else {
            prevStudiesVo.setProgTypeOthers("");
            prevStudiesVo.setProgrammeTypeCd("");
            prevStudiesVo.setIsActiveProgTypeCd("0");
        }
    }

    public void changeNotGPA() {
        if (acadBgVo.getNotGpa() != null) {
            if (acadBgVo.getNotGpa()) {
                acadBgVo.setLatestCumulativeGpa("");
                acadBgVo.setMaxGpa("");
            } else {
                acadBgVo.setFinalRslt("");
            }
        }
        if (prevStudiesVo.getNotGpa() != null) {
            if (prevStudiesVo.getNotGpa()) {
                prevStudiesVo.setFinalCumulativeGpa("");
                prevStudiesVo.setMaxGpa("");
            } else {
                prevStudiesVo.setFinalRslt("");
            }
        }
    }

    public void dateFormat(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 定义要输出日期字符串的
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("deprecation")
    public void calculationYear() {
        int primaryMonths = 0;
        int secondaryMonths = 0;
        int tertiaryMonths = 0;
        for (PrevStudiesVo prevStudie : prevStudies) {
            switch (prevStudie.getTypeOfEducation()) {
            case Constants.PE:
                if (prevStudie.getStudyFrom().getMonth() == prevStudie.getStudyTo().getMonth()) {
                    primaryMonths = primaryMonths + countMonths(prevStudie.getStudyFrom(), prevStudie.getStudyTo()) + 1;
                } else {
                    primaryMonths = primaryMonths + countMonths(prevStudie.getStudyFrom(), prevStudie.getStudyTo());
                }
                break;
            case Constants.SE:
                if (prevStudie.getStudyFrom().getMonth() == prevStudie.getStudyTo().getMonth()) {
                    secondaryMonths = secondaryMonths + countMonths(prevStudie.getStudyFrom(), prevStudie.getStudyTo())
                            + 1;
                } else {
                    secondaryMonths = secondaryMonths + countMonths(prevStudie.getStudyFrom(), prevStudie.getStudyTo());
                }
                break;
            case Constants.TPSE:
                if (prevStudie.getStudyFrom().getMonth() == prevStudie.getStudyTo().getMonth()) {
                    tertiaryMonths = tertiaryMonths + countMonths(prevStudie.getStudyFrom(), prevStudie.getStudyTo())
                            + 1;
                } else {
                    tertiaryMonths = tertiaryMonths + countMonths(prevStudie.getStudyFrom(), prevStudie.getStudyTo());
                }
                break;
            default:
                break;
            }
        }
        if (!acadBgVo.getIsNotStuding()) {
            int acadMonths = countMonths(acadBgVo.getDateOfAdmissionToProg(), acadBgVo.getExpectedDateOfGrad());
            switch (acadBgVo.getTypeOfEducation()) {
            case Constants.PE:
                primaryMonths = primaryMonths + acadMonths;
                break;
            case Constants.SE:
                secondaryMonths = secondaryMonths + acadMonths;
                break;
            case Constants.TPSE:
                tertiaryMonths = tertiaryMonths + acadMonths;
                break;
            default:
                break;
            }
        }
        String primaryYrs = String.valueOf(primaryMonths % 12 > 8 ? primaryMonths / 12 + 1 : primaryMonths / 12);
        acadBgVo.setPrimaryYrs(primaryYrs);
        String secondaryYrs = String
                .valueOf(secondaryMonths % 12 > 8 ? secondaryMonths / 12 + 1 : secondaryMonths / 12);
        acadBgVo.setSecondaryYrs(secondaryYrs);
        String postYrs = String.valueOf(tertiaryMonths % 12 > 8 ? tertiaryMonths / 12 + 1 : tertiaryMonths / 12);
        acadBgVo.setPostYrs(postYrs);
    }

    @SuppressWarnings("deprecation")
    public int countMonths(Date begin, Date end) {
        return (end.getYear() - begin.getYear()) * 12 + (end.getMonth() - begin.getMonth());
    }

    public void changePartcpNjcee() {
        if (acadBgVo.getIsPartcpNextNjcee()) {
            UIUtil.execute("PF('messageDialog').show()");
        }
    }

    public void confirmChangePartcpNjcee(String msg) {
        if (Constants.NO.equals(msg)) {
            acadBgVo.setIsPartcpNextNjcee(false);
        }
    }

    public void initChoiceOfCurriculumFormPorgDisable(String highestQualificationCd) {
        if (StringUtils.isNotBlank(highestQualificationCd)) {
            if (!Constants.HIGHEST_QUALIFICATION_CD.contains(acadBgVo.getHighestQualificationCd())) {
                if (StringUtils.isNotBlank(applicationId)) {
                    List<ProgrammeChoiceVo> choiceList = programmeChoiceService.getChoiceByApplicationId(applicationId);
                    for (ProgrammeChoiceVo choice : choiceList) {
                        String firstCode = choice.getHkuProgrammeCode().substring(0, 1);
                        if ("A".equals(firstCode)) {
                            choice.setIsWithdrawn(true);
                            choice.setIsWithdrawnApproved(true);
                            programmeChoiceService.save(choice);
                        }
                    }
                }
            }
        }
    }

    public List<String> getHighestQualificationCds() {
        highestQualifications = new ArrayList<RefCdVo>();
        List<String> programmeTypeCds = new ArrayList<String>();
        if (StringUtils.isNotBlank(acadBgVo.getProgrammeTypeCd())) {
            programmeTypeCds.add(acadBgVo.getProgrammeTypeCd());
        }
        for (PrevStudiesVo prevStudy : prevStudies) {
            if (!programmeTypeCds.contains(prevStudy.getProgrammeTypeCd())) {
                programmeTypeCds.add(prevStudy.getProgrammeTypeCd());
            }
        }
        for (String programmeTypeCd : programmeTypeCds) {
            RefCdVo refCdVo = new RefCdVo();
            if (!Constants.OTHERS.equals(programmeTypeCd)) {
                refCdVo = refCdService.getByTypeAndCd(Constants.PROGRAMME_TYPE, programmeTypeCd);
                highestQualifications.add(refCdVo);
            } else {
                refCdVo.setCd(programmeTypeCd);
                refCdVo.setValue(Constants.HIGHEST_QUALIFICATION_OTHERS);
                highestQualifications.add(refCdVo);
            }
        }
        return programmeTypeCds;
    }

    public List<RefCdVo> getHkuProgrammes() {
        return hkuProgrammes;
    }

    public void setHkuProgrammes(List<RefCdVo> hkuProgrammes) {
        this.hkuProgrammes = hkuProgrammes;
    }

    public AcadBgVo getAcadBgVo() {
        return acadBgVo;
    }

    public void setAcadBgVo(AcadBgVo acadBgVo) {
        this.acadBgVo = acadBgVo;
    }

    public PrevStudiesVo getPrevStudiesVo() {
        return prevStudiesVo;
    }

    public void setPrevStudiesVo(PrevStudiesVo prevStudiesVo) {
        this.prevStudiesVo = prevStudiesVo;
    }

    public List<RefCdVo> getStudyModeCds() {
        return studyModeCds;
    }

    public void setStudyModeCds(List<RefCdVo> studyModeCds) {
        this.studyModeCds = studyModeCds;
    }

    public List<RefCdVo> getLevelAttaineds() {
        return levelAttaineds;
    }

    public void setLevelAttaineds(List<RefCdVo> levelAttaineds) {
        this.levelAttaineds = levelAttaineds;
    }

    public List<PrevStudiesVo> getPrevStudies() {
        return prevStudies;
    }

    public void setPrevStudies(List<PrevStudiesVo> prevStudies) {
        this.prevStudies = prevStudies;
    }

    public List<ProvinceVo> getProvinceOfStudys() {
        return studyProvinceLst;
    }

    public void setProvinceOfStudys(List<ProvinceVo> studyProvinceLst) {
        this.studyProvinceLst = studyProvinceLst;
    }

    public List<CityVo> getCityOfStudys() {
        return studyCityLst;
    }

    public void setCityOfStudys(List<CityVo> studyCityLst) {
        this.studyCityLst = studyCityLst;
    }

    public List<InstitutionVo> getInstitutions() {
        return institutions;
    }

    public void setInstitutions(List<InstitutionVo> institutions) {
        this.institutions = institutions;
    }

    public Boolean getIsActiveOtherInstitution() {
        return isActiveOtherInstitution;
    }

    public void setIsActiveOtherInstitution(Boolean isActiveOtherInstitution) {
        this.isActiveOtherInstitution = isActiveOtherInstitution;
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

    public CountryVo getCountry() {
        return country;
    }

    public void setCountry(CountryVo country) {
        this.country = country;
    }

    public Integer getCurrTabIndex() {
        return currTabIndex;
    }

    public void setCurrTabIndex(Integer currTabIndex) {
        this.currTabIndex = currTabIndex;
    }

    public PrevStudiesVo getStudiesVo() {
        return studiesVo;
    }

    public void setStudiesVo(PrevStudiesVo studiesVo) {
        this.studiesVo = studiesVo;
    }

    public Integer getAdmissionYear() {
        return admissionYear;
    }

    public void setAdmissionYear(Integer admissionYear) {
        this.admissionYear = admissionYear;
    }

    public List<RefCdVo> getHighestQualifications() {
        return highestQualifications;
    }

    public void setHighestQualifications(List<RefCdVo> highestQualifications) {
        this.highestQualifications = highestQualifications;
    }

}
