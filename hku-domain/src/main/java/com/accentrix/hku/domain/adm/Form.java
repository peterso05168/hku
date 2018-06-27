package com.accentrix.hku.domain.adm;

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
 * @date 创建时间：2018年1月30日 下午3:59:49
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "adm_form")
@ApiObject(name = "Form")
public class Form implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "adm_form_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "adm_exe_id")
    @ApiObjectField(description = "admExeId")
    private String admExeId;

    @Column(name = "description")
    @ApiObjectField(description = "description")
    private String description;

    @Column(name = "desc_chi")
    @ApiObjectField(description = "descChi")
    private String descChi;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdmExeId() {
        return admExeId;
    }

    public void setAdmExeId(String admExeId) {
        this.admExeId = admExeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescChi() {
        return descChi;
    }

    public void setDescChi(String descChi) {
        this.descChi = descChi;
    }
}
