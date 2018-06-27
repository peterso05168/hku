package com.accentrix.hku.web.configuration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.commons.lang.StringUtils;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.accentrix.hku.service.adm.FormProgService;
import com.accentrix.hku.service.app.InstitutionService;
import com.accentrix.hku.service.campaign.CampaignService;
import com.accentrix.hku.service.campaign.CentreService;
import com.accentrix.hku.service.campaign.CpgnSessionService;
import com.accentrix.hku.service.campaign.CritChinaDtlService;
import com.accentrix.hku.service.campaign.CritChinaService;
import com.accentrix.hku.service.campaign.CritNjService;
import com.accentrix.hku.service.campaign.CritNjToProgService;
import com.accentrix.hku.service.campaign.CritToTagService;
import com.accentrix.hku.service.cpc.CityService;
import com.accentrix.hku.service.cpc.CountryService;
import com.accentrix.hku.service.cpc.ProvinceService;
import com.accentrix.hku.service.exam.TypeService;
import com.accentrix.hku.service.tag.TagService;
import com.accentrix.hku.util.JSFUtil;
import com.accentrix.hku.util.UIUtil;
import com.accentrix.hku.vo.adm.FormProgVo;
import com.accentrix.hku.vo.app.InstitutionVo;
import com.accentrix.hku.vo.campaign.CampaignVo;
import com.accentrix.hku.vo.campaign.CentreVo;
import com.accentrix.hku.vo.campaign.CpgnSessionVo;
import com.accentrix.hku.vo.campaign.CritChinaAndNjVo;
import com.accentrix.hku.vo.campaign.CritChinaDtlVo;
import com.accentrix.hku.vo.campaign.CritChinaVo;
import com.accentrix.hku.vo.campaign.CritNjToProgVo;
import com.accentrix.hku.vo.campaign.CritNjVo;
import com.accentrix.hku.vo.campaign.CritToTagVo;
import com.accentrix.hku.vo.cpc.CityVo;
import com.accentrix.hku.vo.cpc.CountryVo;
import com.accentrix.hku.vo.cpc.ProvinceCityVo;
import com.accentrix.hku.vo.cpc.ProvinceVo;
import com.accentrix.hku.vo.exam.TypeVo;
import com.accentrix.hku.vo.tag.TagVo;
import com.accentrix.hku.web.common.InternationalizationBean;
import com.eaio.uuid.UUID;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年3月20日 下午6:13:51
 */
