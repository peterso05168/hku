package com.accentrix.hku.domain.app;

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

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "app_ibd_best_exam")
@ApiObject(name = "IbdBestExam")
public class IbdBestExam implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "app_ibd_best_exam_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "application_id")
    @ApiObjectField(description = "applicationId")
    private String applicationId;

    @Column(name = "app_qualification_id")
    @ApiObjectField(description = "qualificationId")
    private String qualificationId;

    @Column(name = "total_rslt")
    @ApiObjectField(description = "totalRslt")
    private Integer totalRslt;

    @Column(name = "ee_tok")
    @ApiObjectField(description = "eeTok")
    private Integer eeTok;

    @Column(name = "calculate_type")
    @ApiObjectField(description = "calculateType")
    private String calculateType;

    @Column(name = "out_of")
    @ApiObjectField(description = "outOf")
    private String outOf;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getQualificationId() {
        return qualificationId;
    }

    public void setQualificationId(String qualificationId) {
        this.qualificationId = qualificationId;
    }

    public Integer getTotalRslt() {
        return totalRslt;
    }

    public void setTotalRslt(Integer totalRslt) {
        this.totalRslt = totalRslt;
    }

    public String getCalculateType() {
        return calculateType;
    }

    public void setCalculateType(String calculateType) {
        this.calculateType = calculateType;
    }

    public String getOutOf() {
        return outOf;
    }

    public void setOutOf(String outOf) {
        this.outOf = outOf;
    }

    public Integer getEeTok() {
        return eeTok;
    }

    public void setEeTok(Integer eeTok) {
        this.eeTok = eeTok;
    }

}
