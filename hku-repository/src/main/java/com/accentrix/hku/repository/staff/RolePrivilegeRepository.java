package com.accentrix.hku.repository.staff;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.staff.RolePrivilege;
import com.accentrix.hku.repository.staff.custom.RolePrivilegeRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 上午10:45:25
 * @version 1.0
 */

@Repository
public interface RolePrivilegeRepository extends JpaRepository<RolePrivilege, String>, RolePrivilegeRepositoryCustom {

}