@ManagedBean
@ViewScoped
@Configurable(preConstruction = true)
public class EditCampaignBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(EditCampaignBean.class);
    private static final String PROVINCE = "Province";
    private static final String CITY = "City";

    @Autowired
    private CampaignService campaignService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private CentreService centreService;
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private CityService cityService;
    @Autowired
    private CpgnSessionService cpgnSessionService;
    @Autowired
    private FormProgService formProgService;
    @Autowired
    private TagService tagService;
    @Autowired
    private InstitutionService institutionService;
    @Autowired
    private CritNjService critNjService;
    @Autowired
    private CritChinaService critChinaService;
    @Autowired
    private CritToTagService critToTagService;
    @Autowired
    private CritNjToProgService critNjToProgService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private CritChinaDtlService critChinaDtlService;

    private Boolean isActiveRunGroup;
    private boolean isActiveCriteriaType1;
    private boolean isActiveCriteriaType2;
    private CampaignVo campaignVo;
    private String cpgnId;
    private List<CountryVo> countries;
    private List<CentreVo> centreVos;
    private List<ProvinceCityVo> provinceCityVos;
    private CentreVo centreVo;
    private CpgnSessionVo cpgnSessionVo;
    private List<CritChinaAndNjVo> critChinaAndNjVos;
    private CritNjVo critNjVo;
    private CritChinaVo critChinaVo;
    private CritChinaAndNjVo critChinaAndNjVo;
    private List<FormProgVo> formProgs;
    private List<TagVo> tagVos;
    private CritChinaDtlVo critChinaDtlVo;
    private Map<String, CritChinaDtlVo> map;
    private Map<String, CentreVo> mapCentre;
    private CentreVo centre;

    private List<CountryVo> countryVos;
    private List<ProvinceVo> provinces;
    private List<CityVo> citys;
    private List<InstitutionVo> institutions;
    private List<TypeVo> typeVos;
    private boolean isActiveProvince;
    private boolean isActiveCity;
    private String critChinaDtlId;

    public EditCampaignBean() {
        LOG.debug("init editCampaignBean...");
        init();
        cpgnId = (String) JSFUtil.getSessionMap().get("cpgnId");
        campaignVo = campaignService.get(cpgnId);
        countries = countryService.findList();
        centreVos = centreService.findByCpgnId(cpgnId);
        provinceCityVos = new ArrayList<ProvinceCityVo>();
        centreVo = new CentreVo();
        cpgnSessionVo = new CpgnSessionVo();
        critChinaAndNjVos = campaignService.findCritList(cpgnId);
        critNjVo = new CritNjVo();
        critChinaVo = new CritChinaVo();
        critChinaAndNjVo = new CritChinaAndNjVo();
        formProgs = formProgService.findVos(campaignVo.getCpgnYear());
        tagVos = tagService.findList();
        critChinaDtlVo = new CritChinaDtlVo();
        map = new HashMap<String, CritChinaDtlVo>();
        typeVos = new ArrayList<TypeVo>();

        countryVos = countryService.findList();
        provinces = new ArrayList<ProvinceVo>();
        citys = new ArrayList<CityVo>();
        institutions = new ArrayList<InstitutionVo>();
        mapCentre = new HashMap<String, CentreVo>();
        if (StringUtils.isNotBlank(campaignVo.getMappedBy())) {
            List<CentreVo> centreVoList = centreService.findByCpgnIdAndProvinceOrCity(cpgnId, campaignVo.getMappedBy());
            for (CentreVo centreVo : centreVoList) {
                mapCentre.put(new UUID().toString().replace("-", ""), centreVo);
            }
        }
    }

    public void save() {
        if (validateContentToCampaign()) {
            UIUtil.displayManFieldMissingDialog(InternationalizationBean.locale);
            return;
        }
        campaignService.save(campaignVo);
    }

    private boolean validateContentToCampaign() {
        boolean value = false;
        if (StringUtils.isEmpty(campaignVo.getCpgnName())) {
            UIUtil.setInvalid("campaignForm:campaignName");
            value = true;
        }
        return value;
    }

    public void toCreateCentreDialog() {
        campaignVo = campaignService.get(cpgnId);
        centreVo = new CentreVo();
        if (StringUtils.isNotBlank(campaignVo.getMappedBy())) {
            if (campaignVo.getMappedBy().equals(PROVINCE)) {
                provinceCityVos = provinceService.findByCId(campaignVo.getCountryId());
            } else if (campaignVo.getMappedBy().equals(CITY)) {
                provinceCityVos = cityService.findByCountryId(campaignVo.getCountryId());
            }
        }
        UIUtil.setValid("formAddEvent:provinceOrCity");
        UIUtil.setValid("formAddEvent:centreName");
    }

    public void saveCentre() {
        if (validateContentToCentre()) {
            UIUtil.displayManFieldMissingDialog(InternationalizationBean.locale);
            return;
        }
        if (StringUtils.isNotBlank(campaignVo.getMappedBy())) {
            if (campaignVo.getMappedBy().equals(PROVINCE)) {
                centreVo.setProvinceId(centreVo.getProvinceOrCityId());
            } else if (campaignVo.getMappedBy().equals(CITY)) {
                centreVo.setCityId(centreVo.getProvinceOrCityId());
            }
        }
        centreVo.setCpgnId(campaignVo.getId());
        centreVo.setMapped(true);
        centreService.save(centreVo);
        centreVos = centreService.findByCpgnId(cpgnId);
        List<CentreVo> centreVoList = centreService.findByCpgnIdAndProvinceOrCity(cpgnId, campaignVo.getMappedBy());
        mapCentre = new HashMap<String, CentreVo>();
        for (CentreVo centreVo : centreVoList) {
            mapCentre.put(new UUID().toString().replace("-", ""), centreVo);
        }
        UIUtil.hide("createDialog");
    }

    private boolean validateContentToCentre() {
        boolean value = false;
        if (StringUtils.isEmpty(centreVo.getProvinceOrCityId())) {
            UIUtil.setInvalid("formAddEvent:provinceOrCity");
            value = true;
        }
        if (StringUtils.isEmpty(centreVo.getCentreName())) {
            UIUtil.setInvalid("formAddEvent:centreName");
            value = true;
        }
        return value;
    }

    public void init() {
        isActiveRunGroup = true;
    }

    public void toEditCentre(String id) {
        centreVo = centreService.get(id);
        if (StringUtils.isNotBlank(campaignVo.getMappedBy())) {
            if (campaignVo.getMappedBy().equals(PROVINCE)) {
                centreVo.setProvinceOrCityId(centreVo.getProvinceId());
                provinceCityVos = provinceService.findByCId(campaignVo.getCountryId());
            } else if (campaignVo.getMappedBy().equals(CITY)) {
                centreVo.setProvinceOrCityId(centreVo.getProvinceId());
                provinceCityVos = cityService.findByCountryId(campaignVo.getCountryId());
            }
        }
    }

    public void delete(String id) {
        List<CpgnSessionVo> cpgnSessionVos = cpgnSessionService.findByCentreId(id);
        for (CpgnSessionVo cpgnSessionVo : cpgnSessionVos) {
            cpgnSessionService.delete(cpgnSessionVo);
        }
        centreService.delete(id);
        centreVos = centreService.findByCpgnId(cpgnId);
    }

    public void createSession(String id) {
        cpgnSessionVo = new CpgnSessionVo();
        centreVo = centreService.get(id);
    }

    public void saveSession() {
        if (validateContentToSession()) {
            UIUtil.displayManFieldMissingDialog(InternationalizationBean.locale);
            return;
        }
        cpgnSessionVo.setCpgnCentreId(centreVo.getId());
        cpgnSessionService.save(cpgnSessionVo);
        centreVos = centreService.findByCpgnId(cpgnId);
        UIUtil.hide("editSessionDialog");
    }

    private boolean validateContentToSession() {
        boolean value = false;
        if (cpgnSessionVo.getSessionDatetime() == null) {
            UIUtil.setInvalid("formEditSession:sessionDateTime");
            value = true;
        }
        if (cpgnSessionVo.getAssignedQuota() == null) {
            UIUtil.setInvalid("formEditSession:assignedQuota");
            value = true;
        }
        return value;
    }

    public void toEditSession(String id) {
        cpgnSessionVo = cpgnSessionService.get(id);
        centreVo = centreService.get(cpgnSessionVo.getCpgnCentreId());
    }

    public void deleteSession(String id) {
        cpgnSessionService.delete(id);
        centreVos = centreService.findByCpgnId(cpgnId);
    }

    public void loadCriteriaType(AjaxBehaviorEvent event) {
        SelectOneMenu menu = (SelectOneMenu) event.getSource();
        String criteriaType = "";
        if (menu.getValue() != null) {
            criteriaType = menu.getValue().toString();
        }
        if (StringUtils.isNotBlank(criteriaType)) {
            if ("0".equals(criteriaType)) {
                isActiveCriteriaType1 = false;
                isActiveCriteriaType2 = false;
                critChinaVo = new CritChinaVo();
                critNjVo = new CritNjVo();
                isActiveProvince = false;
                isActiveCity = false;
                provinces = null;
                citys = null;
                institutions = null;
                map = new HashMap<String, CritChinaDtlVo>();
            } else if ("1".equals(criteriaType)) {
                isActiveCriteriaType1 = true;
                isActiveCriteriaType2 = false;
                critChinaVo = new CritChinaVo();
                map = new HashMap<String, CritChinaDtlVo>();
            } else if ("2".equals(criteriaType)) {
                isActiveCriteriaType1 = false;
                isActiveCriteriaType2 = true;
                critNjVo = new CritNjVo();
                isActiveProvince = false;
                isActiveCity = false;
                provinces = null;
                citys = null;
                institutions = null;
            }
        }
    }

    public void loadProvinceOrCityList(AjaxBehaviorEvent event) {
        SelectOneMenu menu = (SelectOneMenu) event.getSource();
        String countryId = "";
        if (menu.getValue() != null) {
            countryId = menu.getValue().toString();
        }
        if (StringUtils.isNotBlank(countryId)) {
            CountryVo c = countryService.get(countryId);
            if (c.getCode().equals("CHN")) {
                citys = null;
                provinces = provinceService.findByCountryId(countryId);
                isActiveProvince = true;
                isActiveCity = false;
                institutions = null;
            } else {
                provinces = null;
                citys = cityService.findByCountryIdOrProvinceId(countryId, null);
                isActiveProvince = false;
                isActiveCity = true;
                institutions = null;
            }
        } else {
            isActiveProvince = false;
            isActiveCity = false;
            provinces = null;
            citys = null;
            institutions = null;
        }
    }

    public void loadCity(AjaxBehaviorEvent event) {
        SelectOneMenu menu = (SelectOneMenu) event.getSource();
        String provinceId = "";
        if (menu.getValue() != null) {
            provinceId = menu.getValue().toString();
        }
        if (StringUtils.isNotBlank(provinceId) && !"0".equals(provinceId)) {
            citys = cityService.findByCountryIdOrProvinceId(null, provinceId);
            isActiveCity = true;
            institutions = null;
        } else {
            isActiveCity = false;
            citys = null;
            institutions = null;
        }
    }

    public void loadInstitutionList(AjaxBehaviorEvent event) {
        SelectOneMenu menu = (SelectOneMenu) event.getSource();
        String id = "";
        if (menu.getValue() != null) {
            id = menu.getValue().toString();
        }
        if (StringUtils.isNotBlank(id) && !"0".equals(id)) {
            institutions = institutionService.findInstitutions(null, null, id);
        } else {
            institutions = null;
        }
    }

    public void toCritNJOrChina(String id, String type, Boolean isSelected, String name) {
        critChinaAndNjVo = new CritChinaAndNjVo();
        critChinaAndNjVo.setId(id);
        critChinaAndNjVo.setName(name);
        critChinaAndNjVo.setSelectedForShortlist(isSelected);
        critChinaAndNjVo.setStatus(true);
        if (type.equals("NJ")) {
            critChinaAndNjVo.setType("1");
            critNjVo = critNjService.get(id);
            critNjVo.setFormProgList(critNjToProgService.findByCritNjId(id));
            critNjVo.setTagList(critToTagService.findByCrit(id, "NJ"));
            if (StringUtils.isNotBlank(critNjVo.getProvinceId())) {
                isActiveProvince = true;
                provinces = provinceService.findByCountryId(critNjVo.getCountryId());
            }
            if (StringUtils.isNotBlank(critNjVo.getCityId())) {
                isActiveCity = true;
                citys = cityService.findByCountryIdOrProvinceId(critNjVo.getCountryId(), critNjVo.getProvinceId());
            }
            if (StringUtils.isNotBlank(critNjVo.getInstitutionId())) {
                institutions = institutionService.findInstitutions(critNjVo.getCountryId(), critNjVo.getProvinceId(),
                        critNjVo.getCityId());
            }
            isActiveCriteriaType1 = true;
            isActiveCriteriaType2 = false;
        } else if (type.equals("China")) {
            critChinaAndNjVo.setType("2");
            critChinaVo = critChinaService.get(id);
            critChinaVo.setTagList(critToTagService.findByCrit(id, "China"));
            List<CritChinaDtlVo> critChinaDtlVos = critChinaDtlService.findByCritChinaId(id);
            map = new HashMap<String, CritChinaDtlVo>();
            for (CritChinaDtlVo critChinaDtlVo : critChinaDtlVos) {
                ProvinceVo provinceVo = provinceService.get(critChinaDtlVo.getProvinceId());
                TypeVo typeVo = typeService.get(critChinaDtlVo.getExamTypeId());
                critChinaDtlVo.setProvinceName(provinceVo.getDescription());
                critChinaDtlVo.setExamTypeName(typeVo.getExamDesc());
                if (critChinaDtlVo.getStream().equals("1")) {
                    critChinaDtlVo.setStreamName("Arts");
                } else if (critChinaDtlVo.getStream().equals("2")) {
                    critChinaDtlVo.setStreamName("Science");
                }
                map.put(new UUID().toString().replace("-", ""), critChinaDtlVo);
            }
            isActiveCriteriaType1 = false;
            isActiveCriteriaType2 = true;
        }
        UIUtil.setValid("formEditCriteria:criteriaName");
        UIUtil.setValid("formEditCriteria:criteriaType");
        UIUtil.setValid("formEditCriteria:multipleProg");
        UIUtil.show("editCriteriaDialog");
    }

    public void toNewCritNJOrChina() {
        critNjVo = new CritNjVo();
        critChinaVo = new CritChinaVo();
        critChinaAndNjVo = new CritChinaAndNjVo();
        map = new HashMap<String, CritChinaDtlVo>();
        isActiveCriteriaType1 = false;
        isActiveCriteriaType2 = false;
        UIUtil.setValid("formEditCriteria:criteriaName");
        UIUtil.setValid("formEditCriteria:criteriaType");
        UIUtil.setValid("formEditCriteria:multipleProg");
        UIUtil.show("editCriteriaDialog");
    }

    public void saveCritNJOrChina() {
        if (validateShortlistingCriteriaContent()) {
            UIUtil.displayManFieldMissingDialog(InternationalizationBean.locale);
            return;
        }
        if (critChinaAndNjVo != null && StringUtils.isNotBlank(critChinaAndNjVo.getType())) {
            if (critChinaAndNjVo.getType().equals("1")) {
                critNjVo.setName(critChinaAndNjVo.getName());
                critNjVo.setSelectedForShortlist(critChinaAndNjVo.getSelectedForShortlist());
                critNjVo.setCpgnId(cpgnId);
                critNjVo = critNjService.save(critNjVo);

                List<CritNjToProgVo> critNjToProgVos = critNjToProgService.findByCritNjIdToList(critNjVo.getId());
                for (CritNjToProgVo critNjToProgVo : critNjToProgVos) {
                    critNjToProgService.delete(critNjToProgVo);
                }
                if (critNjVo.getFormProgList() != null && critNjVo.getFormProgList().size() > 0) {
                    for (String progId : critNjVo.getFormProgList()) {
                        CritNjToProgVo critNjToProgVo = new CritNjToProgVo();
                        critNjToProgVo.setAdmFormProgId(progId);
                        critNjToProgVo.setCpgnCritNjId(critNjVo.getId());
                        critNjToProgService.save(critNjToProgVo);
                    }
                }
                List<CritToTagVo> critToTagVos = critToTagService.findByCritToList(critNjVo.getId(), "NJ");
                for (CritToTagVo critToTagVo : critToTagVos) {
                    critToTagService.delete(critToTagVo);
                }
                if (critNjVo.getTagList() != null && critNjVo.getTagList().size() > 0) {
                    for (String tagId : critNjVo.getTagList()) {
                        CritToTagVo critToTagVo = new CritToTagVo();
                        critToTagVo.setTagId(tagId);
                        critToTagVo.setType("NJ");
                        critToTagVo.setCpgnCritId(critNjVo.getId());
                        critToTagService.save(critToTagVo);
                    }
                }
            } else if (critChinaAndNjVo.getType().equals("2")) {
                critChinaVo.setName(critChinaAndNjVo.getName());
                critChinaVo.setSelectedForShortlist(critChinaAndNjVo.getSelectedForShortlist());
                critChinaVo.setCpgnId(cpgnId);
                critChinaVo = critChinaService.save(critChinaVo);
                if (critChinaVo.getTagList() != null) {
                    List<CritToTagVo> critToTagVos = critToTagService.findByCritToList(critChinaVo.getId(), "China");
                    for (CritToTagVo critToTagVo : critToTagVos) {
                        critToTagService.delete(critToTagVo);
                    }
                    for (String tagId : critChinaVo.getTagList()) {
                        CritToTagVo critToTagVo = new CritToTagVo();
                        critToTagVo.setTagId(tagId);
                        critToTagVo.setType("China");
                        critToTagVo.setCpgnCritId(critChinaVo.getId());
                        critToTagService.save(critToTagVo);
                    }
                }
                for (Map.Entry<String, CritChinaDtlVo> entry : map.entrySet()) {
                    CritChinaDtlVo critChinaDtlVo = entry.getValue();
                    critChinaDtlVo.setCpgnChinaId(critChinaVo.getId());
                    critChinaDtlService.save(critChinaDtlVo);
                }
            }
            UIUtil.hide("editCriteriaDialog");
            critChinaAndNjVos = campaignService.findCritList(cpgnId);
        }
    }

    private boolean validateShortlistingCriteriaContent() {
        boolean value = false;
        if (critChinaAndNjVo != null) {
            if (StringUtils.isEmpty(critChinaAndNjVo.getName())) {
                UIUtil.setInvalid("formEditCriteria:criteriaName");
                value = true;
            }
            if (StringUtils.isEmpty(critChinaAndNjVo.getType())) {
                UIUtil.setInvalid("formEditCriteria:criteriaType");
                value = true;
            } else {
                if (critChinaAndNjVo.getType().equals("1")) {
                    if (critNjVo.getFormProgList().size() == 0) {
                        UIUtil.setInvalid("formEditCriteria:multipleProg");
                        value = true;
                    }
                } else if (critChinaAndNjVo.getType().equals("2")) {
                    if (map.entrySet().size() == 0) {
                        value = true;
                    }
                }
            }
        } else {
            value = true;
        }
        return value;
    }

    public void toEditProvince(String id) {
        if (StringUtils.isNotBlank(id)) {
            critChinaDtlVo = map.get(id);
            critChinaDtlId = id;
        } else {
            critChinaDtlVo = new CritChinaDtlVo();
        }
        CountryVo countryVo = countryService.getByCode("CHN");
        provinces = provinceService.findByCountryId(countryVo.getId());
        typeVos = typeService.findList();
        UIUtil.show("editProvinceDialog");
    }

    public void saveCritChinaDtlToProvince() {
        if (validateCritChinaDtlToProvince()) {
            UIUtil.displayManFieldMissingDialog(InternationalizationBean.locale);
            return;
        }
        ProvinceVo provinceVo = provinceService.get(critChinaDtlVo.getProvinceId());
        TypeVo typeVo = typeService.get(critChinaDtlVo.getExamTypeId());
        critChinaDtlVo.setProvinceName(provinceVo.getDescription());
        critChinaDtlVo.setExamTypeName(typeVo.getExamDesc());
        if (critChinaDtlVo.getStream().equals("1")) {
            critChinaDtlVo.setStreamName("Arts");
        } else if (critChinaDtlVo.getStream().equals("2")) {
            critChinaDtlVo.setStreamName("Science");
        }
        if (StringUtils.isEmpty(critChinaDtlId)) {
            map.put(new UUID().toString().replace("-", ""), critChinaDtlVo);
        }
        UIUtil.hide("editProvinceDialog");
        critChinaDtlId = null;
    }

    private boolean validateCritChinaDtlToProvince() {
        boolean value = false;
        if (critChinaDtlVo != null) {
            if (StringUtils.isEmpty(critChinaDtlVo.getProvinceId())) {
                UIUtil.setInvalid("formEditProvince:critChinaDtlVoProvinceId");
                value = true;
            }
            if (StringUtils.isEmpty(critChinaDtlVo.getExamTypeId())) {
                UIUtil.setInvalid("formEditProvince:critChinaDtlVoExamTypeId");
                value = true;
            }
            if (StringUtils.isEmpty(critChinaDtlVo.getStream())) {
                UIUtil.setInvalid("formEditProvince:critChinaDtlVoStream");
                value = true;
            }
            if (critChinaDtlVo.getCutoffTotal() == null) {
                UIUtil.setInvalid("formEditProvince:critChinaDtlVoCutoffTotal");
                value = true;
            }
            if (critChinaDtlVo.getCutoffEnglish() == null) {
                UIUtil.setInvalid("formEditProvince:critChinaDtlVoCutoffEnglish");
                value = true;
            }
        } else {
            return true;
        }
        return value;
    }

    public void toMapping(String id) {
        centre = mapCentre.get(id);
        CountryVo countryVo = new CountryVo();
        if (campaignVo.getMappedBy().equals(PROVINCE)) {
            ProvinceVo provinceVo = provinceService.get(centre.getProvinceOrCityId());
            countryVo = countryService.get(provinceVo.getCountryId());
        } else if (campaignVo.getMappedBy().equals(CITY)) {
            CityVo cityVo = cityService.get(centre.getProvinceOrCityId());
            countryVo = countryService.get(cityVo.getCountryId());
        }
        centre.setCountryName(countryVo.getDescription());
        List<CentreVo> list = centreService.findByType(campaignVo.getMappedBy(), centre.getProvinceOrCityId(),
                campaignVo.getId());
        centre.setCentreVos(list);
        UIUtil.show("editMappingDialog");
    }

    public void saveMappin() {
        List<CentreVo> list = centreService.findByIds(centre.getCentreIds(), campaignVo.getMappedBy(),
                centre.getProvinceOrCityId(), campaignVo.getId());
        for (CentreVo centreVo : list) {
            centreVo.setMapped(false);
        }
        centreService.save(list);
        list = centreService.findByIdList(centre.getCentreIds());
        for (CentreVo centreVo : list) {
            centreVo.setMapped(true);
        }
        centreService.save(list);
        list = centreService.findByCpgnIdAndProvinceOrCity(cpgnId, campaignVo.getMappedBy());
        mapCentre = new HashMap<String, CentreVo>();
        for (CentreVo centreVo : list) {
            mapCentre.put(new UUID().toString().replace("-", ""), centreVo);
        }
        UIUtil.hide("editMappingDialog");
    }

    public void changeRunTabGroup(String groupId) {
        if ("processGroup".equals(groupId)) {
            isActiveRunGroup = false;
        } else if ("confirmGroup".equals(groupId)) {
            isActiveRunGroup = null;
        } else if ("centreDataGroup".equals(groupId)) {
            isActiveRunGroup = true;
        }
        UIUtil.update(":runGroup", false);
    }

    public Boolean getIsActiveRunGroup() {
        return isActiveRunGroup;
    }

    public void setIsActiveRunGroup(Boolean isActiveRunGroup) {
        this.isActiveRunGroup = isActiveRunGroup;
    }

    public boolean getIsActiveCriteriaType1() {
        return isActiveCriteriaType1;
    }

    public void setIsActiveCriteriaType1(boolean isActiveCriteriaType1) {
        this.isActiveCriteriaType1 = isActiveCriteriaType1;
    }

    public boolean getIsActiveCriteriaType2() {
        return isActiveCriteriaType2;
    }

    public void setIsActiveCriteriaType2(boolean isActiveCriteriaType2) {
        this.isActiveCriteriaType2 = isActiveCriteriaType2;
    }

    public CampaignVo getCampaignVo() {
        return campaignVo;
    }

    public void setCampaignVo(CampaignVo campaignVo) {
        this.campaignVo = campaignVo;
    }

    public String getCpgnId() {
        return cpgnId;
    }

    public void setCpgnId(String cpgnId) {
        this.cpgnId = cpgnId;
    }

    public List<CountryVo> getCountries() {
        return countries;
    }

    public void setCountries(List<CountryVo> countries) {
        this.countries = countries;
    }

    public List<CentreVo> getCentreVos() {
        return centreVos;
    }

    public void setCentreVos(List<CentreVo> centreVos) {
        this.centreVos = centreVos;
    }

    public List<ProvinceCityVo> getProvinceCityVos() {
        return provinceCityVos;
    }

    public void setProvinceCityVos(List<ProvinceCityVo> provinceCityVos) {
        this.provinceCityVos = provinceCityVos;
    }

    public CentreVo getCentreVo() {
        return centreVo;
    }

    public void setCentreVo(CentreVo centreVo) {
        this.centreVo = centreVo;
    }

    public CpgnSessionVo getCpgnSessionVo() {
        return cpgnSessionVo;
    }

    public void setCpgnSessionVo(CpgnSessionVo cpgnSessionVo) {
        this.cpgnSessionVo = cpgnSessionVo;
    }

    public List<CritChinaAndNjVo> getCritChinaAndNjVos() {
        return critChinaAndNjVos;
    }

    public void setCritChinaAndNjVos(List<CritChinaAndNjVo> critChinaAndNjVos) {
        this.critChinaAndNjVos = critChinaAndNjVos;
    }

    public CritNjVo getCritNjVo() {
        return critNjVo;
    }

    public void setCritNjVo(CritNjVo critNjVo) {
        this.critNjVo = critNjVo;
    }

    public CritChinaVo getCritChinaVo() {
        return critChinaVo;
    }

    public void setCritChinaVo(CritChinaVo critChinaVo) {
        this.critChinaVo = critChinaVo;
    }

    public CritChinaAndNjVo getCritChinaAndNjVo() {
        return critChinaAndNjVo;
    }

    public void setCritChinaAndNjVo(CritChinaAndNjVo critChinaAndNjVo) {
        this.critChinaAndNjVo = critChinaAndNjVo;
    }

    public List<FormProgVo> getFormProgs() {
        return formProgs;
    }

    public void setFormProgs(List<FormProgVo> formProgs) {
        this.formProgs = formProgs;
    }

    public List<TagVo> getTagVos() {
        return tagVos;
    }

    public void setTagVos(List<TagVo> tagVos) {
        this.tagVos = tagVos;
    }

    public List<CountryVo> getCountryVos() {
        return countryVos;
    }

    public void setCountryVos(List<CountryVo> countryVos) {
        this.countryVos = countryVos;
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

    public CritChinaDtlVo getCritChinaDtlVo() {
        return critChinaDtlVo;
    }

    public void setCritChinaDtlVo(CritChinaDtlVo critChinaDtlVo) {
        this.critChinaDtlVo = critChinaDtlVo;
    }

    public Map<String, CritChinaDtlVo> getMap() {
        return map;
    }

    public void setMap(Map<String, CritChinaDtlVo> map) {
        this.map = map;
    }

    public List<TypeVo> getTypeVos() {
        return typeVos;
    }

    public void setTypeVos(List<TypeVo> typeVos) {
        this.typeVos = typeVos;
    }

    public String getCritChinaDtlId() {
        return critChinaDtlId;
    }

    public void setCritChinaDtlId(String critChinaDtlId) {
        this.critChinaDtlId = critChinaDtlId;
    }

    public Map<String, CentreVo> getMapCentre() {
        return mapCentre;
    }

    public void setMapCentre(Map<String, CentreVo> mapCentre) {
        this.mapCentre = mapCentre;
    }

    public CentreVo getCentre() {
        return centre;
    }

    public void setCentre(CentreVo centre) {
        this.centre = centre;
    }
}
