package com.accentrix.hku.vo.app;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午4:28:23
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class AcadQualHousingMgmtVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private Date periodFrom;

    private Date periodTo;

    private Date hmAwardDate;

    private String positionHeld;

    private Date startingDate;

    private String nameAndAddr;

    private boolean isSelectHousingMgmt;

    private boolean saveHousingMgmt;

    private boolean displayHousingMgmt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getPeriodFrom() {
        return periodFrom;
    }

    public void setPeriodFrom(Date periodFrom) {
        this.periodFrom = periodFrom;
    }

    public Date getPeriodTo() {
        return periodTo;
    }

    public void setPeriodTo(Date periodTo) {
        this.periodTo = periodTo;
    }

    public Date getHmAwardDate() {
        return hmAwardDate;
    }

    public void setHmAwardDate(Date hmAwardDate) {
        this.hmAwardDate = hmAwardDate;
    }

    public String getPositionHeld() {
        return positionHeld;
    }

    public void setPositionHeld(String positionHeld) {
        this.positionHeld = positionHeld;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public String getNameAndAddr() {
        return nameAndAddr;
    }

    public void setNameAndAddr(String nameAndAddr) {
        this.nameAndAddr = nameAndAddr;
    }

    public boolean getIsSelectHousingMgmt() {
        return isSelectHousingMgmt;
    }

    public void setIsSelectHousingMgmt(boolean isSelectHousingMgmt) {
        this.isSelectHousingMgmt = isSelectHousingMgmt;
    }

    public boolean getSaveHousingMgmt() {
        return saveHousingMgmt;
    }

    public void setSaveHousingMgmt(boolean saveHousingMgmt) {
        this.saveHousingMgmt = saveHousingMgmt;
    }

    public boolean getDisplayHousingMgmt() {
        return displayHousingMgmt;
    }

    public void setDisplayHousingMgmt(boolean displayHousingMgmt) {
        this.displayHousingMgmt = displayHousingMgmt;
    }
}
