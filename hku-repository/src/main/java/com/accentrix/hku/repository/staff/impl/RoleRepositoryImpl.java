package com.accentrix.hku.repository.staff.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.staff.QRole;
import com.accentrix.hku.domain.staff.Role;
import com.accentrix.hku.repository.staff.custom.RoleRepositoryCustom;
import com.accentrix.hku.vo.staff.RoleVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 上午10:45:16
 * @version 1.0
 */

public class RoleRepositoryImpl extends JpaDslQuery<Role, QRole> implements RoleRepositoryCustom {

    @Override
    public Page<Role> findPage(RoleVo roleVo, Pageable pageable) {
        if (StringUtils.isNotBlank(roleVo.getStatus())) {
            eq($.isActive, Boolean.valueOf(roleVo.getStatus()));
        }
        contains($.roleName, roleVo.getRoleName());
        contains($.description, roleVo.getDescription());
        if (StringUtils.isNotBlank(roleVo.getCombination())) {
            removeCurrentBooleanBuilder();
            orContains($.roleName, $.description, roleVo.getCombination());
        }
        return findAll(pageable);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<RoleVo> findVos(String staffId) {
        String sql = "SELECT b.staff_role_id,b.role_name,b.description,IF(ISNULL(a.staff_info_id),'0','1') FROM staff_role_rel a RIGHT JOIN staff_role b ON a.staff_role_id = b.staff_role_id AND a.staff_info_id = ?1 ORDER BY b.role_name";
        Query query = getEntityManager().createNativeQuery(sql);
        query.setParameter(1, staffId);
        List<Object> list = query.getResultList();
        List<RoleVo> vos = new ArrayList<RoleVo>();
        for (int i = 0; i < list.size(); i++) {
            Object[] objects = (Object[]) list.get(i);
            RoleVo vo = new RoleVo();
            vo.setId(objects[0].toString());
            vo.setRoleName(objects[1].toString());
            vo.setDescription(objects[2] != null ? objects[2].toString() : "");
            vo.setIsRoleId(objects[3].toString().equals("1") ? true : false);
            vos.add(vo);
        }
        return vos;
    }

}
