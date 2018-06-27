package com.accentrix.hku.repository.campaign;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.campaign.Campaign;
import com.accentrix.hku.repository.campaign.custom.CampaignRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年4月9日 下午2:24:10
 * @version 1.0
 */

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, String>, CampaignRepositoryCustom {

}
