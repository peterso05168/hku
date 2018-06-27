package com.accentrix.hku.repository.cpc.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.cpc.Province;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年1月25日 上午11:56:56 
 * @version 1.0 
 */

@Repository
public interface ProvinceRepositoryCustom {

    public List<Province> findByCountryId(String countryId);
}
