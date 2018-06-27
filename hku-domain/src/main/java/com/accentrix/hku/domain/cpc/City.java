package com.accentrix.hku.domain.cpc;

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
 * @date 创建时间：2018年1月25日 上午11:37:15
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "cpc_city")
@ApiObject(name = "City")
public class City implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "cd")
    @ApiObjectField(description = "code")
    private String code;

    @Column(name = "desc")
    @ApiObjectField(description = "description")
    private String description;

    @Column(name = "desc_chi")
    @ApiObjectField(description = "descriptionChinese")
    private String descriptionChinese;

    @Column(name = "country_id")
    @ApiObjectField(description = "countryId")
    private String countryId;

    @Column(name = "province_id")
    @ApiObjectField(description = "provinceId")
    private String provinceId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionChinese() {
        return descriptionChinese;
    }

    public void setDescriptionChinese(String descriptionChinese) {
        this.descriptionChinese = descriptionChinese;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }
}
