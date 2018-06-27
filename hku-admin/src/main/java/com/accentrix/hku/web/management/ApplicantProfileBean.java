package com.accentrix.hku.web.management;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.accentrix.hku.service.app.InstitutionService;
import com.accentrix.hku.service.applicant.AccountService;
import com.accentrix.hku.service.applicant.ApplicantInformationService;
import com.accentrix.hku.service.cpc.CityService;
import com.accentrix.hku.service.cpc.CountryService;
import com.accentrix.hku.service.cpc.ProvinceService;
import com.accentrix.hku.util.JSFUtil;
import com.accentrix.hku.vo.app.InstitutionVo;
import com.accentrix.hku.vo.applicant.AccountVo;
import com.accentrix.hku.vo.applicant.ApplicantInformationVo;
import com.accentrix.hku.vo.cpc.CityVo;
import com.accentrix.hku.vo.cpc.CountryVo;
import com.accentrix.hku.vo.cpc.ProvinceVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年5月4日 上午11:10:26
 * @version 1.0
 */

@ManagedBean
@ViewScoped
@Configurable(preConstruction = true)
public class ApplicantProfileBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(ApplicantProfileBean.class);

    @Autowired
    private ApplicantInformationService applicantInformationService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private CityService cityService;
    @Autowired
    private InstitutionService institutionService;

    private ApplicantInformationVo applicantInformationVo;
    private AccountVo accountVo;
    private String applicantId;
    private List<CountryVo> countryVos;
    private List<ProvinceVo> provinceVos;
    private List<CityVo> cityVos;
    private List<InstitutionVo> institutionVos;

    public ApplicantProfileBean() {
        LOG.debug("init ApplicantProfileBean...");
        applicantInformationVo = new ApplicantInformationVo();
        accountVo = new AccountVo();
        applicantId = (String) JSFUtil.getSessionMap().get("applicantId");
        countryVos = countryService.findList();
        provinceVos = new ArrayList<ProvinceVo>();
        cityVos = new ArrayList<CityVo>();
        institutionVos = new ArrayList<InstitutionVo>();
        if (StringUtils.isNotBlank(applicantId)) {
            applicantInformationVo = applicantInformationService.getById(applicantId);
            accountVo = accountService.getByApplicantInfoId(applicantId);
            if (StringUtils.isNotBlank(applicantInformationVo.getStudyCountryId())) {
                provinceVos = provinceService.findByCountryId(applicantInformationVo.getStudyCountryId());
            }
            if (StringUtils.isNotBlank(applicantInformationVo.getStudyProvinceId())) {
                cityVos = cityService.findByCountryIdOrProvinceId(applicantInformationVo.getStudyCountryId(),
                        applicantInformationVo.getStudyProvinceId());
            }
            institutionVos = institutionService.findInstitutions(applicantInformationVo.getStudyCountryId(),
                    applicantInformationVo.getStudyProvinceId(), applicantInformationVo.getStudyCityId());
        }
    }

    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    public ApplicantInformationVo getApplicantInformationVo() {
        return applicantInformationVo;
    }

    public void setApplicantInformationVo(ApplicantInformationVo applicantInformationVo) {
        this.applicantInformationVo = applicantInformationVo;
    }

    public AccountVo getAccountVo() {
        return accountVo;
    }

    public void setAccountVo(AccountVo accountVo) {
        this.accountVo = accountVo;
    }

    public List<CountryVo> getCountryVos() {
        return countryVos;
    }

    public void setCountryVos(List<CountryVo> countryVos) {
        this.countryVos = countryVos;
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

    public List<InstitutionVo> getInstitutionVos() {
        return institutionVos;
    }

    public void setInstitutionVos(List<InstitutionVo> institutionVos) {
        this.institutionVos = institutionVos;
    }

}