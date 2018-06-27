package com.accentrix.hku.repository.app.custom;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.AppNjceeScoringSystem;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年4月25日 下午7:49:49
 */
@Repository
public interface AppNjceeScoringSystemRepositoryCustom {
    AppNjceeScoringSystem getByProvinceIdAndStream(String provinceId, String stream);
}
