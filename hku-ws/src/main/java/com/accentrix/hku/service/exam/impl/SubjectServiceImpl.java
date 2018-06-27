package com.accentrix.hku.service.exam.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.exam.Subject;
import com.accentrix.hku.repository.exam.SubjectRepository;
import com.accentrix.hku.service.adm.GpsScoringSubjectService;
import com.accentrix.hku.service.exam.SubjectService;
import com.accentrix.hku.vo.adm.GpsScoringSubjectVo;
import com.accentrix.hku.vo.exam.SubjectVo;
import com.accentrix.hku.timer.annotation.Timer;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 下午2:31:28
 * @version 1.0
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("subject")
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private GpsScoringSubjectService gpsScoringSubjectService;

    @Override
    public SubjectVo get(String id) {
        Subject subject = subjectRepository.findOne(id);
        return toVo(subject);
    }

    @Transactional
    @Override
    public SubjectVo save(SubjectVo vo) {
        Subject subject = toSubject(vo);
        vo.setId(subject.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<SubjectVo> save(List<SubjectVo> vos) {
        List<SubjectVo> subjectVos = new ArrayList<SubjectVo>();
        for (SubjectVo subjectVo : subjectVos) {
            Subject subject = toSubject(subjectVo);
            subject = subjectRepository.save(subject);
            subjectVo.setId(subject.getId());
            subjectVos.add(subjectVo);
        }
        return subjectVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        subjectRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(SubjectVo vo) {
        Subject subject = toSubject(vo);
        subjectRepository.delete(subject);
    }

    @Override
    public List<SubjectVo> findList() {
        List<SubjectVo> subjectVos = new ArrayList<SubjectVo>();
        List<Subject> subjects = subjectRepository.findAll();
        for (Subject subject : subjects) {
            subjectVos.add(toVo(subject));
        }
        return subjectVos;
    }

    @Override
    public SubjectVo getBySubjectCdAndExamTypeId(String examSubjectCd, String examTypeId) {
        Subject subject = subjectRepository.getBySubjectCdAndExamTypeId(examSubjectCd, examTypeId);
        return toVo(subject);
    }

    @Override
    public List<SubjectVo> getByExamTypeId(String examTypeId) {
        List<SubjectVo> subjectVos = new ArrayList<SubjectVo>();
        List<Subject> subjects = subjectRepository.getByExamTypeId(examTypeId);
        for (Subject subject : subjects) {
            subjectVos.add(toVo(subject));
        }
        return subjectVos;
    }

    //	@Override
    //	public List<String> getIdsByExamTypeId(String examTypeId) {
    //		return subjectRepository.getIdsByExamTypeId(examTypeId);
    //	}

    private Subject toSubject(SubjectVo vo) {
        Subject subject = new Subject();
        subject.setExamSubjectCd(vo.getExamSubjectCd());
        subject.setExamSubjectDesc(vo.getExamSubjectDesc());
        subject.setExamSubjectDescChi(vo.getExamSubjectDescChi());
        subject.setSubSubject(vo.getSubSubject());
        subject.setExamTypeId(vo.getExamTypeId());
        subject.setExamLevel(vo.getExamLevel());
        subject.setExamBoard(vo.getExamBoard());
        return subject;
    }

    private SubjectVo toVo(Subject subject) {
        SubjectVo vo = new SubjectVo();
        if (subject != null) {
            vo.setId(subject.getId());
            vo.setExamSubjectCd(subject.getExamSubjectCd());
            vo.setExamSubjectDesc(subject.getExamSubjectDesc());
            vo.setSubSubject(subject.getSubSubject());
            vo.setExamTypeId(subject.getExamTypeId());
            vo.setExamTypeId(subject.getExamTypeId());
            vo.setExamLevel(subject.getExamLevel());
            vo.setExamBoard(subject.getExamBoard());
        }
        return vo;
    }

    @Override
    public List<String> getIdsByScoringFormulaId(String scoringFormulaId, String type) {
        List<GpsScoringSubjectVo> scoringSubjects = gpsScoringSubjectService.findByScoringFormulaId(scoringFormulaId,
                type);
        List<String> ids = new ArrayList<String>();
        for (GpsScoringSubjectVo scoringSubject : scoringSubjects) {
            ids.add(scoringSubject.getExamSubjectId());
        }
        return ids;
    }

    @Override
    public List<SubjectVo> findByExamTypeIdAndExamBoardAndExamLevelAndSubjectDescNotIn(String examTypeId,
            String examBoard, String examLevel, List<String> subjectStrs) {
        List<SubjectVo> subjectVos = new ArrayList<SubjectVo>();
        List<Subject> subjects = subjectRepository.findByExamTypeIdAndExamBoardAndExamLevelAndSubjectDescNotIn(
                examTypeId, examBoard, examLevel, subjectStrs);
        for (Subject subject : subjects) {
            subjectVos.add(toVo(subject));
        }
        return subjectVos;
    }

    @Override
    public List<String> findExamLevelsByExamTypeIdAndExamBoardGroupByExamLevel(String examTypeId, String examBoard) {
        return subjectRepository.findExamLevelsByExamTypeIdAndExamBoardGroupByExamLevel(examTypeId, examBoard);
    }

    @Override
    public List<String> findExamBoardsByExamTypeIdAndExamLevelGroupByExamBoard(String examTypeId, String examLevel) {
        return subjectRepository.findExamBoardsByExamTypeIdAndExamLevelGroupByExamBoard(examTypeId, examLevel);
    }

    @Override
    public List<SubjectVo> findByExamTypeIdAndExamBoardAndExamLevel(String examTypeId, String examBoard,
            String examLevel) {
        List<SubjectVo> subjectVos = new ArrayList<SubjectVo>();
        List<Subject> subjects = subjectRepository.findByExamTypeIdAndExamBoardAndExamLevel(examTypeId, examBoard,
                examLevel);
        for (Subject subject : subjects) {
            subjectVos.add(toVo(subject));
        }
        return subjectVos;
    }
}
