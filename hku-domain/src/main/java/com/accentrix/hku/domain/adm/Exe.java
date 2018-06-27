package com.accentrix.hku.domain.adm;

import java.io.Serializable;
import java.util.Date;

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
 * @date 创建时间：2018年1月30日 下午3:59:37
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "adm_exe")
@ApiObject(name = "Exe")
public class Exe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "adm_exe_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "admission_year")
    @ApiObjectField(description = "admissionYear")
    private Integer admissionYear;

    @Column(name = "application_start_date")
    @ApiObjectField(description = "applicationStartDate")
    private Date applicationStartDate;

    @Column(name = "application_end_date")
    @ApiObjectField(description = "applicationEndDate")
    private Date applicationEndDate;

    @Column(name = "programme_choice_end_date")
    @ApiObjectField(description = "programmeChoiceEndDate")
    private Date programmeChoiceEndDate;

    @Column(name = "mf_excellent_sche_end_date")
    @ApiObjectField(description = "mfExcellentScheEndDate")
    private Date mfExcellentScheEndDate;

    @Column(name = "adm_cycle_end_date")
    @ApiObjectField(description = "admCycleEndDate")
    private Date admCycleEndDate;

    @Column(name = "display_sixth_choice")
    @ApiObjectField(description = "displaySixthChoice")
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
