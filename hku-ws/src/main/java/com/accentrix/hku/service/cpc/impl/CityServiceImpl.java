package com.accentrix.hku.service.cpc.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.cpc.City;
import com.accentrix.hku.repository.cpc.CityRepository;
import com.accentrix.hku.service.cpc.CityService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.cpc.CityVo;
import com.accentrix.hku.vo.cpc.ProvinceCityVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月25日 下午1:41:03
 * @version 1.0
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("city")
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public CityVo get(String id) {
        City city = cityRepository.getOne(id);
        return toVo(city);
    }

    @Transactional
    @Override
    public CityVo save(CityVo vo) {
        City city = toCity(vo);
        city = cityRepository.save(city);
        vo.setId(city.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<CityVo> save(List<CityVo> vos) {
        List<CityVo> cityVos = new ArrayList<CityVo>();
        for (CityVo cityVo : vos) {
            City city = toCity(cityVo);
            city = cityRepository.save(city);
            cityVo.setId(city.getId());
            cityVos.add(cityVo);
        }
        return cityVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        cityRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(CityVo vo) {
        cityRepository.delete(toCity(vo));
    }

    @Override
    public List<CityVo> findList() {
        List<City> cities = cityRepository.findAll();
        List<CityVo> vos = new ArrayList<CityVo>();
        for (City city : cities) {
            vos.add(toVo(city));
        }
        sortCities(vos);
        return vos;
    }

    @Override
    public List<CityVo> findByCountryIdOrProvinceId(String countryId, String provinceId) {
        List<City> cities = cityRepository.findByCountryIdOrProvinceId(countryId, provinceId);
        List<CityVo> vos = new ArrayList<CityVo>();
        for (City city : cities) {
            vos.add(toVo(city));
        }
        sortCities(vos);
        return vos;
    }

    private City toCity(CityVo vo) {
        City city = new City();
        city.setCode(vo.getCode());
        city.setDescription(vo.getDescription());
        city.setDescriptionChinese(vo.getDescriptionChinese());
        city.setCountryId(vo.getCountryId());
        city.setProvinceId(vo.getProvinceId());
        return city;
    }

    private CityVo toVo(City city) {
        CityVo vo = new CityVo();
        if (city != null) {
            vo.setId(city.getId());
            vo.setCode(city.getCode());
            vo.setDescription(city.getDescription());
            vo.setDescriptionChinese(city.getDescriptionChinese());
            vo.setCountryId(city.getCountryId());
            vo.setProvinceId(city.getProvinceId());
        }
        return vo;
    }

    @Override
    public List<ProvinceCityVo> findByCountryId(String countryId) {
        List<City> cities = cityRepository.findByCountryIdOrProvinceId(countryId, null);
        List<ProvinceCityVo> vos = new ArrayList<ProvinceCityVo>();
        for (City city : cities) {
            ProvinceCityVo vo = new ProvinceCityVo();
            vo.setId(city.getId());
            vo.setCode(city.getCode());
            vo.setDescription(city.getDescription());
            vo.setDescriptionChinese(city.getDescriptionChinese());
            vos.add(vo);
        }
        return vos;
    }

    public void sortCities(List<CityVo> cities) {
        Collections.sort(cities, new Comparator<CityVo>() {
            @Override
            public int compare(CityVo o1, CityVo o2) {
                return o1.getDescription().compareTo(o2.getDescription());
            }
        });
    }
}
