package com.accentrix.hku.repository.campaign.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.campaign.Centre;
import com.accentrix.hku.vo.campaign.CentreVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年4月9日 下午2:24:56 
 * @version 1.0 
 */

@Repository
public interface CentreRepositoryCustom {

    List<Centre> findByCpgnId(String cpgnId);

    List<CentreVo> findByCpgnIdAndProvinceOrCity(String cpgnId, String type);

    List<Centre> findByType(String type, String provinceOrCity, String cpgnId);

    List<Centre> findByIds(List<String> ids, String type, String provinceOrCity, String cpgnId);

    List<Centre> findByIdList(List<String> ids);
}
