package com.accentrix.hku.repository.adm.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.adm.GpaFormulaItm;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年3月19日 上午11:29:35 
 * @version 1.0 
 */

@Repository
public interface GpaFormulaItmRepositoryCustom {

    List<GpaFormulaItm> findByScoringFormulaId(String scoringFormulaId);

}
