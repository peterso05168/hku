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
 * @date 创建时间：2018年4月10日 下午1:37:00
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "app_acad_qual_housing_mgmt_rwe")
@ApiObject(name = "AcadQualHousingMgmtRwe")
public class AcadQualHousingMgmtRwe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "app_acad_qual_housing_mgmt_rwe_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "appointment")
    @ApiObjectField(description = "appointment")
    private String appointment;

    @Column(name = "date_from")
    @ApiObjectField(description = "dateFrom")
    private Date dateFrom;

    @Column(name = "date_to")
    @ApiObjectField(description = "dateTo")
    private Date dateTo;

    @Column(name = "name_of_organization")
    @ApiObjectField(description = "nameOfOrganization")
    private String nameOfOrganization;

    @Column(name = "nature_of_duties")
    @ApiObjectField(description = "natureOfDuties")
    private String natureOfDuties;

    @Column(name = "app_acad_qual_housing_mgmt_id")
    @ApiObjectField(description = "appAcadQualHousingMgmtId")
    private String appAcadQualHousingMgmtId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppointment() {
        return appointment;
    }

    public void setAppointment(String appointment) {
        this.appointment = appointment;
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

    public String getNameOfOrganization() {
        return nameOfOrganization;
    }

    public void setNameOfOrganization(String nameOfOrganization) {
        this.nameOfOrganization = nameOfOrganization;
    }

    public String getNatureOfDuties() {
        return natureOfDuties;
    }

    public void setNatureOfDuties(String natureOfDuties) {
        this.natureOfDuties = natureOfDuties;
    }

    public String getAppAcadQualHousingMgmtId() {
        return appAcadQualHousingMgmtId;
    }

    public void setAppAcadQualHousingMgmtId(String appAcadQualHousingMgmtId) {
        this.appAcadQualHousingMgmtId = appAcadQualHousingMgmtId;
    }
}
