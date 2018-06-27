package com.accentrix.hku.repository.app.impl;

import org.apache.commons.lang.StringUtils;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.app.QTransCredits;
import com.accentrix.hku.domain.app.TransCredits;
import com.accentrix.hku.repository.app.custom.TransCreditsRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年2月9日 下午3:11:25
 * @version 1.0
 */

public class TransCreditsRepositoryImpl extends JpaDslQuery<TransCredits, QTransCredits>
        implements TransCreditsRepositoryCustom {

    @Override
    public TransCredits getByApplicationId(String applicationId) {
        if (StringUtils.isNotBlank(applicationId)) {
            eq($.applicationId, applicationId);
        } else {
            isNull($.applicationId);
        }
        return unique();
    }

}
