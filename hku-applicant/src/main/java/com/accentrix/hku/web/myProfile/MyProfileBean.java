package com.accentrix.hku.web.myProfile;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.accentrix.hku.constant.ConstantCommon;
import com.accentrix.hku.constant.Constants;
import com.accentrix.hku.service.app.InstitutionService;
import com.accentrix.hku.service.app.TelNoService;
import com.accentrix.hku.service.applicant.ApplicantInformationService;
import com.accentrix.hku.service.audit.AuditLogService;
import com.accentrix.hku.service.cpc.CityService;
import com.accentrix.hku.service.cpc.CountryService;
import com.accentrix.hku.service.cpc.ProvinceService;
import com.accentrix.hku.util.JSFUtil;
import com.accentrix.hku.util.UIUtil;
import com.accentrix.hku.util.app.AuditLogUtil;
import com.accentrix.hku.util.sys.AccountUtils;
import com.accentrix.hku.vo.app.InstitutionVo;
import com.accentrix.hku.vo.app.TelNoVo;
import com.accentrix.hku.vo.applicant.AccountVo;
import com.accentrix.hku.vo.applicant.ApplicantInformationVo;
import com.accentrix.hku.vo.audit.AuditLogVo;
import com.accentrix.hku.vo.cpc.CityVo;
import com.accentrix.hku.vo.cpc.CountryVo;
import com.accentrix.hku.vo.cpc.ProvinceVo;
import com.accentrix.hku.web.common.CommonBean;
import com.accentrix.hku.web.common.InternationalizationBean;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年4月17日 下午4:04:28
 */
