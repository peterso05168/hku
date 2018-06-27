package com.accentrix.hku.service.adm.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.adm.Form;
import com.accentrix.hku.repository.adm.FormRepository;
import com.accentrix.hku.service.adm.FormService;
import com.accentrix.hku.vo.adm.FormVo;
import com.accentrix.hku.timer.annotation.Timer;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 下午2:18:15
 * @version 1.0
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("form")
public class FormServiceImpl implements FormService {

    @Autowired
    private FormRepository formRepository;

    @Override
    public FormVo get(String id) {
        Form form = formRepository.findOne(id);
        return toVo(form);
    }

    @Transactional
    @Override
    public FormVo save(FormVo vo) {
        Form form = toForm(vo);
        form = formRepository.save(form);
        vo.setId(form.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<FormVo> save(List<FormVo> vos) {
        List<FormVo> formVos = new ArrayList<FormVo>();
        for (FormVo formVo : vos) {
            Form form = toForm(formVo);
            form = formRepository.save(form);
            formVo.setId(form.getId());
            formVos.add(formVo);
        }
        return formVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        formRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(FormVo vo) {
        formRepository.delete(toForm(vo));
    }

    @Override
    public List<FormVo> findList() {
        List<Form> forms = formRepository.findAll();
        List<FormVo> vos = new ArrayList<FormVo>();
        for (Form form : forms) {
            vos.add(toVo(form));
        }
        return vos;
    }

    @Override
    public FormVo getByAdmExeId(String admExeId) {
        Form form = formRepository.getByAdmExeId(admExeId);
        return toVo(form);
    }

    private Form toForm(FormVo vo) {
        Form form = new Form();
        form.setAdmExeId(vo.getAdmExeId());
        form.setDescription(vo.getDescription());
        form.setDescChi(vo.getDescChi());
        return form;
    }

    private FormVo toVo(Form form) {
        FormVo vo = new FormVo();
        vo.setId(form.getId());
        vo.setAdmExeId(form.getAdmExeId());
        vo.setDescription(form.getDescription());
        vo.setDescChi(form.getDescChi());
        return vo;
    }

}
