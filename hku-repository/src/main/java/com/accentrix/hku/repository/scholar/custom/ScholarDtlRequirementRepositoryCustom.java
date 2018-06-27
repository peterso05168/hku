package com.accentrix.hku.repository.scholar.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.scholar.ScholarDtlRequirement;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年3月19日 上午11:36:07 
 * @version 1.0 
 */

@Repository
public interface ScholarDtlRequirementRepositoryCustom {

    List<ScholarDtlRequirement> findByScholarDtlId(String scholarDtlId);

}
