package com.accentrix.hku.service.staff.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.staff.RolePrivilege;
import com.accentrix.hku.repository.staff.RolePrivilegeRepository;
import com.accentrix.hku.service.staff.RolePrivilegeService;
import com.accentrix.hku.vo.staff.RolePrivilegeVo;
import com.accentrix.hku.timer.annotation.Timer;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 下午2:55:40
 * @version 1.0
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("rolePrivilege")
public class RolePrivilegeServiceImpl implements RolePrivilegeService {

    @Autowired
    private RolePrivilegeRepository rolePrivilegeRepository;

    @Override
    public RolePrivilegeVo get(String id) {
        RolePrivilege rolePrivilege = rolePrivilegeRepository.findOne(id);
        return toVo(rolePrivilege);
    }

    @Transactional
    @Override
    public RolePrivilegeVo save(RolePrivilegeVo vo) {
        RolePrivilege rolePrivilege = toRolePrivilege(vo);
        vo.setId(rolePrivilege.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<RolePrivilegeVo> save(List<RolePrivilegeVo> vos) {
        List<RolePrivilegeVo> rolePrivilegeVos = new ArrayList<RolePrivilegeVo>();
        for (RolePrivilegeVo rolePrivilegeVo : rolePrivilegeVos) {
            RolePrivilege rolePrivilege = toRolePrivilege(rolePrivilegeVo);
            rolePrivilege = rolePrivilegeRepository.save(rolePrivilege);
            rolePrivilegeVo.setId(rolePrivilege.getId());
            rolePrivilegeVos.add(rolePrivilegeVo);
        }
        return rolePrivilegeVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        rolePrivilegeRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(RolePrivilegeVo vo) {
        RolePrivilege rolePrivilege = toRolePrivilege(vo);
        rolePrivilegeRepository.delete(rolePrivilege);
    }

    @Override
    public List<RolePrivilegeVo> findList() {
        List<RolePrivilegeVo> privilegeVos = new ArrayList<RolePrivilegeVo>();
        List<RolePrivilege> privileges = rolePrivilegeRepository.findAll();
        for (RolePrivilege rolePrivilege : privileges) {
            privilegeVos.add(toVo(rolePrivilege));
        }
        return privilegeVos;
    }

    private RolePrivilege toRolePrivilege(RolePrivilegeVo vo) {
        RolePrivilege rolePrivilege = new RolePrivilege();
        rolePrivilege.setStaffRoleId(vo.getStaffRoleId());
        rolePrivilege.setStaffPrivilegeId(vo.getStaffPrivilegeId());
        return rolePrivilege;
    }

    private RolePrivilegeVo toVo(RolePrivilege rolePrivilege) {
        RolePrivilegeVo vo = new RolePrivilegeVo();
        vo.setId(rolePrivilege.getId());
        vo.setStaffRoleId(rolePrivilege.getStaffRoleId());
        vo.setStaffPrivilegeId(rolePrivilege.getStaffPrivilegeId());
        return vo;
    }

    @Transactional
    @Override
    public void saveRolePrivilege(String roleId, List<String> privilegeIds) {
        List<RolePrivilege> list = rolePrivilegeRepository.findByRoleId(roleId);
        rolePrivilegeRepository.delete(list);
        list = new ArrayList<RolePrivilege>();
        for (String id : privilegeIds) {
            RolePrivilege rolePrivilege = new RolePrivilege();
            rolePrivilege.setStaffRoleId(roleId);
            rolePrivilege.setStaffPrivilegeId(id);
            list.add(rolePrivilege);
        }
        rolePrivilegeRepository.save(list);
    }
}
