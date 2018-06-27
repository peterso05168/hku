package com.accentrix.hku.vo.campaign;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年4月9日 上午10:51:14
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class CampaignVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String cpgnName;

    private String cpgnCd;

    private String cpgnDtl;

    private String statusCd;

    private String mappedBy;

    private String countryId;

    private String countryName;

    private String combination;

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

    public String getCombination() {
        return combination;
    }

    public void setCombination(String combination) {
        this.combination = combination;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Integer getCpgnYear() {
        return cpgnYear;
    }

    public void setCpgnYear(Integer cpgnYear) {
        this.cpgnYear = cpgnYear;
    }
}
