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
 * @date 创建时间：2018年3月15日 上午10:57:46 
 * @version 1.0 
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "staff_role_rel")
@ApiObject(name = "RoleRel")
public class RoleRel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "staff_role_rel_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "staff_info_id")
    @ApiObjectField(description = "staffInfoId")
    private String staffInfoId;

    @Column(name = "staff_role_id")
    @ApiObjectField(description = "staffRoleId")
    private String staffRoleId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStaffInfoId() {
        return staffInfoId;
    }

    public void setStaffInfoId(String staffInfoId) {
        this.staffInfoId = staffInfoId;
    }

    public String getStaffRoleId() {
        return staffRoleId;
    }

    public void setStaffRoleId(String staffRoleId) {
        this.staffRoleId = staffRoleId;
    }
}
