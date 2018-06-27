package com.accentrix.hku.web.myInbox;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.accentrix.hku.constant.Constants;
import com.accentrix.hku.service.adm.AnncmntTemplateService;
import com.accentrix.hku.service.applicant.AnncmntService;
import com.accentrix.hku.service.applicant.ApplicantInformationService;
import com.accentrix.hku.service.applicant.ApplicationService;
import com.accentrix.hku.util.ConstantsUtils;
import com.accentrix.hku.util.UIUtil;
import com.accentrix.hku.util.UserUtils;
import com.accentrix.hku.util.sys.AccountUtils;
import com.accentrix.hku.vo.adm.AdmAnncmntVo;
import com.accentrix.hku.vo.applicant.AccountVo;
import com.accentrix.hku.vo.applicant.AnncmntVo;
import com.accentrix.hku.vo.applicant.ApplicantInformationVo;
import com.accentrix.hku.vo.applicant.ApplicationVo;

@ManagedBean
@ViewScoped
@Configurable(preConstruction = true)
public class AnnouncementBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(AnnouncementBean.class);

    @Autowired
    private AnncmntService anncmntService;
    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private ApplicantInformationService applicantInformationService;
    @Autowired
    private AnncmntTemplateService anncmntTemplateService;

    private List<AnncmntVo> announcements;

    private ApplicantInformationVo applicantInfoVo;

    private AnncmntVo anncmnt;

    private String[] contents;

    private AccountVo accountVo;

    public AnnouncementBean() {
        accountVo = AccountUtils.getAccountVo();
        List<ApplicationVo> applicationVos = applicationService.findByAccountId(accountVo.getId());
        AdmAnncmntVo admAnncmntVo = anncmntTemplateService.getByTypeCd(Constants.PAYMENT_FAIL_CD);
        for (ApplicationVo vo : applicationVos) {
            if (vo.getStatus().equals(Constants.STATUS_IN_PAYMENT) && StringUtils.isBlank(vo.getApplicationNo())) {
                vo.setStatus(Constants.STATUS_IN_PROCESS);
                applicationService.save(vo);
                String msgContent = admAnncmntVo.getContent().replace("Application No", vo.getApplicationNo());
                anncmntService.saveAnncmnt(Constants.PAYMENT_FAIL_CD, Constants.PAYMENT_FAIL_VALUE,
                        Constants.ANNCMNT_STATUS_NEW, vo.getApplicantAccountId(), null, msgContent, vo.getId());
            }
        }
        retrieveData();
    }

    private void retrieveData() {
        applicantInfoVo = applicantInformationService.get(accountVo.getUserInfoId());
        announcements = anncmntService.findByApplicantAccountId(accountVo.getId());
    }

    public void loadDetail(AnncmntVo announcement) {
        if (!announcement.getIsRead()) {
            announcement.setIsRead(true);
            anncmntService.save(announcement);
            announcements = anncmntService.findByApplicantAccountId(accountVo.getId());
        }
        String str = announcement.getMsgContent();
        while (str.contains("\r\n\r\n")) {
            str = str.replace("\r\n\r\n", "\r\n");
        }
        if (StringUtils.isNotBlank(announcement.getTypeCd())) {
            if (Constants.PAYMENT_FAIL_CD.equals(announcement.getTypeCd())
                    || Constants.PAYMENT_SUCCESS_CD.equals(announcement.getTypeCd())) {
                UIUtil.show("payment");
            } else if (Constants.WELCOME_CD.equals(announcement.getTypeCd())) {
                UIUtil.show("welcome");
            }
        } else {
            UIUtil.show("common");
        }
        contents = str.split("\r\n");
        anncmnt = announcement;
    }

    public void payment(String applicationId) {
        // 支付前修改转态为支付中
        ApplicationVo applicationVo = applicationService.get(applicationId);
        if (applicationVo != null && StringUtils.isNotBlank(applicationVo.getStatus())
                && applicationVo.getStatus().equals(Constants.STATUS_IN_PROCESS)
                && StringUtils.isBlank(applicantInfoVo.getApplicationNo())) {
            applicationVo.setStatus(Constants.STATUS_IN_PAYMENT);
            applicationService.save(applicationVo);
            try {
                FacesContext context = FacesContext.getCurrentInstance();
                HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
                Cookie cookie = new Cookie(UserUtils.APPLICATION_ID, applicationId);
                cookie.setPath(
                        FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath() + "/");
                response.addCookie(cookie);
                ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                externalContext.redirect(
                        ConstantsUtils.SERVER_URL + "PG/pg/pg_redirect?templatesPath=" + ConstantsUtils.FILE_PATH);
            } catch (IOException e) {
                LOG.debug("to payment page fail", e);
            }
        }
    }

    public String formatDateYyyyMMdd(Date date) {
        return date == null ? "" : DateFormatUtils.format(date, "yyyy-MM-dd", TimeZone.getTimeZone("UTC+8:00"));
    }

    public List<AnncmntVo> getAnnouncements() {
        return announcements;
    }

    public String[] getContents() {
        return contents;
    }

    public ApplicantInformationVo getApplicantInfoVo() {
        return applicantInfoVo;
    }

    public AnncmntVo getAnncmnt() {
        return anncmnt;
    }

    public void setAnncmnt(AnncmntVo anncmnt) {
        this.anncmnt = anncmnt;
    }

}
