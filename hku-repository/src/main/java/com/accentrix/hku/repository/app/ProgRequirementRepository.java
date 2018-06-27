package com.accentrix.hku.repository.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.ProgRequirement;
import com.accentrix.hku.repository.app.custom.ProgRequirementRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午7:09:27
 * @version 1.0
 */

@Repository
public interface ProgRequirementRepository
        extends JpaRepository<ProgRequirement, String>, ProgRequirementRepositoryCustom {

}
