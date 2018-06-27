package com.accentrix.hku.repository.cpc.custom;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.cpc.Country;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年1月25日 上午11:54:06 
 * @version 1.0 
 */

@Repository
public interface CountryRepositoryCustom {

    Country getByDesc(String desc);

    Country getByCode(String code);
}
