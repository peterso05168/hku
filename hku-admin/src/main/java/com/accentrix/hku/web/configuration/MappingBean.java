package com.accentrix.hku.web.configuration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.commons.lang.StringUtils;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.accentrix.hku.service.campaign.MappingService;
import com.accentrix.hku.service.cpc.CityService;
import com.accentrix.hku.service.cpc.CountryService;
import com.accentrix.hku.service.cpc.ProvinceService;
import com.accentrix.hku.util.UIUtil;
import com.accentrix.hku.vo.campaign.MappingVo;
import com.accentrix.hku.vo.cpc.CityVo;
import com.accentrix.hku.vo.cpc.CountryVo;
import com.accentrix.hku.vo.cpc.ProvinceVo;
import com.accentrix.hku.web.common.InternationalizationBean;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年4月26日 下午6:28:33 
 * @version 1.0 
 */

@ManagedBean
@ViewScoped
@Configurable(preConstruction = true)
public class MappingBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(MappingBean.class);

    @Autowired
    private MappingService mappingService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private CityService cityService;

    private MappingVo mappingVo;
    private List<MappingVo> mappingVos;
    private List<CountryVo> countryVos;
    private List<ProvinceVo> provinceVos;
    private List<ProvinceVo> proVos;
    private List<CityVo> cityVos;

    public MappingBean() {
        LOG.debug("MappingBean init ......");
        init();
    }

    public void init() {
        mappingVo = new MappingVo();
        mappingVos = mappingService.findMappingList(mappingVo.getCountryId(), mappingVo.getProvinceId());
        countryVos = countryService.findList();
        provinceVos = provinceService.findList();
        cityVos = cityService.findList();
        proVos = new ArrayList<ProvinceVo>();
    }

    public void loadProvinceList(AjaxBehaviorEvent event) {
        SelectOneMenu menu = (SelectOneMenu) event.getSource();
        String countryId = menu.getValue().toString();
        if (StringUtils.isNotBlank(countryId)) {
            proVos = provinceService.findByCountryId(countryId);
        }
    }

    public void search() {
        LOG.debug("MappingBean search ......");
        mappingVos = mappingService.findMappingList(mappingVo.getCountryId(), mappingVo.getProvinceId());
    }

    public void reset() {
        LOG.debug("MappingBean reset ......");
        init();
    }

    public void save() {
        mappingService.save(mappingVos);
        UIUtil.displaySaveSuccessDialog(InternationalizationBean.locale);
    }

    public List<MappingVo> getMappingVos() {
        return mappingVos;
    }

    public void setMappingVos(List<MappingVo> mappingVos) {
        this.mappingVos = mappingVos;
    }

    public List<ProvinceVo> getProvinceVos() {
        return provinceVos;
    }

    public void setProvinceVos(List<ProvinceVo> provinceVos) {
        this.provinceVos = provinceVos;
    }

    public List<CityVo> getCityVos() {
        return cityVos;
    }

    public void setCityVos(List<CityVo> cityVos) {
        this.cityVos = cityVos;
    }

    public MappingVo getMappingVo() {
        return mappingVo;
    }

    public void setMappingVo(MappingVo mappingVo) {
        this.mappingVo = mappingVo;
    }

    public List<CountryVo> getCountryVos() {
        return countryVos;
    }

    public void setCountryVos(List<CountryVo> countryVos) {
        this.countryVos = countryVos;
    }

    public List<ProvinceVo> getProVos() {
        return proVos;
    }

    public void setProVos(List<ProvinceVo> proVos) {
        this.proVos = proVos;
    }
}
