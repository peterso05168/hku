package com.accentrix.hku.vo.app;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.accentrix.hku.vo.exam.GradeVo;
import com.accentrix.hku.vo.exam.TypeVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午4:31:20
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class QualificationVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String applicationId;

    private Date dateOfReleaseOfRslt;

    private String examTypeId;

    private String ibSchoolCd;

    private String ibSessionNo;

    private String ibAchievedRsltGradeCd;

    private String ibAchievedRsltPoints;

    private String ibAchievedRslt;

    private String ibPredictedRsltGradeCd;

    private String ibPredictedRsltPoints;

    private String ibPredictedRslt;

    private String njceeExamProvinceId;

    private String njceeCandidateNo;

    private String njceeStreamCd;

    private String njceeStreamOthers;

    private String njceeCurrentEduStatusCd;

    private String njceeExamNo;

    private Boolean njceeHkuMfExcellentSche;

    private Date lastUpdatedDate;

    private Date lastBatchExecDate;

    private Integer examTypeYear;

    private Integer examTypeMonth;

    private Boolean isDeleted;

    private Boolean isDeleteApproved;

    private TypeVo type;

    private List<QualificationRsltVo> qualificationRslts;

    private List<GradeVo> grades;

    private String achievedTotalPoints;

    private String predictedTotalPoints;

    private String njceeExamProvinceDesc;

    private boolean changeProvince;

    private boolean changeStream;

    private boolean isSelectNJCEE;

    private String toeflIdno;

    private String njceeTotalScore;

    private Map<String, QualificationRsltVo> njceeRsltMap;

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

    public TypeVo getType() {
        return type;
    }

    public void setType(TypeVo type) {
        this.type = type;
    }

    public List<QualificationRsltVo> getQualificationRslts() {
        return qualificationRslts;
    }

    public void setQualificationRslts(List<QualificationRsltVo> qualificationRslts) {
        this.qualificationRslts = qualificationRslts;
    }

    public List<GradeVo> getGrades() {
        return grades;
    }

    public void setGrades(List<GradeVo> grades) {
        this.grades = grades;
    }

    public String getAchievedTotalPoints() {
        return achievedTotalPoints;
    }

    public void setAchievedTotalPoints(String achievedTotalPoints) {
        this.achievedTotalPoints = achievedTotalPoints;
    }

    public String getPredictedTotalPoints() {
        return predictedTotalPoints;
    }

    public void setPredictedTotalPoints(String predictedTotalPoints) {
        this.predictedTotalPoints = predictedTotalPoints;
    }

    public String getNjceeExamProvinceDesc() {
        return njceeExamProvinceDesc;
    }

    public void setNjceeExamProvinceDesc(String njceeExamProvinceDesc) {
        this.njceeExamProvinceDesc = njceeExamProvinceDesc;
    }

    public boolean getChangeProvince() {
        return changeProvince;
    }

    public void setChangeProvince(boolean changeProvince) {
        this.changeProvince = changeProvince;
    }

    public boolean getChangeStream() {
        return changeStream;
    }

    public void setChangeStream(boolean changeStream) {
        this.changeStream = changeStream;
    }

    public boolean getIsSelectNJCEE() {
        return isSelectNJCEE;
    }

    public void setIsSelectNJCEE(boolean isSelectNJCEE) {
        this.isSelectNJCEE = isSelectNJCEE;
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

    public Map<String, QualificationRsltVo> getNjceeRsltMap() {
        return njceeRsltMap;
    }

    public void setNjceeRsltMap(Map<String, QualificationRsltVo> njceeRsltMap) {
        this.njceeRsltMap = njceeRsltMap;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
}
