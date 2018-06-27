package com.accentrix.hku.web.configuration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang.StringUtils;
import org.primefaces.model.LazyDataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.accentrix.hku.service.adm.ExeService;
import com.accentrix.hku.service.campaign.CampaignService;
import com.accentrix.hku.service.general.RefCdService;
import com.accentrix.hku.util.JSFUtil;
import com.accentrix.hku.util.UIUtil;
import com.accentrix.hku.vo.campaign.CampaignVo;
import com.accentrix.hku.vo.general.RefCdVo;
import com.accentrix.hku.web.common.InternationalizationBean;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年4月10日 上午11:51:29
 * @version 1.0
 */

@ManagedBean
@ViewScoped
@Configurable(preConstruction = true)
public class CampaignBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(CampaignBean.class);
    private static final String CONFIGURING = "Configuring";
    private static final String CAMPCD = "CAMPCD";

    @Autowired
    private CampaignService campaignService;
    @Autowired
    private RefCdService refCdService;
    @Autowired
    private ExeService exeService;

    private LazyDataModel<CampaignVo> lazyDataModel;
    private CampaignVo campaignVo;
    private CampaignVo saveCampaign;
    private boolean status;
    private boolean divStatus;
    private List<Integer> admissionYears;

    public CampaignBean() {
        LOG.debug("Campaign init ...");
        init();
    }

    private void init() {
        status = false;
        campaignVo = new CampaignVo();
        saveCampaign = new CampaignVo();
        admissionYears = new ArrayList<Integer>();
        lazyDataModel = new LazyCampaignDataModel(campaignVo);
    }

    public void search() {
        LOG.debug("Campaign search ...");
        status = true;
        lazyDataModel = new LazyCampaignDataModel(campaignVo);
    }

    public void reset() {
        LOG.debug("Campaign reset ...");
        init();
    }

    public void toCreateCampaign() {
        saveCampaign = new CampaignVo();
        admissionYears = exeService.findAdmissionYear();
    }

    public String save() {
        LOG.debug("Campaign save ...");
        if (validateContentToCampaign()) {
            UIUtil.displayManFieldMissingDialog(InternationalizationBean.locale);
            return null;
        }
        RefCdVo refCdVo = refCdService.getByTypeAndCd(CAMPCD, null);
        int value = Integer.parseInt(refCdVo.getValue()) + 1;
        refCdVo.setValue(value + "");
        refCdService.save(refCdVo);
        saveCampaign.setCpgnCd(refCdVo.getCd() + value);
        saveCampaign.setStatusCd(CONFIGURING);
        saveCampaign = campaignService.save(saveCampaign);
        UIUtil.hide("createDialog");
        JSFUtil.getSessionMap().put("cpgnId", saveCampaign.getId());
        return "edit.xhtml?faces-redirect=true";
    }

    private boolean validateContentToCampaign() {
        boolean value = false;
        if (StringUtils.isEmpty(saveCampaign.getCpgnName())) {
            UIUtil.setInvalid("formCampaign:campaignName");
            value = true;
        }
        if (saveCampaign.getCpgnYear() == null || saveCampaign.getCpgnYear() == 0) {
            UIUtil.setInvalid("formCampaign:campaignYear");
            value = true;
        }
        return value;
    }

    public void clickSearch(String value) {
        divStatus = Boolean.valueOf(value);
        campaignVo = new CampaignVo();
    }

    public String formEdit(String id) {
        JSFUtil.getSessionMap().put("cpgnId", id);
        return "edit.xhtml?faces-redirect=true";
    }

    public LazyDataModel<CampaignVo> getLazyDataModel() {
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<CampaignVo> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public CampaignVo getCampaignVo() {
        return campaignVo;
    }

    public void setCampaignVo(CampaignVo campaignVo) {
        this.campaignVo = campaignVo;
    }

    public CampaignVo getSaveCampaign() {
        return saveCampaign;
    }

    public void setSaveCampaign(CampaignVo saveCampaign) {
        this.saveCampaign = saveCampaign;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isDivStatus() {
        return divStatus;
    }

    public void setDivStatus(boolean divStatus) {
        this.divStatus = divStatus;
    }

    public List<Integer> getAdmissionYears() {
        return admissionYears;
    }

    public void setAdmissionYears(List<Integer> admissionYears) {
        this.admissionYears = admissionYears;
    }
}
