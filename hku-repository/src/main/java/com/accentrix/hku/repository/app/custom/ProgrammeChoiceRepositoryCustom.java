package com.accentrix.hku.repository.app.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.ProgrammeChoice;
import com.accentrix.hku.vo.app.ProgrammeChoiceVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午7:09:05
 * @version 1.0
 */

@Repository
public interface ProgrammeChoiceRepositoryCustom {

    ProgrammeChoice getFirstChoiceByApplicationId(String applicationId);

    List<ProgrammeChoice> getOtherChoiceByApplicationId(String applicationId);

    List<ProgrammeChoice> findByHkuProgrammeId(String programmeId);

    List<ProgrammeChoice> findByFormProgId(String formProgId);

    List<ProgrammeChoice> getChoiceByApplicationId(String applicationId);

    List<ProgrammeChoiceVo> findListVo(ProgrammeChoiceVo vo, Integer offset, Integer pageSize);

    int findCount(ProgrammeChoiceVo vo);
}
