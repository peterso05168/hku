package com.accentrix.hku.vo.xml;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class GpsVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String examination;

    private boolean outOf45;

    private Integer minScore;

    public String getExamination() {
        return examination;
    }

    public void setExamination(String examination) {
        this.examination = examination;
    }

    public boolean isOutOf45() {
        return outOf45;
    }

    public void setOutOf45(boolean outOf45) {
        this.outOf45 = outOf45;
    }

    public Integer getMinScore() {
        return minScore;
    }

    public void setMinScore(Integer minScore) {
        this.minScore = minScore;
    }

}
