package com.accentrix.hku.web.configuration;

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

import com.accentrix.hku.constant.ConstantCommon;
import com.accentrix.hku.constant.Constants;
import com.accentrix.hku.service.adm.FormProgReqService;
import com.accentrix.hku.service.app.RequirementService;
import com.accentrix.hku.service.general.RefCdService;
import com.accentrix.hku.util.JSFUtil;
import com.accentrix.hku.util.UIUtil;
import com.accentrix.hku.vo.adm.FormProgReqVo;
import com.accentrix.hku.vo.app.RequirementVo;
import com.accentrix.hku.vo.general.RefCdVo;
import com.accentrix.hku.vo.xml.AgeRangeVo;
import com.accentrix.hku.vo.xml.ExaminationVo;
import com.accentrix.hku.vo.xml.GpsVo;

@ManagedBean
@ViewScoped
@Configurable(preConstruction = true)
public class RequirementSearchBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(RequirementSearchBean.class);

    @Autowired
    private RequirementService requirementService;
    @Autowired
    private FormProgReqService formProgReqService;

    @Autowired
    private RefCdService refCdService;

    private List<RequirementVo> requirementVos;

    private RequirementVo requirementSearchVo;

    private RequirementVo requirementPersistVo;

    private List<RefCdVo> refCds;

    private String isPublished;

    private boolean renderedResult;

    public RequirementSearchBean() {
        init();
    }

    public void init() {
        LOG.info("Requirement loading...");
        requirementSearchVo = (RequirementVo) JSFUtil.getSessionMap().get("requirementSearchVo");
        if (requirementSearchVo == null)
            requirementSearchVo = new RequirementVo();
        else {
            requirementVos = requirementService.findList(requirementSearchVo);
            renderedResult = true;
            JSFUtil.getSessionMap().remove("requirementSearchVo");
        }
        requirementPersistVo = new RequirementVo();
        refCds = refCdService.findListByType(Constants.EREQUIREMENT);
    }

    public void save() {
        if (validateContent()) {
            requirementPersistVo.setIsPublished(true);
            if (requirementPersistVo.getType().equals(Constants.AGE))
                requirementPersistVo.setObjClassName(AgeRangeVo.class.getName());
            else if (requirementPersistVo.getType().equals(Constants.PUBLICEXAM))
                requirementPersistVo.setObjClassName(ExaminationVo.class.getName());
            else if (requirementPersistVo.getType().equals(Constants.GPS))
                requirementPersistVo.setObjClassName(GpsVo.class.getName());
            requirementPersistVo = requirementService.save(requirementPersistVo);
            edit(requirementPersistVo.getId());
            UIUtil.execute("window.location = 'edit.xhtml'");
        }
    }

    private boolean validateContent() {
        boolean valid = true;
        boolean uniqueName = true;
        if (StringUtils.isBlank(requirementPersistVo.getDescription())) {
            UIUtil.setInvalid(":createEntranceForm:requirementNameCreate");
            valid = false;
        }
        if (StringUtils.isBlank(requirementPersistVo.getType())) {
            UIUtil.setInvalid(":createEntranceForm:typeCreate");
            valid = false;
        }
        for (RequirementVo requirementVo : requirementVos) {
            if (requirementVo.getDescription().equals(requirementPersistVo.getDescription())) {
                UIUtil.setInvalid(":createEntranceForm:requirementNameCreate");
                UIUtil.displayErrorDialog(ConstantCommon.LOCALE_UK, Constants.REQUIREMENT_NAME_EXISTS,
                        Constants.REQUIREMENT_NAME_EXISTS_CHI);
                uniqueName = false;
                break;
            }
        }
        if (!valid) {
            UIUtil.displayErrorDialog(ConstantCommon.LOCALE_UK, ConstantCommon.MADATORY_FIELD_MISSING,
                    ConstantCommon.MADATORY_FIELD_MISSING_CHI);
        }
        return valid && uniqueName;
    }

    public void edit(String id) {
        JSFUtil.getSessionMap().put("requirementId", id);
        JSFUtil.getSessionMap().put("requirementSearchVo", requirementSearchVo);
    }

    public void search() {
        if ("true".equals(isPublished))
            requirementSearchVo.setIsPublished(true);
        else if ("false".equals(isPublished))
            requirementSearchVo.setIsPublished(false);
        else if (StringUtils.isBlank(isPublished))
            requirementSearchVo.setIsPublished(null);
        requirementVos = requirementService.findList(requirementSearchVo);
        renderedResult = true;
    }

    public void reset() {
        requirementSearchVo = new RequirementVo();
        isPublished = "";
        requirementVos = new ArrayList<RequirementVo>();
        renderedResult = false;
    }

    //    public void delete(String id) {
    //        requirementService.delete(id);
    //        requirementVos = requirementService.findList(requirementSearchVo);
    //    }

    public void inactive(String id) {
        RequirementVo vo = requirementService.get(id);
        vo.setIsPublished(false);
        vo = requirementService.save(vo);
        List<FormProgReqVo> formProgReqs = formProgReqService.getByRequirementId(id);
        for (FormProgReqVo formProgReq : formProgReqs) {
            formProgReqService.delete(formProgReq.getId());
        }
        search();
    }

    public void loadCreateDialog() {
        requirementPersistVo = new RequirementVo();
    }

    public List<RequirementVo> getRequirementVos() {
        return requirementVos;
    }

    public void setRequirementVos(List<RequirementVo> requirementVos) {
        this.requirementVos = requirementVos;
    }

    public RequirementVo getRequirementSearchVo() {
        return requirementSearchVo;
    }

    public void setRequirementSearchVo(RequirementVo requirementSearchVo) {
        this.requirementSearchVo = requirementSearchVo;
    }

    public RequirementVo getRequirementPersistVo() {
        return requirementPersistVo;
    }

    public void setRequirementPersistVo(RequirementVo requirementPersistVo) {
        this.requirementPersistVo = requirementPersistVo;
    }

    public List<RefCdVo> getRefCds() {
        return refCds;
    }

    public void setRefCds(List<RefCdVo> refCds) {
        this.refCds = refCds;
    }

    public String getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(String isPublished) {
        this.isPublished = isPublished;
    }

    public boolean isRenderedResult() {
        return renderedResult;
    }
}
