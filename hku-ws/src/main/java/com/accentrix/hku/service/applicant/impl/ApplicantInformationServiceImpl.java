package com.accentrix.hku.service.applicant.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.applicant.ApplicantInformation;
import com.accentrix.hku.repository.applicant.ApplicantInformationRepository;
import com.accentrix.hku.service.app.TelNoService;
import com.accentrix.hku.service.applicant.AccountService;
import com.accentrix.hku.service.applicant.ApplicantInformationService;
import com.accentrix.hku.service.applicant.ApplicantToTagService;
import com.accentrix.hku.service.tag.TagService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.app.TelNoVo;
import com.accentrix.hku.vo.applicant.AccountVo;
import com.accentrix.hku.vo.applicant.ApplicantInformationForm;
import com.accentrix.hku.vo.applicant.ApplicantInformationVo;
import com.accentrix.hku.vo.applicant.ApplicantToTagVo;
import com.accentrix.hku.vo.tag.TagVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 下午2:30:18
 * @version 1.0
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("applicantInformation")
public class ApplicantInformationServiceImpl implements ApplicantInformationService {

    @Autowired
    private ApplicantInformationRepository applicantInformationRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private ApplicantToTagService applicantToTagService;
    @Autowired
    private TagService tagService;
    @Autowired
    private TelNoService telNoService;

    @Override
    public ApplicantInformationVo get(String id) {
        ApplicantInformation applicantInformation = applicantInformationRepository.findOne(id);
        return toVo(applicantInformation);
    }

