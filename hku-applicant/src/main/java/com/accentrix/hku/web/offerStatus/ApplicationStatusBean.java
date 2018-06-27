package com.accentrix.hku.web.offerStatus;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.StreamedContent;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.accentrix.hku.constant.ConstantCommon;
import com.accentrix.hku.constant.Constants;
import com.accentrix.hku.service.app.ProgrammeChoiceService;
import com.accentrix.hku.service.applicant.ApplicantInformationService;
import com.accentrix.hku.service.applicant.ApplicationService;
import com.accentrix.hku.service.general.RefCdService;
import com.accentrix.hku.service.report.ReportGenerationService;
import com.accentrix.hku.util.UIUtil;
import com.accentrix.hku.util.report.ReportGenerator;
import com.accentrix.hku.util.sys.AccountUtils;
import com.accentrix.hku.vo.app.ProgrammeChoiceVo;
import com.accentrix.hku.vo.applicant.AccountVo;
import com.accentrix.hku.vo.applicant.ApplicantInformationVo;
import com.accentrix.hku.vo.applicant.ApplicationVo;
import com.accentrix.hku.vo.general.RefCdVo;
import com.accentrix.hku.web.common.InternationalizationBean;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年5月25日 上午10:01:30
 */
@ManagedBean
@ViewScoped
@Configurable(preConstruction = true)
public class ApplicationStatusBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(ApplicationStatusBean.class);

    @Autowired
    private ApplicantInformationService applicantInformationService;
    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private ReportGenerationService reportGenerationService;
    @Autowired
    private ProgrammeChoiceService programmeChoiceService;
    @Autowired
    private RefCdService refCdService;

    private ApplicantInformationVo applicantInformationVo;
    private List<ApplicationVo> applicationVos;
    private ProgrammeChoiceVo programmeChoiceVo;
    private AccountVo accountVo;
    private List<RefCdVo> reasons;

    private StreamedContent downloadFile;

    public ApplicationStatusBean() {
        init();
    }

    public void init() {
        LOG.debug("ApplicationStatusBean init...");
        applicantInformationVo = new ApplicantInformationVo();
        applicationVos = new ArrayList<ApplicationVo>();
        programmeChoiceVo = new ProgrammeChoiceVo();

        retrieveData();
    }

    private void retrieveData() {
        LOG.info("retrieveData() start");
        accountVo = AccountUtils.getAccountVo();
        applicantInformationVo = applicantInformationService.get(accountVo.getUserInfoId());
        applicationVos = applicationService.findByAccountIdAndStatus(accountVo.getId(), Constants.STATUS_SUBMITTED);
        reasons = refCdService.findListByType(Constants.WITHDRAWN_ACCEPTANCE_REASON);
        LOG.info("retrieveData() end");
    }

    public void downloadPaymentInstruction() {
        List<String> reportData = reportGenerationService.getReportData();
        Map<String, Object> params = new HashMap<String, Object>();
        try {
            downloadFile = ReportGenerator.generaterPDF("/jrxml/TTS_Form.jrxml", "TTS_Form.PDF", params,
                    new JRBeanCollectionDataSource(reportData), null);
        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editApplicationStatus(String id) {
        LOG.info("editApplicationStatus() start" + id);
        if (StringUtils.isNotBlank(id)) {
            programmeChoiceVo = programmeChoiceService.get(id);
        } else {
            programmeChoiceVo = new ProgrammeChoiceVo();
        }
        LOG.info("editApplicationStatus() end");
    }

    public void responseOffer(String status) {
        LOG.info("responseOffer() start" + status);
        if (StringUtils.isNotBlank(programmeChoiceVo.getId())) {
            programmeChoiceVo.setOfferStatusCd(status);
            programmeChoiceService.save(programmeChoiceVo);
            applicationVos = applicationService.findByAccountIdAndStatus(accountVo.getId(), Constants.STATUS_SUBMITTED);
        }
        LOG.info("responseOffer() end");
    }

    public void withdrawnOffer() {
        LOG.info("withdrawnOffer() start");
        if (StringUtils.isNotBlank(programmeChoiceVo.getId())) {
            if (StringUtils.isBlank(programmeChoiceVo.getWdraCd())) {
                UIUtil.setInvalid("formWithdrawReason:withdrawReason");
                UIUtil.displayErrorDialog(InternationalizationBean.locale, ConstantCommon.MADATORY_FIELD_MISSING,
                        ConstantCommon.MADATORY_FIELD_MISSING_CHI);
            } else {
                programmeChoiceVo.setOfferStatusCd(Constants.PROG_CHOICE_OW_CD);
                programmeChoiceService.save(programmeChoiceVo);
                applicationVos = applicationService.findByAccountIdAndStatus(accountVo.getId(),
                        Constants.STATUS_SUBMITTED);
                UIUtil.execute("PF('withdrawAcceptDialog').hide()");
            }
        }
        LOG.info("withdrawnOffer() end");
    }

    public String dateFormat(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 定义要输出日期字符串的
        return date != null ? sdf.format(date) : "";
    }

    public ApplicantInformationVo getApplicantInformationVo() {
        return applicantInformationVo;
    }

    public void setApplicantInformationVo(ApplicantInformationVo applicantInformationVo) {
        this.applicantInformationVo = applicantInformationVo;
    }

    public List<ApplicationVo> getApplicationVos() {
        return applicationVos;
    }

    public void setApplicationVos(List<ApplicationVo> applicationVos) {
        this.applicationVos = applicationVos;
    }

    public StreamedContent getDownloadFile() {
        return downloadFile;
    }

    public ProgrammeChoiceVo getProgrammeChoiceVo() {
        return programmeChoiceVo;
    }

    public void setProgrammeChoiceVo(ProgrammeChoiceVo programmeChoiceVo) {
        this.programmeChoiceVo = programmeChoiceVo;
    }

    public List<RefCdVo> getReasons() {
        return reasons;
    }

    public void setReasons(List<RefCdVo> reasons) {
        this.reasons = reasons;
    }

}
