package com.accentrix.hku.vo.campaign;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年4月9日 上午11:38:59 
 * @version 1.0 
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class CritNjVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private Boolean isNotStudying;

    private String countryId;

    private String provinceId;

    private String cityId;

    private String institutionId;

    private String cpgnId;

    private Boolean selectedForShortlist;

    private String name;

    private List<String> formProgList;

    private List<String> tagList;

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

    public List<String> getFormProgList() {
        return formProgList;
    }

    public void setFormProgList(List<String> formProgList) {
        this.formProgList = formProgList;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }
}
