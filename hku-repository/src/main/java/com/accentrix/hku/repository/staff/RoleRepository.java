package com.accentrix.hku.repository.staff;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.staff.Role;
import com.accentrix.hku.repository.staff.custom.RoleRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 上午10:45:02
 * @version 1.0
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, String>, RoleRepositoryCustom {

}
