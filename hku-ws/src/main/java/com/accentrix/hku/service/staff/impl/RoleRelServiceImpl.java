package com.accentrix.hku.service.staff.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.staff.RoleRel;
import com.accentrix.hku.repository.staff.RoleRelRepository;
import com.accentrix.hku.service.staff.RoleRelService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.staff.RoleRelVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年3月15日 上午11:09:10 
 * @version 1.0 
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("roleRel")
public class RoleRelServiceImpl implements RoleRelService {

    @Autowired
    private RoleRelRepository roleRelRepository;

    @Override
    public RoleRelVo get(String id) {
        RoleRel roleRel = roleRelRepository.findOne(id);
        return toVo(roleRel);
    }

    @Transactional
    @Override
    public RoleRelVo save(RoleRelVo vo) {
        RoleRel roleRel = toRoleRel(vo);
        roleRel = roleRelRepository.save(roleRel);
        vo.setId(roleRel.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<RoleRelVo> save(List<RoleRelVo> vos) {
        List<RoleRelVo> roleRelVos = new ArrayList<RoleRelVo>();
        for (RoleRelVo roleRelVo : vos) {
            RoleRel roleRel = toRoleRel(roleRelVo);
            roleRel = roleRelRepository.save(roleRel);
            roleRelVo.setId(roleRel.getId());
            roleRelVos.add(roleRelVo);
        }
        return roleRelVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        roleRelRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(RoleRelVo vo) {
        roleRelRepository.delete(toRoleRel(vo));
    }

    @Override
    public List<RoleRelVo> findList() {
        List<RoleRel> list = roleRelRepository.findAll();
        List<RoleRelVo> vos = new ArrayList<RoleRelVo>();
        for (RoleRel roleRel : list) {
            vos.add(toVo(roleRel));
        }
        return vos;
    }

    private RoleRel toRoleRel(RoleRelVo vo) {
        RoleRel roleRel = new RoleRel();
        roleRel.setStaffInfoId(vo.getStaffInfoId());
        roleRel.setStaffRoleId(vo.getStaffRoleId());
        return roleRel;
    }

    private RoleRelVo toVo(RoleRel roleRel) {
        RoleRelVo vo = new RoleRelVo();
        vo.setId(roleRel.getId());
        vo.setStaffInfoId(roleRel.getStaffInfoId());
        vo.setStaffRoleId(roleRel.getStaffRoleId());
        return vo;
    }

    @Transactional
    @Override
    public void deleteByStaffId(String staffId) {
        List<RoleRel> roleRels = roleRelRepository.findByStaffId(staffId);
        roleRelRepository.delete(roleRels);
    }

}
