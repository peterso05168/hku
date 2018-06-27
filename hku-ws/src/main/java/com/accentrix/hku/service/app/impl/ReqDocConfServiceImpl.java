package com.accentrix.hku.service.app.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.app.ReqDocConf;
import com.accentrix.hku.repository.app.ReqDocConfRepository;
import com.accentrix.hku.service.app.ReqDocConfService;
import com.accentrix.hku.vo.app.ReqDocConfVo;
import com.accentrix.hku.timer.annotation.Timer;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年1月31日 下午2:26:41 
 * @version 1.0 
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("reqDocConf")
public class ReqDocConfServiceImpl implements ReqDocConfService {

    @Autowired
    private ReqDocConfRepository reqDocConfRepository;

    @Override
    public ReqDocConfVo get(String id) {
        ReqDocConf reqDocConf = reqDocConfRepository.findOne(id);
        return toVo(reqDocConf);
    }

    @Transactional
    @Override
    public ReqDocConfVo save(ReqDocConfVo vo) {
        ReqDocConf reqDocConf = toReqDocConf(vo);
        reqDocConf = reqDocConfRepository.save(reqDocConf);
        vo.setId(reqDocConf.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<ReqDocConfVo> save(List<ReqDocConfVo> vos) {
        List<ReqDocConfVo> reqDocConfVos = new ArrayList<ReqDocConfVo>();
        for (ReqDocConfVo reqDocConfVo : vos) {
            ReqDocConf reqDocConf = toReqDocConf(reqDocConfVo);
            reqDocConf = reqDocConfRepository.save(reqDocConf);
            reqDocConfVo.setId(reqDocConf.getId());
            reqDocConfVos.add(reqDocConfVo);
        }
        return reqDocConfVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        reqDocConfRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(ReqDocConfVo vo) {
        reqDocConfRepository.delete(toReqDocConf(vo));
    }

    @Override
    public List<ReqDocConfVo> findList() {
        List<ReqDocConf> reqDocConfs = reqDocConfRepository.findAll();
        List<ReqDocConfVo> vos = new ArrayList<ReqDocConfVo>();
        for (ReqDocConf reqDocConf : reqDocConfs) {
            vos.add(toVo(reqDocConf));
        }
        return vos;
    }

    private ReqDocConf toReqDocConf(ReqDocConfVo vo) {
        ReqDocConf reqDocConf = new ReqDocConf();
        reqDocConf.setReqDocCd(vo.getReqDocCd());
        reqDocConf.setReqDocName(vo.getReqDocName());
        reqDocConf.setReqDocNameChi(vo.getReqDocNameChi());
        reqDocConf.setReqDocType(vo.getReqDocType());
        reqDocConf.setToolTipMsg(vo.getToolTipMsg());
        return reqDocConf;
    }

    private ReqDocConfVo toVo(ReqDocConf reqDocConf) {
        ReqDocConfVo vo = new ReqDocConfVo();
        vo.setId(reqDocConf.getId());
        vo.setReqDocCd(reqDocConf.getReqDocCd());
        vo.setReqDocName(reqDocConf.getReqDocName());
        vo.setReqDocNameChi(reqDocConf.getReqDocNameChi());
        vo.setReqDocType(reqDocConf.getReqDocType());
        vo.setToolTipMsg(reqDocConf.getToolTipMsg());
        return vo;
    }

    @Override
    public ReqDocConfVo getByTypeAndCdAndName(String type, String cd, String desc) {
        ReqDocConf reqDocConf = reqDocConfRepository.getByTypeAndCdAndName(type, cd, desc);
        return reqDocConf != null ? toVo(reqDocConf) : null;
    }

}
