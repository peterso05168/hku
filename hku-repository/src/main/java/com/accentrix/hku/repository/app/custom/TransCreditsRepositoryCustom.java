package com.accentrix.hku.repository.app.custom;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.TransCredits;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年2月9日 下午3:11:19
 * @version 1.0
 */

@Repository
public interface TransCreditsRepositoryCustom {
    TransCredits getByApplicationId(String applicationId);
}
