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
 * @date 创建时间：2018年4月10日 下午1:49:18
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "app_acad_qual_nursing_exp")
@ApiObject(name = "AcadQualNursingExp")
public class AcadQualNursingExp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "app_acad_qual_nursing_exp_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "date_from")
    @ApiObjectField(description = "dateFrom")
    private Date dateFrom;

    @Column(name = "date_to")
    @ApiObjectField(description = "dateTo")
    private Date dateTo;

    @Column(name = "mode")
    @ApiObjectField(description = "mode")
    private String mode;

    @Column(name = "name_of_institute")
    @ApiObjectField(description = "nameOfInstitute")
    private String nameOfInstitute;

    @Column(name = "position_held")
    @ApiObjectField(description = "positionHeld")
    private String positionHeld;

    @Column(name = "aow_nod")
    @ApiObjectField(description = "aowNod")
    private String aowNod;

    @Column(name = "exp_type")
    @ApiObjectField(description = "expType")
    private String expType;

    @Column(name = "app_acad_qual_nursing_id")
    @ApiObjectField(description = "appAcadQualNursingId")
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
