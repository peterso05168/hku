package com.accentrix.hku.vo.applicant;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.accentrix.hku.vo.app.ProgrammeChoiceVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午6:12:12
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ApplicationVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String personId;

    private String acadBgId;

    private String referenceId;

    private String othersId;

    private String applicantAccountId;

    private String acadQualHousingMgmtId;

    private String predictedGrade;

    private String actualGrade;

    private String admFormId;

    private Boolean scholarHeforshe;

    private Boolean scholarUwc;

    private Boolean scholarNigerian;

    private Boolean scholarVtp;

    private Boolean scholarAfl;

    private Boolean scholarHksarGsft;

    private Boolean scholarHksarGsftBrs;

    private String acadQualNursingId;

    private String applicationNo;

    private String status;

    private String applicantName;

    private String type;

    private String admissionYear;

    private String applicantId;

    private String approvalId;

    private boolean isRemoveQualification;

    private boolean isRemoveProgChoice;

    private Boolean engReq;

    private List<ProgrammeChoiceVo> programmeChoiceVos;

    private String year;

    private Long progress;

    private String num;

    private String ccaigInterview;

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

    public Boolean getScholarHeforshe() {
        return scholarHeforshe;
    }

    public void setScholarHeforshe(Boolean scholarHeforshe) {
        this.scholarHeforshe = scholarHeforshe;
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

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAdmissionYear() {
        return admissionYear;
    }

    public void setAdmissionYear(String admissionYear) {
        this.admissionYear = admissionYear;
    }

    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    public String getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(String approvalId) {
        this.approvalId = approvalId;
    }

    public boolean getIsRemoveQualification() {
        return isRemoveQualification;
    }

    public void setIsRemoveQualification(boolean isRemoveQualification) {
        this.isRemoveQualification = isRemoveQualification;
    }

    public boolean getIsRemoveProgChoice() {
        return isRemoveProgChoice;
    }

    public void setIsRemoveProgChoice(boolean isRemoveProgChoice) {
        this.isRemoveProgChoice = isRemoveProgChoice;
    }

    public Boolean getEngReq() {
        return engReq;
    }

    public void setEngReq(Boolean engReq) {
        this.engReq = engReq;
    }

    public List<ProgrammeChoiceVo> getProgrammeChoiceVos() {
        return programmeChoiceVos;
    }

    public void setProgrammeChoiceVos(List<ProgrammeChoiceVo> programmeChoiceVos) {
        this.programmeChoiceVos = programmeChoiceVos;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Long getProgress() {
        return progress;
    }

    public void setProgress(Long progress) {
        this.progress = progress;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
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
