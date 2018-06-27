package com.accentrix.hku.repository.app.custom;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.SpecialScheme;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 上午10:34:05
 * @version 1.0
 */

@Repository
public interface SpecialSchemeRepositoryCustom {

    SpecialScheme getByCodeAndApplicationId(String code, String applicationId);
}
