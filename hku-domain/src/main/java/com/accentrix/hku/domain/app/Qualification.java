package com.accentrix.hku.domain.app;

import java.io.Serializable;
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

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午4:31:20
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "app_qualification")
@ApiObject(name = "Qualification")
public class Qualification implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "app_qualification_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "application_id")
    @ApiObjectField(description = "applicationId")
    private String applicationId;

    @Column(name = "date_of_release_of_rslt")
    @ApiObjectField(description = "dateOfReleaseOfRslt")
    private Date dateOfReleaseOfRslt;

    @Column(name = "exam_type_id")
    @ApiObjectField(description = "examTypeId")
    private String examTypeId;

    @Column(name = "ib_school_cd")
    @ApiObjectField(description = "ibSchoolCd")
    private String ibSchoolCd;

    @Column(name = "ib_session_no")
    @ApiObjectField(description = "ibSessionNo")
    private String ibSessionNo;

    @Column(name = "ib_achieved_rslt_grade_cd")
    @ApiObjectField(description = "ibAchievedRsltGradeCd")
    private String ibAchievedRsltGradeCd;

    @Column(name = "ib_achieved_rslt_points")
    @ApiObjectField(description = "ibAchievedRsltPoints")
    private String ibAchievedRsltPoints;

    @Column(name = "ib_achieved_rslt")
    @ApiObjectField(description = "ibAchievedRslt")
    private String ibAchievedRslt;

    @Column(name = "ib_predicted_rslt_grade_cd")
    @ApiObjectField(description = "ibPredictedRsltGradeCd")
    private String ibPredictedRsltGradeCd;

    @Column(name = "ib_predicted_rslt_points")
    @ApiObjectField(description = "ibPredictedRsltPoints")
    private String ibPredictedRsltPoints;

    @Column(name = "ib_predicted_rslt")
    @ApiObjectField(description = "ibPredictedRslt")
    private String ibPredictedRslt;

    @Column(name = "njcee_exam_province_id")
    @ApiObjectField(description = "njceeExamProvinceId")
    private String njceeExamProvinceId;

    @Column(name = "njcee_candidate_no")
    @ApiObjectField(description = "njceeCandidateNo")
    private String njceeCandidateNo;

    @Column(name = "njcee_stream_cd")
    @ApiObjectField(description = "njceeStreamCd")
    private String njceeStreamCd;

    @Column(name = "njcee_stream_others")
    @ApiObjectField(description = "njceeStreamOthers")
    private String njceeStreamOthers;

    @Column(name = "njcee_current_edu_status_cd")
    @ApiObjectField(description = "njceeCurrentEduStatusCd")
    private String njceeCurrentEduStatusCd;

    @Column(name = "njcee_exam_no")
    @ApiObjectField(description = "njceeExamNo")
    private String njceeExamNo;

    @Column(name = "njcee_hku_mf_excellent_sche")
    @ApiObjectField(description = "njceeHkuMfExcellentSche")
    private Boolean njceeHkuMfExcellentSche;

    @Column(name = "last_updated_date")
    @ApiObjectField(description = "lastUpdatedDate")
    private Date lastUpdatedDate;

    @Column(name = "last_batch_exec_date")
    @ApiObjectField(description = "lastBatchExecDate")
    private Date lastBatchExecDate;

    @Column(name = "exam_type_year")
    @ApiObjectField(description = "examTypeYear")
    private Integer examTypeYear;

    @Column(name = "exam_type_month")
    @ApiObjectField(description = "examTypeMonth")
    private Integer examTypeMonth;

    @Column(name = "is_deleted")
    @ApiObjectField(description = "isDeleted")
    private Boolean isDeleted;

    @Column(name = "is_delete_approved")
    @ApiObjectField(description = "isDeleteApproved")
    private Boolean isDeleteApproved;

    @Column(name = "toefl_idno")
    @ApiObjectField(description = "toeflIdno")
    private String toeflIdno;

    @Column(name = "njcee_total_score")
    @ApiObjectField(description = "njceeTotalScore")
    private String njceeTotalScore;

    @Column(name = "last_updated_by")
    @ApiObjectField(description = "lastUpdatedBy")
    private String lastUpdatedBy;

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

    public Date getDateOfReleaseOfRslt() {
        return dateOfReleaseOfRslt;
    }

    public void setDateOfReleaseOfRslt(Date dateOfReleaseOfRslt) {
        this.dateOfReleaseOfRslt = dateOfReleaseOfRslt;
    }

    public String getExamTypeId() {
        return examTypeId;
    }

    public void setExamTypeId(String examTypeId) {
        this.examTypeId = examTypeId;
    }

    public String getIbSchoolCd() {
        return ibSchoolCd;
    }

    public void setIbSchoolCd(String ibSchoolCd) {
        this.ibSchoolCd = ibSchoolCd;
    }

    public String getIbSessionNo() {
        return ibSessionNo;
    }

    public void setIbSessionNo(String ibSessionNo) {
        this.ibSessionNo = ibSessionNo;
    }

    public String getIbAchievedRsltGradeCd() {
        return ibAchievedRsltGradeCd;
    }

    public void setIbAchievedRsltGradeCd(String ibAchievedRsltGradeCd) {
        this.ibAchievedRsltGradeCd = ibAchievedRsltGradeCd;
    }

    public String getIbAchievedRsltPoints() {
        return ibAchievedRsltPoints;
    }

    public void setIbAchievedRsltPoints(String ibAchievedRsltPoints) {
        this.ibAchievedRsltPoints = ibAchievedRsltPoints;
    }

    public String getIbAchievedRslt() {
        return ibAchievedRslt;
    }

    public void setIbAchievedRslt(String ibAchievedRslt) {
        this.ibAchievedRslt = ibAchievedRslt;
    }

    public String getIbPredictedRsltGradeCd() {
        return ibPredictedRsltGradeCd;
    }

    public void setIbPredictedRsltGradeCd(String ibPredictedRsltGradeCd) {
        this.ibPredictedRsltGradeCd = ibPredictedRsltGradeCd;
    }

    public String getIbPredictedRsltPoints() {
        return ibPredictedRsltPoints;
    }

    public void setIbPredictedRsltPoints(String ibPredictedRsltPoints) {
        this.ibPredictedRsltPoints = ibPredictedRsltPoints;
    }

    public String getIbPredictedRslt() {
        return ibPredictedRslt;
    }

    public void setIbPredictedRslt(String ibPredictedRslt) {
        this.ibPredictedRslt = ibPredictedRslt;
    }

    public String getNjceeExamProvinceId() {
        return njceeExamProvinceId;
    }

    public void setNjceeExamProvinceId(String njceeExamProvinceId) {
        this.njceeExamProvinceId = njceeExamProvinceId;
    }

    public String getNjceeCandidateNo() {
        return njceeCandidateNo;
    }

    public void setNjceeCandidateNo(String njceeCandidateNo) {
        this.njceeCandidateNo = njceeCandidateNo;
    }

    public String getNjceeStreamCd() {
        return njceeStreamCd;
    }

    public void setNjceeStreamCd(String njceeStreamCd) {
        this.njceeStreamCd = njceeStreamCd;
    }

    public String getNjceeStreamOthers() {
        return njceeStreamOthers;
    }

    public void setNjceeStreamOthers(String njceeStreamOthers) {
        this.njceeStreamOthers = njceeStreamOthers;
    }

    public String getNjceeCurrentEduStatusCd() {
        return njceeCurrentEduStatusCd;
    }

    public void setNjceeCurrentEduStatusCd(String njceeCurrentEduStatusCd) {
        this.njceeCurrentEduStatusCd = njceeCurrentEduStatusCd;
    }

    public String getNjceeExamNo() {
        return njceeExamNo;
    }

    public void setNjceeExamNo(String njceeExamNo) {
        this.njceeExamNo = njceeExamNo;
    }

    public Boolean getNjceeHkuMfExcellentSche() {
        return njceeHkuMfExcellentSche;
    }

    public void setNjceeHkuMfExcellentSche(Boolean njceeHkuMfExcellentSche) {
        this.njceeHkuMfExcellentSche = njceeHkuMfExcellentSche;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Date getLastBatchExecDate() {
        return lastBatchExecDate;
    }

    public void setLastBatchExecDate(Date lastBatchExecDate) {
        this.lastBatchExecDate = lastBatchExecDate;
    }

    public Integer getExamTypeYear() {
        return examTypeYear;
    }

    public void setExamTypeYear(Integer examTypeYear) {
        this.examTypeYear = examTypeYear;
    }

    public Integer getExamTypeMonth() {
        return examTypeMonth;
    }

    public void setExamTypeMonth(Integer examTypeMonth) {
        this.examTypeMonth = examTypeMonth;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Boolean getIsDeleteApproved() {
        return isDeleteApproved;
    }

    public void setIsDeleteApproved(Boolean isDeleteApproved) {
        this.isDeleteApproved = isDeleteApproved;
    }

    public String getToeflIdno() {
        return toeflIdno;
    }

    public void setToeflIdno(String toeflIdno) {
        this.toeflIdno = toeflIdno;
    }

    public String getNjceeTotalScore() {
        return njceeTotalScore;
    }

    public void setNjceeTotalScore(String njceeTotalScore) {
        this.njceeTotalScore = njceeTotalScore;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
}
