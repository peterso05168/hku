package com.accentrix.hku.repository.app.impl;

import java.util.List;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.app.PrevStudies;
import com.accentrix.hku.domain.app.QPrevStudies;
import com.accentrix.hku.repository.app.custom.PrevStudiesRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午7:08:40
 * @version 1.0
 */

public class PrevStudiesRepositoryImpl extends JpaDslQuery<PrevStudies, QPrevStudies>
        implements PrevStudiesRepositoryCustom {

    @Override
    public List<PrevStudies> findListByApplicationId(String applicationId) {
        eq($.applicationId, applicationId);
        eq($.isDeleted, false);
        return list();
    }

}
