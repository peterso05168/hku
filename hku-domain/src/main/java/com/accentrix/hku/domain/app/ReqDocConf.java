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
 * @date 创建时间：2018年1月30日 下午4:32:26
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "app_req_doc_conf")
@ApiObject(name = "ReqDocConf")
public class ReqDocConf implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "app_req_doc_conf_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "req_doc_type")
    @ApiObjectField(description = "reqDocType")
    private String reqDocType;

    @Column(name = "req_doc_cd")
    @ApiObjectField(description = "reqDocCd")
    private String reqDocCd;

    @Column(name = "req_doc_name")
    @ApiObjectField(description = "reqDocName")
    private String reqDocName;

    @Column(name = "req_doc_name_chi")
    @ApiObjectField(description = "reqDocNameChi")
    private String reqDocNameChi;

    @Column(name = "tool_tip_msg")
    @ApiObjectField(description = "toolTipMsg")
    private String toolTipMsg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReqDocType() {
        return reqDocType;
    }

    public void setReqDocType(String reqDocType) {
        this.reqDocType = reqDocType;
    }

    public String getReqDocCd() {
        return reqDocCd;
    }

    public void setReqDocCd(String reqDocCd) {
        this.reqDocCd = reqDocCd;
    }

    public String getReqDocName() {
        return reqDocName;
    }

    public void setReqDocName(String reqDocName) {
        this.reqDocName = reqDocName;
    }

    public String getReqDocNameChi() {
        return reqDocNameChi;
    }

    public void setReqDocNameChi(String reqDocNameChi) {
        this.reqDocNameChi = reqDocNameChi;
    }

    public String getToolTipMsg() {
        return toolTipMsg;
    }

    public void setToolTipMsg(String toolTipMsg) {
        this.toolTipMsg = toolTipMsg;
    }
}
