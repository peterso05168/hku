package com.accentrix.hku.vo.cpc;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月26日 下午3:04:02
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ProvinceCityVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String code;
    private String description;
    private String descriptionChinese;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionChinese() {
        return descriptionChinese;
    }

    public void setDescriptionChinese(String descriptionChinese) {
        this.descriptionChinese = descriptionChinese;
    }
}
