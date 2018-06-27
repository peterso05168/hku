package com.accentrix.hku.service.campaign.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.campaign.Mapping;
import com.accentrix.hku.repository.campaign.MappingRepository;
import com.accentrix.hku.service.campaign.MappingService;
import com.accentrix.hku.service.cpc.CityService;
import com.accentrix.hku.service.cpc.ProvinceService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.campaign.MappingVo;
import com.accentrix.hku.vo.cpc.CityVo;
import com.accentrix.hku.vo.cpc.ProvinceVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年4月9日 下午2:32:09
 * @version 1.0
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("mapping")
public class MappingServiceImpl implements MappingService {

    @Autowired
    private MappingRepository mappingRepository;
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private CityService cityService;

    @Override
    public MappingVo get(String id) {
        Mapping mapping = mappingRepository.findOne(id);
        return toVo(mapping);
    }

    @Transactional
    @Override
    public MappingVo save(MappingVo vo) {
        Mapping mapping = toMapping(vo);
        mapping = mappingRepository.save(mapping);
        vo.setId(mapping.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<MappingVo> save(List<MappingVo> vos) {
        List<MappingVo> mappingVos = new ArrayList<MappingVo>();
        for (MappingVo mappingVo : vos) {
            Mapping mapping = toMapping(mappingVo);
            System.out.println(mapping.getInterviewCityId());
            System.out.println(mapping.getInterviewProvinceId());
            mapping = mappingRepository.save(mapping);
            mappingVo.setId(mapping.getId());
            mappingVos.add(mappingVo);
        }
        return mappingVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        mappingRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(MappingVo vo) {
        Mapping mapping = toMapping(vo);
        mappingRepository.delete(mapping);
    }

    @Override
    public List<MappingVo> findList() {
        List<Mapping> list = mappingRepository.findAll();
        List<MappingVo> vos = new ArrayList<MappingVo>();
        for (Mapping mapping : list) {
            vos.add(toVo(mapping));
        }
        return vos;
    }

    private MappingVo toVo(Mapping mapping) {
        MappingVo vo = new MappingVo();
        vo.setId(mapping.getId());
        vo.setInterviewCityId(mapping.getInterviewCityId());
        vo.setInterviewProvinceId(mapping.getInterviewProvinceId());
        vo.setStudyCityId(mapping.getStudyCityId());
        vo.setStudyProvinceId(mapping.getStudyProvinceId());
        return vo;
    }

    private Mapping toMapping(MappingVo vo) {
        Mapping mapping = new Mapping();
        mapping.setId(vo.getId());
        mapping.setInterviewCityId(StringUtils.isNotBlank(vo.getInterviewCityId()) ? vo.getInterviewCityId() : null);
        mapping.setInterviewProvinceId(
                StringUtils.isNotBlank(vo.getInterviewProvinceId()) ? vo.getInterviewProvinceId() : null);
        mapping.setStudyCityId(vo.getStudyCityId());
        mapping.setStudyProvinceId(vo.getStudyProvinceId());
        return mapping;

    }

    @Override
    public List<MappingVo> findMappingList(String countryId, String provinceId) {
        List<Mapping> list = mappingRepository.findByCountryIdAndProvinceId(countryId, provinceId);
        List<MappingVo> vos = new ArrayList<MappingVo>();
        for (Mapping mapping : list) {
            MappingVo vo = toVo(mapping);
            if (StringUtils.isNotBlank(vo.getStudyProvinceId())) {
                ProvinceVo provinceVo = provinceService.get(vo.getStudyProvinceId());
                vo.setStudyProvinceOrCityName(provinceVo.getDescription());
            } else if (StringUtils.isNotBlank(vo.getStudyCityId())) {
                CityVo cityVo = cityService.get(vo.getStudyCityId());
                vo.setStudyProvinceOrCityName(cityVo.getDescription());
            }
            vos.add(vo);
        }
        return vos;
    }
}
