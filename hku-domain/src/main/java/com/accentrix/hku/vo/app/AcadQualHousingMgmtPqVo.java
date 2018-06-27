package com.accentrix.hku.vo.app;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年4月10日 下午2:21:16
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class AcadQualHousingMgmtPqVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String typeOfMem;

    private String abbre;

    private String awardInstitution;

    private Date dateAward;

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
