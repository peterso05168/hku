package com.accentrix.hku.vo.app;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年2月9日 下午3:04:11
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class TransCreditsVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String yearOfStudy;

    private String curriculum;

    private Boolean isApplyDirEntry;

    private Boolean isApplyTransCredits;

    private String applicationId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(String yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public String getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(String curriculum) {
        this.curriculum = curriculum;
    }

    public Boolean getIsApplyDirEntry() {
        return isApplyDirEntry;
    }

    public void setIsApplyDirEntry(Boolean isApplyDirEntry) {
        this.isApplyDirEntry = isApplyDirEntry;
    }

    public Boolean getIsApplyTransCredits() {
        return isApplyTransCredits;
    }

    public void setIsApplyTransCredits(Boolean isApplyTransCredits) {
        this.isApplyTransCredits = isApplyTransCredits;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }
}
