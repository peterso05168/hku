package com.accentrix.hku.repository.staff;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.staff.RoleRel;
import com.accentrix.hku.repository.staff.custom.RoleRelRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年3月15日 上午11:06:04
 * @version 1.0
 */

@Repository
public interface RoleRelRepository extends JpaRepository<RoleRel, String>, RoleRelRepositoryCustom {

}
