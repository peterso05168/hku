package com.accentrix.hku.domain.app;

import java.io.Serializable;
import java.util.Date;

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
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年4月10日 下午1:41:13
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "app_acad_qual_nursing_nr")
@ApiObject(name = "AcadQualNursingNr")
public class AcadQualNursingNr implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "app_acad_qual_nursing_nr_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "nursing_school")
    @ApiObjectField(description = "nursingSchool")
    private String nursingSchool;

    @Column(name = "date_from")
    @ApiObjectField(description = "dateFrom")
    private Date dateFrom;

    @Column(name = "date_to")
    @ApiObjectField(description = "dateTo")
    private Date dateTo;

    @Column(name = "gen_or_psy")
    @ApiObjectField(description = "genOrPsy")
    private Boolean genOrPsy;

    @Column(name = "date_of_reg_and_ia")
    @ApiObjectField(description = "dateOfRegAndIa")
    private Date dateOfRegAndIa;

    @Column(name = "reg_status")
    @ApiObjectField(description = "regStatus")
    private String regStatus;

    @Column(name = "app_acad_qual_nursing_id")
    @ApiObjectField(description = "appAcadQualNursingId")
    private String appAcadQualNursingId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNursingSchool() {
        return nursingSchool;
    }

    public void setNursingSchool(String nursingSchool) {
        this.nursingSchool = nursingSchool;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Boolean getGenOrPsy() {
        return genOrPsy;
    }

    public void setGenOrPsy(Boolean genOrPsy) {
        this.genOrPsy = genOrPsy;
    }

    public Date getDateOfRegAndIa() {
        return dateOfRegAndIa;
    }

    public void setDateOfRegAndIa(Date dateOfRegAndIa) {
        this.dateOfRegAndIa = dateOfRegAndIa;
    }

    public String getRegStatus() {
        return regStatus;
    }

    public void setRegStatus(String regStatus) {
        this.regStatus = regStatus;
    }

    public String getAppAcadQualNursingId() {
        return appAcadQualNursingId;
    }

    public void setAppAcadQualNursingId(String appAcadQualNursingId) {
        this.appAcadQualNursingId = appAcadQualNursingId;
    }
}
