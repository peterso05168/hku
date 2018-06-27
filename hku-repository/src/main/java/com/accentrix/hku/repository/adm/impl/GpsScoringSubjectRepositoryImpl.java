package com.accentrix.hku.repository.adm.impl;

import java.util.List;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.adm.GpsScoringSubject;
import com.accentrix.hku.domain.adm.QGpsScoringSubject;
import com.accentrix.hku.repository.adm.custom.GpsScoringSubjectRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年3月19日 上午11:30:28
 * @version 1.0
 */

public class GpsScoringSubjectRepositoryImpl extends JpaDslQuery<GpsScoringSubject, QGpsScoringSubject>
        implements GpsScoringSubjectRepositoryCustom {

    @Override
    public List<GpsScoringSubject> findByScoringFormulaId(String scoringFormulaId, String type) {
        eq($.admScoringFormulaId, scoringFormulaId);
        eq($.type, type);
        return list();
    }

}
