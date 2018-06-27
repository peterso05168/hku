package com.accentrix.hku.service.app.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.constant.Constants;
import com.accentrix.hku.domain.app.PrevStudies;
import com.accentrix.hku.repository.app.PrevStudiesRepository;
import com.accentrix.hku.service.app.InstitutionService;
import com.accentrix.hku.service.app.PrevStudiesService;
import com.accentrix.hku.service.cpc.CountryService;
import com.accentrix.hku.service.general.RefCdService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.app.PrevStudiesVo;
import com.accentrix.hku.vo.general.RefCdVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 下午2:23:22
 * @version 1.0
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("prevStudies")
public class PrevStudiesServiceImpl implements PrevStudiesService {

    @Autowired
    private PrevStudiesRepository prevStudiesRepository;

    @Autowired
    private CountryService countryService;
    @Autowired
    private InstitutionService institutionService;
    @Autowired
    private RefCdService refCdService;

    @Override
    public PrevStudiesVo get(String id) {
        PrevStudies prevStudies = prevStudiesRepository.findOne(id);
        return toVo(prevStudies);
    }

    @Transactional
    @Override
    public PrevStudiesVo save(PrevStudiesVo vo) {
        PrevStudies prevStudies = toPrevStudies(vo);
        prevStudies = prevStudiesRepository.save(prevStudies);
        vo.setId(prevStudies.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<PrevStudiesVo> save(List<PrevStudiesVo> vos) {
        List<PrevStudiesVo> prevStudiesVos = new ArrayList<PrevStudiesVo>();
        for (PrevStudiesVo vo : vos) {
            PrevStudies prevStudies = toPrevStudies(vo);
            prevStudies = prevStudiesRepository.save(prevStudies);
            vo.setId(prevStudies.getId());
            prevStudiesVos.add(vo);
        }
        return prevStudiesVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        PrevStudies prevStudies = prevStudiesRepository.getOne(id);
        prevStudies.setIsDeleted(true);
        prevStudiesRepository.save(prevStudies);
    }

    @Transactional
    @Override
    public void delete(PrevStudiesVo vo) {
        prevStudiesRepository.delete(toPrevStudies(vo));
    }

    @Override
    public List<PrevStudiesVo> findList() {
        List<PrevStudies> prevStudiesList = prevStudiesRepository.findAll();
        List<PrevStudiesVo> vos = new ArrayList<PrevStudiesVo>();
        for (PrevStudies prevStudies : prevStudiesList) {
            vos.add(toVo(prevStudies));
        }
        return vos;
    }

    @Override
    public List<PrevStudiesVo> findListByApplicationId(String applicationId) {
        List<PrevStudies> prevStudiesList = prevStudiesRepository.findListByApplicationId(applicationId);
        List<PrevStudiesVo> vos = new ArrayList<PrevStudiesVo>();
        for (PrevStudies prevStudies : prevStudiesList) {
            vos.add(toVo(prevStudies));
        }
        return vos;
    }

    private PrevStudies toPrevStudies(PrevStudiesVo vo) {
        PrevStudies prevStudies = new PrevStudies();
        prevStudies.setId(vo.getId());
        prevStudies.setApplicationId(vo.getApplicationId());
        prevStudies.setInstitutionId(vo.getInstitutionId());
        prevStudies.setProgrammeTypeCd(vo.getProgrammeTypeCd());
        prevStudies.setProgrammeTitle(vo.getProgrammeTitle());
        prevStudies.setCountryId(vo.getCountryId());
        prevStudies.setFinalCumulativeGpa(vo.getFinalCumulativeGpa());
        prevStudies.setMaxGpa(vo.getMaxGpa());
        prevStudies.setStudyModeCd(vo.getStudyModeCd());
        prevStudies.setIsDeleted(vo.getIsDeleted());
        prevStudies.setProvinceId(vo.getProvinceId());
        prevStudies.setCityId(vo.getCityId());
        prevStudies.setStudyCountryOthers(vo.getStudyCountryOthers());
        prevStudies.setInstitutionOthers(vo.getInstitutionOthers());
        prevStudies.setProgTypeOthers(vo.getProgTypeOthers());
        prevStudies.setStudyFrom(vo.getStudyFrom());
        prevStudies.setStudyTo(vo.getStudyTo());
        prevStudies.setNotGpa(vo.getNotGpa());
        prevStudies.setFinalRslt(vo.getFinalRslt());
        prevStudies.setDateOfAward(vo.getDateOfAward());
        prevStudies.setCreateBy(vo.getCreateBy());
        prevStudies.setCreateDate(vo.getCreateDate());
        prevStudies.setUpdateBy(vo.getUpdateBy());
        prevStudies.setUpdateDate(vo.getUpdateDate());
        prevStudies.setVersion(vo.getVersion());
        prevStudies.setTypeOfEducation(vo.getTypeOfEducation());
        prevStudies.setIsCompletedStudy(vo.getIsCompletedStudy());
        return prevStudies;
    }

    private PrevStudiesVo toVo(PrevStudies prevStudies) {
        PrevStudiesVo vo = new PrevStudiesVo();
        vo.setId(prevStudies.getId());
        vo.setApplicationId(prevStudies.getApplicationId());
        vo.setInstitutionId(prevStudies.getInstitutionId());
        vo.setProgrammeTypeCd(prevStudies.getProgrammeTypeCd());
        vo.setProgrammeTitle(prevStudies.getProgrammeTitle());
        vo.setCountryId(prevStudies.getCountryId());
        vo.setFinalCumulativeGpa(prevStudies.getFinalCumulativeGpa());
        vo.setMaxGpa(prevStudies.getMaxGpa());
        vo.setStudyModeCd(prevStudies.getStudyModeCd());
        vo.setCreateBy(prevStudies.getCreateBy());
        vo.setUpdateBy(prevStudies.getUpdateBy());
        vo.setCreateDate(prevStudies.getCreateDate());
        vo.setUpdateDate(prevStudies.getUpdateDate());
        vo.setVersion(prevStudies.getVersion());
        vo.setIsDeleted(prevStudies.getIsDeleted());
        if (StringUtils.isNotBlank(vo.getCountryId())) {
            vo.setCountryVo(countryService.get(vo.getCountryId()));
        }
        vo.setProvinceId(prevStudies.getProvinceId());
        vo.setCityId(prevStudies.getCityId());
        vo.setStudyCountryOthers(prevStudies.getStudyCountryOthers());
        vo.setInstitutionOthers(prevStudies.getInstitutionOthers());
        vo.setProgTypeOthers(prevStudies.getProgTypeOthers());
        vo.setStudyFrom(prevStudies.getStudyFrom());
        vo.setStudyTo(prevStudies.getStudyTo());
        vo.setNotGpa(prevStudies.getNotGpa());
        vo.setFinalRslt(prevStudies.getFinalRslt());
        if (StringUtils.isNotBlank(vo.getInstitutionId())) {
            vo.setInstitutionVo(institutionService.get(vo.getInstitutionId()));
        }
        vo.setDateOfAward(prevStudies.getDateOfAward());
        vo.setTypeOfEducation(prevStudies.getTypeOfEducation());
        vo.setIsCompletedStudy(prevStudies.getIsCompletedStudy());
        if (StringUtils.isNotBlank(prevStudies.getProgrammeTypeCd())) {
            if (Constants.OTHERS.equals(prevStudies.getProgrammeTypeCd())) {
                vo.setTypeOfProg(prevStudies.getProgTypeOthers());
            } else {
                RefCdVo refCdVo = refCdService.getByTypeAndCd(Constants.PROGRAMME_TYPE,
                        prevStudies.getProgrammeTypeCd());
                vo.setTypeOfProg(refCdVo.getValue());
            }
        }
        if (StringUtils.isNotBlank(prevStudies.getStudyModeCd())) {
            vo.setStudyMode(refCdService.getByTypeAndCd(Constants.STUDY_MODE, prevStudies.getStudyModeCd()).getValue());
        }
        return vo;
    }
}
