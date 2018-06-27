package com.accentrix.hku.service.app.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.app.AcadQualNursingPrq;
import com.accentrix.hku.repository.app.AcadQualNursingPrqRepository;
import com.accentrix.hku.service.app.AcadQualNursingPrqService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.app.AcadQualNursingPrqVo;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年4月10日 下午4:18:49
 */
@Service
@Transactional(readOnly = true)
@Timer
@Path("acadQualNursingPrq")
public class AcadQualNursingPrqServiceImpl implements AcadQualNursingPrqService {

    @Autowired
    private AcadQualNursingPrqRepository acadQualNursingPrqRepository;

    @Override
    public AcadQualNursingPrqVo get(String id) {
        AcadQualNursingPrq acadQualNursingPrq = acadQualNursingPrqRepository.findOne(id);
        return toVo(acadQualNursingPrq);
    }

    @Transactional
    @Override
    public AcadQualNursingPrqVo save(AcadQualNursingPrqVo vo) {
        AcadQualNursingPrq acadQualNursingPrq = toAcadQualNursingPrq(vo);
        acadQualNursingPrq = acadQualNursingPrqRepository.save(acadQualNursingPrq);
        vo.setId(acadQualNursingPrq.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<AcadQualNursingPrqVo> save(List<AcadQualNursingPrqVo> vos) {
        List<AcadQualNursingPrqVo> acadQualNursingPrqVos = new ArrayList<AcadQualNursingPrqVo>();
        for (AcadQualNursingPrqVo acadQualNursingPrqVo : vos) {
            AcadQualNursingPrq acadQualNursingPrq = toAcadQualNursingPrq(acadQualNursingPrqVo);
            acadQualNursingPrq = acadQualNursingPrqRepository.save(acadQualNursingPrq);
            acadQualNursingPrqVo.setId(acadQualNursingPrq.getId());
            acadQualNursingPrqVos.add(acadQualNursingPrqVo);
        }
        return acadQualNursingPrqVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        acadQualNursingPrqRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(AcadQualNursingPrqVo vo) {
        acadQualNursingPrqRepository.delete(toAcadQualNursingPrq(vo));
    }

    @Override
    public List<AcadQualNursingPrqVo> findList() {
        List<AcadQualNursingPrq> acadQualNursingPrqs = acadQualNursingPrqRepository.findAll();
        List<AcadQualNursingPrqVo> vos = new ArrayList<AcadQualNursingPrqVo>();
        for (AcadQualNursingPrq acadQualNursingPrq : acadQualNursingPrqs) {
            AcadQualNursingPrqVo vo = toVo(acadQualNursingPrq);
            vos.add(vo);
        }
        return vos;
    }

    @Override
    public List<AcadQualNursingPrqVo> getByAppAcadQualNursingId(String appAcadQualNursingId) {
        List<AcadQualNursingPrq> acadQualNursingPrqs = acadQualNursingPrqRepository
                .getByAppAcadQualNursingId(appAcadQualNursingId);
        List<AcadQualNursingPrqVo> vos = new ArrayList<AcadQualNursingPrqVo>();
        for (AcadQualNursingPrq acadQualNursingPrq : acadQualNursingPrqs) {
            AcadQualNursingPrqVo vo = toVo(acadQualNursingPrq);
            vos.add(vo);
        }
        return vos;
    }

    private AcadQualNursingPrqVo toVo(AcadQualNursingPrq acadQualNursingPrq) {
        AcadQualNursingPrqVo vo = new AcadQualNursingPrqVo();
        vo.setId(acadQualNursingPrq.getId());
        vo.setTypeOfMem(acadQualNursingPrq.getTypeOfMem());
        vo.setDateFrom(acadQualNursingPrq.getDateFrom());
        vo.setDateTo(acadQualNursingPrq.getDateTo());
        vo.setAbbre(acadQualNursingPrq.getAbbre());
        vo.setDateOfAward(acadQualNursingPrq.getDateOfAward());
        vo.setAwardInstitution(acadQualNursingPrq.getAwardInstitution());
        vo.setAppAcadQualNursingId(acadQualNursingPrq.getAppAcadQualNursingId());
        return vo;
    }

    private AcadQualNursingPrq toAcadQualNursingPrq(AcadQualNursingPrqVo vo) {
        AcadQualNursingPrq acadQualNursingPrq = new AcadQualNursingPrq();
        acadQualNursingPrq.setId(vo.getId());
        acadQualNursingPrq.setTypeOfMem(vo.getTypeOfMem());
        acadQualNursingPrq.setDateFrom(vo.getDateFrom());
        acadQualNursingPrq.setDateTo(vo.getDateTo());
        acadQualNursingPrq.setAbbre(vo.getAbbre());
        acadQualNursingPrq.setDateOfAward(vo.getDateOfAward());
        acadQualNursingPrq.setAwardInstitution(vo.getAwardInstitution());
        acadQualNursingPrq.setAppAcadQualNursingId(vo.getAppAcadQualNursingId());
        return acadQualNursingPrq;
    }
}
