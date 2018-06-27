package com.accentrix.hku.domain.scholar;

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
 * @date 创建时间：2018年3月19日 上午11:04:37
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "scholar")
@ApiObject(name = "Scholar")
public class Scholar implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "scholar_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "name")
    @ApiObjectField(description = "name")
    private String name;

    @Column(name = "status")
    @ApiObjectField(description = "status")
    private String status;

    @Column(name = "not_app_end_date")
    @ApiObjectField(description = "notAppEndDate")
    private Boolean notAppEndDate;

    @Column(name = "scholar_start_date")
    @ApiObjectField(description = "scholarStartDate")
    private Date scholarStartDate;

    @Column(name = "scholar_end_date")
    @ApiObjectField(description = "scholarEndDate")
    private Date scholarEndDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getNotAppEndDate() {
        return notAppEndDate;
    }

    public void setNotAppEndDate(Boolean notAppEndDate) {
        this.notAppEndDate = notAppEndDate;
    }

    public Date getScholarStartDate() {
        return scholarStartDate;
    }

    public void setScholarStartDate(Date scholarStartDate) {
        this.scholarStartDate = scholarStartDate;
    }

    public Date getScholarEndDate() {
        return scholarEndDate;
    }

    public void setScholarEndDate(Date scholarEndDate) {
        this.scholarEndDate = scholarEndDate;
    }
}
