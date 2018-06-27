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
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午4:28:23
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "app_acad_qual_housing_mgmt")
@ApiObject(name = "AcadQualHousingMgmt")
public class AcadQualHousingMgmt implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "acad_qual_housing_mgmt_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "period_from")
    @ApiObjectField(description = "periodFrom")
    private Date periodFrom;

    @Column(name = "period_to")
    @ApiObjectField(description = "periodTo")
    private Date periodTo;

    @Column(name = "hm_award_date")
    @ApiObjectField(description = "hmAwardDate")
    private Date hmAwardDate;

    @Column(name = "position_held")
    @ApiObjectField(description = "positionHeld")
    private String positionHeld;

    @Column(name = "starting_date")
    @ApiObjectField(description = "startingDate")
    private Date startingDate;

    @Column(name = "name_and_addr")
    @ApiObjectField(description = "nameAndAddr")
    private String nameAndAddr;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getPeriodFrom() {
        return periodFrom;
    }

    public void setPeriodFrom(Date periodFrom) {
        this.periodFrom = periodFrom;
    }

    public Date getPeriodTo() {
        return periodTo;
    }

    public void setPeriodTo(Date periodTo) {
        this.periodTo = periodTo;
    }

    public Date getHmAwardDate() {
        return hmAwardDate;
    }

    public void setHmAwardDate(Date hmAwardDate) {
        this.hmAwardDate = hmAwardDate;
    }

    public String getPositionHeld() {
        return positionHeld;
    }

    public void setPositionHeld(String positionHeld) {
        this.positionHeld = positionHeld;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public String getNameAndAddr() {
        return nameAndAddr;
    }

    public void setNameAndAddr(String nameAndAddr) {
        this.nameAndAddr = nameAndAddr;
    }
}
