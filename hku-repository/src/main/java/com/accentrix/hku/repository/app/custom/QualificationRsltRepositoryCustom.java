package com.accentrix.hku.repository.app.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.QualificationRslt;
import com.accentrix.hku.vo.app.BestExamSubjRsltVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午7:10:34
 * @version 1.0
 */

@Repository
public interface QualificationRsltRepositoryCustom {
    List<QualificationRslt> getByAppQualificationId(String appQualificationId);

    List<QualificationRslt> findByQualificationIdInAndSubjectId(List<String> qualificationIds, String subjectId);

    List<String> getExamSubjectIdsByApplicationIdAndExamTypeId(String applicationId, String examTypeId);

    BestExamSubjRsltVo getBestExamSubjRsltByApplicationIdAndExamTypeIdAndExamSubjectId(String applicationId,
            String examTypeId, String subjectId);

    List<BestExamSubjRsltVo> getBestGceSubjsPredictedAndActualScores(String applicationId, String examTypeId,
            String subjectId);
}
