package com.accentrix.hku.repository.audit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.audit.AuditLog;
import com.accentrix.hku.repository.audit.custom.LogRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年3月5日 下午2:08:33
 * @version 1.0
 */

@Repository
public interface LogRepository extends JpaRepository<AuditLog, String>, LogRepositoryCustom {

}
