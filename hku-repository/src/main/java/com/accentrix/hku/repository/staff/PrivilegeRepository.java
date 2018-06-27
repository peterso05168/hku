package com.accentrix.hku.repository.staff;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.staff.Privilege;
import com.accentrix.hku.repository.staff.custom.PrivilegeRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 上午10:44:13
 * @version 1.0
 */

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, String>, PrivilegeRepositoryCustom {

}
