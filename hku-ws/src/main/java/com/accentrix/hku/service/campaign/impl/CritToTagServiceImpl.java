package com.accentrix.hku.service.campaign.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.campaign.CritToTag;
import com.accentrix.hku.repository.campaign.CritToTagRepository;
import com.accentrix.hku.service.campaign.CritToTagService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.campaign.CritToTagVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年4月9日 下午2:31:52
 * @version 1.0
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("critToTag")
public class CritToTagServiceImpl implements CritToTagService {

    @Autowired
    private CritToTagRepository critToTagRepository;

    @Override
    public CritToTagVo get(String id) {
        CritToTag critToTag = critToTagRepository.findOne(id);
        return toVo(critToTag);
    }

    @Transactional
    @Override
    public CritToTagVo save(CritToTagVo vo) {
        CritToTag critToTag = toCritToTag(vo);
        critToTag = critToTagRepository.save(critToTag);
        vo.setId(critToTag.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<CritToTagVo> save(List<CritToTagVo> vos) {
        List<CritToTagVo> critToTagVos = new ArrayList<CritToTagVo>();
        for (CritToTagVo critToTagVo : vos) {
            CritToTag critToTag = toCritToTag(critToTagVo);
            critToTag = critToTagRepository.save(critToTag);
            critToTagVo.setId(critToTag.getId());
            critToTagVos.add(critToTagVo);
        }
        return critToTagVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        critToTagRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(CritToTagVo vo) {
        CritToTag critToTag = toCritToTag(vo);
        critToTagRepository.delete(critToTag);
    }

    @Override
    public List<CritToTagVo> findList() {
        List<CritToTag> list = critToTagRepository.findAll();
        List<CritToTagVo> vos = new ArrayList<CritToTagVo>();
        for (CritToTag critToTag : list) {
            vos.add(toVo(critToTag));
        }
        return vos;
    }

    private CritToTagVo toVo(CritToTag critToTag) {
        CritToTagVo vo = new CritToTagVo();
        vo.setId(critToTag.getId());
        vo.setCpgnCritId(critToTag.getCpgnCritId());
        vo.setTagId(critToTag.getTagId());
        vo.setType(critToTag.getType());
        return vo;
    }

    private CritToTag toCritToTag(CritToTagVo vo) {
        CritToTag critToTag = new CritToTag();
        critToTag.setId(vo.getId());
        critToTag.setCpgnCritId(vo.getCpgnCritId());
        critToTag.setTagId(vo.getTagId());
        critToTag.setType(vo.getType());
        return critToTag;
    }

    @Override
    public List<String> findByCrit(String critId, String type) {
        List<String> list = critToTagRepository.findByCrit(critId, type);
        if (list != null && list.size() > 0) {
            return list;
        }
        return new ArrayList<String>();
    }

    @Override
    public List<CritToTagVo> findByCritToList(String critId, String type) {
        List<CritToTag> list = critToTagRepository.findByCritToList(critId, type);
        List<CritToTagVo> vos = new ArrayList<CritToTagVo>();
        for (CritToTag critToTag : list) {
            vos.add(toVo(critToTag));
        }
        return vos;
    }
}
