package com.accentrix.hku.service.adm.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.adm.GpsScoringSubject;
import com.accentrix.hku.repository.adm.GpsScoringSubjectRepository;
import com.accentrix.hku.service.adm.GpsScoringSubjectService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.adm.GpsScoringSubjectVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年3月19日 上午11:44:10 
 * @version 1.0 
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("gpsScoringSubject")
public class GpsScoringSubjectServiceImpl implements GpsScoringSubjectService {

    @Autowired
    private GpsScoringSubjectRepository gpsScoringSubjectRepository;

    @Override
    public GpsScoringSubjectVo get(String id) {
        GpsScoringSubject gpsScoringSubject = gpsScoringSubjectRepository.findOne(id);
        return toVo(gpsScoringSubject);
    }

    @Transactional
    @Override
    public GpsScoringSubjectVo save(GpsScoringSubjectVo vo) {
        GpsScoringSubject gpsScoringSubject = toGpsScoringSubject(vo);
        gpsScoringSubject = gpsScoringSubjectRepository.save(gpsScoringSubject);
        vo.setId(gpsScoringSubject.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<GpsScoringSubjectVo> save(List<GpsScoringSubjectVo> vos) {
        List<GpsScoringSubjectVo> gpsScoringSubjectVos = new ArrayList<GpsScoringSubjectVo>();
        for (GpsScoringSubjectVo gpsScoringSubjectVo : vos) {
            GpsScoringSubject gpsScoringSubject = toGpsScoringSubject(gpsScoringSubjectVo);
            gpsScoringSubject = gpsScoringSubjectRepository.save(gpsScoringSubject);
            gpsScoringSubjectVo.setId(gpsScoringSubject.getId());
            gpsScoringSubjectVos.add(gpsScoringSubjectVo);
        }
        return gpsScoringSubjectVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        gpsScoringSubjectRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(GpsScoringSubjectVo vo) {
        GpsScoringSubject gpsScoringSubject = toGpsScoringSubject(vo);
        gpsScoringSubjectRepository.delete(gpsScoringSubject);
    }

    @Override
    public List<GpsScoringSubjectVo> findList() {
        List<GpsScoringSubject> list = gpsScoringSubjectRepository.findAll();
        List<GpsScoringSubjectVo> vos = new ArrayList<GpsScoringSubjectVo>();
        for (GpsScoringSubject gpsScoringSubject : list) {
            vos.add(toVo(gpsScoringSubject));
        }
        return vos;
    }

    private GpsScoringSubject toGpsScoringSubject(GpsScoringSubjectVo vo) {
        GpsScoringSubject gpsScoringSubject = new GpsScoringSubject();
        gpsScoringSubject.setAdmScoringFormulaId(vo.getAdmScoringFormulaId());
        gpsScoringSubject.setExamSubjectId(vo.getExamSubjectId());
        gpsScoringSubject.setType(vo.getType());
        return gpsScoringSubject;
    }

    private GpsScoringSubjectVo toVo(GpsScoringSubject gpsScoringSubject) {
        GpsScoringSubjectVo vo = new GpsScoringSubjectVo();
        vo.setId(gpsScoringSubject.getId());
        vo.setAdmScoringFormulaId(gpsScoringSubject.getAdmScoringFormulaId());
        vo.setExamSubjectId(gpsScoringSubject.getExamSubjectId());
        vo.setType(gpsScoringSubject.getType());
        return vo;
    }

    @Override
    public List<GpsScoringSubjectVo> findByScoringFormulaId(String scoringFormulaId, String type) {
        List<GpsScoringSubject> subs = gpsScoringSubjectRepository.findByScoringFormulaId(scoringFormulaId, type);
        List<GpsScoringSubjectVo> vos = new ArrayList<GpsScoringSubjectVo>();
        for (GpsScoringSubject sub : subs) {
            vos.add(toVo(sub));
        }
        return vos;
    }

}
