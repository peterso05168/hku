package com.accentrix.hku.repository.scholar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.scholar.ScholarDtlRequirement;
import com.accentrix.hku.repository.scholar.custom.ScholarDtlRequirementRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年3月19日 上午11:35:28
 * @version 1.0
 */

@Repository
public interface ScholarDtlRequirementRepository
        extends JpaRepository<ScholarDtlRequirement, String>, ScholarDtlRequirementRepositoryCustom {

}
