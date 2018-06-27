package com.accentrix.hku.repository.campaign.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.campaign.CritNj;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年4月9日 下午2:26:50 
 * @version 1.0 
 */

@Repository
public interface CritNjRepositoryCustom {

    List<CritNj> findByCpgnId(String cpgnId);
}
