package com.accentrix.hku.service.app.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.app.TransCredits;
import com.accentrix.hku.repository.app.TransCreditsRepository;
import com.accentrix.hku.service.app.TransCreditsService;
import com.accentrix.hku.vo.app.TransCreditsVo;
import com.accentrix.hku.timer.annotation.Timer;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年2月9日 下午3:13:43
 * @version 1.0
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("transCredits")
public class TransCreditsServiceImpl implements TransCreditsService {

    @Autowired
    private TransCreditsRepository transCreditsRepository;

    @Override
    public TransCreditsVo get(String id) {
        TransCredits transCredits = transCreditsRepository.findOne(id);
        return toVo(transCredits);
    }

    @Transactional
    @Override
    public TransCreditsVo save(TransCreditsVo vo) {
        TransCredits transCredits = toTransCredits(vo);
        transCredits = transCreditsRepository.save(transCredits);
        vo.setId(transCredits.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<TransCreditsVo> save(List<TransCreditsVo> vos) {
        List<TransCreditsVo> transCreditsVos = new ArrayList<TransCreditsVo>();
        for (TransCreditsVo transCreditsVo : vos) {
            TransCredits transCredits = toTransCredits(transCreditsVo);
            transCredits = transCreditsRepository.save(transCredits);
            transCreditsVo.setId(transCredits.getId());
            transCreditsVos.add(transCreditsVo);
        }
        return transCreditsVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        transCreditsRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(TransCreditsVo vo) {
        transCreditsRepository.delete(toTransCredits(vo));
    }

    @Override
    public List<TransCreditsVo> findList() {
        List<TransCredits> transCreditsList = transCreditsRepository.findAll();
        List<TransCreditsVo> vos = new ArrayList<TransCreditsVo>();
        for (TransCredits transCredits : transCreditsList) {
            vos.add(toVo(transCredits));
        }
        return vos;
    }

    @Override
    public TransCreditsVo getByApplicationId(String applicationId) {
        TransCredits transCredits = transCreditsRepository.getByApplicationId(applicationId);
        return toVo(transCredits);
    }

    private TransCreditsVo toVo(TransCredits transCredits) {
        TransCreditsVo vo = new TransCreditsVo();
        if (transCredits != null) {
            vo.setId(transCredits.getId());
            vo.setApplicationId(transCredits.getApplicationId());
            vo.setCurriculum(transCredits.getCurriculum());
            vo.setIsApplyDirEntry(transCredits.getIsApplyDirEntry());
            vo.setIsApplyTransCredits(transCredits.getIsApplyTransCredits());
            vo.setYearOfStudy(transCredits.getYearOfStudy());
        }
        return vo;
    }

    private TransCredits toTransCredits(TransCreditsVo vo) {
        TransCredits transCredits = new TransCredits();
        transCredits.setId(vo.getId());
        transCredits.setApplicationId(vo.getApplicationId());
        transCredits.setCurriculum(vo.getCurriculum());
        transCredits.setIsApplyDirEntry(vo.getIsApplyDirEntry());
        transCredits.setIsApplyTransCredits(vo.getIsApplyTransCredits());
        transCredits.setYearOfStudy(vo.getYearOfStudy());
        return transCredits;
    }
}
