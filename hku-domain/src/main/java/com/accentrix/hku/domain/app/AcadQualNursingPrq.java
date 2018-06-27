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
 * @date 创建时间：2018年4月10日 下午1:45:23
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "app_acad_qual_nursing_prq")
@ApiObject(name = "AcadQualNursingPrq")
public class AcadQualNursingPrq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "app_acad_qual_nursing_prq_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "type_of_mem")
    @ApiObjectField(description = "typeOfMem")
    private String typeOfMem;

    @Column(name = "date_from")
    @ApiObjectField(description = "dateFrom")
    private Date dateFrom;

    @Column(name = "date_to")
    @ApiObjectField(description = "dateTo")
    private Date dateTo;

    @Column(name = "abbre")
    @ApiObjectField(description = "abbre")
    private String abbre;

    @Column(name = "date_of_award")
    @ApiObjectField(description = "dateOfAward")
    private Date dateOfAward;

    @Column(name = "award_institution")
    @ApiObjectField(description = "awardInstitution")
    private String awardInstitution;

    @Column(name = "app_acad_qual_nursing_id")
    @ApiObjectField(description = "appAcadQualNursingId")
    private String appAcadQualNursingId;

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

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public String getAbbre() {
        return abbre;
    }

    public void setAbbre(String abbre) {
        this.abbre = abbre;
    }

    public Date getDateOfAward() {
        return dateOfAward;
    }

    public void setDateOfAward(Date dateOfAward) {
        this.dateOfAward = dateOfAward;
    }

    public String getAwardInstitution() {
        return awardInstitution;
    }

    public void setAwardInstitution(String awardInstitution) {
        this.awardInstitution = awardInstitution;
    }

    public String getAppAcadQualNursingId() {
        return appAcadQualNursingId;
    }

    public void setAppAcadQualNursingId(String appAcadQualNursingId) {
        this.appAcadQualNursingId = appAcadQualNursingId;
    }
}
