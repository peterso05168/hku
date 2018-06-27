package com.accentrix.hku.repository.app.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.Requirement;
import com.accentrix.hku.vo.app.ProgChoiceReqSubjectVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 上午10:32:47
 * @version 1.0
 */

@Repository
public interface RequirementRepositoryCustom {

    public List<Requirement> findList(String desc, String type, Boolean isPublished);

    List<Requirement> findByIdNotIn(List<String> ids);

    List<ProgChoiceReqSubjectVo> findProgrammeChoiceRequirementSubjects();

    List<ProgChoiceReqSubjectVo> findChildRequirementSubjectsForProgrammeChoice(
            ProgChoiceReqSubjectVo progChoiceReqSubject, List<ProgChoiceReqSubjectVo> progChoiceReqSubjects);
}
