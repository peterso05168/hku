package com.accentrix.hku.vo.app;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午4:30:25
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class PersonalParticularsVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String email;

    private String surname;

    private String givenName;

    private String chineseName;

    private Date dateOfBirth;

    private String sex;

    private String nationalityCountryId;

    private String residenceProvinceId;

    private String residenceCityId;

    private String residenceCountryId;

    private String homeTelNoId;

    private String mobileTelNoId;

    private Boolean reqVisaInd;

    private String correspondenceAddr;

    private String hkid;

    private String passportNo;

    private String nationalIdCard;

    private String createBy;

    private Date createDate;

    private String updateBy;

    private Date updateDate;

    private int version;

    private Boolean notProvideId;

    private String region;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
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
