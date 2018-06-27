package com.accentrix.hku.service.app.impl;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.app.IbdBestExam;
import com.accentrix.hku.repository.app.IbdBestExamRepository;
import com.accentrix.hku.service.app.IbdBestExamService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.app.IbdBestExamVo;

@Service
@Transactional(readOnly = true)
@Timer
@Path("ibdBestExam")
public class IbdBestExamServiceImpl implements IbdBestExamService {

    @Autowired
    private IbdBestExamRepository ibdBestExamRepository;

    @Override
    public IbdBestExamVo getBestIBD(String applicationId, String calculateType, String outOf) {
        IbdBestExam ibdBestExam = ibdBestExamRepository.getBestIBD(applicationId, calculateType, outOf);
        return ibdBestExam == null ? new IbdBestExamVo() : toVo(ibdBestExam);
    }

    private IbdBestExamVo toVo(IbdBestExam ibdBestExam) {
        IbdBestExamVo vo = new IbdBestExamVo();
        vo.setId(ibdBestExam.getId());
        vo.setApplicationId(ibdBestExam.getApplicationId());
        vo.setQualificationId(ibdBestExam.getQualificationId());
        vo.setTotalRslt(ibdBestExam.getTotalRslt());
        vo.setEeTok(ibdBestExam.getEeTok());
        vo.setCalculateType(ibdBestExam.getCalculateType());
        vo.setOutOf(ibdBestExam.getOutOf());
        return vo;
    }

    private IbdBestExam toIbdBestExam(IbdBestExamVo vo) {
        IbdBestExam ibdBestExam = new IbdBestExam();
        ibdBestExam.setId(vo.getId());
        ibdBestExam.setApplicationId(vo.getApplicationId());
        ibdBestExam.setQualificationId(vo.getQualificationId());
        ibdBestExam.setTotalRslt(vo.getTotalRslt());
        ibdBestExam.setEeTok(vo.getEeTok());
        ibdBestExam.setCalculateType(vo.getCalculateType());
        ibdBestExam.setOutOf(vo.getOutOf());
        return ibdBestExam;
    }

    @Transactional
    @Override
    public IbdBestExamVo save(IbdBestExamVo vo) {
        IbdBestExam ibdBestExam = toIbdBestExam(vo);
        ibdBestExam = ibdBestExamRepository.save(ibdBestExam);
        vo.setId(ibdBestExam.getId());
        return vo;
    }

    @Transactional
    @Override
    public void delete(String id) {
        ibdBestExamRepository.delete(id);
    }

}
