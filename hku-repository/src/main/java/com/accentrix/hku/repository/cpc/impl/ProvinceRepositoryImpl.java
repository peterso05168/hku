package com.accentrix.hku.repository.cpc.impl;

import java.util.List;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.cpc.Province;
import com.accentrix.hku.domain.cpc.QProvince;
import com.accentrix.hku.repository.cpc.custom.ProvinceRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月25日 上午11:57:02
 * @version 1.0
 */

public class ProvinceRepositoryImpl extends JpaDslQuery<Province, QProvince> implements ProvinceRepositoryCustom {

    @Override
    public List<Province> findByCountryId(String countryId) {
        eq($.countryId, countryId);
        return list();
    }

}
