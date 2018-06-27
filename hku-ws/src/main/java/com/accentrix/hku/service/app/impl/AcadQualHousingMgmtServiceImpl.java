package com.accentrix.hku.service.app.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.app.AcadQualHousingMgmt;
import com.accentrix.hku.repository.app.AcadQualHousingMgmtRepository;
import com.accentrix.hku.service.app.AcadQualHousingMgmtService;
import com.accentrix.hku.vo.app.AcadQualHousingMgmtVo;
import com.accentrix.hku.timer.annotation.Timer;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年2月9日 下午3:12:54
 * @version 1.0
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("acadQualHousingMgmt")
public class AcadQualHousingMgmtServiceImpl implements AcadQualHousingMgmtService {

    @Autowired
    private AcadQualHousingMgmtRepository acadQualHousingMgmtRepository;

    @Override
    public AcadQualHousingMgmtVo get(String id) {
        AcadQualHousingMgmt acadQualHousingMgmt = acadQualHousingMgmtRepository.findOne(id);
        return toVo(acadQualHousingMgmt);
    }

    @Transactional
    @Override
    public AcadQualHousingMgmtVo save(AcadQualHousingMgmtVo vo) {
        AcadQualHousingMgmt acadQualHousingMgmt = toAcadQualHousingMgmt(vo);
        acadQualHousingMgmt = acadQualHousingMgmtRepository.save(acadQualHousingMgmt);
        vo.setId(acadQualHousingMgmt.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<AcadQualHousingMgmtVo> save(List<AcadQualHousingMgmtVo> vos) {
        List<AcadQualHousingMgmtVo> acadQualHousingMgmtVos = new ArrayList<AcadQualHousingMgmtVo>();
        for (AcadQualHousingMgmtVo acadQualHousingMgmtVo : vos) {
            AcadQualHousingMgmt acadQualHousingMgmt = toAcadQualHousingMgmt(acadQualHousingMgmtVo);
            acadQualHousingMgmt = acadQualHousingMgmtRepository.save(acadQualHousingMgmt);
            acadQualHousingMgmtVo.setId(acadQualHousingMgmt.getId());
            acadQualHousingMgmtVos.add(acadQualHousingMgmtVo);
        }
        return acadQualHousingMgmtVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        acadQualHousingMgmtRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(AcadQualHousingMgmtVo vo) {
        acadQualHousingMgmtRepository.delete(toAcadQualHousingMgmt(vo));
    }

    @Override
    public List<AcadQualHousingMgmtVo> findList() {
        List<AcadQualHousingMgmt> acadQualHousingMgmts = acadQualHousingMgmtRepository.findAll();
        List<AcadQualHousingMgmtVo> vos = new ArrayList<AcadQualHousingMgmtVo>();
        for (AcadQualHousingMgmt acadQualHousingMgmt : acadQualHousingMgmts) {
            vos.add(toVo(acadQualHousingMgmt));
        }
        return vos;
    }

    private AcadQualHousingMgmtVo toVo(AcadQualHousingMgmt acadQualHousingMgmt) {
        AcadQualHousingMgmtVo vo = new AcadQualHousingMgmtVo();
        if (acadQualHousingMgmt != null) {
            vo.setId(acadQualHousingMgmt.getId());
            vo.setPeriodFrom(acadQualHousingMgmt.getPeriodFrom());
            vo.setPeriodTo(acadQualHousingMgmt.getPeriodTo());
            vo.setHmAwardDate(acadQualHousingMgmt.getHmAwardDate());
            vo.setPositionHeld(acadQualHousingMgmt.getPositionHeld());
            vo.setStartingDate(acadQualHousingMgmt.getStartingDate());
            vo.setNameAndAddr(acadQualHousingMgmt.getNameAndAddr());
        }
        return vo;
    }

    private AcadQualHousingMgmt toAcadQualHousingMgmt(AcadQualHousingMgmtVo vo) {
        AcadQualHousingMgmt acadQualHousingMgmt = new AcadQualHousingMgmt();
        acadQualHousingMgmt.setId(vo.getId());
        acadQualHousingMgmt.setPeriodFrom(vo.getPeriodFrom());
        acadQualHousingMgmt.setPeriodTo(vo.getPeriodTo());
        acadQualHousingMgmt.setHmAwardDate(vo.getHmAwardDate());
        acadQualHousingMgmt.setPositionHeld(vo.getPositionHeld());
        acadQualHousingMgmt.setStartingDate(vo.getStartingDate());
        acadQualHousingMgmt.setNameAndAddr(vo.getNameAndAddr());
        return acadQualHousingMgmt;
    }

}
