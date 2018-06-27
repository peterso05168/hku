package com.accentrix.hku.repository.staff.impl;

import java.util.List;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.staff.QRolePrivilege;
import com.accentrix.hku.domain.staff.RolePrivilege;
import com.accentrix.hku.repository.staff.custom.RolePrivilegeRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 上午10:45:39
 * @version 1.0
 */

public class RolePrivilegeRepositoryImpl extends JpaDslQuery<RolePrivilege, QRolePrivilege>
        implements RolePrivilegeRepositoryCustom {

    @Override
    public List<RolePrivilege> findByRoleId(String roleId) {
        eq($.staffRoleId, roleId);
        return list();
    }

}
