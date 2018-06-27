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
 * @date 创建时间：2018年1月30日 下午6:12:39
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "applicant_msg")
@ApiObject(name = "Message")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "applicant_msg_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "from")
    @ApiObjectField(description = "from")
    private String from;

    @Column(name = "to")
    @ApiObjectField(description = "to")
    private String to;

    @Column(name = "msg_content")
    @ApiObjectField(description = "msgContent")
    private String msgContent;

    @Column(name = "timestamp")
    @ApiObjectField(description = "timestamp")
    private Date timestamp;

    @Column(name = "applicant_account_id")
    @ApiObjectField(description = "applicantAccountId")
    private String applicantAccountId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getApplicantAccountId() {
        return applicantAccountId;
    }

    public void setApplicantAccountId(String applicantAccountId) {
        this.applicantAccountId = applicantAccountId;
    }
}
