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
 * @date 创建时间：2018年1月30日 下午4:32:12
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "app_req_doc")
@ApiObject(name = "ReqDoc")
public class ReqDoc implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "app_req_doc_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "application_id")
    @ApiObjectField(description = "applicationId")
    private String applicationId;

    @Column(name = "status_cd")
    @ApiObjectField(description = "statusCd")
    private String statusCd;

    @Column(name = "submission_due_date")
    @ApiObjectField(description = "submissionDueDate")
    private Date submissionDueDate;

    @Column(name = "req_doc_conf_id")
    @ApiObjectField(description = "reqDocConfId")
    private String reqDocConfId;

    @Column(name = "app_qualification_id")
    @ApiObjectField(description = "qualificationId")
    private String qualificationId;

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

    public String getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd;
    }

    public Date getSubmissionDueDate() {
        return submissionDueDate;
    }

    public void setSubmissionDueDate(Date submissionDueDate) {
        this.submissionDueDate = submissionDueDate;
    }

    public String getReqDocConfId() {
        return reqDocConfId;
    }

    public void setReqDocConfId(String reqDocConfId) {
        this.reqDocConfId = reqDocConfId;
    }

    public String getQualificationId() {
        return qualificationId;
    }

    public void setQualificationId(String qualificationId) {
        this.qualificationId = qualificationId;
    }
}
