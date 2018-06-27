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
 * @date 创建时间：2018年1月30日 下午6:31:12
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "exam_grade")
@ApiObject(name = "Grade")
public class Grade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "exam_grade_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "exam_type_id")
    @ApiObjectField(description = "examTypeId")
    private String examTypeId;

    @Column(name = "grade_cd")
    @ApiObjectField(description = "gradeCd")
    private String gradeCd;

    @Column(name = "grade_point")
    @ApiObjectField(description = "gradePoint")
    private Integer gradePoint;

    @Column(name = "comparable_value")
    @ApiObjectField(description = "comparableValue")
    private Integer comparableValue;

    @Column(name = "exam_level")
    @ApiObjectField(description = "examLevel")
    private String examLevel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExamTypeId() {
        return examTypeId;
    }

    public void setExamTypeId(String examTypeId) {
        this.examTypeId = examTypeId;
    }

    public String getGradeCd() {
        return gradeCd;
    }

    public void setGradeCd(String gradeCd) {
        this.gradeCd = gradeCd;
    }

    public Integer getGradePoint() {
        return gradePoint;
    }

    public void setGradePoint(Integer gradePoint) {
        this.gradePoint = gradePoint;
    }

    public Integer getComparableValue() {
        return comparableValue;
    }

    public void setComparableValue(Integer comparableValue) {
        this.comparableValue = comparableValue;
    }

    public String getExamLevel() {
        return examLevel;
    }

    public void setExamLevel(String examLevel) {
        this.examLevel = examLevel;
    }
}
