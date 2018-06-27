package com.accentrix.hku.domain.adm;

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
 * @date 创建时间：2018年3月19日 上午10:55:21 
 * @version 1.0 
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "adm_scoring_formula")
@ApiObject(name = "ScoringFormula")
public class ScoringFormula implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "adm_scoring_formula_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "formula_name")
    @ApiObjectField(description = "formulaName")
    private String formulaName;

    @Column(name = "description")
    @ApiObjectField(description = "description")
    private String description;

    @Column(name = "formula_type")
    @ApiObjectField(description = "formulaType")
    private String formulaType;

    @Column(name = "adm_form_prog_id")
    @ApiObjectField(description = "admFormProgId")
    private String admFormProgId;

    @Column(name = "exam_type_id")
    @ApiObjectField(description = "examTypeId")
    private String examTypeId;

    @Column(name = "including")
    @ApiObjectField(description = "including")
    private Integer including;

    @Column(name = "exam_board")
    @ApiObjectField(description = "examBoard")
    private String examBoard;

    @Column(name = "exam_level")
    @ApiObjectField(description = "examLevel")
    private String examLevel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFormulaName() {
        return formulaName;
    }

    public void setFormulaName(String formulaName) {
        this.formulaName = formulaName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFormulaType() {
        return formulaType;
    }

    public void setFormulaType(String formulaType) {
        this.formulaType = formulaType;
    }

    public String getAdmFormProgId() {
        return admFormProgId;
    }

    public void setAdmFormProgId(String admFormProgId) {
        this.admFormProgId = admFormProgId;
    }

    public String getExamTypeId() {
        return examTypeId;
    }

    public void setExamTypeId(String examTypeId) {
        this.examTypeId = examTypeId;
    }

    public Integer getIncluding() {
        return including;
    }

    public void setIncluding(Integer including) {
        this.including = including;
    }

    public String getExamBoard() {
        return examBoard;
    }

    public void setExamBoard(String examBoard) {
        this.examBoard = examBoard;
    }

    public String getExamLevel() {
        return examLevel;
    }

    public void setExamLevel(String examLevel) {
        this.examLevel = examLevel;
    }
}
