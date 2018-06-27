package com.accentrix.hku.vo.xml;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Lonny Wei
 * @date 2018年3月26日 上午10:43:04
 * @version 1.0
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ExaminationVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String examination;

    private List<SubjectRequirementVo> subjectRequirements;

    public String getExamination() {
        return examination;
    }

    public void setExamination(String examination) {
        this.examination = examination;
    }

    public List<SubjectRequirementVo> getSubjectRequirements() {
        return subjectRequirements;
    }

    public void setSubjectRequirements(List<SubjectRequirementVo> subjectRequirements) {
        this.subjectRequirements = subjectRequirements;
    }

}
