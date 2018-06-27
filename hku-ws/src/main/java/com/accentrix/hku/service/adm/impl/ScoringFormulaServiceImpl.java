package com.accentrix.hku.service.adm.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.adm.ScoringFormula;
import com.accentrix.hku.repository.adm.ScoringFormulaRepository;
import com.accentrix.hku.service.adm.ScoringFormulaService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.adm.ScoringFormulaVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年3月19日 上午11:44:20 
 * @version 1.0 
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("scoringFormula")
public class ScoringFormulaServiceImpl implements ScoringFormulaService {

    @Autowired
    private ScoringFormulaRepository scoringFormulaRepository;

    @Override
    public ScoringFormulaVo get(String id) {
        ScoringFormula scoringFormula = scoringFormulaRepository.findOne(id);
        return toVo(scoringFormula);
    }

    @Transactional
    @Override
    public ScoringFormulaVo save(ScoringFormulaVo vo) {
        ScoringFormula scoringFormula = toScoringFormula(vo);
        scoringFormula = scoringFormulaRepository.save(scoringFormula);
        vo.setId(scoringFormula.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<ScoringFormulaVo> save(List<ScoringFormulaVo> vos) {
        List<ScoringFormulaVo> scoringFormulaVos = new ArrayList<ScoringFormulaVo>();
        for (ScoringFormulaVo scoringFormulaVo : vos) {
            ScoringFormula scoringFormula = toScoringFormula(scoringFormulaVo);
            scoringFormula = scoringFormulaRepository.save(scoringFormula);
            scoringFormulaVo.setId(scoringFormula.getId());
            scoringFormulaVos.add(scoringFormulaVo);
        }
        return scoringFormulaVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        scoringFormulaRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(ScoringFormulaVo vo) {
        ScoringFormula scoringFormula = toScoringFormula(vo);
        scoringFormulaRepository.delete(scoringFormula);
    }

    @Override
    public List<ScoringFormulaVo> findList() {
        List<ScoringFormula> list = scoringFormulaRepository.findAll();
        List<ScoringFormulaVo> vos = new ArrayList<ScoringFormulaVo>();
        for (ScoringFormula scoringFormula : list) {
            vos.add(toVo(scoringFormula));
        }
        return vos;
    }

    private ScoringFormulaVo toVo(ScoringFormula scoringFormula) {
        ScoringFormulaVo vo = new ScoringFormulaVo();
        vo.setId(scoringFormula.getId());
        vo.setFormulaName(scoringFormula.getFormulaName());
        vo.setDescription(scoringFormula.getDescription());
        vo.setFormulaType(scoringFormula.getFormulaType());
        vo.setAdmFormProgId(scoringFormula.getAdmFormProgId());
        vo.setExamTypeId(scoringFormula.getExamTypeId());
        vo.setIncluding(scoringFormula.getIncluding());
        vo.setExamBoard(scoringFormula.getExamBoard());
        vo.setExamLevel(scoringFormula.getExamLevel());
        //        if ("GPS".equals(vo.getFormulaType())) {
        //            vo.setIncludingSubjectIds(subjectService.getIdsByScoringFormulaId(vo.getId(), "IN"));
        //            vo.setExcludingSubjectIds(subjectService.getIdsByScoringFormulaId(vo.getId(), "EX"));
        //        } else if ("GPA".equals(vo.getFormulaType())) {
        //            List<GpaFormulaItmVo> formulaItms = gpaFormulaItmService.findByScoringFormulaId(vo.getId());
        //            vo.setGpaFormulaItms(formulaItms == null ? new ArrayList<GpaFormulaItmVo>() : formulaItms);
        //        }
        return vo;
    }

    private ScoringFormula toScoringFormula(ScoringFormulaVo vo) {
        ScoringFormula scoringFormula = new ScoringFormula();
        if (StringUtils.isNotBlank(vo.getId()))
            scoringFormula.setId(vo.getId());
        scoringFormula.setFormulaName(vo.getFormulaName());
        scoringFormula.setDescription(vo.getDescription());
        scoringFormula.setFormulaType(vo.getFormulaType());
        scoringFormula.setAdmFormProgId(vo.getAdmFormProgId());
        scoringFormula.setExamTypeId(vo.getExamTypeId());
        scoringFormula.setIncluding(vo.getIncluding());
        scoringFormula.setExamBoard(vo.getExamBoard());
        scoringFormula.setExamLevel(vo.getExamLevel());
        return scoringFormula;
    }

    @Override
    public List<ScoringFormulaVo> findByFormProgId(String formProgId) {
        List<ScoringFormula> formulas = scoringFormulaRepository.findByFormProgId(formProgId);
        List<ScoringFormulaVo> vos = new ArrayList<ScoringFormulaVo>();
        for (ScoringFormula formula : formulas) {
            vos.add(toVo(formula));
        }
        return vos;
    }
}
