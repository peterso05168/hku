package com.accentrix.hku.vo.app;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午4:32:54
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class RequirementRelationshipVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String parentRequirementId;

    private String childRequirementId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentRequirementId() {
        return parentRequirementId;
    }

    public void setParentRequirementId(String parentRequirementId) {
        this.parentRequirementId = parentRequirementId;
    }

    public String getChildRequirementId() {
        return childRequirementId;
    }

    public void setChildRequirementId(String childRequirementId) {
        this.childRequirementId = childRequirementId;
    }
}
