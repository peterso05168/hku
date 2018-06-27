package com.accentrix.hku.vo.adm;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年3月19日 上午10:58:54
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class GpsScoringSubjectVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String admScoringFormulaId;

    private String examSubjectId;

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
