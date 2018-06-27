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
 * @date 创建时间：2018年1月30日 下午4:30:38
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "app_prev_studies")
@ApiObject(name = "PrevStudies")
public class PrevStudies extends AuditedObject {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "prev_studies_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "application_id")
    @ApiObjectField(description = "applicationId")
    private String applicationId;

    @Column(name = "institution_id")
    @ApiObjectField(description = "institutionId")
    private String institutionId;

    @Column(name = "programme_type_cd")
    @ApiObjectField(description = "programmeTypeCd")
    private String programmeTypeCd;

    @Column(name = "programme_title")
    @ApiObjectField(description = "programmeTitle")
    private String programmeTitle;

    @Column(name = "country_id")
    @ApiObjectField(description = "countryId")
    private String countryId;

    @Column(name = "date_of_award")
    @ApiObjectField(description = "dateOfAward")
    private Date dateOfAward;

    @Column(name = "final_cumulative_gpa")
    @ApiObjectField(description = "finalCumulativeGpa")
    private String finalCumulativeGpa;

    @Column(name = "max_gpa")
    @ApiObjectField(description = "maxGpa")
    private String maxGpa;

    @Column(name = "study_mode_cd")
    @ApiObjectField(description = "studyModeCd")
    private String studyModeCd;

    @Column(name = "is_deleted")
    @ApiObjectField(description = "isDeleted")
    private Boolean isDeleted;

    @Column(name = "province_id")
    @ApiObjectField(description = "provinceId")
    private String provinceId;

    @Column(name = "city_id")
    @ApiObjectField(description = "cityId")
    private String cityId;

    @Column(name = "study_country_others")
    @ApiObjectField(description = "studyCountryOthers")
    private String studyCountryOthers;

    @Column(name = "institution_others")
    @ApiObjectField(description = "institutionOthers")
    private String institutionOthers;

    @Column(name = "prog_type_others")
    @ApiObjectField(description = "progTypeOthers")
    private String progTypeOthers;

    @Column(name = "study_from")
    @ApiObjectField(description = "studyFrom")
    private Date studyFrom;

    @Column(name = "study_to")
    @ApiObjectField(description = "studyTo")
    private Date studyTo;

    @Column(name = "not_gpa")
    @ApiObjectField(description = "notGpa")
    private Boolean notGpa;

    @Column(name = "final_rslt")
    @ApiObjectField(description = "finalRslt")
    private String finalRslt;

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

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
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

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public Date getDateOfAward() {
        return dateOfAward;
    }

    public void setDateOfAward(Date dateOfAward) {
        this.dateOfAward = dateOfAward;
    }

    public String getFinalCumulativeGpa() {
        return finalCumulativeGpa;
    }

    public void setFinalCumulativeGpa(String finalCumulativeGpa) {
        this.finalCumulativeGpa = finalCumulativeGpa;
    }

    public String getMaxGpa() {
        return maxGpa;
    }

    public void setMaxGpa(String maxGpa) {
        this.maxGpa = maxGpa;
    }

    public String getStudyModeCd() {
        return studyModeCd;
    }

    public void setStudyModeCd(String studyModeCd) {
        this.studyModeCd = studyModeCd;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId;
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

    public String getProgTypeOthers() {
        return progTypeOthers;
    }

    public void setProgTypeOthers(String progTypeOthers) {
        this.progTypeOthers = progTypeOthers;
    }

    public Date getStudyFrom() {
        return studyFrom;
    }

    public void setStudyFrom(Date studyFrom) {
        this.studyFrom = studyFrom;
    }

    public Date getStudyTo() {
        return studyTo;
    }

    public void setStudyTo(Date studyTo) {
        this.studyTo = studyTo;
    }

    public Boolean getNotGpa() {
        return notGpa;
    }

    public void setNotGpa(Boolean notGpa) {
        this.notGpa = notGpa;
    }

    public String getFinalRslt() {
        return finalRslt;
    }

    public void setFinalRslt(String finalRslt) {
        this.finalRslt = finalRslt;
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
