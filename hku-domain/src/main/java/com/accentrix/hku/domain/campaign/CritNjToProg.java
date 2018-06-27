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
 * @date 创建时间：2018年4月9日 上午11:39:22 
 * @version 1.0 
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "cpgn_crit_nj_to_prog")
@ApiObject(name = "CritNjToProg")
public class CritNjToProg implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "cpgn_crit_nj_to_prog_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "cpgn_crit_nj_id")
    @ApiObjectField(description = "cpgnCritNjId")
    private String cpgnCritNjId;

    @Column(name = "adm_form_prog_id")
    @ApiObjectField(description = "admFormProgId")
    private String admFormProgId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCpgnCritNjId() {
        return cpgnCritNjId;
    }

    public void setCpgnCritNjId(String cpgnCritNjId) {
        this.cpgnCritNjId = cpgnCritNjId;
    }

    public String getAdmFormProgId() {
        return admFormProgId;
    }

    public void setAdmFormProgId(String admFormProgId) {
        this.admFormProgId = admFormProgId;
    }
}
