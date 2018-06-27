package com.accentrix.hku.service.app.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.app.Reference;
import com.accentrix.hku.repository.app.ReferenceRepository;
import com.accentrix.hku.service.app.ReferenceService;
import com.accentrix.hku.vo.app.ReferenceVo;
import com.accentrix.hku.timer.annotation.Timer;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 下午2:26:01
 * @version 1.0
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("reference")
public class ReferenceServiceImpl implements ReferenceService {

    @Autowired
    private ReferenceRepository referenceRepository;

    @Override
    public ReferenceVo get(String id) {
        Reference reference = referenceRepository.findOne(id);
        return toVo(reference);
    }

    @Transactional
    @Override
    public ReferenceVo save(ReferenceVo vo) {
        Reference reference = toReference(vo);
        reference.setId(vo.getId());
        reference.setCreateBy(vo.getCreateBy());
        reference.setUpdateBy(vo.getUpdateBy());
        reference.setCreateDate(vo.getCreateDate());
        reference.setUpdateDate(vo.getUpdateDate());
        reference.setVersion(vo.getVersion());
        reference = referenceRepository.save(reference);
        vo.setId(reference.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<ReferenceVo> save(List<ReferenceVo> vos) {
        List<ReferenceVo> referenceVos = new ArrayList<ReferenceVo>();
        for (ReferenceVo vo : vos) {
            Reference reference = toReference(vo);
            reference = referenceRepository.save(reference);
            vo.setId(reference.getId());
            referenceVos.add(vo);
        }
        return referenceVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        referenceRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(ReferenceVo vo) {
        referenceRepository.delete(toReference(vo));
    }

    @Override
    public List<ReferenceVo> findList() {
        List<Reference> references = referenceRepository.findAll();
        List<ReferenceVo> vos = new ArrayList<ReferenceVo>();
        for (Reference reference : references) {
            vos.add(toVo(reference));
        }
        return vos;
    }

    private Reference toReference(ReferenceVo vo) {
        Reference reference = new Reference();
        return reference;
    }

    private ReferenceVo toVo(Reference reference) {
        ReferenceVo vo = new ReferenceVo();
        vo.setId(reference.getId());
        vo.setCreateBy(reference.getCreateBy());
        vo.setUpdateBy(reference.getUpdateBy());
        vo.setCreateDate(reference.getCreateDate());
        vo.setUpdateDate(reference.getUpdateDate());
        vo.setVersion(reference.getVersion());
        return vo;
    }
}
