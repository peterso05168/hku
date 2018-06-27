package com.accentrix.hku.vo.campaign;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年4月9日 上午11:40:37
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class SessionToApplicationVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String cpgnSessionId;

    private String applicantApplicationId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCpgnSessionId() {
        return cpgnSessionId;
    }

    public void setCpgnSessionId(String cpgnSessionId) {
        this.cpgnSessionId = cpgnSessionId;
    }

    public String getApplicantApplicationId() {
        return applicantApplicationId;
    }

    public void setApplicantApplicationId(String applicantApplicationId) {
        this.applicantApplicationId = applicantApplicationId;
    }
}
