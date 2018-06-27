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
 * @date 创建时间：2018年3月19日 上午10:58:54
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "adm_gps_scoring_subject")
@ApiObject(name = "GpsScoringSubject")
public class GpsScoringSubject implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "adm_gps_scoring_subject_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "adm_scoring_formula_id")
    @ApiObjectField(description = "admScoringFormulaId")
    private String admScoringFormulaId;

    @Column(name = "exam_subject_id")
    @ApiObjectField(description = "examSubjectId")
    private String examSubjectId;

    @Column(name = "type")
    @ApiObjectField(description = "type")
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdmScoringFormulaId() {
        return admScoringFormulaId;
    }

    public void setAdmScoringFormulaId(String admScoringFormulaId) {
        this.admScoringFormulaId = admScoringFormulaId;
    }

    public String getExamSubjectId() {
        return examSubjectId;
    }

    public void setExamSubjectId(String examSubjectId) {
        this.examSubjectId = examSubjectId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
