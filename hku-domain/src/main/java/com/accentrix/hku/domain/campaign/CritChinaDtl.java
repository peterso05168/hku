package com.accentrix.hku.domain.campaign;

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
 * @date 创建时间：2018年4月9日 上午11:38:45 
 * @version 1.0 
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "cpgn_crit_china_dtl")
@ApiObject(name = "CritChinaDtl")
public class CritChinaDtl implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "cpgn_crit_china_dtl_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "province_id")
    @ApiObjectField(description = "provinceId")
    private String provinceId;

    @Column(name = "exam_type_id")
    @ApiObjectField(description = "examTypeId")
    private String examTypeId;

    @Column(name = "stream")
    @ApiObjectField(description = "stream")
    private String stream;

    @Column(name = "cutoff_total")
    @ApiObjectField(description = "cutoffTotal")
    private Integer cutoffTotal;

    @Column(name = "cutoff_english")
    @ApiObjectField(description = "cutoffEnglish")
    private Integer cutoffEnglish;

    @Column(name = "no_elig_appl")
    @ApiObjectField(description = "noEligAppl")
    private Integer noEligAppl;

    @Column(name = "pct_elig_appl")
    @ApiObjectField(description = "pctEligAppl")
    private Integer pctEligAppl;

    @Column(name = "cpgn_china_id")
    @ApiObjectField(description = "cpgnChinaId")
    private String cpgnChinaId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getExamTypeId() {
        return examTypeId;
    }

    public void setExamTypeId(String examTypeId) {
        this.examTypeId = examTypeId;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public Integer getCutoffTotal() {
        return cutoffTotal;
    }

    public void setCutoffTotal(Integer cutoffTotal) {
        this.cutoffTotal = cutoffTotal;
    }

    public Integer getCutoffEnglish() {
        return cutoffEnglish;
    }

    public void setCutoffEnglish(Integer cutoffEnglish) {
        this.cutoffEnglish = cutoffEnglish;
    }

    public String getCpgnChinaId() {
        return cpgnChinaId;
    }

    public void setCpgnChinaId(String cpgnChinaId) {
        this.cpgnChinaId = cpgnChinaId;
    }

    public Integer getNoEligAppl() {
        return noEligAppl;
    }

    public void setNoEligAppl(Integer noEligAppl) {
        this.noEligAppl = noEligAppl;
    }

    public Integer getPctEligAppl() {
        return pctEligAppl;
    }

    public void setPctEligAppl(Integer pctEligAppl) {
        this.pctEligAppl = pctEligAppl;
    }
}
