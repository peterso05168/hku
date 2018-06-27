package com.accentrix.hku.service.app.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.app.QualificationRslt;
import com.accentrix.hku.repository.app.QualificationRsltRepository;
import com.accentrix.hku.service.app.QualificationRsltService;
import com.accentrix.hku.service.exam.SubjectService;
import com.accentrix.hku.vo.app.BestExamSubjRsltVo;
import com.accentrix.hku.vo.app.QualificationRsltVo;
import com.accentrix.hku.timer.annotation.Timer;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 下午2:25:17
 * @version 1.0
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("qualificationRslt")
public class QualificationRsltServiceImpl implements QualificationRsltService {

    @Autowired
    private QualificationRsltRepository qualificationRsltRepository;
    @Autowired
    private SubjectService subjectService;

    @Override
    public QualificationRsltVo get(String id) {
        QualificationRslt qualificationRslt = qualificationRsltRepository.findOne(id);
        return toVo(qualificationRslt);
    }

    @Transactional
    @Override
    public QualificationRsltVo save(QualificationRsltVo vo) {
        QualificationRslt qualificationRslt = toQualificationRslt(vo);
        qualificationRslt = qualificationRsltRepository.save(qualificationRslt);
        vo.setId(qualificationRslt.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<QualificationRsltVo> save(List<QualificationRsltVo> vos) {
        List<QualificationRsltVo> qualificationRsltVos = new ArrayList<QualificationRsltVo>();
        for (QualificationRsltVo vo : vos) {
            QualificationRslt qualificationRslt = toQualificationRslt(vo);
            qualificationRslt = qualificationRsltRepository.save(qualificationRslt);
            vo.setId(qualificationRslt.getId());
            qualificationRsltVos.add(vo);
        }
        return qualificationRsltVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        qualificationRsltRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(QualificationRsltVo vo) {
        qualificationRsltRepository.delete(toQualificationRslt(vo));
    }

    @Override
    public List<QualificationRsltVo> findList() {
        List<QualificationRslt> qualificationRslts = qualificationRsltRepository.findAll();
        List<QualificationRsltVo> vos = new ArrayList<QualificationRsltVo>();
        for (QualificationRslt qualificationRslt : qualificationRslts) {
            vos.add(toVo(qualificationRslt));
        }
        return vos;
    }

    @Override
    public List<QualificationRsltVo> getByAppQualificationId(String appQualificationId) {
        List<QualificationRslt> qualificationRslts = qualificationRsltRepository
                .getByAppQualificationId(appQualificationId);
        List<QualificationRsltVo> vos = new ArrayList<QualificationRsltVo>();
        for (QualificationRslt qualificationRslt : qualificationRslts) {
            vos.add(toVo(qualificationRslt));
        }
        return vos;
    }

    private QualificationRslt toQualificationRslt(QualificationRsltVo vo) {
        QualificationRslt qualificationRslt = new QualificationRslt();
        qualificationRslt.setId(vo.getId());
        qualificationRslt.setAppQualificationId(vo.getAppQualificationId());
        qualificationRslt.setExamSubjectId(vo.getExamSubjectId());
        qualificationRslt.setExamLevel(vo.getExamLevel());
        qualificationRslt.setAchievedGradeCd(vo.getAchievedGradeCd());
        qualificationRslt.setAchievedGradeOthers(vo.getAchievedGradeOthers());
        qualificationRslt.setPredictedGradeCd(vo.getPredictedGradeCd());
        qualificationRslt.setPredictedGradeOthers(vo.getPredictedGradeOthers());
        qualificationRslt.setIsDeleted(vo.getIsDeleted());
        qualificationRslt.setIsDeleteApproved(vo.getIsDeleteApproved());
        qualificationRslt.setExamBoard(vo.getExamBoard());
        qualificationRslt.setGceDateOfRelease(vo.getGceDateOfRelease());
        return qualificationRslt;
    }

    private QualificationRsltVo toVo(QualificationRslt qualificationRslt) {
        QualificationRsltVo vo = new QualificationRsltVo();
        vo.setId(qualificationRslt.getId());
        vo.setAppQualificationId(qualificationRslt.getAppQualificationId());
        vo.setExamSubjectId(qualificationRslt.getExamSubjectId());
        vo.setExamLevel(qualificationRslt.getExamLevel());
        vo.setAchievedGradeCd(qualificationRslt.getAchievedGradeCd());
        vo.setAchievedGradeOthers(qualificationRslt.getAchievedGradeOthers());
        vo.setPredictedGradeCd(qualificationRslt.getPredictedGradeCd());
        vo.setPredictedGradeOthers(qualificationRslt.getPredictedGradeOthers());
        vo.setIsDeleted(qualificationRslt.getIsDeleted());
        vo.setIsDeleteApproved(qualificationRslt.getIsDeleteApproved());
        if (StringUtils.isNotBlank(qualificationRslt.getExamSubjectId())) {
            vo.setExamSubjectDesc(subjectService.get(qualificationRslt.getExamSubjectId()).getExamSubjectDesc());
        }
        vo.setExamBoard(qualificationRslt.getExamBoard());
        vo.setGceDateOfRelease(qualificationRslt.getGceDateOfRelease());
        return vo;
    }

    @Override
    public List<QualificationRsltVo> findByQualificationIdInAndSubjectId(List<String> qualificationIds,
            String subjectId) {
        List<QualificationRslt> qualificationRslts = qualificationRsltRepository
                .findByQualificationIdInAndSubjectId(qualificationIds, subjectId);
        List<QualificationRsltVo> vos = new ArrayList<QualificationRsltVo>();
        for (QualificationRslt qualificationRslt : qualificationRslts) {
            vos.add(toVo(qualificationRslt));
        }
        return vos;
    }

    @Override
    public List<String> getExamSubjectIdsByApplicationIdAndExamTypeId(String applicationId, String examTypeId) {
        return qualificationRsltRepository.getExamSubjectIdsByApplicationIdAndExamTypeId(applicationId, examTypeId);
    }

    @Override
    public BestExamSubjRsltVo getBestExamSubjRsltByApplicationIdAndExamTypeIdAndExamSubjectId(String applicationId,
            String examTypeId, String subjectId) {
        return qualificationRsltRepository
                .getBestExamSubjRsltByApplicationIdAndExamTypeIdAndExamSubjectId(applicationId, examTypeId, subjectId);
    }

    @Override
    public List<BestExamSubjRsltVo> getBestGceSubjsPredictedAndActualScores(String applicationId, String examTypeId,
            String subjectId) {
        return qualificationRsltRepository.getBestGceSubjsPredictedAndActualScores(applicationId, examTypeId,
                subjectId);
    }

}
