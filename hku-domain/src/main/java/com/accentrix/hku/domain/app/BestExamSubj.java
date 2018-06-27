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
 * @author Jaye.Lin 
 * @date 创建时间：2018年5月29日 下午7:29:02 
 * @version 1.0 
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "app_best_exam_subj")
@ApiObject(name = "BestExamSubj")
public class BestExamSubj implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "app_best_exam_subj_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "exam_type_id")
    @ApiObjectField(description = "examTypeId")
    private String examTypeId;

    @Column(name = "application_id")
    @ApiObjectField(description = "applicationId")
    private String applicationId;

    @Column(name = "calculate_type")
    @ApiObjectField(description = "calculateType")
    private String calculateType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExamTypeId() {
        return examTypeId;
    }

    public void setExamTypeId(String examTypeId) {
        this.examTypeId = examTypeId;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getCalculateType() {
        return calculateType;
    }

    public void setCalculateType(String calculateType) {
        this.calculateType = calculateType;
    }
}
