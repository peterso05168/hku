package com.accentrix.hku.service.app.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.app.TelNo;
import com.accentrix.hku.repository.app.TelNoRepository;
import com.accentrix.hku.service.app.TelNoService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.app.TelNoVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 下午2:28:11
 * @version 1.0
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("telNo")
public class TelNoServiceImpl implements TelNoService {

    @Autowired
    private TelNoRepository telNoRepository;

    @Override
    public TelNoVo get(String id) {
        TelNo telNo = telNoRepository.findOne(id);
        return toVo(telNo);
    }

    @Transactional
    @Override
    public TelNoVo save(TelNoVo vo) {
        TelNo telNo = toTelNo(vo);
        telNo = telNoRepository.save(telNo);
        vo.setId(telNo.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<TelNoVo> save(List<TelNoVo> vos) {
        List<TelNoVo> telNoVos = new ArrayList<TelNoVo>();
        for (TelNoVo telNoVo : vos) {
            TelNo telNo = toTelNo(telNoVo);
            telNo = telNoRepository.save(telNo);
            telNoVo.setId(telNo.getId());
            telNoVos.add(telNoVo);
        }
        return telNoVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        telNoRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(TelNoVo vo) {
        telNoRepository.delete(toTelNo(vo));
    }

    @Override
    public List<TelNoVo> findList() {
        List<TelNo> telNos = telNoRepository.findAll();
        List<TelNoVo> vos = new ArrayList<TelNoVo>();
        for (TelNo telNo : telNos) {
            vos.add(toVo(telNo));
        }
        return vos;
    }

    private TelNo toTelNo(TelNoVo vo) {
        TelNo telNo = new TelNo();
        telNo.setId(vo.getId());
        telNo.setCountryCd(vo.getCountryCd());
        telNo.setAreaCd(vo.getAreaCd());
        telNo.setNumber(vo.getNumber());
        telNo.setCreateBy(vo.getCreateBy());
        telNo.setUpdateBy(vo.getUpdateBy());
        telNo.setCreateDate(vo.getCreateDate());
        telNo.setUpdateDate(vo.getUpdateDate());
        telNo.setVersion(vo.getVersion());
        return telNo;
    }

    private TelNoVo toVo(TelNo telNo) {
        TelNoVo vo = new TelNoVo();
        vo.setId(telNo.getId());
        vo.setCountryCd(telNo.getCountryCd());
        vo.setAreaCd(telNo.getAreaCd());
        vo.setNumber(telNo.getNumber());
        vo.setCreateBy(telNo.getCreateBy());
        vo.setUpdateBy(telNo.getUpdateBy());
        vo.setCreateDate(telNo.getCreateDate());
        vo.setUpdateDate(telNo.getUpdateDate());
        vo.setVersion(telNo.getVersion());
        return vo;
    }

}
