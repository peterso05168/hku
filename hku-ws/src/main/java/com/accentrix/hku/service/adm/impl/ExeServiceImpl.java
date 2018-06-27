package com.accentrix.hku.service.adm.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.adm.Exe;
import com.accentrix.hku.repository.adm.ExeRepository;
import com.accentrix.hku.service.adm.ExeService;
import com.accentrix.hku.vo.adm.ExeVo;
import com.accentrix.hku.timer.annotation.Timer;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 下午2:17:57
 * @version 1.0
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("exe")
public class ExeServiceImpl implements ExeService {

    @Autowired
    private ExeRepository exeRepository;

    @Override
    public ExeVo get(String id) {
        Exe exe = exeRepository.findOne(id);
        return toVo(exe);
    }

    @Transactional
    @Override
    public ExeVo save(ExeVo vo) {
        Exe exe = toExe(vo);
        exe = exeRepository.save(exe);
        vo.setId(exe.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<ExeVo> save(List<ExeVo> vos) {
        List<ExeVo> exeVos = new ArrayList<ExeVo>();
        for (ExeVo exeVo : vos) {
            Exe exe = toExe(exeVo);
            exe = exeRepository.save(exe);
            exeVo.setId(exe.getId());
            exeVos.add(exeVo);
        }
        return exeVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        exeRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(ExeVo vo) {
        Exe exe = toExe(vo);
        exeRepository.delete(exe);
    }

    @Override
    public List<ExeVo> findList() {
        List<ExeVo> exeVos = new ArrayList<ExeVo>();
        List<Exe> exes = exeRepository.findAll();
        for (Exe exe : exes) {
            exeVos.add(toVo(exe));
        }
        return exeVos;
    }

    private Exe toExe(ExeVo vo) {
        Exe exe = new Exe();
        exe.setId(vo.getId());
        exe.setAdmissionYear(vo.getAdmissionYear());
        exe.setApplicationStartDate(vo.getApplicationStartDate());
        exe.setApplicationEndDate(vo.getApplicationEndDate());
        exe.setProgrammeChoiceEndDate(vo.getProgrammeChoiceEndDate());
        exe.setMfExcellentScheEndDate(vo.getMfExcellentScheEndDate());
        exe.setAdmCycleEndDate(vo.getAdmCycleEndDate());
        exe.setDisplaySixthChoice(vo.getDisplaySixthChoice());
        return exe;
    }

    private ExeVo toVo(Exe exe) {
        ExeVo vo = new ExeVo();
        vo.setId(exe.getId());
        vo.setAdmissionYear(exe.getAdmissionYear());
        vo.setApplicationStartDate(exe.getApplicationStartDate());
        vo.setApplicationEndDate(exe.getApplicationEndDate());
        vo.setProgrammeChoiceEndDate(exe.getProgrammeChoiceEndDate());
        vo.setMfExcellentScheEndDate(exe.getMfExcellentScheEndDate());
        vo.setAdmCycleEndDate(exe.getAdmCycleEndDate());
        vo.setDisplaySixthChoice(exe.getDisplaySixthChoice());
        return vo;
    }

    @Override
    public ExeVo getByApplicationId(String applicationId) {
        Exe exe = exeRepository.getByApplicationId(applicationId);
        return toVo(exe);
    }

    @Override
    public List<Integer> findAdmissionYear() {
        return exeRepository.findAdmissionYear();
    }

}
