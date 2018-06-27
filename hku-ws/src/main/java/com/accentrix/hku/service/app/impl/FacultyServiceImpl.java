package com.accentrix.hku.service.app.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.app.Faculty;
import com.accentrix.hku.repository.app.FacultyRepository;
import com.accentrix.hku.service.app.FacultyService;
import com.accentrix.hku.vo.app.FacultyVo;
import com.accentrix.hku.timer.annotation.Timer;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 下午2:21:21
 * @version 1.0
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("faculty")
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    @Override
    public FacultyVo get(String id) {
        Faculty faculty = facultyRepository.findOne(id);
        return toVo(faculty);
    }

    @Transactional
    @Override
    public FacultyVo save(FacultyVo vo) {
        Faculty faculty = toFaculty(vo);
        faculty = facultyRepository.save(faculty);
        vo.setId(faculty.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<FacultyVo> save(List<FacultyVo> vos) {
        List<FacultyVo> facultyVos = new ArrayList<FacultyVo>();
        for (FacultyVo facultyVo : vos) {
            Faculty faculty = toFaculty(facultyVo);
            faculty = facultyRepository.save(faculty);
            facultyVo.setId(faculty.getId());
            facultyVos.add(facultyVo);
        }
        return facultyVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        facultyRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(FacultyVo vo) {
        facultyRepository.delete(toFaculty(vo));
    }

    @Override
    public List<FacultyVo> findList() {
        List<Faculty> faculties = facultyRepository.findAll();
        List<FacultyVo> vos = new ArrayList<FacultyVo>();
        for (Faculty faculty : faculties) {
            vos.add(toVo(faculty));
        }
        return vos;
    }

    private Faculty toFaculty(FacultyVo vo) {
        Faculty faculty = new Faculty();
        faculty.setFacultyCd(vo.getFacultyCd());
        faculty.setFacultyDesc(vo.getFacultyDesc());
        return faculty;
    }

    private FacultyVo toVo(Faculty faculty) {
        FacultyVo vo = new FacultyVo();
        vo.setId(faculty.getId());
        vo.setFacultyCd(faculty.getFacultyCd());
        vo.setFacultyDesc(faculty.getFacultyDesc());
        vo.setCreateBy(faculty.getCreateBy());
        vo.setUpdateBy(faculty.getUpdateBy());
        vo.setCreateDate(faculty.getCreateDate());
        vo.setUpdateDate(faculty.getUpdateDate());
        vo.setVersion(faculty.getVersion());
        return vo;
    }

}
