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
 * @date 创建时间：2018年1月30日 下午4:31:35
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "app_qualification_rslt")
@ApiObject(name = "QualificationRslt")
public class QualificationRslt implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "app_qualification_rslt_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "app_qualification_id")
    @ApiObjectField(description = "appQualificationId")
    private String appQualificationId;

    @Column(name = "exam_subject_id")
    @ApiObjectField(description = "examSubjectId")
    private String examSubjectId;

    @Column(name = "exam_level")
    @ApiObjectField(description = "examLevel")
    private String examLevel;

    @Column(name = "achieved_grade_cd")
    @ApiObjectField(description = "achievedGradeCd")
    private String achievedGradeCd;

    @Column(name = "achieved_grade_others")
    @ApiObjectField(description = "achievedGradeOthers")
    private String achievedGradeOthers;

    @Column(name = "predicted_grade_cd")
    @ApiObjectField(description = "predictedGradeCd")
    private String predictedGradeCd;

    @Column(name = "predicted_grade_others")
    @ApiObjectField(description = "predictedGradeOthers")
    private String predictedGradeOthers;

    @Column(name = "is_deleted")
    @ApiObjectField(description = "isDeleted")
    private Boolean isDeleted;

    @Column(name = "is_delete_approved")
    @ApiObjectField(description = "isDeleteApproved")
    private Boolean isDeleteApproved;

    @Column(name = "exam_board")
    @ApiObjectField(description = "examBoard")
    private String examBoard;

    @Column(name = "gce_date_of_release")
    @ApiObjectField(description = "gceDateOfRelease")
    private Date gceDateOfRelease;

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

    public Boolean getIsDeleteApproved() {
        return isDeleteApproved;
    }

    public void setIsDeleteApproved(Boolean isDeleteApproved) {
        this.isDeleteApproved = isDeleteApproved;
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
}
