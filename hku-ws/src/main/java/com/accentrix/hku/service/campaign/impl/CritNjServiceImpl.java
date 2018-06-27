package com.accentrix.hku.service.campaign.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.campaign.CritNj;
import com.accentrix.hku.repository.campaign.CritNjRepository;
import com.accentrix.hku.service.campaign.CritNjService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.campaign.CritNjVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年4月9日 下午2:31:16
 * @version 1.0
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("critNj")
public class CritNjServiceImpl implements CritNjService {

    @Autowired
    private CritNjRepository critNjRepository;

    @Override
    public CritNjVo get(String id) {
        CritNj critNj = critNjRepository.findOne(id);
        return toVo(critNj);
    }

    @Transactional
    @Override
    public CritNjVo save(CritNjVo vo) {
        CritNj critNj = toCritNj(vo);
        critNj = critNjRepository.save(critNj);
        vo.setId(critNj.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<CritNjVo> save(List<CritNjVo> vos) {
        List<CritNjVo> critNjVos = new ArrayList<CritNjVo>();
        for (CritNjVo critNjVo : vos) {
            CritNj critNj = toCritNj(critNjVo);
            critNj = critNjRepository.save(critNj);
            critNjVo.setId(critNj.getId());
            critNjVos.add(critNjVo);
        }
        return critNjVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        critNjRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(CritNjVo vo) {
        CritNj critNj = toCritNj(vo);
        critNjRepository.delete(critNj);
    }

    @Override
    public List<CritNjVo> findList() {
        List<CritNj> list = critNjRepository.findAll();
        List<CritNjVo> vos = new ArrayList<CritNjVo>();
        for (CritNj critNj : list) {
            vos.add(toVo(critNj));
        }
        return vos;
    }

    private CritNj toCritNj(CritNjVo vo) {
        CritNj critNj = new CritNj();
        critNj.setId(vo.getId());
        critNj.setName(vo.getName());
        critNj.setIsNotStudying(vo.getIsNotStudying());
        critNj.setCountryId(StringUtils.isNotBlank(vo.getCountryId()) ? vo.getCountryId() : null);
        critNj.setProvinceId(StringUtils.isNotBlank(vo.getProvinceId()) ? vo.getProvinceId() : null);
        critNj.setCityId(StringUtils.isNotBlank(vo.getCityId()) ? vo.getCityId() : null);
        critNj.setInstitutionId(StringUtils.isNotBlank(vo.getInstitutionId()) ? vo.getInstitutionId() : null);
        critNj.setCpgnId(StringUtils.isNotBlank(vo.getCpgnId()) ? vo.getCpgnId() : null);
        critNj.setSelectedForShortlist(vo.getSelectedForShortlist());
        return critNj;
    }

    private CritNjVo toVo(CritNj critNj) {
        CritNjVo vo = new CritNjVo();
        vo.setId(critNj.getId());
        vo.setIsNotStudying(critNj.getIsNotStudying());
        vo.setCountryId(critNj.getCountryId());
        vo.setProvinceId(critNj.getProvinceId());
        vo.setCityId(critNj.getCityId());
        vo.setInstitutionId(critNj.getInstitutionId());
        vo.setCpgnId(critNj.getCpgnId());
        vo.setSelectedForShortlist(critNj.getSelectedForShortlist());
        vo.setName(critNj.getName());
        return vo;
    }

    @Override
    public List<CritNjVo> findByCpgnId(String cpgnId) {
        List<CritNj> list = critNjRepository.findByCpgnId(cpgnId);
        List<CritNjVo> vos = new ArrayList<CritNjVo>();
        for (CritNj critNj : list) {
            vos.add(toVo(critNj));
        }
        return vos;
    }
}
