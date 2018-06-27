package com.accentrix.hku.vo.cpc;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月25日 上午11:04:01
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class CountryVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String code;

    private String description;

    private String descriptionChinese;

    private boolean isDisplayScholar;

    private boolean isActiveScholar;

    private boolean isDisplayHeForSheScholar;

    private boolean isDisplayNigeriaScholar;

    private boolean isDisplayVtpScholar;

    private boolean isDisplayAflScholar;

    private boolean isDisplayHksarGsftScholar;

    private boolean isDisplayHksarBrGsftScholar;

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

    public boolean getIsDisplayScholar() {
        return isDisplayScholar;
    }

    public void setIsDisplayScholar(boolean isDisplayScholar) {
        this.isDisplayScholar = isDisplayScholar;
    }

    public boolean getIsActiveScholar() {
        return isActiveScholar;
    }

    public void setIsActiveScholar(boolean isActiveScholar) {
        this.isActiveScholar = isActiveScholar;
    }

    public boolean getIsDisplayHeForSheScholar() {
        return isDisplayHeForSheScholar;
    }

    public void setIsDisplayHeForSheScholar(boolean isDisplayHeForSheScholar) {
        this.isDisplayHeForSheScholar = isDisplayHeForSheScholar;
    }

    public boolean getIsDisplayNigeriaScholar() {
        return isDisplayNigeriaScholar;
    }

    public void setIsDisplayNigeriaScholar(boolean isDisplayNigeriaScholar) {
        this.isDisplayNigeriaScholar = isDisplayNigeriaScholar;
    }

    public boolean getIsDisplayVtpScholar() {
        return isDisplayVtpScholar;
    }

    public void setIsDisplayVtpScholar(boolean isDisplayVtpScholar) {
        this.isDisplayVtpScholar = isDisplayVtpScholar;
    }

    public boolean getIsDisplayAflScholar() {
        return isDisplayAflScholar;
    }

    public void setIsDisplayAflScholar(boolean isDisplayAflScholar) {
        this.isDisplayAflScholar = isDisplayAflScholar;
    }

    public boolean getIsDisplayHksarGsftScholar() {
        return isDisplayHksarGsftScholar;
    }

    public void setIsDisplayHksarGsftScholar(boolean isDisplayHksarGsftScholar) {
        this.isDisplayHksarGsftScholar = isDisplayHksarGsftScholar;
    }

    public boolean getIsDisplayHksarBrGsftScholar() {
        return isDisplayHksarBrGsftScholar;
    }

    public void setIsDisplayHksarBrGsftScholar(boolean isDisplayHksarBrGsftScholar) {
        this.isDisplayHksarBrGsftScholar = isDisplayHksarBrGsftScholar;
    }

}
