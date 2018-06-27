package com.accentrix.hku.vo.campaign;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年4月9日 上午11:40:19 
 * @version 1.0 
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class CpgnSessionVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private Date sessionDatetime;

    private Integer assignedQuota;

    private Integer reservedQuota;

    private String cpgnCentreId;

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
