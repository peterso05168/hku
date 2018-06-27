package com.accentrix.hku.service.app.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.app.AcadBg;
import com.accentrix.hku.repository.app.AcadBgRepository;
import com.accentrix.hku.service.app.AcadBgService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.app.AcadBgVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 下午2:19:24
 * @version 1.0
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("acadBg")
public class AcadBgServiceImpl implements AcadBgService {

    @Autowired
    private AcadBgRepository acadBgRepository;

    @Override
    public AcadBgVo get(String id) {
        AcadBg acadBg = acadBgRepository.findOne(id);
        return toVo(acadBg);
    }

    @Transactional
    @Override
    public AcadBgVo save(AcadBgVo vo) {
        AcadBg acadBg = toAcadBg(vo);
        acadBg = acadBgRepository.save(acadBg);
        vo.setId(acadBg.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<AcadBgVo> save(List<AcadBgVo> vos) {
        List<AcadBgVo> acadBgVos = new ArrayList<AcadBgVo>();
        for (AcadBgVo acadBgVo : vos) {
            AcadBg acadBg = toAcadBg(acadBgVo);
            acadBg = acadBgRepository.save(acadBg);
            acadBgVo.setId(acadBg.getId());
            acadBgVos.add(acadBgVo);
        }
        return acadBgVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        acadBgRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(AcadBgVo vo) {
        acadBgRepository.delete(toAcadBg(vo));
    }

    @Override
    public List<AcadBgVo> findList() {
        List<AcadBg> acadBgs = acadBgRepository.findAll();
        List<AcadBgVo> vos = new ArrayList<AcadBgVo>();
        for (AcadBg acadBg : acadBgs) {
            AcadBgVo vo = toVo(acadBg);
            vos.add(vo);
        }
        return vos;
    }

    private AcadBgVo toVo(AcadBg acadBg) {
        AcadBgVo vo = new AcadBgVo();
        vo.setId(acadBg.getId());
        vo.setCountryId(acadBg.getCountryId());
        vo.setProvinceId(acadBg.getProvinceId());
        vo.setCityId(acadBg.getCityId());
        vo.setInstitutionId(acadBg.getInstitutionId());
        vo.setProgrammeTypeCd(acadBg.getProgrammeTypeCd());
        vo.setProgrammeTitle(acadBg.getProgrammeTitle());
        vo.setDateOfAdmissionToProg(acadBg.getDateOfAdmissionToProg());
        vo.setCurrentYrOfStudy(acadBg.getCurrentYrOfStudy());
        vo.setExpectedDateOfGrad(acadBg.getExpectedDateOfGrad());
        vo.setLatestCumulativeGpa(acadBg.getLatestCumulativeGpa());
        vo.setMaxGpa(acadBg.getMaxGpa());
        vo.setPrimaryEduYrs(acadBg.getPrimaryEduYrs());
        vo.setSecondaryEduYrs(acadBg.getSecondaryEduYrs());
        vo.setPostEduYrs(acadBg.getPostEduYrs());
        vo.setHighestQualificationCd(acadBg.getHighestQualificationCd());
        vo.setStudyModeCd(acadBg.getStudyModeCd());
        vo.setIsPartcpNextNjcee(acadBg.getIsPartcpNextNjcee());
        vo.setIsNotStuding(acadBg.getIsNotStuding());
        vo.setInstitutionOthers(acadBg.getInstitutionOthers());
        vo.setStudyCountryOthers(acadBg.getStudyCountryOthers());
        vo.setCreateBy(acadBg.getCreateBy());
        vo.setUpdateBy(acadBg.getUpdateBy());
        vo.setCreateDate(acadBg.getCreateDate());
        vo.setUpdateDate(acadBg.getUpdateDate());
        vo.setVersion(acadBg.getVersion());
        vo.setFinalRslt(acadBg.getFinalRslt());
        vo.setNotGpa(acadBg.getNotGpa());
        vo.setProgTypeOthers(acadBg.getProgTypeOthers());
        vo.setTypeOfEducation(acadBg.getTypeOfEducation());
        vo.setIsCompletedStudy(acadBg.getIsCompletedStudy());
        return vo;
    }

    private AcadBg toAcadBg(AcadBgVo vo) {
        AcadBg acadBg = new AcadBg();
        acadBg.setId(vo.getId());
        acadBg.setCountryId(vo.getCountryId());
        acadBg.setProvinceId(vo.getProvinceId());
        acadBg.setCityId(vo.getCityId());
        acadBg.setInstitutionId(vo.getInstitutionId());
        acadBg.setProgrammeTypeCd(vo.getProgrammeTypeCd());
        acadBg.setProgrammeTitle(vo.getProgrammeTitle());
        acadBg.setDateOfAdmissionToProg(vo.getDateOfAdmissionToProg());
        acadBg.setCurrentYrOfStudy(vo.getCurrentYrOfStudy());
        acadBg.setExpectedDateOfGrad(vo.getExpectedDateOfGrad());
        acadBg.setLatestCumulativeGpa(vo.getLatestCumulativeGpa());
        acadBg.setMaxGpa(vo.getMaxGpa());
        acadBg.setPrimaryEduYrs(vo.getPrimaryEduYrs());
        acadBg.setSecondaryEduYrs(vo.getSecondaryEduYrs());
        acadBg.setPostEduYrs(vo.getPostEduYrs());
        acadBg.setHighestQualificationCd(vo.getHighestQualificationCd());
        acadBg.setStudyModeCd(vo.getStudyModeCd());
        acadBg.setIsPartcpNextNjcee(vo.getIsPartcpNextNjcee());
        acadBg.setIsNotStuding(vo.getIsNotStuding());
        acadBg.setInstitutionOthers(vo.getInstitutionOthers());
        acadBg.setStudyCountryOthers(vo.getStudyCountryOthers());
        acadBg.setFinalRslt(vo.getFinalRslt());
        acadBg.setNotGpa(vo.getNotGpa());
        acadBg.setProgTypeOthers(vo.getProgTypeOthers());
        acadBg.setCreateBy(vo.getCreateBy());
        acadBg.setUpdateBy(vo.getUpdateBy());
        acadBg.setCreateDate(vo.getCreateDate());
        acadBg.setUpdateDate(vo.getUpdateDate());
        acadBg.setVersion(vo.getVersion());
        acadBg.setTypeOfEducation(vo.getTypeOfEducation());
        acadBg.setIsCompletedStudy(vo.getIsCompletedStudy());
        return acadBg;
    }

}
