package com.accentrix.hku.service.app.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.app.AcadQualNursingNr;
import com.accentrix.hku.repository.app.AcadQualNursingNrRepository;
import com.accentrix.hku.service.app.AcadQualNursingNrService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.app.AcadQualNursingNrVo;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年4月10日 下午3:53:58
 */
@Service
@Transactional(readOnly = true)
@Timer
@Path("acadQualNursingNr")
public class AcadQualNursingNrServiceImpl implements AcadQualNursingNrService {

    @Autowired
    private AcadQualNursingNrRepository acadQualNursingNrRepository;

    @Override
    public AcadQualNursingNrVo get(String id) {
        AcadQualNursingNr acadQualNursingNr = acadQualNursingNrRepository.findOne(id);
        return toVo(acadQualNursingNr);
    }

    @Transactional
    @Override
    public AcadQualNursingNrVo save(AcadQualNursingNrVo vo) {
        AcadQualNursingNr acadQualNursingNr = toAcadQualNursingNr(vo);
        acadQualNursingNr = acadQualNursingNrRepository.save(acadQualNursingNr);
        vo.setId(acadQualNursingNr.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<AcadQualNursingNrVo> save(List<AcadQualNursingNrVo> vos) {
        List<AcadQualNursingNrVo> acadQualNursingNrVos = new ArrayList<AcadQualNursingNrVo>();
        for (AcadQualNursingNrVo acadQualNursingNrVo : vos) {
            AcadQualNursingNr acadQualNursingNr = toAcadQualNursingNr(acadQualNursingNrVo);
            acadQualNursingNr = acadQualNursingNrRepository.save(acadQualNursingNr);
            acadQualNursingNrVo.setId(acadQualNursingNr.getId());
            acadQualNursingNrVos.add(acadQualNursingNrVo);
        }
        return acadQualNursingNrVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        acadQualNursingNrRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(AcadQualNursingNrVo vo) {
        acadQualNursingNrRepository.delete(toAcadQualNursingNr(vo));
    }

    @Override
    public List<AcadQualNursingNrVo> findList() {
        List<AcadQualNursingNr> acadQualNursingNrs = acadQualNursingNrRepository.findAll();
        List<AcadQualNursingNrVo> vos = new ArrayList<AcadQualNursingNrVo>();
        for (AcadQualNursingNr acadQualNursingNr : acadQualNursingNrs) {
            AcadQualNursingNrVo vo = toVo(acadQualNursingNr);
            vos.add(vo);
        }
        return vos;
    }

    @Override
    public List<AcadQualNursingNrVo> getByAppAcadQualNursingId(String appAcadQualNursingId) {
        List<AcadQualNursingNr> acadQualNursingNrs = acadQualNursingNrRepository
                .getByAppAcadQualNursingId(appAcadQualNursingId);
        List<AcadQualNursingNrVo> vos = new ArrayList<AcadQualNursingNrVo>();
        for (AcadQualNursingNr acadQualNursingNr : acadQualNursingNrs) {
            AcadQualNursingNrVo vo = toVo(acadQualNursingNr);
            vos.add(vo);
        }
        return vos;
    }

    private AcadQualNursingNrVo toVo(AcadQualNursingNr acadQualNursingNr) {
        AcadQualNursingNrVo vo = new AcadQualNursingNrVo();
        vo.setId(acadQualNursingNr.getId());
        vo.setNursingSchool(acadQualNursingNr.getNursingSchool());
        vo.setDateFrom(acadQualNursingNr.getDateFrom());
        vo.setDateTo(acadQualNursingNr.getDateTo());
        vo.setGenOrPsy(acadQualNursingNr.getGenOrPsy());
        vo.setDateOfRegAndIa(acadQualNursingNr.getDateOfRegAndIa());
        vo.setRegStatus(acadQualNursingNr.getRegStatus());
        vo.setAppAcadQualNursingId(acadQualNursingNr.getAppAcadQualNursingId());
        return vo;
    }

    private AcadQualNursingNr toAcadQualNursingNr(AcadQualNursingNrVo vo) {
        AcadQualNursingNr acadQualNursingNr = new AcadQualNursingNr();
        acadQualNursingNr.setId(vo.getId());
        acadQualNursingNr.setNursingSchool(vo.getNursingSchool());
        acadQualNursingNr.setDateFrom(vo.getDateFrom());
        acadQualNursingNr.setDateTo(vo.getDateTo());
        acadQualNursingNr.setGenOrPsy(vo.getGenOrPsy());
        acadQualNursingNr.setDateOfRegAndIa(vo.getDateOfRegAndIa());
        acadQualNursingNr.setRegStatus(vo.getRegStatus());
        acadQualNursingNr.setAppAcadQualNursingId(vo.getAppAcadQualNursingId());
        return acadQualNursingNr;
    }

}
