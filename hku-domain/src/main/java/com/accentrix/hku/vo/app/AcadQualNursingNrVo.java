package com.accentrix.hku.vo.app;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年4月10日 下午3:48:11
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class AcadQualNursingNrVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String nursingSchool;

    private Date dateFrom;

    private Date dateTo;

    private Boolean genOrPsy;

    private Date dateOfRegAndIa;

    private String regStatus;

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
