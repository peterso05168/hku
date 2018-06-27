package com.accentrix.hku.repository.campaign.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.campaign.CritChina;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年4月9日 下午2:25:49 
 * @version 1.0 
 */

@Repository
public interface CritChinaRepositoryCustom {

    List<CritChina> findByCpgnId(String cpgnId);
}
