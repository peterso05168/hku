package com.accentrix.hku.vo.app;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年5月29日 下午7:31:36
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class BestExamSubjRsltVo implements Comparable<BestExamSubjRsltVo>, Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String bestExamSubjId;

    private String qualificationRsltId;

    private Integer seqNo;

    private String examSubjectDesc;

    private String examTypeMonth;

    private Integer examTypeYear;

    private String examGradeCd;

    private String predictedGradeCd;

    private Integer comparableValue;

    private String examLevel;

    private String rsltType;

    @Override
    public int compareTo(BestExamSubjRsltVo o) {
        return this.getComparableValue().compareTo(o.getComparableValue());
    }

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

    public Integer getComparableValue() {
        return comparableValue;
    }

    public void setComparableValue(Integer comparableValue) {
        this.comparableValue = comparableValue;
    }

    public String getPredictedGradeCd() {
        return predictedGradeCd;
    }

    public void setPredictedGradeCd(String predictedGradeCd) {
        this.predictedGradeCd = predictedGradeCd;
    }

    public String getExamLevel() {
        return examLevel;
    }

    public void setExamLevel(String examLevel) {
        this.examLevel = examLevel;
    }

    public String getRsltType() {
        return rsltType;
    }

    public void setRsltType(String rsltType) {
        this.rsltType = rsltType;
    }
}
