package com.accentrix.hku.repository.staff.custom;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.staff.Role;
import com.accentrix.hku.vo.staff.RoleVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 上午10:45:10
 * @version 1.0
 */

@Repository
public interface RoleRepositoryCustom {

    Page<Role> findPage(RoleVo roleVo, Pageable pageable);

    List<RoleVo> findVos(String staffId);

}
