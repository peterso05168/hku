package com.accentrix.hku.repository.adm.impl;

import java.util.List;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.adm.QScoringFormula;
import com.accentrix.hku.domain.adm.ScoringFormula;
import com.accentrix.hku.repository.adm.custom.ScoringFormulaRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年3月19日 上午11:30:20
 * @version 1.0
 */

public class ScoringFormulaRepositoryImpl extends JpaDslQuery<ScoringFormula, QScoringFormula>
        implements ScoringFormulaRepositoryCustom {

    @Override
    public List<ScoringFormula> findByFormProgId(String formProgId) {
        eq($.admFormProgId, formProgId);
        return list();
    }

}
