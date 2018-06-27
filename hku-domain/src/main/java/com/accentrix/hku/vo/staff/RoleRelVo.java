package com.accentrix.hku.vo.staff;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年3月15日 上午10:57:46 
 * @version 1.0 
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class RoleRelVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String staffInfoId;

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
