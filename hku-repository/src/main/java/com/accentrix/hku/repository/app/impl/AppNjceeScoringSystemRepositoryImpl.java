package com.accentrix.hku.repository.app.impl;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.app.AppNjceeScoringSystem;
import com.accentrix.hku.domain.app.QAppNjceeScoringSystem;
import com.accentrix.hku.repository.app.custom.AppNjceeScoringSystemRepositoryCustom;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年4月25日 下午7:52:30
 */
public class AppNjceeScoringSystemRepositoryImpl extends JpaDslQuery<AppNjceeScoringSystem, QAppNjceeScoringSystem>
        implements AppNjceeScoringSystemRepositoryCustom {

    @Override
    public AppNjceeScoringSystem getByProvinceIdAndStream(String provinceId, String stream) {
        eq($.provinceId, provinceId);
        eq($.stream, stream);
        return unique();
    }

}
