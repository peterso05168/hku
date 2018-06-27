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
 * @date 创建时间：2018年4月9日 上午11:39:55 
 * @version 1.0 
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "cpgn_mapping")
@ApiObject(name = "Mapping")
public class Mapping implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "cpgn_mapping_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "study_province_id")
    @ApiObjectField(description = "studyProvinceId")
    private String studyProvinceId;

    @Column(name = "study_city_id")
    @ApiObjectField(description = "studyCityId")
    private String studyCityId;

    @Column(name = "interview_province_id")
    @ApiObjectField(description = "interviewProvinceId")
    private String interviewProvinceId;

    @Column(name = "interview_city_id")
    @ApiObjectField(description = "interviewCityId")
    private String interviewCityId;

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
}
