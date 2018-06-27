package com.accentrix.hku.repository.adm.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.adm.ScoringFormula;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年3月19日 上午11:30:08 
 * @version 1.0 
 */

@Repository
public interface ScoringFormulaRepositoryCustom {

    List<ScoringFormula> findByFormProgId(String formProgId);

}
