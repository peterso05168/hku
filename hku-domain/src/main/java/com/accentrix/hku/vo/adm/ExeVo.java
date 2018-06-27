package com.accentrix.hku.vo.adm;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午3:59:37
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ExeVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private Integer admissionYear;

    private Date applicationStartDate;

    private Date applicationEndDate;

    private Date programmeChoiceEndDate;

    private Date mfExcellentScheEndDate;

    private Date admCycleEndDate;

    private Boolean displaySixthChoice;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getAdmissionYear() {
        return admissionYear;
    }

    public void setAdmissionYear(Integer admissionYear) {
        this.admissionYear = admissionYear;
    }

    public Date getApplicationStartDate() {
        return applicationStartDate;
    }

    public void setApplicationStartDate(Date applicationStartDate) {
        this.applicationStartDate = applicationStartDate;
    }

    public Date getApplicationEndDate() {
        return applicationEndDate;
    }

    public void setApplicationEndDate(Date applicationEndDate) {
        this.applicationEndDate = applicationEndDate;
    }

    public Date getProgrammeChoiceEndDate() {
        return programmeChoiceEndDate;
    }

    public void setProgrammeChoiceEndDate(Date programmeChoiceEndDate) {
        this.programmeChoiceEndDate = programmeChoiceEndDate;
    }

    public Date getMfExcellentScheEndDate() {
        return mfExcellentScheEndDate;
    }

    public void setMfExcellentScheEndDate(Date mfExcellentScheEndDate) {
        this.mfExcellentScheEndDate = mfExcellentScheEndDate;
    }

    public Date getAdmCycleEndDate() {
        return admCycleEndDate;
    }

    public void setAdmCycleEndDate(Date admCycleEndDate) {
        this.admCycleEndDate = admCycleEndDate;
    }

    public Boolean getDisplaySixthChoice() {
        return displaySixthChoice;
    }

    public void setDisplaySixthChoice(Boolean displaySixthChoice) {
        this.displaySixthChoice = displaySixthChoice;
    }
}
