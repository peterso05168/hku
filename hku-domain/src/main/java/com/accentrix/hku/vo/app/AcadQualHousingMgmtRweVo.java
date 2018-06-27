package com.accentrix.hku.vo.app;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/** 
* @author 作者lance.mao  
* @Email lance.mao@accentrix.com 
* @date 创建时间：2018年4月10日 下午2:43:33 
*/
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class AcadQualHousingMgmtRweVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String appointment;

    private Date dateFrom;

    private Date dateTo;

    private String nameOfOrganization;

    private String natureOfDuties;

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
