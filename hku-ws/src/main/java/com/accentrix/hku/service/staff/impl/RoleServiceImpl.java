package com.accentrix.hku.service.staff.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.staff.Role;
import com.accentrix.hku.repository.staff.RoleRepository;
import com.accentrix.hku.service.staff.RoleService;
import com.accentrix.hku.vo.staff.RoleForm;
import com.accentrix.hku.vo.staff.RoleVo;
import com.accentrix.hku.timer.annotation.Timer;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 下午2:53:52
 * @version 1.0
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("role")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public RoleVo get(String id) {
        Role role = roleRepository.findOne(id);
        return toVo(role);
    }

    @Transactional
    @Override
    public RoleVo save(RoleVo vo) {
        Role role = toRole(vo);
        role = roleRepository.save(role);
        vo.setId(role.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<RoleVo> save(List<RoleVo> vos) {
        List<RoleVo> roleVos = new ArrayList<RoleVo>();
        for (RoleVo roleVo : roleVos) {
            Role role = toRole(roleVo);
            role = roleRepository.save(role);
            roleVo.setId(role.getId());
            roleVos.add(roleVo);
        }
        return roleVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        roleRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(RoleVo vo) {
        Role role = toRole(vo);
        roleRepository.delete(role);
    }

    @Override
    public List<RoleVo> findList() {
        List<RoleVo> roleVos = new ArrayList<RoleVo>();
        List<Role> roles = roleRepository.findAll();
        for (Role role : roles) {
            roleVos.add(toVo(role));
        }
        return roleVos;
    }

    private Role toRole(RoleVo vo) {
        Role role = new Role();
        role.setId(vo.getId());
        role.setRoleName(vo.getRoleName());
        role.setDescription(vo.getDescription());
        role.setIsActive(vo.getIsActive());
        return role;
    }

    private RoleVo toVo(Role role) {
        RoleVo vo = new RoleVo();
        vo.setId(role.getId());
        vo.setRoleName(role.getRoleName());
        vo.setDescription(role.getDescription());
        vo.setIsActive(role.getIsActive());
        return vo;
    }

    @Override
    public Page<RoleVo> findPage(RoleForm roleForm) {
        Page<Role> rolePage = roleRepository.findPage(roleForm.getRoleVo(), roleForm.getPageable());
        return rolePage.map(s -> toVo(s));
    }

    @Override
    public List<RoleVo> findVos(String staffId) {
        return roleRepository.findVos(staffId);
    }
}
