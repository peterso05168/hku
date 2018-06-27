package com.accentrix.hku.util.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.accentrix.hku.constant.Constants;
import com.accentrix.hku.service.audit.AuditLogService;
import com.accentrix.hku.util.SpringContextHolder;
import com.accentrix.hku.util.sys.AccountUtils;
import com.accentrix.hku.vo.audit.AuditLogVo;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年4月24日 下午1:12:48
 */
public class AuditLogUtil {

    private static final Logger LOG = LoggerFactory.getLogger(AccountUtils.class);

    private static AuditLogService auditLogService = SpringContextHolder.getBean(AuditLogService.class);

    public static void saveAuditLog(String accountId, String msg) {
        LOG.debug("save...");
        AuditLogVo auditLog = new AuditLogVo();
        auditLog.setApplicantAccountId(accountId);
        auditLog.setMsg(msg);
        auditLog.setLevel(1);
        auditLog.setTypeCd(Constants.TYPE_FORM);
        auditLog.setStatusCd(Constants.STATUS_DONE);
        auditLog.setIsDeleted(false);
        auditLogService.save(auditLog);
    }
}
