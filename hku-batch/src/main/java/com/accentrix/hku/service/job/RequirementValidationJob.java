package com.accentrix.hku.service.job;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.accentrix.hku.constant.Constants;
import com.accentrix.hku.domain.app.AppProgChoiceGpsRslt;
import com.accentrix.hku.domain.app.IbdBestExam;
import com.accentrix.hku.domain.app.ProgrammeChoice;
import com.accentrix.hku.domain.exam.Grade;
import com.accentrix.hku.domain.exam.Type;
import com.accentrix.hku.repository.app.AppProgChoiceGpsRsltRepository;
import com.accentrix.hku.repository.app.IbdBestExamRepository;
import com.accentrix.hku.repository.app.ProgrammeChoiceRepository;
import com.accentrix.hku.repository.app.QualificationRepository;
import com.accentrix.hku.repository.app.RequirementRepository;
import com.accentrix.hku.repository.exam.GradeRepository;
import com.accentrix.hku.repository.exam.TypeRepository;
import com.accentrix.hku.vo.app.ProgChoiceReqSubjectVo;
import com.accentrix.hku.vo.app.QualificationSubjectVo;
import com.accentrix.nttca.dcms.common.interceptor.AuditorAware;

@Service
public class RequirementValidationJob {

    @Autowired
    private RequirementRepository requirementRepository;
    @Autowired
    private QualificationRepository qualificationRepository;
    @Autowired
    private ProgrammeChoiceRepository programmeChoiceRepository;
    @Autowired
    private AppProgChoiceGpsRsltRepository appProgChoiceGpsRsltRepository;
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private IbdBestExamRepository ibdBestExamRepository;
    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private AuditorAware auditorAware;

    // @Scheduled(cron = "* 59 * * * ?")
    @Scheduled(cron = "10 * * * * ?")
    public void requirementValidation() {
        List<ProgChoiceReqSubjectVo> progChoiceReqSubjects = requirementRepository
                .findProgrammeChoiceRequirementSubjects();
        List<QualificationSubjectVo> qualificationSubjects = qualificationRepository.findQualificationRsltSubjects();
        String programmeChoiceId = "";
        boolean meetSubjectReq = false;
        for (ProgChoiceReqSubjectVo progChoiceReqSubject : progChoiceReqSubjects) {
            if (progChoiceReqSubject.getApplicationId().equals("ff808081641e1a9801642035922f0031")
                    && progChoiceReqSubject.getProgrammeChoiceId().equals("ff808081641e1a980164203a32fe0040"))
                System.out.println("ff80808163d3e6140163d40212d7000a");

            if (StringUtils.isBlank(programmeChoiceId)) {
                programmeChoiceId = progChoiceReqSubject.getProgrammeChoiceId();
            } else if (!programmeChoiceId.equals(progChoiceReqSubject.getProgrammeChoiceId())) {
                updateProgrammeChoiceStatus(programmeChoiceId, meetSubjectReq);
                programmeChoiceId = progChoiceReqSubject.getProgrammeChoiceId();
                meetSubjectReq = false;
            } else if (!meetSubjectReq) {
                continue;
            }
            meetSubjectReq = requirementValidation(progChoiceReqSubject, qualificationSubjects,
                    progChoiceReqSubject.getApplicationId());
        }
        if (StringUtils.isNotBlank(programmeChoiceId)) {
            updateProgrammeChoiceStatus(programmeChoiceId, meetSubjectReq);
        }
    }

    private void updateProgrammeChoiceStatus(String programmeChoiceId, boolean meetSubjectReq) {
        auditorAware.setCurrentAuditor("system");
        ProgrammeChoice programmeChoice = programmeChoiceRepository.findOne(programmeChoiceId);
        programmeChoice.setMeetReq(meetSubjectReq);
        if (meetSubjectReq) {
            if (Constants.CHOICE_SUBMITTED.equals(programmeChoice.getOfferStatusCd()))
                programmeChoice.setOfferStatusCd(Constants.CHOICE_SHORTLISTED);
        } else {
            programmeChoice.setOfferStatusCd(Constants.CHOICE_SUBMITTED);
        }
        programmeChoiceRepository.save(programmeChoice);
    }

