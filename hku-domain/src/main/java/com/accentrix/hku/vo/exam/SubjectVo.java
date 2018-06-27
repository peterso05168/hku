package com.accentrix.hku.vo.exam;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午6:31:22
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class SubjectVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String examSubjectCd;

    private String examSubjectDesc;

    private String examSubjectDescChi;

    private Boolean subSubject;

    private String examTypeId;

    private String examLevel;

    private boolean subjectDisable;

    private String examBoard;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExamSubjectCd() {
        return examSubjectCd;
    }

    public void setExamSubjectCd(String examSubjectCd) {
        this.examSubjectCd = examSubjectCd;
    }

    public String getExamSubjectDesc() {
        return examSubjectDesc;
    }

    public void setExamSubjectDesc(String examSubjectDesc) {
        this.examSubjectDesc = examSubjectDesc;
    }

    public String getExamSubjectDescChi() {
        return examSubjectDescChi;
    }

    public void setExamSubjectDescChi(String examSubjectDescChi) {
        this.examSubjectDescChi = examSubjectDescChi;
    }

    public Boolean getSubSubject() {
        return subSubject;
    }

    public void setSubSubject(Boolean subSubject) {
        this.subSubject = subSubject;
    }

    public String getExamTypeId() {
        return examTypeId;
    }

    public void setExamTypeId(String examTypeId) {
        this.examTypeId = examTypeId;
    }

    public String getExamLevel() {
        return examLevel;
    }

    public void setExamLevel(String examLevel) {
        this.examLevel = examLevel;
    }

    public boolean getSubjectDisable() {
        return subjectDisable;
    }

    public void setSubjectDisable(boolean subjectDisable) {
        this.subjectDisable = subjectDisable;
    }

    public String getExamBoard() {
        return examBoard;
    }

    public void setExamBoard(String examBoard) {
        this.examBoard = examBoard;
    }

}
