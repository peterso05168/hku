package com.accentrix.hku.domain.app;

import java.io.Serializable;
import java.util.Date;

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
 * @date 创建时间：2018年4月10日 上午11:34:00
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "app_acad_qual_housing_mgmt_pq")
@ApiObject(name = "AcadQualHousingMgmtPq")
public class AcadQualHousingMgmtPq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "acad_qual_housing_mgmt_pq_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "type_of_mem")
    @ApiObjectField(description = "typeOfMem")
    private String typeOfMem;

    @Column(name = "abbre")
    @ApiObjectField(description = "abbre")
    private String abbre;

    @Column(name = "award_institution")
    @ApiObjectField(description = "awardInstitution")
    private String awardInstitution;

    @Column(name = "date_of_award")
    @ApiObjectField(description = "dateAward")
    private Date dateAward;

    @Column(name = "app_acad_qual_housing_mgmt_id")
    @ApiObjectField(description = "appAcadQualHousingMgmtId")
    private String appAcadQualHousingMgmtId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeOfMem() {
        return typeOfMem;
    }

    public void setTypeOfMem(String typeOfMem) {
        this.typeOfMem = typeOfMem;
    }

    public String getAbbre() {
        return abbre;
    }

    public void setAbbre(String abbre) {
        this.abbre = abbre;
    }

    public String getAwardInstitution() {
        return awardInstitution;
    }

    public void setAwardInstitution(String awardInstitution) {
        this.awardInstitution = awardInstitution;
    }

    public Date getDateAward() {
        return dateAward;
    }

    public void setDateAward(Date dateAward) {
        this.dateAward = dateAward;
    }

    public String getAppAcadQualHousingMgmtId() {
        return appAcadQualHousingMgmtId;
    }

    public void setAppAcadQualHousingMgmtId(String appAcadQualHousingMgmtId) {
        this.appAcadQualHousingMgmtId = appAcadQualHousingMgmtId;
    }

}
