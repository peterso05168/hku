package com.accentrix.hku.vo.app;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午4:29:37
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class HkuProgrammeVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String hkuProgrammeCd;

    private String hkuProgrammeDesc;

    private String hkuProgrammeDescChi;

    private String academicYear;

    private Boolean enabled;

    private String facultyCd;

    private boolean isHkuProgrammeId;

    private boolean hkuProgDisabled;

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

    public boolean getIsHkuProgrammeId() {
        return isHkuProgrammeId;
    }

    public void setIsHkuProgrammeId(boolean isHkuProgrammeId) {
        this.isHkuProgrammeId = isHkuProgrammeId;
    }

    public boolean getHkuProgDisabled() {
        return hkuProgDisabled;
    }

    public void setHkuProgDisabled(boolean hkuProgDisabled) {
        this.hkuProgDisabled = hkuProgDisabled;
    }
}
