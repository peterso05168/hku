package com.accentrix.hku.service.campaign.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.campaign.CritChina;
import com.accentrix.hku.repository.campaign.CritChinaRepository;
import com.accentrix.hku.service.campaign.CritChinaService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.campaign.CritChinaVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年4月9日 下午2:30:30 
 * @version 1.0 
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("critChina")
public class CritChinaServiceImpl implements CritChinaService {

    @Autowired
    private CritChinaRepository critChinaRepository;

    @Override
    public CritChinaVo get(String id) {
        CritChina critChina = critChinaRepository.findOne(id);
        return toVo(critChina);
    }

    @Transactional
    @Override
    public CritChinaVo save(CritChinaVo vo) {
        CritChina critChina = toCritChina(vo);
        critChina = critChinaRepository.save(critChina);
        vo.setId(critChina.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<CritChinaVo> save(List<CritChinaVo> vos) {
        List<CritChinaVo> critChinaVos = new ArrayList<CritChinaVo>();
        for (CritChinaVo critChinaVo : vos) {
            CritChina critChina = toCritChina(critChinaVo);
            critChina = critChinaRepository.save(critChina);
            critChinaVo.setId(critChina.getId());
            critChinaVos.add(critChinaVo);
        }
        return critChinaVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        critChinaRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(CritChinaVo vo) {
        CritChina critChina = toCritChina(vo);
        critChinaRepository.delete(critChina);
    }

    @Override
    public List<CritChinaVo> findList() {
        List<CritChina> list = critChinaRepository.findAll();
        List<CritChinaVo> vos = new ArrayList<CritChinaVo>();
        for (CritChina critChina : list) {
            vos.add(toVo(critChina));
        }
        return vos;
    }

    private CritChinaVo toVo(CritChina critChina) {
        CritChinaVo vo = new CritChinaVo();
        vo.setId(critChina.getId());
        vo.setName(critChina.getName());
        vo.setCpgnId(critChina.getCpgnId());
        vo.setSelectedForShortlist(critChina.getSelectedForShortlist());
        return vo;
    }

    private CritChina toCritChina(CritChinaVo vo) {
        CritChina critChina = new CritChina();
        critChina.setId(vo.getId());
        critChina.setName(vo.getName());
        critChina.setCpgnId(vo.getCpgnId());
        critChina.setSelectedForShortlist(vo.getSelectedForShortlist());
        return critChina;
    }

    @Override
    public List<CritChinaVo> findByCpgnId(String cpgnId) {
        List<CritChina> list = critChinaRepository.findByCpgnId(cpgnId);
        List<CritChinaVo> vos = new ArrayList<CritChinaVo>();
        for (CritChina critChina : list) {
            vos.add(toVo(critChina));
        }
        return vos;
    }
}
