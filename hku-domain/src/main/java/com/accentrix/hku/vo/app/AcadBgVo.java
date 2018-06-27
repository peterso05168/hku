package com.accentrix.hku.vo.app;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午4:28:03
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class AcadBgVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String countryId;

    private String provinceId;

    private String cityId;

    private String institutionId;

    private String programmeTypeCd;

    private String programmeTitle;

    private Date dateOfAdmissionToProg;

    private String currentYrOfStudy;

    private Date expectedDateOfGrad;

    private String latestCumulativeGpa;

    private String maxGpa;

    private Integer primaryEduYrs;

    private Integer secondaryEduYrs;

    private Integer postEduYrs;

    private String highestQualificationCd;

    private String studyModeCd;

    private String createBy;

    private Date createDate;

    private String updateBy;

    private Date updateDate;

    private int version;

    private Boolean isNotStuding;

    private Boolean isPartcpNextNjcee;

    private String studyCountryOthers;

    private String primaryYrs;

    private String secondaryYrs;

    private String postYrs;

    private String adminssionFormat;

    private String expectedFormat;

    private String institutionOthers;

    private String finalRslt;

    private Boolean notGpa;

    private String progTypeOthers;

    private String isActiveProgTypeCd;

    private String typeOfEducation;

    private Boolean isCompletedStudy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId;
    }

    public String getProgrammeTypeCd() {
        return programmeTypeCd;
    }

    public void setProgrammeTypeCd(String programmeTypeCd) {
        this.programmeTypeCd = programmeTypeCd;
    }

    public String getProgrammeTitle() {
        return programmeTitle;
    }

    public void setProgrammeTitle(String programmeTitle) {
        this.programmeTitle = programmeTitle;
    }

    public Date getDateOfAdmissionToProg() {
        return dateOfAdmissionToProg;
    }

    public void setDateOfAdmissionToProg(Date dateOfAdmissionToProg) {
        this.dateOfAdmissionToProg = dateOfAdmissionToProg;
    }

    public String getCurrentYrOfStudy() {
        return currentYrOfStudy;
    }

    public void setCurrentYrOfStudy(String currentYrOfStudy) {
        this.currentYrOfStudy = currentYrOfStudy;
    }

    public Date getExpectedDateOfGrad() {
        return expectedDateOfGrad;
    }

    public void setExpectedDateOfGrad(Date expectedDateOfGrad) {
        this.expectedDateOfGrad = expectedDateOfGrad;
    }

    public String getLatestCumulativeGpa() {
        return latestCumulativeGpa;
    }

    public void setLatestCumulativeGpa(String latestCumulativeGpa) {
        this.latestCumulativeGpa = latestCumulativeGpa;
    }

    public String getMaxGpa() {
        return maxGpa;
    }

    public void setMaxGpa(String maxGpa) {
        this.maxGpa = maxGpa;
    }

    public Integer getPrimaryEduYrs() {
        return primaryEduYrs;
    }

    public void setPrimaryEduYrs(Integer primaryEduYrs) {
        this.primaryEduYrs = primaryEduYrs;
    }

    public Integer getSecondaryEduYrs() {
        return secondaryEduYrs;
    }

    public void setSecondaryEduYrs(Integer secondaryEduYrs) {
        this.secondaryEduYrs = secondaryEduYrs;
    }

    public Integer getPostEduYrs() {
        return postEduYrs;
    }

    public void setPostEduYrs(Integer postEduYrs) {
        this.postEduYrs = postEduYrs;
    }

    public String getHighestQualificationCd() {
        return highestQualificationCd;
    }

    public void setHighestQualificationCd(String highestQualificationCd) {
        this.highestQualificationCd = highestQualificationCd;
    }

    public String getStudyModeCd() {
        return studyModeCd;
    }

    public void setStudyModeCd(String studyModeCd) {
        this.studyModeCd = studyModeCd;
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

    public Boolean getIsNotStuding() {
        return isNotStuding;
    }

    public void setIsNotStuding(Boolean isNotStuding) {
        this.isNotStuding = isNotStuding;
    }

    public Boolean getIsPartcpNextNjcee() {
        return isPartcpNextNjcee;
    }

    public void setIsPartcpNextNjcee(Boolean isPartcpNextNjcee) {
        this.isPartcpNextNjcee = isPartcpNextNjcee;
    }

    public String getStudyCountryOthers() {
        return studyCountryOthers;
    }

    public void setStudyCountryOthers(String studyCountryOthers) {
        this.studyCountryOthers = studyCountryOthers;
    }

    public String getPrimaryYrs() {
        return primaryYrs;
    }

    public void setPrimaryYrs(String primaryYrs) {
        this.primaryYrs = primaryYrs;
    }

    public String getSecondaryYrs() {
        return secondaryYrs;
    }

    public void setSecondaryYrs(String secondaryYrs) {
        this.secondaryYrs = secondaryYrs;
    }

    public String getPostYrs() {
        return postYrs;
    }

    public void setPostYrs(String postYrs) {
        this.postYrs = postYrs;
    }

    public String getAdminssionFormat() {
        return adminssionFormat;
    }

    public void setAdminssionFormat(String adminssionFormat) {
        this.adminssionFormat = adminssionFormat;
    }

    public String getExpectedFormat() {
        return expectedFormat;
    }

    public void setExpectedFormat(String expectedFormat) {
        this.expectedFormat = expectedFormat;
    }

    public String getInstitutionOthers() {
        return institutionOthers;
    }

    public void setInstitutionOthers(String institutionOthers) {
        this.institutionOthers = institutionOthers;
    }

    public String getFinalRslt() {
        return finalRslt;
    }

    public void setFinalRslt(String finalRslt) {
        this.finalRslt = finalRslt;
    }

    public Boolean getNotGpa() {
        return notGpa;
    }

    public void setNotGpa(Boolean notGpa) {
        this.notGpa = notGpa;
    }

    public String getProgTypeOthers() {
        return progTypeOthers;
    }

    public void setProgTypeOthers(String progTypeOthers) {
        this.progTypeOthers = progTypeOthers;
    }

    public String getIsActiveProgTypeCd() {
        return isActiveProgTypeCd;
    }

    public void setIsActiveProgTypeCd(String isActiveProgTypeCd) {
        this.isActiveProgTypeCd = isActiveProgTypeCd;
    }

    public String getTypeOfEducation() {
        return typeOfEducation;
    }

    public void setTypeOfEducation(String typeOfEducation) {
        this.typeOfEducation = typeOfEducation;
    }

    public Boolean getIsCompletedStudy() {
        return isCompletedStudy;
    }

    public void setIsCompletedStudy(Boolean isCompletedStudy) {
        this.isCompletedStudy = isCompletedStudy;
    }

}
