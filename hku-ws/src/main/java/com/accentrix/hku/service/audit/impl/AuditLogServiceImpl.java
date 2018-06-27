package com.accentrix.hku.service.audit.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.audit.AuditLog;
import com.accentrix.hku.repository.audit.LogRepository;
import com.accentrix.hku.service.audit.AuditLogService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.audit.AuditLogVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年3月5日 下午2:14:08
 * @version 1.0
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("auditLog")
public class AuditLogServiceImpl implements AuditLogService {

    @Autowired
    private LogRepository logRepository;

    @Override
    public AuditLogVo get(String id) {
        AuditLog auditLog = logRepository.findOne(id);
        return toVo(auditLog);
    }

    @Transactional
    @Override
    public AuditLogVo save(AuditLogVo vo) {
        AuditLog auditLog = toAuditLog(vo);
        auditLog = logRepository.save(auditLog);
        vo.setId(auditLog.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<AuditLogVo> save(List<AuditLogVo> vos) {
        List<AuditLogVo> auditLogVos = new ArrayList<AuditLogVo>();
        for (AuditLogVo auditLogVo : vos) {
            AuditLog auditLog = toAuditLog(auditLogVo);
            auditLog = logRepository.save(auditLog);
            auditLogVo.setId(auditLog.getId());
            auditLogVos.add(auditLogVo);
        }
        return auditLogVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        logRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(AuditLogVo vo) {
        AuditLog auditLog = toAuditLog(vo);
        logRepository.delete(auditLog);
    }

    @Override
    public List<AuditLogVo> findList() {
        List<AuditLog> list = logRepository.findAll();
        List<AuditLogVo> vos = new ArrayList<AuditLogVo>();
        for (AuditLog auditLog : list) {
            vos.add(toVo(auditLog));
        }
        return vos;
    }

    @Override
    public List<AuditLogVo> getByApplicantAccountId(String applicantAccountId) {
        List<AuditLog> list = logRepository.getByApplicantAccountId(applicantAccountId);
        List<AuditLogVo> vos = new ArrayList<AuditLogVo>();
        for (AuditLog auditLog : list) {
            vos.add(toVo(auditLog));
        }
        return vos;
    }

    private AuditLogVo toVo(AuditLog auditLog) {
        AuditLogVo vo = new AuditLogVo();
        vo.setId(auditLog.getId());
        vo.setApplicantAccountId(auditLog.getApplicantAccountId());
        vo.setMsg(auditLog.getMsg());
        vo.setLevel(auditLog.getLevel());
        vo.setTypeCd(auditLog.getTypeCd());
        vo.setStatusCd(auditLog.getStatusCd());
        vo.setCreateBy(auditLog.getCreateBy());
        vo.setCreateDate(auditLog.getCreateDate());
        vo.setUpdateBy(auditLog.getUpdateBy());
        vo.setUpdateDate(auditLog.getUpdateDate());
        vo.setVersion(auditLog.getVersion());
        vo.setIsDeleted(auditLog.getIsDeleted());
        return vo;
    }

    private AuditLog toAuditLog(AuditLogVo vo) {
        AuditLog auditLog = new AuditLog();
        auditLog.setId(vo.getId());
        auditLog.setApplicantAccountId(vo.getApplicantAccountId());
        auditLog.setMsg(vo.getMsg());
        auditLog.setLevel(vo.getLevel());
        auditLog.setTypeCd(vo.getTypeCd());
        auditLog.setStatusCd(vo.getStatusCd());
        auditLog.setCreateBy(vo.getCreateBy());
        auditLog.setCreateDate(vo.getCreateDate());
        auditLog.setUpdateBy(vo.getUpdateBy());
        auditLog.setUpdateDate(vo.getUpdateDate());
        auditLog.setVersion(vo.getVersion());
        auditLog.setIsDeleted(vo.getIsDeleted());
        return auditLog;
    }

}
