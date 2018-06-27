package com.accentrix.hku.service.campaign.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.campaign.Centre;
import com.accentrix.hku.repository.campaign.CentreRepository;
import com.accentrix.hku.service.campaign.CentreService;
import com.accentrix.hku.service.campaign.CpgnSessionService;
import com.accentrix.hku.service.cpc.CityService;
import com.accentrix.hku.service.cpc.ProvinceService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.campaign.CentreVo;
import com.accentrix.hku.vo.campaign.CpgnSessionVo;
import com.accentrix.hku.vo.cpc.CityVo;
import com.accentrix.hku.vo.cpc.ProvinceVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年4月9日 下午2:29:47
 * @version 1.0
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("centre")
public class CentreServiceImpl implements CentreService {

    @Autowired
    private CentreRepository centreRepository;
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private CityService cityService;
    @Autowired
    private CpgnSessionService cpgnSessionService;

    @Override
    public CentreVo get(String id) {
        Centre centre = centreRepository.findOne(id);
        return toVo(centre);
    }

    @Transactional
    @Override
    public CentreVo save(CentreVo vo) {
        Centre centre = toCentre(vo);
        centre = centreRepository.save(centre);
        vo.setId(centre.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<CentreVo> save(List<CentreVo> vos) {
        List<CentreVo> centreVos = new ArrayList<CentreVo>();
        for (CentreVo centreVo : vos) {
            Centre centre = toCentre(centreVo);
            centre = centreRepository.save(centre);
            centreVo.setId(centre.getId());
            centreVos.add(centreVo);
        }
        return centreVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        centreRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(CentreVo vo) {
        Centre centre = toCentre(vo);
        centreRepository.delete(centre);
    }

    @Override
    public List<CentreVo> findList() {
        List<Centre> list = centreRepository.findAll();
        List<CentreVo> vos = new ArrayList<CentreVo>();
        for (Centre centre : list) {
            vos.add(toVo(centre));
        }
        return vos;
    }

    private CentreVo toVo(Centre centre) {
        CentreVo vo = new CentreVo();
        vo.setId(centre.getId());
        vo.setCityId(centre.getCityId());
        vo.setCentreAddr(centre.getCentreAddr());
        vo.setCentreName(centre.getCentreName());
        vo.setCpgnId(centre.getCpgnId());
        vo.setProvinceId(centre.getProvinceId());
        vo.setMapped(centre.getMapped());
        return vo;
    }

    private Centre toCentre(CentreVo vo) {
        Centre centre = new Centre();
        centre.setId(vo.getId());
        centre.setCityId(StringUtils.isNotBlank(vo.getCityId()) ? vo.getCityId() : null);
        centre.setCentreAddr(vo.getCentreAddr());
        centre.setCentreName(vo.getCentreName());
        centre.setCpgnId(StringUtils.isNotBlank(vo.getCpgnId()) ? vo.getCpgnId() : null);
        centre.setProvinceId(StringUtils.isNotBlank(vo.getProvinceId()) ? vo.getProvinceId() : null);
        centre.setMapped(vo.getMapped());
        return centre;
    }

    @Override
    public List<CentreVo> findByCpgnId(String cpgnId) {
        List<Centre> list = centreRepository.findByCpgnId(cpgnId);
        List<CentreVo> vos = new ArrayList<CentreVo>();
        for (Centre centre : list) {
            CentreVo vo = toVo(centre);
            if (StringUtils.isNotBlank(centre.getProvinceId())) {
                ProvinceVo provinceVo = provinceService.get(centre.getProvinceId());
                vo.setProvinceOrCityName(provinceVo.getDescription());
            } else if (StringUtils.isNotBlank(centre.getCityId())) {
                CityVo cityVo = cityService.get(centre.getCityId());
                vo.setProvinceOrCityName(cityVo.getDescription());
            }
            vos.add(vo);
            List<CpgnSessionVo> cpgnSessionVos = cpgnSessionService.findByCentreId(vo.getId());
            for (CpgnSessionVo cpgnSessionVo : cpgnSessionVos) {
                CentreVo centreVo = new CentreVo();
                centreVo.setCpgnSessionId(cpgnSessionVo.getId());
                centreVo.setSessionDatetime(cpgnSessionVo.getSessionDatetime());
                centreVo.setDayName(cpgnSessionVo.getDayName());
                centreVo.setAssignedQuota(cpgnSessionVo.getAssignedQuota());
                centreVo.setReservedQuota(cpgnSessionVo.getReservedQuota());
                vos.add(centreVo);
            }
        }
        return vos;
    }

    @Override
    public List<CentreVo> findByCpgnIdAndProvinceOrCity(String cpgnId, String type) {
        return centreRepository.findByCpgnIdAndProvinceOrCity(cpgnId, type);
    }

    @Override
    public List<CentreVo> findByType(String type, String provinceOrCity, String cpgnId) {
        List<Centre> list = centreRepository.findByType(type, provinceOrCity, cpgnId);
        List<CentreVo> vos = new ArrayList<CentreVo>();
        for (Centre centre : list) {
            vos.add(toVo(centre));
        }
        return vos;
    }

    @Override
    public List<CentreVo> findByIds(List<String> ids, String type, String provinceOrCity, String cpgnId) {
        List<Centre> list = centreRepository.findByIds(ids, type, provinceOrCity, cpgnId);
        List<CentreVo> vos = new ArrayList<CentreVo>();
        for (Centre centre : list) {
            vos.add(toVo(centre));
        }
        return vos;
    }

    @Override
    public List<CentreVo> findByIdList(List<String> ids) {
        List<Centre> list = centreRepository.findByIdList(ids);
        List<CentreVo> vos = new ArrayList<CentreVo>();
        for (Centre centre : list) {
            vos.add(toVo(centre));
        }
        return vos;
    }

}
