package com.accentrix.hku.service.app.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Path;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.constant.Constants;
import com.accentrix.hku.domain.app.SpecialScheme;
import com.accentrix.hku.repository.app.SpecialSchemeRepository;
import com.accentrix.hku.service.app.SpecialSchemeService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.app.SpecialSchemeVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 下午3:14:50
 * @version 1.0
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("specialScheme")
public class SpecialSchemeServiceImpl implements SpecialSchemeService {

    @Autowired
    private SpecialSchemeRepository specialSchemeRepository;

    @Override
    public SpecialSchemeVo get(String id) {
        SpecialScheme specialScheme = specialSchemeRepository.findOne(id);
        return toVo(specialScheme);
    }

    @Transactional
    @Override
    public SpecialSchemeVo save(SpecialSchemeVo vo) {
        SpecialScheme specialScheme = toSpecialScheme(vo);
        specialScheme = specialSchemeRepository.save(specialScheme);
        vo.setId(specialScheme.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<SpecialSchemeVo> save(List<SpecialSchemeVo> vos) {
        List<SpecialSchemeVo> specialSchemeVos = new ArrayList<SpecialSchemeVo>();
        for (SpecialSchemeVo specialSchemeVo : vos) {
            SpecialScheme specialScheme = toSpecialScheme(specialSchemeVo);
            specialScheme = specialSchemeRepository.save(specialScheme);
            specialSchemeVo.setId(specialScheme.getId());
            specialSchemeVos.add(specialSchemeVo);
        }
        return specialSchemeVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        specialSchemeRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(SpecialSchemeVo vo) {
        specialSchemeRepository.delete(toSpecialScheme(vo));
    }

    @Override
    public List<SpecialSchemeVo> findList() {
        List<SpecialScheme> specialSchemes = specialSchemeRepository.findAll();
        List<SpecialSchemeVo> vos = new ArrayList<SpecialSchemeVo>();
        for (SpecialScheme specialScheme : specialSchemes) {
            vos.add(toVo(specialScheme));
        }
        return vos;
    }

    @Override
    public SpecialSchemeVo getByCodeAndApplicationId(String code, String applicationId) {
        SpecialScheme specialScheme = specialSchemeRepository.getByCodeAndApplicationId(code, applicationId);
        return toVo(specialScheme);
    }

    private SpecialScheme toSpecialScheme(SpecialSchemeVo vo) {
        SpecialScheme specialScheme = new SpecialScheme();
        specialScheme.setId(vo.getId());
        specialScheme.setApplicationId(vo.getApplicationId());
        specialScheme.setSpecialSchemeCd(vo.getSpecialSchemeCd());
        specialScheme.setSptAppScheme(vo.getSptAppScheme());
        specialScheme.setSptSports(vo.getSptSports());
        specialScheme.setSptLevel(vo.getSptLevel());
        specialScheme.setSptLevelOthers(vo.getSptLevelOthers());
        specialScheme.setSptHyperlink(vo.getSptHyperlink());
        return specialScheme;
    }

    private SpecialSchemeVo toVo(SpecialScheme specialScheme) {
        SpecialSchemeVo vo = new SpecialSchemeVo();
        if (specialScheme != null) {
            vo.setId(specialScheme.getId());
            vo.setApplicationId(specialScheme.getApplicationId());
            vo.setSpecialSchemeCd(specialScheme.getSpecialSchemeCd());
            vo.setSptAppScheme(specialScheme.getSptAppScheme());
            vo.setSptSports(specialScheme.getSptSports());
            vo.setSptLevel(specialScheme.getSptLevel());
            vo.setSptLevelOthers(specialScheme.getSptLevelOthers());
            vo.setSptHyperlink(specialScheme.getSptHyperlink());
            if (StringUtils.isNotBlank(vo.getSptLevel())) {
                String[] split = vo.getSptLevel().split(",");
                vo.setSptLevels(Arrays.asList(split));
                if (vo.getSptLevels().contains(Constants.OTHERS)) {
                    vo.setIsActiveSptLevelOthers(true);
                }
            } else {
                vo.setSptLevels(new ArrayList<String>());
            }
        }
        return vo;
    }
}
