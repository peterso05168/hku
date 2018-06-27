package com.accentrix.hku.domain.app;

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

import com.accentrix.hku.domain.common.AuditedObject;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年3月1日 上午10:28:28 
 * @version 1.0 
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "app_progress")
@ApiObject(name = "Progress")
public class Progress extends AuditedObject {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "app_progress_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "application_id")
    @ApiObjectField(description = "applicationId")
    private String applicationId;

    @Column(name = "prsnal_part")
    @ApiObjectField(description = "prsnalPart")
    private Boolean prsnalPart;

    @Column(name = "acad_bg")
    @ApiObjectField(description = "acadBg")
    private Boolean acadBg;

    @Column(name = "other_quali")
    @ApiObjectField(description = "otherQuali")
    private Boolean otherQuali;

    @Column(name = "choice_of_curri")
    @ApiObjectField(description = "choiceOfCurri")
    private Boolean choiceOfCurri;

    @Column(name = "exp_and_achi")
    @ApiObjectField(description = "expAndAchi")
    private Boolean expAndAchi;

    @Column(name = "reference")
    @ApiObjectField(description = "reference")
    private Boolean reference;

    @Column(name = "others")
    @ApiObjectField(description = "others")
    private Boolean others;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public Boolean getPrsnalPart() {
        return prsnalPart;
    }

    public void setPrsnalPart(Boolean prsnalPart) {
        this.prsnalPart = prsnalPart;
    }

    public Boolean getAcadBg() {
        return acadBg;
    }

    public void setAcadBg(Boolean acadBg) {
        this.acadBg = acadBg;
    }

    public Boolean getOtherQuali() {
        return otherQuali;
    }

    public void setOtherQuali(Boolean otherQuali) {
        this.otherQuali = otherQuali;
    }

    public Boolean getChoiceOfCurri() {
        return choiceOfCurri;
    }

    public void setChoiceOfCurri(Boolean choiceOfCurri) {
        this.choiceOfCurri = choiceOfCurri;
    }

    public Boolean getExpAndAchi() {
        return expAndAchi;
    }

    public void setExpAndAchi(Boolean expAndAchi) {
        this.expAndAchi = expAndAchi;
    }

    public Boolean getReference() {
        return reference;
    }

    public void setReference(Boolean reference) {
        this.reference = reference;
    }

    public Boolean getOthers() {
        return others;
    }

    public void setOthers(Boolean others) {
        this.others = others;
    }
}
