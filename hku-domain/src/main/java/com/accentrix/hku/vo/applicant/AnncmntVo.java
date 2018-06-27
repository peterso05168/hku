package com.accentrix.hku.vo.applicant;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.accentrix.hku.constant.Constants;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午6:11:40
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class AnncmntVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String typeCd;

    private String value;

    private String msgContent;

    private String statusCd;

    private Date issueDate;

    private String applicantAccountId;

    private String applicationNo;

    private Boolean isRead;

    private String color;

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

    public String getColor() {
        if (Constants.ANNCMNT_STATUS_NEW.equals(statusCd))
            color = "blue";
        if (Constants.ANNCMNT_STATUS_UPDATED.equals(statusCd))
            color = "green";
        if (Constants.ANNCMNT_STATUS_URGENT.equals(statusCd))
            color = "red blinker-me";
        if (Constants.ANNCMNT_STATUS_PROCESSING.equals(statusCd))
            color = "purple";
        return color;
    }
}
