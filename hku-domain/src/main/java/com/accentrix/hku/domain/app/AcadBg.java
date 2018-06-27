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
 * @date 创建时间：2018年1月30日 下午4:28:03
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "app_acad_bg")
@ApiObject(name = "AcadBg")
public class AcadBg extends AuditedObject {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "acad_bg_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "country_id")
    @ApiObjectField(description = "countryId")
    private String countryId;

    @Column(name = "province_id")
    @ApiObjectField(description = "provinceId")
    private String provinceId;

    @Column(name = "city_id")
    @ApiObjectField(description = "cityId")
    private String cityId;

    @Column(name = "institution_id")
    @ApiObjectField(description = "institutionId")
    private String institutionId;

    @Column(name = "programme_type_cd")
    @ApiObjectField(description = "programmeTypeCd")
    private String programmeTypeCd;

    @Column(name = "programme_title")
    @ApiObjectField(description = "programmeTitle")
    private String programmeTitle;

    @Column(name = "date_of_admission_to_prog")
    @ApiObjectField(description = "dateOfAdmissionToProg")
    private Date dateOfAdmissionToProg;

    @Column(name = "current_yr_of_study")
    @ApiObjectField(description = "currentYrOfStudy")
    private String currentYrOfStudy;

    @Column(name = "expected_date_of_grad")
    @ApiObjectField(description = "expectedDateOfGrad")
    private Date expectedDateOfGrad;

    @Column(name = "latest_cumulative_gpa")
    @ApiObjectField(description = "latestCumulativeGpa")
    private String latestCumulativeGpa;

    @Column(name = "max_gpa")
    @ApiObjectField(description = "maxGpa")
    private String maxGpa;

    @Column(name = "primary_edu_yrs")
    @ApiObjectField(description = "primaryEduYrs")
    private Integer primaryEduYrs;

    @Column(name = "secondary_edu_yrs")
    @ApiObjectField(description = "secondaryEduYrs")
    private Integer secondaryEduYrs;

    @Column(name = "post_edu_yrs")
    @ApiObjectField(description = "postEduYrs")
    private Integer postEduYrs;

    @Column(name = "highest_qualification_cd")
    @ApiObjectField(description = "highestQualificationCd")
    private String highestQualificationCd;

    @Column(name = "study_mode_cd")
    @ApiObjectField(description = "studyModeCd")
    private String studyModeCd;

    @Column(name = "is_not_studing")
    @ApiObjectField(description = "isNotStuding")
    private Boolean isNotStuding;

    @Column(name = "is_partcp_next_njcee")
    @ApiObjectField(description = "isPartcpNextNjcee")
    private Boolean isPartcpNextNjcee;

    @Column(name = "study_country_others")
    @ApiObjectField(description = "studyCountryOthers")
    private String studyCountryOthers;

    @Column(name = "institution_others")
    @ApiObjectField(description = "institutionOthers")
    private String institutionOthers;

    @Column(name = "final_rslt")
    @ApiObjectField(description = "finalRslt")
    private String finalRslt;

    @Column(name = "not_gpa")
    @ApiObjectField(description = "notGpa")
    private Boolean notGpa;

    @Column(name = "prog_type_others")
    @ApiObjectField(description = "progTypeOthers")
    private String progTypeOthers;

    @Column(name = "type_of_education")
    @ApiObjectField(description = "typeOfEducation")
    private String typeOfEducation;

    @Column(name = "is_completed_study")
    @ApiObjectField(description = "isCompletedStudy")
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
