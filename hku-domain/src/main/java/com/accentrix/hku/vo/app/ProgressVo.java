package com.accentrix.hku.vo.app;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年3月1日 上午10:28:28
 * @version 1.0
 */

public class ProgressVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String applicationId;

    private Boolean prsnalPart;

    private Boolean acadBg;

    private Boolean otherQuali;

    private Boolean choiceOfCurri;

    private Boolean expAndAchi;

    private Boolean reference;

    private Boolean others;

    private String createBy;

    private Date createDate;

    private String updateBy;

    private Date updateDate;

    private int version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public Boolean getPrsnalPart() {
        return prsnalPart;
    }

    public void setPrsnalPart(Boolean prsnalPart) {
        this.prsnalPart = prsnalPart;
    }

    public Boolean getAcadBg() {
        return acadBg;
    }

    public void setAcadBg(Boolean acadBg) {
        this.acadBg = acadBg;
    }

    public Boolean getOtherQuali() {
        return otherQuali;
    }

    public void setOtherQuali(Boolean otherQuali) {
        this.otherQuali = otherQuali;
    }

    public Boolean getChoiceOfCurri() {
        return choiceOfCurri;
    }

    public void setChoiceOfCurri(Boolean choiceOfCurri) {
        this.choiceOfCurri = choiceOfCurri;
    }

    public Boolean getExpAndAchi() {
        return expAndAchi;
    }

    public void setExpAndAchi(Boolean expAndAchi) {
        this.expAndAchi = expAndAchi;
    }

    public Boolean getReference() {
        return reference;
    }

    public void setReference(Boolean reference) {
        this.reference = reference;
    }

    public Boolean getOthers() {
        return others;
    }

    public void setOthers(Boolean others) {
        this.others = others;
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
}
