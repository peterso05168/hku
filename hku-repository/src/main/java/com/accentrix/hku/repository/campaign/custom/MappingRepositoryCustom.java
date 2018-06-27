package com.accentrix.hku.repository.campaign.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.campaign.Mapping;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年4月9日 下午2:28:18 
 * @version 1.0 
 */

@Repository
public interface MappingRepositoryCustom {

    List<Mapping> findByCountryIdAndProvinceId(String countryId, String provinceId);
}
