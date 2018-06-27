package com.accentrix.hku.vo.campaign;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年4月9日 上午11:39:22 
 * @version 1.0 
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class CritNjToProgVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String cpgnCritNjId;

    private String admFormProgId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCpgnCritNjId() {
        return cpgnCritNjId;
    }

    public void setCpgnCritNjId(String cpgnCritNjId) {
        this.cpgnCritNjId = cpgnCritNjId;
    }

    public String getAdmFormProgId() {
        return admFormProgId;
    }

    public void setAdmFormProgId(String admFormProgId) {
        this.admFormProgId = admFormProgId;
    }
}
