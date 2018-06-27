package com.accentrix.hku.web.index;

import java.io.Serializable;
import java.util.Date;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.accentrix.hku.constant.Constants;
import com.accentrix.hku.service.app.TelNoService;
import com.accentrix.hku.service.applicant.AccountService;
import com.accentrix.hku.service.applicant.ApplicantInformationService;
import com.accentrix.hku.service.cpc.CountryService;
import com.accentrix.hku.service.email.MailSenderService;
import com.accentrix.hku.util.ConstantsUtils;
import com.accentrix.hku.util.UIUtil;
import com.accentrix.hku.util.UserUtils;
import com.accentrix.hku.vo.app.TelNoVo;
import com.accentrix.hku.vo.applicant.AccountVo;
import com.accentrix.hku.vo.applicant.ApplicantInformationVo;
import com.accentrix.hku.vo.cpc.CountryVo;
import com.accentrix.hku.vo.email.MailInfo;
import com.accentrix.hku.web.common.InternationalizationBean;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月26日 下午2:18:02
 * @version 1.0
 */

@ManagedBean
@ViewScoped
@Configurable(preConstruction = true)
public class RegistrationBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(RegistrationBean.class);

    @Autowired
    private ApplicantInformationService applicantInformationService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private TelNoService telNoService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private MailSenderService mailSenderService;

    private ApplicantInformationVo applicantInformation;
    private AccountVo account;
    private TelNoVo homeTel;
    private TelNoVo mobileTel;

    public RegistrationBean() {
        LOG.info("registration loading...");
        applicantInformation = new ApplicantInformationVo();
        account = new AccountVo();
        homeTel = new TelNoVo();
        mobileTel = new TelNoVo();
    }

    public String save() {
        if (!validateContent()) {
            UIUtil.displayManFieldMissingDialog(InternationalizationBean.locale);
            return null;
        }
        if (StringUtils.isNotBlank(homeTel.getNumber())) {
            homeTel.setCreateBy(account.getPersonEmail());
            homeTel.setUpdateBy(account.getPersonEmail());
            homeTel.setCreateDate(new Date());
            homeTel.setUpdateDate(new Date());
            homeTel = telNoService.save(homeTel);
            applicantInformation.setHomeTelId(homeTel.getId());
        }
        if (StringUtils.isNotBlank(mobileTel.getNumber())) {
            mobileTel.setCreateBy(account.getPersonEmail());
            mobileTel.setUpdateBy(account.getPersonEmail());
            mobileTel.setCreateDate(new Date());
            mobileTel.setUpdateDate(new Date());
            mobileTel = telNoService.save(mobileTel);
            applicantInformation.setMobileTelId(mobileTel.getId());
        }

        applicantInformation = applicantInformationService.save(applicantInformation);
        account.setUserInfoId(applicantInformation.getId());
        account.setRegDate(new Date());
        account = accountService.save(account);

        try {
            MailInfo mailInfo = new MailInfo();
            mailInfo.setNotifyTo(account.getPersonEmail());
            mailInfo.setNotifyCc(account.getPersonEmail());
            String emailSubject = "";
            String emailContent = "";
            if (InternationalizationBean.locale.equals(Locale.ENGLISH)) {
                emailSubject = Constants.ACTIVATE_ACC;
                emailContent = Constants.ACTIVATE_ACC_CONT;
            } else {
                emailSubject = Constants.ACTIVATE_ACC_CHI;
                emailContent = Constants.ACTIVATE_ACC_CONT_CHI;
            }
            mailInfo.setSubject(emailSubject);
            mailInfo.setContent(emailContent + "<br/>" + ConstantsUtils.SERVER_URL
                    + FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath()
                    + "/hku/index/login.xhtml?id=" + UserUtils.transcodingId(account.getId()));
            mailSenderService.sendHtmlMail(mailInfo, 3);
        } catch (Exception e) {
            LOG.debug("send main fail!", e);
        }
        return "registration-acknowledgement.xhtml?faces-redirect=true";
    }

    private boolean validateContent() {
        boolean value = true;
        if (StringUtils.isNotBlank(account.getPersonEmail())) {
            boolean status = accountService.checkEmail(account.getPersonEmail());
            if (status) {
                UIUtil.displayErrorDialog(InternationalizationBean.locale, Constants.ACCOUNT_EMAIL,
                        Constants.ACCOUNT_EMAIL_CHI);
                value = false;
            }
            String personEmailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            if (!account.getPersonEmail().matches(personEmailRegex)) {
                UIUtil.displayErrorDialog(InternationalizationBean.locale, Constants.ACCOUNT_EMAIL_FORMAT,
                        Constants.ACCOUNT_EMAIL_FORMAT_CHI);
                value = false;
            }
        } else {
            UIUtil.setInvalid("registration_form:regEmailAddr");
            value = false;
        }

        if (StringUtils.isNotEmpty(applicantInformation.getAlternateEmail())) {
            String alternateEmailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            if (!applicantInformation.getAlternateEmail().matches(alternateEmailRegex)) {
                UIUtil.setInvalid("registration_form:alterEmailAddr");
                UIUtil.displayErrorDialog(InternationalizationBean.locale, Constants.ACCOUNT_EMAIL_FORMAT,
                        Constants.ACCOUNT_EMAIL_FORMAT_CHI);
                value = false;
            }
        } else {
            UIUtil.setInvalid("registration_form:alterEmailAddr");
            value = false;
        }

        if (StringUtils.isBlank(account.getPassword())) {
            UIUtil.setInvalid("registration_form:password");
            value = false;
        } else {
            if (account.getPassword().length() < 6) {
                UIUtil.setInvalid("registration_form:password");
                UIUtil.displayErrorDialog(InternationalizationBean.locale, Constants.PASSWORD_FORMAT,
                        Constants.PASSWORD_FORMAT_CHI);
                value = false;
            } else {
                String letterRegex = "^[a-zA-Z0-9_.+-]*(?:[a-zA-Z][a-zA-Z0-9_.+-]*){2,}$";
                String numRegex = "^(?=(?:\\D*\\d){2,})[a-zA-Z0-9_.+-]*$";
                if (!account.getPassword().matches(letterRegex) || !account.getPassword().matches(numRegex)) {
                    UIUtil.setInvalid("registration_form:password");
                    UIUtil.displayErrorDialog(InternationalizationBean.locale, Constants.PASSWORD_FORMAT,
                            Constants.PASSWORD_FORMAT_CHI);
                    value = false;
                }
            }
        }

        if (StringUtils.isEmpty(account.getVerifyPassword())) {
            UIUtil.setInvalid("registration_form:retypePassword");
            value = false;
        }

        if (StringUtils.isNotEmpty(applicantInformation.getGivenName())
                || StringUtils.isNotEmpty(applicantInformation.getSurname())) {
            String nameRegex = "^[a-zA-Z]+$";
            if (!applicantInformation.getGivenName().matches(nameRegex)
                    || !applicantInformation.getSurname().matches(nameRegex)) {
                if (!applicantInformation.getGivenName().matches(nameRegex)) {
                    UIUtil.setInvalid("registration_form:givenName");
                }
                if (!applicantInformation.getSurname().matches(nameRegex)) {
                    UIUtil.setInvalid("registration_form:surname");
                }
                UIUtil.displayErrorDialog(InternationalizationBean.locale, Constants.REGISTRATION_TEXT_LANGUAGE,
                        Constants.REGISTRATION_TEXT_LANGUAGE_CHI);
                value = false;
            }
        } else {
            if (StringUtils.isEmpty(applicantInformation.getGivenName())) {
                UIUtil.setInvalid("registration_form:givenName");
            }
            if (StringUtils.isEmpty(applicantInformation.getSurname())) {
                UIUtil.setInvalid("registration_form:surname");
            }
            value = false;
        }

        if (StringUtils.isNotBlank(applicantInformation.getNationalityCountryId())) {
            CountryVo vo = countryService.get(applicantInformation.getNationalityCountryId());
            if (vo != null) {
                if (vo.getCode().equals("CHN")) {
                    if (StringUtils.isEmpty(applicantInformation.getNationalityProvinceId())) {
                        UIUtil.setInvalid("registration_form:provinceOfNationality");
                        value = false;
                    }
                } else {
                    if (StringUtils.isEmpty(applicantInformation.getNationalityCityId())) {
                        UIUtil.setInvalid("registration_form:cityOfNationality");
                        value = false;
                    }
                }
            }
        } else {
            UIUtil.setInvalid("registration_form:countryOfNationality");
            value = false;
            if (StringUtils.isEmpty(applicantInformation.getNationalityCityId())) {
                UIUtil.setInvalid("registration_form:cityOfNationality");
                value = false;
            }
        }

        if (StringUtils.isEmpty(applicantInformation.getResidenceCountryId())) {
            UIUtil.setInvalid("registration_form:currCountryOfResidence");
            value = false;
        }

        if (!applicantInformation.getIsNotStuding()) {
            if (StringUtils.isEmpty(applicantInformation.getStudyCountryId())) {
                UIUtil.setInvalid("registration_form:currCountryOfStudy");
                value = false;
            } else {
                if (applicantInformation.getStudyCountryId().equals("others")) {
                    UIUtil.setInvalid("registration_form:currCountryOfStudyInputText");
                    value = false;
                } else {
                    CountryVo vo = countryService.get(applicantInformation.getStudyCountryId());
                    if (vo != null) {
                        if (vo.getCode().equals("CHN")) {
                            if (StringUtils.isEmpty(applicantInformation.getStudyProvinceId())) {
                                UIUtil.setInvalid("registration_form:province");
                                value = false;
                            }
                        }
                    }
                    if (StringUtils.isEmpty(applicantInformation.getStudyCityId())) {
                        UIUtil.setInvalid("registration_form:city");
                        value = false;
                    }
                }
            }

            if (StringUtils.isEmpty(applicantInformation.getInstitutionId())) {
                UIUtil.setInvalid("registration_form:nameOfInstitution");
                value = false;
            } else {
                if (applicantInformation.getInstitutionId().equals("others")) {
                    if (StringUtils.isEmpty(applicantInformation.getInstitutionOthers())) {
                        UIUtil.setInvalid("registration_form:nameOfInstitutionInputText");
                        value = false;
                    }
                }
            }
        }

        if (StringUtils.isEmpty(mobileTel.getNumber())) {
            UIUtil.setInvalid("registration_form:mobilePhoneNo");
            value = false;
        }

        if (StringUtils.isNotBlank(account.getPersonEmail())
                && StringUtils.isNotBlank(applicantInformation.getAlternateEmail())) {
            if (account.getPersonEmail().equals(applicantInformation.getAlternateEmail())) {
                UIUtil.setInvalid("registration_form:alterEmailAddr");
                UIUtil.displayErrorDialog(InternationalizationBean.locale, Constants.REGISTRATION_ACCOUNT_NO,
                        Constants.REGISTRATION_ACCOUNT_NO_CHI);
                value = false;
            }
        }

        if (StringUtils.isNotEmpty(account.getVerifyPassword()) && StringUtils.isNotEmpty(account.getPassword())) {
            if (!account.getPassword().equals(account.getVerifyPassword())) {
                UIUtil.setInvalid("registration_form:retypePassword");
                UIUtil.displayErrorDialog(InternationalizationBean.locale, Constants.RESET_PASSWORD_EQUALS,
                        Constants.RESET_PASSWORD_EQUALS_CHI);
                value = false;
            }
        }
        return value;
    }

    public void changeArea() {
        if (StringUtils.isBlank(applicantInformation.getNationalityCountryId())) {
            applicantInformation.setNationalityProvinceId("");
            applicantInformation.setNationalityCityId("");
        }
        if (StringUtils.isBlank(applicantInformation.getStudyCountryId())
                || applicantInformation.getStudyCountryId().equals("others")) {
            applicantInformation.setStudyProvinceId("");
            applicantInformation.setStudyCityId("");
            applicantInformation.setInstitutionId("");
            applicantInformation.setInstitutionOthers("");
        }
        if (StringUtils.isBlank(applicantInformation.getStudyProvinceId())) {
            if (StringUtils.isNotBlank(applicantInformation.getStudyCountryId())
                    && !applicantInformation.getStudyCountryId().equals("others")) {
                CountryVo countryVo = countryService.get(applicantInformation.getStudyCountryId());
                if (countryVo != null && countryVo.getCode().equals("CHN")) {
                    applicantInformation.setStudyCityId("");
                    applicantInformation.setInstitutionId("");
                    applicantInformation.setInstitutionOthers("");
                }
            }
        }
        if (StringUtils.isBlank(applicantInformation.getStudyCityId())) {
            applicantInformation.setInstitutionId("");
            applicantInformation.setInstitutionOthers("");
        }
        if (StringUtils.isBlank(applicantInformation.getInstitutionId())) {
            applicantInformation.setInstitutionOthers("");
        }
    }

    public ApplicantInformationVo getApplicantInformation() {
        return applicantInformation;
    }

    public void setApplicantInformation(ApplicantInformationVo applicantInformation) {
        this.applicantInformation = applicantInformation;
    }

    public AccountVo getAccount() {
        return account;
    }

    public void setAccount(AccountVo account) {
        this.account = account;
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
}