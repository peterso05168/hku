package com.accentrix.hku.repository.cpc.impl;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.cpc.Country;
import com.accentrix.hku.domain.cpc.QCountry;
import com.accentrix.hku.repository.cpc.custom.CountryRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月25日 上午11:54:12
 * @version 1.0
 */

public class CountryRepositoryImpl extends JpaDslQuery<Country, QCountry> implements CountryRepositoryCustom {

    @Override
    public Country getByCode(String code) {
        eq($.code, code);
        return unique();
    }

    @Override
    public Country getByDesc(String desc) {
        eq($.description, desc);
        return unique();
    }

}
