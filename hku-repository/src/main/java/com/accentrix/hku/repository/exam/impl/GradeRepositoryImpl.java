package com.accentrix.hku.repository.exam.impl;

import java.util.List;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.exam.Grade;
import com.accentrix.hku.domain.exam.QGrade;
import com.accentrix.hku.repository.exam.custom.GradeRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 上午10:39:57
 * @version 1.0
 */

public class GradeRepositoryImpl extends JpaDslQuery<Grade, QGrade> implements GradeRepositoryCustom {

    @Override
    public List<Grade> getByExamTypeId(String examTypeId) {
        eq($.examTypeId, examTypeId);
        return list();
    }

    @Override
    public Grade getByExamTypeIdAndGradeCd(String examTypeId, String gradeCd) {
        eq($.examTypeId, examTypeId);
        eq($.gradeCd, gradeCd);
        return unique();
    }

    @Override
    public List<Grade> getByExamTypeIdAndExamLevel(String examTypeId, String examLevel) {
        eq($.examTypeId, examTypeId);
        eq($.examLevel, examLevel);
        return list();
    }

    @Override
    public Grade getByExamTypeIdAndGradeCdAndExamLevel(String examTypeId, String gradeCd, String examLevel) {
        eq($.examTypeId, examTypeId);
        eq($.gradeCd, gradeCd);
        eq($.examLevel, examLevel);
        return unique();
    }

}
