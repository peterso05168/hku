package com.accentrix.hku.vo.campaign;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年4月9日 上午11:38:45 
 * @version 1.0 
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class CritChinaDtlVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String provinceId;

    private String provinceName;

    private String examTypeId;

    private String examTypeName;

    private String stream;

    private String streamName;

    private Integer cutoffTotal;

    private Integer cutoffEnglish;

    private String cpgnChinaId;

    private Integer noEligAppl;

    private Integer pctEligAppl;

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

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getExamTypeName() {
        return examTypeName;
    }

    public void setExamTypeName(String examTypeName) {
        this.examTypeName = examTypeName;
    }

    public String getStreamName() {
        return streamName;
    }

    public void setStreamName(String streamName) {
        this.streamName = streamName;
    }
}
