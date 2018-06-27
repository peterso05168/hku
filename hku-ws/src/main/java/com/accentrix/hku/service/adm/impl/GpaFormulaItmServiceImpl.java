package com.accentrix.hku.service.adm.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.adm.GpaFormulaItm;
import com.accentrix.hku.repository.adm.GpaFormulaItmRepository;
import com.accentrix.hku.service.adm.GpaFormulaItmService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.adm.GpaFormulaItmVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年3月19日 上午11:44:01 
 * @version 1.0 
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("gpaFormulaItm")
public class GpaFormulaItmServiceImpl implements GpaFormulaItmService {

    @Autowired
    private GpaFormulaItmRepository gpaFormulaItmRepository;

    @Override
    public GpaFormulaItmVo get(String id) {
        GpaFormulaItm gpaFormulaItm = gpaFormulaItmRepository.findOne(id);
        return toVo(gpaFormulaItm);
    }

    @Transactional
    @Override
    public GpaFormulaItmVo save(GpaFormulaItmVo vo) {
        GpaFormulaItm gpaFormulaItm = toGpaFormulaItm(vo);
        gpaFormulaItm = gpaFormulaItmRepository.save(gpaFormulaItm);
        vo.setId(gpaFormulaItm.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<GpaFormulaItmVo> save(List<GpaFormulaItmVo> vos) {
        List<GpaFormulaItmVo> gpaFormulaItmVos = new ArrayList<GpaFormulaItmVo>();
        for (GpaFormulaItmVo gpaFormulaItmVo : vos) {
            GpaFormulaItm gpaFormulaItm = toGpaFormulaItm(gpaFormulaItmVo);
            gpaFormulaItm = gpaFormulaItmRepository.save(gpaFormulaItm);
            gpaFormulaItmVo.setId(gpaFormulaItm.getId());
            gpaFormulaItmVos.add(gpaFormulaItmVo);
        }
        return gpaFormulaItmVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        gpaFormulaItmRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(GpaFormulaItmVo vo) {
        GpaFormulaItm gpaFormulaItm = toGpaFormulaItm(vo);
        gpaFormulaItmRepository.delete(gpaFormulaItm);
    }

    @Override
    public List<GpaFormulaItmVo> findList() {
        List<GpaFormulaItm> list = gpaFormulaItmRepository.findAll();
        List<GpaFormulaItmVo> vos = new ArrayList<GpaFormulaItmVo>();
        for (GpaFormulaItm gpaFormulaItm : list) {
            vos.add(toVo(gpaFormulaItm));
        }
        return vos;
    }

    private GpaFormulaItmVo toVo(GpaFormulaItm gpaFormulaItm) {
        GpaFormulaItmVo vo = new GpaFormulaItmVo();
        vo.setId(gpaFormulaItm.getId());
        vo.setMaxCgpa(gpaFormulaItm.getMaxCgpa().toString());
        vo.setMinFinalYearCgpa(gpaFormulaItm.getMinFinalYearCgpa().toString());
        vo.setMinYearOneCgpa(gpaFormulaItm.getMinYearOneCgpa().toString());
        vo.setIsDeleted(gpaFormulaItm.getIsDeleted());
        vo.setAdmScoringFormulaId(gpaFormulaItm.getAdmScoringFormulaId());
        return vo;
    }

    private GpaFormulaItm toGpaFormulaItm(GpaFormulaItmVo vo) {
        GpaFormulaItm gpaFormulaItm = new GpaFormulaItm();
        gpaFormulaItm.setId(vo.getId());
        gpaFormulaItm.setMaxCgpa(stringToBigDecimal(vo.getMaxCgpa()));
        gpaFormulaItm.setMinFinalYearCgpa(stringToBigDecimal(vo.getMinFinalYearCgpa()));
        gpaFormulaItm.setMinYearOneCgpa(stringToBigDecimal(vo.getMinYearOneCgpa()));
        gpaFormulaItm.setIsDeleted(vo.getIsDeleted());
        gpaFormulaItm.setAdmScoringFormulaId(vo.getAdmScoringFormulaId());
        return gpaFormulaItm;
    }

    private BigDecimal stringToBigDecimal(String str) {
        BigDecimal bd = new BigDecimal(str);
        bd = bd.setScale(2);
        return bd;
    }

    @Override
    public List<GpaFormulaItmVo> findByScoringFormulaId(String scoringFormulaId) {
        List<GpaFormulaItm> items = gpaFormulaItmRepository.findByScoringFormulaId(scoringFormulaId);
        List<GpaFormulaItmVo> vos = new ArrayList<GpaFormulaItmVo>();
        for (GpaFormulaItm item : items) {
            vos.add(toVo(item));
        }
        return vos;
    }

}
