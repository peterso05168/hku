package com.accentrix.hku.vo.app;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午4:32:12
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ReqDocVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String applicationId;

    private String statusCd;

    private String statusBgColor;

    private Date submissionDate;

    private Date submissionDueDate;

    private String remark;

    private String reqDocConfId;

    private String qualificationId;

    private String reqDocName;

    private String toolTipMsg;

    private String uploadedDocId;

    private String fileName;

    private String displayFileName;

    private String filePath;

    public String getStatusBgColor() {
        if ("waitingforupload".equals(statusCd) || "rejected".equals(statusCd))
            statusBgColor = "grey";
        else if ("reviewing".equals(statusCd) || "verified".equals(statusCd))
            statusBgColor = "blue";
        else if ("upload".equals(statusCd))
            statusBgColor = "green";
        return statusBgColor;
    }

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

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    public Date getSubmissionDueDate() {
        return submissionDueDate;
    }

    public void setSubmissionDueDate(Date submissionDueDate) {
        this.submissionDueDate = submissionDueDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getReqDocName() {
        return reqDocName;
    }

    public void setReqDocName(String reqDocName) {
        this.reqDocName = reqDocName;
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

    public String getUploadedDocId() {
        return uploadedDocId;
    }

    public void setUploadedDocId(String uploadedDocId) {
        this.uploadedDocId = uploadedDocId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getToolTipMsg() {
        return toolTipMsg;
    }

    public void setToolTipMsg(String toolTipMsg) {
        this.toolTipMsg = toolTipMsg;
    }

    public String getDisplayFileName() {
        return displayFileName;
    }

    public void setDisplayFileName(String displayFileName) {
        this.displayFileName = displayFileName;
    }
}
