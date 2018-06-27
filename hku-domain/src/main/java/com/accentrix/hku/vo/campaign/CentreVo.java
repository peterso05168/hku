package com.accentrix.hku.vo.campaign;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年4月9日 上午11:37:50
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class CentreVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String provinceId;

    private String cityId;

    private String provinceOrCityName;

    private String provinceOrCityId;

    private String centreName;

    private String cpgnId;

    private String centreAddr;

    private String cpgnSessionId;

    private Date sessionDatetime;

    private Integer assignedQuota;

    private Integer reservedQuota;

    private String dayName;

    private Boolean mapped;

    private String centreNames;

    private List<String> centreIds;

    private String countryName;

    private List<CentreVo> centreVos;

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

    public String getProvinceOrCityName() {
        return provinceOrCityName;
    }

    public void setProvinceOrCityName(String provinceOrCityName) {
        this.provinceOrCityName = provinceOrCityName;
    }

    public String getProvinceOrCityId() {
        return provinceOrCityId;
    }

    public void setProvinceOrCityId(String provinceOrCityId) {
        this.provinceOrCityId = provinceOrCityId;
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

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public String getCpgnSessionId() {
        return cpgnSessionId;
    }

    public void setCpgnSessionId(String cpgnSessionId) {
        this.cpgnSessionId = cpgnSessionId;
    }

    public Boolean getMapped() {
        return mapped;
    }

    public void setMapped(Boolean mapped) {
        this.mapped = mapped;
    }

    public String getCentreNames() {
        return centreNames;
    }

    public void setCentreNames(String centreNames) {
        this.centreNames = centreNames;
    }

    public List<String> getCentreIds() {
        return centreIds;
    }

    public void setCentreIds(List<String> centreIds) {
        this.centreIds = centreIds;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public List<CentreVo> getCentreVos() {
        return centreVos;
    }

    public void setCentreVos(List<CentreVo> centreVos) {
        this.centreVos = centreVos;
    }
}
