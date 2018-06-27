package com.accentrix.hku.domain.audit;

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
 * @date 创建时间：2018年3月5日 下午2:00:33
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "audit_log")
@ApiObject(name = "AuditLog")
public class AuditLog extends AuditedObject {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "applicant_account_id")
    @ApiObjectField(description = "applicantAccountId")
    private String applicantAccountId;

    @Column(name = "msg")
    @ApiObjectField(description = "msg")
    private String msg;

    @Column(name = "level")
    @ApiObjectField(description = "level")
    private Integer level;

    @Column(name = "type_cd")
    @ApiObjectField(description = "typeCd")
    private String typeCd;

    @Column(name = "status_cd")
    @ApiObjectField(description = "statusCd")
    private String statusCd;

    @Column(name = "is_deleted")
    @ApiObjectField(description = "isDeleted")
    private Boolean isDeleted;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApplicantAccountId() {
        return applicantAccountId;
    }

    public void setApplicantAccountId(String applicantAccountId) {
        this.applicantAccountId = applicantAccountId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getTypeCd() {
        return typeCd;
    }

    public void setTypeCd(String typeCd) {
        this.typeCd = typeCd;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd;
    }
}
