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
 * @date 创建时间：2018年1月30日 下午4:32:38
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "app_requirement")
@ApiObject(name = "Requirement")
public class Requirement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "app_requirement_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "type")
    @ApiObjectField(description = "type")
    private String type;

    @Column(name = "description")
    @ApiObjectField(description = "description")
    private String description;

    @Column(name = "obj_class_name")
    @ApiObjectField(description = "objClassName")
    private String objClassName;

    @Column(name = "obj_xml")
    @ApiObjectField(description = "objXml")
    private String objXml;

    @Column(name = "is_published")
    @ApiObjectField(description = "isPublished")
    private Boolean isPublished;

    @Column(name = "relationship")
    @ApiObjectField(description = "relationship")
    private String relationship;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getObjClassName() {
        return objClassName;
    }

    public void setObjClassName(String objClassName) {
        this.objClassName = objClassName;
    }

    public String getObjXml() {
        return objXml;
    }

    public void setObjXml(String objXml) {
        this.objXml = objXml;
    }

    public Boolean getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(Boolean isPublished) {
        this.isPublished = isPublished;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
}
