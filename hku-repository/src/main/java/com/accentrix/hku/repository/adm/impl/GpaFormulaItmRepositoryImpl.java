package com.accentrix.hku.repository.adm.impl;

import java.util.List;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.adm.GpaFormulaItm;
import com.accentrix.hku.domain.adm.QGpaFormulaItm;
import com.accentrix.hku.repository.adm.custom.GpaFormulaItmRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年3月19日 上午11:30:37
 * @version 1.0
 */

public class GpaFormulaItmRepositoryImpl extends JpaDslQuery<GpaFormulaItm, QGpaFormulaItm>
        implements GpaFormulaItmRepositoryCustom {

    @Override
    public List<GpaFormulaItm> findByScoringFormulaId(String scoringFormulaId) {
        eq($.admScoringFormulaId, scoringFormulaId);
        return list();
    }

}
