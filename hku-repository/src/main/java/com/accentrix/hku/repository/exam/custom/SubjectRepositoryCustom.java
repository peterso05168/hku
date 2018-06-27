package com.accentrix.hku.repository.exam.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.exam.Subject;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年1月31日 上午10:40:16 
 * @version 1.0 
 */

@Repository
public interface SubjectRepositoryCustom {
    Subject getBySubjectCdAndExamTypeId(String subjectCd, String examTypeId);

    List<Subject> getByExamTypeId(String examTypeId);

    List<Subject> findByExamTypeIdAndExamBoardAndExamLevel(String examTypeId, String examBoard, String examLevel);

    //    List<String> getIdsByExamTypeId(String examTypeId);

    List<Subject> findByExamTypeIdAndExamBoardAndExamLevelAndSubjectDescNotIn(String examTypeId, String examBoard,
            String examLevel, List<String> subjects);

    List<String> findExamLevelsByExamTypeIdAndExamBoardGroupByExamLevel(String examTypeId, String examBoard);

    List<String> findExamBoardsByExamTypeIdAndExamLevelGroupByExamBoard(String examTypeId, String examLevel);
}
