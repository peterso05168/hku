package com.accentrix.hku.vo.app;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class IbdBestExamVo implements Comparable<IbdBestExamVo>, Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String applicationId;

    private String qualificationId;

    private Integer totalRslt;

    private Integer eeTok;

    private String calculateType;

    private String outOf;

    @Override
    public int compareTo(IbdBestExamVo o) {
        return this.getTotalRslt().compareTo(o.getTotalRslt());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getQualificationId() {
        return qualificationId;
    }

    public void setQualificationId(String qualificationId) {
        this.qualificationId = qualificationId;
    }

    public Integer getTotalRslt() {
        return totalRslt;
    }

    public void setTotalRslt(Integer totalRslt) {
        this.totalRslt = totalRslt;
    }

    public String getCalculateType() {
        return calculateType;
    }

    public void setCalculateType(String calculateType) {
        this.calculateType = calculateType;
    }

    public String getOutOf() {
        return outOf;
    }

    public void setOutOf(String outOf) {
        this.outOf = outOf;
    }

    public Integer getEeTok() {
        return eeTok;
    }

    public void setEeTok(Integer eeTok) {
        this.eeTok = eeTok;
    }

}
