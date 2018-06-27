package com.accentrix.hku.service.app.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.app.Counselor;
import com.accentrix.hku.repository.app.CounselorRepository;
import com.accentrix.hku.service.app.CounselorService;
import com.accentrix.hku.vo.app.CounselorVo;
import com.accentrix.hku.timer.annotation.Timer;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 下午2:20:37
 * @version 1.0
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("counselor")
public class CounselorServiceImpl implements CounselorService {

    @Autowired
    private CounselorRepository counselorRepository;

    @Override
    public CounselorVo get(String id) {
        Counselor counselor = counselorRepository.findOne(id);
        return toVo(counselor);
    }

    @Transactional
    @Override
    public CounselorVo save(CounselorVo vo) {
        Counselor counselor = toCounselor(vo);
        counselor = counselorRepository.save(counselor);
        vo.setId(counselor.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<CounselorVo> save(List<CounselorVo> vos) {
        List<CounselorVo> counselorVos = new ArrayList<CounselorVo>();
        for (CounselorVo counselorVo : vos) {
            Counselor counselor = toCounselor(counselorVo);
            counselor = counselorRepository.save(counselor);
            counselorVo.setId(counselor.getId());
            counselorVos.add(counselorVo);
        }
        return counselorVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        counselorRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(CounselorVo vo) {
        counselorRepository.delete(toCounselor(vo));
    }

    @Override
    public List<CounselorVo> findList() {
        List<Counselor> counselors = counselorRepository.findAll();
        List<CounselorVo> vos = new ArrayList<CounselorVo>();
        for (Counselor counselor : counselors) {
            vos.add(toVo(counselor));
        }
        return vos;
    }

    @Override
    public CounselorVo findByFullName(String fullName) {
        Counselor counselor = counselorRepository.findByFullName(fullName);
        return toVo(counselor);
    }

    private Counselor toCounselor(CounselorVo vo) {
        Counselor counselor = new Counselor();
        counselor.setCounselorCd(vo.getCounselorCd());
        counselor.setEmail(vo.getEmail());
        counselor.setSurname(vo.getSurname());
        counselor.setGivenName(vo.getGivenName());
        counselor.setFullName(vo.getFullName());
        return counselor;
    }

    private CounselorVo toVo(Counselor counselor) {
        CounselorVo vo = new CounselorVo();
        if (counselor != null) {
            vo.setId(counselor.getId());
            vo.setCounselorCd(counselor.getCounselorCd());
            vo.setEmail(counselor.getEmail());
            vo.setSurname(counselor.getSurname());
            vo.setGivenName(counselor.getGivenName());
            vo.setFullName(counselor.getFullName());
            vo.setCreateBy(counselor.getCreateBy());
            vo.setUpdateBy(counselor.getUpdateBy());
            vo.setCreateDate(counselor.getCreateDate());
            vo.setUpdateDate(counselor.getUpdateDate());
            vo.setVersion(counselor.getVersion());
        }
        return vo;
    }
}
