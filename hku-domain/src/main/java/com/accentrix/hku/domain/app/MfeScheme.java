package com.accentrix.hku.domain.app;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年4月3日 下午6:28:53
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "app_mfe_scheme")
@ApiObject(name = "MfeScheme")
public class MfeScheme implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "app_mfe_scheme_id", length = 32)
    @ApiObjectField(description = "The primary key")
    private String id;

    @Column(name = "app_qualifications_id")
    @ApiObjectField(description = "qualificationsId")
    private String qualificationsId;

    @Column(name = "chinese")
    @ApiObjectField(description = "chinese")
    private String chinese;

    @Column(name = "maths")
    @ApiObjectField(description = "maths")
    private String maths;

    @Column(name = "english")
    @ApiObjectField(description = "english")
    private String english;

    @Column(name = "physics")
    @ApiObjectField(description = "physics")
    private String physics;

    @Column(name = "chemistry")
    @ApiObjectField(description = "chemistry")
    private String chemistry;

    @Column(name = "biology")
    @ApiObjectField(description = "biology")
    private String biology;

    @Column(name = "history")
    @ApiObjectField(description = "history")
    private String history;

    @Column(name = "geography")
    @ApiObjectField(description = "geography")
    private String geography;

    @Column(name = "politics")
    @ApiObjectField(description = "politics")
    private String politics;

    @Column(name = "technology")
    @ApiObjectField(description = "technology")
    private String technology;

    @Column(name = "integrated_arts")
    @ApiObjectField(description = "integratedArts")
    private String integratedArts;

    @Column(name = "integrated_science")
    @ApiObjectField(description = "integratedScience")
    private String integratedScience;

    @Column(name = "total")
    @ApiObjectField(description = "total")
    private String total;

    @Column(name = "rank")
    @ApiObjectField(description = "rank")
    private String rank;

    @Column(name = "total_students")
    @ApiObjectField(description = "totalStudents")
    private String totalStudents;

    @Column(name = "integrated_rank")
    @ApiObjectField(description = "integratedRank")
    private String integratedRank;

    @Column(name = "year_semester")
    @ApiObjectField(description = "yearSemester")
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
