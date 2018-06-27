package com.accentrix.hku.service.app.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.app.ReqDoc;
import com.accentrix.hku.repository.app.ReqDocRepository;
import com.accentrix.hku.service.app.ReqDocService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.app.ReqDocVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年1月31日 下午2:26:20 
 * @version 1.0 
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("reqDoc")
public class ReqDocServiceImpl implements ReqDocService {

    @Autowired
    private ReqDocRepository reqDocRepository;

    @Override
    public ReqDocVo get(String id) {
        ReqDoc reqDoc = reqDocRepository.findOne(id);
        return toVo(reqDoc);
    }

    @Transactional
    @Override
    public ReqDocVo save(ReqDocVo vo) {
        ReqDoc reqDoc = toReqDoc(vo);
        reqDoc = reqDocRepository.save(reqDoc);
        vo.setId(reqDoc.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<ReqDocVo> save(List<ReqDocVo> vos) {
        List<ReqDocVo> reqDocVos = new ArrayList<ReqDocVo>();
        for (ReqDocVo vo : vos) {
            ReqDoc reqDoc = toReqDoc(vo);
            reqDoc = reqDocRepository.save(reqDoc);
            vo.setId(reqDoc.getId());
            reqDocVos.add(vo);
        }
        return reqDocVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        reqDocRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(ReqDocVo vo) {
        reqDocRepository.delete(toReqDoc(vo));
    }

    @Override
    public List<ReqDocVo> findList() {
        List<ReqDoc> reqDocs = reqDocRepository.findAll();
        List<ReqDocVo> vos = new ArrayList<ReqDocVo>();
        for (ReqDoc reqDoc : reqDocs) {
            vos.add(toVo(reqDoc));
        }
        return vos;
    }

    private ReqDoc toReqDoc(ReqDocVo vo) {
        ReqDoc reqDoc = new ReqDoc();
        if (StringUtils.isNotBlank(vo.getId()))
            reqDoc.setId(vo.getId());
        reqDoc.setApplicationId(vo.getApplicationId());
        reqDoc.setStatusCd(vo.getStatusCd());
        reqDoc.setSubmissionDueDate(vo.getSubmissionDueDate());
        reqDoc.setReqDocConfId(vo.getReqDocConfId());
        reqDoc.setQualificationId(vo.getQualificationId());
        return reqDoc;
    }

    private ReqDocVo toVo(ReqDoc reqDoc) {
        ReqDocVo vo = new ReqDocVo();
        vo.setId(reqDoc.getId());
        vo.setApplicationId(reqDoc.getApplicationId());
        vo.setStatusCd(reqDoc.getStatusCd());
        vo.setSubmissionDueDate(reqDoc.getSubmissionDueDate());
        return vo;
    }

    @Override
    public List<ReqDocVo> findByReqDocType(String applicationId, String reqDocType) {
        return reqDocRepository.findByReqDocType(applicationId, reqDocType);
    }

    @Override
    public ReqDocVo getByApplicationIdAndReqDocConfIdAndQualificationId(String applicationId, String reqDocConfId,
            String qualificationId) {
        ReqDoc reqDoc = reqDocRepository.getByApplicationIdAndReqDocConfIdAndQualificationId(applicationId,
                reqDocConfId, qualificationId);
        return reqDoc != null ? toVo(reqDoc) : null;
    }

}
