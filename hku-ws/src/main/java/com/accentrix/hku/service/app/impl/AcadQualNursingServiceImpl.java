package com.accentrix.hku.service.app.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.app.AcadQualNursing;
import com.accentrix.hku.repository.app.AcadQualNursingRepository;
import com.accentrix.hku.service.app.AcadQualNursingService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.app.AcadQualNursingVo;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年4月10日 下午2:02:42
 */
@Service
@Transactional(readOnly = true)
@Timer
@Path("acadQualNursing")
public class AcadQualNursingServiceImpl implements AcadQualNursingService {

    @Autowired
    private AcadQualNursingRepository acadQualNursingRepository;

    @Override
    public AcadQualNursingVo get(String id) {
        AcadQualNursing acadQualNursing = acadQualNursingRepository.findOne(id);
        return toVo(acadQualNursing);
    }

    @Transactional
    @Override
    public AcadQualNursingVo save(AcadQualNursingVo vo) {
        AcadQualNursing acadQualNursing = toAcadQualNursing(vo);
        acadQualNursing = acadQualNursingRepository.save(acadQualNursing);
        vo.setId(acadQualNursing.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<AcadQualNursingVo> save(List<AcadQualNursingVo> vos) {
        List<AcadQualNursingVo> acadQualNursingVos = new ArrayList<AcadQualNursingVo>();
        for (AcadQualNursingVo acadQualNursingVo : vos) {
            AcadQualNursing acadQualNursing = toAcadQualNursing(acadQualNursingVo);
            acadQualNursing = acadQualNursingRepository.save(acadQualNursing);
            acadQualNursingVo.setId(acadQualNursing.getId());
            acadQualNursingVos.add(acadQualNursingVo);
        }
        return acadQualNursingVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        acadQualNursingRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(AcadQualNursingVo vo) {
        acadQualNursingRepository.delete(toAcadQualNursing(vo));
    }

    @Override
    public List<AcadQualNursingVo> findList() {
        List<AcadQualNursing> acadQualNursings = acadQualNursingRepository.findAll();
        List<AcadQualNursingVo> vos = new ArrayList<AcadQualNursingVo>();
        for (AcadQualNursing acadQualNursing : acadQualNursings) {
            vos.add(toVo(acadQualNursing));
        }
        return vos;
    }

    private AcadQualNursingVo toVo(AcadQualNursing acadQualNursing) {
        AcadQualNursingVo vo = new AcadQualNursingVo();
        vo.setId(acadQualNursing.getId());
        vo.setSpecResponsibilty(vo.getSpecResponsibilty());
        return vo;
    }

    private AcadQualNursing toAcadQualNursing(AcadQualNursingVo vo) {
        AcadQualNursing acadQualNursing = new AcadQualNursing();
        acadQualNursing.setId(vo.getId());
        acadQualNursing.setSpecResponsibilty(vo.getSpecResponsibilty());
        return acadQualNursing;
    }
}
