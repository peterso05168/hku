package com.accentrix.hku.repository.campaign.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.campaign.Campaign;
import com.accentrix.hku.domain.campaign.QCampaign;
import com.accentrix.hku.repository.campaign.custom.CampaignRepositoryCustom;
import com.accentrix.hku.vo.campaign.CampaignVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年4月9日 下午2:24:38
 * @version 1.0
 */

public class CampaignRepositoryImpl extends JpaDslQuery<Campaign, QCampaign> implements CampaignRepositoryCustom {

    @Override
    public Page<Campaign> findPage(CampaignVo campaignVo, Pageable pageable) {
        contains($.cpgnCd, campaignVo.getCpgnCd());
        contains($.cpgnName, campaignVo.getCpgnName());
        eq($.statusCd, campaignVo.getStatusCd());
        if (StringUtils.isNotBlank(campaignVo.getCombination())) {
            removeCurrentBooleanBuilder();
            orContains($.cpgnCd, $.cpgnName, campaignVo.getCombination());
        }
        addSort(pageable.getSort());
        return findAll(pageable);
    }

}
