package com.accentrix.hku.repository.app.impl;

import java.util.List;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.app.ExpAndAchievements;
import com.accentrix.hku.domain.app.QExpAndAchievements;
import com.accentrix.hku.repository.app.custom.ExpAndAchievementsRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午7:05:52
 * @version 1.0
 */

public class ExpAndAchievementsRepositoryImpl extends JpaDslQuery<ExpAndAchievements, QExpAndAchievements>
        implements ExpAndAchievementsRepositoryCustom {

    @Override
    public List<ExpAndAchievements> findListByApplicationId(String applicationId) {
        eq($.applicationId, applicationId);
        eq($.isDeleted, false);
        return list();
    }

    @Override
    public List<ExpAndAchievements> findListByApplicationIdAndCat(String applicationId, String catCd) {
        eq($.applicationId, applicationId);
        eq($.isDeleted, false);
        eq($.activityCatCd, catCd);
        return list();
    }
}
