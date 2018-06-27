package com.accentrix.hku.web.configuration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.accentrix.hku.service.adm.AnncmntTemplateService;
import com.accentrix.hku.util.JSFUtil;
import com.accentrix.hku.vo.adm.AdmAnncmntVo;

@ManagedBean
@ViewScoped
@Configurable(preConstruction = true)
public class AnnouncementSearchBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(AnnouncementSearchBean.class);

    @Autowired
    private AnncmntTemplateService anncmntTemplateService;

    private List<AdmAnncmntVo> admAnncmntVos;

    private String templateName;

    private boolean renderedResult;

    public AnnouncementSearchBean() {
        init();
    }

    public void init() {
        LOG.info("Requirement loading...");
        templateName = (String) JSFUtil.getSessionMap().get("templateName");
        if (templateName != null) {
            admAnncmntVos = anncmntTemplateService.findByTemplateName(templateName);
            renderedResult = true;
            JSFUtil.getSessionMap().remove("templateName");
        }
    }

    public void edit(String id) {
        JSFUtil.getSessionMap().put("anncmntId", id);
        JSFUtil.getSessionMap().put("templateName", templateName);
    }

    public void search() {
        admAnncmntVos = anncmntTemplateService.findByTemplateName(templateName);
        renderedResult = true;
    }

    public void reset() {
        templateName = "";
        admAnncmntVos = new ArrayList<AdmAnncmntVo>();
        renderedResult = false;
    }

    public List<AdmAnncmntVo> getAnncmntVos() {
        return admAnncmntVos;
    }

    public void setAnncmntVos(List<AdmAnncmntVo> admAnncmntVos) {
        this.admAnncmntVos = admAnncmntVos;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public boolean isRenderedResult() {
        return renderedResult;
    }
}
