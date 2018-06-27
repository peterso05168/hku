package com.accentrix.hku.vo.app;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年4月10日 下午4:07:52
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class AcadQualNursingPrqVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String typeOfMem;

    private Date dateFrom;

    private Date dateTo;

    private String abbre;

    private Date dateOfAward;

    private String awardInstitution;

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
