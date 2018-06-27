package com.accentrix.hku.repository.campaign.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.campaign.CpgnSession;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年4月9日 下午2:25:21 
 * @version 1.0 
 */

@Repository
public interface CpgnSessionRepositoryCustom {

    List<CpgnSession> findByCentreId(String centreId);
}
