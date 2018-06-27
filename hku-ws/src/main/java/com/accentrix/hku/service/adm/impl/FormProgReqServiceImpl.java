package com.accentrix.hku.service.adm.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.adm.FormProgReq;
import com.accentrix.hku.repository.adm.FormProgReqRepository;
import com.accentrix.hku.service.adm.FormProgReqService;
import com.accentrix.hku.vo.adm.FormProgReqVo;
import com.accentrix.hku.timer.annotation.Timer;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年1月31日 下午3:12:32 
 * @version 1.0 
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("formProgReq")
public class FormProgReqServiceImpl implements FormProgReqService {

    @Autowired
    private FormProgReqRepository formProgReqRepository;

    @Override
    public FormProgReqVo get(String id) {
        FormProgReq formProgReq = formProgReqRepository.findOne(id);
        return toVo(formProgReq);
    }

    @Transactional
    @Override
    public FormProgReqVo save(FormProgReqVo vo) {
        FormProgReq formProgReq = toFormProgReq(vo);
        formProgReq = formProgReqRepository.save(formProgReq);
        vo.setId(formProgReq.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<FormProgReqVo> save(List<FormProgReqVo> vos) {
        List<FormProgReqVo> formProgReqs = new ArrayList<FormProgReqVo>();
        for (FormProgReqVo vo : vos) {
            FormProgReq formProgReq = toFormProgReq(vo);
            formProgReq = formProgReqRepository.save(formProgReq);
            vo.setId(formProgReq.getId());
            formProgReqs.add(vo);
        }
        return formProgReqs;
    }

    @Transactional
    @Override
    public void delete(String id) {
        formProgReqRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(FormProgReqVo vo) {
        formProgReqRepository.delete(toFormProgReq(vo));
    }

    @Override
    public List<FormProgReqVo> findList() {
        List<FormProgReq> formProgReqs = formProgReqRepository.findAll();
        List<FormProgReqVo> vos = new ArrayList<FormProgReqVo>();
        for (FormProgReq formProgReq : formProgReqs) {
            vos.add(toVo(formProgReq));
        }
        return vos;
    }

    private FormProgReq toFormProgReq(FormProgReqVo vo) {
        FormProgReq formProgReq = new FormProgReq();
        formProgReq.setAdmFormProgId(vo.getAdmFormProgId());
        formProgReq.setAppRequirementId(vo.getAppRequirementId());
        return formProgReq;
    }

    private FormProgReqVo toVo(FormProgReq formProgReq) {
        FormProgReqVo vo = new FormProgReqVo();
        vo.setId(formProgReq.getId());
        vo.setAdmFormProgId(formProgReq.getAdmFormProgId());
        vo.setAppRequirementId(formProgReq.getAppRequirementId());
        return vo;
    }

    @Override
    public List<FormProgReqVo> getByFormProgId(String formProgId) {
        List<FormProgReq> list = formProgReqRepository.getByFormProgId(formProgId);
        List<FormProgReqVo> vos = new ArrayList<FormProgReqVo>();
        for (FormProgReq formProgReq : list) {
            vos.add(toVo(formProgReq));
        }
        return vos;
    }

    @Override
    public List<FormProgReqVo> getByRequirementId(String requirementId) {
        List<FormProgReq> list = formProgReqRepository.getByRequirementId(requirementId);
        List<FormProgReqVo> vos = new ArrayList<FormProgReqVo>();
        for (FormProgReq formProgReq : list) {
            vos.add(toVo(formProgReq));
        }
        return vos;
    }
}
