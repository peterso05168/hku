package com.accentrix.hku.service.adm.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.constant.Constants;
import com.accentrix.hku.domain.adm.AdmAnncmnt;
import com.accentrix.hku.repository.adm.AnncmntTemplateRepository;
import com.accentrix.hku.service.adm.AnncmntTemplateService;
import com.accentrix.hku.service.general.RefCdService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.adm.AdmAnncmntVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年1月31日 下午2:29:40 
 * @version 1.0 
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("admAnncmnt")
public class AnncmntTemplateServiceImpl implements AnncmntTemplateService {

    @Autowired
    private AnncmntTemplateRepository anncmntTemplateRepository;

    @Autowired
    private RefCdService refCdService;

    @Override
    public AdmAnncmntVo get(String id) {
        AdmAnncmnt admAnncmnt = anncmntTemplateRepository.findOne(id);
        return toVo(admAnncmnt);
    }

    @Transactional
    @Override
    public AdmAnncmntVo save(AdmAnncmntVo vo) {
        AdmAnncmnt admAnncmnt = toAnncmnt(vo);
        admAnncmnt = anncmntTemplateRepository.save(admAnncmnt);
        vo.setId(admAnncmnt.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<AdmAnncmntVo> save(List<AdmAnncmntVo> vos) {
        List<AdmAnncmntVo> admAnncmntVos = new ArrayList<AdmAnncmntVo>();
        for (AdmAnncmntVo admAnncmntVo : vos) {
            AdmAnncmnt admAnncmnt = toAnncmnt(admAnncmntVo);
            admAnncmnt = anncmntTemplateRepository.save(admAnncmnt);
            admAnncmntVo.setId(admAnncmnt.getId());
            admAnncmntVos.add(admAnncmntVo);
        }
        return admAnncmntVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        anncmntTemplateRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(AdmAnncmntVo admAnncmntVo) {
        anncmntTemplateRepository.delete(toAnncmnt(admAnncmntVo));
    }

    @Override
    public List<AdmAnncmntVo> findList() {
        List<AdmAnncmntVo> admAnncmntVos = new ArrayList<AdmAnncmntVo>();
        List<AdmAnncmnt> admAnncmnts = anncmntTemplateRepository.findAll();
        for (AdmAnncmnt admAnncmnt : admAnncmnts) {
            admAnncmntVos.add(toVo(admAnncmnt));
        }
        return admAnncmntVos;
    }

    private AdmAnncmnt toAnncmnt(AdmAnncmntVo vo) {
        AdmAnncmnt admAnncmnt = new AdmAnncmnt();
        if (StringUtils.isNotBlank(vo.getId()))
            admAnncmnt.setId(vo.getId());
        admAnncmnt.setTypeCd(vo.getTypeCd());
        admAnncmnt.setSubject(vo.getSubject());
        admAnncmnt.setContent(vo.getContent());
        admAnncmnt.setCreateDate(vo.getCreateDate());
        admAnncmnt.setModifyDate(vo.getModifyDate());
        return admAnncmnt;
    }

    private AdmAnncmntVo toVo(AdmAnncmnt admAnncmnt) {
        AdmAnncmntVo vo = new AdmAnncmntVo();
        vo.setId(admAnncmnt.getId());
        vo.setTypeCd(admAnncmnt.getTypeCd());
        vo.setTemplateName(refCdService.getByTypeAndCd(Constants.ANNCMNT_TYPE, admAnncmnt.getTypeCd()).getValue());
        vo.setSubject(admAnncmnt.getSubject());
        vo.setContent(admAnncmnt.getContent());
        vo.setCreateDate(admAnncmnt.getCreateDate());
        vo.setModifyDate(admAnncmnt.getModifyDate());
        return vo;
    }

    @Override
    public List<AdmAnncmntVo> findByTemplateName(String templateName) {
        List<AdmAnncmntVo> admAnncmntVos = new ArrayList<AdmAnncmntVo>();
        List<AdmAnncmnt> admAnncmnts = anncmntTemplateRepository.findByTemplateName(templateName);
        for (AdmAnncmnt admAnncmnt : admAnncmnts) {
            admAnncmntVos.add(toVo(admAnncmnt));
        }
        return admAnncmntVos;
    }

    @Override
    public AdmAnncmntVo getByTypeCd(String typeCd) {
        AdmAnncmnt admAnncmnt = anncmntTemplateRepository.getByTypeCd(typeCd);
        return toVo(admAnncmnt);
    }
}
