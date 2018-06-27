package com.accentrix.hku.service.campaign.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.campaign.CpgnSession;
import com.accentrix.hku.repository.campaign.CpgnSessionRepository;
import com.accentrix.hku.service.campaign.CpgnSessionService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.campaign.CpgnSessionVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年4月9日 下午2:30:06 
 * @version 1.0 
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("cpgnSession")
public class CpgnSessionServiceImpl implements CpgnSessionService {

    @Autowired
    private CpgnSessionRepository cpgnSessionRepository;

    @Override
    public CpgnSessionVo get(String id) {
        CpgnSession cpgnSession = cpgnSessionRepository.findOne(id);
        return toVo(cpgnSession);
    }

    @Transactional
    @Override
    public CpgnSessionVo save(CpgnSessionVo vo) {
        CpgnSession cpgnSession = toCpgnSession(vo);
        cpgnSession = cpgnSessionRepository.save(cpgnSession);
        vo.setId(cpgnSession.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<CpgnSessionVo> save(List<CpgnSessionVo> vos) {
        List<CpgnSessionVo> cpgnSessionVos = new ArrayList<CpgnSessionVo>();
        for (CpgnSessionVo cpgnSessionVo : vos) {
            CpgnSession cpgnSession = toCpgnSession(cpgnSessionVo);
            cpgnSession = cpgnSessionRepository.save(cpgnSession);
            cpgnSessionVo.setId(cpgnSession.getId());
            cpgnSessionVos.add(cpgnSessionVo);
        }
        return cpgnSessionVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        cpgnSessionRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(CpgnSessionVo vo) {
        CpgnSession cpgnSession = toCpgnSession(vo);
        cpgnSessionRepository.delete(cpgnSession);
    }

    @Override
    public List<CpgnSessionVo> findList() {
        List<CpgnSession> list = cpgnSessionRepository.findAll();
        List<CpgnSessionVo> vos = new ArrayList<CpgnSessionVo>();
        for (CpgnSession cpgnSession : list) {
            vos.add(toVo(cpgnSession));
        }
        return vos;
    }

    private CpgnSessionVo toVo(CpgnSession cpgnSession) {
        CpgnSessionVo vo = new CpgnSessionVo();
        vo.setId(cpgnSession.getId());
        vo.setAssignedQuota(cpgnSession.getAssignedQuota());
        vo.setCpgnCentreId(cpgnSession.getCpgnCentreId());
        vo.setDayName(cpgnSession.getDayName());
        vo.setReservedQuota(cpgnSession.getReservedQuota());
        vo.setSessionDatetime(cpgnSession.getSessionDatetime());
        return vo;
    }

    private CpgnSession toCpgnSession(CpgnSessionVo vo) {
        CpgnSession cpgnSession = new CpgnSession();
        cpgnSession.setId(vo.getId());
        cpgnSession.setAssignedQuota(vo.getAssignedQuota());
        cpgnSession.setCpgnCentreId(vo.getCpgnCentreId());
        cpgnSession.setDayName(vo.getDayName());
        cpgnSession.setReservedQuota(vo.getReservedQuota());
        cpgnSession.setSessionDatetime(vo.getSessionDatetime());
        return cpgnSession;
    }

    @Override
    public List<CpgnSessionVo> findByCentreId(String centreId) {
        List<CpgnSession> list = cpgnSessionRepository.findByCentreId(centreId);
        List<CpgnSessionVo> vos = new ArrayList<CpgnSessionVo>();
        for (CpgnSession cpgnSession : list) {
            vos.add(toVo(cpgnSession));
        }
        return vos;
    }

}
