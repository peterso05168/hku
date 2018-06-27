package com.accentrix.hku.repository.campaign.impl;

import java.util.List;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.campaign.Mapping;
import com.accentrix.hku.domain.campaign.QMapping;
import com.accentrix.hku.domain.cpc.QCity;
import com.accentrix.hku.domain.cpc.QCountry;
import com.accentrix.hku.domain.cpc.QProvince;
import com.accentrix.hku.repository.campaign.custom.MappingRepositoryCustom;
import com.querydsl.core.BooleanBuilder;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年4月9日 下午2:28:23
 * @version 1.0
 */

public class MappingRepositoryImpl extends JpaDslQuery<Mapping, QMapping> implements MappingRepositoryCustom {

    @Override
    public List<Mapping> findByCountryIdAndProvinceId(String countryId, String provinceId) {
        QCountry country = QCountry.country;
        QProvince province = QProvince.province;
        QCity city = QCity.city;
        eq(country.id, countryId);
        orContains(province.id, city.provinceId, provinceId);
        BooleanBuilder builder = getCurrentBooleanBuilder();
        List<Mapping> list = createJPAQuery().select($).from($).leftJoin(province).on(province.id.eq($.studyProvinceId))
                .leftJoin(city).on(city.id.eq($.studyCityId)).leftJoin(country)
                .on(country.id.eq(province.countryId).or(city.countryId.eq(country.id))).where(builder).fetch();
        removeCurrentBooleanBuilder();
        return list;
    }

}
