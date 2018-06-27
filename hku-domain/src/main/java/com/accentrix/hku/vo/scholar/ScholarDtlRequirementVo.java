package com.accentrix.hku.vo.scholar;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年3月19日 上午11:09:19
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ScholarDtlRequirementVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String scholarDtlId;

    private String requirementId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScholarDtlId() {
        return scholarDtlId;
    }

    public void setScholarDtlId(String scholarDtlId) {
        this.scholarDtlId = scholarDtlId;
    }

    public String getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(String requirementId) {
        this.requirementId = requirementId;
    }
}
