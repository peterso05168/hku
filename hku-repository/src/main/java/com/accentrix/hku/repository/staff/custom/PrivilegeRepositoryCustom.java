package com.accentrix.hku.repository.staff.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.vo.staff.PrivilegeVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 上午10:44:21
 * @version 1.0
 */

@Repository
public interface PrivilegeRepositoryCustom {

    List<PrivilegeVo> findListGroupConcat(String roleId);

    boolean findModule(String staffInfoId, String module, String moduleCode);
}
