package com.accentrix.hku.domain.applicant;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.accentrix.hku.domain.app.Institution;
import com.accentrix.hku.domain.cpc.City;
import com.accentrix.hku.domain.cpc.Country;
import com.accentrix.hku.domain.cpc.Province;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月26日 下午2:35:21
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "applicant_info")
@ApiObject(name = "ApplicantInformation")
public class ApplicantInformation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "applicant_info_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "alternate_email")
    @ApiObjectField(description = "alternateEmail")
    private String alternateEmail;

    @Column(name = "surname")
    @ApiObjectField(description = "surname")
    private String surname;

    @Column(name = "given_name")
    @ApiObjectField(description = "givenName")
    private String givenName;

    @Column(name = "nationality_country_id")
    @ApiObjectField(description = "nationalityCountryId")
    private String nationalityCountryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nationality_country_id", updatable = false, insertable = false)
    @ApiObjectField(description = "nationalityCountry")
    private Country nationalityCountry;

    @Column(name = "nationality_province_id")
    @ApiObjectField(description = "nationalityProvinceId")
    private String nationalityProvinceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nationality_province_id", updatable = false, insertable = false)
    @ApiObjectField(description = "nationalityProvince")
    private Province nationalityProvince;

    @Column(name = "nationality_city_id")
    @ApiObjectField(description = "nationalityCityId")
    private String nationalityCityId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nationality_city_id", updatable = false, insertable = false)
    @ApiObjectField(description = "nationalityCity")
    private City nationalityCity;

    @Column(name = "study_country_id")
    @ApiObjectField(description = "studyCountryId")
    private String studyCountryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_country_id", updatable = false, insertable = false)
    @ApiObjectField(description = "studyCountry")
    private Country studyCountry;

    @Column(name = "study_country_others")
    @ApiObjectField(description = "studyCountryOthers")
    private String studyCountryOthers;

    @Column(name = "home_tel_id")
    @ApiObjectField(description = "homeTelId")
    private String homeTelId;

    @Column(name = "mobile_tel_id")
    @ApiObjectField(description = "mobileTelId")
    private String mobileTelId;

    @Column(name = "institution_id")
    @ApiObjectField(description = "institutionId")
    private String institutionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "institution_id", updatable = false, insertable = false)
    @ApiObjectField(description = "institution")
    private Institution institution;

    @Column(name = "institution_others")
    @ApiObjectField(description = "institutionOthers")
    private String institutionOthers;

    @Column(name = "receive_info_flag")
    @ApiObjectField(description = "receiveInfoFlag")
    private Boolean receiveInfoFlag;

    @Column(name = "residence_country_id")
    @ApiObjectField(description = "residenceCountryId")
    private String residenceCountryId;

    @Column(name = "sex")
    @ApiObjectField(description = "sex")
    private char sex;

    @Column(name = "date_of_birth")
    @ApiObjectField(description = "dateOfBirth")
    private Date dateOfBirth;

    @Column(name = "passport_no")
    @ApiObjectField(description = "passportNo")
    private String passportNo;

    @Column(name = "hkid")
    @ApiObjectField(description = "hkid")
    private String hkid;

    @Column(name = "correspondence_addr")
    @ApiObjectField(description = "correspondenceAddr")
    private String correspondenceAddr;

    @Column(name = "chinese_name")
    @ApiObjectField(description = "chineseName")
    private String chineseName;

    @Column(name = "study_province_id")
    @ApiObjectField(description = "studyProvinceId")
    private String studyProvinceId;

    @Column(name = "study_city_id")
    @ApiObjectField(description = "studyCityId")
    private String studyCityId;

    @Column(name = "is_not_studing")
    @ApiObjectField(description = "isNotStuding")
    private Boolean isNotStuding;

    @Column(name = "batch_anncmnt_date")
    @ApiObjectField(description = "batchAnncmntDate")
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

    public String getNationalityProvinceId() {
        return nationalityProvinceId;
    }

    public void setNationalityProvinceId(String nationalityProvinceId) {
        this.nationalityProvinceId = nationalityProvinceId;
    }

    public String getNationalityCityId() {
        return nationalityCityId;
    }

    public void setNationalityCityId(String nationalityCityId) {
        this.nationalityCityId = nationalityCityId;
    }

    public String getStudyCountryId() {
        return studyCountryId;
    }

    public void setStudyCountryId(String studyCountryId) {
        this.studyCountryId = studyCountryId;
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

    public Country getNationalityCountry() {
        return nationalityCountry;
    }

    public void setNationalityCountry(Country nationalityCountry) {
        this.nationalityCountry = nationalityCountry;
    }

    public Province getNationalityProvince() {
        return nationalityProvince;
    }

    public void setNationalityProvince(Province nationalityProvince) {
        this.nationalityProvince = nationalityProvince;
    }

    public City getNationalityCity() {
        return nationalityCity;
    }

    public void setNationalityCity(City nationalityCity) {
        this.nationalityCity = nationalityCity;
    }

    public Country getStudyCountry() {
        return studyCountry;
    }

    public void setStudyCountry(Country studyCountry) {
        this.studyCountry = studyCountry;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
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

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
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

    public Boolean getIsNotStuding() {
        return isNotStuding;
    }

    public void setIsNotStuding(Boolean isNotStuding) {
        this.isNotStuding = isNotStuding;
    }

    public Date getBatchAnncmntDate() {
        return batchAnncmntDate;
    }

    public void setBatchAnncmntDate(Date batchAnncmntDate) {
        this.batchAnncmntDate = batchAnncmntDate;
    }
}
