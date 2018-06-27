package com.accentrix.hku.domain.scholar;

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
 * @date 创建时间：2018年3月19日 上午11:09:19
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "scholar_dtl_requirement")
@ApiObject(name = "ScholarDtlRequirement")
public class ScholarDtlRequirement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "scholar_dtl_requirement_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "scholar_dtl_id")
    @ApiObjectField(description = "scholarDtlId")
    private String scholarDtlId;

    @Column(name = "requirement_id")
    @ApiObjectField(description = "requirementId")
    private String requirementId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScholarDtlId() {
        return scholarDtlId;
    }

    public void setScholarDtlId(String scholarDtlId) {
        this.scholarDtlId = scholarDtlId;
    }

    public String getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(String requirementId) {
        this.requirementId = requirementId;
    }
}
