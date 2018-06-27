package com.accentrix.hku.vo.app;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午4:31:35
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class QualificationRsltVo implements Comparable<QualificationRsltVo> {

    private String id;

    private String appQualificationId;

    private String examSubjectId;

    private String examLevel;

    private String achievedGradeCd;

    private int comparableValue;

    private String achievedGradeOthers;

    private String predictedGradeCd;

    private String predictedGradeOthers;

    private Boolean isDeleted;

    private boolean isAchievedOthers;

    private boolean isPredictedOthers;

    private Boolean isDeleteApproved;

    private String examSubjectDesc;

    private boolean isActiveAchievedGrade;

    private String initExamSubjectId;

    private String examBoard;

    private Date gceDateOfRelease;

    @Override
    public int compareTo(QualificationRsltVo o) {
        return o.getComparableValue() - this.getComparableValue();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppQualificationId() {
        return appQualificationId;
    }

    public void setAppQualificationId(String appQualificationId) {
        this.appQualificationId = appQualificationId;
    }

    public String getExamSubjectId() {
        return examSubjectId;
    }

    public void setExamSubjectId(String examSubjectId) {
        this.examSubjectId = examSubjectId;
    }

    public String getExamLevel() {
        return examLevel;
    }

    public void setExamLevel(String examLevel) {
        this.examLevel = examLevel;
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

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public boolean getIsAchievedOthers() {
        return isAchievedOthers;
    }

    public void setIsAchievedOthers(boolean isAchievedOthers) {
        this.isAchievedOthers = isAchievedOthers;
    }

    public boolean getIsPredictedOthers() {
        return isPredictedOthers;
    }

    public void setIsPredictedOthers(boolean isPredictedOthers) {
        this.isPredictedOthers = isPredictedOthers;
    }

    public Boolean getIsDeleteApproved() {
        return isDeleteApproved;
    }

    public void setIsDeleteApproved(Boolean isDeleteApproved) {
        this.isDeleteApproved = isDeleteApproved;
    }

    public String getExamSubjectDesc() {
        return examSubjectDesc;
    }

    public void setExamSubjectDesc(String examSubjectDesc) {
        this.examSubjectDesc = examSubjectDesc;
    }

    public boolean getIsActiveAchievedGrade() {
        return isActiveAchievedGrade;
    }

    public void setIsActiveAchievedGrade(boolean isActiveAchievedGrade) {
        this.isActiveAchievedGrade = isActiveAchievedGrade;
    }

    public String getInitExamSubjectId() {
        return initExamSubjectId;
    }

    public void setInitExamSubjectId(String initExamSubjectId) {
        this.initExamSubjectId = initExamSubjectId;
    }

    public String getExamBoard() {
        return examBoard;
    }

    public void setExamBoard(String examBoard) {
        this.examBoard = examBoard;
    }

    public Date getGceDateOfRelease() {
        return gceDateOfRelease;
    }

    public void setGceDateOfRelease(Date gceDateOfRelease) {
        this.gceDateOfRelease = gceDateOfRelease;
    }

    public int getComparableValue() {
        return comparableValue;
    }

    public void setComparableValue(int comparableValue) {
        this.comparableValue = comparableValue;
    }
}
