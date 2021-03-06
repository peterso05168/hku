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
 * @date 创建时间：2018年1月30日 下午4:30:53
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "app_prog_requirement")
@ApiObject(name = "ProgRequirement")
public class ProgRequirement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "app_prog_requirement_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "app_hku_programme_id")
    @ApiObjectField(description = "appHkuProgrammeId")
    private String appHkuProgrammeId;

    @Column(name = "app_requirement_id")
    @ApiObjectField(description = "appRequirementId")
    private String appRequirementId;

    @Column(name = "relationship")
    @ApiObjectField(description = "relationship")
    private String relationship;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppHkuProgrammeId() {
        return appHkuProgrammeId;
    }

    public void setAppHkuProgrammeId(String appHkuProgrammeId) {
        this.appHkuProgrammeId = appHkuProgrammeId;
    }

    public String getAppRequirementId() {
        return appRequirementId;
    }

    public void setAppRequirementId(String appRequirementId) {
        this.appRequirementId = appRequirementId;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
}
