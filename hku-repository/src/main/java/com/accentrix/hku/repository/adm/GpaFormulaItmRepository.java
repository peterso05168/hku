package com.accentrix.hku.repository.adm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.adm.GpaFormulaItm;
import com.accentrix.hku.repository.adm.custom.GpaFormulaItmRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年3月19日 上午11:29:23
 * @version 1.0
 */

@Repository
public interface GpaFormulaItmRepository extends JpaRepository<GpaFormulaItm, String>, GpaFormulaItmRepositoryCustom {

}