    @Transactional
    @Override
    public ApplicantInformationVo save(ApplicantInformationVo vo) {
        ApplicantInformation applicantInformation = toApplicantInformation(vo);
        applicantInformation = applicantInformationRepository.save(applicantInformation);
        vo.setId(applicantInformation.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<ApplicantInformationVo> save(List<ApplicantInformationVo> vos) {
        List<ApplicantInformationVo> applicantInformationVos = new ArrayList<ApplicantInformationVo>();
        for (ApplicantInformationVo applicantInformationVo : vos) {
            ApplicantInformation applicantInformation = toApplicantInformation(applicantInformationVo);
            applicantInformation = applicantInformationRepository.save(applicantInformation);
            applicantInformationVo.setId(applicantInformation.getId());
            applicantInformationVos.add(applicantInformationVo);
        }
        return applicantInformationVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        applicantInformationRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(ApplicantInformationVo applicantInformationVo) {
        applicantInformationRepository.delete(toApplicantInformation(applicantInformationVo));
    }

    @Override
    public List<ApplicantInformationVo> findList() {
        List<ApplicantInformationVo> applicantInformationVos = new ArrayList<ApplicantInformationVo>();
        List<ApplicantInformation> applicantInformations = applicantInformationRepository.findAll();
        for (ApplicantInformation applicantInformation : applicantInformations) {
            applicantInformationVos.add(toVo(applicantInformation));
        }
        return applicantInformationVos;
    }

    private ApplicantInformation toApplicantInformation(ApplicantInformationVo vo) {
        ApplicantInformation applicantInformation = new ApplicantInformation();
        applicantInformation.setId(vo.getId());
        applicantInformation.setAlternateEmail(vo.getAlternateEmail());
        applicantInformation.setSurname(vo.getSurname());
        applicantInformation.setGivenName(vo.getGivenName());
        applicantInformation.setNationalityCountryId(vo.getNationalityCountryId());
        applicantInformation.setNationalityProvinceId(vo.getNationalityProvinceId());
        applicantInformation.setNationalityCityId(vo.getNationalityCityId());
        applicantInformation.setStudyCountryId(vo.getStudyCountryId());
        applicantInformation.setStudyCountryOthers(vo.getStudyCountryOthers());
        applicantInformation.setHomeTelId(vo.getHomeTelId());
        applicantInformation.setMobileTelId(vo.getMobileTelId());
        applicantInformation.setInstitutionId(vo.getInstitutionId());
        applicantInformation.setInstitutionOthers(vo.getInstitutionOthers());
        applicantInformation.setReceiveInfoFlag(vo.getReceiveInfoFlag());
        applicantInformation.setResidenceCountryId(vo.getResidenceCountryId());
        applicantInformation.setSex(vo.getSex());
        applicantInformation.setDateOfBirth(vo.getDateOfBirth());
        applicantInformation.setHkid(vo.getHkid());
        applicantInformation.setPassportNo(vo.getPassportNo());
        applicantInformation.setCorrespondenceAddr(vo.getCorrespondenceAddr());
        applicantInformation.setChineseName(vo.getChineseName());
        applicantInformation.setStudyProvinceId(vo.getStudyProvinceId());
        applicantInformation.setStudyCityId(vo.getStudyCityId());
        applicantInformation.setIsNotStuding(vo.getIsNotStuding());
        applicantInformation.setBatchAnncmntDate(vo.getBatchAnncmntDate());
        return applicantInformation;
    }

    private ApplicantInformationVo toVo(ApplicantInformation applicantInformation) {
        ApplicantInformationVo vo = new ApplicantInformationVo();
        vo.setId(applicantInformation.getId());
        vo.setAlternateEmail(applicantInformation.getAlternateEmail());
        vo.setSurname(applicantInformation.getSurname());
        vo.setGivenName(applicantInformation.getGivenName());
        vo.setNationalityCountryId(applicantInformation.getNationalityCountryId());
        vo.setNationalityProvinceId(applicantInformation.getNationalityProvinceId());
        vo.setNationalityCityId(applicantInformation.getNationalityCityId());
        vo.setStudyCountryId(applicantInformation.getStudyCountryId());
        vo.setStudyCountryOthers(applicantInformation.getStudyCountryOthers());
        vo.setHomeTelId(applicantInformation.getHomeTelId());
        vo.setMobileTelId(applicantInformation.getMobileTelId());
        vo.setInstitutionId(applicantInformation.getInstitutionId());
        vo.setInstitutionOthers(applicantInformation.getInstitutionOthers());
        vo.setReceiveInfoFlag(applicantInformation.getReceiveInfoFlag());
        vo.setResidenceCountryId(applicantInformation.getResidenceCountryId());
        vo.setSex(applicantInformation.getSex());
        vo.setDateOfBirth(applicantInformation.getDateOfBirth());
        vo.setHkid(applicantInformation.getHkid());
        vo.setPassportNo(applicantInformation.getPassportNo());
        vo.setCorrespondenceAddr(applicantInformation.getCorrespondenceAddr());
        vo.setChineseName(applicantInformation.getChineseName());
        vo.setStudyProvinceId(applicantInformation.getStudyProvinceId());
        vo.setStudyCityId(applicantInformation.getStudyCityId());
        vo.setIsNotStuding(applicantInformation.getIsNotStuding());
        vo.setBatchAnncmntDate(applicantInformation.getBatchAnncmntDate());
        if (StringUtils.isNotBlank(applicantInformation.getId())) {
            AccountVo accountVo = accountService.getByApplicantInfoId(applicantInformation.getId());
            vo.setRegisteredEmail(accountVo.getPersonEmail());
            List<ApplicantToTagVo> applicantToTagVos = applicantToTagService
                    .findByApplicantId(applicantInformation.getId());
            String tags = "";
            for (ApplicantToTagVo applicantToTagVo : applicantToTagVos) {
                TagVo tagVo = tagService.get(applicantToTagVo.getTagId());
                tags = tags + tagVo.getDesc() + ",";
            }
            if (StringUtils.isNotBlank(tags)) {
                vo.setTags(tags.substring(0, tags.length() - 1));
            }
        }
        return vo;
    }

    @Override
    public Page<ApplicantInformationVo> findPage(ApplicantInformationForm applicantInformationForm) {
        List<ApplicantInformation> list = applicantInformationRepository.findPage(
                applicantInformationForm.getApplicantInformationVo(),
                applicantInformationForm.getPageable().getOffset(),
                applicantInformationForm.getPageable().getPageSize());
        List<ApplicantInformationVo> vos = new ArrayList<ApplicantInformationVo>();
        list.forEach(applicantInformation -> vos.add(toVo(applicantInformation)));
        Integer countNum = applicantInformationRepository
                .countNumber(applicantInformationForm.getApplicantInformationVo());
        return new PageImpl<ApplicantInformationVo>(vos, applicantInformationForm.getPageable(), countNum);
    }

    @Override
    public ApplicantInformationVo getById(String id) {
        ApplicantInformation applicantInformation = applicantInformationRepository.findOne(id);
        ApplicantInformationVo vo = toVo(applicantInformation);
        List<ApplicantToTagVo> applicantToTagVos = applicantToTagService.findByApplicantId(id);
        TelNoVo telNoVo = new TelNoVo();
        if (StringUtils.isNotBlank(vo.getHomeTelId())) {
            telNoVo = telNoService.get(vo.getHomeTelId());
            vo.setHomeTel(telNoVo);
        }
        if (StringUtils.isNotBlank(vo.getMobileTelId())) {
            telNoVo = telNoService.get(vo.getMobileTelId());
            vo.setMobileTel(telNoVo);
        }
        String tagNames = "";
        for (ApplicantToTagVo applicantToTagVo : applicantToTagVos) {
            TagVo tagVo = tagService.get(applicantToTagVo.getTagId());
            tagNames = tagNames + "#" + tagVo.getDesc() + ",";
        }
        if (StringUtils.isNotBlank(tagNames)) {
            vo.setTags(tagNames.substring(0, tagNames.length() - 1));
        }
        return vo;
    }
}
