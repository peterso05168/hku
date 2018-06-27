package com.accentrix.hku.service.app.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.app.AcadQualHousingMgmtRwe;
import com.accentrix.hku.repository.app.AcadQualHousingMgmtRweRepository;
import com.accentrix.hku.service.app.AcadQualHousingMgmtRweService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.app.AcadQualHousingMgmtRweVo;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年4月10日 下午2:57:09
 */
@Service
@Transactional(readOnly = true)
@Timer
@Path("acadQualHousingMgmtRwe")
public class AcadQualHousingMgmtRweServiceImpl implements AcadQualHousingMgmtRweService {

    @Autowired
    private AcadQualHousingMgmtRweRepository acadQualHousingMgmtRweRepository;

    @Override
    public AcadQualHousingMgmtRweVo get(String id) {
        AcadQualHousingMgmtRwe acadQualHousingMgmtRwe = acadQualHousingMgmtRweRepository.findOne(id);
        return toVo(acadQualHousingMgmtRwe);
    }

    @Transactional
    @Override
    public AcadQualHousingMgmtRweVo save(AcadQualHousingMgmtRweVo vo) {
        AcadQualHousingMgmtRwe acadQualHousingMgmtRwe = toAcadQualHousingMgmtRwe(vo);
        acadQualHousingMgmtRwe = acadQualHousingMgmtRweRepository.save(acadQualHousingMgmtRwe);
        vo.setId(acadQualHousingMgmtRwe.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<AcadQualHousingMgmtRweVo> save(List<AcadQualHousingMgmtRweVo> vos) {
        List<AcadQualHousingMgmtRweVo> acadQualHousingMgmtRweVos = new ArrayList<AcadQualHousingMgmtRweVo>();
        for (AcadQualHousingMgmtRweVo acadQualHousingMgmtRweVo : vos) {
            AcadQualHousingMgmtRwe acadQualHousingMgmtRwe = toAcadQualHousingMgmtRwe(acadQualHousingMgmtRweVo);
            acadQualHousingMgmtRwe = acadQualHousingMgmtRweRepository.save(acadQualHousingMgmtRwe);
            acadQualHousingMgmtRweVo.setId(acadQualHousingMgmtRwe.getId());
            acadQualHousingMgmtRweVos.add(acadQualHousingMgmtRweVo);
        }
        return acadQualHousingMgmtRweVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        acadQualHousingMgmtRweRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(AcadQualHousingMgmtRweVo vo) {
        acadQualHousingMgmtRweRepository.delete(toAcadQualHousingMgmtRwe(vo));
    }

    @Override
    public List<AcadQualHousingMgmtRweVo> findList() {
        List<AcadQualHousingMgmtRwe> acadQualHousingMgmtRwes = acadQualHousingMgmtRweRepository.findAll();
        List<AcadQualHousingMgmtRweVo> vos = new ArrayList<AcadQualHousingMgmtRweVo>();
        for (AcadQualHousingMgmtRwe acadQualHousingMgmtRwe : acadQualHousingMgmtRwes) {
            AcadQualHousingMgmtRweVo vo = toVo(acadQualHousingMgmtRwe);
            vos.add(vo);
        }
        return vos;
    }

    @Override
    public List<AcadQualHousingMgmtRweVo> getByAppAcadQualHousingMgmtId(String appAcadQualHousingMgmtId) {
        List<AcadQualHousingMgmtRwe> acadQualHousingMgmtRwes = acadQualHousingMgmtRweRepository
                .getByAppAcadQualHousingMgmtId(appAcadQualHousingMgmtId);
        List<AcadQualHousingMgmtRweVo> vos = new ArrayList<AcadQualHousingMgmtRweVo>();
        for (AcadQualHousingMgmtRwe acadQualHousingMgmtRwe : acadQualHousingMgmtRwes) {
            AcadQualHousingMgmtRweVo vo = toVo(acadQualHousingMgmtRwe);
            vos.add(vo);
        }
        return vos;
    }

    private AcadQualHousingMgmtRweVo toVo(AcadQualHousingMgmtRwe acadQualHousingMgmtRwe) {
        AcadQualHousingMgmtRweVo vo = new AcadQualHousingMgmtRweVo();
        vo.setId(acadQualHousingMgmtRwe.getId());
        vo.setAppointment(acadQualHousingMgmtRwe.getAppointment());
        vo.setDateFrom(acadQualHousingMgmtRwe.getDateFrom());
        vo.setDateTo(acadQualHousingMgmtRwe.getDateTo());
        vo.setNameOfOrganization(acadQualHousingMgmtRwe.getNameOfOrganization());
        vo.setNatureOfDuties(acadQualHousingMgmtRwe.getNatureOfDuties());
        vo.setAppAcadQualHousingMgmtId(acadQualHousingMgmtRwe.getAppAcadQualHousingMgmtId());
        return vo;
    }

    private AcadQualHousingMgmtRwe toAcadQualHousingMgmtRwe(AcadQualHousingMgmtRweVo vo) {
        AcadQualHousingMgmtRwe acadQualHousingMgmtRwe = new AcadQualHousingMgmtRwe();
        acadQualHousingMgmtRwe.setId(vo.getId());
        acadQualHousingMgmtRwe.setAppointment(vo.getAppointment());
        acadQualHousingMgmtRwe.setDateFrom(vo.getDateFrom());
        acadQualHousingMgmtRwe.setDateTo(vo.getDateTo());
        acadQualHousingMgmtRwe.setNameOfOrganization(vo.getNameOfOrganization());
        acadQualHousingMgmtRwe.setNatureOfDuties(vo.getNatureOfDuties());
        acadQualHousingMgmtRwe.setAppAcadQualHousingMgmtId(vo.getAppAcadQualHousingMgmtId());
        return acadQualHousingMgmtRwe;
    }

}