    private boolean requirementValidation(ProgChoiceReqSubjectVo progChoiceReqSubject,
            List<QualificationSubjectVo> qualificationSubjects, String applicationId) {
        if (Constants.PUBLICEXAM.equals(progChoiceReqSubject.getRequirementType())) {
            for (QualificationSubjectVo qualificationSubject : qualificationSubjects) {
                if (applicationId.equals(qualificationSubject.getApplicationId())
                        && progChoiceReqSubject.getExamTypeId().equals(qualificationSubject.getExamTypeId())) {
                    if (progChoiceReqSubject.getSubject().contains(qualificationSubject.getExamSubjectDesc())
                            && progChoiceReqSubject.getExamLevel().equals(qualificationSubject.getExamLevel())) {
                        if (StringUtils.isNotBlank(qualificationSubject.getAchievedGradeCd())
                                && !Constants.OTHERS.equals(qualificationSubject.getAchievedGradeCd())) {
                            if (compareQualificationAndRequirementScore(progChoiceReqSubject, qualificationSubject,
                                    qualificationSubject.getAchievedGradeCd()))
                                return true;
                        }
                        //                        if (StringUtils.isNotBlank(qualificationSubject.getPredictedGradeCd())
                        //                                && !Constants.OTHERS.equals(qualificationSubject.getPredictedGradeCd())) {
                        //                            if (compareQualificationAndRequirementScore(progChoiceReqSubject, qualificationSubject,
                        //                                    qualificationSubject.getPredictedGradeCd()))
                        //                                return true;
                        //                        }
                    }
                }
            }
        } else if (Constants.GPS.equals(progChoiceReqSubject.getRequirementType())) {
            if (StringUtils.isNotBlank(progChoiceReqSubject.getExamTypeId())) {
                Type type = typeRepository.findOne(progChoiceReqSubject.getExamTypeId());
                if ("IBD".equals(type.getExamCd())) {
                    IbdBestExam ibdBestExamA42 = null;
                    IbdBestExam ibdBestExamPA42 = null;
                    IbdBestExam ibdBestExamA45 = null;
                    IbdBestExam ibdBestExamPA45 = null;
                    if (StringUtils.isNotBlank(progChoiceReqSubject.getApplicationId())) {
                        if (!progChoiceReqSubject.isOutOf45()) {
                            ibdBestExamA42 = ibdBestExamRepository.getBestIBD(progChoiceReqSubject.getApplicationId(),
                                    "Actual", "42");
                            ibdBestExamPA42 = ibdBestExamRepository.getBestIBD(progChoiceReqSubject.getApplicationId(),
                                    "Predicted and Actual", "42");
                        }
                        ibdBestExamA45 = ibdBestExamRepository.getBestIBD(progChoiceReqSubject.getApplicationId(),
                                "Actual", "45");
                        ibdBestExamPA45 = ibdBestExamRepository.getBestIBD(progChoiceReqSubject.getApplicationId(),
                                "Predicted and Actual", "45");
                    }
                    if ((ibdBestExamA42 != null && ibdBestExamA42.getTotalRslt() != null
                            && ibdBestExamA42.getTotalRslt() >= progChoiceReqSubject.getMinScore())
                            || (ibdBestExamPA42 != null && ibdBestExamPA42.getTotalRslt() != null
                                    && ibdBestExamPA42.getTotalRslt() >= progChoiceReqSubject.getMinScore())
                            || (ibdBestExamA45 != null && ibdBestExamA45.getTotalRslt() != null
                                    && ibdBestExamA45.getEeTok() != null
                                    && ibdBestExamA45.getTotalRslt() - ibdBestExamA45.getEeTok() >= progChoiceReqSubject
                                            .getMinScore())
                            || (ibdBestExamPA45 != null && ibdBestExamPA45.getTotalRslt() != null
                                    && ibdBestExamPA45.getEeTok() != null && ibdBestExamPA45.getTotalRslt()
                                            - ibdBestExamPA45.getEeTok() >= progChoiceReqSubject.getMinScore())) {
                        return true;
                    }
                } else {
                    AppProgChoiceGpsRslt progChoiceGpsRslt = appProgChoiceGpsRsltRepository
                            .getOneByProgrammeChoiceId(progChoiceReqSubject.getProgrammeChoiceId());
                    if (progChoiceGpsRslt != null && StringUtils.isNotBlank(progChoiceGpsRslt.getGpsRslt())) {
                        if (Integer.parseInt(progChoiceGpsRslt.getGpsRslt()) >= progChoiceReqSubject.getMinScore())
                            return true;
                    }
                }
            }
        }
        //        else if (Constants.COMPOSITE.equals(progChoiceReqSubject.getRequirementType())) {
        //            return compositeRequirementValidation(progChoiceReqSubject, qualificationSubjects, applicationId);
        //        }
        return false;
    }

    //    private boolean compositeRequirementValidation(ProgChoiceReqSubjectVo progChoiceReqSubject,
    //            List<QualificationSubjectVo> qualificationSubjects, String applicationId) {
    //        List<ProgChoiceReqSubjectVo> childReqSubjects = new ArrayList<ProgChoiceReqSubjectVo>();
    //        childReqSubjects = requirementRepository.findChildRequirementSubjectsForProgrammeChoice(progChoiceReqSubject,
    //                childReqSubjects);
    //        boolean meetReq = false;
    //        for (ProgChoiceReqSubjectVo childReqSubject : childReqSubjects) {
    //            meetReq = requirementValidation(childReqSubject, qualificationSubjects, applicationId);
    //            if (!meetReq)
    //                return meetReq;
    //        }
    //        return meetReq;
    //    }

    private boolean compareQualificationAndRequirementScore(ProgChoiceReqSubjectVo progChoiceReqSubject,
            QualificationSubjectVo qualificationSubject, String examGradeCd) {
        Type type = typeRepository.findOne(progChoiceReqSubject.getExamTypeId());
        Grade gradeReq = null;
        Grade gradeQua = null;
        if (Constants.EXAM_GCE_GRADE_LEVELS.contains(type.getExamCd())) {
            gradeReq = gradeRepository.getByExamTypeIdAndGradeCdAndExamLevel(progChoiceReqSubject.getExamTypeId(),
                    progChoiceReqSubject.getGrade(), progChoiceReqSubject.getExamLevel());
            gradeQua = gradeRepository.getByExamTypeIdAndGradeCdAndExamLevel(qualificationSubject.getExamTypeId(),
                    examGradeCd, qualificationSubject.getExamLevel());
        } else {
            gradeReq = gradeRepository.getByExamTypeIdAndGradeCd(progChoiceReqSubject.getExamTypeId(),
                    progChoiceReqSubject.getGrade());
            gradeQua = gradeRepository.getByExamTypeIdAndGradeCd(qualificationSubject.getExamTypeId(), examGradeCd);
        }
        if (gradeQua.getComparableValue() >= gradeReq.getComparableValue()) {
            return true;
        }
        return false;
    }

}
