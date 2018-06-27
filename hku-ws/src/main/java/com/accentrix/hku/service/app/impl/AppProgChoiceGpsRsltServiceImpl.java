package com.accentrix.hku.service.app.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.app.AppProgChoiceGpsRslt;
import com.accentrix.hku.repository.app.AppProgChoiceGpsRsltRepository;
import com.accentrix.hku.service.app.AppProgChoiceGpsRsltService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.app.AppProgChoiceGpsRsltVo;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年6月1日 下午1:44:22
 */
@Service
@Transactional(readOnly = true)
@Timer
@Path("appProgChoiceGpsRslt")
public class AppProgChoiceGpsRsltServiceImpl implements AppProgChoiceGpsRsltService {

    @Autowired
    private AppProgChoiceGpsRsltRepository appProgChoiceGpsRsltRepository;

    @Override
    public AppProgChoiceGpsRsltVo get(String id) {
        AppProgChoiceGpsRslt appProgChoiceGpsRslt = appProgChoiceGpsRsltRepository.findOne(id);
        return toVo(appProgChoiceGpsRslt);
    }

    @Transactional
    @Override
    public AppProgChoiceGpsRsltVo save(AppProgChoiceGpsRsltVo vo) {
        AppProgChoiceGpsRslt appProgChoiceGpsRslt = toAppProgChoiceGpsRslt(vo);
        appProgChoiceGpsRslt = appProgChoiceGpsRsltRepository.save(appProgChoiceGpsRslt);
        vo.setId(appProgChoiceGpsRslt.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<AppProgChoiceGpsRsltVo> save(List<AppProgChoiceGpsRsltVo> vos) {
        List<AppProgChoiceGpsRsltVo> appProgChoiceGpsRsltVos = new ArrayList<AppProgChoiceGpsRsltVo>();
        for (AppProgChoiceGpsRsltVo vo : vos) {
            AppProgChoiceGpsRslt appProgChoiceGpsRslt = toAppProgChoiceGpsRslt(vo);
            appProgChoiceGpsRslt = appProgChoiceGpsRsltRepository.save(appProgChoiceGpsRslt);
            vo.setId(appProgChoiceGpsRslt.getId());
            appProgChoiceGpsRsltVos.add(vo);
        }
        return appProgChoiceGpsRsltVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        appProgChoiceGpsRsltRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(AppProgChoiceGpsRsltVo vo) {
        appProgChoiceGpsRsltRepository.delete(toAppProgChoiceGpsRslt(vo));
    }

    @Override
    public List<AppProgChoiceGpsRsltVo> findList() {
        List<AppProgChoiceGpsRslt> appProgChoiceGpsRslts = appProgChoiceGpsRsltRepository.findAll();
        List<AppProgChoiceGpsRsltVo> vos = new ArrayList<AppProgChoiceGpsRsltVo>();
        for (AppProgChoiceGpsRslt appProgChoiceGpsRslt : appProgChoiceGpsRslts) {
            AppProgChoiceGpsRsltVo vo = toVo(appProgChoiceGpsRslt);
            vos.add(vo);
        }
        return vos;
    }

    private AppProgChoiceGpsRsltVo toVo(AppProgChoiceGpsRslt appProgChoiceGpsRslt) {
        AppProgChoiceGpsRsltVo vo = new AppProgChoiceGpsRsltVo();
        if (appProgChoiceGpsRslt != null) {
            vo.setId(appProgChoiceGpsRslt.getId());
            vo.setAppProgrammeChoiceId(appProgChoiceGpsRslt.getAppProgrammeChoiceId());
            vo.setAdmScoringFormulaId(appProgChoiceGpsRslt.getAdmScoringFormulaId());
            vo.setGpsRslt(appProgChoiceGpsRslt.getGpsRslt());
        }
        return vo;
    }

    private AppProgChoiceGpsRslt toAppProgChoiceGpsRslt(AppProgChoiceGpsRsltVo vo) {
        AppProgChoiceGpsRslt appProgChoiceGpsRslt = new AppProgChoiceGpsRslt();
        appProgChoiceGpsRslt.setId(vo.getId());
        appProgChoiceGpsRslt.setAppProgrammeChoiceId(vo.getAppProgrammeChoiceId());
        appProgChoiceGpsRslt.setAdmScoringFormulaId(vo.getAdmScoringFormulaId());
        appProgChoiceGpsRslt.setGpsRslt(vo.getGpsRslt());
        return appProgChoiceGpsRslt;
    }
}
