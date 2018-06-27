package com.accentrix.hku.vo.xml;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Lonny Wei
 * @date 2018年3月26日 上午10:46:48
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class SubjectRequirementVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String subject;

    private String examLevel;

    private String examBoard;

    private String relationship;

    private String grade;

    private boolean inEdit;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getExamLevel() {
        return examLevel;
    }

    public void setExamLevel(String examLevel) {
        this.examLevel = examLevel;
    }

    public String getExamBoard() {
        return examBoard;
    }

    public void setExamBoard(String examBoard) {
        this.examBoard = examBoard;
    }

    public boolean isInEdit() {
        return inEdit;
    }

    public void setInEdit(boolean inEdit) {
        this.inEdit = inEdit;
    }

}
