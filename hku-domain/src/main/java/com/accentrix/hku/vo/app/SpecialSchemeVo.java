package com.accentrix.hku.vo.app;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午4:33:08
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class SpecialSchemeVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String specialSchemeCd;

    private String applicationId;

    private boolean isActiveSport;

    private boolean isActiveMusic;

    private boolean isDisplayBachelorOfArts;

    private boolean isSelectBachelorOfArts;

    private String sptAppScheme;

    private String sptSports;

    private String sptLevel;

    private String sptLevelOthers;

    private String sptHyperlink;

    private List<String> sptLevels;

    private boolean isActiveSptLevelOthers;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpecialSchemeCd() {
        return specialSchemeCd;
    }

    public void setSpecialSchemeCd(String specialSchemeCd) {
        this.specialSchemeCd = specialSchemeCd;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public boolean getIsActiveSport() {
        return isActiveSport;
    }

    public void setIsActiveSport(boolean isActiveSport) {
        this.isActiveSport = isActiveSport;
    }

    public boolean getIsActiveMusic() {
        return isActiveMusic;
    }

    public void setIsActiveMusic(boolean isActiveMusic) {
        this.isActiveMusic = isActiveMusic;
    }

    public boolean getIsDisplayBachelorOfArts() {
        return isDisplayBachelorOfArts;
    }

    public void setIsDisplayBachelorOfArts(boolean isDisplayBachelorOfArts) {
        this.isDisplayBachelorOfArts = isDisplayBachelorOfArts;
    }

    public boolean getIsSelectBachelorOfArts() {
        return isSelectBachelorOfArts;
    }

    public void setIsSelectBachelorOfArts(boolean isSelectBachelorOfArts) {
        this.isSelectBachelorOfArts = isSelectBachelorOfArts;
    }

    public String getSptAppScheme() {
        return sptAppScheme;
    }

    public void setSptAppScheme(String sptAppScheme) {
        this.sptAppScheme = sptAppScheme;
    }

    public String getSptSports() {
        return sptSports;
    }

    public void setSptSports(String sptSports) {
        this.sptSports = sptSports;
    }

    public String getSptLevel() {
        return sptLevel;
    }

    public void setSptLevel(String sptLevel) {
        this.sptLevel = sptLevel;
    }

    public String getSptLevelOthers() {
        return sptLevelOthers;
    }

    public void setSptLevelOthers(String sptLevelOthers) {
        this.sptLevelOthers = sptLevelOthers;
    }

    public String getSptHyperlink() {
        return sptHyperlink;
    }

    public void setSptHyperlink(String sptHyperlink) {
        this.sptHyperlink = sptHyperlink;
    }

    public List<String> getSptLevels() {
        return sptLevels;
    }

    public void setSptLevels(List<String> sptLevels) {
        this.sptLevels = sptLevels;
    }

    public boolean getIsActiveSptLevelOthers() {
        return isActiveSptLevelOthers;
    }

    public void setIsActiveSptLevelOthers(boolean isActiveSptLevelOthers) {
        this.isActiveSptLevelOthers = isActiveSptLevelOthers;
    }
}
