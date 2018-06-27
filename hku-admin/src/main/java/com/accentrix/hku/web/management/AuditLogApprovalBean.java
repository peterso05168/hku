package com.accentrix.hku.web.management;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.accentrix.hku.common.enumeration.SearchMode;
import com.accentrix.hku.constant.ConstantCommon;
import com.accentrix.hku.constant.Constants;
import com.accentrix.hku.service.app.ProgrammeChoiceService;
import com.accentrix.hku.service.app.QualificationService;
import com.accentrix.hku.service.applicant.ApplicationService;
import com.accentrix.hku.util.UIUtil;
import com.accentrix.hku.vo.app.ProgrammeChoiceVo;
import com.accentrix.hku.vo.app.QualificationVo;
import com.accentrix.hku.vo.applicant.ApplicationVo;
import com.accentrix.hku.web.common.BaseSearchBean;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年5月4日 下午1:40:52
 */
@ManagedBean
@ViewScoped
@Configurable(preConstruction = true)
public class AuditLogApprovalBean extends BaseSearchBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(AuditLogApprovalBean.class);

    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private QualificationService qualificationService;
    @Autowired
    private ProgrammeChoiceService programmeChoiceService;

    private List<ApplicationVo> applicationVos;
    private ApplicationVo searchVo;
    private String basicSearchCriteria;
    private List<ApplicationVo> selectApplicationVos;

    public AuditLogApprovalBean() {
        LOG.debug("init auditLogApprovalBean...");
        init();
    }

    public void init() {
        searchMode = SearchMode.Basic;
        searchVo = new ApplicationVo();
        basicSearchCriteria = "";
    }

    @Override
    public void search() {
        result = true;
        LOG.info("searchMode is " + searchMode);
        switch (searchMode) {
        case Basic:
            applicationVos = applicationService.basicSearch(basicSearchCriteria);
            break;
        case Advance:
            applicationVos = applicationService.advancedSearch(searchVo);
            break;
        }
        selectApplicationVos = new ArrayList<ApplicationVo>();
    }

    @Override
    public void reset() {
        result = false;
        basicSearchCriteria = "";
        searchVo = new ApplicationVo();
        applicationVos = new ArrayList<ApplicationVo>();
        selectApplicationVos = new ArrayList<ApplicationVo>();
    }

    @Override
    public void edit(String id) {
    }

    public void singleApproval(String approvalId, boolean isRemoveQualification, boolean isRemoveProgChoice) {
        approval(approvalId, isRemoveQualification, isRemoveProgChoice);
        search();
    }

    public void batchApproval() {
        if (CollectionUtils.isNotEmpty(selectApplicationVos)) {
            for (ApplicationVo applicationVo : selectApplicationVos) {
                approval(applicationVo.getApprovalId(), applicationVo.getIsRemoveQualification(),
                        applicationVo.getIsRemoveProgChoice());
            }
        } else {
            UIUtil.displayErrorDialog(ConstantCommon.LOCALE_UK, Constants.NO_RECORD_SELECTED,
                    Constants.NO_RECORD_SELECTED_CHI);
        }
        search();
    }

    private void approval(String approvalId, boolean isRemoveQualification, boolean isRemoveProgChoice) {
        if (StringUtils.isNotBlank(approvalId)) {
            if (isRemoveQualification) {
                QualificationVo qualificationVo = qualificationService.get(approvalId);
                qualificationVo.setIsDeleteApproved(true);
                qualificationService.save(qualificationVo);
            }
            if (isRemoveProgChoice) {
                ProgrammeChoiceVo programmeChoiceVo = programmeChoiceService.get(approvalId);
                programmeChoiceVo.setIsWithdrawnApproved(true);
                programmeChoiceService.save(programmeChoiceVo);
            }
        }
    }

    public List<ApplicationVo> getApplicationVos() {
        return applicationVos;
    }

    public void setApplicationVos(List<ApplicationVo> applicationVos) {
        this.applicationVos = applicationVos;
    }

    public String getBasicSearchCriteria() {
        return basicSearchCriteria;
    }

    public void setBasicSearchCriteria(String basicSearchCriteria) {
        this.basicSearchCriteria = basicSearchCriteria;
    }

    public ApplicationVo getSearchVo() {
        return searchVo;
    }

    public void setSearchVo(ApplicationVo searchVo) {
        this.searchVo = searchVo;
    }

    public List<ApplicationVo> getSelectApplicationVos() {
        return selectApplicationVos;
    }

    public void setSelectApplicationVos(List<ApplicationVo> selectApplicationVos) {
        this.selectApplicationVos = selectApplicationVos;
    }

}
