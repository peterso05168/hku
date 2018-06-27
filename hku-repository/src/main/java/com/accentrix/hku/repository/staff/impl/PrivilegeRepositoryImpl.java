package com.accentrix.hku.repository.staff.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.staff.Privilege;
import com.accentrix.hku.domain.staff.QPrivilege;
import com.accentrix.hku.domain.staff.QRole;
import com.accentrix.hku.domain.staff.QRolePrivilege;
import com.accentrix.hku.domain.staff.QRoleRel;
import com.accentrix.hku.repository.staff.custom.PrivilegeRepositoryCustom;
import com.accentrix.hku.vo.staff.PrivilegeVo;
import com.querydsl.core.BooleanBuilder;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 上午10:44:27
 * @version 1.0
 */

public class PrivilegeRepositoryImpl extends JpaDslQuery<Privilege, QPrivilege> implements PrivilegeRepositoryCustom {

    @SuppressWarnings("unchecked")
    @Override
    public List<PrivilegeVo> findListGroupConcat(String roleId) {
        String sql = "SELECT a.module,GROUP_CONCAT(a.staff_privilege_id,'-',a.privilege_desc,'-',IF(ISNULL(b.staff_role_id),'0','1')  ORDER BY a.privilege_desc DESC) FROM staff_privilege a LEFT JOIN staff_role_privilege b ON a.staff_privilege_id = b.staff_privilege_id AND b.staff_role_id = ?1 GROUP BY a.module ORDER BY a.module DESC";
        Query query = getEntityManager().createNativeQuery(sql);
        query.setParameter(1, roleId);
        List<Object> list = query.getResultList();
        List<PrivilegeVo> vos = new ArrayList<PrivilegeVo>();
        for (int i = 0; i < list.size(); i++) {
            Object[] objects = (Object[]) list.get(i);
            PrivilegeVo vo = new PrivilegeVo();
            vo.setModule(objects[0].toString());
            vo.setVos(new ArrayList<PrivilegeVo>());
            vo.setPrivilegeIds(new ArrayList<String>());
            String[] privilege = objects[1].toString().split(",");
            for (String str : privilege) {
                String[] desc = str.split("-");
                PrivilegeVo privilegeVo = new PrivilegeVo();
                privilegeVo.setId(desc[0]);
                privilegeVo.setPrivilegeDesc(desc[1]);
                if (desc[2].equals("1")) {
                    vo.getPrivilegeIds().add(desc[0]);
                }
                vo.getVos().add(privilegeVo);
            }
            vos.add(vo);
        }
        return vos;
    }

    @Override
    public boolean findModule(String staffInfoId, String module, String moduleCode) {
        QRolePrivilege rolePrivilege = QRolePrivilege.rolePrivilege;
        QRole role = QRole.role;
        QRoleRel roleRel = QRoleRel.roleRel;
        eq(roleRel.staffInfoId, staffInfoId);
        eq($.module, module);
        eq($.moduleCd, moduleCode);
        BooleanBuilder builder = getCurrentBooleanBuilder();
        removeCurrentBooleanBuilder();
        Privilege privilege = createJPAQuery().select($).from($).innerJoin(rolePrivilege)
                .on($.id.eq(rolePrivilege.staffPrivilegeId)).innerJoin(role).on(role.id.eq(rolePrivilege.staffRoleId))
                .innerJoin(roleRel).on(roleRel.staffRoleId.eq(role.id)).where(builder).fetchOne();
        if (privilege != null) {
            return true;
        }
        return false;
    }

}
