package com.accentrix.hku.vo.app;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年2月9日 下午2:54:24
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class AcadQualOthersVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String degreeTitle;

    private String courseDuration;

    private String majorSubject;

    private String honours;

    private String awardInstitution;

    private Date otherAwardDate;

    private String acadQualHousingMgmtId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDegreeTitle() {
        return degreeTitle;
    }

    public void setDegreeTitle(String degreeTitle) {
        this.degreeTitle = degreeTitle;
    }

    public String getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(String courseDuration) {
        this.courseDuration = courseDuration;
    }

    public String getMajorSubject() {
        return majorSubject;
    }

    public void setMajorSubject(String majorSubject) {
        this.majorSubject = majorSubject;
    }

    public String getHonours() {
        return honours;
    }

    public void setHonours(String honours) {
        this.honours = honours;
    }

    public String getAwardInstitution() {
        return awardInstitution;
    }

    public void setAwardInstitution(String awardInstitution) {
        this.awardInstitution = awardInstitution;
    }

    public Date getOtherAwardDate() {
        return otherAwardDate;
    }

    public void setOtherAwardDate(Date otherAwardDate) {
        this.otherAwardDate = otherAwardDate;
    }

    public String getAcadQualHousingMgmtId() {
        return acadQualHousingMgmtId;
    }

    public void setAcadQualHousingMgmtId(String acadQualHousingMgmtId) {
        this.acadQualHousingMgmtId = acadQualHousingMgmtId;
    }
}
