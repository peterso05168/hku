package com.accentrix.hku.web.configuration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.accentrix.hku.constant.ConstantCommon;
import com.accentrix.hku.service.app.RequirementService;
import com.accentrix.hku.service.scholar.ScholarDtlRequirementService;
import com.accentrix.hku.service.scholar.ScholarDtlService;
import com.accentrix.hku.service.scholar.ScholarService;
import com.accentrix.hku.util.JSFUtil;
import com.accentrix.hku.util.UIUtil;
import com.accentrix.hku.vo.app.RequirementVo;
import com.accentrix.hku.vo.scholar.ScholarDtlRequirementVo;
import com.accentrix.hku.vo.scholar.ScholarDtlVo;
import com.accentrix.hku.vo.scholar.ScholarVo;

@ManagedBean
@ViewScoped
@Configurable(preConstruction = true)
public class ScholarEditBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private ScholarService scholarService;

    @Autowired
    private ScholarDtlService scholarDtlService;

    @Autowired
    private ScholarDtlRequirementService scholarDtlRequirementService;

    @Autowired
    private RequirementService requirementService;

    private String scholarId;

    private ScholarVo scholarVo;

    private List<ScholarDtlVo> scholarDtlVos;

    private ScholarDtlVo scholarDtlVo;

    private List<RequirementVo> requirementVos;

    private List<ScholarTierVo> tiers;

    private String editTier;

    //    private List<String> excludedRequirementIds;

    public ScholarEditBean() {
        init();
    }

    public void init() {
        tiers = ScholarTierVo.initTiersDropDownList();
        scholarId = (String) JSFUtil.getSessionMap().get("scholarId");
        if (StringUtils.isNotBlank(scholarId)) {
            scholarVo = scholarService.get(scholarId);
            scholarDtlVos = scholarDtlService.findByScholarId(scholarId);
            //            excludedRequirementIds = new ArrayList<String>();
            for (ScholarDtlVo scholarDtlVo : scholarDtlVos) {
                hideOrShowTierSelectItem(scholarDtlVo.getTier(), true);
                //                excludedRequirementIds.addAll(scholarDtlVo.getRequirementIds());
            }
            //            requirementVos = requirementService.findByIdNotIn(excludedRequirementIds);
            requirementVos = requirementService.findList(new RequirementVo());
        }
        scholarDtlVo = new ScholarDtlVo();
    }

    private void hideOrShowTierSelectItem(String editTier, boolean itemDisabled) {
        for (ScholarTierVo tier : tiers) {
            if (editTier.equals(tier.getTier()))
                tier.setItemDisabled(itemDisabled);
        }
    }

    public void saveScholarDtl() {
        if (!validateContent())
            return;
        if (StringUtils.isBlank(scholarDtlVo.getId())) {
            scholarDtlVo.setScholarId(scholarVo.getId());
            scholarDtlVos.add(scholarDtlVo);
        }
        if (StringUtils.isNotBlank(editTier) && StringUtils.isBlank(scholarDtlVo.getTier()))
            scholarDtlVo.setTier(editTier);
        else {
            hideOrShowTierSelectItem(scholarDtlVo.getTier(), true);
            if (StringUtils.isNotBlank(editTier))
                hideOrShowTierSelectItem(editTier, false);
        }
        for (RequirementVo reqVo : requirementVos) {
            if (scholarDtlVo.getRequirementNames().contains(reqVo.getDescription())) {
                //                excludedRequirementIds.add(reqVo.getId());
                scholarDtlVo.getRequirementIds().add(reqVo.getId());
            }
        }
        //        requirementVos = requirementService.findByIdNotIn(excludedRequirementIds);
        UIUtil.hide("editTierDialog");
    }

    private boolean validateContent() {
        boolean valid = true;
        if (StringUtils.isBlank(scholarDtlVo.getTier())) {
            UIUtil.setInvalid("formEditTier:tier");
            valid = false;
        }
        if (CollectionUtils.isEmpty(scholarDtlVo.getRequirementNames())) {
            UIUtil.setInvalid("formEditTier:multipleCondition");
            valid = false;
        }
        if (scholarDtlVo.getAmount() == null || scholarDtlVo.getAmount().intValue() <= 0) {
            UIUtil.setInvalid("formEditTier:amount");
            valid = false;
        }
        if (!valid) {
            UIUtil.displayErrorDialog(ConstantCommon.LOCALE_UK, ConstantCommon.MADATORY_FIELD_MISSING,
                    ConstantCommon.MADATORY_FIELD_MISSING_CHI);
            return false;
        }
        return true;
    }

    public void removeScholarDtl() {
        hideOrShowTierSelectItem(scholarDtlVo.getTier(), false);
        //        excludedRequirementIds.addAll(scholarDtlVo.getRequirementIds());
        //        requirementVos = requirementService.findByIdNotIn(excludedRequirementIds);
        scholarDtlVos.remove(scholarDtlVo);
    }

    public void resetEditScholarDtlDlg() {
        scholarDtlVo = new ScholarDtlVo();
        scholarDtlVo.setRequirementIds(new ArrayList<String>());
    }

    public void save() {
        scholarService.save(scholarVo);
        List<ScholarDtlVo> deleteScholarDtlVos = scholarDtlService.findByScholarId(scholarId);
        if (deleteScholarDtlVos.size() > scholarDtlVos.size()) {
            deleteScholarDtlVos.removeAll(scholarDtlVos);
            for (ScholarDtlVo deleteVo : deleteScholarDtlVos) {
                List<ScholarDtlRequirementVo> dtlReqVos = scholarDtlRequirementService
                        .findByScholarDtlId(deleteVo.getId());
                for (ScholarDtlRequirementVo dtlReqVo : dtlReqVos) {
                    scholarDtlRequirementService.delete(dtlReqVo);
                }
                scholarDtlService.delete(deleteVo);
            }
        }
        scholarDtlVos = scholarDtlService.save(scholarDtlVos);
        for (ScholarDtlVo scholarDtlVo : scholarDtlVos) {
            for (String requirementId : scholarDtlVo.getRequirementIds()) {
                ScholarDtlRequirementVo dltReqVo = new ScholarDtlRequirementVo();
                dltReqVo.setScholarDtlId(scholarDtlVo.getId());
                dltReqVo.setRequirementId(requirementId);
                scholarDtlRequirementService.save(dltReqVo);
            }
        }
        UIUtil.displaySaveSuccessDialog(ConstantCommon.LOCALE_UK);
    }

    public ScholarVo getScholarVo() {
        return scholarVo;
    }

    public void setScholarVo(ScholarVo scholarVo) {
        this.scholarVo = scholarVo;
    }

    public List<ScholarDtlVo> getScholarDtlVos() {
        return scholarDtlVos;
    }

    public void setScholarDtlVos(List<ScholarDtlVo> scholarDtlVos) {
        this.scholarDtlVos = scholarDtlVos;
    }

    public ScholarDtlVo getScholarDtlVo() {
        return scholarDtlVo;
    }

    public void setScholarDtlVo(ScholarDtlVo scholarDtlVo) {
        this.scholarDtlVo = scholarDtlVo;
    }

    public List<RequirementVo> getRequirementVos() {
        return requirementVos;
    }

    public void setRequirementVos(List<RequirementVo> requirementVos) {
        this.requirementVos = requirementVos;
    }

    public List<ScholarTierVo> getTiers() {
        return tiers;
    }

    public void setTiers(List<ScholarTierVo> tiers) {
        this.tiers = tiers;
    }

    public String getEditTier() {
        return editTier;
    }

    public void setEditTier(String editTier) {
        this.editTier = editTier;
    }

}
