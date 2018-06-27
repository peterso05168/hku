package com.accentrix.hku.repository.app.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.ExpAndAchievements;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午7:05:47
 * @version 1.0
 */

@Repository
public interface ExpAndAchievementsRepositoryCustom {

    public List<ExpAndAchievements> findListByApplicationId(String applicationId);

    public List<ExpAndAchievements> findListByApplicationIdAndCat(String applicationId, String catCd);
}
