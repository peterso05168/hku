package com.accentrix.hku.repository.staff.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.staff.RoleRel;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年3月15日 上午11:06:13 
 * @version 1.0 
 */

@Repository
public interface RoleRelRepositoryCustom {

    List<RoleRel> findByStaffId(String staffId);
}
