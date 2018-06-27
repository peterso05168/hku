package com.accentrix.hku.vo.app;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午4:30:53
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ProgRequirementVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String appHkuProgrammeId;

    private String appRequirementId;

    private String relationship;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppHkuProgrammeId() {
        return appHkuProgrammeId;
    }

    public void setAppHkuProgrammeId(String appHkuProgrammeId) {
        this.appHkuProgrammeId = appHkuProgrammeId;
    }

    public String getAppRequirementId() {
        return appRequirementId;
    }

    public void setAppRequirementId(String appRequirementId) {
        this.appRequirementId = appRequirementId;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
}
