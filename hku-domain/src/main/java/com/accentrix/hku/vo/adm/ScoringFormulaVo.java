package com.accentrix.hku.vo.adm;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年3月19日 上午10:55:21 
 * @version 1.0 
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ScoringFormulaVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String formulaName;

    private String description;

    private String formulaType;

    private String admFormProgId;

    private String examTypeId;

    private Integer including;

    private String examBoard;

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
