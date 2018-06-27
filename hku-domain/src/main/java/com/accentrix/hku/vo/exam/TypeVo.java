package com.accentrix.hku.vo.exam;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午6:31:30
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class TypeVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String examCd;

    private String examDesc;

    private String examDescChi;

    private Integer yearStart;

    private Integer yearEnd;

    private String provinceDesc;

    private boolean isActiveIB;

    private boolean isActiveGCE;

    private boolean isActiveSAT;

    private boolean isActiveIT;

    private boolean isActiveNJCEE;

    private boolean disabled;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExamCd() {
        return examCd;
    }

    public void setExamCd(String examCd) {
        this.examCd = examCd;
    }

    public String getExamDesc() {
        return examDesc;
    }

    public void setExamDesc(String examDesc) {
        this.examDesc = examDesc;
    }

    public String getExamDescChi() {
        return examDescChi;
    }

    public void setExamDescChi(String examDescChi) {
        this.examDescChi = examDescChi;
    }

    public Integer getYearStart() {
        return yearStart;
    }

    public void setYearStart(Integer yearStart) {
        this.yearStart = yearStart;
    }

    public Integer getYearEnd() {
        return yearEnd;
    }

    public void setYearEnd(Integer yearEnd) {
        this.yearEnd = yearEnd;
    }

    public String getProvinceDesc() {
        return provinceDesc;
    }

    public void setProvinceDesc(String provinceDesc) {
        this.provinceDesc = provinceDesc;
    }

    public boolean getIsActiveIB() {
        return isActiveIB;
    }

    public void setIsActiveIB(boolean isActiveIB) {
        this.isActiveIB = isActiveIB;
    }

    public boolean getIsActiveGCE() {
        return isActiveGCE;
    }

    public void setIsActiveGCE(boolean isActiveGCE) {
        this.isActiveGCE = isActiveGCE;
    }

    public boolean getIsActiveSAT() {
        return isActiveSAT;
    }

    public void setIsActiveSAT(boolean isActiveSAT) {
        this.isActiveSAT = isActiveSAT;
    }

    public boolean getIsActiveIT() {
        return isActiveIT;
    }

    public void setIsActiveIT(boolean isActiveIT) {
        this.isActiveIT = isActiveIT;
    }

    public boolean getIsActiveNJCEE() {
        return isActiveNJCEE;
    }

    public void setIsActiveNJCEE(boolean isActiveNJCEE) {
        this.isActiveNJCEE = isActiveNJCEE;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}
