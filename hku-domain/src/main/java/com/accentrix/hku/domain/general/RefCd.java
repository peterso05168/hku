package com.accentrix.hku.domain.general;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午6:39:32
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "general_ref_cd")
@ApiObject(name = "RefCd")
public class RefCd implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "general_ref_cd_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "type")
    @ApiObjectField(description = "type")
    private String type;

    @Column(name = "cd")
    @ApiObjectField(description = "cd")
    private String cd;

    @Column(name = "value")
    @ApiObjectField(description = "value")
    private String value;

    @Column(name = "value_chi")
    @ApiObjectField(description = "valueChi")
    private String valueChi;

    @Column(name = "expiration_date")
    @ApiObjectField(description = "expirationDate")
    private Date expirationDate;

    @Version
    @Column(name = "version")
    @ApiObjectField(description = "The optimistic lock value")
    private int version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValueChi() {
        return valueChi;
    }

    public void setValueChi(String valueChi) {
        this.valueChi = valueChi;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
