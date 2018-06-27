package com.accentrix.hku.domain.app;

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

import com.accentrix.hku.domain.common.AuditedObject;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午4:31:08
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "app_programme_choice")
@ApiObject(name = "ProgrammeChoice")
public class ProgrammeChoice extends AuditedObject {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "programme_choice_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "adm_form_prog_id")
    @ApiObjectField(description = "admFormProgId")
    private String admFormProgId;

    @Column(name = "first_choice")
    @ApiObjectField(description = "firstChoice")
    private Boolean firstChoice;

    @Column(name = "application_id")
    @ApiObjectField(description = "applicationId")
    private String applicationId;

    @Column(name = "offer_status_cd")
    @ApiObjectField(description = "offerStatusCd")
    private String offerStatusCd;

    @Column(name = "is_withdrawn_approved")
    @ApiObjectField(description = "isWithdrawnApproved")
    private Boolean isWithdrawnApproved;

    @Column(name = "is_withdrawn")
    @ApiObjectField(description = "isWithdrawn")
    private Boolean isWithdrawn;

    @Column(name = "meet_req")
    @ApiObjectField(description = "meetReq")
    private Boolean meetReq;

    @Column(name = "prog_req")
    @ApiObjectField(description = "progReq")
    private Boolean progReq;

    @Column(name = "select_for_interview")
    @ApiObjectField(description = "selectForInterview")
    private Boolean selectForInterview;

    @Column(name = "prog_interview_score")
    @ApiObjectField(description = "progInterviewScore")
    private String progInterviewScore;

    @Column(name = "other_interview")
    @ApiObjectField(description = "otherInterview")
    private String otherInterview;

    @Column(name = "reply_deadline")
    @ApiObjectField(description = "replyDeadline")
    private Date replyDeadline;

    @Column(name = "replied_on")
    @ApiObjectField(description = "repliedOn")
    private Date repliedOn;

    @Column(name = "wdra_cd")
    @ApiObjectField(description = "wdraCd")
    private String wdraCd;

    @Column(name = "sis_number")
    @ApiObjectField(description = "sisNumber")
    private Integer sisNumber;

    @Column(name = "last_updated_by")
    @ApiObjectField(description = "lastUpdatedBy")
    private String lastUpdatedBy;

    @Column(name = "last_updated_date")
    @ApiObjectField(description = "lastUpdatedDate")
    private Date lastUpdatedDate;

    @Column(name = "assigned_quota_local")
    @ApiObjectField(description = "assignedQuotaLocal")
    private Integer assignedQuotaLocal;

    @Column(name = "assigned_quota_overseas")
    @ApiObjectField(description = "assignedQuotaOverseas")
    private Integer assignedQuotaOverseas;

    @Column(name = "assigned_quota_mainland")
    @ApiObjectField(description = "assignedQuotaMainland")
    private Integer assignedQuotaMainland;

    @Column(name = "assigned_to")
    @ApiObjectField(description = "assignedTo")
    private String assignedTo;

    @Column(name = "uni_comp_req")
    @ApiObjectField(description = "uniCompReq")
    private Boolean uniCompReq;

    @Column(name = "offer_type")
    @ApiObjectField(description = "offerType")
    private String offerType;

    @Column(name = "conditional_type")
    @ApiObjectField(description = "conditionalType")
    private String conditionalType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdmFormProgId() {
        return admFormProgId;
    }

    public void setAdmFormProgId(String admFormProgId) {
        this.admFormProgId = admFormProgId;
    }

    public Boolean getFirstChoice() {
        return firstChoice;
    }

    public void setFirstChoice(Boolean firstChoice) {
        this.firstChoice = firstChoice;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getOfferStatusCd() {
        return offerStatusCd;
    }

    public void setOfferStatusCd(String offerStatusCd) {
        this.offerStatusCd = offerStatusCd;
    }

    public Boolean getIsWithdrawnApproved() {
        return isWithdrawnApproved;
    }

    public void setIsWithdrawnApproved(Boolean isWithdrawnApproved) {
        this.isWithdrawnApproved = isWithdrawnApproved;
    }

    public Boolean getIsWithdrawn() {
        return isWithdrawn;
    }

    public void setIsWithdrawn(Boolean isWithdrawn) {
        this.isWithdrawn = isWithdrawn;
    }

    public Boolean getMeetReq() {
        return meetReq;
    }

    public void setMeetReq(Boolean meetReq) {
        this.meetReq = meetReq;
    }

    public Boolean getProgReq() {
        return progReq;
    }

    public void setProgReq(Boolean progReq) {
        this.progReq = progReq;
    }

    public Boolean getSelectForInterview() {
        return selectForInterview;
    }

    public void setSelectForInterview(Boolean selectForInterview) {
        this.selectForInterview = selectForInterview;
    }

    public String getProgInterviewScore() {
        return progInterviewScore;
    }

    public void setProgInterviewScore(String progInterviewScore) {
        this.progInterviewScore = progInterviewScore;
    }

    public String getOtherInterview() {
        return otherInterview;
    }

    public void setOtherInterview(String otherInterview) {
        this.otherInterview = otherInterview;
    }

    public Date getReplyDeadline() {
        return replyDeadline;
    }

    public void setReplyDeadline(Date replyDeadline) {
        this.replyDeadline = replyDeadline;
    }

    public Date getRepliedOn() {
        return repliedOn;
    }

    public void setRepliedOn(Date repliedOn) {
        this.repliedOn = repliedOn;
    }

    public String getWdraCd() {
        return wdraCd;
    }

    public void setWdraCd(String wdraCd) {
        this.wdraCd = wdraCd;
    }

    public Integer getSisNumber() {
        return sisNumber;
    }

    public void setSisNumber(Integer sisNumber) {
        this.sisNumber = sisNumber;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Integer getAssignedQuotaLocal() {
        return assignedQuotaLocal;
    }

    public void setAssignedQuotaLocal(Integer assignedQuotaLocal) {
        this.assignedQuotaLocal = assignedQuotaLocal;
    }

    public Integer getAssignedQuotaOverseas() {
        return assignedQuotaOverseas;
    }

    public void setAssignedQuotaOverseas(Integer assignedQuotaOverseas) {
        this.assignedQuotaOverseas = assignedQuotaOverseas;
    }

    public Integer getAssignedQuotaMainland() {
        return assignedQuotaMainland;
    }

    public void setAssignedQuotaMainland(Integer assignedQuotaMainland) {
        this.assignedQuotaMainland = assignedQuotaMainland;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public Boolean getUniCompReq() {
        return uniCompReq;
    }

    public void setUniCompReq(Boolean uniCompReq) {
        this.uniCompReq = uniCompReq;
    }

    public String getOfferType() {
        return offerType;
    }

    public void setOfferType(String offerType) {
        this.offerType = offerType;
    }

    public String getConditionalType() {
        return conditionalType;
    }

    public void setConditionalType(String conditionalType) {
        this.conditionalType = conditionalType;
    }

}
