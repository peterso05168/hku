package com.accentrix.hku.vo.applicant;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.accentrix.hku.domain.app.Institution;
import com.accentrix.hku.domain.cpc.City;
import com.accentrix.hku.domain.cpc.Country;
import com.accentrix.hku.domain.cpc.Province;
import com.accentrix.hku.vo.app.TelNoVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月26日 下午2:49:07
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ApplicantInformationVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String alternateEmail;

    private String surname;

    private String givenName;

    private String nationalityCountryId;

    private Country nationalityCountry;

    private String nationalityProvinceId;

    private Province nationalityProvince;

    private String nationalityCityId;

    private City nationalityCity;

    private String studyCountryId;

    private Country studyCountry;

    private String studyCountryOthers;

    private String studyProvinceId;

    private String studyCityId;

    private String homeTelId;

    private String mobileTelId;

    private String institutionId;

    private Institution institution;

    private String institutionOthers;

    private Boolean receiveInfoFlag;

    private String residenceCountryId;

    private Country residenceCountry;

    private char sex;

    private Date dateOfBirth;

    private String passportNo;

    private String hkid;

    private String correspondenceAddr;

    private String chineseName;

    private Boolean isNotStuding;

    private String name;

    private String registeredEmail;

    private String tags;

    private Integer admissionYear;

    private String admissionYearStr;

    private String applicationNo;

    private String mobile;

    private String provinceCityId;

    private String searchName;

    private String includeTag;

    private String excludeTag;

    private TelNoVo homeTel;

    private TelNoVo mobileTel;

    private Date batchAnncmntDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlternateEmail() {
        return alternateEmail;
    }

    public void setAlternateEmail(String alternateEmail) {
        this.alternateEmail = alternateEmail;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getNationalityCountryId() {
        return nationalityCountryId;
    }

    public void setNationalityCountryId(String nationalityCountryId) {
        this.nationalityCountryId = nationalityCountryId;
    }

    public Country getNationalityCountry() {
        return nationalityCountry;
    }

    public void setNationalityCountry(Country nationalityCountry) {
        this.nationalityCountry = nationalityCountry;
    }

    public String getNationalityProvinceId() {
        return nationalityProvinceId;
    }

    public void setNationalityProvinceId(String nationalityProvinceId) {
        this.nationalityProvinceId = nationalityProvinceId;
    }

    public Province getNationalityProvince() {
        return nationalityProvince;
    }

    public void setNationalityProvince(Province nationalityProvince) {
        this.nationalityProvince = nationalityProvince;
    }

    public String getNationalityCityId() {
        return nationalityCityId;
    }

    public void setNationalityCityId(String nationalityCityId) {
        this.nationalityCityId = nationalityCityId;
    }

    public City getNationalityCity() {
        return nationalityCity;
    }

    public void setNationalityCity(City nationalityCity) {
        this.nationalityCity = nationalityCity;
    }

    public String getStudyCountryId() {
        return studyCountryId;
    }

    public void setStudyCountryId(String studyCountryId) {
        this.studyCountryId = studyCountryId;
    }

    public Country getStudyCountry() {
        return studyCountry;
    }

    public void setStudyCountry(Country studyCountry) {
        this.studyCountry = studyCountry;
    }

    public String getHomeTelId() {
        return homeTelId;
    }

    public void setHomeTelId(String homeTelId) {
        this.homeTelId = homeTelId;
    }

    public String getMobileTelId() {
        return mobileTelId;
    }

    public void setMobileTelId(String mobileTelId) {
        this.mobileTelId = mobileTelId;
    }

    public String getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public String getInstitutionOthers() {
        return institutionOthers;
    }

    public void setInstitutionOthers(String institutionOthers) {
        this.institutionOthers = institutionOthers;
    }

    public Boolean getReceiveInfoFlag() {
        return receiveInfoFlag;
    }

    public void setReceiveInfoFlag(Boolean receiveInfoFlag) {
        this.receiveInfoFlag = receiveInfoFlag;
    }

    public String getResidenceCountryId() {
        return residenceCountryId;
    }

    public void setResidenceCountryId(String residenceCountryId) {
        this.residenceCountryId = residenceCountryId;
    }

    public Country getResidenceCountry() {
        return residenceCountry;
    }

    public void setResidenceCountry(Country residenceCountry) {
        this.residenceCountry = residenceCountry;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public String getHkid() {
        return hkid;
    }

    public void setHkid(String hkid) {
        this.hkid = hkid;
    }

    public String getCorrespondenceAddr() {
        return correspondenceAddr;
    }

    public void setCorrespondenceAddr(String correspondenceAddr) {
        this.correspondenceAddr = correspondenceAddr;
    }

    public String getStudyCountryOthers() {
        return studyCountryOthers;
    }

    public void setStudyCountryOthers(String studyCountryOthers) {
        this.studyCountryOthers = studyCountryOthers;
    }

    public String getStudyProvinceId() {
        return studyProvinceId;
    }

    public void setStudyProvinceId(String studyProvinceId) {
        this.studyProvinceId = studyProvinceId;
    }

    public String getStudyCityId() {
        return studyCityId;
    }

    public void setStudyCityId(String studyCityId) {
        this.studyCityId = studyCityId;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public Boolean getIsNotStuding() {
        return isNotStuding;
    }

    public void setIsNotStuding(Boolean isNotStuding) {
        this.isNotStuding = isNotStuding;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegisteredEmail() {
        return registeredEmail;
    }

    public void setRegisteredEmail(String registeredEmail) {
        this.registeredEmail = registeredEmail;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getAdmissionYear() {
        return admissionYear;
    }

    public void setAdmissionYear(Integer admissionYear) {
        this.admissionYear = admissionYear;
    }

    public String getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProvinceCityId() {
        return provinceCityId;
    }

    public void setProvinceCityId(String provinceCityId) {
        this.provinceCityId = provinceCityId;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public String getIncludeTag() {
        return includeTag;
    }

    public void setIncludeTag(String includeTag) {
        this.includeTag = includeTag;
    }

    public String getExcludeTag() {
        return excludeTag;
    }

    public void setExcludeTag(String excludeTag) {
        this.excludeTag = excludeTag;
    }

    public String getAdmissionYearStr() {
        return admissionYearStr;
    }

    public void setAdmissionYearStr(String admissionYearStr) {
        this.admissionYearStr = admissionYearStr;
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

    public Date getBatchAnncmntDate() {
        return batchAnncmntDate;
    }

    public void setBatchAnncmntDate(Date batchAnncmntDate) {
        this.batchAnncmntDate = batchAnncmntDate;
    }
}
