package com.accentrix.hku.repository.staff.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.staff.RolePrivilege;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年1月31日 上午10:45:33 
 * @version 1.0 
 */

@Repository
public interface RolePrivilegeRepositoryCustom {

    List<RolePrivilege> findByRoleId(String roleId);
}
