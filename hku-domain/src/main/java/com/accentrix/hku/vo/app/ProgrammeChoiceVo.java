package com.accentrix.hku.vo.app;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.accentrix.hku.vo.general.RefCdVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午4:31:08
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ProgrammeChoiceVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String admFormProgId;

    private String hkuProgrammeCode;

    private Boolean firstChoice;

    private String applicationId;

    private String offerStatusCd;

    private Boolean isWithdrawnApproved;

    private String createBy;

    private Date createDate;

    private String updateBy;

    private Date updateDate;

    private int version;

    private RefCdVo refCd;

    private String statusValue;

    private String statusValueChi;

    private Boolean isWithdrawn;

    private Boolean meetReq;
    private String meetReqStr;

    private Boolean progReq;

    private Boolean selectForInterview;

    private String progInterviewScore;

    private String otherInterview;

    private String hkuProgrammeId;

    private String applicationNo;

    private Boolean engReq;
    private String engReqStr;

    private Date replyDeadline;

    private Date repliedOn;

    private String hkuProgrammeDesc;

    private String wdraCd;

    private Integer sisNumber;

    private String lastUpdatedBy;

    private Date lastUpdatedDate;

    private Integer assignedQuotaLocal;

    private Integer assignedQuotaOverseas;

    private Integer assignedQuotaMainland;

    private String assignedTo;

    private Boolean uniCompReq;

    private String offerType;

    private String conditionalType;

    private String applicationStatus;

    private String ccaigInterview;

    private String ccaiiInterview;

    private String region;

    private List<BestExamSubjRsltVo> actualGCEs;

    private List<BestExamSubjRsltVo> predictedActualGCEs;

    private IbdBestExamVo actualIbd42;

    private IbdBestExamVo actualIbd45;

    private IbdBestExamVo predictedActualIbd42;

    private IbdBestExamVo predictedActualIbd45;

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

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getHkuProgrammeCode() {
        return hkuProgrammeCode;
    }

    public void setHkuProgrammeCode(String hkuProgrammeCode) {
        this.hkuProgrammeCode = hkuProgrammeCode;
    }

    public RefCdVo getRefCd() {
        return refCd;
    }

    public void setRefCd(RefCdVo refCd) {
        this.refCd = refCd;
    }

    public String getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }

    public String getStatusValueChi() {
        return statusValueChi;
    }

    public void setStatusValueChi(String statusValueChi) {
        this.statusValueChi = statusValueChi;
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

    public String getHkuProgrammeId() {
        return hkuProgrammeId;
    }

    public void setHkuProgrammeId(String hkuProgrammeId) {
        this.hkuProgrammeId = hkuProgrammeId;
    }

    public String getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public Boolean getEngReq() {
        return engReq;
    }

    public void setEngReq(Boolean engReq) {
        this.engReq = engReq;
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

    public String getHkuProgrammeDesc() {
        return hkuProgrammeDesc;
    }

    public void setHkuProgrammeDesc(String hkuProgrammeDesc) {
        this.hkuProgrammeDesc = hkuProgrammeDesc;
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

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public List<BestExamSubjRsltVo> getActualGCEs() {
        return actualGCEs;
    }

    public void setActualGCEs(List<BestExamSubjRsltVo> actualGCEs) {
        this.actualGCEs = actualGCEs;
    }

    public List<BestExamSubjRsltVo> getPredictedActualGCEs() {
        return predictedActualGCEs;
    }

    public void setPredictedActualGCEs(List<BestExamSubjRsltVo> predictedActualGCEs) {
        this.predictedActualGCEs = predictedActualGCEs;
    }

    public IbdBestExamVo getActualIbd42() {
        return actualIbd42;
    }

    public void setActualIbd42(IbdBestExamVo actualIbd42) {
        this.actualIbd42 = actualIbd42;
    }

    public IbdBestExamVo getActualIbd45() {
        return actualIbd45;
    }

    public void setActualIbd45(IbdBestExamVo actualIbd45) {
        this.actualIbd45 = actualIbd45;
    }

    public IbdBestExamVo getPredictedActualIbd42() {
        return predictedActualIbd42;
    }

    public void setPredictedActualIbd42(IbdBestExamVo predictedActualIbd42) {
        this.predictedActualIbd42 = predictedActualIbd42;
    }

    public IbdBestExamVo getPredictedActualIbd45() {
        return predictedActualIbd45;
    }

    public void setPredictedActualIbd45(IbdBestExamVo predictedActualIbd45) {
        this.predictedActualIbd45 = predictedActualIbd45;
    }

    public String getMeetReqStr() {
        return meetReqStr;
    }

    public void setMeetReqStr(String meetReqStr) {
        this.meetReqStr = meetReqStr;
    }

    public String getEngReqStr() {
        return engReqStr;
    }

    public void setEngReqStr(String engReqStr) {
        this.engReqStr = engReqStr;
    }
}
