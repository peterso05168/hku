package com.accentrix.hku.web.myApplication;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.accentrix.hku.constant.ConstantCommon;
import com.accentrix.hku.constant.Constants;
import com.accentrix.hku.service.adm.ExeService;
import com.accentrix.hku.service.adm.FormService;
import com.accentrix.hku.service.app.AcadBgService;
import com.accentrix.hku.service.app.PersonalParticularsService;
import com.accentrix.hku.service.app.ProgressService;
import com.accentrix.hku.service.app.ReqDocConfService;
import com.accentrix.hku.service.app.ReqDocService;
import com.accentrix.hku.service.app.TelNoService;
import com.accentrix.hku.service.app.UploadedDocService;
import com.accentrix.hku.service.applicant.ApplicationService;
import com.accentrix.hku.service.cpc.CityService;
import com.accentrix.hku.service.cpc.CountryService;
import com.accentrix.hku.service.cpc.ProvinceService;
import com.accentrix.hku.util.JSFUtil;
import com.accentrix.hku.util.UIUtil;
import com.accentrix.hku.util.app.AuditLogUtil;
import com.accentrix.hku.util.sys.AccountUtils;
import com.accentrix.hku.vo.adm.ExeVo;
import com.accentrix.hku.vo.adm.FormVo;
import com.accentrix.hku.vo.app.AcadBgVo;
import com.accentrix.hku.vo.app.PersonalParticularsVo;
import com.accentrix.hku.vo.app.ProgressVo;
import com.accentrix.hku.vo.app.ReqDocConfVo;
import com.accentrix.hku.vo.app.ReqDocVo;
import com.accentrix.hku.vo.app.TelNoVo;
import com.accentrix.hku.vo.app.UploadedDocVo;
import com.accentrix.hku.vo.applicant.AccountVo;
import com.accentrix.hku.vo.applicant.ApplicationVo;
import com.accentrix.hku.vo.cpc.CityVo;
import com.accentrix.hku.vo.cpc.CountryVo;
import com.accentrix.hku.vo.cpc.ProvinceVo;
import com.accentrix.hku.web.common.CommonBean;
import com.accentrix.hku.web.common.InternationalizationBean;

/**
 * @author 作者 lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年2月5日 上午10:57:59
 */
