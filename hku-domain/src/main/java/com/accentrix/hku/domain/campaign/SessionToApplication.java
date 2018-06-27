package com.accentrix.hku.domain.campaign;

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
 * @date 创建时间：2018年4月9日 上午11:40:37
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "cpgn_session_to_application")
@ApiObject(name = "SessionToApplication")
public class SessionToApplication implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "cpgn_session_applicant_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "cpgn_session_id")
    @ApiObjectField(description = "cpgnSessionId")
    private String cpgnSessionId;

    @Column(name = "applicant_application_id")
    @ApiObjectField(description = "applicantApplicationId")
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
