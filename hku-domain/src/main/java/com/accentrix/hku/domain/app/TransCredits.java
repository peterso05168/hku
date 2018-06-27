package com.accentrix.hku.domain.app;

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
 * @date 创建时间：2018年2月9日 下午3:04:11 
 * @version 1.0 
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "app_trans_credits")
@ApiObject(name = "TransCredits")
public class TransCredits implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "app_trans_credits_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "year_of_study")
    @ApiObjectField(description = "yearOfStudy")
    private String yearOfStudy;

    @Column(name = "curriculum")
    @ApiObjectField(description = "curriculum")
    private String curriculum;

    @Column(name = "is_apply_dir_entry")
    @ApiObjectField(description = "isApplyDirEntry")
    private Boolean isApplyDirEntry;

    @Column(name = "is_apply_trans_credits")
    @ApiObjectField(description = "isApplyTransCredits")
    private Boolean isApplyTransCredits;

    @Column(name = "application_id")
    @ApiObjectField(description = "applicationId")
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
