package com.accentrix.hku.repository.app.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.Qualification;
import com.accentrix.hku.vo.app.QualificationSubjectVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午7:10:07
 * @version 1.0
 */

@Repository
public interface QualificationRepositoryCustom {
    List<Qualification> getByApplicationId(String applicationId);

    List<Qualification> getByApplicationIdAndExamTypeId(String applicationId, String examTypeId);

    void calculate_actual_score_GCE();

    void calculate_predicted_actual_score_GCE();

    void calculate_actual_score_IBD_42();

    void calculate_actual_score_IBD_45();

    void calculate_predicted_actual_score_IBD_42();

    void calculate_predicted_actual_score_IBD_45();

    List<QualificationSubjectVo> findQualificationRsltSubjects();

    void calculate_IB_GPS();

    void calculate_GCE_GPS();
}
