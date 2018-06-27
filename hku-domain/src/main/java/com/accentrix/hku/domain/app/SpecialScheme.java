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
 * @date 创建时间：2018年1月30日 下午4:33:08
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "app_special_scheme")
@ApiObject(name = "SpecialScheme")
public class SpecialScheme implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "special_scheme_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "special_scheme_cd")
    @ApiObjectField(description = "specialSchemeCd")
    private String specialSchemeCd;

    @Column(name = "application_id")
    @ApiObjectField(description = "applicationId")
    private String applicationId;

    @Column(name = "spt_app_scheme")
    @ApiObjectField(description = "sptAppScheme")
    private String sptAppScheme;

    @Column(name = "spt_sports")
    @ApiObjectField(description = "sptSports")
    private String sptSports;

    @Column(name = "spt_level")
    @ApiObjectField(description = "sptLevel")
    private String sptLevel;

    @Column(name = "spt_level_others")
    @ApiObjectField(description = "sptLevelOthers")
    private String sptLevelOthers;

    @Column(name = "spt_hyperlink")
    @ApiObjectField(description = "sptHyperlink")
    private String sptHyperlink;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpecialSchemeCd() {
        return specialSchemeCd;
    }

    public void setSpecialSchemeCd(String specialSchemeCd) {
        this.specialSchemeCd = specialSchemeCd;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getSptAppScheme() {
        return sptAppScheme;
    }

    public void setSptAppScheme(String sptAppScheme) {
        this.sptAppScheme = sptAppScheme;
    }

    public String getSptSports() {
        return sptSports;
    }

    public void setSptSports(String sptSports) {
        this.sptSports = sptSports;
    }

    public String getSptLevel() {
        return sptLevel;
    }

    public void setSptLevel(String sptLevel) {
        this.sptLevel = sptLevel;
    }

    public String getSptLevelOthers() {
        return sptLevelOthers;
    }

    public void setSptLevelOthers(String sptLevelOthers) {
        this.sptLevelOthers = sptLevelOthers;
    }

    public String getSptHyperlink() {
        return sptHyperlink;
    }

    public void setSptHyperlink(String sptHyperlink) {
        this.sptHyperlink = sptHyperlink;
    }
}
