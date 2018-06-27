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
 * @date 创建时间：2018年1月30日 下午4:30:08
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "app_others")
@ApiObject(name = "Others")
public class Others implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "others_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "disability_type_cd")
    @ApiObjectField(description = "disabilityTypeCd")
    private String disabilityTypeCd;

    @Column(name = "deg_of_impairmnt")
    @ApiObjectField(description = "degOfImpairmnt")
    private String degOfImpairmnt;

    @Column(name = "description")
    @ApiObjectField(description = "description")
    private String description;

    @Column(name = "uni_no")
    @ApiObjectField(description = "uniNo")
    private String uniNo;

    @Column(name = "de_reg")
    @ApiObjectField(description = "deReg")
    private Boolean deReg;

    @Column(name = "de_reg_prog")
    @ApiObjectField(description = "deRegProg")
    private String deRegProg;

    @Column(name = "de_reg_year")
    @ApiObjectField(description = "deRegYear")
    private Integer deRegYear;

    @Column(name = "discontinued")
    @ApiObjectField(description = "discontinued")
    private Boolean discontinued;

    @Column(name = "discon_prog")
    @ApiObjectField(description = "disconProg")
    private String disconProg;

    @Column(name = "discon_year")
    @ApiObjectField(description = "disconYear")
    private Integer disconYear;

    @Column(name = "discon_uni_no")
    @ApiObjectField(description = "disconUniNo")
    private String disconUniNo;

    @Column(name = "not_success_app_submit")
    @ApiObjectField(description = "notSuccessAppSubmit")
    private Boolean notSuccessAppSubmit;

    @Column(name = "not_success_app_year")
    @ApiObjectField(description = "notSuccessAppYear")
    private Integer notSuccessAppYear;

    @Column(name = "not_success_app_uni_no")
    @ApiObjectField(description = "notSuccessAppUniNo")
    private String notSuccessAppUniNo;

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
}
