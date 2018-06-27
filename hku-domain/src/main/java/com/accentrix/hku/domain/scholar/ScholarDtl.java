package com.accentrix.hku.domain.scholar;

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
 * @date 创建时间：2018年3月19日 上午11:07:09
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "scholar_dtl")
@ApiObject(name = "ScholarDtl")
public class ScholarDtl implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "scholar_dtl_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "tier")
    @ApiObjectField(description = "tier")
    private String tier;

    @Column(name = "amount")
    @ApiObjectField(description = "amount")
    private Integer amount;

    @Column(name = "letter_content")
    @ApiObjectField(description = "letterContent")
    private String letterContent;

    @Column(name = "scholar_id")
    @ApiObjectField(description = "scholarId")
    private String scholarId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getLetterContent() {
        return letterContent;
    }

    public void setLetterContent(String letterContent) {
        this.letterContent = letterContent;
    }

    public String getScholarId() {
        return scholarId;
    }

    public void setScholarId(String scholarId) {
        this.scholarId = scholarId;
    }
}
