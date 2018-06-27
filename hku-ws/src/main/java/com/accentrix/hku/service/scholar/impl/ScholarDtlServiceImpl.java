package com.accentrix.hku.service.scholar.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.scholar.ScholarDtl;
import com.accentrix.hku.repository.scholar.ScholarDtlRepository;
import com.accentrix.hku.service.app.RequirementService;
import com.accentrix.hku.service.scholar.ScholarDtlRequirementService;
import com.accentrix.hku.service.scholar.ScholarDtlService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.app.RequirementVo;
import com.accentrix.hku.vo.scholar.ScholarDtlRequirementVo;
import com.accentrix.hku.vo.scholar.ScholarDtlVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年3月19日 上午11:45:30
 * @version 1.0
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("scholarDtl")
public class ScholarDtlServiceImpl implements ScholarDtlService {

    @Autowired
    private ScholarDtlRepository scholarDtlRepository;

    @Autowired
    private ScholarDtlRequirementService scholarDtlRequirementService;

    @Autowired
    private RequirementService requirementService;

    @Override
    public ScholarDtlVo get(String id) {
        ScholarDtl scholarDtl = scholarDtlRepository.findOne(id);
        return toVo(scholarDtl);
    }

    @Transactional
    @Override
    public ScholarDtlVo save(ScholarDtlVo vo) {
        ScholarDtl scholarDtl = toScholarDtl(vo);
        scholarDtl = scholarDtlRepository.save(scholarDtl);
        vo.setId(scholarDtl.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<ScholarDtlVo> save(List<ScholarDtlVo> vos) {
        List<ScholarDtlVo> scholarDtlVos = new ArrayList<ScholarDtlVo>();
        for (ScholarDtlVo scholarDtlVo : vos) {
            ScholarDtl scholarDtl = toScholarDtl(scholarDtlVo);
            scholarDtl = scholarDtlRepository.save(scholarDtl);
            scholarDtlVo.setId(scholarDtl.getId());
            scholarDtlVos.add(scholarDtlVo);
        }
        return scholarDtlVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        scholarDtlRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(ScholarDtlVo vo) {
        ScholarDtl scholarDtl = toScholarDtl(vo);
        scholarDtlRepository.delete(scholarDtl);
    }

    @Override
    public List<ScholarDtlVo> findList() {
        List<ScholarDtl> list = scholarDtlRepository.findAll();
        List<ScholarDtlVo> vos = new ArrayList<ScholarDtlVo>();
        for (ScholarDtl scholarDtl : list) {
            vos.add(toVo(scholarDtl));
        }
        return vos;
    }

    private ScholarDtlVo toVo(ScholarDtl scholarDtl) {
        ScholarDtlVo vo = new ScholarDtlVo();
        vo.setId(scholarDtl.getId());
        vo.setTier(scholarDtl.getTier());
        vo.setAmount(scholarDtl.getAmount());
        vo.setLetterContent(scholarDtl.getLetterContent());
        vo.setScholarId(scholarDtl.getScholarId());
        List<ScholarDtlRequirementVo> dtlReqVos = scholarDtlRequirementService.findByScholarDtlId(vo.getId());
        List<String> requirementNames = new ArrayList<String>();
        List<String> requirementIds = new ArrayList<String>();
        if (CollectionUtils.isNotEmpty(dtlReqVos)) {
            for (ScholarDtlRequirementVo dtlReqVo : dtlReqVos) {
                RequirementVo requirementVo = requirementService.get(dtlReqVo.getRequirementId());
                requirementNames.add(requirementVo.getDescription());
                requirementIds.add(requirementVo.getId());
            }
        }
        vo.setRequirementNames(requirementNames);
        vo.setRequirementIds(requirementIds);
        return vo;
    }

    private ScholarDtl toScholarDtl(ScholarDtlVo vo) {
        ScholarDtl scholarDtl = new ScholarDtl();
        scholarDtl.setId(vo.getId());
        scholarDtl.setTier(vo.getTier());
        scholarDtl.setAmount(vo.getAmount());
        scholarDtl.setLetterContent(vo.getLetterContent());
        scholarDtl.setScholarId(vo.getScholarId());
        return scholarDtl;
    }

    @Override
    public List<ScholarDtlVo> findByScholarId(String scholarId) {
        List<ScholarDtl> list = scholarDtlRepository.findByScholarId(scholarId);
        List<ScholarDtlVo> vos = new ArrayList<ScholarDtlVo>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (ScholarDtl scholarDtl : list) {
                vos.add(toVo(scholarDtl));
            }
        }
        return vos;
    }

}
