package com.accentrix.hku.service.applicant.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Path;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.applicant.Anncmnt;
import com.accentrix.hku.repository.applicant.AnncmntRepository;
import com.accentrix.hku.service.applicant.AnncmntService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.applicant.AnncmntVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年1月31日 下午2:29:40 
 * @version 1.0 
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("anncmnt")
public class AnncmntServiceImpl implements AnncmntService {

    @Autowired
    private AnncmntRepository anncmntRepository;

    @Override
    public AnncmntVo get(String id) {
        Anncmnt anncmnt = anncmntRepository.findOne(id);
        return toVo(anncmnt);
    }

    @Transactional
    @Override
    public AnncmntVo save(AnncmntVo vo) {
        Anncmnt anncmnt = toAnncmnt(vo);
        anncmnt = anncmntRepository.save(anncmnt);
        vo.setId(anncmnt.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<AnncmntVo> save(List<AnncmntVo> vos) {
        List<AnncmntVo> anncmntVos = new ArrayList<AnncmntVo>();
        for (AnncmntVo anncmntVo : vos) {
            Anncmnt anncmnt = toAnncmnt(anncmntVo);
            anncmnt = anncmntRepository.save(anncmnt);
            anncmntVo.setId(anncmnt.getId());
            anncmntVos.add(anncmntVo);
        }
        return anncmntVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        anncmntRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(AnncmntVo anncmntVo) {
        anncmntRepository.delete(toAnncmnt(anncmntVo));
    }

    @Override
    public List<AnncmntVo> findList() {
        List<AnncmntVo> anncmntVos = new ArrayList<AnncmntVo>();
        List<Anncmnt> anncmnts = anncmntRepository.findAll();
        for (Anncmnt anncmnt : anncmnts) {
            anncmntVos.add(toVo(anncmnt));
        }
        return anncmntVos;
    }

    private Anncmnt toAnncmnt(AnncmntVo vo) {
        Anncmnt anncmnt = new Anncmnt();
        if (StringUtils.isNotBlank(vo.getId()))
            anncmnt.setId(vo.getId());
        anncmnt.setTypeCd(vo.getTypeCd());
        anncmnt.setValue(vo.getValue());
        anncmnt.setMsgContent(vo.getMsgContent());
        anncmnt.setStatusCd(vo.getStatusCd());
        anncmnt.setIssueDate(vo.getIssueDate());
        anncmnt.setApplicantAccountId(vo.getApplicantAccountId());
        anncmnt.setApplicationNo(vo.getApplicationNo());
        anncmnt.setIsRead(vo.getIsRead());
        anncmnt.setApplicationId(vo.getApplicationId());
        return anncmnt;
    }

    private AnncmntVo toVo(Anncmnt anncmnt) {
        AnncmntVo vo = new AnncmntVo();
        vo.setId(anncmnt.getId());
        vo.setTypeCd(anncmnt.getTypeCd());
        vo.setValue(anncmnt.getValue());
        vo.setMsgContent(anncmnt.getMsgContent());
        vo.setStatusCd(anncmnt.getStatusCd());
        vo.setIssueDate(anncmnt.getIssueDate());
        vo.setApplicantAccountId(anncmnt.getApplicantAccountId());
        vo.setApplicationNo(anncmnt.getApplicationNo());
        vo.setIsRead(anncmnt.getIsRead());
        vo.setApplicationId(anncmnt.getApplicationId());
        return vo;
    }

    @Override
    public List<AnncmntVo> findByApplicantAccountId(String applicantAccountId) {
        List<AnncmntVo> anncmntVos = new ArrayList<AnncmntVo>();
        List<Anncmnt> anncmnts = anncmntRepository.findByApplicantAccountId(applicantAccountId);
        for (Anncmnt anncmnt : anncmnts) {
            anncmntVos.add(toVo(anncmnt));
        }
        return anncmntVos;
    }

    @Override
    public AnncmntVo getByTypeCdAndAccountIdAndApplicationNo(String typeCd, String accountId, String applicationNo) {
        Anncmnt anncmnt = anncmntRepository.getByTypeCdAndAccountIdAndApplicationNo(typeCd, accountId, applicationNo);
        return anncmnt != null ? toVo(anncmnt) : null;
    }

    @Transactional
    @Override
    public void saveAnncmnt(String typeCd, String value, String statusCd, String accountId, String applicationNo,
            String msgContent, String applicationId) {
        Anncmnt anncmnt = new Anncmnt();
        anncmnt.setTypeCd(typeCd);
        anncmnt.setValue(value);
        anncmnt.setStatusCd(statusCd);
        anncmnt.setIssueDate(new Date());
        anncmnt.setApplicantAccountId(accountId);
        anncmnt.setApplicationNo(applicationNo);
        anncmnt.setIsRead(false);
        anncmnt.setMsgContent(msgContent);
        anncmnt.setApplicationId(applicationId);
        anncmntRepository.save(anncmnt);
    }
}
