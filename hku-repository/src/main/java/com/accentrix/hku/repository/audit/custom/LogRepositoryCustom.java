package com.accentrix.hku.repository.audit.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.audit.AuditLog;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年3月5日 下午2:08:43 
 * @version 1.0 
 */

@Repository
public interface LogRepositoryCustom {
    List<AuditLog> getByApplicantAccountId(String applicantAccountId);
}
