package com.accentrix.hku.service.app.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.app.UploadedDoc;
import com.accentrix.hku.repository.app.UploadedDocRepository;
import com.accentrix.hku.service.app.UploadedDocService;
import com.accentrix.hku.vo.app.UploadedDocVo;
import com.accentrix.hku.timer.annotation.Timer;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 下午2:28:35
 * @version 1.0
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("uploadedDoc")
public class UploadedDocServiceImpl implements UploadedDocService {

    @Autowired
    private UploadedDocRepository uploadedDocRepository;

    @Override
    public UploadedDocVo get(String id) {
        UploadedDoc uploadedDoc = uploadedDocRepository.findOne(id);
        return toVo(uploadedDoc);
    }

    @Transactional
    @Override
    public UploadedDocVo save(UploadedDocVo vo) {
        UploadedDoc uploadedDoc = toUploadedDoc(vo);
        uploadedDoc = uploadedDocRepository.save(uploadedDoc);
        vo.setId(uploadedDoc.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<UploadedDocVo> save(List<UploadedDocVo> vos) {
        List<UploadedDocVo> uploadedDocVos = new ArrayList<UploadedDocVo>();
        for (UploadedDocVo uploadedDocVo : vos) {
            UploadedDoc uploadedDoc = toUploadedDoc(uploadedDocVo);
            uploadedDoc = uploadedDocRepository.save(uploadedDoc);
            uploadedDocVo.setId(uploadedDoc.getId());
            uploadedDocVos.add(uploadedDocVo);
        }
        return uploadedDocVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        uploadedDocRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(UploadedDocVo vo) {
        uploadedDocRepository.delete(toUploadedDoc(vo));
    }

    @Override
    public List<UploadedDocVo> findList() {
        List<UploadedDoc> uploadedDocs = uploadedDocRepository.findAll();
        List<UploadedDocVo> vos = new ArrayList<UploadedDocVo>();
        for (UploadedDoc uploadedDoc : uploadedDocs) {
            vos.add(toVo(uploadedDoc));
        }
        return vos;
    }

    private UploadedDoc toUploadedDoc(UploadedDocVo vo) {
        UploadedDoc uploadedDoc = new UploadedDoc();
        if (StringUtils.isNotBlank(vo.getId()))
            uploadedDoc.setId(vo.getId());
        uploadedDoc.setReqDocId(vo.getReqDocId());
        uploadedDoc.setFileName(vo.getFileName());
        uploadedDoc.setDisplayFileName(vo.getDisplayFileName());
        uploadedDoc.setFilePath(vo.getFilePath());
        uploadedDoc.setActive(vo.getActive());
        uploadedDoc.setSubmissionDate(vo.getSubmissionDate());
        uploadedDoc.setRemark(vo.getRemark());
        return uploadedDoc;
    }

    private UploadedDocVo toVo(UploadedDoc uploadedDoc) {
        UploadedDocVo vo = new UploadedDocVo();
        vo.setId(uploadedDoc.getId());
        vo.setReqDocId(uploadedDoc.getReqDocId());
        vo.setFileName(uploadedDoc.getFileName());
        vo.setDisplayFileName(uploadedDoc.getDisplayFileName());
        vo.setFilePath(uploadedDoc.getFilePath());
        vo.setActive(uploadedDoc.getActive());
        vo.setSubmissionDate(uploadedDoc.getSubmissionDate());
        vo.setRemark(uploadedDoc.getRemark());
        return vo;
    }

    @Override
    public List<UploadedDocVo> findByReqDocId(String reqDocId) {
        List<UploadedDoc> uploadedDocs = uploadedDocRepository.findByReqDocId(reqDocId);
        List<UploadedDocVo> vos = new ArrayList<UploadedDocVo>();
        for (UploadedDoc uploadedDoc : uploadedDocs) {
            vos.add(toVo(uploadedDoc));
        }
        return vos;
    }

}
