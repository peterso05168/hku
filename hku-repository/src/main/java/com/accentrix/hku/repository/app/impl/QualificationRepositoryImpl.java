package com.accentrix.hku.repository.app.impl;

import java.util.ArrayList;
import java.util.List;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.app.QQualification;
import com.accentrix.hku.domain.app.QQualificationRslt;
import com.accentrix.hku.domain.app.Qualification;
import com.accentrix.hku.domain.applicant.QApplication;
import com.accentrix.hku.domain.exam.QGrade;
import com.accentrix.hku.domain.exam.QSubject;
import com.accentrix.hku.repository.app.custom.QualificationRepositoryCustom;
import com.accentrix.hku.vo.app.QualificationSubjectVo;
import com.querydsl.core.Tuple;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午7:10:13
 * @version 1.0
 */

public class QualificationRepositoryImpl extends JpaDslQuery<Qualification, QQualification>
        implements QualificationRepositoryCustom {

    @Override
    public List<Qualification> getByApplicationId(String applicationId) {
        eq($.applicationId, applicationId);
        eq($.isDeleteApproved, false);
        return list();
    }

    @Override
    public List<Qualification> getByApplicationIdAndExamTypeId(String applicationId, String examTypeId) {
        eq($.applicationId, applicationId);
        eq($.examTypeId, examTypeId);
        eq($.isDeleteApproved, false);
        return list();
    }

    @Override
    public void calculate_actual_score_GCE() {
        @SuppressWarnings("unused")
        Object object = getEntityManager().createNativeQuery("call calculate_actual_score_GCE();").executeUpdate();
    }

    @Override
    public void calculate_predicted_actual_score_GCE() {
        @SuppressWarnings("unused")
        Object object = getEntityManager().createNativeQuery("call calculate_predicted_actual_score_GCE();")
                .executeUpdate();
    }

    @Override
    public void calculate_actual_score_IBD_42() {
        @SuppressWarnings("unused")
        Object object = getEntityManager().createNativeQuery("call calculate_actual_score_IBD_42()").executeUpdate();
    }

    @Override
    public void calculate_actual_score_IBD_45() {
        @SuppressWarnings("unused")
        Object object = getEntityManager().createNativeQuery("call calculate_actual_score_IBD_45()").executeUpdate();
    }

    @Override
    public void calculate_predicted_actual_score_IBD_42() {
        @SuppressWarnings("unused")
        Object object = getEntityManager().createNativeQuery("call calculate_predicted_actual_score_IBD_42()")
                .executeUpdate();
    }

    @Override
    public void calculate_predicted_actual_score_IBD_45() {
        @SuppressWarnings("unused")
        Object object = getEntityManager().createNativeQuery("call calculate_predicted_actual_score_IBD_45()")
                .executeUpdate();
    }

    @Override
    public List<QualificationSubjectVo> findQualificationRsltSubjects() {
        QApplication application = QApplication.application;
        QQualificationRslt qualificationRslt = QQualificationRslt.qualificationRslt;
        QSubject subject = QSubject.subject;
        QGrade gradeA = QGrade.grade;
        QGrade gradeP = QGrade.grade;
        List<Tuple> tuples = createJPAQuery()
                .select($.applicationId, $.id, $.examTypeId, qualificationRslt.id, subject.id, subject.examSubjectDesc,
                        qualificationRslt.achievedGradeCd, qualificationRslt.achievedGradeOthers,
                        qualificationRslt.predictedGradeCd, qualificationRslt.predictedGradeOthers,
                        qualificationRslt.examLevel, gradeA.comparableValue, gradeP.comparableValue)
                .from(application).innerJoin($).on(application.id.eq($.applicationId)).innerJoin(qualificationRslt)
                .on($.id.eq(qualificationRslt.appQualificationId)).innerJoin(subject)
                .on(qualificationRslt.examSubjectId.eq(subject.id)).leftJoin(gradeA)
                .on(qualificationRslt.achievedGradeCd.eq(gradeA.gradeCd)).leftJoin(gradeP)
                .on(qualificationRslt.predictedGradeCd.eq(gradeA.gradeCd))
                .where(qualificationRslt.isDeleteApproved.eq(false).and($.isDeleteApproved.eq(false)))
                .orderBy($.applicationId.asc(), $.id.asc()).fetch();
        List<QualificationSubjectVo> qualificationSubjects = new ArrayList<QualificationSubjectVo>();
        for (Tuple tuple : tuples) {
            QualificationSubjectVo qualificationSubject = new QualificationSubjectVo();
            qualificationSubject.setApplicationId(tuple.get($.applicationId));
            qualificationSubject.setQualificationId(tuple.get($.id));
            qualificationSubject.setExamTypeId(tuple.get($.examTypeId));
            qualificationSubject.setQualificationRsltId(tuple.get(qualificationRslt.id));
            qualificationSubject.setExamSubjectId(tuple.get(subject.id));
            qualificationSubject.setExamSubjectDesc(tuple.get(subject.examSubjectDesc));
            String achievedCd = tuple.get(qualificationRslt.achievedGradeCd);
            String predictedCd = tuple.get(qualificationRslt.predictedGradeCd);
            if (achievedCd != null && !"".equals(achievedCd.trim()))
                qualificationSubject.setAchievedGradeCd(achievedCd);
            else if (predictedCd != null && !"".equals(predictedCd.trim()))
                qualificationSubject.setAchievedGradeCd(predictedCd);
            qualificationSubject.setExamLevel(tuple.get(qualificationRslt.examLevel));
            qualificationSubjects.add(qualificationSubject);
        }
        return qualificationSubjects;
    }

    @Override
    public void calculate_IB_GPS() {
        @SuppressWarnings("unused")
        Object object = getEntityManager().createNativeQuery("call calculate_IB_GPS()").executeUpdate();
    }

    @Override
    public void calculate_GCE_GPS() {
        @SuppressWarnings("unused")
        Object object = getEntityManager().createNativeQuery("call calculate_GCE_GPS()").executeUpdate();
    }
}
