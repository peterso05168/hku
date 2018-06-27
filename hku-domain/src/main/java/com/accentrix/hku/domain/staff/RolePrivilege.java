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
 * @date 创建时间：2018年1月30日 下午6:55:21
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "staff_role_privilege")
@ApiObject(name = "RolePrivilege")
public class RolePrivilege implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "staff_role_privilege_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "staff_role_id")
    @ApiObjectField(description = "staffRoleId")
    private String staffRoleId;

    @Column(name = "staff_privilege_id")
    @ApiObjectField(description = "staffPrivilegeId")
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
