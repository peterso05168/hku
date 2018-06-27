package com.accentrix.hku.vo.staff;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午6:46:44
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ProgVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String staffId;

    private String hkuProgrammeId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getHkuProgrammeId() {
        return hkuProgrammeId;
    }

    public void setHkuProgrammeId(String hkuProgrammeId) {
        this.hkuProgrammeId = hkuProgrammeId;
    }
}
