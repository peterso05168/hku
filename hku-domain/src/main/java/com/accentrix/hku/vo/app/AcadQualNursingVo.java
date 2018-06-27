package com.accentrix.hku.vo.app;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午4:28:40
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class AcadQualNursingVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String specResponsibilty;

    private boolean isSelectNursing;

    private boolean isSaveNursing;

    private boolean isDisplayNursing;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpecResponsibilty() {
        return specResponsibilty;
    }

    public void setSpecResponsibilty(String specResponsibilty) {
        this.specResponsibilty = specResponsibilty;
    }

    public boolean getIsSelectNursing() {
        return isSelectNursing;
    }

    public void setIsSelectNursing(boolean isSelectNursing) {
        this.isSelectNursing = isSelectNursing;
    }

    public boolean getIsSaveNursing() {
        return isSaveNursing;
    }

    public void setIsSaveNursing(boolean isSaveNursing) {
        this.isSaveNursing = isSaveNursing;
    }

    public boolean getIsDisplayNursing() {
        return isDisplayNursing;
    }

    public void setIsDisplayNursing(boolean isDisplayNursing) {
        this.isDisplayNursing = isDisplayNursing;
    }
}
