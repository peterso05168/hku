package com.accentrix.hku.repository.scholar.impl;

import java.util.List;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.scholar.QScholarDtlRequirement;
import com.accentrix.hku.domain.scholar.ScholarDtlRequirement;
import com.accentrix.hku.repository.scholar.custom.ScholarDtlRequirementRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年3月19日 上午11:36:41
 * @version 1.0
 */

public class ScholarDtlRequirementRepositoryImpl extends JpaDslQuery<ScholarDtlRequirement, QScholarDtlRequirement>
        implements ScholarDtlRequirementRepositoryCustom {

    @Override
    public List<ScholarDtlRequirement> findByScholarDtlId(String scholarDtlId) {
        eq($.scholarDtlId, scholarDtlId);
        return list();
    }

}
