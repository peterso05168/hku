package com.accentrix.hku.domain.staff;

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
 * @date 创建时间：2018年1月30日 下午6:42:54
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "staff_info")
@ApiObject(name = "StaffInformation")
public class StaffInformation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "staff_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "email")
    @ApiObjectField(description = "email")
    private String email;

    @Column(name = "username")
    @ApiObjectField(description = "username")
    private String username;

    @Column(name = "staff_hku_no")
    @ApiObjectField(description = "staffHkuNo")
    private String staffHkuNo;

    @Column(name = "staff_uid")
    @ApiObjectField(description = "staffUid")
    private String staffUid;

    @Column(name = "is_active")
    @ApiObjectField(description = "isActive")
    private Boolean isActive;

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
}
