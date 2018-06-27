package com.accentrix.hku.repository.campaign.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.campaign.CritChinaDtl;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年4月9日 下午2:26:20 
 * @version 1.0 
 */

@Repository
public interface CritChinaDtlRepositoryCustom {

    List<CritChinaDtl> findByCritChinaId(String cpgnChinaId);
}
