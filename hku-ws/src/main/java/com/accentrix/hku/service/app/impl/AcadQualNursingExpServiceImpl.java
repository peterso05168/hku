package com.accentrix.hku.service.app.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.app.AcadQualNursingExp;
import com.accentrix.hku.repository.app.AcadQualNursingExpRepository;
import com.accentrix.hku.service.app.AcadQualNursingExpService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.app.AcadQualNursingExpVo;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年4月10日 下午3:22:43
 */
@Service
@Transactional(readOnly = true)
@Timer
@Path("acadQualNursingExp")
public class AcadQualNursingExpServiceImpl implements AcadQualNursingExpService {

    @Autowired
    private AcadQualNursingExpRepository acadQualNursingExpRepository;

    @Override
    public AcadQualNursingExpVo get(String id) {
        AcadQualNursingExp acadQualNursingExp = acadQualNursingExpRepository.findOne(id);
        return toVo(acadQualNursingExp);
    }

    @Transactional
    @Override
    public AcadQualNursingExpVo save(AcadQualNursingExpVo vo) {
        AcadQualNursingExp acadQualNursingExp = toAcadQualNursingExp(vo);
        acadQualNursingExp = acadQualNursingExpRepository.save(acadQualNursingExp);
        vo.setId(acadQualNursingExp.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<AcadQualNursingExpVo> save(List<AcadQualNursingExpVo> vos) {
        List<AcadQualNursingExpVo> acadQualNursingExpVos = new ArrayList<AcadQualNursingExpVo>();
        for (AcadQualNursingExpVo acadQualNursingExpVo : vos) {
            AcadQualNursingExp acadQualNursingExp = toAcadQualNursingExp(acadQualNursingExpVo);
            acadQualNursingExp = acadQualNursingExpRepository.save(acadQualNursingExp);
            acadQualNursingExpVo.setId(acadQualNursingExp.getId());
            acadQualNursingExpVos.add(acadQualNursingExpVo);
        }
        return acadQualNursingExpVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        acadQualNursingExpRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(AcadQualNursingExpVo vo) {
        acadQualNursingExpRepository.delete(toAcadQualNursingExp(vo));
    }

    @Override
    public List<AcadQualNursingExpVo> findList() {
        List<AcadQualNursingExp> acadQualNursingExps = acadQualNursingExpRepository.findAll();
        List<AcadQualNursingExpVo> vos = new ArrayList<AcadQualNursingExpVo>();
        for (AcadQualNursingExp acadQualNursingExp : acadQualNursingExps) {
            AcadQualNursingExpVo vo = toVo(acadQualNursingExp);
            vos.add(vo);
        }
        return vos;
    }

    @Override
    public List<AcadQualNursingExpVo> getByAppAcadQualNursingId(String appAcadQualNursingId) {
        List<AcadQualNursingExp> acadQualNursingExps = acadQualNursingExpRepository
                .getByAppAcadQualNursingId(appAcadQualNursingId);
        List<AcadQualNursingExpVo> vos = new ArrayList<AcadQualNursingExpVo>();
        for (AcadQualNursingExp acadQualNursingExp : acadQualNursingExps) {
            AcadQualNursingExpVo vo = toVo(acadQualNursingExp);
            vos.add(vo);
        }
        return vos;
    }

    @Override
    public List<AcadQualNursingExpVo> getByAppAcadQualNursingIdAndType(String appAcadQualNursingId, String type) {
        List<AcadQualNursingExp> acadQualNursingExps = acadQualNursingExpRepository
                .getByAppAcadQualNursingIdAndType(appAcadQualNursingId, type);
        List<AcadQualNursingExpVo> vos = new ArrayList<AcadQualNursingExpVo>();
        for (AcadQualNursingExp acadQualNursingExp : acadQualNursingExps) {
            AcadQualNursingExpVo vo = toVo(acadQualNursingExp);
            vos.add(vo);
        }
        return vos;
    }

    private AcadQualNursingExpVo toVo(AcadQualNursingExp acadQualNursingExp) {
        AcadQualNursingExpVo vo = new AcadQualNursingExpVo();
        vo.setId(acadQualNursingExp.getId());
        vo.setDateFrom(acadQualNursingExp.getDateFrom());
        vo.setDateTo(acadQualNursingExp.getDateTo());
        vo.setMode(acadQualNursingExp.getMode());
        vo.setNameOfInstitute(acadQualNursingExp.getNameOfInstitute());
        vo.setPositionHeld(acadQualNursingExp.getPositionHeld());
        vo.setAowNod(acadQualNursingExp.getAowNod());
        vo.setExpType(acadQualNursingExp.getExpType());
        vo.setAppAcadQualNursingId(acadQualNursingExp.getAppAcadQualNursingId());
        return vo;
    }

    private AcadQualNursingExp toAcadQualNursingExp(AcadQualNursingExpVo vo) {
        AcadQualNursingExp acadQualNursingExp = new AcadQualNursingExp();
        acadQualNursingExp.setId(vo.getId());
        acadQualNursingExp.setDateFrom(vo.getDateFrom());
        acadQualNursingExp.setDateTo(vo.getDateTo());
        acadQualNursingExp.setMode(vo.getMode());
        acadQualNursingExp.setNameOfInstitute(vo.getNameOfInstitute());
        acadQualNursingExp.setPositionHeld(vo.getPositionHeld());
        acadQualNursingExp.setAowNod(vo.getAowNod());
        acadQualNursingExp.setExpType(vo.getExpType());
        acadQualNursingExp.setAppAcadQualNursingId(vo.getAppAcadQualNursingId());
        return acadQualNursingExp;
    }
}
