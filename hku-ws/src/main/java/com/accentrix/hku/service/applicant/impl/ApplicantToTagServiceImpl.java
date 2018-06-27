package com.accentrix.hku.service.applicant.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.applicant.ApplicantToTag;
import com.accentrix.hku.repository.applicant.ApplicantToTagRepository;
import com.accentrix.hku.service.applicant.ApplicantToTagService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.applicant.ApplicantToTagVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年4月27日 下午5:56:33 
 * @version 1.0 
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("applicantToTag")
public class ApplicantToTagServiceImpl implements ApplicantToTagService {

    @Autowired
    private ApplicantToTagRepository applicantToTagRepository;

    @Override
    public ApplicantToTagVo get(String id) {
        ApplicantToTag applicantToTag = applicantToTagRepository.findOne(id);
        return toVo(applicantToTag);
    }

    @Transactional
    @Override
    public ApplicantToTagVo save(ApplicantToTagVo vo) {
        ApplicantToTag applicantToTag = toApplicantToTag(vo);
        applicantToTag = applicantToTagRepository.save(applicantToTag);
        vo.setId(applicantToTag.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<ApplicantToTagVo> save(List<ApplicantToTagVo> vos) {
        List<ApplicantToTagVo> applicantToTagVos = new ArrayList<ApplicantToTagVo>();
        for (ApplicantToTagVo applicantToTagVo : vos) {
            ApplicantToTag applicantToTag = toApplicantToTag(applicantToTagVo);
            applicantToTag = applicantToTagRepository.save(applicantToTag);
            applicantToTagVo.setId(applicantToTag.getId());
            applicantToTagVos.add(applicantToTagVo);
        }
        return applicantToTagVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        applicantToTagRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(ApplicantToTagVo vo) {
        ApplicantToTag applicantToTag = toApplicantToTag(vo);
        applicantToTagRepository.delete(applicantToTag);
    }

    @Override
    public List<ApplicantToTagVo> findList() {
        List<ApplicantToTag> list = applicantToTagRepository.findAll();
        List<ApplicantToTagVo> vos = new ArrayList<ApplicantToTagVo>();
        for (ApplicantToTag applicantToTag : list) {
            vos.add(toVo(applicantToTag));
        }
        return vos;
    }

    private ApplicantToTagVo toVo(ApplicantToTag applicantToTag) {
        ApplicantToTagVo vo = new ApplicantToTagVo();
        vo.setId(applicantToTag.getId());
        vo.setApplicantInfoId(applicantToTag.getApplicantInfoId());
        vo.setTagId(applicantToTag.getTagId());
        return vo;
    }

    private ApplicantToTag toApplicantToTag(ApplicantToTagVo vo) {
        ApplicantToTag applicantToTag = new ApplicantToTag();
        applicantToTag.setId(vo.getId());
        applicantToTag.setApplicantInfoId(vo.getApplicantInfoId());
        applicantToTag.setTagId(vo.getTagId());
        return applicantToTag;
    }

    @Override
    public List<ApplicantToTagVo> findByApplicantId(String applicantId) {
        List<ApplicantToTag> list = applicantToTagRepository.findByApplicantId(applicantId);
        List<ApplicantToTagVo> vos = new ArrayList<ApplicantToTagVo>();
        for (ApplicantToTag applicantToTag : list) {
            ApplicantToTagVo applicantToTagVo = toVo(applicantToTag);
            vos.add(applicantToTagVo);
        }
        return vos;
    }

}
