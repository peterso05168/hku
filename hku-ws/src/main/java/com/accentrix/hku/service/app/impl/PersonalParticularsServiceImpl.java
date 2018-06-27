package com.accentrix.hku.service.app.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.app.PersonalParticulars;
import com.accentrix.hku.repository.app.PersonalParticularsRepository;
import com.accentrix.hku.service.app.PersonalParticularsService;
import com.accentrix.hku.vo.app.PersonalParticularsVo;
import com.accentrix.hku.timer.annotation.Timer;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 下午2:22:59
 * @version 1.0
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("personalParticulars")
public class PersonalParticularsServiceImpl implements PersonalParticularsService {

    @Autowired
    private PersonalParticularsRepository personalParticularsRepository;

    @Override
    public PersonalParticularsVo get(String id) {
        PersonalParticulars personalParticulars = personalParticularsRepository.findOne(id);
        return toVo(personalParticulars);
    }

    @Transactional
    @Override
    public PersonalParticularsVo save(PersonalParticularsVo vo) {
        PersonalParticulars personalParticulars = toPersonalParticulars(vo);
        personalParticulars = personalParticularsRepository.save(personalParticulars);
        vo.setId(personalParticulars.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<PersonalParticularsVo> save(List<PersonalParticularsVo> vos) {
        List<PersonalParticularsVo> personalParticularsVos = new ArrayList<PersonalParticularsVo>();
        for (PersonalParticularsVo vo : vos) {
            PersonalParticulars personalParticulars = toPersonalParticulars(vo);
            personalParticulars = personalParticularsRepository.save(personalParticulars);
            vo.setId(personalParticulars.getId());
            personalParticularsVos.add(vo);
        }
        return personalParticularsVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        personalParticularsRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(PersonalParticularsVo vo) {
        personalParticularsRepository.delete(toPersonalParticulars(vo));
    }

    @Override
    public List<PersonalParticularsVo> findList() {
        List<PersonalParticulars> personalParticularsList = personalParticularsRepository.findAll();
        List<PersonalParticularsVo> vos = new ArrayList<PersonalParticularsVo>();
        for (PersonalParticulars personalParticulars : personalParticularsList) {
            vos.add(toVo(personalParticulars));
        }
        return vos;
    }

    private PersonalParticulars toPersonalParticulars(PersonalParticularsVo vo) {
        PersonalParticulars personalParticulars = new PersonalParticulars();
        personalParticulars.setId(vo.getId());
        personalParticulars.setEmail(vo.getEmail());
        personalParticulars.setSurname(vo.getSurname());
        personalParticulars.setGivenName(vo.getGivenName());
        personalParticulars.setChineseName(vo.getChineseName());
        personalParticulars.setDateOfBirth(vo.getDateOfBirth());
        personalParticulars.setSex(vo.getSex());
        personalParticulars.setNationalityCountryId(vo.getNationalityCountryId());
        personalParticulars.setResidenceProvinceId(vo.getResidenceProvinceId());
        personalParticulars.setResidenceCityId(vo.getResidenceCityId());
        personalParticulars.setResidenceCountryId(vo.getResidenceCountryId());
        personalParticulars.setHomeTelNoId(vo.getHomeTelNoId());
        personalParticulars.setMobileTelNoId(vo.getMobileTelNoId());
        personalParticulars.setReqVisaInd(vo.getReqVisaInd());
        personalParticulars.setCorrespondenceAddr(vo.getCorrespondenceAddr());
        personalParticulars.setHkid(vo.getHkid());
        personalParticulars.setPassportNo(vo.getPassportNo());
        personalParticulars.setNationalIdCard(vo.getNationalIdCard());
        personalParticulars.setNotProvideId(vo.getNotProvideId());
        personalParticulars.setCreateBy(vo.getCreateBy());
        personalParticulars.setUpdateBy(vo.getUpdateBy());
        personalParticulars.setCreateDate(vo.getCreateDate());
        personalParticulars.setUpdateDate(vo.getUpdateDate());
        personalParticulars.setVersion(vo.getVersion());
        personalParticulars.setRegion(vo.getRegion());
        return personalParticulars;
    }

    private PersonalParticularsVo toVo(PersonalParticulars personalParticulars) {
        PersonalParticularsVo vo = new PersonalParticularsVo();
        if (personalParticulars != null) {
            vo.setId(personalParticulars.getId());
            vo.setEmail(personalParticulars.getEmail());
            vo.setSurname(personalParticulars.getSurname());
            vo.setGivenName(personalParticulars.getGivenName());
            vo.setChineseName(personalParticulars.getChineseName());
            vo.setDateOfBirth(personalParticulars.getDateOfBirth());
            vo.setSex(personalParticulars.getSex());
            vo.setNationalityCountryId(personalParticulars.getNationalityCountryId());
            vo.setResidenceProvinceId(personalParticulars.getResidenceProvinceId());
            vo.setResidenceCityId(personalParticulars.getResidenceCityId());
            vo.setResidenceCountryId(personalParticulars.getResidenceCountryId());
            vo.setHomeTelNoId(personalParticulars.getHomeTelNoId());
            vo.setMobileTelNoId(personalParticulars.getMobileTelNoId());
            vo.setReqVisaInd(personalParticulars.getReqVisaInd());
            vo.setCorrespondenceAddr(personalParticulars.getCorrespondenceAddr());
            vo.setHkid(personalParticulars.getHkid());
            vo.setPassportNo(personalParticulars.getPassportNo());
            vo.setNationalIdCard(personalParticulars.getNationalIdCard());
            vo.setCreateBy(personalParticulars.getCreateBy());
            vo.setUpdateBy(personalParticulars.getUpdateBy());
            vo.setCreateDate(personalParticulars.getCreateDate());
            vo.setUpdateDate(personalParticulars.getUpdateDate());
            vo.setVersion(personalParticulars.getVersion());
            vo.setNotProvideId(personalParticulars.getNotProvideId());
            vo.setRegion(personalParticulars.getRegion());
        }
        return vo;
    }

}
