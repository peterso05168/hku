package com.accentrix.hku.service.cpc.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.cpc.Country;
import com.accentrix.hku.repository.cpc.CountryRepository;
import com.accentrix.hku.service.cpc.CountryService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.cpc.CountryVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月25日 下午1:40:28
 * @version 1.0
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("country")
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public CountryVo get(String id) {
        Country country = countryRepository.getOne(id);
        return toVo(country);
    }

    @Transactional
    @Override
    public CountryVo save(CountryVo vo) {
        Country country = toCountry(vo);
        country = countryRepository.save(country);
        vo.setId(country.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<CountryVo> save(List<CountryVo> vos) {
        List<CountryVo> countryVos = new ArrayList<CountryVo>();
        for (CountryVo vo : vos) {
            Country country = toCountry(vo);
            country = countryRepository.save(country);
            vo.setId(country.getId());
            countryVos.add(vo);
        }
        return countryVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        Country country = countryRepository.getOne(id);
        countryRepository.delete(country);
    }

    @Transactional
    @Override
    public void delete(CountryVo vo) {
        countryRepository.delete(toCountry(vo));
    }

    @Override
    public List<CountryVo> findList() {
        List<Country> countries = countryRepository.findAll();
        List<CountryVo> vos = new ArrayList<CountryVo>();
        for (Country country : countries) {
            vos.add(toVo(country));
        }
        sortCountrys(vos);
        return vos;
    }

    @Override
    public CountryVo getByDesc(String description) {
        Country country = countryRepository.getByDesc(description);
        return toVo(country);
    }

    private Country toCountry(CountryVo vo) {
        Country country = new Country();
        country.setCode(vo.getCode());
        country.setDescription(vo.getDescription());
        country.setDescriptionChinese(vo.getDescriptionChinese());
        return country;
    }

    private CountryVo toVo(Country country) {
        CountryVo vo = new CountryVo();
        vo.setId(country.getId());
        vo.setCode(country.getCode());
        vo.setDescription(country.getDescription());
        vo.setDescriptionChinese(country.getDescriptionChinese());
        return vo;
    }

    @Override
    public CountryVo getByCode(String code) {
        Country country = countryRepository.getByCode(code);
        return toVo(country);
    }

    public void sortCountrys(List<CountryVo> countrys) {
        Collections.sort(countrys, new Comparator<CountryVo>() {
            @Override
            public int compare(CountryVo o1, CountryVo o2) {
                return o1.getDescription().compareTo(o2.getDescription());
            }
        });
    }
}
