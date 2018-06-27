package com.accentrix.hku.repository.campaign.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.campaign.CritNjToProg;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年4月9日 下午2:27:17 
 * @version 1.0 
 */

@Repository
public interface CritNjToProgRepositoryCustom {

    List<String> findByCritNjId(String critNjId);

    List<CritNjToProg> findByCritNjIdToList(String critNjId);
}
