package com.accentrix.hku.service.app.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.app.BestExamSubj;
import com.accentrix.hku.repository.app.BestExamSubjRepository;
import com.accentrix.hku.service.app.BestExamSubjService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.app.BestExamSubjVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年5月29日 下午8:13:17 
 * @version 1.0 
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("bestExamSubj")
public class BestExamSubjServiceImpl implements BestExamSubjService {

    @Autowired
    private BestExamSubjRepository bestExamSubjRepository;

    @Override
    public BestExamSubjVo get(String id) {
        BestExamSubj bestExamSubj = bestExamSubjRepository.getOne(id);
        return toVo(bestExamSubj);
    }

    @Transactional
    @Override
    public BestExamSubjVo save(BestExamSubjVo vo) {
        BestExamSubj bestExamSubj = toBestExamSubj(vo);
        bestExamSubj = bestExamSubjRepository.save(bestExamSubj);
        vo.setId(bestExamSubj.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<BestExamSubjVo> save(List<BestExamSubjVo> vos) {
        List<BestExamSubjVo> bestExamSubjVos = new ArrayList<BestExamSubjVo>();
        for (BestExamSubjVo bestExamSubjVo : vos) {
            BestExamSubj bestExamSubj = toBestExamSubj(bestExamSubjVo);
            bestExamSubj = bestExamSubjRepository.save(bestExamSubj);
            bestExamSubjVo.setId(bestExamSubj.getId());
            bestExamSubjVos.add(bestExamSubjVo);
        }
        return bestExamSubjVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        bestExamSubjRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(BestExamSubjVo vo) {
        BestExamSubj bestExamSubj = toBestExamSubj(vo);
        bestExamSubjRepository.delete(bestExamSubj);
    }

    @Override
    public List<BestExamSubjVo> findList() {
        List<BestExamSubj> list = bestExamSubjRepository.findAll();
        List<BestExamSubjVo> vos = new ArrayList<BestExamSubjVo>();
        for (BestExamSubj bestExamSubj : list) {
            vos.add(toVo(bestExamSubj));
        }
        return vos;
    }

    private BestExamSubjVo toVo(BestExamSubj bestExamSubj) {
        BestExamSubjVo vo = new BestExamSubjVo();
        vo.setId(bestExamSubj.getId());
        vo.setApplicationId(bestExamSubj.getApplicationId());
        vo.setExamTypeId(bestExamSubj.getExamTypeId());
        vo.setCalculateType(bestExamSubj.getCalculateType());
        return vo;
    }

    private BestExamSubj toBestExamSubj(BestExamSubjVo vo) {
        BestExamSubj bestExamSubj = new BestExamSubj();
        bestExamSubj.setId(vo.getId());
        bestExamSubj.setApplicationId(vo.getApplicationId());
        bestExamSubj.setExamTypeId(vo.getExamTypeId());
        bestExamSubj.setCalculateType(vo.getCalculateType());
        return bestExamSubj;
    }

    @Override
    public BestExamSubjVo getByApplicationIdAndExamTypeIdAndCalculateType(String applicationId, String examTypeId,
            String calculateType) {
        BestExamSubj bestExamSubj = bestExamSubjRepository
                .getByApplicationIdAndExamTypeIdAndCalculateType(applicationId, examTypeId, calculateType);
        return bestExamSubj == null ? null : toVo(bestExamSubj);
    }

}
