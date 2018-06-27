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

import com.accentrix.hku.common.enumeration.SearchMode;
import com.accentrix.hku.constant.Constants;
import com.accentrix.hku.service.adm.FormProgService;
import com.accentrix.hku.service.app.FacultyService;
import com.accentrix.hku.service.general.RefCdService;
import com.accentrix.hku.util.JSFUtil;
import com.accentrix.hku.vo.adm.FormProgVo;
import com.accentrix.hku.vo.app.FacultyVo;
import com.accentrix.hku.vo.general.RefCdVo;
import com.accentrix.hku.web.common.BaseSearchBean;

@ManagedBean
@ViewScoped
@Configurable(preConstruction = true)
public class ProgrammeSearchBean extends BaseSearchBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(ProgrammeSearchBean.class);

    @Autowired
    private FormProgService formProgService;

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private RefCdService refCdService;

    private FormProgVo searchVo;

    private List<FormProgVo> FormProgVos;

    private List<FacultyVo> faculties;

    private List<RefCdVo> refCds;

    private String exeId;

    public ProgrammeSearchBean() {
        init();
    }

    public void init() {
        LOG.info("Programme");
        basicSearchCriteria = (String) JSFUtil.getSessionMap().get("basicSearchCriteria");
        searchVo = (FormProgVo) JSFUtil.getSessionMap().get("programmeSearchCriteria");
        if (searchVo != null) {
            FormProgVos = formProgService.advancedSearch(searchVo);
            result = true;
            searchMode = SearchMode.Advance;
            JSFUtil.getSessionMap().remove("programmeSearchCriteria");
        } else if (basicSearchCriteria != null) {
            FormProgVos = formProgService.basicSearch(basicSearchCriteria);
            result = true;
            searchMode = SearchMode.Basic;
            JSFUtil.getSessionMap().remove("basicSearchCriteria");
        } else
            searchVo = new FormProgVo();
        faculties = facultyService.findList();
        refCds = refCdService.findListByType(Constants.LVLOFSTUDY);
    }

    @Override
    public void search() {
        result = true;
        LOG.info("searchMode is " + searchMode);
        switch (searchMode) {
        case Basic:
            FormProgVos = formProgService.basicSearch(basicSearchCriteria);
            break;
        case Advance:
            FormProgVos = formProgService.advancedSearch(searchVo);
        }
    }

    @Override
    public void reset() {
        basicSearchCriteria = "";
        searchVo = new FormProgVo();
        FormProgVos = new ArrayList<FormProgVo>();
        result = false;
    }

    @Override
    public void edit(String id) {
        JSFUtil.getSessionMap().put("formProgId", id);
        switch (searchMode) {
        case Basic:
            JSFUtil.getSessionMap().put("basicSearchCriteria", basicSearchCriteria);
            break;
        case Advance:
            JSFUtil.getSessionMap().put("programmeSearchCriteria", searchVo);
        }
    }

    public void delete(String id) {
        formProgService.delete(id);
        search();
    }

    public List<FacultyVo> getFaculties() {
        return faculties;
    }

    public void setFaculties(List<FacultyVo> faculties) {
        this.faculties = faculties;
    }

    public List<RefCdVo> getRefCds() {
        return refCds;
    }

    public void setRefCds(List<RefCdVo> refCds) {
        this.refCds = refCds;
    }

    public FormProgVo getSearchVo() {
        return searchVo;
    }

    public void setSearchVo(FormProgVo searchVo) {
        this.searchVo = searchVo;
    }

    public List<FormProgVo> getFormProgVos() {
        return FormProgVos;
    }

    public void setFormProgVos(List<FormProgVo> formProgVos) {
        FormProgVos = formProgVos;
    }

    public String getExeId() {
        return exeId;
    }

    public void setExeId(String exeId) {
        this.exeId = exeId;
    }
}
