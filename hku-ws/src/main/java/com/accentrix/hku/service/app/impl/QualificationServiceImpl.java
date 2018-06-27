package com.accentrix.hku.service.app.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Path;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.constant.Constants;
import com.accentrix.hku.domain.app.Qualification;
import com.accentrix.hku.repository.app.QualificationRepository;
import com.accentrix.hku.service.app.QualificationRsltService;
import com.accentrix.hku.service.app.QualificationService;
import com.accentrix.hku.service.cpc.ProvinceService;
import com.accentrix.hku.service.exam.GradeService;
import com.accentrix.hku.service.exam.TypeService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.app.QualificationRsltVo;
import com.accentrix.hku.vo.app.QualificationVo;
import com.eaio.uuid.UUID;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 下午2:24:48
 * @version 1.0
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("qualification")
public class QualificationServiceImpl implements QualificationService {

    @Autowired
    private QualificationRepository qualificationRepository;
    @Autowired
    private TypeService typeService;
    @Autowired
    private QualificationRsltService qualificationRsltService;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private ProvinceService provinceService;

    @Override
    public QualificationVo get(String id) {
        Qualification qualification = qualificationRepository.findOne(id);
        return toVo(qualification);
    }

    @Transactional
    @Override
    public QualificationVo save(QualificationVo vo) {
        Qualification qualification = toQualification(vo);
        qualification = qualificationRepository.save(qualification);
        vo.setId(qualification.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<QualificationVo> save(List<QualificationVo> vos) {
        List<QualificationVo> qualificationVos = new ArrayList<QualificationVo>();
        for (QualificationVo vo : vos) {
            Qualification qualification = toQualification(vo);
            qualification.setId(vo.getId());
            qualification = qualificationRepository.save(qualification);
            vo.setId(qualification.getId());
            qualificationVos.add(vo);
        }
        return qualificationVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        qualificationRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(QualificationVo vo) {
        qualificationRepository.delete(toQualification(vo));
    }

    @Override
    public List<QualificationVo> findList() {
        List<Qualification> qualifications = qualificationRepository.findAll();
        List<QualificationVo> vos = new ArrayList<QualificationVo>();
        for (Qualification qualification : qualifications) {
            vos.add(toVo(qualification));
        }
        return vos;
    }

    @Override
    public List<QualificationVo> getByApplicationId(String applicationId) {
        List<Qualification> qualifications = qualificationRepository.getByApplicationId(applicationId);
        List<QualificationVo> vos = new ArrayList<QualificationVo>();
        for (Qualification qualification : qualifications) {
            vos.add(toVo(qualification));
        }
        return vos;
    }

    private Qualification toQualification(QualificationVo vo) {
        Qualification qualification = new Qualification();
        qualification.setId(vo.getId());
        qualification.setApplicationId(vo.getApplicationId());
        qualification.setDateOfReleaseOfRslt(vo.getDateOfReleaseOfRslt());
        qualification.setExamTypeId(vo.getExamTypeId());
        qualification.setIbSchoolCd(vo.getIbSchoolCd());
        qualification.setIbSessionNo(vo.getIbSessionNo());
        qualification.setIbAchievedRsltGradeCd(vo.getIbAchievedRsltGradeCd());
        qualification.setIbAchievedRsltPoints(vo.getIbAchievedRsltPoints());
        qualification.setIbAchievedRslt(vo.getIbAchievedRslt());
        qualification.setIbPredictedRsltGradeCd(vo.getIbPredictedRsltGradeCd());
        qualification.setIbPredictedRsltPoints(vo.getIbPredictedRsltPoints());
        qualification.setIbPredictedRslt(vo.getIbPredictedRslt());
        qualification.setNjceeExamProvinceId(vo.getNjceeExamProvinceId());
        qualification.setNjceeCandidateNo(vo.getNjceeCandidateNo());
        qualification.setNjceeStreamCd(vo.getNjceeStreamCd());
        qualification.setNjceeStreamOthers(vo.getNjceeStreamOthers());
        qualification.setNjceeCurrentEduStatusCd(vo.getNjceeCurrentEduStatusCd());
        qualification.setNjceeExamNo(vo.getNjceeExamNo());
        qualification.setNjceeHkuMfExcellentSche(vo.getNjceeHkuMfExcellentSche());
        qualification.setLastBatchExecDate(vo.getLastBatchExecDate());
        qualification.setLastUpdatedDate(vo.getLastUpdatedDate());
        qualification.setExamTypeYear(vo.getExamTypeYear());
        qualification.setExamTypeMonth(vo.getExamTypeMonth());
        qualification.setIsDeleted(vo.getIsDeleted());
        qualification.setIsDeleteApproved(vo.getIsDeleteApproved());
        qualification.setToeflIdno(vo.getToeflIdno());
        qualification.setNjceeTotalScore(vo.getNjceeTotalScore());
        qualification.setLastUpdatedBy(vo.getLastUpdatedBy());
        return qualification;
    }

    private QualificationVo toVo(Qualification qualification) {
        QualificationVo vo = new QualificationVo();
        vo.setId(qualification.getId());
        vo.setApplicationId(qualification.getApplicationId());
        vo.setDateOfReleaseOfRslt(qualification.getDateOfReleaseOfRslt());
        vo.setExamTypeId(qualification.getExamTypeId());
        vo.setIbSchoolCd(qualification.getIbSchoolCd());
        vo.setIbSessionNo(qualification.getIbSessionNo());
        vo.setIbAchievedRsltGradeCd(qualification.getIbAchievedRsltGradeCd());
        vo.setIbAchievedRsltPoints(qualification.getIbAchievedRsltPoints());
        vo.setIbAchievedRslt(qualification.getIbAchievedRslt());
        vo.setIbPredictedRsltGradeCd(qualification.getIbPredictedRsltGradeCd());
        vo.setIbPredictedRsltPoints(qualification.getIbPredictedRsltPoints());
        vo.setIbPredictedRslt(qualification.getIbPredictedRslt());
        vo.setNjceeExamProvinceId(qualification.getNjceeExamProvinceId());
        vo.setNjceeCandidateNo(qualification.getNjceeCandidateNo());
        vo.setNjceeStreamCd(qualification.getNjceeStreamCd());
        vo.setNjceeStreamOthers(qualification.getNjceeStreamOthers());
        vo.setNjceeCurrentEduStatusCd(qualification.getNjceeCurrentEduStatusCd());
        vo.setNjceeExamNo(qualification.getNjceeExamNo());
        vo.setNjceeHkuMfExcellentSche(qualification.getNjceeHkuMfExcellentSche());
        vo.setLastBatchExecDate(qualification.getLastBatchExecDate());
        vo.setLastUpdatedDate(qualification.getLastUpdatedDate());
        vo.setExamTypeYear(qualification.getExamTypeYear());
        vo.setExamTypeMonth(qualification.getExamTypeMonth());
        vo.setIsDeleted(qualification.getIsDeleted());
        vo.setIsDeleteApproved(qualification.getIsDeleteApproved());
        vo.setToeflIdno(qualification.getToeflIdno());
        vo.setNjceeTotalScore(qualification.getNjceeTotalScore());
        vo.setNjceeRsltMap(new HashMap<String, QualificationRsltVo>());
        vo.setLastUpdatedBy(qualification.getLastUpdatedBy());
        if (StringUtils.isNotBlank(vo.getExamTypeId())) {
            vo.setType(typeService.get(vo.getExamTypeId()));
            vo.setGrades(gradeService.getByExamTypeId(vo.getExamTypeId()));
        }
        if (StringUtils.isNotBlank(vo.getId())) {
            vo.setQualificationRslts(qualificationRsltService.getByAppQualificationId(vo.getId()));
        }
        if (StringUtils.isNotBlank(vo.getNjceeExamProvinceId())) {
            vo.setNjceeExamProvinceDesc(provinceService.get(vo.getNjceeExamProvinceId()).getDescription());
        }
        if (Constants.EXAM_IBD.equals(vo.getType().getExamCd())) {
            setIBDExamTotalPoints(vo);
        } else if (Constants.EXAM_NJCEE_CODES.contains(vo.getType().getExamCd())) {
            putNjceeRslt(vo);
        }
        return vo;
    }

    private void setIBDExamTotalPoints(QualificationVo vo) {
        if (StringUtils.isNotBlank(vo.getIbAchievedRsltGradeCd())) {
            if (Constants.N_NO_GRADE.equals(vo.getIbAchievedRsltGradeCd())) {
                vo.setAchievedTotalPoints("42");
            } else {
                vo.setAchievedTotalPoints("45");
            }
        }
        if (StringUtils.isNotBlank(vo.getIbPredictedRsltGradeCd())) {
            if (Constants.N_NO_GRADE.equals(vo.getIbPredictedRsltGradeCd())) {
                vo.setPredictedTotalPoints("42");
            } else {
                vo.setPredictedTotalPoints("45");
            }
        }
    }

    private void putNjceeRslt(QualificationVo vo) {
        List<QualificationRsltVo> qualificationRslts = vo.getQualificationRslts();
        if (CollectionUtils.isNotEmpty(qualificationRslts)) {
            for (QualificationRsltVo qualificationRsltVo : qualificationRslts) {
                vo.getNjceeRsltMap().put(new UUID().toString().replace("-", ""), qualificationRsltVo);
            }
        }
    }

    @Override
    public List<QualificationVo> getByApplicationIdAndExamTypeId(String applicationId, String examTypeId) {
        List<Qualification> qualifications = qualificationRepository.getByApplicationIdAndExamTypeId(applicationId,
                examTypeId);
        List<QualificationVo> vos = new ArrayList<QualificationVo>();
        for (Qualification qualification : qualifications) {
            vos.add(toVo(qualification));
        }
        return vos;
    }

}