package com.accentrix.hku.web.management.application;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.accentrix.hku.service.applicant.ApplicationService;
import com.accentrix.hku.service.audit.AuditLogService;
import com.accentrix.hku.util.JSFUtil;
import com.accentrix.hku.vo.applicant.ApplicationVo;
import com.accentrix.hku.vo.audit.AuditLogVo;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年5月31日 下午1:07:49
 */
@ManagedBean
@ViewScoped
@Configurable(preConstruction = true)
public class HistoryBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(HistoryBean.class);

    @Autowired
    private AuditLogService auditLogService;
    @Autowired
    private ApplicationService applicationService;

    private List<AuditLogVo> auditLogVos;

    public HistoryBean() {
        init();
    }

    public void init() {
        LOG.debug("historyBean init...");
        auditLogVos = new ArrayList<AuditLogVo>();
        retrieveData();
    }

    public void retrieveData() {
        String applicationId = (String) JSFUtil.getSessionMap().get("applicationId");
        if (StringUtils.isNotBlank(applicationId)) {
            ApplicationVo applicationVo = applicationService.get(applicationId);
            auditLogVos = auditLogService.getByApplicantAccountId(applicationVo.getApplicantAccountId());
        }
    }

    public String dateFormat(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 定义要输出日期字符串的
        return sdf.format(date);
    }

    public List<AuditLogVo> getAuditLogVos() {
        return auditLogVos;
    }

    public void setAuditLogVos(List<AuditLogVo> auditLogVos) {
        this.auditLogVos = auditLogVos;
    }

}
