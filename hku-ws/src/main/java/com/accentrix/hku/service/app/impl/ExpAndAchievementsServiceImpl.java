package com.accentrix.hku.service.app.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.app.ExpAndAchievements;
import com.accentrix.hku.repository.app.ExpAndAchievementsRepository;
import com.accentrix.hku.service.app.ExpAndAchievementsService;
import com.accentrix.hku.vo.app.ExpAndAchievementsVo;
import com.accentrix.hku.timer.annotation.Timer;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 下午2:20:56
 * @version 1.0
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("expAndAchievements")
public class ExpAndAchievementsServiceImpl implements ExpAndAchievementsService {

    @Autowired
    private ExpAndAchievementsRepository expAndAchievementsRepository;

    @Override
    public ExpAndAchievementsVo get(String id) {
        ExpAndAchievements expAndAchievements = expAndAchievementsRepository.findOne(id);
        return toVo(expAndAchievements);
    }

    @Transactional
    @Override
    public ExpAndAchievementsVo save(ExpAndAchievementsVo vo) {
        ExpAndAchievements expAndAchievements = toExpAndAchievements(vo);
        expAndAchievements.setId(vo.getId());
        expAndAchievements.setCreateBy(vo.getCreateBy());
        expAndAchievements.setUpdateBy(vo.getUpdateBy());
        expAndAchievements.setCreateDate(vo.getCreateDate());
        expAndAchievements.setUpdateDate(vo.getUpdateDate());
        expAndAchievements.setVersion(vo.getVersion());
        expAndAchievements = expAndAchievementsRepository.save(expAndAchievements);
        vo.setId(expAndAchievements.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<ExpAndAchievementsVo> save(List<ExpAndAchievementsVo> vos) {
        List<ExpAndAchievementsVo> expAndAchievementsVos = new ArrayList<ExpAndAchievementsVo>();
        for (ExpAndAchievementsVo vo : vos) {
            ExpAndAchievements expAndAchievements = toExpAndAchievements(vo);
            expAndAchievements = expAndAchievementsRepository.save(expAndAchievements);
            vo.setId(expAndAchievements.getId());
            expAndAchievementsVos.add(vo);
        }
        return expAndAchievementsVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        ExpAndAchievements expAndAchievements = expAndAchievementsRepository.findOne(id);
        expAndAchievements.setIsDeleted(true);
        expAndAchievementsRepository.save(expAndAchievements);
    }

    @Transactional
    @Override
    public void delete(ExpAndAchievementsVo vo) {
        expAndAchievementsRepository.delete(toExpAndAchievements(vo));
    }

    @Override
    public List<ExpAndAchievementsVo> findList() {
        List<ExpAndAchievements> list = expAndAchievementsRepository.findAll();
        List<ExpAndAchievementsVo> vos = new ArrayList<ExpAndAchievementsVo>();
        for (ExpAndAchievements expAndAchievements : list) {
            vos.add(toVo(expAndAchievements));
        }
        return vos;
    }

    @Override
    public List<ExpAndAchievementsVo> findListByApplicationId(String applicationId) {
        List<ExpAndAchievements> list = expAndAchievementsRepository.findListByApplicationId(applicationId);
        List<ExpAndAchievementsVo> vos = new ArrayList<ExpAndAchievementsVo>();
        for (ExpAndAchievements expAndAchievements : list) {
            vos.add(toVo(expAndAchievements));
        }
        return vos;
    }

    @Override
    public List<ExpAndAchievementsVo> findListByApplicationIdAndCat(String applicationId, String catCd) {
        List<ExpAndAchievements> list = expAndAchievementsRepository.findListByApplicationIdAndCat(applicationId,
                catCd);
        List<ExpAndAchievementsVo> vos = new ArrayList<ExpAndAchievementsVo>();
        for (ExpAndAchievements expAndAchievements : list) {
            vos.add(toVo(expAndAchievements));
        }
        return vos;
    }

    private ExpAndAchievements toExpAndAchievements(ExpAndAchievementsVo vo) {
        ExpAndAchievements expAndAchievements = new ExpAndAchievements();
        expAndAchievements.setApplicationId(vo.getApplicationId());
        expAndAchievements.setActivityCatCd(vo.getActivityCatCd());
        expAndAchievements.setName(vo.getName());
        expAndAchievements.setDateFrom(vo.getDateFrom());
        expAndAchievements.setDateTo(vo.getDateTo());
        expAndAchievements.setOrganizer(vo.getOrganizer());
        expAndAchievements.setRole(vo.getRole());
        expAndAchievements.setIsDeleted(vo.getIsDeleted());
        return expAndAchievements;
    }

    private ExpAndAchievementsVo toVo(ExpAndAchievements expAndAchievements) {
        ExpAndAchievementsVo vo = new ExpAndAchievementsVo();
        vo.setId(expAndAchievements.getId());
        vo.setApplicationId(expAndAchievements.getApplicationId());
        vo.setActivityCatCd(expAndAchievements.getActivityCatCd());
        vo.setName(expAndAchievements.getName());
        vo.setDateFrom(expAndAchievements.getDateFrom());
        vo.setDateTo(expAndAchievements.getDateTo());
        vo.setOrganizer(expAndAchievements.getOrganizer());
        vo.setRole(expAndAchievements.getRole());
        vo.setCreateBy(expAndAchievements.getCreateBy());
        vo.setUpdateBy(expAndAchievements.getUpdateBy());
        vo.setCreateDate(expAndAchievements.getCreateDate());
        vo.setUpdateDate(expAndAchievements.getUpdateDate());
        vo.setVersion(expAndAchievements.getVersion());
        vo.setIsDeleted(expAndAchievements.getIsDeleted());
        return vo;
    }
}
