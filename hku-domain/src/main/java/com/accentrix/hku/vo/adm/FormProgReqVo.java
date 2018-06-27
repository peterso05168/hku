package com.accentrix.hku.vo.adm;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午4:03:02
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class FormProgReqVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String admFormProgId;

    private String appRequirementId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdmFormProgId() {
        return admFormProgId;
    }

    public void setAdmFormProgId(String admFormProgId) {
        this.admFormProgId = admFormProgId;
    }

    public String getAppRequirementId() {
        return appRequirementId;
    }

    public void setAppRequirementId(String appRequirementId) {
        this.appRequirementId = appRequirementId;
    }
}
