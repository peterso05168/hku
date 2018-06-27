package com.accentrix.hku.repository.scholar.impl;

import java.util.List;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.scholar.QScholarDtl;
import com.accentrix.hku.domain.scholar.ScholarDtl;
import com.accentrix.hku.repository.scholar.custom.ScholarDtlRepositoryCustom;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年3月19日 上午11:36:31 
 * @version 1.0 
 */

public class ScholarDtlRepositoryImpl extends JpaDslQuery<ScholarDtl, QScholarDtl>
        implements ScholarDtlRepositoryCustom {

    @Override
    public List<ScholarDtl> findByScholarId(String scholarId) {
        eq($.scholarId, scholarId);
        return list();
    }

}
