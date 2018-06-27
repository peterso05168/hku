package com.accentrix.hku.service.campaign.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.campaign.CritNjToProg;
import com.accentrix.hku.repository.campaign.CritNjToProgRepository;
import com.accentrix.hku.service.campaign.CritNjToProgService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.campaign.CritNjToProgVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年4月9日 下午2:31:34
 * @version 1.0
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("critNjToProg")
public class CritNjToProgServiceImpl implements CritNjToProgService {

    @Autowired
    private CritNjToProgRepository critNjToProgRepository;

    @Override
    public CritNjToProgVo get(String id) {
        CritNjToProg critNjToProg = critNjToProgRepository.findOne(id);
        return toVo(critNjToProg);
    }

    @Transactional
    @Override
    public CritNjToProgVo save(CritNjToProgVo vo) {
        CritNjToProg critNjToProg = toCritNjToProg(vo);
        critNjToProg = critNjToProgRepository.save(critNjToProg);
        vo.setId(critNjToProg.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<CritNjToProgVo> save(List<CritNjToProgVo> vos) {
        List<CritNjToProgVo> critNjToProgVos = new ArrayList<CritNjToProgVo>();
        for (CritNjToProgVo critNjToProgVo : vos) {
            CritNjToProg critNjToProg = toCritNjToProg(critNjToProgVo);
            critNjToProg = critNjToProgRepository.save(critNjToProg);
            critNjToProgVo.setId(critNjToProg.getId());
        }
        return critNjToProgVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        critNjToProgRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(CritNjToProgVo vo) {
        CritNjToProg critNjToProg = toCritNjToProg(vo);
        critNjToProgRepository.delete(critNjToProg);
    }

    @Override
    public List<CritNjToProgVo> findList() {
        List<CritNjToProg> list = critNjToProgRepository.findAll();
        List<CritNjToProgVo> vos = new ArrayList<CritNjToProgVo>();
        for (CritNjToProg critNjToProg : list) {
            vos.add(toVo(critNjToProg));
        }
        return vos;
    }

    private CritNjToProgVo toVo(CritNjToProg critNjToProg) {
        CritNjToProgVo vo = new CritNjToProgVo();
        vo.setId(critNjToProg.getId());
        vo.setAdmFormProgId(critNjToProg.getAdmFormProgId());
        vo.setCpgnCritNjId(critNjToProg.getCpgnCritNjId());
        return vo;
    }

    private CritNjToProg toCritNjToProg(CritNjToProgVo vo) {
        CritNjToProg critNjToProg = new CritNjToProg();
        critNjToProg.setId(vo.getId());
        critNjToProg.setAdmFormProgId(vo.getAdmFormProgId());
        critNjToProg.setCpgnCritNjId(vo.getCpgnCritNjId());
        return critNjToProg;
    }

    @Override
    public List<String> findByCritNjId(String critNjId) {
        List<String> list = critNjToProgRepository.findByCritNjId(critNjId);
        if (list != null && list.size() > 0) {
            return list;
        }
        return new ArrayList<String>();
    }

    @Override
    public List<CritNjToProgVo> findByCritNjIdToList(String critNjId) {
        List<CritNjToProg> list = critNjToProgRepository.findByCritNjIdToList(critNjId);
        List<CritNjToProgVo> vos = new ArrayList<CritNjToProgVo>();
        for (CritNjToProg critNjToProg : list) {
            vos.add(toVo(critNjToProg));
        }
        return vos;
    }
}
