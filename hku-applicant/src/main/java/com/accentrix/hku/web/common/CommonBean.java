package com.accentrix.hku.web.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.accentrix.hku.service.app.HkuProgrammeService;
import com.accentrix.hku.service.app.InstitutionService;
import com.accentrix.hku.service.cpc.CityService;
import com.accentrix.hku.service.cpc.CountryService;
import com.accentrix.hku.service.cpc.ProvinceService;
import com.accentrix.hku.vo.app.HkuProgrammeVo;
import com.accentrix.hku.vo.app.InstitutionVo;
import com.accentrix.hku.vo.cpc.CityVo;
import com.accentrix.hku.vo.cpc.CountryVo;
import com.accentrix.hku.vo.cpc.ProvinceVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月29日 下午5:32:57
 * @version 1.0
 */

@ManagedBean
@ViewScoped
@Configurable(preConstruction = true)
public class CommonBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(CommonBean.class);

    @Autowired
    private CountryService countryService;
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private CityService cityService;
    @Autowired
    private HkuProgrammeService hkuProgrammeService;
    @Autowired
    private InstitutionService institutionService;

    private boolean isActiveProvinceOrCity;
    private boolean isActiveProvince;
    private boolean isActiveCity;
    private boolean isActiveStudyInstitution;
    private boolean isActiveStudyInstitutionName;
    private boolean isActiveStudyCountry;
    private List<CountryVo> countries;
    private List<ProvinceVo> provinces;
    private List<CityVo> citys;
    private List<InstitutionVo> institutions;
    private CountryVo country;
    private String provinceId;
    private List<ProvinceVo> provinceOfStudys;
    private List<CityVo> cityOfStudys;
    private List<HkuProgrammeVo> hkuProgrammes;
    private Date minDate;
    private Date maxDate;
    private Date minBirthDate;
    private Date maxBirthDate;

    public CommonBean() {
        init();
    }

    public void init() {
        LOG.info("country loading...");
        countries = countryService.findList();
        hkuProgrammes = hkuProgrammeService.findList();
        provinces = new ArrayList<ProvinceVo>();
        citys = new ArrayList<CityVo>();
        country = new CountryVo();
        isActiveProvince = false;
        isActiveCity = false;
        isActiveStudyInstitution = false;
        isActiveStudyInstitutionName = true;
        isActiveStudyCountry = false;
        provinceOfStudys = new ArrayList<ProvinceVo>();
        cityOfStudys = new ArrayList<CityVo>();
        minDate = calendarRangeDate(-30, 0, 1);
        maxDate = calendarRangeDate(5, 11, 31);
        minBirthDate = calendarRangeDate(-50, 0, 1);
        maxBirthDate = calendarRangeDate(-10, 11, 31);
    }

    private Date calendarRangeDate(int yearPlus, int month, int date) {
        Calendar c = Calendar.getInstance();
        c.set(c.get(Calendar.YEAR) + yearPlus, month, date);
        return c.getTime();
    }

    public void loadProvinceOrCityList(AjaxBehaviorEvent event) {
        SelectOneMenu menu = (SelectOneMenu) event.getSource();
        String countryId = menu != null && menu.getValue() != null ? menu.getValue().toString() : "";
        if (StringUtils.isNotBlank(countryId) && !"0".equals(countryId)) {
            CountryVo c = countryService.get(countryId);
            if (c.getCode().equals("CHN")) {
                isActiveProvinceOrCity = true;
                citys = null;
                provinces = provinceService.findByCountryId(countryId);
            } else {
                isActiveProvinceOrCity = false;
                provinces = null;
                citys = cityService.findByCountryIdOrProvinceId(countryId, null);
            }
        } else {
            provinces = null;
            citys = null;
        }
    }

    public void loadProvinceOrCityListOfStudy(AjaxBehaviorEvent event) {
        SelectOneMenu menu = (SelectOneMenu) event.getSource();
        String countryOfStudyId = menu != null && menu.getValue() != null ? menu.getValue().toString() : "";
        if (StringUtils.isNotBlank(countryOfStudyId) && !"select".equals(countryOfStudyId)) {
            if (countryOfStudyId.equals("others")) {
                isActiveProvince = false;
                isActiveCity = false;
                isActiveStudyInstitutionName = false;
                isActiveStudyInstitution = true;
                isActiveStudyCountry = true;
                provinces = null;
                citys = null;
            } else {
                isActiveStudyInstitutionName = true;
                isActiveStudyInstitution = false;
                isActiveStudyCountry = false;
                country = countryService.get(countryOfStudyId);
                if (country.getCode().equals("CHN")) {
                    isActiveProvince = true;
                    isActiveCity = false;
                    provinceOfStudys = provinceService.findByCountryId(countryOfStudyId);
                } else {
                    isActiveCity = true;
                    isActiveProvince = false;
                    cityOfStudys = cityService.findByCountryIdOrProvinceId(countryOfStudyId, null);
                }
            }
        } else {
            isActiveProvince = false;
            isActiveCity = false;
            isActiveStudyCountry = false;
            provinceOfStudys = new ArrayList<ProvinceVo>();
            cityOfStudys = new ArrayList<CityVo>();
        }
        institutions = new ArrayList<InstitutionVo>();
    }

    public void loadCityList(AjaxBehaviorEvent event) {
        SelectOneMenu menu = (SelectOneMenu) event.getSource();
        provinceId = menu != null && menu.getValue() != null ? menu.getValue().toString() : "";
        if (StringUtils.isNotBlank(provinceId) && !"select".equals(provinceId)) {
            isActiveCity = true;
            cityOfStudys = cityService.findByCountryIdOrProvinceId(country.getId(), provinceId);
        } else {
            isActiveCity = false;
            cityOfStudys = new ArrayList<CityVo>();
        }
        institutions = new ArrayList<InstitutionVo>();
    }

    public void loadInstitutionList(AjaxBehaviorEvent event) {
        SelectOneMenu menu = (SelectOneMenu) event.getSource();
        String id = menu != null && menu.getValue() != null ? menu.getValue().toString() : "";
        if (StringUtils.isNotBlank(id) && !"select".equals(id)) {
            if (country.getCode().equals("CHN")) {
                institutions = institutionService.findInstitutions(country.getId(), provinceId, id);
            } else {
                institutions = institutionService.findInstitutions(country.getId(), null, id);
            }
        } else {
            institutions = null;
        }
    }

    public void loadOthers(AjaxBehaviorEvent event) {
        SelectOneMenu menu = (SelectOneMenu) event.getSource();
        String id = menu != null && menu.getValue() != null ? menu.getValue().toString() : "";
        if (id != null && id.equals("others")) {
            isActiveStudyInstitution = true;
        } else {
            isActiveStudyInstitution = false;
        }
    }

    public boolean getIsActiveProvinceOrCity() {
        return isActiveProvinceOrCity;
    }

    public void setIsActiveProvinceOrCity(boolean isActiveProvinceOrCity) {
        this.isActiveProvinceOrCity = isActiveProvinceOrCity;
    }

    public boolean getIsActiveProvince() {
        return isActiveProvince;
    }

    public void setIsActiveProvince(boolean isActiveProvince) {
        this.isActiveProvince = isActiveProvince;
    }

    public boolean getIsActiveCity() {
        return isActiveCity;
    }

    public void setIsActiveCity(boolean isActiveCity) {
        this.isActiveCity = isActiveCity;
    }

    public List<CountryVo> getCountries() {
        return countries;
    }

    public void setCountries(List<CountryVo> countries) {
        this.countries = countries;
    }

    public List<ProvinceVo> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<ProvinceVo> provinces) {
        this.provinces = provinces;
    }

    public List<CityVo> getCitys() {
        return citys;
    }

    public void setCitys(List<CityVo> citys) {
        this.citys = citys;
    }

    public List<InstitutionVo> getInstitutions() {
        return institutions;
    }

    public void setInstitutions(List<InstitutionVo> institutions) {
        this.institutions = institutions;
    }

    public CountryVo getCountry() {
        return country;
    }

    public void setCountry(CountryVo country) {
        this.country = country;
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

    public boolean getIsActiveStudyInstitution() {
        return isActiveStudyInstitution;
    }

    public void setIsActiveStudyInstitution(boolean isActiveStudyInstitution) {
        this.isActiveStudyInstitution = isActiveStudyInstitution;
    }

    public boolean getIsActiveStudyInstitutionName() {
        return isActiveStudyInstitutionName;
    }

    public void setIsActiveStudyInstitutionName(boolean isActiveStudyInstitutionName) {
        this.isActiveStudyInstitutionName = isActiveStudyInstitutionName;
    }

    public boolean getIsActiveStudyCountry() {
        return isActiveStudyCountry;
    }

    public void setIsActiveStudyCountry(boolean isActiveStudyCountry) {
        this.isActiveStudyCountry = isActiveStudyCountry;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public List<HkuProgrammeVo> getHkuProgrammes() {
        return hkuProgrammes;
    }

    public void setHkuProgrammes(List<HkuProgrammeVo> hkuProgrammes) {
        this.hkuProgrammes = hkuProgrammes;
    }

    public Date getMinDate() {
        return minDate;
    }

    public void setMinDate(Date minDate) {
        this.minDate = minDate;
    }

    public Date getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(Date maxDate) {
        this.maxDate = maxDate;
    }

    public Date getMinBirthDate() {
        return minBirthDate;
    }

    public void setMinBirthDate(Date minBirthDate) {
        this.minBirthDate = minBirthDate;
    }

    public Date getMaxBirthDate() {
        return maxBirthDate;
    }

    public void setMaxBirthDate(Date maxBirthDate) {
        this.maxBirthDate = maxBirthDate;
    }
}
