package com.accentrix.hku.vo.campaign;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年4月9日 上午11:39:55 
 * @version 1.0 
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class MappingVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String studyProvinceId;

    private String studyProvinceOrCityName;

    private String studyCityId;

    private String interviewProvinceId;

    private String interviewCityId;

    private String countryId;

    private String provinceId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudyProvinceId() {
        return studyProvinceId;
    }

    public void setStudyProvinceId(String studyProvinceId) {
        this.studyProvinceId = studyProvinceId;
    }

    public String getStudyCityId() {
        return studyCityId;
    }

    public void setStudyCityId(String studyCityId) {
        this.studyCityId = studyCityId;
    }

    public String getInterviewProvinceId() {
        return interviewProvinceId;
    }

    public void setInterviewProvinceId(String interviewProvinceId) {
        this.interviewProvinceId = interviewProvinceId;
    }

    public String getInterviewCityId() {
        return interviewCityId;
    }

    public void setInterviewCityId(String interviewCityId) {
        this.interviewCityId = interviewCityId;
    }

    public String getStudyProvinceOrCityName() {
        return studyProvinceOrCityName;
    }

    public void setStudyProvinceOrCityName(String studyProvinceOrCityName) {
        this.studyProvinceOrCityName = studyProvinceOrCityName;
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
}
