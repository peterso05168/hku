package com.accentrix.hku.repository.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.Requirement;
import com.accentrix.hku.repository.app.custom.RequirementRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 上午10:32:37
 * @version 1.0
 */

@Repository
public interface RequirementRepository extends JpaRepository<Requirement, String>, RequirementRepositoryCustom {

}
