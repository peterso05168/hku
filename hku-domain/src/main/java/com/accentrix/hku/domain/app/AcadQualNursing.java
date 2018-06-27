package com.accentrix.hku.domain.app;

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
 * @date 创建时间：2018年1月30日 下午4:28:40
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "app_acad_qual_nursing")
@ApiObject(name = "AcadQualNursing")
public class AcadQualNursing implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "acad_qual_nursing_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "spec_responsibilty")
    @ApiObjectField(description = "specResponsibilty")
    private String specResponsibilty;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpecResponsibilty() {
        return specResponsibilty;
    }

    public void setSpecResponsibilty(String specResponsibilty) {
        this.specResponsibilty = specResponsibilty;
    }
}
