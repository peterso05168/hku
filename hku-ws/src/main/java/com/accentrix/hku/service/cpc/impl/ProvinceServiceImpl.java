package com.accentrix.hku.service.cpc.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.cpc.Province;
import com.accentrix.hku.repository.cpc.ProvinceRepository;
import com.accentrix.hku.service.cpc.ProvinceService;
import com.accentrix.hku.vo.cpc.ProvinceCityVo;
import com.accentrix.hku.vo.cpc.ProvinceVo;
import com.accentrix.hku.timer.annotation.Timer;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月25日 下午1:40:46
 * @version 1.0
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("province")
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceRepository provinceRepository;

    @Override
    public ProvinceVo get(String id) {
        Province province = provinceRepository.getOne(id);
        return toVo(province);
    }

    @Transactional
    @Override
    public ProvinceVo save(ProvinceVo vo) {
        Province province = toProvince(vo);
        province = provinceRepository.save(province);
        vo.setId(province.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<ProvinceVo> save(List<ProvinceVo> vos) {
        List<ProvinceVo> provinceVos = new ArrayList<ProvinceVo>();
        for (ProvinceVo provinceVo : vos) {
            Province province = toProvince(provinceVo);
            province = provinceRepository.save(province);
            provinceVo.setId(province.getId());
            provinceVos.add(provinceVo);
        }
        return provinceVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        Province province = provinceRepository.getOne(id);
        provinceRepository.delete(province);
    }

    @Transactional
    @Override
    public void delete(ProvinceVo vo) {
        provinceRepository.delete(toProvince(vo));
    }

    @Override
    public List<ProvinceVo> findList() {
        List<Province> provinces = provinceRepository.findAll();
        List<ProvinceVo> vos = new ArrayList<ProvinceVo>();
        for (Province province : provinces) {
            vos.add(toVo(province));
        }
        sortProvinces(vos);
        return vos;
    }

    @Override
    public List<ProvinceVo> findByCountryId(String countryId) {
        List<Province> provinces = provinceRepository.findByCountryId(countryId);
        List<ProvinceVo> vos = new ArrayList<ProvinceVo>();
        for (Province province : provinces) {
            vos.add(toVo(province));
        }
        sortProvinces(vos);
        return vos;
    }

    private Province toProvince(ProvinceVo vo) {
        Province province = new Province();
        province.setCode(vo.getCode());
        province.setDescription(vo.getDescription());
        province.setDescriptionChinese(vo.getDescriptionChinese());
        province.setCountryId(vo.getCountryId());
        return province;
    }

    private ProvinceVo toVo(Province province) {
        ProvinceVo vo = new ProvinceVo();
        if (province != null) {
            vo.setId(province.getId());
            vo.setCode(province.getCode());
            vo.setDescription(province.getDescription());
            vo.setDescriptionChinese(province.getDescriptionChinese());
            vo.setCountryId(province.getCountryId());
        }
        return vo;
    }

    @Override
    public List<ProvinceCityVo> findByCId(String countryId) {
        List<Province> provinces = provinceRepository.findByCountryId(countryId);
        List<ProvinceCityVo> vos = new ArrayList<ProvinceCityVo>();
        for (Province province : provinces) {
            ProvinceCityVo vo = new ProvinceCityVo();
            vo.setId(province.getId());
            vo.setCode(province.getCode());
            vo.setDescription(province.getDescription());
            vo.setDescriptionChinese(province.getDescriptionChinese());
            vos.add(vo);
        }
        return vos;
    }

    public void sortProvinces(List<ProvinceVo> provinces) {
        Collections.sort(provinces, new Comparator<ProvinceVo>() {
            @Override
            public int compare(ProvinceVo o1, ProvinceVo o2) {
                return o1.getDescription().compareTo(o2.getDescription());
            }
        });
    }
}