@ManagedBean
@ViewScoped
@Configurable(preConstruction = true)
public class PersonalParticularsBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(PersonalParticularsBean.class);

    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private PersonalParticularsService personalParticularsService;
    @Autowired
    private TelNoService telNoService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private CityService cityService;
    @Autowired
    private ProgressService progressService;
    @Autowired
    private FormService formService;
    @Autowired
    private ExeService exeService;
    @Autowired
    private ReqDocConfService reqDocConfService;
    @Autowired
    private ReqDocService reqDocService;
    @Autowired
    private UploadedDocService uploadedDocService;
    @Autowired
    private AcadBgService acadBgService;

    private PersonalParticularsVo personalParticulars;
    private TelNoVo homeTel;
    private TelNoVo mobileTel;
    private String applicationId;
    private String initResidenceCountryId;
    private String birthFormat;
    private Integer admissionYear;
    private String initNationalityCountryId;

    public PersonalParticularsBean() {
        init();
    }

    public void init() {
        LOG.debug("init ...");
        birthFormat = Constants.BIRTH_WRONG;
        initResidenceCountryId = "";
        personalParticulars = new PersonalParticularsVo();
        applicationId = (String) JSFUtil.getSessionMap().get("applicationId");
        admissionYear = (Integer) JSFUtil.getSessionMap().get("admissionYear");
        retrieveData(applicationId);
    }

    /**
     * 
     * @param applicationId
     */
    @SuppressWarnings("deprecation")
    private void retrieveData(String applicationId) {
        ApplicationVo applicationVo = null;
        if (StringUtils.isNotBlank(applicationId)) {
            applicationVo = applicationService.get(applicationId);
        }

        String personalParticularId = "";
        if (applicationVo != null) {
            personalParticularId = applicationVo.getPersonId();
        }
        if (StringUtils.isNotBlank(personalParticularId)) {
            personalParticulars = personalParticularsService.get(personalParticularId);

            if (StringUtils.isNotBlank(personalParticulars.getHomeTelNoId())) {
                homeTel = telNoService.get(personalParticulars.getHomeTelNoId());
            } else {
                homeTel = new TelNoVo();
            }

            if (StringUtils.isNotBlank(personalParticulars.getMobileTelNoId())) {
                mobileTel = telNoService.get(personalParticulars.getMobileTelNoId());
            } else {
                mobileTel = new TelNoVo();
            }

            if (personalParticulars.getDateOfBirth().getYear() == 0) {
                personalParticulars.setDateOfBirth(null);
            }

            initResidenceCountryId = personalParticulars.getResidenceCountryId();
            if (StringUtils.isNotBlank(personalParticulars.getResidenceCountryId())) {
                CommonBean commonBean = JSFUtil.getCurrentInstance("commonBean", CommonBean.class);
                CountryVo countryVo = countryService.get(personalParticulars.getResidenceCountryId());
                commonBean.setCountry(countryVo);
                if (countryVo.getCode().equals("CHN")) {
                    List<ProvinceVo> provinces = provinceService
                            .findByCountryId(personalParticulars.getResidenceCountryId());
                    commonBean.setProvinces(provinces);
                    commonBean.setIsActiveProvinceOrCity(true);
                } else {
                    List<CityVo> cities = cityService
                            .findByCountryIdOrProvinceId(personalParticulars.getResidenceCountryId(), null);
                    commonBean.setCitys(cities);
                    commonBean.setIsActiveProvinceOrCity(false);
                }
            }

            initNationalityCountryId = personalParticulars.getNationalityCountryId();
        } else {
            // FIXME: HANDLE IT LATER, SHOULD THROW EXCEPTION
            personalParticulars = new PersonalParticularsVo();
            homeTel = new TelNoVo();
            mobileTel = new TelNoVo();
        }
    }

    public void saveNextPage(String index) {
        if (save()) {
            processDocumentRequirements();
            ApplicationBean applicationBean = JSFUtil.getCurrentInstance("applicationBean", ApplicationBean.class);
            applicationBean.nextPage(index);
        }
    }

    private void processDocumentRequirements() {
        ApplicationVo applicationVo = applicationService.get(applicationId);
        if (initNationalityCountryId != null
                && !initNationalityCountryId.equals(personalParticulars.getNationalityCountryId())) {
            CountryVo countryVo = countryService.get(initNationalityCountryId);
            if (Constants.HE_FOR_SHE_COUNTRYS.contains(countryVo.getDescription())) {
                applicationVo.setScholarHeforshe(false);
                applicationService.save(applicationVo);
                deleteDocumentRequirement(Constants.DOC_TYPE_OTHERS, "SupplementaryEssayMale",
                        "HeForShe – Supplementary Essay: How you have contributed to the goals of the HeForShe movement",
                        applicationId);
                deleteDocumentRequirement(Constants.DOC_TYPE_OTHERS, "SupplementaryEssayFemale",
                        "HeForShe – Supplementary Essay: How gender disparity impacts your life?", applicationId);
                deleteDocumentRequirement(Constants.DOC_TYPE_OTHERS, "AcademicTranscriptForScholarHeforShe",
                        "HeForShe – Academic transcript of 2 previous years of study", applicationId);
            }
            if (Constants.AFL_COUNTRYS.contains(countryVo.getDescription())) {
                applicationVo.setScholarAfl(false);
                applicationService.save(applicationVo);
                deleteDocumentRequirement(Constants.DOC_TYPE_OTHERS, "ScholarAFL", "Asian Future Leaders Scholarship",
                        applicationId);
            }
            if (Constants.HKSAR_GSFT_COUNTRYS.contains(countryVo.getDescription())) {
                applicationVo.setScholarHksarGsft(false);
                applicationService.save(applicationVo);
                deleteDocumentRequirement(Constants.DOC_TYPE_OTHERS, "ScholarHksarGsft",
                        "HKSAR Government Scholarship Fund Targeted Scholarship", applicationId);
            }
            if (Constants.NIGERIA.equals(countryVo.getDescription())) {
                applicationVo.setScholarNigerian(false);
                applicationService.save(applicationVo);
                deleteDocumentRequirement(Constants.DOC_TYPE_OTHERS, "ScholarNigerian", "Nigerian Scholarship",
                        applicationId);
            }
            if (Constants.VIET_NAM.equals(countryVo.getDescription())) {
                applicationVo.setScholarVtp(false);
                applicationService.save(applicationVo);
                deleteDocumentRequirement(Constants.DOC_TYPE_OTHERS, "HRTR",
                        "VTP – Household Registry and Temporary Residence Record", applicationId);
                deleteDocumentRequirement(Constants.DOC_TYPE_OTHERS, "HST",
                        "VTP – High School Transcript of Year 10-12", applicationId);
                AcadBgVo acadBgVo = acadBgService.get(applicationVo.getAcadBgId());
                if (!Constants.NOT_POST_SECONDARY_STUDIES_QUALIFICATION.contains(acadBgVo.getHighestQualificationCd()))
                    deleteDocumentRequirement(Constants.DOC_TYPE_OTHERS, "PVUEER",
                            "VTP – Past Vietnamese University Entrance Exam Results", applicationId);
            }
            if (Constants.HKSAR_GSFT_BR_COUNTRYS.contains(countryVo.getDescription())) {
                applicationVo.setScholarHksarGsftBrs(false);
                ;
                applicationService.save(applicationVo);
                deleteDocumentRequirement(Constants.DOC_TYPE_OTHERS, "ScholarHksarGsftBrs",
                        "HKSAR Government Scholarship Fund Targeted Scholarship – Belt and Road Scholarships",
                        applicationId);
            }
        }
        if (applicationVo.getScholarHeforshe() != null && applicationVo.getScholarHeforshe()) {
            if ("m".equals(personalParticulars.getSex())) {
                deleteDocumentRequirement(Constants.DOC_TYPE_OTHERS, "SupplementaryEssayFemale",
                        "Supplementary Essay: How gender disparity impacts your life?", applicationId);
                addDocumentRequirement(Constants.DOC_TYPE_OTHERS, "SupplementaryEssayMale",
                        "Supplementary Essay: How you have contributed to the goals of the HeForShe movement",
                        applicationId);
            } else if ("f".equals(personalParticulars.getSex())) {
                deleteDocumentRequirement(Constants.DOC_TYPE_OTHERS, "SupplementaryEssayMale",
                        "Supplementary Essay: How you have contributed to the goals of the HeForShe movement",
                        applicationId);
                addDocumentRequirement(Constants.DOC_TYPE_OTHERS, "SupplementaryEssayFemale",
                        "Supplementary Essay: How gender disparity impacts your life?", applicationId);
            }
        }
        if (personalParticulars.getReqVisaInd())
            addDocumentRequirement(Constants.DOC_TYPE_PIS, Constants.DOC_CD_VISA, "Visa / entry permit", applicationId);
        else {
            deleteDocumentRequirement(Constants.DOC_TYPE_PIS, Constants.DOC_CD_VISA, "Visa / entry permit",
                    applicationId);
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

    public boolean save() {
        if (!validateContent()) {
            return false;
        }
        try {
            if (StringUtils.isNotBlank(homeTel.getNumber())) {
                homeTel = telNoService.save(homeTel);
                personalParticulars.setHomeTelNoId(homeTel.getId());
            } else {
                personalParticulars.setHomeTelNoId(null);
            }
            mobileTel = telNoService.save(mobileTel);
            personalParticulars.setMobileTelNoId(mobileTel.getId());

            CommonBean commonBean = JSFUtil.getCurrentInstance("commonBean", CommonBean.class);
            if (!commonBean.getIsActiveProvinceOrCity()) {
                personalParticulars.setResidenceProvinceId(null);
            } else {
                personalParticulars.setResidenceCityId(null);
            }
            if (personalParticulars.getNotProvideId()) {
                personalParticulars.setNationalIdCard(null);
                personalParticulars.setPassportNo(null);
                personalParticulars.setHkid(null);
            }
            CountryVo countryVo = countryService.get(personalParticulars.getNationalityCountryId());
            if (StringUtils.isNotBlank(personalParticulars.getHkid())) {
                personalParticulars.setRegion("L");
            } else {
                if (Constants.CHINA.equals(countryVo.getDescription())) {
                    personalParticulars.setRegion("N");
                } else {
                    personalParticulars.setRegion("O");
                }
            }
            personalParticulars = personalParticularsService.save(personalParticulars);
            if (personalParticulars != null) {
                if (StringUtils.isNotBlank(applicationId)) {
                    ProgressVo progress = progressService.findByApplicationId(applicationId);
                    progress.setPrsnalPart(true);
                    progressService.save(progress);
                }
            }
            AccountVo accountVo = AccountUtils.getAccountVo();
            AuditLogUtil.saveAuditLog(accountVo.getId(), Constants.SAVED_PERSONAL_PARTICULARS);
        } catch (Exception e) {
            // TODO: handle exception
        }
        UIUtil.displaySaveSuccessDialog(InternationalizationBean.locale);
        return true;
    }

    @SuppressWarnings("deprecation")
    public boolean validateContent() {
        boolean valid = true;
        boolean wrongDateFormat = false;

        if (StringUtils.isBlank(personalParticulars.getSurname())) {
            UIUtil.setInvalid(":mainTab:formPersonal:familyName");
            valid = false;
        }
        if (StringUtils.isBlank(personalParticulars.getGivenName())) {
            UIUtil.setInvalid(":mainTab:formPersonal:givenName");
            valid = false;
        }
        if (personalParticulars.getDateOfBirth() == null) {
            UIUtil.setInvalid(":mainTab:formPersonal:dateOfBirth");
            valid = false;
        } else {
            if (personalParticulars.getDateOfBirth().getYear() <= 0) {
                wrongDateFormat = true;
                UIUtil.setInvalid(":mainTab:formPersonal:dateOfBirth");
            }
            if (personalParticulars.getDateOfBirth().getTime() > new Date().getTime()) {
                wrongDateFormat = true;
                UIUtil.setInvalid(":mainTab:formPersonal:dateOfBirth");
            }
        }
        if (StringUtils.isBlank(personalParticulars.getNationalityCountryId())) {
            UIUtil.setInvalid(":mainTab:formPersonal:countryOfNationality");
            valid = false;
        }
        if (StringUtils.isBlank(personalParticulars.getSex())) {
            UIUtil.setInvalid(":mainTab:formPersonal:personal_sex");
            valid = false;
        }
        if ("0".equals(personalParticulars.getResidenceCountryId())) {
            UIUtil.setInvalid(":mainTab:formPersonal:countryOfResidence");
            valid = false;
        }
        if (StringUtils.isBlank(personalParticulars.getResidenceProvinceId())
                && StringUtils.isBlank(personalParticulars.getResidenceCityId())) {
            UIUtil.setInvalid(":mainTab:formPersonal:cityOfResidence");
            UIUtil.setInvalid(":mainTab:formPersonal:provinceOfResidence");
            valid = false;
        }
        if (StringUtils.isBlank(mobileTel.getNumber())) {
            UIUtil.setInvalid(":mainTab:formPersonal:mobilePhoneNo");
            valid = false;
        }
        if ((StringUtils.isNotBlank(homeTel.getCountryCd()) || StringUtils.isNotBlank(homeTel.getAreaCd()))
                && StringUtils.isBlank(homeTel.getNumber())) {
            UIUtil.setInvalid(":mainTab:formPersonal:homeTelNo");
            valid = false;
        }
        if (StringUtils.isBlank(personalParticulars.getCorrespondenceAddr())) {
            UIUtil.setInvalid(":mainTab:formPersonal:correspondenceAddress");
            valid = false;
        }
        if (StringUtils.isBlank(personalParticulars.getEmail())) {
            UIUtil.setInvalid(":mainTab:formPersonal:alternateEmail");
            valid = false;
        } else {
            String personEmailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            if (!personalParticulars.getEmail().matches(personEmailRegex)) {
                UIUtil.displayErrorDialog(InternationalizationBean.locale, Constants.ACCOUNT_EMAIL_FORMAT,
                        Constants.ACCOUNT_EMAIL_FORMAT_CHI);
                UIUtil.setInvalid(":mainTab:formPersonal:alternateEmail");
                valid = false;
            }
        }
        if (!personalParticulars.getNotProvideId()) {
            if (StringUtils.isBlank(personalParticulars.getNationalIdCard())
                    && StringUtils.isBlank(personalParticulars.getPassportNo())
                    && StringUtils.isBlank(personalParticulars.getHkid())) {
                UIUtil.setInvalid(":mainTab:formPersonal:nationalIdCardInput");
                UIUtil.setInvalid(":mainTab:formPersonal:passportNoInput");
                UIUtil.setInvalid(":mainTab:formPersonal:hkidInput");
                UIUtil.displayErrorDialog(InternationalizationBean.locale, Constants.CARD_NO_FORMAT,
                        Constants.CARD_NO_FORMAT_CHI);
                valid = false;
            }
            if (StringUtils.isNotBlank(personalParticulars.getNationalIdCard())) {
                String cardNo = "^[1-9][0-9]{5}(19|20)[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|31)|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}([0-9]|x|X)$";
                if (!personalParticulars.getNationalIdCard().matches(cardNo)) {
                    UIUtil.displayErrorDialog(InternationalizationBean.locale, Constants.CHINA_CARD_NO_FORMAT,
                            Constants.CHINA_CARD_NO_FORMAT_CHI);
                    UIUtil.setInvalid(":mainTab:formPersonal:nationalIdCardInput");
                    valid = false;
                }
            }
        }
        if (!valid && wrongDateFormat) {
            UIUtil.displayErrorDialog(InternationalizationBean.locale, ConstantCommon.MADATORY_FIELD_MISSING,
                    ConstantCommon.MADATORY_FIELD_MISSING_CHI);
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
        } else {
            return true;
        }
    }

    public void setDateOfBirth(Date dateOfBirth) {
        if (null != dateOfBirth) {
            personalParticulars.setDateOfBirth(dateOfBirth);
        }
    }

    public void changeCountryOfResidence() {
        if (personalParticulars.getResidenceCountryId().equals(initResidenceCountryId)) {
            personalParticulars.setResidenceCityId("");
            personalParticulars.setResidenceProvinceId("");
        }
        if ("0".equals(personalParticulars.getResidenceCountryId())
                || StringUtils.isBlank(personalParticulars.getResidenceCountryId())) {
            personalParticulars.setResidenceCityId("");
            personalParticulars.setResidenceProvinceId("");
        }
    }

    public String getCountryOfResidence() {
        if (!"0".equals(personalParticulars.getResidenceCountryId())
                && StringUtils.isNotBlank(personalParticulars.getResidenceCountryId())) {
            return countryService.get(personalParticulars.getResidenceCountryId()).getDescription();
        } else {
            return "";
        }
    }

    public String getProvinceOrCity() {
        CommonBean commonBean = JSFUtil.getCurrentInstance("commonBean", CommonBean.class);
        if (commonBean.getIsActiveProvinceOrCity()
                && StringUtils.isNotBlank(personalParticulars.getResidenceProvinceId())) {
            return provinceService.get(personalParticulars.getResidenceProvinceId()).getDescription();
        } else if (!commonBean.getIsActiveProvinceOrCity()
                && StringUtils.isNotBlank(personalParticulars.getResidenceCityId())) {
            return cityService.get(personalParticulars.getResidenceCityId()).getDescription();
        } else {
            return "";
        }
    }

    public PersonalParticularsVo getPersonalParticulars() {
        return personalParticulars;
    }

    public void setPersonalParticulars(PersonalParticularsVo personalParticulars) {
        this.personalParticulars = personalParticulars;
    }

    public TelNoVo getHomeTel() {
        return homeTel;
    }

    public void setHomeTel(TelNoVo homeTel) {
        this.homeTel = homeTel;
    }

    public TelNoVo getMobileTel() {
        return mobileTel;
    }

    public void setMobileTel(TelNoVo mobileTel) {
        this.mobileTel = mobileTel;
    }

    public String getBirthFormat() {
        return birthFormat;
    }

    public void setBirthFormat(String birthFormat) {
        this.birthFormat = birthFormat;
    }

    public Integer getAdmissionYear() {
        return admissionYear;
    }

    public void setAdmissionYear(Integer admissionYear) {
        this.admissionYear = admissionYear;
    }
}
