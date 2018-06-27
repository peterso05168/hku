package com.accentrix.hku.service.app.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.app.MfeScheme;
import com.accentrix.hku.repository.app.MfeSchemeRepository;
import com.accentrix.hku.service.app.MfeSchemeService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.app.MfeSchemeVo;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年4月3日 下午6:51:21
 */
@Service
@Transactional(readOnly = true)
@Timer
@Path("mfeScheme")
public class MfeSchemeServiceImpl implements MfeSchemeService {

    @Autowired
    private MfeSchemeRepository mfeSchemeRepository;

    @Override
    public MfeSchemeVo get(String id) {
        MfeScheme mfeScheme = mfeSchemeRepository.findOne(id);
        return toVo(mfeScheme);
    }

    @Transactional
    @Override
    public MfeSchemeVo save(MfeSchemeVo vo) {
        MfeScheme mfeScheme = toMfeScheme(vo);
        mfeScheme = mfeSchemeRepository.save(mfeScheme);
        vo.setId(mfeScheme.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<MfeSchemeVo> save(List<MfeSchemeVo> vos) {
        List<MfeSchemeVo> mfeSchemeVos = new ArrayList<MfeSchemeVo>();
        for (MfeSchemeVo mfeSchemeVo : vos) {
            MfeScheme mfeScheme = toMfeScheme(mfeSchemeVo);
            mfeScheme = mfeSchemeRepository.save(mfeScheme);
            mfeSchemeVo.setId(mfeScheme.getId());
            mfeSchemeVos.add(mfeSchemeVo);
        }
        return mfeSchemeVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        mfeSchemeRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(MfeSchemeVo vo) {
        mfeSchemeRepository.delete(toMfeScheme(vo));
    }

    @Override
    public List<MfeSchemeVo> findList() {
        List<MfeScheme> mfeSchemes = mfeSchemeRepository.findAll();
        List<MfeSchemeVo> mfeSchemeVos = new ArrayList<MfeSchemeVo>();
        for (MfeScheme mfeScheme : mfeSchemes) {
            MfeSchemeVo vo = toVo(mfeScheme);
            mfeSchemeVos.add(vo);
        }
        return mfeSchemeVos;
    }

    @Override
    public MfeSchemeVo getByQualificationIdAndYear(String qualificationId, Integer yearSemester) {
        MfeScheme mfeScheme = mfeSchemeRepository.getByQualificationIdAndYear(qualificationId, yearSemester);
        return toVo(mfeScheme);
    }

    private MfeScheme toMfeScheme(MfeSchemeVo vo) {
        MfeScheme mfeScheme = new MfeScheme();
        mfeScheme.setId(vo.getId());
        mfeScheme.setQualificationsId(vo.getQualificationsId());
        mfeScheme.setChinese(vo.getChinese());
        mfeScheme.setMaths(vo.getMaths());
        mfeScheme.setEnglish(vo.getEnglish());
        mfeScheme.setPhysics(vo.getPhysics());
        mfeScheme.setChemistry(vo.getChemistry());
        mfeScheme.setBiology(vo.getBiology());
        mfeScheme.setHistory(vo.getHistory());
        mfeScheme.setGeography(vo.getGeography());
        mfeScheme.setPolitics(vo.getPolitics());
        mfeScheme.setTechnology(vo.getTechnology());
        mfeScheme.setIntegratedArts(vo.getIntegratedArts());
        mfeScheme.setIntegratedScience(vo.getIntegratedScience());
        mfeScheme.setTotal(vo.getTotal());
        mfeScheme.setRank(vo.getRank());
        mfeScheme.setTotalStudents(vo.getTotalStudents());
        mfeScheme.setIntegratedRank(vo.getIntegratedRank());
        mfeScheme.setYearSemester(vo.getYearSemester());
        return mfeScheme;
    }

    private MfeSchemeVo toVo(MfeScheme mfeScheme) {
        MfeSchemeVo vo = new MfeSchemeVo();
        if (mfeScheme != null) {
            vo.setId(mfeScheme.getId());
            vo.setQualificationsId(mfeScheme.getQualificationsId());
            vo.setChinese(mfeScheme.getChinese());
            vo.setMaths(mfeScheme.getMaths());
            vo.setEnglish(mfeScheme.getEnglish());
            vo.setPhysics(mfeScheme.getPhysics());
            vo.setChemistry(mfeScheme.getChemistry());
            vo.setBiology(mfeScheme.getBiology());
            vo.setHistory(mfeScheme.getHistory());
            vo.setGeography(mfeScheme.getGeography());
            vo.setPolitics(mfeScheme.getPolitics());
            vo.setTechnology(mfeScheme.getTechnology());
            vo.setIntegratedArts(mfeScheme.getIntegratedArts());
            vo.setIntegratedScience(mfeScheme.getIntegratedScience());
            vo.setTotal(mfeScheme.getTotal());
            vo.setRank(mfeScheme.getRank());
            vo.setTotalStudents(mfeScheme.getTotalStudents());
            vo.setIntegratedRank(mfeScheme.getIntegratedRank());
            vo.setYearSemester(mfeScheme.getYearSemester());
        }
        return vo;
    }

}
