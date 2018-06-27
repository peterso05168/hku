package com.accentrix.hku.repository.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.ExpAndAchievements;
import com.accentrix.hku.repository.app.custom.ExpAndAchievementsRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午7:05:40
 * @version 1.0
 */

@Repository
public interface ExpAndAchievementsRepository
        extends JpaRepository<ExpAndAchievements, String>, ExpAndAchievementsRepositoryCustom {

}
