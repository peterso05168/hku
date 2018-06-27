package com.accentrix.hku.repository.staff.impl;

import java.util.List;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.staff.QRoleRel;
import com.accentrix.hku.domain.staff.RoleRel;
import com.accentrix.hku.repository.staff.custom.RoleRelRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年3月15日 上午11:06:18
 * @version 1.0
 */

public class RoleRelRepositoryImpl extends JpaDslQuery<RoleRel, QRoleRel> implements RoleRelRepositoryCustom {

    @Override
    public List<RoleRel> findByStaffId(String staffId) {
        eq($.staffInfoId, staffId);
        return list();
    }

}
