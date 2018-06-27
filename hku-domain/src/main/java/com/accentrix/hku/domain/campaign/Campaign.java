package com.accentrix.hku.domain.campaign;

import java.io.Serializable;

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
 * @date 创建时间：2018年4月9日 上午10:51:14
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "cpgn")
@ApiObject(name = "Campaign")
public class Campaign implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "cpgn_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "cpgn_name")
    @ApiObjectField(description = "cpgnName")
    private String cpgnName;

    @Column(name = "cpgn_cd")
    @ApiObjectField(description = "cpgnCd")
    private String cpgnCd;

    @Column(name = "cpgn_dtl")
    @ApiObjectField(description = "cpgnDtl")
    private String cpgnDtl;

    @Column(name = "status_cd")
    @ApiObjectField(description = "statusCd")
    private String statusCd;

    @Column(name = "mapped_by")
    @ApiObjectField(description = "mappedBy")
    private String mappedBy;

    @Column(name = "country_id")
    @ApiObjectField(description = "countryId")
    private String countryId;

    @Column(name = "cpgn_year")
    @ApiObjectField(description = "cpgnYear")
    private Integer cpgnYear;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCpgnName() {
        return cpgnName;
    }

    public void setCpgnName(String cpgnName) {
        this.cpgnName = cpgnName;
    }

    public String getCpgnCd() {
        return cpgnCd;
    }

    public void setCpgnCd(String cpgnCd) {
        this.cpgnCd = cpgnCd;
    }

    public String getCpgnDtl() {
        return cpgnDtl;
    }

    public void setCpgnDtl(String cpgnDtl) {
        this.cpgnDtl = cpgnDtl;
    }

    public String getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd;
    }

    public String getMappedBy() {
        return mappedBy;
    }

    public void setMappedBy(String mappedBy) {
        this.mappedBy = mappedBy;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public Integer getCpgnYear() {
        return cpgnYear;
    }

    public void setCpgnYear(Integer cpgnYear) {
        this.cpgnYear = cpgnYear;
    }
}
