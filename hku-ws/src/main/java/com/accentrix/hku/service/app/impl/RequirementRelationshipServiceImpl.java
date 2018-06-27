package com.accentrix.hku.service.app.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.app.RequirementRelationship;
import com.accentrix.hku.repository.app.RequirementRelationshipRepository;
import com.accentrix.hku.service.app.RequirementRelationshipService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.app.RequirementRelationshipVo;

@Service
@Transactional(readOnly = true)
@Timer
@Path("requirementRelationship")
public class RequirementRelationshipServiceImpl implements RequirementRelationshipService {

    @Autowired
    private RequirementRelationshipRepository requirementRelationshipRepository;

    @Override
    public RequirementRelationshipVo get(String id) {
        RequirementRelationship requirementRelationship = requirementRelationshipRepository.findOne(id);
        return toVo(requirementRelationship);
    }

    @Transactional
    @Override
    public RequirementRelationshipVo save(RequirementRelationshipVo vo) {
        RequirementRelationship requirementRelationship = toRequirementRelation(vo);
        requirementRelationship = requirementRelationshipRepository.save(requirementRelationship);
        vo.setId(requirementRelationship.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<RequirementRelationshipVo> save(List<RequirementRelationshipVo> vos) {
        List<RequirementRelationshipVo> RequirementRelationshipVos = new ArrayList<RequirementRelationshipVo>();
        for (RequirementRelationshipVo vo : vos) {
            RequirementRelationship requirementRelationship = toRequirementRelation(vo);
            requirementRelationship = requirementRelationshipRepository.save(requirementRelationship);
            vo.setId(requirementRelationship.getId());
            RequirementRelationshipVos.add(vo);
        }
        return RequirementRelationshipVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        requirementRelationshipRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(RequirementRelationshipVo vo) {
        requirementRelationshipRepository.delete(toRequirementRelation(vo));
    }

    @Override
    public List<RequirementRelationshipVo> findListByParentId(String parentRequirementId) {
        List<RequirementRelationship> requirementRelationships = requirementRelationshipRepository
                .findListByParentId(parentRequirementId);
        List<RequirementRelationshipVo> vos = new ArrayList<RequirementRelationshipVo>();
        for (RequirementRelationship requirementRelationship : requirementRelationships) {
            vos.add(toVo(requirementRelationship));
        }
        return vos;
    }

    private RequirementRelationship toRequirementRelation(RequirementRelationshipVo vo) {
        RequirementRelationship requirementRelationship = new RequirementRelationship();
        requirementRelationship.setParentRequirementId(vo.getParentRequirementId());
        requirementRelationship.setChildRequirementId(vo.getChildRequirementId());
        return requirementRelationship;
    }

    private RequirementRelationshipVo toVo(RequirementRelationship requirementRelationship) {
        RequirementRelationshipVo vo = new RequirementRelationshipVo();
        vo.setId(requirementRelationship.getId());
        vo.setParentRequirementId(requirementRelationship.getParentRequirementId());
        vo.setChildRequirementId(requirementRelationship.getChildRequirementId());
        return vo;
    }
}
