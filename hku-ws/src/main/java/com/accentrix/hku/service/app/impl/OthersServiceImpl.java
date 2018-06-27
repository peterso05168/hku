package com.accentrix.hku.service.app.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.app.Others;
import com.accentrix.hku.repository.app.OthersRepository;
import com.accentrix.hku.service.app.OthersService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.app.OthersVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年3月6日 下午5:14:18
 * @version 1.0
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("others")
public class OthersServiceImpl implements OthersService {

    @Autowired
    private OthersRepository othersRepository;

    @Override
    public OthersVo get(String id) {
        Others others = othersRepository.findOne(id);
        return toVo(others);
    }

    @Transactional
    @Override
    public OthersVo save(OthersVo vo) {
        Others others = toOthers(vo);
        others = othersRepository.save(others);
        vo.setId(others.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<OthersVo> save(List<OthersVo> vos) {
        List<OthersVo> othersVos = new ArrayList<OthersVo>();
        for (OthersVo othersVo : vos) {
            Others others = toOthers(othersVo);
            others = othersRepository.save(others);
            othersVo.setId(others.getId());
            othersVos.add(othersVo);
        }
        return othersVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        othersRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(OthersVo vo) {
        Others others = toOthers(vo);
        othersRepository.delete(others);
    }

    @Override
    public List<OthersVo> findList() {
        List<Others> list = othersRepository.findAll();
        List<OthersVo> vos = new ArrayList<OthersVo>();
        for (Others others : list) {
            vos.add(toVo(others));
        }
        return vos;
    }

    private OthersVo toVo(Others others) {
        OthersVo vo = new OthersVo();
        vo.setId(others.getId());
        vo.setDisabilityTypeCd(others.getDisabilityTypeCd());
        vo.setDegOfImpairmnt(others.getDegOfImpairmnt());
        vo.setDescription(others.getDescription());
        vo.setUniNo(others.getUniNo());
        vo.setDeReg(others.getDeReg());
        vo.setDeRegProg(others.getDeRegProg());
        vo.setDeRegYear(others.getDeRegYear());
        vo.setDiscontinued(others.getDiscontinued());
        vo.setDisconProg(others.getDisconProg());
        vo.setDisconYear(others.getDisconYear());
        vo.setDisconUniNo(others.getDisconUniNo());
        vo.setNotSuccessAppSubmit(others.getNotSuccessAppSubmit());
        vo.setNotSuccessAppYear(others.getNotSuccessAppYear());
        vo.setNotSuccessAppUniNo(others.getNotSuccessAppUniNo());
        return vo;
    }

    private Others toOthers(OthersVo vo) {
        Others others = new Others();
        others.setId(vo.getId());
        others.setDisabilityTypeCd(vo.getDisabilityTypeCd());
        others.setDegOfImpairmnt(vo.getDegOfImpairmnt());
        others.setDescription(vo.getDescription());
        others.setUniNo(vo.getUniNo());
        others.setDeReg(vo.getDeReg());
        others.setDeRegProg(vo.getDeRegProg());
        others.setDeRegYear(vo.getDeRegYear());
        others.setDiscontinued(vo.getDiscontinued());
        others.setDisconProg(vo.getDisconProg());
        others.setDisconYear(vo.getDisconYear());
        others.setDisconUniNo(vo.getDisconUniNo());
        others.setNotSuccessAppSubmit(vo.getNotSuccessAppSubmit());
        others.setNotSuccessAppYear(vo.getNotSuccessAppYear());
        others.setNotSuccessAppUniNo(vo.getNotSuccessAppUniNo());
        return others;
    }
}
