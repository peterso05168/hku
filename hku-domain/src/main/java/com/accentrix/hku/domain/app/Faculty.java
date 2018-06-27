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
 * @date 创建时间：2018年1月30日 下午4:29:22
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "app_faculty")
@ApiObject(name = "Faculty")
public class Faculty extends AuditedObject {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "faculty_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "faculty_cd")
    @ApiObjectField(description = "facultyCd")
    private String facultyCd;

    @Column(name = "faculty_desc")
    @ApiObjectField(description = "facultyDesc")
    private String facultyDesc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFacultyCd() {
        return facultyCd;
    }

    public void setFacultyCd(String facultyCd) {
        this.facultyCd = facultyCd;
    }

    public String getFacultyDesc() {
        return facultyDesc;
    }

    public void setFacultyDesc(String facultyDesc) {
        this.facultyDesc = facultyDesc;
    }
}
