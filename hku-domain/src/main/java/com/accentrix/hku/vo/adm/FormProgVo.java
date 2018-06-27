package com.accentrix.hku.vo.adm;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午4:02:43
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class FormProgVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String admFormId;

    private String appHkuProgrammeId;

    private String appHkuProgrammeName;

    private Integer quotaLocal;

    private Integer quotaOverseas;

    private Integer levelOfEntry;

    private Integer quotaMainland;

    private Integer admissionYear;

    private String programmeCode;

    private String programmeTitle;

    private String faculty;

    private boolean progDisabled;

    private String local;

    private String overseas;

    private String mainland;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdmFormId() {
        return admFormId;
    }

    public void setAdmFormId(String admFormId) {
        this.admFormId = admFormId;
    }

    public String getAppHkuProgrammeId() {
        return appHkuProgrammeId;
    }

    public void setAppHkuProgrammeId(String appHkuProgrammeId) {
        this.appHkuProgrammeId = appHkuProgrammeId;
    }

    public Integer getQuotaLocal() {
        return quotaLocal;
    }

    public void setQuotaLocal(Integer quotaLocal) {
        this.quotaLocal = quotaLocal;
    }

    public Integer getQuotaOverseas() {
        return quotaOverseas;
    }

    public void setQuotaOverseas(Integer quotaOverseas) {
        this.quotaOverseas = quotaOverseas;
    }

    public Integer getLevelOfEntry() {
        return levelOfEntry;
    }

    public void setLevelOfEntry(Integer levelOfEntry) {
        this.levelOfEntry = levelOfEntry;
    }

    public Integer getQuotaMainland() {
        return quotaMainland;
    }

    public void setQuotaMainland(Integer quotaMainland) {
        this.quotaMainland = quotaMainland;
    }

    public boolean isProgDisabled() {
        return progDisabled;
    }

    public void setProgDisabled(boolean progDisabled) {
        this.progDisabled = progDisabled;
    }

    public Integer getAdmissionYear() {
        return admissionYear;
    }

    public void setAdmissionYear(Integer admissionYear) {
        this.admissionYear = admissionYear;
    }

    public String getProgrammeCode() {
        return programmeCode;
    }

    public void setProgrammeCode(String programmeCode) {
        this.programmeCode = programmeCode;
    }

    public String getProgrammeTitle() {
        return programmeTitle;
    }

    public void setProgrammeTitle(String programmeTitle) {
        this.programmeTitle = programmeTitle;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getAppHkuProgrammeName() {
        return appHkuProgrammeName;
    }

    public void setAppHkuProgrammeName(String appHkuProgrammeName) {
        this.appHkuProgrammeName = appHkuProgrammeName;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getOverseas() {
        return overseas;
    }

    public void setOverseas(String overseas) {
        this.overseas = overseas;
    }

    public String getMainland() {
        return mainland;
    }

    public void setMainland(String mainland) {
        this.mainland = mainland;
    }
}
