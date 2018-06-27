package com.accentrix.hku.service.app.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.app.Institution;
import com.accentrix.hku.repository.app.InstitutionRepository;
import com.accentrix.hku.service.app.InstitutionService;
import com.accentrix.hku.vo.app.InstitutionVo;
import com.accentrix.hku.timer.annotation.Timer;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月25日 下午1:41:36
 * @version 1.0
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("institution")
public class InstitutionServiceImpl implements InstitutionService {

    @Autowired
    private InstitutionRepository institutionRepository;

    @Override
    public InstitutionVo get(String id) {
        Institution institution = institutionRepository.getOne(id);
        return toVo(institution);
    }

    @Transactional
    @Override
    public InstitutionVo save(InstitutionVo vo) {
        Institution institution = toInstitution(vo);
        institution = institutionRepository.save(institution);
        vo.setId(institution.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<InstitutionVo> save(List<InstitutionVo> vos) {
        List<InstitutionVo> institutionVos = new ArrayList<InstitutionVo>();
        for (InstitutionVo vo : vos) {
            Institution institution = toInstitution(vo);
            institution = institutionRepository.save(institution);
            vo.setId(institution.getId());
            institutionVos.add(vo);
        }
        return institutionVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        Institution institution = institutionRepository.getOne(id);
        institutionRepository.delete(institution);
    }

    @Transactional
    @Override
    public void delete(InstitutionVo vo) {
        institutionRepository.delete(toInstitution(vo));
    }

    @Override
    public List<InstitutionVo> findList() {
        List<Institution> institutions = institutionRepository.findAll();
        List<InstitutionVo> vos = new ArrayList<InstitutionVo>();
        for (Institution institution : institutions) {
            vos.add(toVo(institution));
        }
        return vos;
    }

    @Override
    public List<InstitutionVo> findInstitutions(String countryId, String provinceId, String cityId) {
        List<Institution> institutions = institutionRepository.findInstitutions(countryId, provinceId, cityId);
        List<InstitutionVo> vos = new ArrayList<InstitutionVo>();
        for (Institution institution : institutions) {
            vos.add(toVo(institution));
        }
        return vos;
    }

    private Institution toInstitution(InstitutionVo vo) {
        Institution institution = new Institution();
        institution.setId(vo.getId());
        institution.setCode(vo.getCode());
        institution.setDescription(vo.getDescription());
        institution.setCountryId(vo.getCountryId());
        institution.setProvinceId(vo.getProvinceId());
        institution.setCityId(vo.getCityId());
        return institution;
    }

    private InstitutionVo toVo(Institution institution) {
        InstitutionVo vo = new InstitutionVo();
        vo.setId(institution.getId());
        vo.setCode(institution.getCode());
        vo.setDescription(institution.getDescription());
        vo.setCountryId(institution.getCountryId());
        vo.setProvinceId(institution.getProvinceId());
        vo.setCityId(institution.getCityId());
        return vo;
    }

}
