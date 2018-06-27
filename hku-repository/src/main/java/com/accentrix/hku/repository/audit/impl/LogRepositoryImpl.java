package com.accentrix.hku.repository.audit.impl;

import java.util.List;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.audit.AuditLog;
import com.accentrix.hku.domain.audit.QAuditLog;
import com.accentrix.hku.repository.audit.custom.LogRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年3月5日 下午2:08:48
 * @version 1.0
 */

public class LogRepositoryImpl extends JpaDslQuery<AuditLog, QAuditLog> implements LogRepositoryCustom {

    @Override
    public List<AuditLog> getByApplicantAccountId(String applicantAccountId) {
        eq($.isDeleted, false);
        eq($.applicantAccountId, applicantAccountId);
        eq($.level, 1);
        return list();
    }

}
