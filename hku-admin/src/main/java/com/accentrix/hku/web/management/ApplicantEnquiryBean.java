package com.accentrix.hku.web.management;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.model.LazyDataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.accentrix.hku.constant.ConstantCommon;
import com.accentrix.hku.constant.Constants;
import com.accentrix.hku.service.applicant.AccountService;
import com.accentrix.hku.service.applicant.AnncmntService;
import com.accentrix.hku.service.applicant.ApplicantInformationService;
import com.accentrix.hku.service.applicant.ApplicantToTagService;
import com.accentrix.hku.service.cpc.CityService;
import com.accentrix.hku.service.cpc.CountryService;
import com.accentrix.hku.service.cpc.ProvinceService;
import com.accentrix.hku.service.tag.TagService;
import com.accentrix.hku.util.JSFUtil;
import com.accentrix.hku.util.UIUtil;
import com.accentrix.hku.vo.applicant.AccountVo;
import com.accentrix.hku.vo.applicant.AnncmntVo;
import com.accentrix.hku.vo.applicant.ApplicantInformationVo;
import com.accentrix.hku.vo.applicant.ApplicantToTagVo;
import com.accentrix.hku.vo.cpc.CityVo;
import com.accentrix.hku.vo.cpc.CountryVo;
import com.accentrix.hku.vo.cpc.ProvinceVo;
import com.accentrix.hku.vo.tag.TagVo;
import com.accentrix.hku.web.common.InternationalizationBean;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年4月27日 下午4:38:15
 * @version 1.0
 */

