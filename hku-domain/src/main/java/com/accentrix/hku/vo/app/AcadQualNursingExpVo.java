package com.accentrix.hku.vo.app;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年4月10日 下午3:13:56
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class AcadQualNursingExpVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private Date dateFrom;

    private Date dateTo;

    private String mode;

    private String nameOfInstitute;

    private String positionHeld;

    private String aowNod;

    private String expType;

    private String appAcadQualNursingId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getNameOfInstitute() {
        return nameOfInstitute;
    }

    public void setNameOfInstitute(String nameOfInstitute) {
        this.nameOfInstitute = nameOfInstitute;
    }

    public String getPositionHeld() {
        return positionHeld;
    }

    public void setPositionHeld(String positionHeld) {
        this.positionHeld = positionHeld;
    }

    public String getAowNod() {
        return aowNod;
    }

    public void setAowNod(String aowNod) {
        this.aowNod = aowNod;
    }

    public String getExpType() {
        return expType;
    }

    public void setExpType(String expType) {
        this.expType = expType;
    }

    public String getAppAcadQualNursingId() {
        return appAcadQualNursingId;
    }

    public void setAppAcadQualNursingId(String appAcadQualNursingId) {
        this.appAcadQualNursingId = appAcadQualNursingId;
    }
}
