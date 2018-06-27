package com.accentrix.hku.vo.app;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class QualificationSubjectVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String applicationId;

    private String qualificationId;

    private String examTypeId;

    private String qualificationRsltId;

    private String examSubjectId;

    private String examSubjectDesc;

    private String achievedGradeCd;

    private String achievedGradeOthers;

    private String predictedGradeCd;

    private String predictedGradeOthers;

    private String examLevel;

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getQualificationId() {
        return qualificationId;
    }

    public void setQualificationId(String qualificationId) {
        this.qualificationId = qualificationId;
    }

    public String getExamTypeId() {
        return examTypeId;
    }

    public void setExamTypeId(String examTypeId) {
        this.examTypeId = examTypeId;
    }

    public String getQualificationRsltId() {
        return qualificationRsltId;
    }

    public void setQualificationRsltId(String qualificationRsltId) {
        this.qualificationRsltId = qualificationRsltId;
    }

    public String getExamSubjectDesc() {
        return examSubjectDesc;
    }

    public void setExamSubjectDesc(String examSubjectDesc) {
        this.examSubjectDesc = examSubjectDesc;
    }

    public String getAchievedGradeCd() {
        return achievedGradeCd;
    }

    public void setAchievedGradeCd(String achievedGradeCd) {
        this.achievedGradeCd = achievedGradeCd;
    }

    public String getAchievedGradeOthers() {
        return achievedGradeOthers;
    }

    public void setAchievedGradeOthers(String achievedGradeOthers) {
        this.achievedGradeOthers = achievedGradeOthers;
    }

    public String getPredictedGradeCd() {
        return predictedGradeCd;
    }

    public void setPredictedGradeCd(String predictedGradeCd) {
        this.predictedGradeCd = predictedGradeCd;
    }

    public String getPredictedGradeOthers() {
        return predictedGradeOthers;
    }

    public void setPredictedGradeOthers(String predictedGradeOthers) {
        this.predictedGradeOthers = predictedGradeOthers;
    }

    public String getExamLevel() {
        return examLevel;
    }

    public void setExamLevel(String examLevel) {
        this.examLevel = examLevel;
    }

    public String getExamSubjectId() {
        return examSubjectId;
    }

    public void setExamSubjectId(String examSubjectId) {
        this.examSubjectId = examSubjectId;
    }

}
