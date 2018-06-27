package com.accentrix.hku.service.app.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.app.BestExamSubjRslt;
import com.accentrix.hku.repository.app.BestExamSubjRsltRepository;
import com.accentrix.hku.service.app.BestExamSubjRsltService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.app.BestExamSubjRsltVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年5月29日 下午8:02:37
 * @version 1.0
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("bestExamSubjRslt")
public class BestExamSubjRsltServiceImpl implements BestExamSubjRsltService {

    @Autowired
    private BestExamSubjRsltRepository bestExamSubjRsltRepository;

    @Override
    public BestExamSubjRsltVo get(String id) {
        BestExamSubjRslt bestExamSubjRslt = bestExamSubjRsltRepository.getOne(id);
        return toVo(bestExamSubjRslt);
    }

    @Transactional
    @Override
    public BestExamSubjRsltVo save(BestExamSubjRsltVo vo) {
        BestExamSubjRslt bestExamSubjRslt = toBestExamSubjRslt(vo);
        bestExamSubjRslt = bestExamSubjRsltRepository.save(bestExamSubjRslt);
        vo.setId(bestExamSubjRslt.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<BestExamSubjRsltVo> save(List<BestExamSubjRsltVo> vos) {
        List<BestExamSubjRsltVo> list = new ArrayList<BestExamSubjRsltVo>();
        for (BestExamSubjRsltVo bestExamSubjRsltVo : vos) {
            BestExamSubjRslt bestExamSubjRslt = toBestExamSubjRslt(bestExamSubjRsltVo);
            bestExamSubjRslt = bestExamSubjRsltRepository.save(bestExamSubjRslt);
            bestExamSubjRsltVo.setId(bestExamSubjRslt.getId());
            list.add(bestExamSubjRsltVo);
        }
        return list;
    }

    @Transactional
    @Override
    public void delete(String id) {
        bestExamSubjRsltRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(BestExamSubjRsltVo vo) {
        BestExamSubjRslt bestExamSubjRslt = toBestExamSubjRslt(vo);
        bestExamSubjRsltRepository.delete(bestExamSubjRslt);
    }

    @Override
    public List<BestExamSubjRsltVo> findList() {
        List<BestExamSubjRslt> list = bestExamSubjRsltRepository.findAll();
        List<BestExamSubjRsltVo> vos = new ArrayList<BestExamSubjRsltVo>();
        for (BestExamSubjRslt bestExamSubjRslt : list) {
            vos.add(toVo(bestExamSubjRslt));
        }
        return vos;
    }

    private BestExamSubjRsltVo toVo(BestExamSubjRslt bestExamSubjRslt) {
        BestExamSubjRsltVo vo = new BestExamSubjRsltVo();
        vo.setId(bestExamSubjRslt.getId());
        vo.setQualificationRsltId(bestExamSubjRslt.getQualificationRsltId());
        vo.setBestExamSubjId(bestExamSubjRslt.getBestExamSubjId());
        vo.setSeqNo(bestExamSubjRslt.getSeqNo());
        vo.setExamSubjectDesc(bestExamSubjRslt.getExamSubjectDesc());
        vo.setExamTypeMonth(bestExamSubjRslt.getExamTypeMonth());
        vo.setExamTypeYear(bestExamSubjRslt.getExamTypeYear());
        vo.setExamGradeCd(bestExamSubjRslt.getExamGradeCd());
        vo.setRsltType(bestExamSubjRslt.getRsltType());
        return vo;
    }

    private BestExamSubjRslt toBestExamSubjRslt(BestExamSubjRsltVo vo) {
        BestExamSubjRslt bestExamSubjRslt = new BestExamSubjRslt();
        bestExamSubjRslt.setId(vo.getId());
        bestExamSubjRslt.setQualificationRsltId(vo.getQualificationRsltId());
        bestExamSubjRslt.setBestExamSubjId(vo.getBestExamSubjId());
        bestExamSubjRslt.setSeqNo(vo.getSeqNo());
        bestExamSubjRslt.setExamSubjectDesc(vo.getExamSubjectDesc());
        bestExamSubjRslt.setExamTypeMonth(vo.getExamTypeMonth());
        bestExamSubjRslt.setExamTypeYear(vo.getExamTypeYear());
        bestExamSubjRslt.setExamGradeCd(vo.getExamGradeCd());
        bestExamSubjRslt.setRsltType(vo.getRsltType());
        return bestExamSubjRslt;
    }

    @Override
    public List<BestExamSubjRsltVo> getBestExamSubjects(String examCd, String applicationId, String calculateType) {
        List<BestExamSubjRslt> list = bestExamSubjRsltRepository.getBestExamSubjects(examCd, applicationId,
                calculateType);
        List<BestExamSubjRsltVo> vos = new ArrayList<BestExamSubjRsltVo>();
        for (BestExamSubjRslt bestExamSubjRslt : list) {
            vos.add(toVo(bestExamSubjRslt));
        }
        return vos;
    }

    @Override
    public List<BestExamSubjRsltVo> findByBestExamSubjId(String bestExamSubjId) {
        List<BestExamSubjRslt> list = bestExamSubjRsltRepository.findByBestExamSubjId(bestExamSubjId);
        List<BestExamSubjRsltVo> vos = new ArrayList<BestExamSubjRsltVo>();
        for (BestExamSubjRslt bestExamSubjRslt : list) {
            vos.add(toVo(bestExamSubjRslt));
        }
        return vos;
    }

}
