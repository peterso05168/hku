package com.accentrix.hku.service.campaign.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.campaign.CritChinaDtl;
import com.accentrix.hku.repository.campaign.CritChinaDtlRepository;
import com.accentrix.hku.service.campaign.CritChinaDtlService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.campaign.CritChinaDtlVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年4月9日 下午2:30:54 
 * @version 1.0 
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("critChinaDtl")
public class CritChinaDtlServiceImpl implements CritChinaDtlService {

    @Autowired
    private CritChinaDtlRepository critChinaDtlRepository;

    @Override
    public CritChinaDtlVo get(String id) {
        CritChinaDtl critChinaDtl = critChinaDtlRepository.findOne(id);
        return toVo(critChinaDtl);
    }

    @Transactional
    @Override
    public CritChinaDtlVo save(CritChinaDtlVo vo) {
        CritChinaDtl critChinaDtl = toCritChinaDtl(vo);
        critChinaDtl = critChinaDtlRepository.save(critChinaDtl);
        vo.setId(critChinaDtl.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<CritChinaDtlVo> save(List<CritChinaDtlVo> vos) {
        List<CritChinaDtlVo> critChinaDtlVos = new ArrayList<CritChinaDtlVo>();
        for (CritChinaDtlVo critChinaDtlVo : vos) {
            CritChinaDtl critChinaDtl = toCritChinaDtl(critChinaDtlVo);
            critChinaDtl = critChinaDtlRepository.save(critChinaDtl);
            critChinaDtlVo.setId(critChinaDtl.getId());
            critChinaDtlVos.add(critChinaDtlVo);
        }
        return critChinaDtlVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        critChinaDtlRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(CritChinaDtlVo vo) {
        CritChinaDtl critChinaDtl = toCritChinaDtl(vo);
        critChinaDtlRepository.delete(critChinaDtl);
    }

    @Override
    public List<CritChinaDtlVo> findList() {
        List<CritChinaDtl> list = critChinaDtlRepository.findAll();
        List<CritChinaDtlVo> vos = new ArrayList<CritChinaDtlVo>();
        for (CritChinaDtl critChinaDtl : list) {
            vos.add(toVo(critChinaDtl));
        }
        return vos;
    }

    private CritChinaDtlVo toVo(CritChinaDtl critChinaDtl) {
        CritChinaDtlVo vo = new CritChinaDtlVo();
        vo.setId(critChinaDtl.getId());
        vo.setProvinceId(critChinaDtl.getProvinceId());
        vo.setExamTypeId(critChinaDtl.getExamTypeId());
        vo.setStream(critChinaDtl.getStream());
        vo.setCutoffTotal(critChinaDtl.getCutoffTotal());
        vo.setCutoffEnglish(critChinaDtl.getCutoffEnglish());
        vo.setNoEligAppl(critChinaDtl.getNoEligAppl());
        vo.setPctEligAppl(critChinaDtl.getPctEligAppl());
        vo.setCpgnChinaId(critChinaDtl.getCpgnChinaId());
        return vo;
    }

    private CritChinaDtl toCritChinaDtl(CritChinaDtlVo vo) {
        CritChinaDtl critChinaDtl = new CritChinaDtl();
        critChinaDtl.setId(vo.getId());
        critChinaDtl.setProvinceId(vo.getProvinceId());
        critChinaDtl.setExamTypeId(vo.getExamTypeId());
        critChinaDtl.setStream(vo.getStream());
        critChinaDtl.setCutoffTotal(vo.getCutoffTotal());
        critChinaDtl.setCutoffEnglish(vo.getCutoffEnglish());
        critChinaDtl.setNoEligAppl(vo.getNoEligAppl());
        critChinaDtl.setPctEligAppl(vo.getPctEligAppl());
        critChinaDtl.setCpgnChinaId(vo.getCpgnChinaId());
        return critChinaDtl;
    }

    @Override
    public List<CritChinaDtlVo> findByCritChinaId(String cpgnChinaId) {
        List<CritChinaDtl> list = critChinaDtlRepository.findByCritChinaId(cpgnChinaId);
        List<CritChinaDtlVo> vos = new ArrayList<CritChinaDtlVo>();
        for (CritChinaDtl critChinaDtl : list) {
            vos.add(toVo(critChinaDtl));
        }
        return vos;
    }
}
