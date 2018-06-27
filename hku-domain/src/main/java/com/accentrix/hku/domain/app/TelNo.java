package com.accentrix.hku.domain.app;

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

import com.accentrix.hku.domain.common.AuditedObject;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午4:33:27
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "app_tel_no")
@ApiObject(name = "TelNo")
public class TelNo extends AuditedObject {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "tel_no_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "country_cd")
    @ApiObjectField(description = "countryCd")
    private String countryCd;

    @Column(name = "area_cd")
    @ApiObjectField(description = "areaCd")
    private String areaCd;

    @Column(name = "number")
    @ApiObjectField(description = "number")
    private String number;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountryCd() {
        return countryCd;
    }

    public void setCountryCd(String countryCd) {
        this.countryCd = countryCd;
    }

    public String getAreaCd() {
        return areaCd;
    }

    public void setAreaCd(String areaCd) {
        this.areaCd = areaCd;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
