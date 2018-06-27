package com.accentrix.hku.repository.exam.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.exam.QSubject;
import com.accentrix.hku.domain.exam.Subject;
import com.accentrix.hku.repository.exam.custom.SubjectRepositoryCustom;
import com.querydsl.core.types.dsl.BooleanExpression;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 上午10:40:22
 * @version 1.0
 */

public class SubjectRepositoryImpl extends JpaDslQuery<Subject, QSubject> implements SubjectRepositoryCustom {

    @Override
    public Subject getBySubjectCdAndExamTypeId(String subjectCd, String examTypeId) {
        eq($.examSubjectCd, subjectCd);
        eq($.examTypeId, examTypeId);
        return unique();
    }

    @Override
    public List<Subject> getByExamTypeId(String examTypeId) {
        eq($.examTypeId, examTypeId);
        return list();
    }

    @Override
    public List<Subject> findByExamTypeIdAndExamBoardAndExamLevelAndSubjectDescNotIn(String examTypeId,
            String examBoard, String examLevel, List<String> subjects) {
        eq($.examTypeId, examTypeId);
        eq($.examBoard, examBoard);
        eq($.examLevel, examLevel);
        notIn($.examSubjectDesc, subjects);
        return list();
    }

    @Override
    public List<String> findExamLevelsByExamTypeIdAndExamBoardGroupByExamLevel(String examTypeId, String examBoard) {
        BooleanExpression whereClause = $.examTypeId.eq(examTypeId);
        if (StringUtils.isNotBlank(examBoard))
            whereClause.and($.examBoard.eq(examBoard));
        return createJPAQuery().select($.examLevel).from($).where(whereClause).groupBy($.examLevel).fetch();
    }

    @Override
    public List<String> findExamBoardsByExamTypeIdAndExamLevelGroupByExamBoard(String examTypeId, String examLevel) {
        BooleanExpression whereClause = $.examTypeId.eq(examTypeId).and($.examBoard.isNotEmpty());
        if (StringUtils.isNotBlank(examLevel))
            whereClause.and($.examBoard.eq(examLevel));
        return createJPAQuery().select($.examBoard).from($).where(whereClause).groupBy($.examBoard).fetch();
    }

    @Override
    public List<Subject> findByExamTypeIdAndExamBoardAndExamLevel(String examTypeId, String examBoard,
            String examLevel) {
        eq($.examTypeId, examTypeId);
        eq($.examBoard, examBoard);
        eq($.examLevel, examLevel);
        return list();
    }

    //	@Override
    //	public List<String> getIdsByExamTypeId(String examTypeId) {
    //		return createJPAQuery().select($.id).from($).where($.examTypeId.eq(examTypeId)).fetch();
    //	}

}
