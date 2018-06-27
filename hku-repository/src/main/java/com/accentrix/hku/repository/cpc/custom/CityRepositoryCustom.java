package com.accentrix.hku.repository.cpc.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.cpc.City;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年1月25日 上午11:57:40 
 * @version 1.0 
 */

@Repository
public interface CityRepositoryCustom {

    public List<City> findByCountryIdOrProvinceId(String countryId, String provinceId);
}
