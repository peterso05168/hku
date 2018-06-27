package com.accentrix.hku.web.myApplication;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

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
import com.accentrix.hku.service.adm.ExeService;
import com.accentrix.hku.service.adm.FormService;
import com.accentrix.hku.service.app.AppNjceeScoringSystemService;
import com.accentrix.hku.service.app.AppNjceeSubjectStructureService;
import com.accentrix.hku.service.app.MfeSchemeService;
import com.accentrix.hku.service.app.ProgressService;
import com.accentrix.hku.service.app.QualificationRsltService;
import com.accentrix.hku.service.app.QualificationService;
import com.accentrix.hku.service.app.ReqDocConfService;
import com.accentrix.hku.service.app.ReqDocService;
import com.accentrix.hku.service.app.UploadedDocService;
import com.accentrix.hku.service.applicant.ApplicationService;
import com.accentrix.hku.service.cpc.CountryService;
import com.accentrix.hku.service.cpc.ProvinceService;
import com.accentrix.hku.service.exam.GradeService;
import com.accentrix.hku.service.exam.SubjectService;
import com.accentrix.hku.service.exam.TypeService;
import com.accentrix.hku.service.general.RefCdService;
import com.accentrix.hku.util.JSFUtil;
import com.accentrix.hku.util.UIUtil;
import com.accentrix.hku.util.app.AuditLogUtil;
import com.accentrix.hku.util.sys.AccountUtils;
import com.accentrix.hku.vo.adm.ExeVo;
import com.accentrix.hku.vo.adm.FormVo;
import com.accentrix.hku.vo.app.AppNjceeScoringSystemVo;
import com.accentrix.hku.vo.app.AppNjceeSubjectStructureVo;
import com.accentrix.hku.vo.app.MfeSchemeVo;
import com.accentrix.hku.vo.app.ProgressVo;
import com.accentrix.hku.vo.app.QualificationRsltVo;
import com.accentrix.hku.vo.app.QualificationVo;
import com.accentrix.hku.vo.app.ReqDocConfVo;
import com.accentrix.hku.vo.app.ReqDocVo;
import com.accentrix.hku.vo.app.UploadedDocVo;
import com.accentrix.hku.vo.applicant.AccountVo;
import com.accentrix.hku.vo.applicant.ApplicationVo;
import com.accentrix.hku.vo.cpc.CountryVo;
import com.accentrix.hku.vo.cpc.ProvinceVo;
import com.accentrix.hku.vo.exam.GradeVo;
import com.accentrix.hku.vo.exam.SubjectVo;
import com.accentrix.hku.vo.exam.TypeVo;
import com.accentrix.hku.vo.general.RefCdVo;
import com.accentrix.hku.web.common.InternationalizationBean;
import com.eaio.uuid.UUID;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年2月27日 下午3:47:02
 */
