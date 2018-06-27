package com.accentrix.hku.vo.exam;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午6:31:12
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class GradeVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String examTypeId;

    private String gradeCd;

    private Integer gradePoint;

    private Integer comparableValue;

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
