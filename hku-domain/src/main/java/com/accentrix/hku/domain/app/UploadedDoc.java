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
 * @date 创建时间：2018年1月30日 下午4:33:41
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "app_uploaded_doc")
@ApiObject(name = "UploadedDoc")
public class UploadedDoc implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "app_uploaded_doc_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "req_doc_id")
    @ApiObjectField(description = "reqDocId")
    private String reqDocId;

    @Column(name = "file_name")
    @ApiObjectField(description = "fileName")
    private String fileName;

    @Column(name = "display_file_name")
    @ApiObjectField(description = "displayFileName")
    private String displayFileName;

    @Column(name = "file_path")
    @ApiObjectField(description = "filePath")
    private String filePath;

    @Column(name = "active")
    @ApiObjectField(description = "active")
    private Boolean active;

    @Column(name = "submission_date")
    @ApiObjectField(description = "submissionDate")
    private Date submissionDate;

    @Column(name = "remark")
    @ApiObjectField(description = "remark")
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReqDocId() {
        return reqDocId;
    }

    public void setReqDocId(String reqDocId) {
        this.reqDocId = reqDocId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDisplayFileName() {
        return displayFileName;
    }

    public void setDisplayFileName(String displayFileName) {
        this.displayFileName = displayFileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
