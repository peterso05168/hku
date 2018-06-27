package com.accentrix.hku.service.app.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.app.ProgRequirement;
import com.accentrix.hku.repository.app.ProgRequirementRepository;
import com.accentrix.hku.service.app.ProgRequirementService;
import com.accentrix.hku.vo.app.ProgRequirementVo;
import com.accentrix.hku.timer.annotation.Timer;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年1月31日 下午2:24:25 
 * @version 1.0 
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("progRequirement")
public class ProgRequirementServiceImpl implements ProgRequirementService {

    @Autowired
    private ProgRequirementRepository progRequirementRepository;

    @Override
    public ProgRequirementVo get(String id) {
        ProgRequirement progRequirement = progRequirementRepository.findOne(id);
        return toVo(progRequirement);
    }

    @Transactional
    @Override
    public ProgRequirementVo save(ProgRequirementVo vo) {
        ProgRequirement progRequirement = toProgRequirement(vo);
        progRequirement = progRequirementRepository.save(progRequirement);
        vo.setId(progRequirement.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<ProgRequirementVo> save(List<ProgRequirementVo> vos) {
        List<ProgRequirementVo> progRequirementVos = new ArrayList<ProgRequirementVo>();
        for (ProgRequirementVo vo : vos) {
            ProgRequirement progRequirement = toProgRequirement(vo);
            progRequirement = progRequirementRepository.save(progRequirement);
            vo.setId(progRequirement.getId());
            progRequirementVos.add(vo);
        }
        return progRequirementVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        progRequirementRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(ProgRequirementVo vo) {
        progRequirementRepository.delete(toProgRequirement(vo));
    }

    @Override
    public List<ProgRequirementVo> findList() {
        List<ProgRequirement> progRequirements = progRequirementRepository.findAll();
        List<ProgRequirementVo> vos = new ArrayList<ProgRequirementVo>();
        for (ProgRequirement progRequirement : progRequirements) {
            vos.add(toVo(progRequirement));
        }
        return vos;
    }

    private ProgRequirement toProgRequirement(ProgRequirementVo vo) {
        ProgRequirement progRequirement = new ProgRequirement();
        progRequirement.setAppHkuProgrammeId(vo.getAppHkuProgrammeId());
        progRequirement.setAppRequirementId(vo.getAppRequirementId());
        progRequirement.setRelationship(vo.getRelationship());
        return progRequirement;
    }

    private ProgRequirementVo toVo(ProgRequirement progRequirement) {
        ProgRequirementVo vo = new ProgRequirementVo();
        vo.setId(progRequirement.getId());
        vo.setAppHkuProgrammeId(progRequirement.getAppHkuProgrammeId());
        vo.setAppRequirementId(progRequirement.getAppRequirementId());
        vo.setRelationship(progRequirement.getRelationship());
        return vo;
    }

}
