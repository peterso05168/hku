package com.accentrix.hku.repository.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.Institution;
import com.accentrix.hku.repository.app.custom.InstitutionRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月25日 下午12:00:44
 * @version 1.0
 */

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, String>, InstitutionRepositoryCustom {

}
