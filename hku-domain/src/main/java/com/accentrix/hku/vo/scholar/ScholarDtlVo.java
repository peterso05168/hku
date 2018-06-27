package com.accentrix.hku.vo.scholar;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年3月19日 上午11:07:09
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ScholarDtlVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String tier;

    private Integer amount;

    private String letterContent;

    private String scholarId;

    private List<String> requirementNames;

    private List<String> requirementIds;

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

    public List<String> getRequirementNames() {
        return requirementNames;
    }

    public void setRequirementNames(List<String> requirementNames) {
        this.requirementNames = requirementNames;
    }

    public List<String> getRequirementIds() {
        return requirementIds;
    }

    public void setRequirementIds(List<String> requirementIds) {
        this.requirementIds = requirementIds;
    }
}
