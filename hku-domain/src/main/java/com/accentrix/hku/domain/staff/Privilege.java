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
 * @date 创建时间：2018年1月30日 下午6:45:52
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "staff_privilege")
@ApiObject(name = "Privilege")
public class Privilege implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "staff_privilege_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "module")
    @ApiObjectField(description = "module")
    private String module;

    @Column(name = "module_cd")
    @ApiObjectField(description = "moduleCd")
    private String moduleCd;

    @Column(name = "privilege")
    @ApiObjectField(description = "privilege")
    private Integer privilege;

    @Column(name = "privilege_desc")
    @ApiObjectField(description = "privilegeDesc")
    private String privilegeDesc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getModuleCd() {
        return moduleCd;
    }

    public void setModuleCd(String moduleCd) {
        this.moduleCd = moduleCd;
    }

    public Integer getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Integer privilege) {
        this.privilege = privilege;
    }

    public String getPrivilegeDesc() {
        return privilegeDesc;
    }

    public void setPrivilegeDesc(String privilegeDesc) {
        this.privilegeDesc = privilegeDesc;
    }
}
