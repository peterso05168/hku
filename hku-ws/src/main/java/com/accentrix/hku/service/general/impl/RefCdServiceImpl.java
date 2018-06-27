package com.accentrix.hku.service.general.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.general.RefCd;
import com.accentrix.hku.repository.general.RefCdRepository;
import com.accentrix.hku.service.general.RefCdService;
import com.accentrix.hku.vo.general.RefCdVo;
import com.accentrix.hku.timer.annotation.Timer;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 下午2:32:48
 * @version 1.0
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("refCd")
public class RefCdServiceImpl implements RefCdService {

    @Autowired
    private RefCdRepository refCdRepository;

    @Override
    public RefCdVo get(String id) {
        RefCd refCd = refCdRepository.findOne(id);
        return toVo(refCd);
    }

    @Transactional
    @Override
    public RefCdVo save(RefCdVo vo) {
        RefCd refCd = toRefCd(vo);
        refCd = refCdRepository.save(refCd);
        vo.setId(refCd.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<RefCdVo> save(List<RefCdVo> vos) {
        List<RefCdVo> refCdVos = new ArrayList<RefCdVo>();
        for (RefCdVo refCdVo : refCdVos) {
            RefCd refCd = toRefCd(refCdVo);
            refCd = refCdRepository.save(refCd);
            refCdVo.setId(refCd.getId());
            refCdVos.add(refCdVo);
        }
        return refCdVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        refCdRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(RefCdVo vo) {
        RefCd refCd = toRefCd(vo);
        refCdRepository.delete(refCd);
    }

    @Override
    public List<RefCdVo> findList() {
        List<RefCdVo> refCdVos = new ArrayList<RefCdVo>();
        List<RefCd> refCds = refCdRepository.findAll();
        for (RefCd refCd : refCds) {
            refCdVos.add(toVo(refCd));
        }
        return refCdVos;
    }

    @Override
    public List<RefCdVo> findListByType(String type) {
        List<RefCdVo> refCdVos = new ArrayList<RefCdVo>();
        List<RefCd> refCds = refCdRepository.findListByType(type);
        for (RefCd refCd : refCds) {
            refCdVos.add(toVo(refCd));
        }
        return refCdVos;
    }

    @Override
    public RefCdVo getByTypeAndCd(String type, String cd) {
        RefCd refCd = refCdRepository.getByTypeAndCd(type, cd);
        return toVo(refCd);
    }

    private RefCd toRefCd(RefCdVo vo) {
        RefCd refCd = new RefCd();
        refCd.setId(vo.getId());
        refCd.setType(vo.getType());
        refCd.setCd(vo.getCd());
        refCd.setValue(vo.getValue());
        refCd.setValueChi(vo.getValueChi());
        refCd.setExpirationDate(vo.getExpirationDate());
        refCd.setVersion(vo.getVersion());
        return refCd;
    }

    private RefCdVo toVo(RefCd refCd) {
        RefCdVo vo = new RefCdVo();
        if (refCd != null) {
            vo.setId(refCd.getId());
            vo.setType(refCd.getType());
            vo.setCd(refCd.getCd());
            vo.setValue(refCd.getValue());
            vo.setValueChi(refCd.getValueChi());
            vo.setExpirationDate(refCd.getExpirationDate());
            vo.setVersion(refCd.getVersion());
        }
        return vo;
    }

}
