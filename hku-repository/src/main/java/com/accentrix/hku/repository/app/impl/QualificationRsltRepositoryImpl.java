package com.accentrix.hku.repository.app.impl;

import java.util.ArrayList;
import java.util.List;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.app.QQualification;
import com.accentrix.hku.domain.app.QQualificationRslt;
import com.accentrix.hku.domain.app.QualificationRslt;
import com.accentrix.hku.domain.exam.QGrade;
import com.accentrix.hku.domain.exam.QSubject;
import com.accentrix.hku.repository.app.custom.QualificationRsltRepositoryCustom;
import com.accentrix.hku.vo.app.BestExamSubjRsltVo;
import com.querydsl.core.Tuple;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午7:10:40
 * @version 1.0
 */

public class QualificationRsltRepositoryImpl extends JpaDslQuery<QualificationRslt, QQualificationRslt>
        implements QualificationRsltRepositoryCustom {

    @Override
    public List<QualificationRslt> getByAppQualificationId(String appQualificationId) {
        eq($.appQualificationId, appQualificationId);
        eq($.isDeleteApproved, false);
        return list();
    }

    @Override
    public List<QualificationRslt> findByQualificationIdInAndSubjectId(List<String> qualificationIds,
            String subjectId) {
        in($.appQualificationId, qualificationIds);
        eq($.examSubjectId, subjectId);
        return list();
    }

    @Override
    public List<String> getExamSubjectIdsByApplicationIdAndExamTypeId(String applicationId, String examTypeId) {
        QQualification qualification = QQualification.qualification;
        QSubject subject = QSubject.subject;
        List<String> subjectIds = createJPAQuery().select(subject.id).from(qualification).innerJoin($)
                .on(qualification.id.eq($.appQualificationId)).innerJoin(subject).on($.examSubjectId.eq(subject.id))
                .where(qualification.applicationId.eq(applicationId).and(qualification.examTypeId.eq(examTypeId))
                        .and($.isDeleteApproved.eq(false)).and(qualification.isDeleteApproved.eq(false)))
                .groupBy(subject.id).fetch();
        return subjectIds;
    }

    @Override
    public BestExamSubjRsltVo getBestExamSubjRsltByApplicationIdAndExamTypeIdAndExamSubjectId(String applicationId,
            String examTypeId, String subjectId) {
        QQualification qualification = QQualification.qualification;
        QSubject subject = QSubject.subject;
        QGrade grade = QGrade.grade;
        Tuple tuple = createJPAQuery()
                .select($.id, subject.examSubjectDesc, qualification.examTypeMonth, qualification.examTypeYear,
                        $.achievedGradeCd, grade.comparableValue)
                .from(qualification).innerJoin($).on(qualification.id.eq($.appQualificationId)).innerJoin(subject)
                .on($.examSubjectId.eq(subject.id)).innerJoin(grade)
                .on($.achievedGradeCd.eq(grade.gradeCd).and(qualification.examTypeId.eq(grade.examTypeId))
                        .and($.examLevel.eq(grade.examLevel)))
                .where(qualification.applicationId.eq(applicationId).and(qualification.examTypeId.eq(examTypeId))
                        .and(subject.id.eq(subjectId)))
                .orderBy(grade.comparableValue.desc()).limit(1).fetchOne();
        if (tuple != null) {
            BestExamSubjRsltVo vo = new BestExamSubjRsltVo();
            vo.setQualificationRsltId(tuple.get($.id));
            vo.setExamSubjectDesc(tuple.get(subject.examSubjectDesc));
            vo.setExamTypeMonth(tuple.get(qualification.examTypeMonth).toString());
            vo.setExamTypeYear(tuple.get(qualification.examTypeYear));
            vo.setExamGradeCd(tuple.get($.achievedGradeCd));
            vo.setComparableValue(tuple.get(grade.comparableValue));
            vo.setRsltType("Achieved");
            return vo;
        }
        return null;
    }

    @Override
    public List<BestExamSubjRsltVo> getBestGceSubjsPredictedAndActualScores(String applicationId, String examTypeId,
            String subjectId) {
        QQualification qualification = QQualification.qualification;
        QSubject subject = QSubject.subject;
        QGrade grade = QGrade.grade;
        List<Tuple> tuples = createJPAQuery()
                .select($.id, subject.examSubjectDesc, qualification.examTypeMonth, qualification.examTypeYear,
                        $.achievedGradeCd, $.predictedGradeCd, grade.comparableValue, grade.examLevel)
                .from(qualification).innerJoin($).on(qualification.id.eq($.appQualificationId)).innerJoin(subject)
                .on($.examSubjectId.eq(subject.id)).innerJoin(grade)
                .on(($.achievedGradeCd.eq(grade.gradeCd).or($.predictedGradeCd.eq(grade.gradeCd)))
                        .and(qualification.examTypeId.eq(grade.examTypeId)).and($.examLevel.eq(grade.examLevel)))
                .where(qualification.applicationId.eq(applicationId).and(qualification.examTypeId.eq(examTypeId))
                        .and(subject.id.eq(subjectId)).and($.isDeleteApproved.eq(false))
                        .and(qualification.isDeleteApproved.eq(false)))
                .orderBy(grade.comparableValue.desc()).fetch();
        List<BestExamSubjRsltVo> vos = new ArrayList<BestExamSubjRsltVo>();
        for (Tuple tuple : tuples) {
            BestExamSubjRsltVo vo = new BestExamSubjRsltVo();
            vo.setQualificationRsltId(tuple.get($.id));
            vo.setExamSubjectDesc(tuple.get(subject.examSubjectDesc));
            vo.setExamTypeMonth(tuple.get(qualification.examTypeMonth).toString());
            vo.setExamTypeYear(tuple.get(qualification.examTypeYear));
            vo.setExamGradeCd(tuple.get($.achievedGradeCd));
            vo.setPredictedGradeCd(tuple.get($.predictedGradeCd));
            vo.setComparableValue(tuple.get(grade.comparableValue));
            vo.setExamLevel(tuple.get(grade.examLevel));
            vo.setRsltType("Achieved");
            vos.add(vo);
        }
        return vos;
    }
}
