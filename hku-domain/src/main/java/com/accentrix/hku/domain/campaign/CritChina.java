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
 * @date 创建时间：2018年4月9日 上午11:38:04 
 * @version 1.0 
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "cpgn_crit_china")
@ApiObject(name = "CritChina")
public class CritChina implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "cpgn_crit_china_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "name")
    @ApiObjectField(description = "name")
    private String name;

    @Column(name = "selected_for_shortlist")
    @ApiObjectField(description = "selectedForShortlist")
    private Boolean selectedForShortlist;

    @Column(name = "cpgn_id")
    @ApiObjectField(description = "cpgnId")
    private String cpgnId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSelectedForShortlist() {
        return selectedForShortlist;
    }

    public void setSelectedForShortlist(Boolean selectedForShortlist) {
        this.selectedForShortlist = selectedForShortlist;
    }

    public String getCpgnId() {
        return cpgnId;
    }

    public void setCpgnId(String cpgnId) {
        this.cpgnId = cpgnId;
    }
}
