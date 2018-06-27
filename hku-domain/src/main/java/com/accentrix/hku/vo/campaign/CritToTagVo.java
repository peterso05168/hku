package com.accentrix.hku.vo.campaign;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年4月9日 上午11:39:44 
 * @version 1.0 
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class CritToTagVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String cpgnCritId;

    private String tagId;

    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCpgnCritId() {
        return cpgnCritId;
    }

    public void setCpgnCritId(String cpgnCritId) {
        this.cpgnCritId = cpgnCritId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }
}
