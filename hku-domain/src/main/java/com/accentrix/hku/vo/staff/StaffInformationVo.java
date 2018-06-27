package com.accentrix.hku.vo.staff;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午6:42:54
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class StaffInformationVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String email;

    private String username;

    private String staffHkuNo;

    private String staffUid;

    private Boolean isActive;

    private String combination;

    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStaffHkuNo() {
        return staffHkuNo;
    }

    public void setStaffHkuNo(String staffHkuNo) {
        this.staffHkuNo = staffHkuNo;
    }

    public String getStaffUid() {
        return staffUid;
    }

    public void setStaffUid(String staffUid) {
        this.staffUid = staffUid;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getCombination() {
        return combination;
    }

    public void setCombination(String combination) {
        this.combination = combination;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
