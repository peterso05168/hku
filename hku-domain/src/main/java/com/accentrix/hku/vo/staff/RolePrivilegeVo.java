package com.accentrix.hku.vo.staff;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午6:55:21
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class RolePrivilegeVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String staffRoleId;

    private String staffPrivilegeId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStaffRoleId() {
        return staffRoleId;
    }

    public void setStaffRoleId(String staffRoleId) {
        this.staffRoleId = staffRoleId;
    }

    public String getStaffPrivilegeId() {
        return staffPrivilegeId;
    }

    public void setStaffPrivilegeId(String staffPrivilegeId) {
        this.staffPrivilegeId = staffPrivilegeId;
    }
}
