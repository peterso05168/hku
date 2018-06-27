package com.accentrix.hku.service.app.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.app.AcadQualHousingMgmtPq;
import com.accentrix.hku.repository.app.AcadQualHousingMgmtPqRepository;
import com.accentrix.hku.service.app.AcadQualHousingMgmtPqService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.app.AcadQualHousingMgmtPqVo;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年4月10日 下午2:28:14
 */
@Service
@Transactional(readOnly = true)
@Timer
@Path("acadQualHousingMgmtPq")
public class AcadQualHousingMgmtPqServiceImpl implements AcadQualHousingMgmtPqService {

    @Autowired
    private AcadQualHousingMgmtPqRepository acadQualHousingMgmtPqRepository;

    @Override
    public AcadQualHousingMgmtPqVo get(String id) {
        AcadQualHousingMgmtPq acadQualHousingMgmtPq = acadQualHousingMgmtPqRepository.findOne(id);
        return toVo(acadQualHousingMgmtPq);
    }

    @Transactional
    @Override
    public AcadQualHousingMgmtPqVo save(AcadQualHousingMgmtPqVo vo) {
        AcadQualHousingMgmtPq acadQualHousingMgmtPq = toAcadQualHousingMgmtPq(vo);
        acadQualHousingMgmtPq = acadQualHousingMgmtPqRepository.save(acadQualHousingMgmtPq);
        vo.setId(acadQualHousingMgmtPq.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<AcadQualHousingMgmtPqVo> save(List<AcadQualHousingMgmtPqVo> vos) {
        List<AcadQualHousingMgmtPqVo> acadQualHousingMgmtPqVos = new ArrayList<AcadQualHousingMgmtPqVo>();
        for (AcadQualHousingMgmtPqVo acadQualHousingMgmtPqVo : vos) {
            AcadQualHousingMgmtPq acadQualHousingMgmtPq = toAcadQualHousingMgmtPq(acadQualHousingMgmtPqVo);
            acadQualHousingMgmtPq = acadQualHousingMgmtPqRepository.save(acadQualHousingMgmtPq);
            acadQualHousingMgmtPqVo.setId(acadQualHousingMgmtPq.getId());
            acadQualHousingMgmtPqVos.add(acadQualHousingMgmtPqVo);
        }
        return acadQualHousingMgmtPqVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        acadQualHousingMgmtPqRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(AcadQualHousingMgmtPqVo vo) {
        acadQualHousingMgmtPqRepository.delete(toAcadQualHousingMgmtPq(vo));
    }

    @Override
    public List<AcadQualHousingMgmtPqVo> findList() {
        List<AcadQualHousingMgmtPq> acadQualHousingMgmtPqs = acadQualHousingMgmtPqRepository.findAll();
        List<AcadQualHousingMgmtPqVo> vos = new ArrayList<AcadQualHousingMgmtPqVo>();
        for (AcadQualHousingMgmtPq acadQualHousingMgmtPq : acadQualHousingMgmtPqs) {
            AcadQualHousingMgmtPqVo vo = toVo(acadQualHousingMgmtPq);
            vos.add(vo);
        }
        return vos;
    }

    @Override
    public List<AcadQualHousingMgmtPqVo> getByAppAcadQualHousingMgmtId(String appAcadQualHousingMgmtId) {
        List<AcadQualHousingMgmtPq> acadQualHousingMgmtPqs = acadQualHousingMgmtPqRepository
                .getByAppAcadQualHousingMgmtId(appAcadQualHousingMgmtId);
        List<AcadQualHousingMgmtPqVo> vos = new ArrayList<AcadQualHousingMgmtPqVo>();
        for (AcadQualHousingMgmtPq acadQualHousingMgmtPq : acadQualHousingMgmtPqs) {
            AcadQualHousingMgmtPqVo vo = toVo(acadQualHousingMgmtPq);
            vos.add(vo);
        }
        return vos;
    }

    private AcadQualHousingMgmtPqVo toVo(AcadQualHousingMgmtPq acadQualHousingMgmtPq) {
        AcadQualHousingMgmtPqVo vo = new AcadQualHousingMgmtPqVo();
        vo.setId(acadQualHousingMgmtPq.getId());
        vo.setAbbre(acadQualHousingMgmtPq.getAbbre());
        vo.setTypeOfMem(acadQualHousingMgmtPq.getTypeOfMem());
        vo.setAwardInstitution(acadQualHousingMgmtPq.getAwardInstitution());
        vo.setDateAward(acadQualHousingMgmtPq.getDateAward());
        vo.setAppAcadQualHousingMgmtId(acadQualHousingMgmtPq.getAppAcadQualHousingMgmtId());
        return vo;
    }

    private AcadQualHousingMgmtPq toAcadQualHousingMgmtPq(AcadQualHousingMgmtPqVo vo) {
        AcadQualHousingMgmtPq acadQualHousingMgmtPq = new AcadQualHousingMgmtPq();
        acadQualHousingMgmtPq.setId(vo.getId());
        acadQualHousingMgmtPq.setAbbre(vo.getAbbre());
        acadQualHousingMgmtPq.setTypeOfMem(vo.getTypeOfMem());
        acadQualHousingMgmtPq.setAwardInstitution(vo.getAwardInstitution());
        acadQualHousingMgmtPq.setDateAward(vo.getDateAward());
        acadQualHousingMgmtPq.setAppAcadQualHousingMgmtId(vo.getAppAcadQualHousingMgmtId());
        return acadQualHousingMgmtPq;
    }
}
