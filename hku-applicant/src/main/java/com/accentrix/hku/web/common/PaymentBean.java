package com.accentrix.hku.web.common;

import java.io.Serializable;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.accentrix.hku.constant.Constants;
import com.accentrix.hku.service.adm.AnncmntTemplateService;
import com.accentrix.hku.service.adm.ExeService;
import com.accentrix.hku.service.applicant.AnncmntService;
import com.accentrix.hku.service.applicant.ApplicationService;
import com.accentrix.hku.service.general.RefCdService;
import com.accentrix.hku.util.UserUtils;
import com.accentrix.hku.vo.adm.AdmAnncmntVo;
import com.accentrix.hku.vo.adm.ExeVo;
import com.accentrix.hku.vo.applicant.ApplicationVo;
import com.accentrix.hku.vo.general.RefCdVo;
import com.accentrix.hku.vo.payment.PaymentDR;

import hkbea.pg.jetco_receive_DR;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年5月11日 下午6:27:29
 * @version 1.0
 */

@ManagedBean
@ViewScoped
@Configurable(preConstruction = true)
public class PaymentBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(PaymentBean.class);

    @Autowired
    private RefCdService refCdService;
    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private ExeService exeService;
    @Autowired
    private AnncmntService anncmntService;
    @Autowired
    private AnncmntTemplateService anncmntTemplateService;

    private String paramString;

    public PaymentBean() {
        LOG.debug("PaymentBean init...");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        try {
            PaymentDR paymentDR = new PaymentDR();
            String DR = request.getParameter("String1").trim();
            jetco_receive_DR encrypt_DR = new jetco_receive_DR(DR);
            paymentDR.setInvoiceNum(encrypt_DR.get_invoiceNum());
            paymentDR.setRespCode(encrypt_DR.get_respCode());
            paymentDR.setApprCode(encrypt_DR.get_apprCode());
            paymentDR.setRrNo(encrypt_DR.get_rrNo());
            paymentDR.setAddnResp(encrypt_DR.get_addnResp());
            paymentDR.setStatus(encrypt_DR.get_status());
            paymentDR.setTamt(encrypt_DR.get_amount());
            encrypt_DR = null;
            String applicationId = "";
            ApplicationVo applicationVo = new ApplicationVo();
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(UserUtils.APPLICATION_ID)) {
                    applicationId = cookie.getValue();
                }
            }
            if (StringUtils.isNoneBlank(applicationId)) {
                applicationVo = applicationService.get(applicationId);
            }
            AdmAnncmntVo admAnncmntVo = new AdmAnncmntVo();
            if (StringUtils.isNotBlank(applicationId)) {
                if (paymentDR.getRespCode() == 0) {
                    ExeVo exeVo = exeService.getByApplicationId(applicationId);
                    applicationVo.setApplicationNo(getApplicationNo(exeVo.getAdmissionYear()));
                    applicationVo.setStatus(Constants.STATUS_SUBMITTED);
                    applicationVo = applicationService.save(applicationVo);
                    admAnncmntVo = anncmntTemplateService.getByTypeCd(Constants.PAYMENT_SUCCESS_CD);
                    String msgContent = admAnncmntVo.getContent().replace("Application No",
                            applicationVo.getApplicationNo());
                    anncmntService.saveAnncmnt(Constants.PAYMENT_SUCCESS_CD, Constants.PAYMENT_SUCCESS_VALUE,
                            Constants.ANNCMNT_STATUS_NEW, applicationVo.getApplicantAccountId(),
                            applicationVo.getApplicationNo(), msgContent, applicationId);
                } else {
                    applicationVo.setStatus(Constants.STATUS_IN_PROCESS);
                    applicationVo = applicationService.save(applicationVo);
                    admAnncmntVo = anncmntTemplateService.getByTypeCd(Constants.PAYMENT_FAIL_CD);
                    String msgContent = admAnncmntVo.getContent().replace("Application No",
                            applicationVo.getApplicationNo());
                    anncmntService.saveAnncmnt(Constants.PAYMENT_FAIL_CD, Constants.PAYMENT_FAIL_VALUE,
                            Constants.ANNCMNT_STATUS_NEW, applicationVo.getApplicantAccountId(), null, msgContent,
                            applicationId);
                }
            }
            response.sendRedirect(request.getContextPath() + "/hku/public/my-inbox/edit.xhtml");
            return;
        } catch (Exception e) {
            LOG.debug("Problem accessing Payment Gateway: ", e);
        }
    }

    private String getApplicationNo(Integer admissionYear) {
        String applicationNo = "";
        RefCdVo refCdVo = refCdService.getByTypeAndCd(Constants.APPNO, admissionYear.toString());
        if (refCdVo == null || StringUtils.isBlank(refCdVo.getId())) {
            refCdVo = new RefCdVo();
            refCdVo.setType(Constants.APPNO);
            refCdVo.setCd(admissionYear.toString());
            refCdVo.setValue("20000");
            refCdVo = refCdService.save(refCdVo);
        }
        Random random = new Random();
        Integer no = Integer.valueOf(refCdVo.getValue()) + 1;
        applicationNo = refCdVo.getCd() + no + random.nextInt(10);
        refCdVo.setValue(no.toString());
        refCdService.save(refCdVo);
        return applicationNo;
    }

    public String getParamString() {
        return paramString;
    }

    public void setParamString(String paramString) {
        this.paramString = paramString;
    }
}
