package com.accentrix.hku.service.app.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.app.HkuProgramme;
import com.accentrix.hku.repository.app.HkuProgrammeRepository;
import com.accentrix.hku.service.app.HkuProgrammeService;
import com.accentrix.hku.vo.app.HkuProgrammeVo;
import com.accentrix.hku.timer.annotation.Timer;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 下午2:21:43
 * @version 1.0
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("hkuProgramme")
public class HkuProgrammeServiceImpl implements HkuProgrammeService {

    @Autowired
    private HkuProgrammeRepository hkuProgrammeRepository;

    @Override
    public HkuProgrammeVo get(String id) {
        HkuProgramme hkuProgramme = hkuProgrammeRepository.findOne(id);
        return toVo(hkuProgramme);
    }

    @Transactional
    @Override
    public HkuProgrammeVo save(HkuProgrammeVo vo) {
        HkuProgramme hkuProgramme = toHkuProgramme(vo);
        hkuProgramme = hkuProgrammeRepository.save(hkuProgramme);
        vo.setId(hkuProgramme.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<HkuProgrammeVo> save(List<HkuProgrammeVo> vos) {
        List<HkuProgrammeVo> hkuProgrammeVos = new ArrayList<HkuProgrammeVo>();
        for (HkuProgrammeVo hkuProgrammeVo : vos) {
            HkuProgramme hkuProgramme = toHkuProgramme(hkuProgrammeVo);
            hkuProgramme = hkuProgrammeRepository.save(hkuProgramme);
            hkuProgrammeVo.setId(hkuProgramme.getId());
            hkuProgrammeVos.add(hkuProgrammeVo);
        }
        return hkuProgrammeVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        hkuProgrammeRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(HkuProgrammeVo vo) {
        hkuProgrammeRepository.delete(toHkuProgramme(vo));
    }

    @Override
    public List<HkuProgrammeVo> findList() {
        List<HkuProgramme> hkuProgrammes = hkuProgrammeRepository.findAll();
        List<HkuProgrammeVo> vos = new ArrayList<HkuProgrammeVo>();
        for (HkuProgramme hkuProgramme : hkuProgrammes) {
            vos.add(toVo(hkuProgramme));
        }
        return vos;
    }

    private HkuProgramme toHkuProgramme(HkuProgrammeVo vo) {
        HkuProgramme hkuProgramme = new HkuProgramme();
        hkuProgramme.setId(vo.getId());
        hkuProgramme.setHkuProgrammeCd(vo.getHkuProgrammeCd());
        hkuProgramme.setHkuProgrammeDesc(vo.getHkuProgrammeDesc());
        hkuProgramme.setHkuProgrammeDescChi(vo.getHkuProgrammeDescChi());
        hkuProgramme.setAcademicYear(vo.getAcademicYear());
        hkuProgramme.setEnabled(vo.getEnabled());
        hkuProgramme.setFacultyCd(vo.getFacultyCd());
        return hkuProgramme;
    }

    private HkuProgrammeVo toVo(HkuProgramme hkuProgramme) {
        HkuProgrammeVo vo = new HkuProgrammeVo();
        if (hkuProgramme != null) {
            vo.setId(hkuProgramme.getId());
            vo.setHkuProgrammeCd(hkuProgramme.getHkuProgrammeCd());
            vo.setHkuProgrammeDesc(hkuProgramme.getHkuProgrammeDesc());
            vo.setHkuProgrammeDescChi(hkuProgramme.getHkuProgrammeDescChi());
            vo.setAcademicYear(hkuProgramme.getAcademicYear());
            vo.setEnabled(hkuProgramme.getEnabled());
            vo.setFacultyCd(hkuProgramme.getFacultyCd());
        }
        return vo;
    }

    @Override
    public List<HkuProgrammeVo> findVos(String staffId) {
        return hkuProgrammeRepository.findVos(staffId);
    }

    @Override
    public List<String> findFacultyCd() {
        return hkuProgrammeRepository.findFacultyCd();
    }

    @Override
    public List<HkuProgrammeVo> findByFacultyCd(String facultyCd) {
        List<HkuProgramme> hkuProgrammes = hkuProgrammeRepository.findByFacultyCd(facultyCd);
        List<HkuProgrammeVo> vos = new ArrayList<HkuProgrammeVo>();
        for (HkuProgramme hkuProgramme : hkuProgrammes) {
            vos.add(toVo(hkuProgramme));
        }
        return vos;
    }

}
