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
 * @date 创建时间：2018年4月9日 上午11:37:50
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "cpgn_centre")
@ApiObject(name = "Centre")
public class Centre implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "cpgn_centre_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "province_id")
    @ApiObjectField(description = "provinceId")
    private String provinceId;

    @Column(name = "city_id")
    @ApiObjectField(description = "cityId")
    private String cityId;

    @Column(name = "centre_name")
    @ApiObjectField(description = "centreName")
    private String centreName;

    @Column(name = "cpgn_id")
    @ApiObjectField(description = "cpgnId")
    private String cpgnId;

    @Column(name = "centre_addr")
    @ApiObjectField(description = "centreAddr")
    private String centreAddr;

    @Column(name = "mapped")
    @ApiObjectField(description = "mapped")
    private Boolean mapped;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCentreName() {
        return centreName;
    }

    public void setCentreName(String centreName) {
        this.centreName = centreName;
    }

    public String getCpgnId() {
        return cpgnId;
    }

    public void setCpgnId(String cpgnId) {
        this.cpgnId = cpgnId;
    }

    public String getCentreAddr() {
        return centreAddr;
    }

    public void setCentreAddr(String centreAddr) {
        this.centreAddr = centreAddr;
    }

    public Boolean getMapped() {
        return mapped;
    }

    public void setMapped(Boolean mapped) {
        this.mapped = mapped;
    }
}
