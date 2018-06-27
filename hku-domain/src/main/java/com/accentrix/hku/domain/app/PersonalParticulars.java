package com.accentrix.hku.domain.app;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.accentrix.hku.domain.common.AuditedObject;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午4:30:25
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "app_personal_particulars")
@ApiObject(name = "PersonalParticulars")
public class PersonalParticulars extends AuditedObject {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "personal_paricular_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "email")
    @ApiObjectField(description = "email")
    private String email;

    @Column(name = "surname")
    @ApiObjectField(description = "surname")
    private String surname;

    @Column(name = "given_name")
    @ApiObjectField(description = "givenName")
    private String givenName;

    @Column(name = "chinese_name")
    @ApiObjectField(description = "chineseName")
    private String chineseName;

    @Column(name = "date_of_birth")
    @ApiObjectField(description = "dateOfBirth")
    private Date dateOfBirth;

    @Column(name = "sex")
    @ApiObjectField(description = "sex")
    private String sex;

    @Column(name = "nationality_country_id")
    @ApiObjectField(description = "nationalityCountryId")
    private String nationalityCountryId;

    @Column(name = "residence_province_id")
    @ApiObjectField(description = "residenceProvinceId")
    private String residenceProvinceId;

    @Column(name = "residence_city_id")
    @ApiObjectField(description = "residenceCityId")
    private String residenceCityId;

    @Column(name = "residence_country_id")
    @ApiObjectField(description = "residenceCountryId")
    private String residenceCountryId;

    @Column(name = "home_tel_no_id")
    @ApiObjectField(description = "homeTelNoId")
    private String homeTelNoId;

    @Column(name = "mobile_tel_no_id")
    @ApiObjectField(description = "mobileTelNoId")
    private String mobileTelNoId;

    @Column(name = "req_visa_ind")
    @ApiObjectField(description = "reqVisaInd")
    private Boolean reqVisaInd;

    @Column(name = "correspondence_addr")
    @ApiObjectField(description = "correspondenceAddr")
    private String correspondenceAddr;

    @Column(name = "hkid")
    @ApiObjectField(description = "hkid")
    private String hkid;

    @Column(name = "passport_no")
    @ApiObjectField(description = "passportNo")
    private String passportNo;

    @Column(name = "national_id_card")
    @ApiObjectField(description = "nationalIdCard")
    private String nationalIdCard;

    @Column(name = "not_provide_id")
    @ApiObjectField(description = "notProvideId")
    private Boolean notProvideId;

    @Column(name = "region")
    @ApiObjectField(description = "region")
    private String region;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNationalityCountryId() {
        return nationalityCountryId;
    }

    public void setNationalityCountryId(String nationalityCountryId) {
        this.nationalityCountryId = nationalityCountryId;
    }

    public String getResidenceCountryId() {
        return residenceCountryId;
    }

    public void setResidenceCountryId(String residenceCountryId) {
        this.residenceCountryId = residenceCountryId;
    }

    public String getHomeTelNoId() {
        return homeTelNoId;
    }

    public void setHomeTelNoId(String homeTelNoId) {
        this.homeTelNoId = homeTelNoId;
    }

    public String getMobileTelNoId() {
        return mobileTelNoId;
    }

    public void setMobileTelNoId(String mobileTelNoId) {
        this.mobileTelNoId = mobileTelNoId;
    }

    public Boolean getReqVisaInd() {
        return reqVisaInd;
    }

    public void setReqVisaInd(Boolean reqVisaInd) {
        this.reqVisaInd = reqVisaInd;
    }

    public String getCorrespondenceAddr() {
        return correspondenceAddr;
    }

    public void setCorrespondenceAddr(String correspondenceAddr) {
        this.correspondenceAddr = correspondenceAddr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResidenceProvinceId() {
        return residenceProvinceId;
    }

    public void setResidenceProvinceId(String residenceProvinceId) {
        this.residenceProvinceId = residenceProvinceId;
    }

    public String getResidenceCityId() {
        return residenceCityId;
    }

    public void setResidenceCityId(String residenceCityId) {
        this.residenceCityId = residenceCityId;
    }

    public String getHkid() {
        return hkid;
    }

    public void setHkid(String hkid) {
        this.hkid = hkid;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public String getNationalIdCard() {
        return nationalIdCard;
    }

    public void setNationalIdCard(String nationalIdCard) {
        this.nationalIdCard = nationalIdCard;
    }

    public Boolean getNotProvideId() {
        return notProvideId;
    }

    public void setNotProvideId(Boolean notProvideId) {
        this.notProvideId = notProvideId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
