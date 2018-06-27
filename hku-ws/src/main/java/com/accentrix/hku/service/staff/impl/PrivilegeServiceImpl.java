package com.accentrix.hku.service.staff.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.staff.Privilege;
import com.accentrix.hku.repository.staff.PrivilegeRepository;
import com.accentrix.hku.service.staff.PrivilegeService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.staff.PrivilegeVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年3月15日 上午11:08:52 
 * @version 1.0 
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("privilege")
public class PrivilegeServiceImpl implements PrivilegeService {

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Override
    public PrivilegeVo get(String id) {
        Privilege privilege = privilegeRepository.findOne(id);
        return toVo(privilege);
    }

    @Transactional
    @Override
    public PrivilegeVo save(PrivilegeVo vo) {
        Privilege privilege = toPrivilege(vo);
        privilege = privilegeRepository.save(privilege);
        vo.setId(privilege.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<PrivilegeVo> save(List<PrivilegeVo> vos) {
        List<PrivilegeVo> privilegeVos = new ArrayList<PrivilegeVo>();
        for (PrivilegeVo privilegeVo : vos) {
            Privilege privilege = toPrivilege(privilegeVo);
            privilege = privilegeRepository.save(privilege);
            privilegeVo.setId(privilege.getId());
            privilegeVos.add(privilegeVo);
        }
        return privilegeVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        privilegeRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(PrivilegeVo vo) {
        privilegeRepository.delete(toPrivilege(vo));
    }

    @Override
    public List<PrivilegeVo> findList() {
        List<Privilege> list = privilegeRepository.findAll();
        List<PrivilegeVo> vos = new ArrayList<PrivilegeVo>();
        for (Privilege privilege : list) {
            vos.add(toVo(privilege));
        }
        return vos;
    }

    @Override
    public List<PrivilegeVo> findVos(String roleId) {
        return privilegeRepository.findListGroupConcat(roleId);
    }

    private PrivilegeVo toVo(Privilege privilege) {
        PrivilegeVo vo = new PrivilegeVo();
        vo.setId(privilege.getId());
        vo.setModule(privilege.getModule());
        vo.setModuleCd(privilege.getModuleCd());
        vo.setPrivilege(privilege.getPrivilege());
        vo.setPrivilegeDesc(privilege.getPrivilegeDesc());
        return vo;
    }

    private Privilege toPrivilege(PrivilegeVo vo) {
        Privilege privilege = new Privilege();
        privilege.setModule(vo.getModule());
        privilege.setModuleCd(vo.getModuleCd());
        privilege.setPrivilege(vo.getPrivilege());
        privilege.setPrivilegeDesc(vo.getPrivilegeDesc());
        return privilege;
    }

    @Override
    public boolean findModule(String staffInfoId, String module, String moduleCode) {
        return privilegeRepository.findModule(staffInfoId, module, moduleCode);
    }

}
