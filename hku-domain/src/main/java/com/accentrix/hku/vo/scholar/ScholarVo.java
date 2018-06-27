package com.accentrix.hku.vo.scholar;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年3月19日 上午11:04:37
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ScholarVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String status;

    private Boolean notAppEndDate;

    private Date scholarStartDate;

    private Date scholarEndDate;

    private boolean isDisplayScholar;

    private String color;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getNotAppEndDate() {
        return notAppEndDate;
    }

    public void setNotAppEndDate(Boolean notAppEndDate) {
        this.notAppEndDate = notAppEndDate;
    }

    public Date getScholarStartDate() {
        return scholarStartDate;
    }

    public void setScholarStartDate(Date scholarStartDate) {
        this.scholarStartDate = scholarStartDate;
    }

    public Date getScholarEndDate() {
        return scholarEndDate;
    }

    public void setScholarEndDate(Date scholarEndDate) {
        this.scholarEndDate = scholarEndDate;
    }

    public boolean getIsDisplayScholar() {
        return isDisplayScholar;
    }

    public void setIsDisplayScholar(boolean isDisplayScholar) {
        this.isDisplayScholar = isDisplayScholar;
    }

    public String getColor() {
        if ("Active".equals(status))
            color = "green";
        if ("Inactive".equals(status))
            color = "grey";
        return color;
    }
}