@ManagedBean
@ViewScoped
@Configurable(preConstruction = true)
public class MyProfileBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(MyProfileBean.class);

    @Autowired
    private ApplicantInformationService applicantInformationService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private CityService cityService;
    @Autowired
    private TelNoService telNoService;
    @Autowired
    private InstitutionService institutionService;
    @Autowired
    private AuditLogService auditLogService;

    private ApplicantInformationVo applicantInformationVo;
    private AccountVo accountVo;
    private TelNoVo homeTel;
    private TelNoVo mobileTel;
    private List<AuditLogVo> auditLogVos;

    public MyProfileBean() {
        init();
    }

    public void init() {
        LOG.debug("myProfileBean init...");

        accountVo = AccountUtils.getAccountVo();
        retrieveData(accountVo.getUserInfoId(), accountVo.getId());
    }

    private void retrieveData(String infoId, String accountId) {
        if (StringUtils.isNotBlank(infoId)) {
            applicantInformationVo = applicantInformationService.get(infoId);

            CommonBean commonBean = JSFUtil.getCurrentInstance("commonBean", CommonBean.class);
            if (StringUtils.isNotBlank(applicantInformationVo.getNationalityCountryId())) {
                if (StringUtils.isNotBlank(applicantInformationVo.getNationalityProvinceId())) {
                    List<ProvinceVo> provinces = provinceService
                            .findByCountryId(applicantInformationVo.getNationalityCountryId());
                    commonBean.setProvinces(provinces);
                    commonBean.setIsActiveProvinceOrCity(true);
                }
                if (StringUtils.isNotBlank(applicantInformationVo.getNationalityCityId())) {
                    List<CityVo> cities = cityService
                            .findByCountryIdOrProvinceId(applicantInformationVo.getNationalityCountryId(), null);
                    commonBean.setCitys(cities);
                    commonBean.setIsActiveProvinceOrCity(false);
                }
            }
            if (StringUtils.isNotBlank(applicantInformationVo.getStudyProvinceId())) {
                List<ProvinceVo> studyProvinceList = provinceService
                        .findByCountryId(applicantInformationVo.getStudyCountryId());
                commonBean.setProvinceOfStudys(studyProvinceList);
                commonBean.setIsActiveProvince(true);
                CountryVo country = countryService.get(applicantInformationVo.getStudyCountryId());
                commonBean.setCountry(country);
            }
            if (StringUtils.isBlank(applicantInformationVo.getStudyCountryId())
                    && StringUtils.isNotBlank(applicantInformationVo.getStudyCountryOthers())) {
                applicantInformationVo.setStudyCountryId(Constants.OTHERS);
                commonBean.setIsActiveStudyCountry(true);
            }
            if (StringUtils.isNotBlank(applicantInformationVo.getStudyCityId())) {
                List<CityVo> studyCityList = cityService.findByCountryIdOrProvinceId(
                        applicantInformationVo.getStudyCountryId(), applicantInformationVo.getStudyProvinceId());
                commonBean.setCityOfStudys(studyCityList);
                commonBean.setIsActiveCity(true);
            }
            if (StringUtils.isNotBlank(applicantInformationVo.getInstitutionId())
                    && !Constants.OTHERS.equals(applicantInformationVo.getInstitutionId())) {
                List<InstitutionVo> institutions = institutionService.findInstitutions(
                        applicantInformationVo.getStudyCountryId(), null, applicantInformationVo.getStudyCityId());
                commonBean.setInstitutions(institutions);
            }
            if (Constants.OTHERS.equals(applicantInformationVo.getInstitutionId())) {
                commonBean.setIsActiveStudyInstitution(true);
            }
        } else {
            applicantInformationVo = new ApplicantInformationVo();
        }

        if (StringUtils.isNotBlank(applicantInformationVo.getHomeTelId())) {
            homeTel = telNoService.get(applicantInformationVo.getHomeTelId());
        } else {
            homeTel = new TelNoVo();
        }

        if (StringUtils.isNotBlank(applicantInformationVo.getMobileTelId())) {
            mobileTel = telNoService.get(applicantInformationVo.getMobileTelId());
        } else {
            mobileTel = new TelNoVo();
        }

        if (StringUtils.isNotBlank(accountId)) {
            auditLogVos = auditLogService.getByApplicantAccountId(accountId);
        } else {
            auditLogVos = new ArrayList<AuditLogVo>();
        }
        for (AuditLogVo auditLogVo : auditLogVos) {
            upDateFormat(auditLogVo);
        }
    }

    public void save() {
        if (!validateContent()) {
            UIUtil.displayErrorDialog(InternationalizationBean.locale, ConstantCommon.MADATORY_FIELD_MISSING,
                    ConstantCommon.MADATORY_FIELD_MISSING_CHI);
        } else {
            // save
            homeTel = telNoService.save(homeTel);
            mobileTel = telNoService.save(mobileTel);

            applicantInformationVo.setHomeTelId(homeTel.getId());
            applicantInformationVo.setMobileTelId(mobileTel.getId());
            applicantInformationService.save(applicantInformationVo);

            AuditLogUtil.saveAuditLog(accountVo.getId(), Constants.SAVED_MY_PROFILE);
            auditLogVos = auditLogService.getByApplicantAccountId(accountVo.getId());
            for (AuditLogVo auditLogVo : auditLogVos) {
                upDateFormat(auditLogVo);
            }

            homeTel = telNoService.get(homeTel.getId());
            mobileTel = telNoService.get(mobileTel.getId());
            UIUtil.displaySaveSuccessDialog(InternationalizationBean.locale);
        }
    }

    public boolean validateContent() {
        boolean valid = true;

        if (StringUtils.isNotEmpty(applicantInformationVo.getAlternateEmail())) {
            String alternateEmailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            if (!applicantInformationVo.getAlternateEmail().matches(alternateEmailRegex)) {
                UIUtil.setInvalid(":infoTabView:appInfoForm:alterEmailAddr");
                UIUtil.displayErrorDialog(InternationalizationBean.locale, Constants.ACCOUNT_EMAIL_FORMAT,
                        Constants.ACCOUNT_EMAIL_FORMAT_CHI);
                valid = false;
            }
        } else {
            UIUtil.setInvalid(":infoTabView:appInfoForm:alterEmailAddr");
            valid = false;
        }

        if (StringUtils.isNotEmpty(applicantInformationVo.getGivenName())
                || StringUtils.isNotEmpty(applicantInformationVo.getSurname())) {
            String nameRegex = "^[a-zA-Z]+$";
            if (!applicantInformationVo.getGivenName().matches(nameRegex)
                    || !applicantInformationVo.getSurname().matches(nameRegex)) {
                if (!applicantInformationVo.getGivenName().matches(nameRegex)) {
                    UIUtil.setInvalid(":infoTabView:appInfoForm:givenName");
                }
                if (!applicantInformationVo.getSurname().matches(nameRegex)) {
                    UIUtil.setInvalid(":infoTabView:appInfoForm:surname");
                }
                UIUtil.displayErrorDialog(InternationalizationBean.locale, Constants.REGISTRATION_TEXT_LANGUAGE,
                        Constants.REGISTRATION_TEXT_LANGUAGE_CHI);
                valid = false;
            }
        } else {
            if (StringUtils.isEmpty(applicantInformationVo.getGivenName())) {
                UIUtil.setInvalid(":infoTabView:appInfoForm:givenName");
            }
            if (StringUtils.isEmpty(applicantInformationVo.getSurname())) {
                UIUtil.setInvalid(":infoTabView:appInfoForm:surname");
            }
            valid = false;
        }

        if (applicantInformationVo.getDateOfBirth() == null) {
            UIUtil.setInvalid(":infoTabView:appInfoForm:dateOfBirth");
            valid = false;
        }

        if (applicantInformationVo.getSex() == '\0') {
            UIUtil.setInvalid(":infoTabView:appInfoForm:sex");
            valid = false;
        }

        if (StringUtils.isNotBlank(applicantInformationVo.getNationalityCountryId())) {
            CountryVo vo = countryService.get(applicantInformationVo.getNationalityCountryId());
            if (vo != null) {
                if (vo.getCode().equals("CHN")) {
                    if (StringUtils.isEmpty(applicantInformationVo.getNationalityProvinceId())) {
                        UIUtil.setInvalid(":infoTabView:appInfoForm:provinceOfNationality");
                        valid = false;
                    }
                } else {
                    if (StringUtils.isEmpty(applicantInformationVo.getNationalityCityId())) {
                        UIUtil.setInvalid(":infoTabView:appInfoForm:cityOfNationality");
                        valid = false;
                    }
                }
            }
        } else {
            UIUtil.setInvalid(":infoTabView:appInfoForm:countryOfNationality");
            valid = false;
        }

        if (StringUtils.isEmpty(applicantInformationVo.getNationalityCityId())
                && StringUtils.isEmpty(applicantInformationVo.getNationalityProvinceId())) {
            UIUtil.setInvalid(":infoTabView:appInfoForm:cityOfNationality");
            UIUtil.setInvalid(":infoTabView:appInfoForm:provinceOfNationality");
            valid = false;
        }

        if (StringUtils.isEmpty(applicantInformationVo.getResidenceCountryId())) {
            UIUtil.setInvalid(":infoTabView:appInfoForm:currCountryOfResidence");
            valid = false;
        }

        if (!applicantInformationVo.getIsNotStuding()) {
            if (StringUtils.isEmpty(applicantInformationVo.getStudyCountryId())) {
                UIUtil.setInvalid(":infoTabView:appInfoForm:currCountryOfStudy");
                valid = false;
            } else {
                if (applicantInformationVo.getStudyCountryId().equals(Constants.OTHERS)) {
                    UIUtil.setInvalid(":infoTabView:appInfoForm:currCountryOfStudyInputText");
                    valid = false;
                } else {
                    CountryVo vo = countryService.get(applicantInformationVo.getStudyCountryId());
                    if (vo != null) {
                        if (vo.getCode().equals("CHN")) {
                            if (StringUtils.isEmpty(applicantInformationVo.getStudyProvinceId())) {
                                UIUtil.setInvalid(":infoTabView:appInfoForm:province");
                                valid = false;
                            }
                        }
                    }
                    if (StringUtils.isEmpty(applicantInformationVo.getStudyCityId())) {
                        UIUtil.setInvalid(":infoTabView:appInfoForm:city");
                        valid = false;
                    }
                }
            }

            if (StringUtils.isEmpty(applicantInformationVo.getInstitutionId())) {
                UIUtil.setInvalid(":infoTabView:appInfoForm:nameOfInstitution");
                valid = false;
            } else {
                if (applicantInformationVo.getInstitutionId().equals(Constants.OTHERS)) {
                    if (StringUtils.isEmpty(applicantInformationVo.getInstitutionOthers())) {
                        UIUtil.setInvalid(":infoTabView:appInfoForm:nameOfInstitutionInputText");
                        valid = false;
                    }
                }
            }
        }

        if (StringUtils.isEmpty(homeTel.getNumber())) {
            UIUtil.setInvalid(":infoTabView:appInfoForm:homeTelNo");
            valid = false;
        }

        if (StringUtils.isEmpty(mobileTel.getNumber())) {
            UIUtil.setInvalid(":infoTabView:appInfoForm:mobilePhoneNo");
            valid = false;
        }

        if (StringUtils.isEmpty(applicantInformationVo.getCorrespondenceAddr())) {
            UIUtil.setInvalid(":infoTabView:appInfoForm:correspondenceAddr");
            valid = false;
        }
        return valid;
    }

    public void changeArea() {
        if (StringUtils.isBlank(applicantInformationVo.getNationalityCountryId())) {
            applicantInformationVo.setNationalityProvinceId("");
            applicantInformationVo.setNationalityCityId("");
        } else {
            CountryVo c = countryService.get(applicantInformationVo.getNationalityCountryId());
            if (c.getCode().equals("CHN")) {
                applicantInformationVo.setNationalityCityId("");
            } else {
                applicantInformationVo.setNationalityProvinceId("");
            }
        }
        if (StringUtils.isBlank(applicantInformationVo.getStudyCountryId())
                || applicantInformationVo.getStudyCountryId().equals("others")) {
            applicantInformationVo.setStudyProvinceId("");
            applicantInformationVo.setStudyCityId("");
            applicantInformationVo.setInstitutionId("");
            applicantInformationVo.setInstitutionOthers("");
        }
        if (StringUtils.isBlank(applicantInformationVo.getStudyProvinceId())) {
            if (StringUtils.isNotBlank(applicantInformationVo.getStudyCountryId())
                    && !applicantInformationVo.getStudyCountryId().equals("others")) {
                CountryVo countryVo = countryService.get(applicantInformationVo.getStudyCountryId());
                if (countryVo != null && countryVo.getCode().equals("CHN")) {
                    applicantInformationVo.setStudyCityId("");
                    applicantInformationVo.setInstitutionId("");
                    applicantInformationVo.setInstitutionOthers("");
                }
            }
        }
        if (StringUtils.isNotBlank(applicantInformationVo.getStudyCountryId())
                && !applicantInformationVo.getStudyCountryId().equals("others")) {
            CountryVo countryVo = countryService.get(applicantInformationVo.getStudyCountryId());
            if (countryVo != null && !countryVo.getCode().equals("CHN")) {
                applicantInformationVo.setStudyProvinceId("");
            }
        }
        if (StringUtils.isBlank(applicantInformationVo.getStudyCityId())) {
            applicantInformationVo.setInstitutionId("");
            applicantInformationVo.setInstitutionOthers("");
        }
        if (StringUtils.isBlank(applicantInformationVo.getInstitutionId())) {
            applicantInformationVo.setInstitutionOthers("");
        }
        if (StringUtils.isNotBlank(applicantInformationVo.getInstitutionId())
                && !Constants.OTHERS.equals(applicantInformationVo.getInstitutionId())) {
            applicantInformationVo.setInstitutionOthers("");
        }
    }

    public void upDateFormat(AuditLogVo auditLogVo) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");// 定义要输出日期字符串的
        auditLogVo.setLastDate(sdf.format(auditLogVo.getUpdateDate()));
    }

    public ApplicantInformationVo getApplicantInformationVo() {
        return applicantInformationVo;
    }

    public void setApplicantInformationVo(ApplicantInformationVo applicantInformationVo) {
        this.applicantInformationVo = applicantInformationVo;
    }

    public AccountVo getAccountVo() {
        return accountVo;
    }

    public void setAccountVo(AccountVo accountVo) {
        this.accountVo = accountVo;
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

    public List<AuditLogVo> getAuditLogVos() {
        return auditLogVos;
    }

    public void setAuditLogVos(List<AuditLogVo> auditLogVos) {
        this.auditLogVos = auditLogVos;
    }
}