@ManagedBean
@ViewScoped
@Configurable(preConstruction = true)
public class OtherQualificationBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(OtherQualificationBean.class);

    @Autowired
    private QualificationService qualificationService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private QualificationRsltService qualificationRsltService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private RefCdService refCdService;
    @Autowired
    private ProgressService progressService;
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private MfeSchemeService mfeSchemeService;
    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private AppNjceeScoringSystemService appNjceeScoringSystemService;
    @Autowired
    private AppNjceeSubjectStructureService appNjceeSubjectStructureService;
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

    private String applicationId;
    private QualificationVo editQualification;
    private List<QualificationVo> qualifications;
    private QualificationRsltVo editQualificationRslt;
    private List<TypeVo> allExamTypeList;
    private List<TypeVo> examTypeList;
    private List<SubjectVo> originSubjectList;
    private List<GradeVo> grades;
    private List<RefCdVo> refCds;
    private List<ProvinceVo> provinces;
    private List<RefCdVo> streamRefCds;
    private MfeSchemeVo oneSemesterOne;
    private MfeSchemeVo oneSemesterTwo;
    private MfeSchemeVo twoSemesterOne;
    private MfeSchemeVo twoSemesterTwo;
    private MfeSchemeVo threeSemesterOne;
    private MfeSchemeVo threeSemesterTwo;
    private TypeVo examTypeVo;
    private QualificationVo tempQualification;

    private List<SubjectVo> displaySubject;
    private List<String> displayLevel;
    private List<String> selectLevel;
    private List<String> examBoards;
    private List<String> selectBoards;
    private List<SubjectVo> selectSubject;
    private boolean isSelectLevel;
    private boolean isSelectSubject;
    private boolean isSelectBoard;
    private int currPanelIndex;
    private String examType;
    private Date appEndDate;
    private List<String> examYears;
    private String promptMsg;
    private List<RefCdVo> eeTokGrades;
    private String editNjceeQualificationRsltId;
    private String njceeQualificationId;

    public OtherQualificationBean() {
        init();
    }

    public void init() {
        LOG.debug("init ...");
        allExamTypeList = typeService.findList();
        streamRefCds = refCdService.findListByType(Constants.STREAM);
        refCds = refCdService.findListByType(Constants.IBRESULT);
        eeTokGrades = refCdService.findListByType(Constants.EETOK);
        editQualification = new QualificationVo();
        editQualificationRslt = new QualificationRsltVo();
        originSubjectList = new ArrayList<SubjectVo>();
        grades = new ArrayList<GradeVo>();
        examTypeList = new ArrayList<TypeVo>();
        selectLevel = new ArrayList<String>();
        selectBoards = new ArrayList<String>();
        selectSubject = new ArrayList<SubjectVo>();
        examTypeVo = new TypeVo();
        tempQualification = new QualificationVo();
        isSelectLevel = false;
        isSelectSubject = false;
        isSelectBoard = false;
        examType = "0";
        currPanelIndex = 0;
        promptMsg = "";

        applicationId = (String) JSFUtil.getSessionMap().get("applicationId");
        appEndDate = (Date) JSFUtil.getSessionMap().get("appEndDate");
        retrieveData(applicationId);
    }

    private void retrieveData(String applicationId) {
        if (StringUtils.isNotBlank(applicationId)) {
            qualifications = qualificationService.getByApplicationId(applicationId);
        } else {
            qualifications = new ArrayList<QualificationVo>();
        }

        CountryVo countryVo = countryService.getByDesc(Constants.CHINA);
        if (countryVo != null) {
            provinces = provinceService.findByCountryId(countryVo.getId());
        } else {
            provinces = new ArrayList<ProvinceVo>();
        }
    }

    public void saveQualification() {
        if (validateQualification()) {
            editQualification.setApplicationId(applicationId);
            editQualification.setNjceeHkuMfExcellentSche(false);
            editQualification.setLastUpdatedDate(new Date());
            editQualification.setIsDeleted(false);
            editQualification.setIsDeleteApproved(false);
            editQualification = qualificationService.save(editQualification);
            editQualification = qualificationService.get(editQualification.getId());
            qualifications.add(editQualification);
            RefereeBean refereeBean = JSFUtil.getCurrentInstance("refereeBean", RefereeBean.class);
            refereeBean.activeReference();
            UIUtil.execute("PF('qualificationDialog').hide()");
            UIUtil.displaySaveSuccessDialog(InternationalizationBean.locale);
        }
    }

    public boolean validateQualification() {
        boolean valid = true;
        boolean existQualification = false;

        if ("0".equals(examType)) {
            UIUtil.setInvalid("mainTab:formQualificationDialog:examTypeMenu");
            valid = false;
        }
        if ("0".equals(editQualification.getExamTypeId())) {
            UIUtil.setInvalid("mainTab:formQualificationDialog:examTypeIdMenu");
            valid = false;
        }
        if (0 == editQualification.getExamTypeYear()) {
            UIUtil.setInvalid("mainTab:formQualificationDialog:examTypeYearMenu");
            valid = false;
        }
        if (!editQualification.getIsSelectNJCEE()) {
            if (0 == editQualification.getExamTypeMonth()) {
                UIUtil.setInvalid("mainTab:formQualificationDialog:examTypeMonthMenu");
                valid = false;
            }
        } else {
            editQualification.setExamTypeMonth(0);
        }
        // exit qualification
        for (QualificationVo qualification : qualifications) {
            if (qualification.getExamTypeId().equals(editQualification.getExamTypeId())
                    && qualification.getExamTypeYear().equals(editQualification.getExamTypeYear())
                    && qualification.getExamTypeMonth().equals(editQualification.getExamTypeMonth())) {
                existQualification = true;
                break;
            }
        }
        if (!valid && existQualification) {
            UIUtil.displayErrorDialog(InternationalizationBean.locale, ConstantCommon.MADATORY_FIELD_MISSING,
                    ConstantCommon.MADATORY_FIELD_MISSING_CHI);
            UIUtil.displayErrorDialog(InternationalizationBean.locale, Constants.WRONG_EXISTED_QUALIFICATION,
                    Constants.WRONG_EXISTED_QUALIFICATION_CHI);
            return false;
        } else if (!valid) {
            UIUtil.displayErrorDialog(InternationalizationBean.locale, ConstantCommon.MADATORY_FIELD_MISSING,
                    ConstantCommon.MADATORY_FIELD_MISSING_CHI);
            return false;
        } else if (existQualification) {
            UIUtil.displayErrorDialog(InternationalizationBean.locale, Constants.WRONG_EXISTED_QUALIFICATION,
                    Constants.WRONG_EXISTED_QUALIFICATION_CHI);
            return false;
        } else {
            return true;
        }
    }

    public String setMonth(String month) {
        if (StringUtils.isNotBlank(month) && !"0".equals(month)) {
            return Month.of(Integer.valueOf(month)).getDisplayName(TextStyle.FULL, InternationalizationBean.locale)
                    + ", ";
        }
        return "";
    }

    public void editQualification() {
        examType = "0";
        editQualification = new QualificationVo();
        examTypeList = new ArrayList<TypeVo>();
    }

    public void editQualificationRslt(String qualificationRsltId, String qualificationId, String examTypeId) {
        boolean valid = false;
        examTypeVo = typeService.get(examTypeId);
        if (Constants.EXAM_IB_CODES.contains(examTypeVo.getExamCd())) {
            if (StringUtils.isBlank(qualificationRsltId)) {
                List<QualificationRsltVo> rsltList = qualificationRsltService.getByAppQualificationId(qualificationId);
                if (rsltList.size() >= 6) {
                    valid = false;
                    UIUtil.execute("PF('messageIbDialog').show()");
                } else {
                    valid = true;
                }
            } else {
                valid = true;
            }
        } else if (Constants.EXAM_IT_CODES.contains(examTypeVo.getExamCd())) {
            if (StringUtils.isBlank(qualificationRsltId)) {
                List<QualificationRsltVo> rsltList = qualificationRsltService.getByAppQualificationId(qualificationId);
                if (rsltList.size() >= 5) {
                    valid = false;
                    UIUtil.execute("PF('messageItDialog').show()");
                } else {
                    valid = true;
                }
            } else {
                valid = true;
            }
        } else {
            valid = true;
        }
        if (valid) {
            // edit
            if (StringUtils.isNotBlank(qualificationRsltId)) {
                editQualificationRslt = qualificationRsltService.get(qualificationRsltId);
                editQualificationRslt.setInitExamSubjectId(editQualificationRslt.getExamSubjectId());
            } else {
                editQualificationRslt = new QualificationRsltVo();
                editQualificationRslt.setInitExamSubjectId("");
                editQualificationRslt.setAppQualificationId(qualificationId);
            }
            selectSubject = new ArrayList<SubjectVo>();
            grades = gradeService.getByExamTypeId(examTypeId);
            originSubjectList = subjectService.getByExamTypeId(examTypeId);
            List<QualificationRsltVo> rsltList = qualificationRsltService.getByAppQualificationId(qualificationId);

            if (Constants.EXAM_GCE_CODES.contains(examTypeVo.getExamCd())) {
                for (QualificationRsltVo qualificationRslt : rsltList) {
                    for (SubjectVo subject : originSubjectList) {
                        if ((qualificationRslt.getExamSubjectDesc().equals(subject.getExamSubjectDesc())
                                && qualificationRslt.getExamBoard().equals(subject.getExamBoard()))
                                && !subject.getId().equals(editQualificationRslt.getInitExamSubjectId())) {
                            subject.setSubjectDisable(true);
                        }
                    }
                }
                // load Grades
                if (StringUtils.isNotBlank(editQualificationRslt.getExamLevel())) {
                    if (Constants.EXAM_GCE_GRADE_LEVELS.contains(examTypeVo.getExamCd())) {
                        grades = gradeService.getByExamTypeIdAndExamLevel(examTypeVo.getId(),
                                editQualificationRslt.getExamLevel());
                    } else {
                        grades = gradeService.getByExamTypeId(examTypeVo.getId());
                    }
                } else {
                    grades = new ArrayList<GradeVo>();
                }
                // load subject
                if (StringUtils.isNotBlank(editQualificationRslt.getExamBoard())) {
                    for (SubjectVo subject : originSubjectList) {
                        if (!subject.getSubjectDisable()) {
                            if (editQualificationRslt.getExamBoard().equals(subject.getExamBoard())) {
                                selectSubject.add(subject);
                            }
                        }
                    }
                }
                loadSubjectAndLevel(originSubjectList);
                changeGrade();
            } else {
                for (QualificationRsltVo qualificationRslt : rsltList) {
                    for (SubjectVo subject : originSubjectList) {
                        if (qualificationRslt.getExamSubjectDesc().equals(subject.getExamSubjectDesc())
                                && !subject.getId().equals(editQualificationRslt.getInitExamSubjectId())) {
                            subject.setSubjectDisable(true);
                        }
                    }
                }
                loadSubjectAndLevel(originSubjectList);
                if (!Constants.EXAM_SAT_CODES.contains(examTypeVo.getExamCd())
                        && !Constants.EXAM_IT_CODES.contains(examTypeVo.getExamCd())) {
                    changeGrade();
                }
                if (Constants.EXAM_SAT_CODES.contains(examTypeVo.getExamCd())) {
                    if (StringUtils.isBlank(editQualificationRslt.getId())) {
                        isSelectSubject = true;
                    }
                }
                if (Constants.EXAM_IB_CODES.contains(examTypeVo.getExamCd())) {
                    selectLevel = new ArrayList<String>();
                    if (StringUtils.isNotBlank(editQualificationRslt.getExamSubjectId())) {
                        SubjectVo subjectVo = subjectService.get(editQualificationRslt.getExamSubjectId());
                        for (SubjectVo subject : originSubjectList) {
                            if (subject.getExamSubjectDesc().equals(subjectVo.getExamSubjectDesc())
                                    && !selectLevel.contains(subject.getExamLevel())) {
                                selectLevel.add(subject.getExamLevel());
                            }
                        }
                    }
                }
            }
            UIUtil.execute("PF('subjectDialog').show()");
        }
    }

    public void editNjceeQualificationRslt(String qualificationRsltId, String qualificationId, String examTypeId,
            String provinceId, String stream, Map<String, QualificationRsltVo> njceeRsltMap) {
        boolean valid = false;
        examTypeVo = typeService.get(examTypeId);
        if (StringUtils.isBlank(provinceId) || StringUtils.isBlank(stream)) {
            valid = false;
            UIUtil.execute("PF('messageNjceeDialog').show()");
        } else {
            valid = true;
            examTypeVo.setProvinceDesc(provinceService.get(provinceId).getDescription());
            for (QualificationVo qualification : qualifications) {
                if (qualification.getId().equals(qualificationId)) {
                    qualificationService.save(qualification);
                }
            }
        }
        if (valid) {
            // edit
            if (StringUtils.isNotBlank(qualificationRsltId)) {
                editQualificationRslt = njceeRsltMap.get(qualificationRsltId);
                editNjceeQualificationRsltId = qualificationRsltId;
                editQualificationRslt.setInitExamSubjectId(editQualificationRslt.getExamSubjectId());
            } else {
                editQualificationRslt = new QualificationRsltVo();
                editQualificationRslt.setInitExamSubjectId("");
                editQualificationRslt.setAppQualificationId(qualificationId);
            }

            njceeQualificationId = qualificationId;
            selectSubject = new ArrayList<SubjectVo>();
            grades = gradeService.getByExamTypeId(examTypeId);
            originSubjectList = subjectService.getByExamTypeId(examTypeId);
            loadSubjectAndLevel(originSubjectList);
            setSubjectDisableByStream(stream);
            for (Map.Entry<String, QualificationRsltVo> entry : njceeRsltMap.entrySet()) {
                QualificationRsltVo qualificationRslt = entry.getValue();
                for (SubjectVo subject : displaySubject) {
                    if (qualificationRslt.getExamSubjectId().equals(subject.getId())
                            && !subject.getId().equals(editQualificationRslt.getInitExamSubjectId())) {
                        subject.setSubjectDisable(true);
                        break;
                    }
                }
            }

            if (StringUtils.isNotBlank(editQualificationRslt.getAchievedGradeCd())) {
                editQualificationRslt.setIsActiveAchievedGrade(true);
            } else {
                editQualificationRslt.setIsActiveAchievedGrade(false);
            }
            UIUtil.execute("PF('subjectDialog').show()");
        }
    }

    public void setSubjectDisableByStream(String stream) {
        for (SubjectVo subject : displaySubject) {
            if (Constants.ARTS.equals(stream)) {
                if (Constants.HAINAN.equals(examTypeVo.getProvinceDesc())) {
                    if (!Constants.HIANAN_ARTS_SUBJECTS.contains(subject.getExamSubjectDesc())) {
                        subject.setSubjectDisable(true);
                    }
                } else if (Constants.JIANGSU.equals(examTypeVo.getProvinceDesc())) {
                    if (!Constants.JIANGSU_ARTS_SUBJECTS.contains(subject.getExamSubjectDesc())) {
                        subject.setSubjectDisable(true);
                    }
                } else if (Constants.HMT_NJCEE_EXAM_PLACES.contains(examTypeVo.getProvinceDesc())) {
                    if (!Constants.HMT_ARTS_SUBJECTS.contains(subject.getExamSubjectDesc())) {
                        subject.setSubjectDisable(true);
                    }
                } else {
                    if (!Constants.COMM_ARTS_SUBJECTS.contains(subject.getExamSubjectDesc())) {
                        subject.setSubjectDisable(true);
                    }
                }
            }
            if (Constants.SCI.equals(stream)) {
                if (Constants.HAINAN.equals(examTypeVo.getProvinceDesc())) {
                    if (!Constants.HAINAN_SCI_SUBJECTS.contains(subject.getExamSubjectDesc())) {
                        subject.setSubjectDisable(true);
                    }
                } else if (Constants.JIANGSU.equals(examTypeVo.getProvinceDesc())) {
                    if (!Constants.JIANGSU_SCI_SUBJECTS.contains(subject.getExamSubjectDesc())) {
                        subject.setSubjectDisable(true);
                    }
                } else if (Constants.HMT_NJCEE_EXAM_PLACES.contains(examTypeVo.getProvinceDesc())) {
                    if (!Constants.HMT_SCI_SUBJECTS.contains(subject.getExamSubjectDesc())) {
                        subject.setSubjectDisable(true);
                    }
                } else {
                    if (!Constants.COMM_SCI_SUBJECTS.contains(subject.getExamSubjectDesc())) {
                        subject.setSubjectDisable(true);
                    }
                }
            }
            if (Constants.OTHERS.equals(stream)) {
                if (Constants.SHANGHAI.equals(examTypeVo.getProvinceDesc())) {
                    if (!Constants.SHANGHAI_OTHERS_SUBJECTS.contains(subject.getExamSubjectDesc())) {
                        subject.setSubjectDisable(true);
                    }
                } else {
                    if (!Constants.ZHEJIANG_OTHERS_SUBJECTS.contains(subject.getExamSubjectDesc())) {
                        subject.setSubjectDisable(true);
                    }
                }
            }
        }
    }

    public boolean saveQualificationRslt() {
        if (!validateQualificationRslt()) {
            return false;
        }
        if (StringUtils.isNotBlank(editQualificationRslt.getId())) {
            editQualificationRslt.setIsDeleted(editQualificationRslt.getIsDeleted());
            editQualificationRslt.setIsDeleteApproved(editQualificationRslt.getIsDeleteApproved());
        } else {
            editQualificationRslt.setIsDeleted(false);
            editQualificationRslt.setIsDeleteApproved(false);
        }
        if (Constants.EXAM_NJCEE_CODES.contains(examTypeVo.getExamCd())) {
            if (StringUtils.isEmpty(editNjceeQualificationRsltId)) {
                for (QualificationVo qualification : qualifications) {
                    if (njceeQualificationId.equals(qualification.getId())) {
                        SubjectVo subjectVo = subjectService.get(editQualificationRslt.getExamSubjectId());
                        editQualificationRslt.setExamSubjectDesc(subjectVo.getExamSubjectDesc());
                        qualification.getNjceeRsltMap().put(new UUID().toString().replace("-", ""),
                                editQualificationRslt);
                        break;
                    }
                }
            }
            njceeQualificationId = null;
            editNjceeQualificationRsltId = null;
        } else {
            editQualificationRslt = qualificationRsltService.save(editQualificationRslt);
            updateQualificationRslts(editQualificationRslt.getAppQualificationId());
        }
        UIUtil.execute("PF('subjectDialog').hide()");
        UIUtil.displaySaveSuccessDialog(InternationalizationBean.locale);
        return true;
    }

    public void deleteQualificationRslt(String qualificationRsltId, String examTypeId) {
        examTypeVo = typeService.get(examTypeId);
        if (StringUtils.isNotBlank(qualificationRsltId)) {
            QualificationRsltVo qualificationRslt = qualificationRsltService.get(qualificationRsltId);
            ApplicationVo applicationVo = applicationService.get(applicationId);
            if ((new Date().compareTo(appEndDate) > 0
                    && Constants.FIRM_OFFER_RECEIVED.equals(applicationVo.getStatus()))
                    || (new Date().compareTo(appEndDate) > 0
                            && Constants.STATUS_SUBMITTED.equals(applicationVo.getStatus()))) {
                qualificationRslt.setIsDeleted(true);
                qualificationRslt.setIsDeleteApproved(false);
            } else {
                qualificationRslt.setIsDeleted(true);
                qualificationRslt.setIsDeleteApproved(true);
            }
            qualificationRsltService.save(qualificationRslt);
            updateQualificationRslts(qualificationRslt.getAppQualificationId());
        }
    }

    public void deleteNjceeQualificationRslt(String key, String qualificationId,
            Map<String, QualificationRsltVo> njceeRsltMap) {
        ApplicationVo applicationVo = applicationService.get(applicationId);
        for (Map.Entry<String, QualificationRsltVo> entry : njceeRsltMap.entrySet()) {
            if (key.equals(entry.getKey())) {
                QualificationRsltVo rsltVo = entry.getValue();
                if ((new Date().compareTo(appEndDate) > 0
                        && Constants.FIRM_OFFER_RECEIVED.equals(applicationVo.getStatus()))
                        || (new Date().compareTo(appEndDate) > 0
                                && Constants.STATUS_SUBMITTED.equals(applicationVo.getStatus()))) {
                    rsltVo.setIsDeleted(true);
                    rsltVo.setIsDeleteApproved(false);
                } else {
                    rsltVo.setIsDeleted(true);
                    rsltVo.setIsDeleteApproved(true);
                    njceeRsltMap.remove(key);
                }
                if (StringUtils.isNotBlank(rsltVo.getId())) {
                    qualificationRsltService.save(rsltVo);
                }
                break;
            }
        }
    }

    public void updateQualificationRslts(String appQualificationId) {
        List<QualificationRsltVo> qualificationRslts = qualificationRsltService
                .getByAppQualificationId(appQualificationId);
        Integer ibAchievedRsltPoints = 0;
        Integer ibPredictedRsltPoints = 0;
        if (Constants.EXAM_IBD.equals(examTypeVo.getExamCd())) {
            Map<String, Integer> rsltPoints = calculateRsltPoints(appQualificationId);
            ibAchievedRsltPoints = rsltPoints.get("ibAchievedRsltPoints");
            ibPredictedRsltPoints = rsltPoints.get("ibPredictedRsltPoints");
        }
        for (QualificationVo qualification : qualifications) {
            if (qualification.getId().equals(appQualificationId)) {
                qualification.setQualificationRslts(qualificationRslts);
                if (Constants.EXAM_IBD.equals(qualification.getType().getExamCd())) {
                    qualification.setIbAchievedRsltPoints(String.valueOf(ibAchievedRsltPoints));
                    qualification.setIbPredictedRsltPoints(String.valueOf(ibPredictedRsltPoints));
                    qualificationService.save(qualification);
                }
                break;
            }
        }
    }

    public Map<String, Integer> calculateRsltPoints(String appQualificationId) {
        LOG.info("calculateRsltPoints() start - appQualificationId: " + appQualificationId);
        Integer ibAchievedRsltPoints = 0;
        Integer ibPredictedRsltPoints = 0;
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        boolean matches;
        List<QualificationRsltVo> qualificationRslts = qualificationRsltService
                .getByAppQualificationId(appQualificationId);
        for (QualificationVo qualificationVo : qualifications) {
            if (qualificationVo.getId().equals(appQualificationId)) {
                if (StringUtils.isNotBlank(qualificationVo.getIbAchievedRsltGradeCd())) {
                    matches = pattern.matcher(qualificationVo.getIbAchievedRsltGradeCd()).matches();
                    if (matches) {
                        ibAchievedRsltPoints = ibAchievedRsltPoints
                                + Integer.valueOf(qualificationVo.getIbAchievedRsltGradeCd());
                    }
                }
                if (StringUtils.isNotBlank(qualificationVo.getIbPredictedRsltGradeCd())) {
                    matches = pattern.matcher(qualificationVo.getIbPredictedRsltGradeCd()).matches();
                    if (matches) {
                        ibPredictedRsltPoints = ibPredictedRsltPoints
                                + Integer.valueOf(qualificationVo.getIbPredictedRsltGradeCd());
                    }
                }
                break;
            }
        }
        for (QualificationRsltVo qualificationRsltVo : qualificationRslts) {
            if (StringUtils.isNotBlank(qualificationRsltVo.getAchievedGradeCd())) {
                matches = pattern.matcher(qualificationRsltVo.getAchievedGradeCd()).matches();
                if (matches) {
                    ibAchievedRsltPoints = ibAchievedRsltPoints
                            + Integer.valueOf(qualificationRsltVo.getAchievedGradeCd());
                }
            }
            if (StringUtils.isNotBlank(qualificationRsltVo.getPredictedGradeCd())) {
                matches = pattern.matcher(qualificationRsltVo.getPredictedGradeCd()).matches();
                if (matches) {
                    ibPredictedRsltPoints = ibPredictedRsltPoints
                            + Integer.valueOf(qualificationRsltVo.getPredictedGradeCd());
                }
            }
        }
        Map<String, Integer> rsltPoints = new HashMap<String, Integer>();
        rsltPoints.put("ibAchievedRsltPoints", ibAchievedRsltPoints);
        rsltPoints.put("ibPredictedRsltPoints", ibPredictedRsltPoints);
        LOG.info("calculateRsltPoints() end - returnVal: " + rsltPoints);
        return rsltPoints;
    }

    public void removeQualification(String qualificationId) {
        if (StringUtils.isNotBlank(qualificationId)) {
            ApplicationVo applicationVo = applicationService.get(applicationId);
            if ((new Date().compareTo(appEndDate) > 0
                    && Constants.FIRM_OFFER_RECEIVED.equals(applicationVo.getStatus()))
                    || (new Date().compareTo(appEndDate) > 0
                            && Constants.STATUS_SUBMITTED.equals(applicationVo.getStatus()))) {
                for (QualificationVo qualificationVo : qualifications) {
                    if (qualificationVo.getId().equals(qualificationId)) {
                        qualificationVo.setIsDeleted(true);
                        qualificationVo.setIsDeleteApproved(false);
                        qualificationService.save(qualificationVo);
                        break;
                    }
                }
            } else {
                for (QualificationVo qualificationVo : qualifications) {
                    if (qualificationVo.getId().equals(qualificationId)) {
                        qualificationVo.setIsDeleted(true);
                        qualificationVo.setIsDeleteApproved(true);
                        deleteDocumentRequirement(qualificationVo);
                        qualificationService.save(qualificationVo);
                        qualifications.remove(qualificationVo);
                        break;
                    }
                }
            }
            RefereeBean refereeBean = JSFUtil.getCurrentInstance("refereeBean", RefereeBean.class);
            refereeBean.activeReference();
        }
        tempQualification = new QualificationVo();
    }

    private void deleteDocumentRequirement(QualificationVo qualification) {
        ReqDocConfVo reqDocConfVo = reqDocConfService.getByTypeAndCdAndName(Constants.DOC_TYPE_OTHERS,
                qualification.getType().getExamCd(), qualification.getType().getExamDesc() + " Transcript");
        if (reqDocConfVo != null) {
            ReqDocVo reqDocVo = reqDocService.getByApplicationIdAndReqDocConfIdAndQualificationId(applicationId,
                    reqDocConfVo.getId(), qualification.getId());
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

    public boolean validateQualificationRslt() {
        boolean valid = true;
        if ("0".equals(editQualificationRslt.getExamSubjectId())) {
            if (isSelectSubject) {
                UIUtil.setInvalid("mainTab:formQualiRslt:rsltSelectExamSubjectDesc");
            } else {
                UIUtil.setInvalid("mainTab:formQualiRslt:rsltExamSubjectDesc");
                UIUtil.setInvalid("mainTab:formQualiRslt:rsltExamGceSubjectDesc");
            }
            valid = false;
        }
        if (Constants.EXAM_GCE_CODES.contains(examTypeVo.getExamCd())
                || Constants.EXAM_SAT_CODES.contains(examTypeVo.getExamCd())) {
            if ("0".equals(editQualificationRslt.getExamLevel())
                    || StringUtils.isBlank(editQualificationRslt.getExamLevel())) {
                if (isSelectLevel) {
                    UIUtil.setInvalid("mainTab:formQualiRslt:rsltSelectExamLevel");
                    UIUtil.setInvalid("mainTab:formQualiRslt:rsltGceSelectExamLevel");
                } else {
                    UIUtil.setInvalid("mainTab:formQualiRslt:rsltExamLevel");
                    UIUtil.setInvalid("mainTab:formQualiRslt:rsltGceExamLevel");
                }
                valid = false;
            }
        }
        if (Constants.EXAM_IB_CODES.contains(examTypeVo.getExamCd())) {
            if ("0".equals(editQualificationRslt.getExamLevel())
                    || StringUtils.isBlank(editQualificationRslt.getExamLevel())) {
                UIUtil.setInvalid("mainTab:formQualiRslt:rsltIbExamLevel");
                valid = false;
            }
        }
        if (Constants.EXAM_NJCEE_CODES.contains(examTypeVo.getExamCd())) {
            if (Constants.JIANGSU.equals(examTypeVo.getProvinceDesc())) {
                if (editQualificationRslt.getIsActiveAchievedGrade()) {
                    if (StringUtils.isBlank(editQualificationRslt.getAchievedGradeCd())) {
                        UIUtil.setInvalid("mainTab:formQualiRslt:rsltAchievedGradeCdJs");
                        valid = false;
                    }
                }
            }
        } else {
            if (Constants.EXAM_GCE_CODES.contains(examTypeVo.getExamCd())) {
                if (StringUtils.isBlank(editQualificationRslt.getExamBoard())) {
                    if (isSelectBoard) {
                        UIUtil.setInvalid("mainTab:formQualiRslt:rsltGceSelectExamBoard");
                    } else {
                        UIUtil.setInvalid("mainTab:formQualiRslt:rsltGceExamBoard");
                    }
                    valid = false;
                }
                if (editQualificationRslt.getGceDateOfRelease() == null) {
                    UIUtil.setInvalid("mainTab:formQualiRslt:gecDateOfRelease");
                    valid = false;
                }
            } else if (Constants.EXAM_SAT_CODES.contains(examTypeVo.getExamCd())) {
                if (StringUtils.isBlank(editQualificationRslt.getAchievedGradeOthers())) {
                    UIUtil.setInvalid("mainTab:formQualiRslt:satAchievedScore");
                    valid = false;
                }
            } else if (Constants.EXAM_IT_CODES.contains(examTypeVo.getExamCd())) {
                if (StringUtils.isBlank(editQualificationRslt.getAchievedGradeOthers())) {
                    UIUtil.setInvalid("mainTab:formQualiRslt:itAchievedScore");
                    valid = false;
                }
            }
            if (editQualificationRslt.getIsPredictedOthers()) {
                if (StringUtils.isBlank(editQualificationRslt.getPredictedGradeOthers())) {
                    UIUtil.setInvalid("mainTab:formQualiRslt:predictedGradeOthers");
                    UIUtil.setInvalid("mainTab:formQualiRslt:gcePredictedGradeOthers");
                    valid = false;
                }
            }
            if (editQualificationRslt.getIsAchievedOthers()) {
                if (StringUtils.isBlank(editQualificationRslt.getAchievedGradeOthers())) {
                    UIUtil.setInvalid("mainTab:formQualiRslt:achievedGradeOthers");
                    UIUtil.setInvalid("mainTab:formQualiRslt:gceAchievedOthers");
                    valid = false;
                }
            }
        }
        if (!valid) {
            UIUtil.displayErrorDialog(InternationalizationBean.locale, ConstantCommon.MADATORY_FIELD_MISSING,
                    ConstantCommon.MADATORY_FIELD_MISSING_CHI);
            return false;
        } else {
            return true;
        }
    }

    public boolean validateQualifications() {
        boolean valid = true;
        boolean subjectValid = false;
        for (int i = 0; i < qualifications.size(); i++) {
            QualificationVo qualification = qualifications.get(i);
            if (qualification.getDateOfReleaseOfRslt() == null
                    && !Constants.EXAM_NJCEE_CODES.contains(qualification.getType().getExamCd())
                    && !Constants.EXAM_GCE_CODES.contains(qualification.getType().getExamCd())) {
                UIUtil.setInvaliTabPanelCalendar("mainTab:formQualifications:panelQuali", null);
                currPanelIndex = i;
                valid = false;
            }
            if (Constants.EXAM_IBD.equals(qualification.getType().getExamCd())) {
                if (StringUtils.isBlank(qualification.getIbAchievedRslt())
                        && StringUtils.isBlank(qualification.getIbPredictedRslt())) {
                    currPanelIndex = i;
                    UIUtil.setInvalidTabPanelSelectMenu("mainTab:formQualifications:panelQuali",
                            Constants.IB_ACHIEVEDRSLT_MENU, i);
                    UIUtil.setInvalidTabPanelSelectMenu("mainTab:formQualifications:panelQuali",
                            Constants.IB_PREDICTEDRSLT_MENU, i);
                    valid = false;
                }
                if (StringUtils.isBlank(qualification.getIbAchievedRsltGradeCd())
                        && StringUtils.isBlank(qualification.getIbPredictedRsltGradeCd())) {
                    currPanelIndex = i;
                    UIUtil.setInvalidTabPanelSelectMenu("mainTab:formQualifications:panelQuali",
                            Constants.ET_ACHIEVED_RSLT_MENU, i);
                    UIUtil.setInvalidTabPanelSelectMenu("mainTab:formQualifications:panelQuali",
                            Constants.ET_PREDICTED_RSLT_MENU, i);
                    valid = false;
                }
            }
            if (Constants.EXAM_NJCEE_CODES.contains(qualification.getType().getExamCd())) {
                if (StringUtils.isBlank(qualification.getNjceeExamProvinceId())) {
                    if (Constants.NJCEE.equals(qualification.getType().getExamCd())) {
                        UIUtil.setInvalidTabPanelSelectMenu("mainTab:formQualifications:panelQuali",
                                Constants.NJCEE_EXAMPLACECD_MENU, i);
                    } else {
                        UIUtil.setInvalidTabPanelSelectMenu("mainTab:formQualifications:panelQuali",
                                Constants.HMT_NJCEE_EXAMPLACECD_MENU, i);
                    }
                    currPanelIndex = i;
                    valid = false;
                }
                if (StringUtils.isBlank(qualification.getNjceeStreamCd())) {
                    currPanelIndex = i;
                    UIUtil.setInvalidTabPanelSelectMenu("mainTab:formQualifications:panelQuali",
                            Constants.NJCEE_STREAMCD_MENU, i);
                    valid = false;
                }
                if (!validNJCEESubject(qualification.getNjceeRsltMap(), qualification.getNjceeExamProvinceId(),
                        qualification.getNjceeStreamCd())) {
                    currPanelIndex = i;
                    subjectValid = true;
                }
                if (StringUtils.isBlank(qualification.getNjceeTotalScore())) {
                    currPanelIndex = i;
                    UIUtil.setInvalidTabPanelTableFooter("mainTab:formQualifications:panelQuali", Constants.NJCEE_TABLE,
                            Constants.TOTAL_SCORE, i);
                    valid = false;
                } else {
                    if (StringUtils.isNotBlank(qualification.getNjceeExamProvinceId())
                            && StringUtils.isNotBlank(qualification.getNjceeStreamCd())) {
                        AppNjceeScoringSystemVo appNjceeScoringSystem = appNjceeScoringSystemService
                                .getByProvinceIdAndStream(qualification.getNjceeExamProvinceId(),
                                        qualification.getNjceeStreamCd());
                        if (Integer.valueOf(qualification.getNjceeTotalScore()) > Integer
                                .valueOf(appNjceeScoringSystem.getTotalScore())) {
                            currPanelIndex = i;
                            subjectValid = true;
                            UIUtil.setInvalidTabPanelTableFooter("mainTab:formQualifications:panelQuali",
                                    Constants.NJCEE_TABLE, Constants.TOTAL_SCORE, i);
                            UIUtil.displayErrorDialog(InternationalizationBean.locale,
                                    Constants.INVALID_TOTAL_SCORE_WRONG, Constants.INVALID_TOTAL_SCORE_WRONG_CHI);
                        }
                    }
                }
            }
            if (qualification.getType().getExamCd().equals(Constants.TOEFL_IT)
                    || qualification.getType().getExamCd().equals(Constants.TOEFL_PT)) {
                if (StringUtils.isBlank(qualification.getToeflIdno())) {
                    currPanelIndex = i;
                    UIUtil.setInvalidTabPanelInput("mainTab:formQualifications:panelQuali", Constants.TOEFL_NO_INPUT);
                    valid = false;
                }
            }
        }

        if (!valid && subjectValid) {
            UIUtil.displayErrorDialog(InternationalizationBean.locale, ConstantCommon.MADATORY_FIELD_MISSING,
                    ConstantCommon.MADATORY_FIELD_MISSING_CHI);
            return false;
        } else if (!valid) {
            UIUtil.displayErrorDialog(InternationalizationBean.locale, ConstantCommon.MADATORY_FIELD_MISSING,
                    ConstantCommon.MADATORY_FIELD_MISSING_CHI);
            return false;
        } else if (subjectValid) {
            return false;
        } else {
            return true;
        }
    }

    public boolean validNJCEESubject(Map<String, QualificationRsltVo> njceeRsltMap, String provinceId, String stream) {
        boolean valid = false;
        if (StringUtils.isNotBlank(provinceId) && StringUtils.isNotBlank(stream)) {
            ProvinceVo provinceVo = provinceService.get(provinceId);
            AppNjceeScoringSystemVo appNjceeScoringSystem = appNjceeScoringSystemService
                    .getByProvinceIdAndStream(provinceId, stream);
            if (StringUtils.isNotBlank(appNjceeScoringSystem.getId())) {
                List<AppNjceeSubjectStructureVo> subjectStructureList = appNjceeSubjectStructureService
                        .getByAppNjceeScoringSystemIdAndType(appNjceeScoringSystem.getId(), Constants.COMPULSORY);
                List<QualificationRsltVo> qualificationRsltList = new ArrayList<QualificationRsltVo>();
                for (Map.Entry<String, QualificationRsltVo> entry : njceeRsltMap.entrySet()) {
                    QualificationRsltVo rslt = entry.getValue();
                    qualificationRsltList.add(rslt);
                }
                int compulsoryCount = 0;
                String invalidSubject = "";
                for (AppNjceeSubjectStructureVo subjectStructure : subjectStructureList) {
                    for (QualificationRsltVo qualificationRslt : qualificationRsltList) {
                        if (subjectStructure.getSubjectId().equals(qualificationRslt.getExamSubjectId())) {
                            if (StringUtils.isNotBlank(qualificationRslt.getAchievedGradeOthers())) {
                                if (Integer.valueOf(qualificationRslt.getAchievedGradeOthers()) <= Integer
                                        .valueOf(subjectStructure.getSubjectScore())) {
                                    compulsoryCount++;
                                } else {
                                    compulsoryCount++;
                                    invalidSubject = invalidSubject + qualificationRslt.getExamSubjectDesc() + ",";
                                }
                            } else {
                                compulsoryCount++;
                            }
                            break;
                        }
                    }
                }
                if (compulsoryCount == subjectStructureList.size() && StringUtils.isBlank(invalidSubject)) {
                    if (Constants.JIANGSU.equals(provinceVo.getDescription())) {
                        valid = validJsSubjectStructure(appNjceeScoringSystem.getId(), qualificationRsltList, stream);
                    } else if (Constants.SHANGHAI.equals(provinceVo.getDescription())) {
                        valid = validShAndZjSubjectStructure(appNjceeScoringSystem.getId(), qualificationRsltList);
                    } else if (Constants.ZHEJIANG.equals(provinceVo.getDescription())) {
                        valid = validShAndZjSubjectStructure(appNjceeScoringSystem.getId(), qualificationRsltList);
                    } else {
                        valid = true;
                    }
                } else {
                    if (StringUtils.isNotBlank(invalidSubject)) {
                        UIUtil.displayErrorDialog(InternationalizationBean.locale,
                                invalidSubject + Constants.INVALID_SCORE_WRONG,
                                invalidSubject + Constants.INVALID_SCORE_WRONG_CHI);
                    }
                    if (compulsoryCount != subjectStructureList.size()) {
                        UIUtil.displayErrorDialog(InternationalizationBean.locale, Constants.FILL_SUBJECT_WRONG,
                                Constants.FILL_SUBJECT_WRONG_CHI);
                    }
                    valid = false;
                }
            }
        } else {
            valid = false;
        }
        return valid;
    }

    public boolean validJsSubjectStructure(String appNjceeScoringSystemId,
            List<QualificationRsltVo> qualificationRsltList, String stream) {
        List<AppNjceeSubjectStructureVo> jsSubjectStructureList = appNjceeSubjectStructureService
                .getByAppNjceeScoringSystemIdAndType(appNjceeScoringSystemId, Constants.ELECTIVE);
        int electiveCount = 0;
        boolean findHistory = false;
        boolean findPhysics = false;
        for (AppNjceeSubjectStructureVo subjectStructure : jsSubjectStructureList) {
            for (QualificationRsltVo qualificationRslt : qualificationRsltList) {
                if (subjectStructure.getSubjectId().equals(qualificationRslt.getExamSubjectId())) {
                    electiveCount++;
                    if (Constants.ARTS.equals(stream)) {
                        if (Constants.HISTORY.equals(qualificationRslt.getExamSubjectDesc())) {
                            findHistory = true;
                        }
                    } else {
                        if (Constants.PHYSICS.equals(qualificationRslt.getExamSubjectDesc())) {
                            findPhysics = true;
                        }
                    }
                    break;
                }
            }
        }
        if (electiveCount == 2 && (findHistory || findPhysics)) {
            return true;
        } else {
            if (electiveCount != 2) {
                UIUtil.displayErrorDialog(InternationalizationBean.locale, Constants.FILL_SUBJECT_WRONG,
                        Constants.FILL_SUBJECT_WRONG_CHI);
            }
            return false;
        }
    }

    public boolean validShAndZjSubjectStructure(String appNjceeScoringSystemId,
            List<QualificationRsltVo> qualificationRsltList) {
        List<AppNjceeSubjectStructureVo> shSubjectStructureList = appNjceeSubjectStructureService
                .getByAppNjceeScoringSystemIdAndType(appNjceeScoringSystemId, Constants.ELECTIVE);
        int electiveCount = 0;
        String invalidSubject = "";
        for (AppNjceeSubjectStructureVo subjectStructure : shSubjectStructureList) {
            for (QualificationRsltVo qualificationRslt : qualificationRsltList) {
                if (subjectStructure.getSubjectId().equals(qualificationRslt.getExamSubjectId())) {
                    if (StringUtils.isNotBlank(qualificationRslt.getAchievedGradeOthers())) {
                        if (Integer.valueOf(qualificationRslt.getAchievedGradeOthers()) <= Integer
                                .valueOf(subjectStructure.getSubjectScore())) {
                            electiveCount++;
                        } else {
                            electiveCount++;
                            invalidSubject = invalidSubject + qualificationRslt.getExamSubjectDesc() + ",";
                        }
                    } else {
                        electiveCount++;
                    }
                    break;
                }
            }
        }
        if (electiveCount == 3 && StringUtils.isBlank(invalidSubject)) {
            return true;
        } else {
            if (StringUtils.isNotBlank(invalidSubject)) {
                UIUtil.displayErrorDialog(InternationalizationBean.locale,
                        invalidSubject + Constants.INVALID_SCORE_WRONG,
                        invalidSubject + Constants.INVALID_SCORE_WRONG_CHI);
            }
            if (electiveCount != 3) {
                UIUtil.displayErrorDialog(InternationalizationBean.locale, Constants.FILL_SUBJECT_WRONG,
                        Constants.FILL_SUBJECT_WRONG_CHI);
            }
            return false;
        }
    }

    public boolean saveQualifications() {
        if (!validateQualifications()) {
            return false;
        }
        try {
            for (QualificationVo qualification : qualifications) {
                if (Constants.EXAM_NJCEE_CODES.contains(qualification.getType().getExamCd())) {
                    for (Map.Entry<String, QualificationRsltVo> entry : qualification.getNjceeRsltMap().entrySet()) {
                        QualificationRsltVo rsltVo = entry.getValue();
                        qualificationRsltService.save(rsltVo);
                    }
                }
            }
            qualifications = qualificationService.save(qualifications);
            if (qualifications.size() > 0) {
                if (StringUtils.isNotBlank(applicationId)) {
                    ProgressVo progress = progressService.findByApplicationId(applicationId);
                    progress.setOtherQuali(true);
                    progressService.save(progress);
                }
            }
            AccountVo accountVo = AccountUtils.getAccountVo();
            AuditLogUtil.saveAuditLog(accountVo.getId(), Constants.SAVED_ACADEMIC_QUALIFICATIONS);
            RefereeBean refereeBean = JSFUtil.getCurrentInstance("refereeBean", RefereeBean.class);
            refereeBean.activeReference();
        } catch (Exception e) {
            // TODO: handle exception
        }
        UIUtil.displaySaveSuccessDialog(InternationalizationBean.locale);
        return true;
    }

    public void savePrePage(String index) {
        if (saveQualifications()) {
            processDocumentRequirements();
            ApplicationBean applicationBean = JSFUtil.getCurrentInstance("applicationBean", ApplicationBean.class);
            applicationBean.prePage(index);
        }
    }

    public void saveNextPage(String index) {
        if (saveQualifications()) {
            processDocumentRequirements();
            ApplicationBean applicationBean = JSFUtil.getCurrentInstance("applicationBean", ApplicationBean.class);
            applicationBean.nextPage(index);
        }
    }

    private void processDocumentRequirements() {
        for (QualificationVo qualification : qualifications) {
            addDocumentRequirement(qualification);
        }
    }

    private void addDocumentRequirement(QualificationVo qualification) {
        ReqDocConfVo reqDocConfVo = reqDocConfService.getByTypeAndCdAndName(Constants.DOC_TYPE_OTHERS,
                qualification.getType().getExamCd(), qualification.getType().getExamDesc() + " Transcript");
        if (reqDocConfVo == null) {
            reqDocConfVo = new ReqDocConfVo();
            reqDocConfVo.setReqDocType(Constants.DOC_TYPE_OTHERS);
            reqDocConfVo.setReqDocCd(qualification.getType().getExamCd());
            reqDocConfVo.setReqDocName(qualification.getType().getExamDesc() + " Transcript");
            reqDocConfVo = reqDocConfService.save(reqDocConfVo);
        }
        ReqDocVo reqDocVo = reqDocService.getByApplicationIdAndReqDocConfIdAndQualificationId(applicationId,
                reqDocConfVo.getId(), qualification.getId());
        if (reqDocVo == null) {
            reqDocVo = new ReqDocVo();
            reqDocVo.setApplicationId(applicationId);
            reqDocVo.setStatusCd(Constants.WAITING_FOR_UPLOAD);
            ApplicationVo applicationVo = applicationService.get(applicationId);
            FormVo formVo = formService.get(applicationVo.getAdmFormId());
            ExeVo exeVo = exeService.get(formVo.getAdmExeId());
            reqDocVo.setSubmissionDueDate(exeVo.getApplicationEndDate());
            reqDocVo.setReqDocConfId(reqDocConfVo.getId());
            reqDocVo.setQualificationId(qualification.getId());
            reqDocService.save(reqDocVo);
        }
    }

    public void loadExamType(AjaxBehaviorEvent event) {
        SelectOneMenu menu = (SelectOneMenu) event.getSource();
        String examTypeCd = menu.getValue().toString();
        examTypeList = new ArrayList<TypeVo>();
        if (!"0".equals(examTypeCd)) {
            for (TypeVo type : allExamTypeList) {
                switch (examTypeCd) {
                case Constants.NJCEE:
                    if (Constants.EXAM_NJCEE_CODES.contains(type.getExamCd())) {
                        examTypeList.add(type);
                        editQualification.setIsSelectNJCEE(true);
                    }
                    break;
                case Constants.HHH:
                    if (Constants.EXAM_HHH_CODES.contains(type.getExamCd())) {
                        examTypeList.add(type);
                        editQualification.setIsSelectNJCEE(false);
                    }
                    break;
                case Constants.IB:
                    if (Constants.EXAM_IB_CODES.contains(type.getExamCd())) {
                        examTypeList.add(type);
                        editQualification.setIsSelectNJCEE(false);
                    }
                    break;
                case Constants.ALL_GCE:
                    if (Constants.EXAM_GCE_CODES.contains(type.getExamCd())) {
                        examTypeList.add(type);
                        editQualification.setIsSelectNJCEE(false);
                    }
                    break;
                case Constants.SAT:
                    if (Constants.EXAM_SAT_CODES.contains(type.getExamCd())) {
                        examTypeList.add(type);
                        editQualification.setIsSelectNJCEE(false);
                    }
                    break;
                case Constants.IT:
                    if (Constants.EXAM_IT_CODES.contains(type.getExamCd())) {
                        examTypeList.add(type);
                        editQualification.setIsSelectNJCEE(false);
                    }
                    break;
                default:
                    editQualification.setIsSelectNJCEE(false);
                    break;
                }
            }
        } else {
            editQualification.setIsSelectNJCEE(false);
        }
    }

    @SuppressWarnings("deprecation")
    public void loadExamYear(AjaxBehaviorEvent event) {
        SelectOneMenu menu = (SelectOneMenu) event.getSource();
        String examTypeCd = menu.getValue().toString();
        examYears = new ArrayList<String>();
        if (!"0".equals(examTypeCd)) {
            Integer year = 0;
            if (examTypeCd.equals(Constants.IT)) {
                for (int i = 0; i < 3; i++) {
                    year = Integer.valueOf(new Date().getYear()) + 1900 - i;
                    examYears.add(year.toString());
                }
            } else {
                for (int i = 0; i < 10; i++) {
                    year = Integer.valueOf(new Date().getYear()) + 1900 - i;
                    examYears.add(year.toString());
                }
            }
        } else {
            editQualification.setExamTypeYear(null);
        }
    }

    public void loadSubjectDesc(AjaxBehaviorEvent event) {
        SelectOneMenu menu = (SelectOneMenu) event.getSource();
        String examLevel = menu.getValue().toString();
        isSelectSubject = true;
        selectSubject = new ArrayList<SubjectVo>();
        editQualificationRslt.setExamSubjectId("");
        if (!"0".equals(examLevel)) {
            for (SubjectVo subject : originSubjectList) {
                if (!subject.getSubjectDisable()) {
                    if (subject.getExamLevel().equals(examLevel)) {
                        selectSubject.add(subject);
                    }
                }
            }
        }
    }

    public void loadExamBoard(AjaxBehaviorEvent event) {
        LOG.info("loadExamBoard() start - " + event);
        SelectOneMenu menu = (SelectOneMenu) event.getSource();
        String examLevel = menu != null && menu.getValue() != null ? menu.getValue().toString() : "";
        selectBoards = new ArrayList<String>();
        selectSubject = new ArrayList<SubjectVo>();
        if (StringUtils.isNotBlank(examLevel)) {
            isSelectBoard = true;
            for (SubjectVo subject : originSubjectList) {
                if (!subject.getSubjectDisable()) {
                    // load boads
                    if (subject.getExamLevel().equals(examLevel) && !selectBoards.contains(subject.getExamBoard())) {
                        selectBoards.add(subject.getExamBoard());
                    }
                    // load subject
                    if (subject.getExamBoard().equals(editQualificationRslt.getExamBoard())
                            && subject.getExamLevel().equals(examLevel)) {
                        selectSubject.add(subject);
                    }
                }
            }
            // load Grades
            if (Constants.EXAM_GCE_GRADE_LEVELS.contains(examTypeVo.getExamCd())) {
                grades = gradeService.getByExamTypeIdAndExamLevel(examTypeVo.getId(), examLevel);
            } else {
                grades = gradeService.getByExamTypeId(examTypeVo.getId());
            }
        } else {
            grades = new ArrayList<GradeVo>();
            editQualificationRslt.setExamSubjectId("");
            editQualificationRslt.setAchievedGradeCd("");
            editQualificationRslt.setPredictedGradeCd("");
            editQualificationRslt.setAchievedGradeOthers("");
            editQualificationRslt.setPredictedGradeOthers("");
            editQualificationRslt.setIsPredictedOthers(false);
            editQualificationRslt.setIsAchievedOthers(false);
            loadSubjectAndLevel(originSubjectList);
        }
        LOG.info("loadExamBoard() end");
    }

    public void loadGceExamLevel(AjaxBehaviorEvent event) {
        LOG.info("loadGceExamLevel() start - " + event);
        SelectOneMenu menu = (SelectOneMenu) event.getSource();
        String examBoard = menu != null && menu.getValue() != null ? menu.getValue().toString() : "";
        selectLevel = new ArrayList<String>();
        selectSubject = new ArrayList<SubjectVo>();
        if (StringUtils.isNotBlank(examBoard)) {
            isSelectLevel = true;
            for (SubjectVo subject : originSubjectList) {
                if (!subject.getSubjectDisable()) {
                    // load level
                    if (subject.getExamBoard().equals(examBoard) && !selectLevel.contains(subject.getExamLevel())) {
                        selectLevel.add(subject.getExamLevel());
                    }
                    // load subject
                    if (subject.getExamBoard().equals(examBoard)
                            && subject.getExamLevel().equals(editQualificationRslt.getExamLevel())) {
                        selectSubject.add(subject);
                    }
                }
            }
        } else {
            editQualificationRslt.setExamSubjectId("");
            loadSubjectAndLevel(originSubjectList);
        }
        LOG.info("loadGceExamLevel() end");
    }

    public void loadIbExamLevel(AjaxBehaviorEvent event) {
        LOG.info("loadIbExamLevel() start - " + event);
        SelectOneMenu menu = (SelectOneMenu) event.getSource();
        String examSubjectId = menu != null && menu.getValue() != null ? menu.getValue().toString() : "";
        selectLevel = new ArrayList<String>();
        if (StringUtils.isNotBlank(examSubjectId) && !"0".equals(examSubjectId)) {
            SubjectVo subjectVo = subjectService.get(examSubjectId);
            for (SubjectVo subject : originSubjectList) {
                if (!subject.getSubjectDisable()) {
                    if (subject.getExamSubjectDesc().equals(subjectVo.getExamSubjectDesc())
                            && !selectLevel.contains(subject.getExamLevel())) {
                        selectLevel.add(subject.getExamLevel());
                    }
                }
            }
        } else {
            editQualificationRslt.setExamLevel("");
        }
        LOG.info("loadIbExamLevel() end");
    }

    public void loadSubjectAndLevel(List<SubjectVo> originSubjectList) {
        isSelectLevel = false;
        isSelectSubject = false;
        isSelectBoard = false;
        displaySubject = new ArrayList<SubjectVo>();
        displayLevel = new ArrayList<String>();
        examBoards = new ArrayList<String>();
        boolean subjectFlag = false;
        boolean levelFlag = false;
        boolean boardFlag = false;
        for (SubjectVo originSubject : originSubjectList) {
            if (!originSubject.getSubjectDisable()) {
                for (SubjectVo subject : displaySubject) {
                    if (subject.getExamSubjectDesc().equals(originSubject.getExamSubjectDesc())) {
                        subjectFlag = true;
                        break;
                    } else {
                        subjectFlag = false;
                    }
                }
                for (String level : displayLevel) {
                    if (level.equals(originSubject.getExamLevel())) {
                        levelFlag = true;
                        break;
                    } else {
                        levelFlag = false;
                    }
                }
                for (String board : examBoards) {
                    if (board.equals(originSubject.getExamBoard())) {
                        boardFlag = true;
                        break;
                    } else {
                        boardFlag = false;
                    }
                }
                if (!subjectFlag) {
                    displaySubject.add(originSubject);
                }
                if (!levelFlag) {
                    if (StringUtils.isNotBlank(originSubject.getExamLevel())) {
                        displayLevel.add(originSubject.getExamLevel());
                    }
                }
                if (!boardFlag) {
                    if (StringUtils.isNotBlank(originSubject.getExamBoard())) {
                        examBoards.add(originSubject.getExamBoard());
                    }
                }
            }
        }
    }

    public void editSchoolResult(String qualificationId) {
        if (StringUtils.isNotBlank(qualificationId)) {
            oneSemesterOne = mfeSchemeService.getByQualificationIdAndYear(qualificationId, 1);
            oneSemesterTwo = mfeSchemeService.getByQualificationIdAndYear(qualificationId, 2);
            twoSemesterOne = mfeSchemeService.getByQualificationIdAndYear(qualificationId, 3);
            twoSemesterTwo = mfeSchemeService.getByQualificationIdAndYear(qualificationId, 4);
            threeSemesterOne = mfeSchemeService.getByQualificationIdAndYear(qualificationId, 5);
            threeSemesterTwo = mfeSchemeService.getByQualificationIdAndYear(qualificationId, 6);
        } else {
            oneSemesterOne = new MfeSchemeVo();
            oneSemesterTwo = new MfeSchemeVo();
            twoSemesterOne = new MfeSchemeVo();
            twoSemesterTwo = new MfeSchemeVo();
            threeSemesterOne = new MfeSchemeVo();
            threeSemesterTwo = new MfeSchemeVo();
        }
        oneSemesterOne.setQualificationsId(qualificationId);
        oneSemesterOne.setYearSemester(1);
        oneSemesterTwo.setQualificationsId(qualificationId);
        oneSemesterTwo.setYearSemester(2);
        twoSemesterOne.setQualificationsId(qualificationId);
        twoSemesterOne.setYearSemester(3);
        twoSemesterTwo.setQualificationsId(qualificationId);
        twoSemesterTwo.setYearSemester(4);
        threeSemesterOne.setQualificationsId(qualificationId);
        threeSemesterOne.setYearSemester(5);
        threeSemesterTwo.setQualificationsId(qualificationId);
        threeSemesterTwo.setYearSemester(6);
    }

    public void saveSchoolResult() {
        mfeSchemeService.save(oneSemesterOne);
        mfeSchemeService.save(oneSemesterTwo);
        mfeSchemeService.save(twoSemesterOne);
        mfeSchemeService.save(twoSemesterTwo);
        mfeSchemeService.save(threeSemesterOne);
        mfeSchemeService.save(threeSemesterTwo);
        UIUtil.displaySaveSuccessDialog(InternationalizationBean.locale);
    }

    public String formatAppEndDate() {
        String time = "";
        if (appEndDate != null) {
            SimpleDateFormat df = new SimpleDateFormat("dd MMMMM,yyyy", InternationalizationBean.locale);
            time = df.format(appEndDate);
        }
        return time;
    }

    public void changeTotalPoints(String qualificationId) {
        if (StringUtils.isNotBlank(qualificationId)) {
            for (QualificationVo qualification : qualifications) {
                if (qualificationId.equals(qualification.getId())) {
                    Integer ibAchievedRsltPoints = 0;
                    Integer ibPredictedRsltPoints = 0;
                    Map<String, Integer> rsltPoints = calculateRsltPoints(qualificationId);
                    ibAchievedRsltPoints = rsltPoints.get("ibAchievedRsltPoints");
                    ibPredictedRsltPoints = rsltPoints.get("ibPredictedRsltPoints");
                    if (StringUtils.isNotBlank(qualification.getIbAchievedRsltGradeCd())) {
                        if (Constants.N_NO_GRADE.equals(qualification.getIbAchievedRsltGradeCd())) {
                            qualification.setAchievedTotalPoints("42");
                        } else {
                            qualification.setAchievedTotalPoints("45");
                        }
                    } else {
                        qualification.setAchievedTotalPoints("");
                    }
                    if (StringUtils.isNotBlank(qualification.getIbPredictedRsltGradeCd())) {
                        if (Constants.N_NO_GRADE.equals(qualification.getIbPredictedRsltGradeCd())) {
                            qualification.setPredictedTotalPoints("42");
                        } else {
                            qualification.setPredictedTotalPoints("45");
                        }
                    } else {
                        qualification.setPredictedTotalPoints("");

                    }
                    qualification.setIbAchievedRsltPoints(String.valueOf(ibAchievedRsltPoints));
                    qualification.setIbPredictedRsltPoints(String.valueOf(ibPredictedRsltPoints));
                    qualificationService.save(qualification);
                    break;
                }
            }
        }
    }

    public void changeGrade() {
        if (Constants.OTHERS.equals(editQualificationRslt.getPredictedGradeCd())) {
            editQualificationRslt.setIsPredictedOthers(true);
        } else {
            editQualificationRslt.setPredictedGradeOthers("");
            editQualificationRslt.setIsPredictedOthers(false);
        }
        if (Constants.OTHERS.equals(editQualificationRslt.getAchievedGradeCd())) {
            editQualificationRslt.setIsAchievedOthers(true);
        } else {
            editQualificationRslt.setAchievedGradeOthers("");
            editQualificationRslt.setIsAchievedOthers(false);
        }
    }

    public void changeExamProvince(AjaxBehaviorEvent event) {
        SelectOneMenu menu = (SelectOneMenu) event.getSource();
        String provinceId = menu != null && menu.getValue() != null ? menu.getValue().toString() : "";
        String qualificationId = (String) event.getComponent().getAttributes().get("qualificationId");
        QualificationVo qualificationVo = qualificationService.get(qualificationId);
        if (StringUtils.isNotBlank(provinceId)) {
            ProvinceVo provinceVo = provinceService.get(provinceId);
            qualificationVo.setNjceeExamProvinceDesc(provinceVo.getDescription());
            if (Constants.ZHEJIANG.equals(provinceVo.getDescription())
                    || Constants.SHANGHAI.equals(provinceVo.getDescription())) {
                qualificationVo.setNjceeStreamCd(Constants.OTHERS);
            } else {
                qualificationVo.setNjceeStreamCd("");
            }
        } else {
            qualificationVo.setNjceeExamProvinceDesc("");
            qualificationVo.setNjceeStreamCd("");
            qualificationVo.setNjceeStreamOthers("");
        }
        for (int i = 0; i < qualifications.size(); i++) {
            if (qualificationId.equals(qualifications.get(i).getId())) {
                qualificationVo.setNjceeRsltMap(qualifications.get(i).getNjceeRsltMap());
                qualifications.remove(i);
                qualifications.add(i, qualificationVo);
                break;
            }
        }
        if (StringUtils.isNotBlank(tempQualification.getNjceeExamProvinceId())) {
            promptMsg = Constants.CHANGE_PROVINCE_MSG;
            UIUtil.execute("PF('changeProvinceDialog').show()");
        }
    }

    public void changeExamStream() {
        if (StringUtils.isNotBlank(tempQualification.getNjceeStreamCd())) {
            promptMsg = Constants.CHANGE_STREAM_MSG;
            UIUtil.execute("PF('changeProvinceDialog').show()");
        }
    }

    public void changeProvinceAndStream(String provinceId, String qualificationId, String streamCd, String changeType) {
        if (Constants.CHANGE_PROVINCE.equals(changeType)) {
            if (StringUtils.isNotBlank(provinceId)) {
                if (!tempQualification.getChangeProvince()) {
                    tempQualification.setId(qualificationId);
                    tempQualification.setNjceeExamProvinceId(provinceId);
                    tempQualification.setNjceeExamProvinceDesc(provinceService.get(provinceId).getDescription());
                    tempQualification.setNjceeStreamCd(streamCd);
                    tempQualification.setChangeProvince(true);
                }
            }
        } else {
            if (StringUtils.isNotBlank(streamCd)) {
                if (!tempQualification.getChangeStream()) {
                    tempQualification.setId(qualificationId);
                    tempQualification.setNjceeExamProvinceId(provinceId);
                    tempQualification.setNjceeExamProvinceDesc(provinceService.get(provinceId).getDescription());
                    tempQualification.setNjceeStreamCd(streamCd);
                    tempQualification.setChangeStream(true);
                }
            }
        }
    }

    public void cleanSubject(String msg) {
        for (QualificationVo qualification : qualifications) {
            if (qualification.getId().equals(tempQualification.getId())) {
                if (Constants.YES.equals(msg)) {
                    Map<String, QualificationRsltVo> njceeRsltMap = qualification.getNjceeRsltMap();
                    for (Map.Entry<String, QualificationRsltVo> entry : njceeRsltMap.entrySet()) {
                        QualificationRsltVo rsltVo = entry.getValue();
                        if (StringUtils.isNotBlank(rsltVo.getId())) {
                            rsltVo.setIsDeleted(true);
                            rsltVo.setIsDeleteApproved(true);
                            qualificationRsltService.save(rsltVo);
                        }
                    }
                    qualification.setNjceeRsltMap(new HashMap<String, QualificationRsltVo>());
                }
                if (Constants.NO.equals(msg)) {
                    qualification.setNjceeExamProvinceId(tempQualification.getNjceeExamProvinceId());
                    qualification.setNjceeStreamCd(tempQualification.getNjceeStreamCd());
                    qualification.setNjceeExamProvinceDesc(tempQualification.getNjceeExamProvinceDesc());
                }
                break;
            }
        }
        tempQualification = new QualificationVo();
        UIUtil.execute("PF('changeProvinceDialog').hide()");
    }

    public void changeSubject(AjaxBehaviorEvent event) {
        SelectOneMenu menu = (SelectOneMenu) event.getSource();
        String subjectId = menu.getValue().toString();
        if (Constants.JIANGSU.equals(examTypeVo.getProvinceDesc())) {
            if (!"0".equals(subjectId)) {
                SubjectVo subjectVo = subjectService.get(subjectId);
                if (Constants.NJCEE_JS_SUBJECTS.contains(subjectVo.getExamSubjectDesc())) {
                    editQualificationRslt.setAchievedGradeOthers(null);
                    editQualificationRslt.setIsActiveAchievedGrade(true);
                } else {
                    editQualificationRslt.setAchievedGradeCd(null);
                    editQualificationRslt.setIsActiveAchievedGrade(false);
                }
            } else {
                editQualificationRslt.setAchievedGradeCd(null);
                editQualificationRslt.setAchievedGradeOthers(null);
                editQualificationRslt.setIsActiveAchievedGrade(false);
            }
        }
    }

    public String dateFormat(Date dateOfRelease) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");// 定义要输出日期字符串的
        String date = dateOfRelease != null ? sdf.format(dateOfRelease) : "";
        return date;
    }

    public void onTabChange() {
        tempQualification = new QualificationVo();
    }

    public void initNjcee() {
        editNjceeQualificationRsltId = null;
        njceeQualificationId = null;
    }

    public QualificationVo getEditQualification() {
        return editQualification;
    }

    public void setEditQualification(QualificationVo editQualification) {
        this.editQualification = editQualification;
    }

    public List<QualificationVo> getQualifications() {
        return qualifications;
    }

    public void setQualifications(List<QualificationVo> qualifications) {
        this.qualifications = qualifications;
    }

    public QualificationRsltVo getEditQualificationRslt() {
        return editQualificationRslt;
    }

    public void setEditQualificationRslt(QualificationRsltVo editQualificationRslt) {
        this.editQualificationRslt = editQualificationRslt;
    }

    public List<GradeVo> getGrades() {
        return grades;
    }

    public void setGrades(List<GradeVo> grades) {
        this.grades = grades;
    }

    public List<TypeVo> getExamTypeList() {
        return examTypeList;
    }

    public void setExamTypeList(List<TypeVo> examTypeList) {
        this.examTypeList = examTypeList;
    }

    public boolean getIsSelectLevel() {
        return isSelectLevel;
    }

    public void setIsSelectLevel(boolean isSelectLevel) {
        this.isSelectLevel = isSelectLevel;
    }

    public List<SubjectVo> getDisplaySubject() {
        return displaySubject;
    }

    public void setDisplaySubject(List<SubjectVo> displaySubject) {
        this.displaySubject = displaySubject;
    }

    public List<String> getDisplayLevel() {
        return displayLevel;
    }

    public void setDisplayLevel(List<String> displayLevel) {
        this.displayLevel = displayLevel;
    }

    public List<String> getSelectLevel() {
        return selectLevel;
    }

    public void setSelectLevel(List<String> selectLevel) {
        this.selectLevel = selectLevel;
    }

    public List<SubjectVo> getSelectSubject() {
        return selectSubject;
    }

    public void setSelectSubject(List<SubjectVo> selectSubject) {
        this.selectSubject = selectSubject;
    }

    public boolean getIsSelectSubject() {
        return isSelectSubject;
    }

    public void setIsSelectSubject(boolean isSelectSubject) {
        this.isSelectSubject = isSelectSubject;
    }

    public List<RefCdVo> getRefCds() {
        return refCds;
    }

    public void setRefCds(List<RefCdVo> refCds) {
        this.refCds = refCds;
    }

    public int getCurrPanelIndex() {
        return currPanelIndex;
    }

    public void setCurrPanelIndex(int currPanelIndex) {
        this.currPanelIndex = currPanelIndex;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public List<ProvinceVo> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<ProvinceVo> provinces) {
        this.provinces = provinces;
    }

    public List<RefCdVo> getStreamRefCds() {
        return streamRefCds;
    }

    public void setStreamRefCds(List<RefCdVo> streamRefCds) {
        this.streamRefCds = streamRefCds;
    }

    public Date getAppEndDate() {
        return appEndDate;
    }

    public void setAppEndDate(Date appEndDate) {
        this.appEndDate = appEndDate;
    }

    public MfeSchemeVo getOneSemesterOne() {
        return oneSemesterOne;
    }

    public void setOneSemesterOne(MfeSchemeVo oneSemesterOne) {
        this.oneSemesterOne = oneSemesterOne;
    }

    public MfeSchemeVo getOneSemesterTwo() {
        return oneSemesterTwo;
    }

    public void setOneSemesterTwo(MfeSchemeVo oneSemesterTwo) {
        this.oneSemesterTwo = oneSemesterTwo;
    }

    public MfeSchemeVo getTwoSemesterOne() {
        return twoSemesterOne;
    }

    public void setTwoSemesterOne(MfeSchemeVo twoSemesterOne) {
        this.twoSemesterOne = twoSemesterOne;
    }

    public MfeSchemeVo getTwoSemesterTwo() {
        return twoSemesterTwo;
    }

    public void setTwoSemesterTwo(MfeSchemeVo twoSemesterTwo) {
        this.twoSemesterTwo = twoSemesterTwo;
    }

    public MfeSchemeVo getThreeSemesterOne() {
        return threeSemesterOne;
    }

    public void setThreeSemesterOne(MfeSchemeVo threeSemesterOne) {
        this.threeSemesterOne = threeSemesterOne;
    }

    public MfeSchemeVo getThreeSemesterTwo() {
        return threeSemesterTwo;
    }

    public void setThreeSemesterTwo(MfeSchemeVo threeSemesterTwo) {
        this.threeSemesterTwo = threeSemesterTwo;
    }

    public TypeVo getExamTypeVo() {
        return examTypeVo;
    }

    public void setExamTypeVo(TypeVo examTypeVo) {
        this.examTypeVo = examTypeVo;
    }

    public List<String> getExamYears() {
        return examYears;
    }

    public void setExamYears(List<String> examYears) {
        this.examYears = examYears;
    }

    public String getPromptMsg() {
        return promptMsg;
    }

    public void setPromptMsg(String promptMsg) {
        this.promptMsg = promptMsg;
    }

    public List<RefCdVo> getEeTokGrades() {
        return eeTokGrades;
    }

    public void setEeTokGrades(List<RefCdVo> eeTokGrades) {
        this.eeTokGrades = eeTokGrades;
    }

    public List<String> getExamBoards() {
        return examBoards;
    }

    public void setExamBoards(List<String> examBoards) {
        this.examBoards = examBoards;
    }

    public List<String> getSelectBoards() {
        return selectBoards;
    }

    public void setSelectBoards(List<String> selectBoards) {
        this.selectBoards = selectBoards;
    }

    public boolean getIsSelectBoard() {
        return isSelectBoard;
    }

    public void setIsSelectBoard(boolean isSelectBoard) {
        this.isSelectBoard = isSelectBoard;
    }

    public String getEditNjceeQualificationRsltId() {
        return editNjceeQualificationRsltId;
    }

    public void setEditNjceeQualificationRsltId(String editNjceeQualificationRsltId) {
        this.editNjceeQualificationRsltId = editNjceeQualificationRsltId;
    }
}
