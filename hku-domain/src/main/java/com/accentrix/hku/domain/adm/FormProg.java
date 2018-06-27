package com.accentrix.hku.domain.adm;

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
 * @date 创建时间：2018年1月30日 下午4:02:43
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "adm_form_prog")
@ApiObject(name = "FormProg")
public class FormProg implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "adm_form_prog_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "adm_form_id")
    @ApiObjectField(description = "admFormId")
    private String admFormId;

    @Column(name = "app_hku_programme_id")
    @ApiObjectField(description = "appHkuProgrammeId")
    private String appHkuProgrammeId;

    @Column(name = "quota_local")
    @ApiObjectField(description = "quotaLocal")
    private Integer quotaLocal;

    @Column(name = "quota_overseas")
    @ApiObjectField(description = "quotaOverseas")
    private Integer quotaOverseas;

    @Column(name = "level_of_entry")
    @ApiObjectField(description = "levelOfEntry")
    private Integer levelOfEntry;

    @Column(name = "quota_mainland")
    @ApiObjectField(description = "quotaMainland")
    private Integer quotaMainland;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdmFormId() {
        return admFormId;
    }

    public void setAdmFormId(String admFormId) {
        this.admFormId = admFormId;
    }

    public String getAppHkuProgrammeId() {
        return appHkuProgrammeId;
    }

    public void setAppHkuProgrammeId(String appHkuProgrammeId) {
        this.appHkuProgrammeId = appHkuProgrammeId;
    }

    public Integer getQuotaLocal() {
        return quotaLocal;
    }

    public void setQuotaLocal(Integer quotaLocal) {
        this.quotaLocal = quotaLocal;
    }

    public Integer getQuotaOverseas() {
        return quotaOverseas;
    }

    public void setQuotaOverseas(Integer quotaOverseas) {
        this.quotaOverseas = quotaOverseas;
    }

    public Integer getLevelOfEntry() {
        return levelOfEntry;
    }

    public void setLevelOfEntry(Integer levelOfEntry) {
        this.levelOfEntry = levelOfEntry;
    }

    public Integer getQuotaMainland() {
        return quotaMainland;
    }

    public void setQuotaMainland(Integer quotaMainland) {
        this.quotaMainland = quotaMainland;
    }
}
