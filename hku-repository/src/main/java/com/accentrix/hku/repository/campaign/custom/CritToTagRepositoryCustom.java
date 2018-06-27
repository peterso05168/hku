package com.accentrix.hku.repository.campaign.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.campaign.CritToTag;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年4月9日 下午2:27:49 
 * @version 1.0 
 */

@Repository
public interface CritToTagRepositoryCustom {

    List<String> findByCrit(String critId, String type);

    List<CritToTag> findByCritToList(String critId, String type);
}
