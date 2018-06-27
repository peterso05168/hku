package com.accentrix.hku.domain.applicant;

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
 * @date 创建时间：2018年4月27日 下午5:51:19 
 * @version 1.0 
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "applicant_to_tag")
@ApiObject(name = "ApplicantToTag")
public class ApplicantToTag implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "applicant_to_tag_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "applicant_info_id")
    @ApiObjectField(description = "applicantInfoId")
    private String applicantInfoId;

    @Column(name = "tag_id")
    @ApiObjectField(description = "tagId")
    private String tagId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApplicantInfoId() {
        return applicantInfoId;
    }

    public void setApplicantInfoId(String applicantInfoId) {
        this.applicantInfoId = applicantInfoId;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

}
