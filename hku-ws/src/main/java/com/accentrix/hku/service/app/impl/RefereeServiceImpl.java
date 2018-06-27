package com.accentrix.hku.service.app.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.app.Referee;
import com.accentrix.hku.repository.app.RefereeRepository;
import com.accentrix.hku.service.app.RefereeService;
import com.accentrix.hku.vo.app.RefereeVo;
import com.accentrix.hku.timer.annotation.Timer;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 下午2:25:39
 * @version 1.0
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("referee")
public class RefereeServiceImpl implements RefereeService {

    @Autowired
    private RefereeRepository refereeRepository;

    @Override
    public RefereeVo get(String id) {
        Referee referee = refereeRepository.findOne(id);
        return toVo(referee);
    }

    @Transactional
    @Override
    public RefereeVo save(RefereeVo vo) {
        Referee referee = toReferee(vo);
        referee.setId(vo.getId());
        referee.setCreateBy(vo.getCreateBy());
        referee.setCreateDate(vo.getCreateDate());
        referee.setUpdateBy(vo.getUpdateBy());
        referee.setUpdateDate(vo.getUpdateDate());
        referee.setVersion(vo.getVersion());
        referee = refereeRepository.save(referee);
        vo.setId(referee.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<RefereeVo> save(List<RefereeVo> vos) {
        List<RefereeVo> refereeVos = new ArrayList<RefereeVo>();
        for (RefereeVo vo : vos) {
            Referee referee = toReferee(vo);
            referee = refereeRepository.save(referee);
            vo.setId(referee.getId());
            refereeVos.add(vo);
        }
        return refereeVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        Referee referee = refereeRepository.findOne(id);
        if (referee != null) {
            referee.setIsDeleted(true);
            refereeRepository.save(referee);
        }
    }

    @Transactional
    @Override
    public void delete(RefereeVo vo) {
        refereeRepository.delete(toReferee(vo));
    }

    @Override
    public List<RefereeVo> findList() {
        List<Referee> referees = refereeRepository.findAll();
        List<RefereeVo> vos = new ArrayList<RefereeVo>();
        for (Referee referee : referees) {
            vos.add(toVo(referee));
        }
        return vos;
    }

    @Override
    public List<RefereeVo> findListByReferenceId(String referenceId) {
        List<Referee> referees = refereeRepository.findListByReferenceId(referenceId);
        List<RefereeVo> vos = new ArrayList<RefereeVo>();
        for (Referee referee : referees) {
            vos.add(toVo(referee));
        }
        return vos;
    }

    private Referee toReferee(RefereeVo vo) {
        Referee referee = new Referee();
        referee.setReferenceId(vo.getReferenceId());
        referee.setRefereeRelationshipCd(vo.getRefereeRelationshipCd());
        referee.setName(vo.getName());
        referee.setEmail(vo.getEmail());
        referee.setIsDeleted(vo.getIsDeleted());
        referee.setCounselorId(vo.getCounselorId());
        return referee;
    }

    private RefereeVo toVo(Referee referee) {
        RefereeVo vo = new RefereeVo();
        vo.setId(referee.getId());
        vo.setReferenceId(referee.getReferenceId());
        vo.setRefereeRelationshipCd(referee.getRefereeRelationshipCd());
        vo.setName(referee.getName());
        vo.setEmail(referee.getEmail());
        vo.setIsDeleted(referee.getIsDeleted());
        vo.setCounselorId(referee.getCounselorId());
        vo.setCreateBy(referee.getCreateBy());
        vo.setCreateDate(referee.getCreateDate());
        vo.setUpdateBy(referee.getUpdateBy());
        vo.setUpdateDate(referee.getUpdateDate());
        vo.setVersion(referee.getVersion());
        return vo;
    }
}