@ManagedBean
@ViewScoped
@Configurable(preConstruction = true)
public class ApplicantEnquiryBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(ApplicantEnquiryBean.class);

    @Autowired
    private ApplicantToTagService applicantToTagService;
    @Autowired
    private TagService tagService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private CityService cityService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private AnncmntService anncmntService;
    @Autowired
    private ApplicantInformationService applicantInformationService;

    private ApplicantInformationVo applicantInformationVo;
    private LazyDataModel<ApplicantInformationVo> lazyDataModel;
    private List<ApplicantInformationVo> applicantInfo;
    private String tags;
    private List<CountryVo> countryVos;
    private List<ProvinceVo> provinces;
    private List<CityVo> citys;
    private boolean isActiveProvinceOrCity;
    private boolean status;
    private boolean divStatus;
    private String title;
    private String msgContent;
    private boolean sendEmail;

    public ApplicantEnquiryBean() {
        LOG.debug("ApplicantEnquiryBean init...");
        init();
    }

    private void init() {
        applicantInformationVo = new ApplicantInformationVo();
        lazyDataModel = new LazyApplicantInformationDataModel(applicantInformationVo);
        countryVos = countryService.findList();
        provinces = new ArrayList<ProvinceVo>();
        citys = new ArrayList<CityVo>();
        isActiveProvinceOrCity = true;
        status = false;
    }

    public void search() {
        if (StringUtils.isNotBlank(applicantInformationVo.getAdmissionYearStr())) {
            applicantInformationVo.setAdmissionYear(Integer.valueOf(applicantInformationVo.getAdmissionYearStr()));
        }
        status = true;
        lazyDataModel = new LazyApplicantInformationDataModel(applicantInformationVo);
    }

    public void reset() {
        LOG.debug("ApplicantEnquiryBean reset...");
        init();
    }

    public void toApplicantToTags(String mode) {
        if (applicantInfo != null && applicantInfo.size() == 0) {
            UIUtil.displayManFieldMissingDialog(InternationalizationBean.locale);
            return;
        }
        if ("tag".equals(mode)) {
            tags = "";
            UIUtil.show("tagDialog");
        } else if ("anncmnt".equals(mode)) {
            title = "";
            msgContent = "";
            sendEmail = false;
            UIUtil.show("batchAnncmntDialog");
        }
    }

    public void saveApplicantToTags() {
        if (validateTagsContent()) {
            UIUtil.displayManFieldMissingDialog(InternationalizationBean.locale);
            return;
        }
        for (ApplicantInformationVo vo : applicantInfo) {
            String[] tag = tags.split(",");
            for (String t : tag) {
                String tagName = t.substring(1, t.length());
                TagVo tagVo = new TagVo();
                tagVo.setDesc(tagName);
                tagVo = tagService.save(tagVo);
                ApplicantToTagVo applicantToTagVo = new ApplicantToTagVo();
                applicantToTagVo.setApplicantInfoId(vo.getId());
                applicantToTagVo.setTagId(tagVo.getId());
                applicantToTagService.save(applicantToTagVo);
            }
        }
        lazyDataModel = new LazyApplicantInformationDataModel(applicantInformationVo);
        applicantInfo = new ArrayList<ApplicantInformationVo>();
        UIUtil.hide("tagDialog");
    }

    public void loadProvinceOrCityList(AjaxBehaviorEvent event) {
        SelectOneMenu menu = (SelectOneMenu) event.getSource();
        String countryId = menu.getValue().toString();
        if (StringUtils.isNotBlank(countryId)) {
            CountryVo c = countryService.get(countryId);
            if (c.getCode().equals("CHN")) {
                citys = null;
                provinces = provinceService.findByCountryId(countryId);
                isActiveProvinceOrCity = true;
            } else {
                provinces = null;
                citys = cityService.findByCountryIdOrProvinceId(countryId, null);
                isActiveProvinceOrCity = false;
            }
        } else {
            isActiveProvinceOrCity = true;
            provinces = null;
            citys = null;
        }
    }

    private boolean validateTagsContent() {
        boolean value = false;
        if (StringUtils.isEmpty(tags)) {
            UIUtil.setInvalid("tagForm:tag");
            value = true;
        } else {
            String[] tag = tags.split(",");
            for (String t : tag) {
                String tagStr = t.substring(0, 1);
                if (!tagStr.equals("#")) {
                    UIUtil.setInvalid("tagForm:tag");
                    return true;
                }
            }
        }
        return value;
    }

    public void clickSearch(String value) {
        divStatus = Boolean.valueOf(value);
        applicantInformationVo = new ApplicantInformationVo();
    }

    public void toApplicant(String applicantId) {
        JSFUtil.getSessionMap().put("applicantId", applicantId);
    }

    public ApplicantInformationVo getApplicantInformationVo() {
        return applicantInformationVo;
    }

    public void sendBatchAnnouncement() {
        if (validateAnnouncement()) {
            for (ApplicantInformationVo appInfoVo : applicantInfo) {
                AccountVo account = accountService.getByApplicantInfoId(appInfoVo.getId());
                AnncmntVo anncmnt = new AnncmntVo();
                anncmnt.setValue(title);
                anncmnt.setMsgContent(msgContent);
                anncmnt.setStatusCd(Constants.ANNCMNT_STATUS_NEW);
                anncmnt.setIssueDate(new Date());
                anncmnt.setApplicantAccountId(account.getId());
                anncmnt.setIsRead(false);
                anncmntService.save(anncmnt);
                if (sendEmail) {

                }
                appInfoVo.setBatchAnncmntDate(new Date());
                appInfoVo = applicantInformationService.save(appInfoVo);
            }
            lazyDataModel = new LazyApplicantInformationDataModel(applicantInformationVo);
            UIUtil.hide("batchAnncmntDialog");
        }
    }

    private boolean validateAnnouncement() {
        boolean valid = true;
        if (StringUtils.isBlank(title)) {
            UIUtil.setInvalid(":batchAnncmntForm:title");
            valid = false;
        }
        if (StringUtils.isBlank(msgContent)) {
            UIUtil.setInvalid(":batchAnncmntForm:msgContent");
            valid = false;
        }
        if (!valid) {
            UIUtil.displayErrorDialog(ConstantCommon.LOCALE_UK, ConstantCommon.MADATORY_FIELD_MISSING,
                    ConstantCommon.MADATORY_FIELD_MISSING_CHI);
        }
        return valid;
    }

    public String formatDateYyyyMMdd(Date date) {
        return date == null ? "" : DateFormatUtils.format(date, "yyyy-MM-dd", TimeZone.getTimeZone("UTC+8:00"));
    }

    public void setApplicantInformationVo(ApplicantInformationVo applicantInformationVo) {
        this.applicantInformationVo = applicantInformationVo;
    }

    public LazyDataModel<ApplicantInformationVo> getLazyDataModel() {
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<ApplicantInformationVo> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public List<ApplicantInformationVo> getApplicantInfo() {
        return applicantInfo;
    }

    public void setApplicantInfo(List<ApplicantInformationVo> applicantInfo) {
        this.applicantInfo = applicantInfo;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
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

    public boolean getIsActiveProvinceOrCity() {
        return isActiveProvinceOrCity;
    }

    public void setIsActiveProvinceOrCity(boolean isActiveProvinceOrCity) {
        this.isActiveProvinceOrCity = isActiveProvinceOrCity;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getDivStatus() {
        return divStatus;
    }

    public void setDivStatus(boolean divStatus) {
        this.divStatus = divStatus;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public boolean isSendEmail() {
        return sendEmail;
    }

    public void setSendEmail(boolean sendEmail) {
        this.sendEmail = sendEmail;
    }
}
