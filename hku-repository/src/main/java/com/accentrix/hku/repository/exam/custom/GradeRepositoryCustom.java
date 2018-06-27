package com.accentrix.hku.repository.exam.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.exam.Grade;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 上午10:39:51
 * @version 1.0
 */

@Repository
public interface GradeRepositoryCustom {
    List<Grade> getByExamTypeId(String examTypeId);

    Grade getByExamTypeIdAndGradeCd(String examTypeId, String gradeCd);

    List<Grade> getByExamTypeIdAndExamLevel(String examTypeId, String examLevel);

    Grade getByExamTypeIdAndGradeCdAndExamLevel(String examTypeId, String gradeCd, String examLevel);
}
