package com.accentrix.hku.service.campaign.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.campaign.Campaign;
import com.accentrix.hku.repository.campaign.CampaignRepository;
import com.accentrix.hku.service.campaign.CampaignService;
import com.accentrix.hku.service.campaign.CritChinaService;
import com.accentrix.hku.service.campaign.CritNjService;
import com.accentrix.hku.service.cpc.CountryService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.campaign.CampaignForm;
import com.accentrix.hku.vo.campaign.CampaignVo;
import com.accentrix.hku.vo.campaign.CritChinaAndNjVo;
import com.accentrix.hku.vo.campaign.CritChinaVo;
import com.accentrix.hku.vo.campaign.CritNjVo;
import com.accentrix.hku.vo.cpc.CountryVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年4月9日 下午2:29:28
 * @version 1.0
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("campaign")
public class CampaignServiceImpl implements CampaignService {

    private final String CHINA = "China";
    private final String NJ = "NJ";

    @Autowired
    private CampaignRepository campaignRepository;
    @Autowired
    private CountryService countryService;
    @Autowired
    private CritChinaService critChinaService;
    @Autowired
    private CritNjService critNjService;

    @Override
    public CampaignVo get(String id) {
        Campaign campaign = campaignRepository.findOne(id);
        return toVo(campaign);
    }

    @Transactional
    @Override
    public CampaignVo save(CampaignVo vo) {
        Campaign campaign = toCampaign(vo);
        campaign = campaignRepository.save(campaign);
        vo.setId(campaign.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<CampaignVo> save(List<CampaignVo> vos) {
        List<CampaignVo> campaignVos = new ArrayList<CampaignVo>();
        for (CampaignVo campaignVo : vos) {
            Campaign campaign = toCampaign(campaignVo);
            campaign = campaignRepository.save(campaign);
            campaignVo.setId(campaign.getId());
            campaignVos.add(campaignVo);
        }
        return campaignVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        campaignRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(CampaignVo vo) {
        Campaign campaign = toCampaign(vo);
        campaignRepository.delete(campaign);
    }

    @Override
    public List<CampaignVo> findList() {
        List<Campaign> list = campaignRepository.findAll();
        List<CampaignVo> vos = new ArrayList<CampaignVo>();
        for (Campaign campaign : list) {
            vos.add(toVo(campaign));
        }
        return vos;
    }

    @Override
    public Page<CampaignVo> findPage(CampaignForm campaignForm) {
        Page<Campaign> page = campaignRepository.findPage(campaignForm.getCampaignVo(), campaignForm.getPageable());
        return page.map(s -> toVo(s));
    }

    private CampaignVo toVo(Campaign campaign) {
        CampaignVo vo = new CampaignVo();
        vo.setId(campaign.getId());
        vo.setCpgnName(campaign.getCpgnName());
        vo.setCpgnCd(campaign.getCpgnCd());
        vo.setCpgnDtl(campaign.getCpgnDtl());
        vo.setStatusCd(campaign.getStatusCd());
        vo.setMappedBy(campaign.getMappedBy());
        vo.setCountryId(campaign.getCountryId());
        vo.setCpgnYear(campaign.getCpgnYear());
        if (StringUtils.isNotBlank(campaign.getCountryId())) {
            CountryVo countryVo = countryService.get(campaign.getCountryId());
            vo.setCountryName(countryVo.getDescription());
        }
        return vo;
    }

    private Campaign toCampaign(CampaignVo vo) {
        Campaign campaign = new Campaign();
        campaign.setId(vo.getId());
        campaign.setCpgnName(vo.getCpgnName());
        campaign.setCpgnCd(vo.getCpgnCd());
        campaign.setCpgnDtl(vo.getCpgnDtl());
        campaign.setStatusCd(vo.getStatusCd());
        campaign.setMappedBy(vo.getMappedBy());
        campaign.setCountryId(vo.getCountryId());
        campaign.setCpgnYear(vo.getCpgnYear());
        return campaign;
    }

    @Override
    public List<CritChinaAndNjVo> findCritList(String cpgnId) {
        List<CritChinaAndNjVo> vos = new ArrayList<CritChinaAndNjVo>();
        List<CritChinaVo> critChinaVos = critChinaService.findByCpgnId(cpgnId);
        for (CritChinaVo critChinaVo : critChinaVos) {
            CritChinaAndNjVo vo = new CritChinaAndNjVo();
            vo.setId(critChinaVo.getId());
            vo.setName(critChinaVo.getName());
            vo.setSelectedForShortlist(critChinaVo.getSelectedForShortlist());
            vo.setType(CHINA);
            vos.add(vo);
        }
        List<CritNjVo> critNjVos = critNjService.findByCpgnId(cpgnId);
        for (CritNjVo critNjVo : critNjVos) {
            CritChinaAndNjVo vo = new CritChinaAndNjVo();
            vo.setId(critNjVo.getId());
            vo.setName(critNjVo.getName());
            vo.setSelectedForShortlist(critNjVo.getSelectedForShortlist());
            vo.setType(NJ);
            vos.add(vo);
        }
        return vos;
    }

}
