package com.accentrix.hku.vo.adm;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年3月19日 上午11:01:06
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class GpaFormulaItmVo implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String maxCgpa;

    private String minYearOneCgpa;

    private String minFinalYearCgpa;

    private Boolean isDeleted = false;

    private String admScoringFormulaId;

    @Override
    public Object clone() {
        GpaFormulaItmVo cloneVo = null;
        try {
            cloneVo = (GpaFormulaItmVo) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return cloneVo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaxCgpa() {
        return maxCgpa;
    }

    public void setMaxCgpa(String maxCgpa) {
        this.maxCgpa = maxCgpa;
    }

    public String getMinYearOneCgpa() {
        return minYearOneCgpa;
    }

    public void setMinYearOneCgpa(String minYearOneCgpa) {
        this.minYearOneCgpa = minYearOneCgpa;
    }

    public String getMinFinalYearCgpa() {
        return minFinalYearCgpa;
    }

    public void setMinFinalYearCgpa(String minFinalYearCgpa) {
        this.minFinalYearCgpa = minFinalYearCgpa;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getAdmScoringFormulaId() {
        return admScoringFormulaId;
    }

    public void setAdmScoringFormulaId(String admScoringFormulaId) {
        this.admScoringFormulaId = admScoringFormulaId;
    }
}
