package com.accentrix.hku.domain.app;

import java.io.Serializable;

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
 * @date 创建时间：2018年5月29日 下午7:31:36
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "app_best_exam_subj_rslt")
@ApiObject(name = "BestExamSubjRslt")
public class BestExamSubjRslt implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "app_best_exam_subj_rslt_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "app_best_exam_subj_id")
    @ApiObjectField(description = "bestExamSubjId")
    private String bestExamSubjId;

    @Column(name = "app_qualification_rslt_id")
    @ApiObjectField(description = "qualificationRsltId")
    private String qualificationRsltId;

    @Column(name = "seq_no")
    @ApiObjectField(description = "seqNo")
    private Integer seqNo;

    @Column(name = "exam_subject_desc")
    @ApiObjectField(description = "examSubjectDesc")
    private String examSubjectDesc;

    @Column(name = "exam_type_month")
    @ApiObjectField(description = "examTypeMonth")
    private String examTypeMonth;

    @Column(name = "exam_type_year")
    @ApiObjectField(description = "examTypeYear")
    private Integer examTypeYear;

    @Column(name = "exam_grade_cd")
    @ApiObjectField(description = "examGradeCd")
    private String examGradeCd;

    @Column(name = "rslt_type")
    @ApiObjectField(description = "rsltType")
    private String rsltType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBestExamSubjId() {
        return bestExamSubjId;
    }

    public void setBestExamSubjId(String bestExamSubjId) {
        this.bestExamSubjId = bestExamSubjId;
    }

    public String getQualificationRsltId() {
        return qualificationRsltId;
    }

    public void setQualificationRsltId(String qualificationRsltId) {
        this.qualificationRsltId = qualificationRsltId;
    }

    public Integer getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

    public String getExamSubjectDesc() {
        return examSubjectDesc;
    }

    public void setExamSubjectDesc(String examSubjectDesc) {
        this.examSubjectDesc = examSubjectDesc;
    }

    public String getExamTypeMonth() {
        return examTypeMonth;
    }

    public void setExamTypeMonth(String examTypeMonth) {
        this.examTypeMonth = examTypeMonth;
    }

    public Integer getExamTypeYear() {
        return examTypeYear;
    }

    public void setExamTypeYear(Integer examTypeYear) {
        this.examTypeYear = examTypeYear;
    }

    public String getExamGradeCd() {
        return examGradeCd;
    }

    public void setExamGradeCd(String examGradeCd) {
        this.examGradeCd = examGradeCd;
    }

    public String getRsltType() {
        return rsltType;
    }

    public void setRsltType(String rsltType) {
        this.rsltType = rsltType;
    }
}
