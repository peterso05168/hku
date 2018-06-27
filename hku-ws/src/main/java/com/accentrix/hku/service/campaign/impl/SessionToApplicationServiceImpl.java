package com.accentrix.hku.service.campaign.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.campaign.SessionToApplication;
import com.accentrix.hku.repository.campaign.SessionToApplicationRepository;
import com.accentrix.hku.service.campaign.SessionToApplicationService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.campaign.SessionToApplicationVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年4月9日 下午2:32:26 
 * @version 1.0 
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("sessionToApplication")
public class SessionToApplicationServiceImpl implements SessionToApplicationService {

    @Autowired
    private SessionToApplicationRepository sessionToApplicationRepository;

    @Override
    public SessionToApplicationVo get(String id) {
        SessionToApplication sessionToApplication = sessionToApplicationRepository.findOne(id);
        return toVo(sessionToApplication);
    }

    @Transactional
    @Override
    public SessionToApplicationVo save(SessionToApplicationVo vo) {
        SessionToApplication sessionToApplication = toSessionToApplication(vo);
        sessionToApplication = sessionToApplicationRepository.save(sessionToApplication);
        vo.setId(sessionToApplication.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<SessionToApplicationVo> save(List<SessionToApplicationVo> vos) {
        List<SessionToApplicationVo> sessionToApplicationVos = new ArrayList<SessionToApplicationVo>();
        for (SessionToApplicationVo sessionToApplicationVo : vos) {
            SessionToApplication sessionToApplication = toSessionToApplication(sessionToApplicationVo);
            sessionToApplication = sessionToApplicationRepository.save(sessionToApplication);
            sessionToApplicationVo.setId(sessionToApplication.getId());
            sessionToApplicationVos.add(sessionToApplicationVo);
        }
        return sessionToApplicationVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        sessionToApplicationRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(SessionToApplicationVo vo) {
        SessionToApplication sessionToApplication = toSessionToApplication(vo);
        sessionToApplicationRepository.delete(sessionToApplication);
    }

    @Override
    public List<SessionToApplicationVo> findList() {
        List<SessionToApplication> list = sessionToApplicationRepository.findAll();
        List<SessionToApplicationVo> vos = new ArrayList<SessionToApplicationVo>();
        for (SessionToApplication sessionToApplication : list) {
            vos.add(toVo(sessionToApplication));
        }
        return vos;
    }

    private SessionToApplicationVo toVo(SessionToApplication sessionToApplication) {
        SessionToApplicationVo vo = new SessionToApplicationVo();
        vo.setId(sessionToApplication.getId());
        vo.setApplicantApplicationId(sessionToApplication.getApplicantApplicationId());
        vo.setCpgnSessionId(sessionToApplication.getCpgnSessionId());
        return vo;
    }

    private SessionToApplication toSessionToApplication(SessionToApplicationVo vo) {
        SessionToApplication sessionToApplication = new SessionToApplication();
        sessionToApplication.setId(vo.getId());
        sessionToApplication.setApplicantApplicationId(vo.getApplicantApplicationId());
        sessionToApplication.setCpgnSessionId(vo.getCpgnSessionId());
        return sessionToApplication;
    }
}
