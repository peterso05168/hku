package com.accentrix.hku.domain.app;

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

import com.accentrix.hku.domain.common.AuditedObject;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午4:29:11
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "app_exp_and_achievements")
@ApiObject(name = "ExpAndAchievements")
public class ExpAndAchievements extends AuditedObject {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "exp_and_achievements_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "application_id")
    @ApiObjectField(description = "applicationId")
    private String applicationId;

    @Column(name = "activity_cat_cd")
    @ApiObjectField(description = "activityCatCd")
    private String activityCatCd;

    @Column(name = "name")
    @ApiObjectField(description = "name")
    private String name;

    @Column(name = "date_from")
    @ApiObjectField(description = "dateFrom")
    private Date dateFrom;

    @Column(name = "date_to")
    @ApiObjectField(description = "dateTo")
    private Date dateTo;

    @Column(name = "organizer")
    @ApiObjectField(description = "organizer")
    private String organizer;

    @Column(name = "role")
    @ApiObjectField(description = "role")
    private String role;

    @Column(name = "is_deleted")
    @ApiObjectField(description = "isDeleted")
    private Boolean isDeleted;

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

    public String getActivityCatCd() {
        return activityCatCd;
    }

    public void setActivityCatCd(String activityCatCd) {
        this.activityCatCd = activityCatCd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
