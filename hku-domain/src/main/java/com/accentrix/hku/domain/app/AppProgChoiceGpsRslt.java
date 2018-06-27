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
 * @date 创建时间：2018年6月1日 下午1:27:50
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "app_prog_choice_gps_rslt")
@ApiObject(name = "AppProgChoiceGpsRslt")
public class AppProgChoiceGpsRslt implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "app_prog_choice_gps_rslt_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "adm_scoring_formula_id")
    @ApiObjectField(description = "admScoringFormulaId")
    private String admScoringFormulaId;

    @Column(name = "app_programme_choice_id")
    @ApiObjectField(description = "appProgrammeChoiceId")
    private String appProgrammeChoiceId;

    @Column(name = "gps_rslt")
    @ApiObjectField(description = "gpsRslt")
    private String gpsRslt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdmScoringFormulaId() {
        return admScoringFormulaId;
    }

    public void setAdmScoringFormulaId(String admScoringFormulaId) {
        this.admScoringFormulaId = admScoringFormulaId;
    }

    public String getAppProgrammeChoiceId() {
        return appProgrammeChoiceId;
    }

    public void setAppProgrammeChoiceId(String appProgrammeChoiceId) {
        this.appProgrammeChoiceId = appProgrammeChoiceId;
    }

    public String getGpsRslt() {
        return gpsRslt;
    }

    public void setGpsRslt(String gpsRslt) {
        this.gpsRslt = gpsRslt;
    }

}
