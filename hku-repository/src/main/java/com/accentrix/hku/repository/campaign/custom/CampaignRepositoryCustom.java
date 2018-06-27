package com.accentrix.hku.repository.campaign.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.campaign.Campaign;
import com.accentrix.hku.vo.campaign.CampaignVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年4月9日 下午2:24:32
 * @version 1.0
 */

@Repository
public interface CampaignRepositoryCustom {

    Page<Campaign> findPage(CampaignVo campaignVo, Pageable pageable);
}
