package com.accentrix.hku.vo.payment;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年5月14日 上午10:16:15
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class PaymentDR implements Serializable {

    private static final long serialVersionUID = 1L;

    private int respCode;
    private String invoiceNum;
    private String apprCode;
    private String rrNo;
    private String addnResp;
    private String status;
    private String tamt;

    public int getRespCode() {
        return respCode;
    }

    public void setRespCode(int respCode) {
        this.respCode = respCode;
    }

    public String getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(String invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public String getApprCode() {
        return apprCode;
    }

    public void setApprCode(String apprCode) {
        this.apprCode = apprCode;
    }

    public String getRrNo() {
        return rrNo;
    }

    public void setRrNo(String rrNo) {
        this.rrNo = rrNo;
    }

    public String getAddnResp() {
        return addnResp;
    }

    public void setAddnResp(String addnResp) {
        this.addnResp = addnResp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTamt() {
        return tamt;
    }

    public void setTamt(String tamt) {
        this.tamt = tamt;
    }
}
