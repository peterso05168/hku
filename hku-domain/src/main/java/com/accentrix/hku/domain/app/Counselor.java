package com.accentrix.hku.domain.app;

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

import com.accentrix.hku.domain.common.AuditedObject;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午4:28:53
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "app_counselor")
@ApiObject(name = "Counselor")
public class Counselor extends AuditedObject {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "counselor_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "counselor_cd")
    @ApiObjectField(description = "counselorCd")
    private String counselorCd;

    @Column(name = "email")
    @ApiObjectField(description = "email")
    private String email;

    @Column(name = "surname")
    @ApiObjectField(description = "surname")
    private String surname;

    @Column(name = "given_name")
    @ApiObjectField(description = "givenName")
    private String givenName;

    @Column(name = "full_name")
    @ApiObjectField(description = "fullName")
    private String fullName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCounselorCd() {
        return counselorCd;
    }

    public void setCounselorCd(String counselorCd) {
        this.counselorCd = counselorCd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
