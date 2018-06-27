package com.accentrix.hku.vo.app;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年4月25日 下午9:20:53
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class AppNjceeSubjectStructureVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String appNjceeScoringSystemId;

    private String subjectId;

    private String subjectScore;

    private String structureType;

    private String subjectDesc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppNjceeScoringSystemId() {
        return appNjceeScoringSystemId;
    }

    public void setAppNjceeScoringSystemId(String appNjceeScoringSystemId) {
        this.appNjceeScoringSystemId = appNjceeScoringSystemId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectScore() {
        return subjectScore;
    }

    public void setSubjectScore(String subjectScore) {
        this.subjectScore = subjectScore;
    }

    public String getStructureType() {
        return structureType;
    }

    public void setStructureType(String structureType) {
        this.structureType = structureType;
    }

    public String getSubjectDesc() {
        return subjectDesc;
    }

    public void setSubjectDesc(String subjectDesc) {
        this.subjectDesc = subjectDesc;
    }
}
