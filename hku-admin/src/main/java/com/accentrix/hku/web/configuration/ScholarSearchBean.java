package com.accentrix.hku.web.configuration;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.accentrix.hku.common.enumeration.SearchMode;
import com.accentrix.hku.service.scholar.ScholarService;
import com.accentrix.hku.util.JSFUtil;
import com.accentrix.hku.util.UIUtil;
import com.accentrix.hku.vo.scholar.ScholarVo;
import com.accentrix.hku.web.common.BaseSearchBean;

@ManagedBean
@ViewScoped
@Configurable(preConstruction = true)
public class ScholarSearchBean extends BaseSearchBean {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(ScholarSearchBean.class);

    @Autowired
    private ScholarService scholarService;

    private ScholarVo searchVo;

    private List<ScholarVo> scholarVos;

    private ScholarVo persistVo;

    public ScholarSearchBean() {
        init();
    }

    public void init() {
        LOG.info("Programme");
        basicSearchCriteria = (String) JSFUtil.getSessionMap().get("basicSearchCriteria");
        searchVo = (ScholarVo) JSFUtil.getSessionMap().get("scholarSearchVo");
        if (searchVo != null) {
            scholarVos = scholarService.advancedSearch(searchVo);
            result = true;
            searchMode = SearchMode.Advance;
            JSFUtil.getSessionMap().remove("scholarSearchVo");
        } else if (basicSearchCriteria != null) {
            scholarVos = scholarService.basicSearch(basicSearchCriteria);
            result = true;
            searchMode = SearchMode.Basic;
            JSFUtil.getSessionMap().remove("basicSearchCriteria");
        } else
            searchVo = new ScholarVo();
        persistVo = new ScholarVo();
    }

    @Override
    public void search() {
        result = true;
        LOG.info("searchMode is " + searchMode);
        switch (searchMode) {
        case Basic:
            basicSearchCriteria = basicSearchCriteria == null ? "" : basicSearchCriteria;
            scholarVos = scholarService.basicSearch(basicSearchCriteria);
            break;
        case Advance:
            scholarVos = scholarService.advancedSearch(searchVo);
        }
    }

    @Override
    public void reset() {
        basicSearchCriteria = "";
        searchVo = new ScholarVo();
        scholarVos = new ArrayList<ScholarVo>();
        result = false;
    }

    @Override
    public void edit(String id) {
        JSFUtil.getSessionMap().put("scholarId", id);
        switch (searchMode) {
        case Basic:
            JSFUtil.getSessionMap().put("basicSearchCriteria", basicSearchCriteria);
            break;
        case Advance:
            JSFUtil.getSessionMap().put("scholarSearchVo", searchVo);
        }
    }

    public void delete(String id) {
        scholarService.delete(id);
        search();
    }

    public void save() {
        persistVo.setStatus("Active");
        persistVo = scholarService.save(persistVo);
        edit(persistVo.getId());
        UIUtil.execute("window.location = 'edit.xhtml'");
    }

    public ScholarVo getSearchVo() {
        return searchVo;
    }

    public void setSearchVo(ScholarVo searchVo) {
        this.searchVo = searchVo;
    }

    public List<ScholarVo> getScholarVos() {
        return scholarVos;
    }

    public void setScholarVos(List<ScholarVo> scholarVos) {
        this.scholarVos = scholarVos;
    }

    public ScholarVo getPersistVo() {
        return persistVo;
    }

    public void setPersistVo(ScholarVo persistVo) {
        this.persistVo = persistVo;
    }

}
