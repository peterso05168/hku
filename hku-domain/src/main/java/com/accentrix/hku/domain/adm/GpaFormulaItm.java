package com.accentrix.hku.domain.adm;

import java.io.Serializable;
import java.math.BigDecimal;

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
 * @date 创建时间：2018年3月19日 上午11:01:06
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "adm_gpa_formula_itm")
@ApiObject(name = "GpaFormulaItm")
public class GpaFormulaItm implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "adm_gpa_formula_itm_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "max_cgpa")
    @ApiObjectField(description = "maxCgpa")
    private BigDecimal maxCgpa;

    @Column(name = "min_year_one_cgpa")
    @ApiObjectField(description = "minYearOneCgpa")
    private BigDecimal minYearOneCgpa;

    @Column(name = "min_final_year_cgpa")
    @ApiObjectField(description = "minFinalYearCgpa")
    private BigDecimal minFinalYearCgpa;

    @Column(name = "is_deleted")
    @ApiObjectField(description = "isDeleted")
    private Boolean isDeleted;

    @Column(name = "adm_scoring_formula_id")
    @ApiObjectField(description = "admScoringFormulaId")
    private String admScoringFormulaId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getMaxCgpa() {
        return maxCgpa;
    }

    public void setMaxCgpa(BigDecimal maxCgpa) {
        this.maxCgpa = maxCgpa;
    }

    public BigDecimal getMinYearOneCgpa() {
        return minYearOneCgpa;
    }

    public void setMinYearOneCgpa(BigDecimal minYearOneCgpa) {
        this.minYearOneCgpa = minYearOneCgpa;
    }

    public BigDecimal getMinFinalYearCgpa() {
        return minFinalYearCgpa;
    }

    public void setMinFinalYearCgpa(BigDecimal minFinalYearCgpa) {
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
