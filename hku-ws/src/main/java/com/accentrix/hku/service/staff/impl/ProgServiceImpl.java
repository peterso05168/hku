package com.accentrix.hku.service.staff.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.staff.Prog;
import com.accentrix.hku.repository.staff.ProgRepository;
import com.accentrix.hku.service.staff.ProgService;
import com.accentrix.hku.vo.staff.ProgVo;
import com.accentrix.hku.timer.annotation.Timer;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 下午2:53:05
 * @version 1.0
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("prog")
public class ProgServiceImpl implements ProgService {

    @Autowired
    private ProgRepository progRepository;

    @Override
    public ProgVo get(String id) {
        Prog prog = progRepository.findOne(id);
        return toVo(prog);
    }

    @Transactional
    @Override
    public ProgVo save(ProgVo vo) {
        Prog prog = toProg(vo);
        vo.setId(prog.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<ProgVo> save(List<ProgVo> vos) {
        List<ProgVo> progVos = new ArrayList<ProgVo>();
        for (ProgVo progVo : vos) {
            Prog prog = toProg(progVo);
            prog = progRepository.save(prog);
            progVo.setId(prog.getId());
            progVos.add(progVo);
        }
        return progVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        progRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(ProgVo vo) {
        Prog prog = toProg(vo);
        progRepository.delete(prog);
    }

    @Override
    public List<ProgVo> findList() {
        List<ProgVo> progVos = new ArrayList<ProgVo>();
        List<Prog> progs = progRepository.findAll();
        for (Prog prog : progs) {
            progVos.add(toVo(prog));
        }
        return progVos;
    }

    private Prog toProg(ProgVo vo) {
        Prog prog = new Prog();
        prog.setId(vo.getId());
        prog.setStaffId(vo.getStaffId());
        prog.setHkuProgrammeId(vo.getHkuProgrammeId());
        return prog;
    }

    private ProgVo toVo(Prog prog) {
        ProgVo vo = new ProgVo();
        vo.setId(prog.getId());
        vo.setStaffId(prog.getStaffId());
        vo.setHkuProgrammeId(prog.getHkuProgrammeId());
        return vo;
    }

    @Transactional
    @Override
    public void deleteByStaffId(String staffId) {
        List<Prog> progs = progRepository.findByStaffId(staffId);
        progRepository.delete(progs);
    }
}
