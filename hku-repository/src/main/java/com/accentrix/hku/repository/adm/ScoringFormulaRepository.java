package com.accentrix.hku.repository.adm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.adm.ScoringFormula;
import com.accentrix.hku.repository.adm.custom.ScoringFormulaRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年3月19日 上午11:28:32
 * @version 1.0
 */

@Repository
public interface ScoringFormulaRepository
        extends JpaRepository<ScoringFormula, String>, ScoringFormulaRepositoryCustom {

}
