package com.accentrix.hku.service.scholar.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.scholar.Scholar;
import com.accentrix.hku.repository.scholar.ScholarRepository;
import com.accentrix.hku.service.scholar.ScholarService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.scholar.ScholarVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年3月19日 上午11:45:20
 * @version 1.0
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("scholar")
public class ScholarServiceImpl implements ScholarService {

    @Autowired
    private ScholarRepository scholarRepository;

    @Override
    public ScholarVo get(String id) {
        Scholar scholar = scholarRepository.findOne(id);
        return toVo(scholar);
    }

    @Transactional
    @Override
    public ScholarVo save(ScholarVo vo) {
        Scholar scholar = toScholar(vo);
        scholar = scholarRepository.save(scholar);
        vo.setId(scholar.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<ScholarVo> save(List<ScholarVo> vos) {
        List<ScholarVo> scholarVos = new ArrayList<ScholarVo>();
        for (ScholarVo scholarVo : vos) {
            Scholar scholar = toScholar(scholarVo);
            scholar = scholarRepository.save(scholar);
            scholarVo.setId(scholar.getId());
            scholarVos.add(scholarVo);
        }
        return scholarVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        scholarRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(ScholarVo vo) {
        Scholar scholar = toScholar(vo);
        scholarRepository.delete(scholar);
    }

    @Override
    public List<ScholarVo> findList() {
        List<Scholar> list = scholarRepository.findAll();
        List<ScholarVo> scholarVos = new ArrayList<ScholarVo>();
        for (Scholar scholar : list) {
            scholarVos.add(toVo(scholar));
        }
        return scholarVos;
    }

    @Override
    public ScholarVo getByName(String name) {
        Scholar scholar = scholarRepository.getByName(name);
        return toVo(scholar);
    }

    private ScholarVo toVo(Scholar scholar) {
        ScholarVo vo = new ScholarVo();
        if (scholar != null) {
            vo.setId(scholar.getId());
            vo.setName(scholar.getName());
            vo.setStatus(scholar.getStatus());
            vo.setNotAppEndDate(scholar.getNotAppEndDate());
            vo.setScholarStartDate(scholar.getScholarStartDate());
            vo.setScholarEndDate(scholar.getScholarEndDate());
        }
        return vo;
    }

    private Scholar toScholar(ScholarVo vo) {
        Scholar scholar = new Scholar();
        scholar.setId(vo.getId());
        scholar.setName(vo.getName());
        scholar.setStatus(vo.getStatus());
        scholar.setNotAppEndDate(vo.getNotAppEndDate());
        scholar.setScholarStartDate(vo.getScholarStartDate());
        scholar.setScholarEndDate(vo.getScholarEndDate());
        return scholar;
    }

    @Override
    public List<ScholarVo> basicSearch(String criteria) {
        List<Scholar> list = scholarRepository.basicSearch(criteria);
        List<ScholarVo> scholarVos = new ArrayList<ScholarVo>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (Scholar scholar : list) {
                scholarVos.add(toVo(scholar));
            }
        }
        return scholarVos;
    }

    @Override
    public List<ScholarVo> advancedSearch(ScholarVo searchVo) {
        List<Scholar> list = scholarRepository.advancedSearch(searchVo);
        List<ScholarVo> scholarVos = new ArrayList<ScholarVo>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (Scholar scholar : list) {
                scholarVos.add(toVo(scholar));
            }
        }
        return scholarVos;
    }

}
