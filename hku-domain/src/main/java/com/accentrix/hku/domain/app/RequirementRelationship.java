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
 * @date 创建时间：2018年1月30日 下午4:32:54
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "app_requirement_relationship")
@ApiObject(name = "RequirementRelationship")
public class RequirementRelationship implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "app_requirement_relationship_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "parent_requirement_id")
    @ApiObjectField(description = "parentRequirementId")
    private String parentRequirementId;

    @Column(name = "child_requirement_id")
    @ApiObjectField(description = "childRequirementId")
    private String childRequirementId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentRequirementId() {
        return parentRequirementId;
    }

    public void setParentRequirementId(String parentRequirementId) {
        this.parentRequirementId = parentRequirementId;
    }

    public String getChildRequirementId() {
        return childRequirementId;
    }

    public void setChildRequirementId(String childRequirementId) {
        this.childRequirementId = childRequirementId;
    }
}
