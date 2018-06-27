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
 * @date 创建时间：2018年4月9日 上午11:38:59 
 * @version 1.0 
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "cpgn_crit_nj")
@ApiObject(name = "CritNj")
public class CritNj implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "cpgn_crit_nj_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "is_not_studying")
    @ApiObjectField(description = "isNotStudying")
    private Boolean isNotStudying;

    @Column(name = "country_id")
    @ApiObjectField(description = "countryId")
    private String countryId;

    @Column(name = "province_id")
    @ApiObjectField(description = "provinceId")
    private String provinceId;

    @Column(name = "city_id")
    @ApiObjectField(description = "cityId")
    private String cityId;

    @Column(name = "institution_id")
    @ApiObjectField(description = "institutionId")
    private String institutionId;

    @Column(name = "cpgn_id")
    @ApiObjectField(description = "cpgnId")
    private String cpgnId;

    @Column(name = "selected_for_shortlist")
    @ApiObjectField(description = "selectedForShortlist")
    private Boolean selectedForShortlist;

    @Column(name = "name")
    @ApiObjectField(description = "name")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getIsNotStudying() {
        return isNotStudying;
    }

    public void setIsNotStudying(Boolean isNotStudying) {
        this.isNotStudying = isNotStudying;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
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

    public String getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId;
    }

    public String getCpgnId() {
        return cpgnId;
    }

    public void setCpgnId(String cpgnId) {
        this.cpgnId = cpgnId;
    }

    public Boolean getSelectedForShortlist() {
        return selectedForShortlist;
    }

    public void setSelectedForShortlist(Boolean selectedForShortlist) {
        this.selectedForShortlist = selectedForShortlist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
