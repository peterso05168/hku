package com.accentrix.hku.domain.staff;

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
 * @date 创建时间：2018年1月30日 下午6:46:44
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "staff_prog")
@ApiObject(name = "Prog")
public class Prog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "staff_prog_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "staff_id")
    @ApiObjectField(description = "staffId")
    private String staffId;

    @Column(name = "hku_programme_id")
    @ApiObjectField(description = "hkuProgrammeId")
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
