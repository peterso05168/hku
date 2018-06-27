package com.accentrix.hku.service.app.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.app.AppNjceeSubjectStructure;
import com.accentrix.hku.repository.app.AppNjceeSubjectStructureRepository;
import com.accentrix.hku.service.app.AppNjceeSubjectStructureService;
import com.accentrix.hku.service.exam.SubjectService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.app.AppNjceeSubjectStructureVo;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年4月25日 下午9:32:47
 */
@Service
@Transactional(readOnly = true)
@Timer
@Path("appNjceeSubjectStructure")
public class AppNjceeSubjectStructureServiceImpl implements AppNjceeSubjectStructureService {

    @Autowired
    private AppNjceeSubjectStructureRepository appNjceeSubjectStructureRepository;
    @Autowired
    private SubjectService subjectService;

    @Override
    public AppNjceeSubjectStructureVo get(String id) {
        AppNjceeSubjectStructure appNjceeSubjectStructure = appNjceeSubjectStructureRepository.findOne(id);
        return toVo(appNjceeSubjectStructure);
    }

    @Transactional
    @Override
    public AppNjceeSubjectStructureVo save(AppNjceeSubjectStructureVo vo) {
        AppNjceeSubjectStructure appNjceeSubjectStructure = toAppNjceeSubjectStructure(vo);
        appNjceeSubjectStructure = appNjceeSubjectStructureRepository.save(appNjceeSubjectStructure);
        vo.setId(appNjceeSubjectStructure.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<AppNjceeSubjectStructureVo> save(List<AppNjceeSubjectStructureVo> vos) {
        List<AppNjceeSubjectStructureVo> appNjceeSubjectStructureVos = new ArrayList<AppNjceeSubjectStructureVo>();
        for (AppNjceeSubjectStructureVo appNjceeSubjectStructureVo : vos) {
            AppNjceeSubjectStructure appNjceeSubjectStructure = toAppNjceeSubjectStructure(appNjceeSubjectStructureVo);
            appNjceeSubjectStructure = appNjceeSubjectStructureRepository.save(appNjceeSubjectStructure);
            appNjceeSubjectStructureVo.setId(appNjceeSubjectStructure.getId());
            appNjceeSubjectStructureVos.add(appNjceeSubjectStructureVo);
        }
        return appNjceeSubjectStructureVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        appNjceeSubjectStructureRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(AppNjceeSubjectStructureVo vo) {
        appNjceeSubjectStructureRepository.delete(toAppNjceeSubjectStructure(vo));
    }

    @Override
    public List<AppNjceeSubjectStructureVo> findList() {
        List<AppNjceeSubjectStructure> appNjceeSubjectStructures = appNjceeSubjectStructureRepository.findAll();
        List<AppNjceeSubjectStructureVo> vos = new ArrayList<AppNjceeSubjectStructureVo>();
        for (AppNjceeSubjectStructure appNjceeSubjectStructure : appNjceeSubjectStructures) {
            AppNjceeSubjectStructureVo vo = toVo(appNjceeSubjectStructure);
            vos.add(vo);
        }
        return vos;
    }

    @Override
    public List<AppNjceeSubjectStructureVo> getByAppNjceeScoringSystemId(String appNjceeScoringSystemId) {
        List<AppNjceeSubjectStructure> appNjceeSubjectStructures = appNjceeSubjectStructureRepository
                .getByAppNjceeScoringSystemId(appNjceeScoringSystemId);
        List<AppNjceeSubjectStructureVo> vos = new ArrayList<AppNjceeSubjectStructureVo>();
        for (AppNjceeSubjectStructure appNjceeSubjectStructure : appNjceeSubjectStructures) {
            AppNjceeSubjectStructureVo vo = toVo(appNjceeSubjectStructure);
            vos.add(vo);
        }
        return vos;
    }

    @Override
    public List<AppNjceeSubjectStructureVo> getByAppNjceeScoringSystemIdAndType(String appNjceeScoringSystemId,
            String type) {
        List<AppNjceeSubjectStructure> appNjceeSubjectStructures = appNjceeSubjectStructureRepository
                .getByAppNjceeScoringSystemIdAndType(appNjceeScoringSystemId, type);
        List<AppNjceeSubjectStructureVo> vos = new ArrayList<AppNjceeSubjectStructureVo>();
        for (AppNjceeSubjectStructure appNjceeSubjectStructure : appNjceeSubjectStructures) {
            AppNjceeSubjectStructureVo vo = toVo(appNjceeSubjectStructure);
            vos.add(vo);
        }
        return vos;
    }

    private AppNjceeSubjectStructureVo toVo(AppNjceeSubjectStructure appNjceeSubjectStructure) {
        AppNjceeSubjectStructureVo vo = new AppNjceeSubjectStructureVo();
        if (appNjceeSubjectStructure != null) {
            vo.setId(appNjceeSubjectStructure.getId());
            vo.setSubjectId(appNjceeSubjectStructure.getSubjectId());
            vo.setAppNjceeScoringSystemId(appNjceeSubjectStructure.getAppNjceeScoringSystemId());
            vo.setSubjectScore(appNjceeSubjectStructure.getSubjectScore());
            vo.setStructureType(appNjceeSubjectStructure.getStructureType());
            if (StringUtils.isNotBlank(vo.getSubjectId())) {
                vo.setSubjectDesc(subjectService.get(vo.getSubjectId()).getExamSubjectDesc());
            }
        }
        return vo;
    }

    private AppNjceeSubjectStructure toAppNjceeSubjectStructure(AppNjceeSubjectStructureVo vo) {
        AppNjceeSubjectStructure appNjceeSubjectStructure = new AppNjceeSubjectStructure();
        appNjceeSubjectStructure.setId(vo.getId());
        appNjceeSubjectStructure.setSubjectId(vo.getSubjectId());
        appNjceeSubjectStructure.setAppNjceeScoringSystemId(vo.getAppNjceeScoringSystemId());
        appNjceeSubjectStructure.setSubjectScore(vo.getSubjectScore());
        appNjceeSubjectStructure.setStructureType(vo.getStructureType());
        return appNjceeSubjectStructure;
    }

}
