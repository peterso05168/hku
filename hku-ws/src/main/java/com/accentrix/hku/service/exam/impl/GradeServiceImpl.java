package com.accentrix.hku.service.exam.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.exam.Grade;
import com.accentrix.hku.repository.exam.GradeRepository;
import com.accentrix.hku.service.exam.GradeService;
import com.accentrix.hku.vo.exam.GradeVo;
import com.accentrix.hku.timer.annotation.Timer;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 下午2:31:10
 * @version 1.0
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("grade")
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    @Override
    public GradeVo get(String id) {
        Grade grade = gradeRepository.findOne(id);
        return toVo(grade);
    }

    @Transactional
    @Override
    public GradeVo save(GradeVo vo) {
        Grade grade = toGrade(vo);
        vo.setId(grade.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<GradeVo> save(List<GradeVo> vos) {
        List<GradeVo> gradeVos = new ArrayList<GradeVo>();
        for (GradeVo gradeVo : gradeVos) {
            Grade grade = toGrade(gradeVo);
            grade = gradeRepository.save(grade);
            gradeVo.setId(grade.getId());
            gradeVos.add(gradeVo);
        }
        return gradeVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        gradeRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(GradeVo vo) {
        Grade grade = toGrade(vo);
        gradeRepository.delete(grade);
    }

    @Override
    public List<GradeVo> findList() {
        List<GradeVo> gradeVos = new ArrayList<GradeVo>();
        List<Grade> gardes = gradeRepository.findAll();
        for (Grade grade : gardes) {
            gradeVos.add(toVo(grade));
        }
        return gradeVos;
    }

    @Override
    public List<GradeVo> getByExamTypeId(String examTypeId) {
        List<GradeVo> gradeVos = new ArrayList<GradeVo>();
        List<Grade> gardes = gradeRepository.getByExamTypeId(examTypeId);
        for (Grade grade : gardes) {
            gradeVos.add(toVo(grade));
        }
        return gradeVos;
    }

    @Override
    public GradeVo getByExamTypeIdAndGradeCd(String examTypeId, String gradeCd) {
        Grade grade = gradeRepository.getByExamTypeIdAndGradeCd(examTypeId, gradeCd);
        return grade != null ? toVo(grade) : null;
    }

    @Override
    public List<GradeVo> getByExamTypeIdAndExamLevel(String examTypeId, String examLevel) {
        List<GradeVo> gradeVos = new ArrayList<GradeVo>();
        List<Grade> gardes = gradeRepository.getByExamTypeIdAndExamLevel(examTypeId, examLevel);
        for (Grade grade : gardes) {
            gradeVos.add(toVo(grade));
        }
        return gradeVos;
    }

    private Grade toGrade(GradeVo vo) {
        Grade grade = new Grade();
        grade.setExamTypeId(vo.getExamTypeId());
        grade.setGradeCd(vo.getGradeCd());
        grade.setGradePoint(vo.getGradePoint());
        grade.setComparableValue(vo.getComparableValue());
        grade.setExamLevel(vo.getExamLevel());
        return grade;
    }

    private GradeVo toVo(Grade grade) {
        GradeVo vo = new GradeVo();
        vo.setId(grade.getId());
        vo.setExamTypeId(grade.getExamTypeId());
        vo.setGradeCd(grade.getGradeCd());
        vo.setGradePoint(grade.getGradePoint());
        vo.setComparableValue(grade.getComparableValue());
        vo.setExamLevel(grade.getExamLevel());
        return vo;
    }

    @Override
    public GradeVo getByExamTypeIdAndGradeCdAndExamLevel(String examTypeId, String gradeCd, String examLevel) {
        Grade grade = gradeRepository.getByExamTypeIdAndGradeCdAndExamLevel(examTypeId, gradeCd, examLevel);
        return toVo(grade);
    }

}
