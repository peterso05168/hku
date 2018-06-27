package com.accentrix.hku.domain.exam;

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
 * @date 创建时间：2018年1月30日 下午6:31:22
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "exam_subject")
@ApiObject(name = "Subject")
public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "exam_subject_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "exam_subject_cd")
    @ApiObjectField(description = "examSubjectCd")
    private String examSubjectCd;

    @Column(name = "exam_subject_desc")
    @ApiObjectField(description = "examSubjectDesc")
    private String examSubjectDesc;

    @Column(name = "exam_subject_desc_chi")
    @ApiObjectField(description = "examSubjectDescChi")
    private String examSubjectDescChi;

    @Column(name = "sub_subject")
    @ApiObjectField(description = "subSubject")
    private Boolean subSubject;

    @Column(name = "exam_type_id")
    @ApiObjectField(description = "examTypeId")
    private String examTypeId;

    @Column(name = "exam_level")
    @ApiObjectField(description = "examLevel")
    private String examLevel;

    @Column(name = "exam_board")
    @ApiObjectField(description = "examBoard")
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

    public String getExamBoard() {
        return examBoard;
    }

    public void setExamBoard(String examBoard) {
        this.examBoard = examBoard;
    }
}
