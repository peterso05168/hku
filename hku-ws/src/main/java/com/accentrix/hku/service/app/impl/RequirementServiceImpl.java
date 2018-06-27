package com.accentrix.hku.service.app.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.app.Requirement;
import com.accentrix.hku.repository.app.RequirementRepository;
import com.accentrix.hku.service.adm.FormProgReqService;
import com.accentrix.hku.service.app.RequirementService;
import com.accentrix.hku.vo.adm.FormProgReqVo;
import com.accentrix.hku.vo.app.RequirementVo;
import com.accentrix.hku.vo.xml.AgeRangeVo;
import com.accentrix.hku.vo.xml.ExaminationVo;
import com.accentrix.hku.vo.xml.GpsVo;
import com.accentrix.hku.xml.XmlConverter;
import com.accentrix.hku.timer.annotation.Timer;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 下午2:26:59
 * @version 1.0
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("requirement")
public class RequirementServiceImpl implements RequirementService {

    @Autowired
    private RequirementRepository requirementRepository;

    @Autowired
    private FormProgReqService formProgReqService;

    @Override
    public RequirementVo get(String id) {
        Requirement requirement = requirementRepository.findOne(id);
        return toVo(requirement);
    }

    @Transactional
    @Override
    public RequirementVo save(RequirementVo vo) {
        Requirement requirement = toRequirement(vo);
        requirement = requirementRepository.save(requirement);
        vo.setId(requirement.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<RequirementVo> save(List<RequirementVo> vos) {
        List<RequirementVo> requirementVos = new ArrayList<RequirementVo>();
        for (RequirementVo vo : vos) {
            Requirement requirement = toRequirement(vo);
            requirement = requirementRepository.save(requirement);
            vo.setId(requirement.getId());
            requirementVos.add(vo);
        }
        return requirementVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        requirementRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(RequirementVo vo) {
        requirementRepository.delete(toRequirement(vo));
    }

    @Override
    public List<RequirementVo> findList(RequirementVo vo) {
        List<Requirement> requirements = requirementRepository.findList(vo.getDescription(), vo.getType(),
                vo.getIsPublished());
        List<RequirementVo> vos = new ArrayList<RequirementVo>();
        for (Requirement requirement : requirements) {
            vos.add(toVo(requirement));
        }
        return vos;
    }

    private Requirement toRequirement(RequirementVo vo) {
        Requirement requirement = new Requirement();
        if (StringUtils.isNotBlank(vo.getId()))
            requirement.setId(vo.getId());
        requirement.setType(vo.getType());
        requirement.setDescription(vo.getDescription());
        requirement.setObjClassName(vo.getObjClassName());
        Object obj = null;
        if (AgeRangeVo.class.getName().equals(vo.getObjClassName()))
            obj = vo.getAgeRange();
        else if (ExaminationVo.class.getName().equals(vo.getObjClassName()))
            obj = vo.getExamination();
        else if (GpsVo.class.getName().equals(vo.getObjClassName()))
            obj = vo.getGpsVo();
        requirement.setObjXml(XmlConverter.toXml(obj));
        requirement.setIsPublished(vo.getIsPublished());
        requirement.setRelationship(vo.getRelationship());
        return requirement;
    }

    private RequirementVo toVo(Requirement requirement) {
        RequirementVo vo = new RequirementVo();
        vo.setId(requirement.getId());
        vo.setType(requirement.getType());
        vo.setDescription(requirement.getDescription());
        vo.setObjClassName(requirement.getObjClassName());
        Object obj = XmlConverter.fromXml(requirement.getObjXml());
        if (AgeRangeVo.class.getName().equals(vo.getObjClassName()))
            vo.setAgeRange(obj == null ? new AgeRangeVo() : (AgeRangeVo) obj);
        else if (ExaminationVo.class.getName().equals(vo.getObjClassName()))
            vo.setExamination(obj == null ? new ExaminationVo() : (ExaminationVo) obj);
        else if (GpsVo.class.getName().equals(vo.getObjClassName()))
            vo.setGpsVo(obj == null ? new GpsVo() : (GpsVo) obj);
        vo.setIsPublished(requirement.getIsPublished());
        vo.setRelationship(requirement.getRelationship());
        return vo;
    }

    @Override
    public List<String> getIdsByFormProgId(String formProgId) {
        List<FormProgReqVo> formProgReqs = formProgReqService.getByFormProgId(formProgId);
        List<String> ids = new ArrayList<String>();
        for (FormProgReqVo fpr : formProgReqs) {
            RequirementVo vo = toVo(requirementRepository.getOne(fpr.getAppRequirementId()));
            ids.add(vo.getId());
        }
        return ids;
    }

    @Override
    public List<RequirementVo> findByIdNotIn(List<String> ids) {
        List<Requirement> requirements = requirementRepository.findByIdNotIn(ids);
        List<RequirementVo> vos = new ArrayList<RequirementVo>();
        for (Requirement requirement : requirements) {
            vos.add(toVo(requirement));
        }
        return vos;
    }

}
