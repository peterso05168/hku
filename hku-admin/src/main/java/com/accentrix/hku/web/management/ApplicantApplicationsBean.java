package com.accentrix.hku.web.management;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.accentrix.hku.service.adm.ExeService;
import com.accentrix.hku.service.adm.FormService;
import com.accentrix.hku.service.applicant.ApplicationService;
import com.accentrix.hku.util.JSFUtil;
import com.accentrix.hku.vo.adm.ExeVo;
import com.accentrix.hku.vo.adm.FormVo;
import com.accentrix.hku.vo.applicant.ApplicationVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年5月8日 下午2:15:02
 * @version 1.0
 */

@ManagedBean
@ViewScoped
@Configurable(preConstruction = true)
public class ApplicantApplicationsBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(ApplicantApplicationsBean.class);

    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private FormService formService;
    @Autowired
    private ExeService exeService;

    private List<ApplicationVo> applicationVos;

    public ApplicantApplicationsBean() {
        LOG.debug("ApplicantApplicationsBean init ...");
        String applicantId = (String) JSFUtil.getSessionMap().get("applicantId");
        if (StringUtils.isNoneBlank(applicantId)) {
            applicationVos = applicationService.findByApplicantId(applicantId);
        }
    }

    public String toEditApplicant(String applicationId) {
        if (StringUtils.isNotBlank(applicationId)) {
            ApplicationVo applicationVo = applicationService.get(applicationId);
            FormVo formVo = formService.get(applicationVo.getAdmFormId());
            ExeVo exeVo = exeService.get(formVo.getAdmExeId());
            JSFUtil.getSessionMap().put("applicationId", applicationVo.getId());
            JSFUtil.getSessionMap().put("admFormId", applicationVo.getAdmFormId());
            JSFUtil.getSessionMap().put("housingMgmtId", applicationVo.getAcadQualHousingMgmtId());
            JSFUtil.getSessionMap().put("nursingId", applicationVo.getAcadQualNursingId());
            JSFUtil.getSessionMap().put("appEndDate", exeVo.getApplicationEndDate());
            JSFUtil.getSessionMap().put("choiceEndDate", exeVo.getProgrammeChoiceEndDate());
            JSFUtil.getSessionMap().put("admissionYear", exeVo.getAdmissionYear());
        }
        return "/hku/admin/management/applicant/applicant-edit.xhtml?faces-redirect=true";
    }

    public List<ApplicationVo> getApplicationVos() {
        return applicationVos;
    }

    public void setApplicationVos(List<ApplicationVo> applicationVos) {
        this.applicationVos = applicationVos;
    }
}
