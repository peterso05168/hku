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
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年4月25日 下午9:20:53
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "app_njcee_subject_structure")
@ApiObject(name = "AppNjceeSubjectStructure")
public class AppNjceeSubjectStructure implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "app_njcee_subject_structure_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "app_njcee_scoring_system_id")
    @ApiObjectField(description = "appNjceeScoringSystemId")
    private String appNjceeScoringSystemId;

    @Column(name = "subject_id")
    @ApiObjectField(description = "subjectId")
    private String subjectId;

    @Column(name = "subject_score")
    @ApiObjectField(description = "subjectScore")
    private String subjectScore;

    @Column(name = "structure_type")
    @ApiObjectField(description = "structureType")
    private String structureType;

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
}
