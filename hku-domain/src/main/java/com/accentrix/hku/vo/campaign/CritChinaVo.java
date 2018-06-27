package com.accentrix.hku.vo.campaign;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年4月9日 上午11:38:04 
 * @version 1.0 
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class CritChinaVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private Boolean selectedForShortlist;

    private String cpgnId;

    private List<String> tagList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSelectedForShortlist() {
        return selectedForShortlist;
    }

    public void setSelectedForShortlist(Boolean selectedForShortlist) {
        this.selectedForShortlist = selectedForShortlist;
    }

    public String getCpgnId() {
        return cpgnId;
    }

    public void setCpgnId(String cpgnId) {
        this.cpgnId = cpgnId;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }
}
