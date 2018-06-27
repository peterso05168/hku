package com.accentrix.hku.service.adm.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.ws.rs.Path;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.adm.FormProg;
import com.accentrix.hku.repository.adm.FormProgRepository;
import com.accentrix.hku.service.adm.ExeService;
import com.accentrix.hku.service.adm.FormProgService;
import com.accentrix.hku.service.adm.FormService;
import com.accentrix.hku.service.app.HkuProgrammeService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.adm.ExeVo;
import com.accentrix.hku.vo.adm.FormProgVo;
import com.accentrix.hku.vo.adm.FormVo;
import com.accentrix.hku.vo.app.HkuProgrammeVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 下午3:12:08
 * @version 1.0
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("formProg")
public class FormProgServiceImpl implements FormProgService {

    @Autowired
    private FormProgRepository formProgRepository;
    @Autowired
    private HkuProgrammeService hkuProgrammeService;
    @Autowired
    private ExeService exeService;
    @Autowired
    private FormService formService;

    @Override
    public FormProgVo get(String id) {
        FormProg formProg = formProgRepository.findOne(id);
        return toVo(formProg);
    }

    @Transactional
    @Override
    public FormProgVo save(FormProgVo vo) {
        FormProg formProg = toFormProg(vo);
        formProg = formProgRepository.save(formProg);
        vo.setId(formProg.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<FormProgVo> save(List<FormProgVo> vos) {
        List<FormProgVo> formProgVos = new ArrayList<FormProgVo>();
        for (FormProgVo formProgVo : vos) {
            FormProg formProg = toFormProg(formProgVo);
            formProg = formProgRepository.save(formProg);
            formProgVo.setId(formProg.getId());
            formProgVos.add(formProgVo);
        }
        return formProgVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        formProgRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(FormProgVo vo) {
        formProgRepository.delete(toFormProg(vo));
    }

    @Override
    public List<FormProgVo> getByAdmFormId(String admFormId) {
        List<FormProg> formProgs = formProgRepository.getByAdmFormId(admFormId);
        List<FormProgVo> vos = new ArrayList<FormProgVo>();
        for (FormProg formProg : formProgs) {
            vos.add(toVo(formProg));
        }
        sortFormPorg(vos);
        return vos;
    }

    private FormProg toFormProg(FormProgVo vo) {
        FormProg formProg = new FormProg();
        if (StringUtils.isNotBlank(vo.getId()))
            formProg.setId(vo.getId());
        formProg.setAdmFormId(vo.getAdmFormId());
        formProg.setAppHkuProgrammeId(vo.getAppHkuProgrammeId());
        formProg.setQuotaLocal(vo.getQuotaLocal());
        formProg.setQuotaOverseas(vo.getQuotaOverseas());
        formProg.setLevelOfEntry(vo.getLevelOfEntry());
        formProg.setQuotaMainland(vo.getQuotaMainland());
        return formProg;
    }

    private FormProgVo toVo(FormProg formProg) {
        FormProgVo vo = new FormProgVo();
        if (formProg != null) {
            vo.setId(formProg.getId());
            vo.setAdmFormId(formProg.getAdmFormId());
            if (StringUtils.isNotBlank(formProg.getAdmFormId())) {
                FormVo formVo = formService.get(formProg.getAdmFormId());
                ExeVo exeVo = exeService.get(formVo.getAdmExeId());
                vo.setAdmissionYear(exeVo.getAdmissionYear());
            }
            vo.setAppHkuProgrammeId(formProg.getAppHkuProgrammeId());
            if (StringUtils.isNotBlank(formProg.getAppHkuProgrammeId())) {
                HkuProgrammeVo hkuProgramme = hkuProgrammeService.get(formProg.getAppHkuProgrammeId());
                vo.setFaculty(hkuProgramme.getFacultyCd());
                vo.setProgrammeTitle(hkuProgramme.getHkuProgrammeDesc());
                vo.setProgrammeCode(hkuProgramme.getHkuProgrammeCd());
            }
            vo.setQuotaLocal(formProg.getQuotaLocal());
            vo.setQuotaOverseas(formProg.getQuotaOverseas());
            vo.setLevelOfEntry(formProg.getLevelOfEntry());
            vo.setQuotaMainland(formProg.getQuotaMainland());
            vo.setLocal(String.valueOf(formProg.getQuotaLocal()));
            vo.setOverseas(String.valueOf(formProg.getQuotaOverseas()));
            vo.setMainland(String.valueOf(formProg.getQuotaMainland()));
        }
        return vo;
    }

    @Override
    public List<FormProgVo> basicSearch(String criteria) {
        return formProgRepository.basicSearch(criteria);
    }

    @Override
    public List<FormProgVo> advancedSearch(FormProgVo searchVo) {
        return formProgRepository.advanceSearch(searchVo);
    }

    @Override
    public List<FormProgVo> findVos(Integer year) {
        return formProgRepository.findVos(year);
    }

    public List<FormProgVo> findByHkuProgrammeId(String programmeId) {
        List<FormProg> formProgs = formProgRepository.findByHkuProgrammeId(programmeId);
        List<FormProgVo> vos = new ArrayList<FormProgVo>();
        for (FormProg formProg : formProgs) {
            vos.add(toVo(formProg));
        }
        return vos;
    }

    public void sortFormPorg(List<FormProgVo> formProgs) {
        Collections.sort(formProgs, new Comparator<FormProgVo>() {
            @Override
            public int compare(FormProgVo o1, FormProgVo o2) {
                return o1.getProgrammeCode().compareTo(o2.getProgrammeCode());
            }
        });
    }
}
