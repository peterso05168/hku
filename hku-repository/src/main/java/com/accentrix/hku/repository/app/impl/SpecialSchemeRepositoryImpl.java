package com.accentrix.hku.repository.app.impl;

import org.apache.commons.lang.StringUtils;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.app.QSpecialScheme;
import com.accentrix.hku.domain.app.SpecialScheme;
import com.accentrix.hku.repository.app.custom.SpecialSchemeRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 上午10:34:13
 * @version 1.0
 */

public class SpecialSchemeRepositoryImpl extends JpaDslQuery<SpecialScheme, QSpecialScheme>
        implements SpecialSchemeRepositoryCustom {

    @Override
    public SpecialScheme getByCodeAndApplicationId(String code, String applicationId) {
        eq($.specialSchemeCd, code);
        if (StringUtils.isNotBlank(applicationId)) {
            eq($.applicationId, applicationId);
        } else {
            isNull($.applicationId);
        }
        return unique();
    }

}
