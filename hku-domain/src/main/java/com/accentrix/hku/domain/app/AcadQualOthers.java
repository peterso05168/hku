package com.accentrix.hku.domain.app;

import java.io.Serializable;
import java.util.Date;

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
 * @date 创建时间：2018年2月9日 下午2:54:24
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "app_acad_qual_housing_mgmt_others")
@ApiObject(name = "AcadQualOthers")
public class AcadQualOthers implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "app_acad_qual_others_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "degree_title")
    @ApiObjectField(description = "degreeTitle")
    private String degreeTitle;

    @Column(name = "course_duration")
    @ApiObjectField(description = "courseDuration")
    private String courseDuration;

    @Column(name = "major_subject")
    @ApiObjectField(description = "majorSubject")
    private String majorSubject;

    @Column(name = "honours")
    @ApiObjectField(description = "honours")
    private String honours;

    @Column(name = "award_institution")
    @ApiObjectField(description = "awardInstitution")
    private String awardInstitution;

    @Column(name = "other_award_date")
    @ApiObjectField(description = "otherAwardDate")
    private Date otherAwardDate;

    @Column(name = "acad_qual_housing_mgmt_id")
    @ApiObjectField(description = "acadQualHousingMgmtId")
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
