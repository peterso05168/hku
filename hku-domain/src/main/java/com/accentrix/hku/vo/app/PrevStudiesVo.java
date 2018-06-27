package com.accentrix.hku.vo.app;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.accentrix.hku.vo.cpc.CityVo;
import com.accentrix.hku.vo.cpc.CountryVo;
import com.accentrix.hku.vo.cpc.ProvinceVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午4:30:38
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class PrevStudiesVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String applicationId;

    private String programmeTypeCd;

    private String programmeTitle;

    private String countryId;

    private Date dateOfAward;

    private String finalCumulativeGpa;

    private String maxGpa;

    private String studyModeCd;

    private String createBy;

    private Date createDate;

    private String updateBy;

    private Date updateDate;

    private int version;

    private Boolean isDeleted;

    private CountryVo countryVo;

    private String institutionId;

    private String provinceId;

    private String cityId;

    private String studyCountryOthers;

    private String institutionOthers;

    private String progTypeOthers;

    private Date studyFrom;

    private Date studyTo;

    private Boolean notGpa;

    private String finalRslt;

    private Boolean isActiveStudyCountry;

    private Boolean isActiveProvince;

    private Boolean isActiveCity;

    private Boolean isActiveOtherInstitution;

    private Boolean isActiveOtherOrGPA;

    private List<ProvinceVo> provinceOfStudys;

    private List<CityVo> cityOfStudys;

    private List<InstitutionVo> institutions;

    private InstitutionVo institutionVo;

    private String isActiveProgTypeCd;

    private String typeOfEducation;

    private Boolean isCompletedStudy;

    private String typeOfProg;

    private String studyMode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getProgrammeTypeCd() {
        return programmeTypeCd;
    }

    public void setProgrammeTypeCd(String programmeTypeCd) {
        this.programmeTypeCd = programmeTypeCd;
    }

    public String getProgrammeTitle() {
        return programmeTitle;
    }

    public void setProgrammeTitle(String programmeTitle) {
        this.programmeTitle = programmeTitle;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public Date getDateOfAward() {
        return dateOfAward;
    }

    public void setDateOfAward(Date dateOfAward) {
        this.dateOfAward = dateOfAward;
    }

    public String getFinalCumulativeGpa() {
        return finalCumulativeGpa;
    }

    public void setFinalCumulativeGpa(String finalCumulativeGpa) {
        this.finalCumulativeGpa = finalCumulativeGpa;
    }

    public String getMaxGpa() {
        return maxGpa;
    }

    public void setMaxGpa(String maxGpa) {
        this.maxGpa = maxGpa;
    }

    public String getStudyModeCd() {
        return studyModeCd;
    }

    public void setStudyModeCd(String studyModeCd) {
        this.studyModeCd = studyModeCd;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public CountryVo getCountryVo() {
        return countryVo;
    }

    public void setCountryVo(CountryVo countryVo) {
        this.countryVo = countryVo;
    }

    public String getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getStudyCountryOthers() {
        return studyCountryOthers;
    }

    public void setStudyCountryOthers(String studyCountryOthers) {
        this.studyCountryOthers = studyCountryOthers;
    }

    public String getInstitutionOthers() {
        return institutionOthers;
    }

    public void setInstitutionOthers(String institutionOthers) {
        this.institutionOthers = institutionOthers;
    }

    public String getProgTypeOthers() {
        return progTypeOthers;
    }

    public void setProgTypeOthers(String progTypeOthers) {
        this.progTypeOthers = progTypeOthers;
    }

    public Date getStudyFrom() {
        return studyFrom;
    }

    public void setStudyFrom(Date studyFrom) {
        this.studyFrom = studyFrom;
    }

    public Date getStudyTo() {
        return studyTo;
    }

    public void setStudyTo(Date studyTo) {
        this.studyTo = studyTo;
    }

    public Boolean getNotGpa() {
        return notGpa;
    }

    public void setNotGpa(Boolean notGpa) {
        this.notGpa = notGpa;
    }

    public String getFinalRslt() {
        return finalRslt;
    }

    public void setFinalRslt(String finalRslt) {
        this.finalRslt = finalRslt;
    }

    public Boolean getIsActiveStudyCountry() {
        return isActiveStudyCountry;
    }

    public void setIsActiveStudyCountry(Boolean isActiveStudyCountry) {
        this.isActiveStudyCountry = isActiveStudyCountry;
    }

    public Boolean getIsActiveProvince() {
        return isActiveProvince;
    }

    public void setIsActiveProvince(Boolean isActiveProvince) {
        this.isActiveProvince = isActiveProvince;
    }

    public Boolean getIsActiveCity() {
        return isActiveCity;
    }

    public void setIsActiveCity(Boolean isActiveCity) {
        this.isActiveCity = isActiveCity;
    }

    public List<ProvinceVo> getProvinceOfStudys() {
        return provinceOfStudys;
    }

    public void setProvinceOfStudys(List<ProvinceVo> provinceOfStudys) {
        this.provinceOfStudys = provinceOfStudys;
    }

    public List<CityVo> getCityOfStudys() {
        return cityOfStudys;
    }

    public void setCityOfStudys(List<CityVo> cityOfStudys) {
        this.cityOfStudys = cityOfStudys;
    }

    public List<InstitutionVo> getInstitutions() {
        return institutions;
    }

    public void setInstitutions(List<InstitutionVo> institutions) {
        this.institutions = institutions;
    }

    public Boolean getIsActiveOtherOrGPA() {
        return isActiveOtherOrGPA;
    }

    public void setIsActiveOtherOrGPA(Boolean isActiveOtherOrGPA) {
        this.isActiveOtherOrGPA = isActiveOtherOrGPA;
    }

    public InstitutionVo getInstitutionVo() {
        return institutionVo;
    }

    public void setInstitutionVo(InstitutionVo institutionVo) {
        this.institutionVo = institutionVo;
    }

    public Boolean getIsActiveOtherInstitution() {
        return isActiveOtherInstitution;
    }

    public void setIsActiveOtherInstitution(Boolean isActiveOtherInstitution) {
        this.isActiveOtherInstitution = isActiveOtherInstitution;
    }

    public String getIsActiveProgTypeCd() {
        return isActiveProgTypeCd;
    }

    public void setIsActiveProgTypeCd(String isActiveProgTypeCd) {
        this.isActiveProgTypeCd = isActiveProgTypeCd;
    }

    public String getTypeOfEducation() {
        return typeOfEducation;
    }

    public void setTypeOfEducation(String typeOfEducation) {
        this.typeOfEducation = typeOfEducation;
    }

    public Boolean getIsCompletedStudy() {
        return isCompletedStudy;
    }

    public void setIsCompletedStudy(Boolean isCompletedStudy) {
        this.isCompletedStudy = isCompletedStudy;
    }

    public String getTypeOfProg() {
        return typeOfProg;
    }

    public void setTypeOfProg(String typeOfProg) {
        this.typeOfProg = typeOfProg;
    }

    public String getStudyMode() {
        return studyMode;
    }

    public void setStudyMode(String studyMode) {
        this.studyMode = studyMode;
    }

}
