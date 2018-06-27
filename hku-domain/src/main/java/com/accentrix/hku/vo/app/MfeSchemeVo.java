package com.accentrix.hku.vo.app;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年4月3日 下午6:28:53
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class MfeSchemeVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String qualificationsId;

    private String chinese;

    private String maths;

    private String english;

    private String physics;

    private String chemistry;

    private String biology;

    private String history;

    private String geography;

    private String politics;

    private String technology;

    private String integratedArts;

    private String integratedScience;

    private String total;

    private String rank;

    private String totalStudents;

    private String integratedRank;

    private Integer yearSemester;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQualificationsId() {
        return qualificationsId;
    }

    public void setQualificationsId(String qualificationsId) {
        this.qualificationsId = qualificationsId;
    }

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }

    public String getMaths() {
        return maths;
    }

    public void setMaths(String maths) {
        this.maths = maths;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getPhysics() {
        return physics;
    }

    public void setPhysics(String physics) {
        this.physics = physics;
    }

    public String getChemistry() {
        return chemistry;
    }

    public void setChemistry(String chemistry) {
        this.chemistry = chemistry;
    }

    public String getBiology() {
        return biology;
    }

    public void setBiology(String biology) {
        this.biology = biology;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getGeography() {
        return geography;
    }

    public void setGeography(String geography) {
        this.geography = geography;
    }

    public String getPolitics() {
        return politics;
    }

    public void setPolitics(String politics) {
        this.politics = politics;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getIntegratedArts() {
        return integratedArts;
    }

    public void setIntegratedArts(String integratedArts) {
        this.integratedArts = integratedArts;
    }

    public String getIntegratedScience() {
        return integratedScience;
    }

    public void setIntegratedScience(String integratedScience) {
        this.integratedScience = integratedScience;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(String totalStudents) {
        this.totalStudents = totalStudents;
    }

    public String getIntegratedRank() {
        return integratedRank;
    }

    public void setIntegratedRank(String integratedRank) {
        this.integratedRank = integratedRank;
    }

    public Integer getYearSemester() {
        return yearSemester;
    }

    public void setYearSemester(Integer yearSemester) {
        this.yearSemester = yearSemester;
    }

}
