package com.accentrix.hku.service.scholar.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.scholar.ScholarDtlRequirement;
import com.accentrix.hku.repository.scholar.ScholarDtlRequirementRepository;
import com.accentrix.hku.service.scholar.ScholarDtlRequirementService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.scholar.ScholarDtlRequirementVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年3月19日 上午11:45:38 
 * @version 1.0 
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("scholarDtlRequirement")
public class ScholarDtlRequirementServiceImpl implements ScholarDtlRequirementService {

    @Autowired
    private ScholarDtlRequirementRepository scholarDtlRequirementRepository;

    @Override
    public ScholarDtlRequirementVo get(String id) {
        ScholarDtlRequirement scholarDtlRequirement = scholarDtlRequirementRepository.findOne(id);
        return toVo(scholarDtlRequirement);
    }

    @Transactional
    @Override
    public ScholarDtlRequirementVo save(ScholarDtlRequirementVo vo) {
        ScholarDtlRequirement scholarDtlRequirement = toScholarDtlRequirement(vo);
        scholarDtlRequirement = scholarDtlRequirementRepository.save(scholarDtlRequirement);
        vo.setId(scholarDtlRequirement.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<ScholarDtlRequirementVo> save(List<ScholarDtlRequirementVo> vos) {
        List<ScholarDtlRequirementVo> scholarDtlRequirementVos = new ArrayList<ScholarDtlRequirementVo>();
        for (ScholarDtlRequirementVo scholarDtlRequirementVo : vos) {
            ScholarDtlRequirement scholarDtlRequirement = toScholarDtlRequirement(scholarDtlRequirementVo);
            scholarDtlRequirement = scholarDtlRequirementRepository.save(scholarDtlRequirement);
            scholarDtlRequirementVo.setId(scholarDtlRequirement.getId());
            scholarDtlRequirementVos.add(scholarDtlRequirementVo);
        }
        return scholarDtlRequirementVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        scholarDtlRequirementRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(ScholarDtlRequirementVo vo) {
        ScholarDtlRequirement scholarDtlRequirement = toScholarDtlRequirement(vo);
        scholarDtlRequirementRepository.delete(scholarDtlRequirement);
    }

    @Override
    public List<ScholarDtlRequirementVo> findList() {
        List<ScholarDtlRequirement> list = scholarDtlRequirementRepository.findAll();
        List<ScholarDtlRequirementVo> vos = new ArrayList<ScholarDtlRequirementVo>();
        for (ScholarDtlRequirement scholarDtlRequirement : list) {
            vos.add(toVo(scholarDtlRequirement));
        }
        return vos;
    }

    private ScholarDtlRequirementVo toVo(ScholarDtlRequirement scholarDtlRequirement) {
        ScholarDtlRequirementVo vo = new ScholarDtlRequirementVo();
        vo.setId(scholarDtlRequirement.getId());
        vo.setRequirementId(scholarDtlRequirement.getRequirementId());
        vo.setScholarDtlId(scholarDtlRequirement.getScholarDtlId());
        return vo;
    }

    private ScholarDtlRequirement toScholarDtlRequirement(ScholarDtlRequirementVo vo) {
        ScholarDtlRequirement scholarDtlRequirement = new ScholarDtlRequirement();
        scholarDtlRequirement.setId(vo.getId());
        scholarDtlRequirement.setRequirementId(vo.getRequirementId());
        scholarDtlRequirement.setScholarDtlId(vo.getScholarDtlId());
        return scholarDtlRequirement;
    }

    @Override
    public List<ScholarDtlRequirementVo> findByScholarDtlId(String scholarDtlId) {
        List<ScholarDtlRequirement> list = scholarDtlRequirementRepository.findByScholarDtlId(scholarDtlId);
        List<ScholarDtlRequirementVo> vos = new ArrayList<ScholarDtlRequirementVo>();
        for (ScholarDtlRequirement scholarDtlRequirement : list) {
            vos.add(toVo(scholarDtlRequirement));
        }
        return vos;
    }

}
