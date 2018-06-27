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
 * @date 创建时间：2018年1月30日 下午6:31:30
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "exam_type")
@ApiObject(name = "Type")
public class Type implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "exam_type_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "exam_cd")
    @ApiObjectField(description = "examCd")
    private String examCd;

    @Column(name = "exam_desc")
    @ApiObjectField(description = "examDesc")
    private String examDesc;

    @Column(name = "exam_desc_chi")
    @ApiObjectField(description = "examDescChi")
    private String examDescChi;

    @Column(name = "year_start")
    @ApiObjectField(description = "yearStart")
    private Integer yearStart;

    @Column(name = "year_end")
    @ApiObjectField(description = "yearEnd")
    private Integer yearEnd;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExamCd() {
        return examCd;
    }

    public void setExamCd(String examCd) {
        this.examCd = examCd;
    }

    public String getExamDesc() {
        return examDesc;
    }

    public void setExamDesc(String examDesc) {
        this.examDesc = examDesc;
    }

    public String getExamDescChi() {
        return examDescChi;
    }

    public void setExamDescChi(String examDescChi) {
        this.examDescChi = examDescChi;
    }

    public Integer getYearStart() {
        return yearStart;
    }

    public void setYearStart(Integer yearStart) {
        this.yearStart = yearStart;
    }

    public Integer getYearEnd() {
        return yearEnd;
    }

    public void setYearEnd(Integer yearEnd) {
        this.yearEnd = yearEnd;
    }
}
