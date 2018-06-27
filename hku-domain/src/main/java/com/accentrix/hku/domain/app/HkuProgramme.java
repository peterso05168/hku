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
 * @date 创建时间：2018年1月30日 下午4:29:37
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "app_hku_programme")
@ApiObject(name = "HkuProgramme")
public class HkuProgramme implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "hku_programme_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "hku_programme_cd")
    @ApiObjectField(description = "hkuProgrammeCd")
    private String hkuProgrammeCd;

    @Column(name = "hku_programme_desc")
    @ApiObjectField(description = "hkuProgrammeDesc")
    private String hkuProgrammeDesc;

    @Column(name = "hku_programme_desc_chi")
    @ApiObjectField(description = "hkuProgrammeDescChi")
    private String hkuProgrammeDescChi;

    @Column(name = "academic_year")
    @ApiObjectField(description = "academicYear")
    private String academicYear;

    @Column(name = "enabled")
    @ApiObjectField(description = "enabled")
    private Boolean enabled;

    @Column(name = "faculty_cd")
    @ApiObjectField(description = "facultyCd")
    private String facultyCd;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHkuProgrammeCd() {
        return hkuProgrammeCd;
    }

    public void setHkuProgrammeCd(String hkuProgrammeCd) {
        this.hkuProgrammeCd = hkuProgrammeCd;
    }

    public String getHkuProgrammeDesc() {
        return hkuProgrammeDesc;
    }

    public void setHkuProgrammeDesc(String hkuProgrammeDesc) {
        this.hkuProgrammeDesc = hkuProgrammeDesc;
    }

    public String getHkuProgrammeDescChi() {
        return hkuProgrammeDescChi;
    }

    public void setHkuProgrammeDescChi(String hkuProgrammeDescChi) {
        this.hkuProgrammeDescChi = hkuProgrammeDescChi;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getFacultyCd() {
        return facultyCd;
    }

    public void setFacultyCd(String facultyCd) {
        this.facultyCd = facultyCd;
    }
}
