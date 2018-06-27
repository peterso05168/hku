package com.accentrix.hku.domain.app;

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
 * @date 创建时间：2018年1月30日 下午4:31:47
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "app_referee")
@ApiObject(name = "Referee")
public class Referee extends AuditedObject {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "referee_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "reference_id")
    @ApiObjectField(description = "referenceId")
    private String referenceId;

    @Column(name = "referee_relationship_cd")
    @ApiObjectField(description = "refereeRelationshipCd")
    private String refereeRelationshipCd;

    @Column(name = "name")
    @ApiObjectField(description = "name")
    private String name;

    @Column(name = "email")
    @ApiObjectField(description = "email")
    private String email;

    @Column(name = "is_deleted")
    @ApiObjectField(description = "isDeleted")
    private Boolean isDeleted;

    @Column(name = "counselor_id")
    @ApiObjectField(description = "counselorId")
    private String counselorId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getRefereeRelationshipCd() {
        return refereeRelationshipCd;
    }

    public void setRefereeRelationshipCd(String refereeRelationshipCd) {
        this.refereeRelationshipCd = refereeRelationshipCd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getCounselorId() {
        return counselorId;
    }

    public void setCounselorId(String counselorId) {
        this.counselorId = counselorId;
    }
}
