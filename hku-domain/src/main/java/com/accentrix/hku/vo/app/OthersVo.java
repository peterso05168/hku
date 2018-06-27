package com.accentrix.hku.vo.app;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午4:30:08
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class OthersVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String disabilityTypeCd;

    private String degOfImpairmnt;

    private String description;

    private String uniNo;

    private Boolean deReg;

    private String deRegProg;

    private Integer deRegYear;

    private Boolean discontinued;

    private String disconProg;

    private Integer disconYear;

    private String disconUniNo;

    private Boolean notSuccessAppSubmit;

    private Integer notSuccessAppYear;

    private String notSuccessAppUniNo;

    private String inputDeRegYear;

    private String inputDisconYear;

    private String inputNotSuccessAppYear;

    private boolean isSubmitted;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisabilityTypeCd() {
        return disabilityTypeCd;
    }

    public void setDisabilityTypeCd(String disabilityTypeCd) {
        this.disabilityTypeCd = disabilityTypeCd;
    }

    public String getDegOfImpairmnt() {
        return degOfImpairmnt;
    }

    public void setDegOfImpairmnt(String degOfImpairmnt) {
        this.degOfImpairmnt = degOfImpairmnt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUniNo() {
        return uniNo;
    }

    public void setUniNo(String uniNo) {
        this.uniNo = uniNo;
    }

    public Boolean getDeReg() {
        return deReg;
    }

    public void setDeReg(Boolean deReg) {
        this.deReg = deReg;
    }

    public String getDeRegProg() {
        return deRegProg;
    }

    public void setDeRegProg(String deRegProg) {
        this.deRegProg = deRegProg;
    }

    public Integer getDeRegYear() {
        return deRegYear;
    }

    public void setDeRegYear(Integer deRegYear) {
        this.deRegYear = deRegYear;
    }

    public Boolean getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(Boolean discontinued) {
        this.discontinued = discontinued;
    }

    public String getDisconProg() {
        return disconProg;
    }

    public void setDisconProg(String disconProg) {
        this.disconProg = disconProg;
    }

    public Integer getDisconYear() {
        return disconYear;
    }

    public void setDisconYear(Integer disconYear) {
        this.disconYear = disconYear;
    }

    public String getDisconUniNo() {
        return disconUniNo;
    }

    public void setDisconUniNo(String disconUniNo) {
        this.disconUniNo = disconUniNo;
    }

    public Boolean getNotSuccessAppSubmit() {
        return notSuccessAppSubmit;
    }

    public void setNotSuccessAppSubmit(Boolean notSuccessAppSubmit) {
        this.notSuccessAppSubmit = notSuccessAppSubmit;
    }

    public Integer getNotSuccessAppYear() {
        return notSuccessAppYear;
    }

    public void setNotSuccessAppYear(Integer notSuccessAppYear) {
        this.notSuccessAppYear = notSuccessAppYear;
    }

    public String getNotSuccessAppUniNo() {
        return notSuccessAppUniNo;
    }

    public void setNotSuccessAppUniNo(String notSuccessAppUniNo) {
        this.notSuccessAppUniNo = notSuccessAppUniNo;
    }

    public String getInputDeRegYear() {
        return inputDeRegYear;
    }

    public void setInputDeRegYear(String inputDeRegYear) {
        this.inputDeRegYear = inputDeRegYear;
    }

    public String getInputDisconYear() {
        return inputDisconYear;
    }

    public void setInputDisconYear(String inputDisconYear) {
        this.inputDisconYear = inputDisconYear;
    }

    public String getInputNotSuccessAppYear() {
        return inputNotSuccessAppYear;
    }

    public void setInputNotSuccessAppYear(String inputNotSuccessAppYear) {
        this.inputNotSuccessAppYear = inputNotSuccessAppYear;
    }

    public boolean getIsSubmitted() {
        return isSubmitted;
    }

    public void setIsSubmitted(boolean isSubmitted) {
        this.isSubmitted = isSubmitted;
    }
}
