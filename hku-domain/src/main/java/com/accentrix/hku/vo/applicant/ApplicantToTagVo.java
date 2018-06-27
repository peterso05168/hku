package com.accentrix.hku.vo.applicant;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年4月27日 下午5:51:19 
 * @version 1.0 
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ApplicantToTagVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String applicantInfoId;

    private String tagId;

    private String tagNames;

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

    public String getTagNames() {
        return tagNames;
    }

    public void setTagNames(String tagNames) {
        this.tagNames = tagNames;
    }

}
