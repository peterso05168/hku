package com.accentrix.hku.service.app.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.app.Progress;
import com.accentrix.hku.repository.app.ProgressRepository;
import com.accentrix.hku.service.app.ProgressService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.app.ProgressVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年3月1日 上午10:45:40
 * @version 1.0
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("progress")
public class ProgressServiceImpl implements ProgressService {

    @Autowired
    private ProgressRepository progressRepository;

    @Override
    public ProgressVo get(String id) {
        Progress progress = progressRepository.findOne(id);
        return toVo(progress);
    }

    @Transactional
    @Override
    public ProgressVo save(ProgressVo vo) {
        Progress progress = toProgress(vo);
        progress.setId(vo.getId());
        progress.setCreateBy(vo.getCreateBy());
        progress.setCreateDate(vo.getCreateDate());
        progress.setUpdateBy(vo.getUpdateBy());
        progress.setUpdateDate(vo.getUpdateDate());
        progress.setVersion(vo.getVersion());
        progress = progressRepository.save(progress);
        vo.setId(progress.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<ProgressVo> save(List<ProgressVo> vos) {
        List<ProgressVo> progressVos = new ArrayList<ProgressVo>();
        for (ProgressVo progressVo : vos) {
            Progress progress = toProgress(progressVo);
            progress = progressRepository.save(progress);
            progressVo.setId(progress.getId());
            progressVos.add(progressVo);
        }
        return progressVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        progressRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(ProgressVo vo) {
        Progress progress = toProgress(vo);
        progressRepository.delete(progress);
    }

    @Override
    public List<ProgressVo> findList(ProgressVo vo) {
        List<Progress> list = progressRepository.findList(vo);
        List<ProgressVo> vos = new ArrayList<ProgressVo>();
        for (Progress progress : list) {
            vos.add(toVo(progress));
        }
        return vos;
    }

    @Override
    public ProgressVo findByApplicationId(String applicationId) {
        Progress progress = progressRepository.findByApplicationId(applicationId);
        if (progress == null) {
            return null;
        }
        return toVo(progress);
    }

    private ProgressVo toVo(Progress progress) {
        ProgressVo vo = new ProgressVo();
        vo.setId(progress.getId());
        vo.setApplicationId(progress.getApplicationId());
        vo.setPrsnalPart(progress.getPrsnalPart());
        vo.setAcadBg(progress.getAcadBg());
        vo.setOtherQuali(progress.getOtherQuali());
        vo.setChoiceOfCurri(progress.getChoiceOfCurri());
        vo.setExpAndAchi(progress.getExpAndAchi());
        vo.setReference(progress.getReference());
        vo.setOthers(progress.getOthers());
        vo.setCreateBy(progress.getCreateBy());
        vo.setUpdateBy(progress.getUpdateBy());
        vo.setCreateDate(progress.getCreateDate());
        vo.setUpdateDate(progress.getUpdateDate());
        vo.setVersion(progress.getVersion());
        return vo;
    }

    private Progress toProgress(ProgressVo vo) {
        Progress progress = new Progress();
        progress.setApplicationId(vo.getApplicationId());
        progress.setPrsnalPart(vo.getPrsnalPart());
        progress.setAcadBg(vo.getAcadBg());
        progress.setOtherQuali(vo.getOtherQuali());
        progress.setChoiceOfCurri(vo.getChoiceOfCurri());
        progress.setExpAndAchi(vo.getExpAndAchi());
        progress.setReference(vo.getReference());
        progress.setOthers(vo.getOthers());
        return progress;
    }
}
