package com.accentrix.hku.service.app.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.app.AcadQualOthers;
import com.accentrix.hku.repository.app.AcadQualOthersRepository;
import com.accentrix.hku.service.app.AcadQualOthersService;
import com.accentrix.hku.vo.app.AcadQualOthersVo;
import com.accentrix.hku.timer.annotation.Timer;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年2月9日 下午3:13:13
 * @version 1.0
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("acadQualOthers")
public class AcadQualOthersServiceImpl implements AcadQualOthersService {

    @Autowired
    private AcadQualOthersRepository acadQualOthersRepository;

    @Override
    public AcadQualOthersVo get(String id) {
        AcadQualOthers acadQualOthers = acadQualOthersRepository.findOne(id);
        return toVo(acadQualOthers);
    }

    @Transactional
    @Override
    public AcadQualOthersVo save(AcadQualOthersVo vo) {
        AcadQualOthers acadQualOthers = toAcadQualOthers(vo);
        acadQualOthers = acadQualOthersRepository.save(acadQualOthers);
        vo.setId(acadQualOthers.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<AcadQualOthersVo> save(List<AcadQualOthersVo> vos) {
        List<AcadQualOthersVo> acadQualOthersVos = new ArrayList<AcadQualOthersVo>();
        for (AcadQualOthersVo acadQualOthersVo : vos) {
            AcadQualOthers acadQualOthers = toAcadQualOthers(acadQualOthersVo);
            acadQualOthers = acadQualOthersRepository.save(acadQualOthers);
            acadQualOthersVo.setId(acadQualOthers.getId());
            acadQualOthersVos.add(acadQualOthersVo);
        }
        return acadQualOthersVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        acadQualOthersRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(AcadQualOthersVo vo) {
        acadQualOthersRepository.delete(toAcadQualOthers(vo));
    }

    @Override
    public List<AcadQualOthersVo> findList() {
        List<AcadQualOthers> acadQualOthersList = acadQualOthersRepository.findAll();
        List<AcadQualOthersVo> vos = new ArrayList<AcadQualOthersVo>();
        for (AcadQualOthers acadQualOthers : acadQualOthersList) {
            vos.add(toVo(acadQualOthers));
        }
        return vos;
    }

    @Override
    public List<AcadQualOthersVo> getByAcadQualHousingMgmtId(String acadQualHousingMgmtId) {
        List<AcadQualOthers> acadQualOthersList = acadQualOthersRepository
                .getByAcadQualHousingMgmtId(acadQualHousingMgmtId);
        List<AcadQualOthersVo> vos = new ArrayList<AcadQualOthersVo>();
        for (AcadQualOthers acadQualOthers : acadQualOthersList) {
            vos.add(toVo(acadQualOthers));
        }
        return vos;
    }

    private AcadQualOthersVo toVo(AcadQualOthers acadQualOthers) {
        AcadQualOthersVo vo = new AcadQualOthersVo();
        vo.setId(acadQualOthers.getId());
        vo.setAcadQualHousingMgmtId(acadQualOthers.getAcadQualHousingMgmtId());
        vo.setAwardInstitution(acadQualOthers.getAwardInstitution());
        vo.setCourseDuration(acadQualOthers.getCourseDuration());
        vo.setDegreeTitle(acadQualOthers.getDegreeTitle());
        vo.setHonours(acadQualOthers.getHonours());
        vo.setMajorSubject(acadQualOthers.getMajorSubject());
        vo.setOtherAwardDate(acadQualOthers.getOtherAwardDate());
        return vo;
    }

    private AcadQualOthers toAcadQualOthers(AcadQualOthersVo vo) {
        AcadQualOthers acadQualOthers = new AcadQualOthers();
        acadQualOthers.setId(vo.getId());
        acadQualOthers.setAcadQualHousingMgmtId(vo.getAcadQualHousingMgmtId());
        acadQualOthers.setAwardInstitution(vo.getAwardInstitution());
        acadQualOthers.setCourseDuration(vo.getCourseDuration());
        acadQualOthers.setDegreeTitle(vo.getDegreeTitle());
        acadQualOthers.setHonours(vo.getHonours());
        acadQualOthers.setMajorSubject(vo.getMajorSubject());
        acadQualOthers.setOtherAwardDate(vo.getOtherAwardDate());
        return acadQualOthers;
    }
}
