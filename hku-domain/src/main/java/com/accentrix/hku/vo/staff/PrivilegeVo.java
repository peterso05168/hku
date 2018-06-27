package com.accentrix.hku.vo.staff;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午6:45:52
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class PrivilegeVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String module;

    private String moduleCd;

    private Integer privilege;

    private String privilegeDesc;

    private List<PrivilegeVo> vos;

    private List<String> privilegeIds;

    private boolean status;

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

    public List<PrivilegeVo> getVos() {
        return vos;
    }

    public void setVos(List<PrivilegeVo> vos) {
        this.vos = vos;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<String> getPrivilegeIds() {
        return privilegeIds;
    }

    public void setPrivilegeIds(List<String> privilegeIds) {
        this.privilegeIds = privilegeIds;
    }
}
