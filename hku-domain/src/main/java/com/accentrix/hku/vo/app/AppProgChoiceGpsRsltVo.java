package com.accentrix.hku.vo.app;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年6月1日 下午1:30:39
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class AppProgChoiceGpsRsltVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String admScoringFormulaId;

    private String appProgrammeChoiceId;

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
