package com.accentrix.hku.domain.campaign;

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
 * @date 创建时间：2018年4月9日 上午11:40:19 
 * @version 1.0 
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "cpgn_session")
@ApiObject(name = "CpgnSession")
public class CpgnSession implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "cpgn_session_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "session_datetime")
    @ApiObjectField(description = "sessionDatetime")
    private Date sessionDatetime;

    @Column(name = "assigned_quota")
    @ApiObjectField(description = "assignedQuota")
    private Integer assignedQuota;

    @Column(name = "reserved_quota")
    @ApiObjectField(description = "reservedQuota")
    private Integer reservedQuota;

    @Column(name = "cpgn_centre_id")
    @ApiObjectField(description = "cpgnCentreId")
    private String cpgnCentreId;

    @Column(name = "day_name")
    @ApiObjectField(description = "dayName")
    private String dayName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getSessionDatetime() {
        return sessionDatetime;
    }

    public void setSessionDatetime(Date sessionDatetime) {
        this.sessionDatetime = sessionDatetime;
    }

    public Integer getAssignedQuota() {
        return assignedQuota;
    }

    public void setAssignedQuota(Integer assignedQuota) {
        this.assignedQuota = assignedQuota;
    }

    public Integer getReservedQuota() {
        return reservedQuota;
    }

    public void setReservedQuota(Integer reservedQuota) {
        this.reservedQuota = reservedQuota;
    }

    public String getCpgnCentreId() {
        return cpgnCentreId;
    }

    public void setCpgnCentreId(String cpgnCentreId) {
        this.cpgnCentreId = cpgnCentreId;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }
}
