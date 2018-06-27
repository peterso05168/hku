package com.accentrix.hku.web.management.application;

import java.io.Serializable;
import java.util.Date;
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
import com.accentrix.hku.service.app.PersonalParticularsService;
import com.accentrix.hku.service.app.ProgressService;
import com.accentrix.hku.service.app.TelNoService;
import com.accentrix.hku.service.applicant.ApplicationService;
import com.accentrix.hku.service.cpc.CityService;
import com.accentrix.hku.service.cpc.CountryService;
import com.accentrix.hku.service.cpc.ProvinceService;
import com.accentrix.hku.util.JSFUtil;
import com.accentrix.hku.util.UIUtil;
import com.accentrix.hku.vo.app.PersonalParticularsVo;
import com.accentrix.hku.vo.app.ProgressVo;
import com.accentrix.hku.vo.app.TelNoVo;
import com.accentrix.hku.vo.applicant.ApplicationVo;
import com.accentrix.hku.vo.cpc.CityVo;
import com.accentrix.hku.vo.cpc.CountryVo;
import com.accentrix.hku.vo.cpc.ProvinceVo;
import com.accentrix.hku.web.common.CommonBean;
import com.accentrix.hku.web.common.InternationalizationBean;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年5月30日 下午2:27:11
 */
@ManagedBean
@ViewScoped
@Configurable(preConstruction = true)
public class EditPersonalParticularsBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(EditPersonalParticularsBean.class);

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

    private PersonalParticularsVo personalParticulars;
    private TelNoVo homeTel;
    private TelNoVo mobileTel;
    private String applicationId;
    private String initResidenceCountryId;
    private String birthFormat;
    private Integer admissionYear;

    public EditPersonalParticularsBean() {
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
        } else {
            // FIXME: HANDLE IT LATER, SHOULD THROW EXCEPTION
            personalParticulars = new PersonalParticularsVo();
            homeTel = new TelNoVo();
            mobileTel = new TelNoVo();
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
            personalParticulars = personalParticularsService.save(personalParticulars);
            if (personalParticulars != null) {
                if (StringUtils.isNotBlank(applicationId)) {
                    ProgressVo progress = progressService.findByApplicationId(applicationId);
                    progress.setPrsnalPart(true);
                    progressService.save(progress);
                }
            }
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
