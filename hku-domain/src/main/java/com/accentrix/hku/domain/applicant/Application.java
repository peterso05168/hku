package com.accentrix.hku.domain.applicant;

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
 * @date 创建时间：2018年1月30日 下午6:12:12
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "applicant_application")
@ApiObject(name = "Application")
public class Application implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "application_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "person_id")
    @ApiObjectField(description = "personId")
    private String personId;

    @Column(name = "acad_bg_id")
    @ApiObjectField(description = "acadBgId")
    private String acadBgId;

    @Column(name = "reference_id")
    @ApiObjectField(description = "referenceId")
    private String referenceId;

    @Column(name = "others_id")
    @ApiObjectField(description = "othersId")
    private String othersId;

    @Column(name = "applicant_account_id")
    @ApiObjectField(description = "applicantAccountId")
    private String applicantAccountId;

    @Column(name = "acad_qual_housing_mgmt_id")
    @ApiObjectField(description = "acadQualHousingMgmtId")
    private String acadQualHousingMgmtId;

    @Column(name = "predicted_grade")
    @ApiObjectField(description = "predictedGrade")
    private String predictedGrade;

    @Column(name = "actual_grade")
    @ApiObjectField(description = "actualGrade")
    private String actualGrade;

    @Column(name = "adm_form_id")
    @ApiObjectField(description = "admFormId")
    private String admFormId;

    @Column(name = "scholar_heforshe")
    @ApiObjectField(description = "scholarHeforshe")
    private Boolean scholarHeforshe;

    @Column(name = "scholar_uwc")
    @ApiObjectField(description = "scholarUwc")
    private Boolean scholarUwc;

    @Column(name = "scholar_nigerian")
    @ApiObjectField(description = "scholarNigerian")
    private Boolean scholarNigerian;

    @Column(name = "scholar_vtp")
    @ApiObjectField(description = "scholarVtp")
    private Boolean scholarVtp;

    @Column(name = "scholar_afl")
    @ApiObjectField(description = "scholarAfl")
    private Boolean scholarAfl;

    @Column(name = "scholar_hksar_gsft")
    @ApiObjectField(description = "scholarHksarGsft")
    private Boolean scholarHksarGsft;

    @Column(name = "scholar_hksar_gsft_brs")
    @ApiObjectField(description = "scholarHksarGsftBrs")
    private Boolean scholarHksarGsftBrs;

    @Column(name = "acad_qual_nursing_id")
    @ApiObjectField(description = "acadQualNursingId")
    private String acadQualNursingId;

    @Column(name = "application_no")
    @ApiObjectField(description = "applicationNo")
    private String applicationNo;

    @Column(name = "status")
    @ApiObjectField(description = "status")
    private String status;

    @Column(name = "eng_req")
    @ApiObjectField(description = "engReq")
    private Boolean engReq;

    @Column(name = "ccai_g_interview")
    @ApiObjectField(description = "ccaigInterview")
    private String ccaigInterview;

    @Column(name = "ccai_i_interview")
    @ApiObjectField(description = "ccaiiInterview")
    private String ccaiiInterview;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getAcadBgId() {
        return acadBgId;
    }

    public void setAcadBgId(String acadBgId) {
        this.acadBgId = acadBgId;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getOthersId() {
        return othersId;
    }

    public void setOthersId(String othersId) {
        this.othersId = othersId;
    }

    public String getApplicantAccountId() {
        return applicantAccountId;
    }

    public void setApplicantAccountId(String applicantAccountId) {
        this.applicantAccountId = applicantAccountId;
    }

    public String getAcadQualHousingMgmtId() {
        return acadQualHousingMgmtId;
    }

    public void setAcadQualHousingMgmtId(String acadQualHousingMgmtId) {
        this.acadQualHousingMgmtId = acadQualHousingMgmtId;
    }

    public String getPredictedGrade() {
        return predictedGrade;
    }

    public void setPredictedGrade(String predictedGrade) {
        this.predictedGrade = predictedGrade;
    }

    public String getActualGrade() {
        return actualGrade;
    }

    public void setActualGrade(String actualGrade) {
        this.actualGrade = actualGrade;
    }

    public String getAdmFormId() {
        return admFormId;
    }

    public void setAdmFormId(String admFormId) {
        this.admFormId = admFormId;
    }

    public Boolean getScholarHeforshe() {
        return scholarHeforshe;
    }

    public void setScholarHeforshe(Boolean scholarHeforshe) {
        this.scholarHeforshe = scholarHeforshe;
    }

    public Boolean getScholarUwc() {
        return scholarUwc;
    }

    public void setScholarUwc(Boolean scholarUwc) {
        this.scholarUwc = scholarUwc;
    }

    public Boolean getScholarNigerian() {
        return scholarNigerian;
    }

    public void setScholarNigerian(Boolean scholarNigerian) {
        this.scholarNigerian = scholarNigerian;
    }

    public Boolean getScholarVtp() {
        return scholarVtp;
    }

    public void setScholarVtp(Boolean scholarVtp) {
        this.scholarVtp = scholarVtp;
    }

    public Boolean getScholarAfl() {
        return scholarAfl;
    }

    public void setScholarAfl(Boolean scholarAfl) {
        this.scholarAfl = scholarAfl;
    }

    public Boolean getScholarHksarGsft() {
        return scholarHksarGsft;
    }

    public void setScholarHksarGsft(Boolean scholarHksarGsft) {
        this.scholarHksarGsft = scholarHksarGsft;
    }

    public Boolean getScholarHksarGsftBrs() {
        return scholarHksarGsftBrs;
    }

    public void setScholarHksarGsftBrs(Boolean scholarHksarGsftBrs) {
        this.scholarHksarGsftBrs = scholarHksarGsftBrs;
    }

    public String getAcadQualNursingId() {
        return acadQualNursingId;
    }

    public void setAcadQualNursingId(String acadQualNursingId) {
        this.acadQualNursingId = acadQualNursingId;
    }

    public String getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getEngReq() {
        return engReq;
    }

    public void setEngReq(Boolean engReq) {
        this.engReq = engReq;
    }

    public String getCcaigInterview() {
        return ccaigInterview;
    }

    public void setCcaigInterview(String ccaigInterview) {
        this.ccaigInterview = ccaigInterview;
    }

    public String getCcaiiInterview() {
        return ccaiiInterview;
    }

    public void setCcaiiInterview(String ccaiiInterview) {
        this.ccaiiInterview = ccaiiInterview;
    }
}
