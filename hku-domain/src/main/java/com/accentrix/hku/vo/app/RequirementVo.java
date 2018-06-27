package com.accentrix.hku.vo.app;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.accentrix.hku.vo.xml.AgeRangeVo;
import com.accentrix.hku.vo.xml.ExaminationVo;
import com.accentrix.hku.vo.xml.GpsVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午4:32:38
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class RequirementVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String type;

    private String description;

    private String objClassName;

    private AgeRangeVo ageRange;

    private ExaminationVo examination;

    private GpsVo gpsVo;

    private Boolean isPublished;

    private String relationship;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getObjClassName() {
        return objClassName;
    }

    public void setObjClassName(String objClassName) {
        this.objClassName = objClassName;
    }

    public AgeRangeVo getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(AgeRangeVo ageRange) {
        this.ageRange = ageRange;
    }

    public ExaminationVo getExamination() {
        return examination;
    }

    public void setExamination(ExaminationVo examination) {
        this.examination = examination;
    }

    public Boolean getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(Boolean isPublished) {
        this.isPublished = isPublished;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public GpsVo getGpsVo() {
        return gpsVo;
    }

    public void setGpsVo(GpsVo gpsVo) {
        this.gpsVo = gpsVo;
    }
}
