package com.accentrix.hku.service.app.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.app.AppNjceeScoringSystem;
import com.accentrix.hku.repository.app.AppNjceeScoringSystemRepository;
import com.accentrix.hku.service.app.AppNjceeScoringSystemService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.app.AppNjceeScoringSystemVo;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年4月25日 下午9:06:47
 */
@Service
@Transactional(readOnly = true)
@Timer
@Path("appNjceeScoringSystem")
public class AppNjceeScoringSystemServiceImpl implements AppNjceeScoringSystemService {

    @Autowired
    private AppNjceeScoringSystemRepository appNjceeScoringSystemRepository;

    @Override
    public AppNjceeScoringSystemVo get(String id) {
        AppNjceeScoringSystem appNjceeScoringSystem = appNjceeScoringSystemRepository.findOne(id);
        return toVo(appNjceeScoringSystem);
    }

    @Transactional
    @Override
    public AppNjceeScoringSystemVo save(AppNjceeScoringSystemVo vo) {
        AppNjceeScoringSystem appNjceeScoringSystem = toAppNjceeScoringSystem(vo);
        appNjceeScoringSystem = appNjceeScoringSystemRepository.save(appNjceeScoringSystem);
        vo.setId(appNjceeScoringSystem.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<AppNjceeScoringSystemVo> save(List<AppNjceeScoringSystemVo> vos) {
        List<AppNjceeScoringSystemVo> appNjceeScoringSystemVos = new ArrayList<AppNjceeScoringSystemVo>();
        for (AppNjceeScoringSystemVo appNjceeScoringSystemVo : vos) {
            AppNjceeScoringSystem appNjceeScoringSystem = toAppNjceeScoringSystem(appNjceeScoringSystemVo);
            appNjceeScoringSystem = appNjceeScoringSystemRepository.save(appNjceeScoringSystem);
            appNjceeScoringSystemVo.setId(appNjceeScoringSystem.getId());
            appNjceeScoringSystemVos.add(appNjceeScoringSystemVo);
        }
        return appNjceeScoringSystemVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        appNjceeScoringSystemRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(AppNjceeScoringSystemVo vo) {
        appNjceeScoringSystemRepository.delete(toAppNjceeScoringSystem(vo));
    }

    @Override
    public List<AppNjceeScoringSystemVo> findList() {
        List<AppNjceeScoringSystem> appNjceeScoringSystems = appNjceeScoringSystemRepository.findAll();
        List<AppNjceeScoringSystemVo> appNjceeScoringSystemVos = new ArrayList<AppNjceeScoringSystemVo>();
        for (AppNjceeScoringSystem appNjceeScoringSystem : appNjceeScoringSystems) {
            AppNjceeScoringSystemVo vo = toVo(appNjceeScoringSystem);
            appNjceeScoringSystemVos.add(vo);
        }
        return appNjceeScoringSystemVos;
    }

    @Override
    public AppNjceeScoringSystemVo getByProvinceIdAndStream(String provinceId, String stream) {
        AppNjceeScoringSystem appNjceeScoringSystem = appNjceeScoringSystemRepository
                .getByProvinceIdAndStream(provinceId, stream);
        return toVo(appNjceeScoringSystem);
    }

    private AppNjceeScoringSystem toAppNjceeScoringSystem(AppNjceeScoringSystemVo vo) {
        AppNjceeScoringSystem appNjceeScoringSystem = new AppNjceeScoringSystem();
        appNjceeScoringSystem.setId(vo.getId());
        appNjceeScoringSystem.setProvinceId(vo.getProvinceId());
        appNjceeScoringSystem.setStream(vo.getStream());
        appNjceeScoringSystem.setTotalScore(vo.getTotalScore());
        return appNjceeScoringSystem;
    }

    private AppNjceeScoringSystemVo toVo(AppNjceeScoringSystem appNjceeScoringSystem) {
        AppNjceeScoringSystemVo vo = new AppNjceeScoringSystemVo();
        if (appNjceeScoringSystem != null) {
            vo.setId(appNjceeScoringSystem.getId());
            vo.setProvinceId(appNjceeScoringSystem.getProvinceId());
            vo.setStream(appNjceeScoringSystem.getStream());
            vo.setTotalScore(appNjceeScoringSystem.getTotalScore());
        }
        return vo;
    }

}
