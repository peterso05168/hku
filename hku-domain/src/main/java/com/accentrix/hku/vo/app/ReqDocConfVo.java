package com.accentrix.hku.vo.app;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午4:32:26
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ReqDocConfVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String reqDocType;

    private String reqDocCd;

    private String reqDocName;

    private String reqDocNameChi;

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
