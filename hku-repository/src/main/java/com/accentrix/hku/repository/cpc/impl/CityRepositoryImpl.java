package com.accentrix.hku.repository.cpc.impl;

import java.util.List;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.cpc.City;
import com.accentrix.hku.domain.cpc.QCity;
import com.accentrix.hku.repository.cpc.custom.CityRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月25日 上午11:57:46
 * @version 1.0
 */

public class CityRepositoryImpl extends JpaDslQuery<City, QCity> implements CityRepositoryCustom {

    @Override
    public List<City> findByCountryIdOrProvinceId(String countryId, String provinceId) {
        eq($.countryId, countryId);
        eq($.provinceId, provinceId);
        return list();
    }

}
