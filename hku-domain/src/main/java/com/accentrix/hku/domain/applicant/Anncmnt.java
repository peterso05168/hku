package com.accentrix.hku.domain.applicant;

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
 * @date 创建时间：2018年1月30日 下午6:11:40
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "applicant_anncmnt")
@ApiObject(name = "Anncmnt")
public class Anncmnt implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "type_cd")
    @ApiObjectField(description = "typeCd")
    private String typeCd;

    @Column(name = "value")
    @ApiObjectField(description = "value")
    private String value;

    @Column(name = "msg_content")
    @ApiObjectField(description = "msgContent")
    private String msgContent;

    @Column(name = "status_cd")
    @ApiObjectField(description = "statusCd")
    private String statusCd;

    @Column(name = "issue_date")
    @ApiObjectField(description = "issueDate")
    private Date issueDate;

    @Column(name = "applicant_account_id")
    @ApiObjectField(description = "applicantAccountId")
    private String applicantAccountId;

    @Column(name = "application_no")
    @ApiObjectField(description = "applicationNo")
    private String applicationNo;

    @Column(name = "is_read")
    @ApiObjectField(description = "isRead")
    private Boolean isRead;

    @Column(name = "application_id")
    @ApiObjectField(description = "applicationId")
    private String applicationId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeCd() {
        return typeCd;
    }

    public void setTypeCd(String typeCd) {
        this.typeCd = typeCd;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getApplicantAccountId() {
        return applicantAccountId;
    }

    public void setApplicantAccountId(String applicantAccountId) {
        this.applicantAccountId = applicantAccountId;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public String getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }
}
