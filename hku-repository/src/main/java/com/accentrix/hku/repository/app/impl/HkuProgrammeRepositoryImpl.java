package com.accentrix.hku.repository.app.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.app.HkuProgramme;
import com.accentrix.hku.domain.app.QHkuProgramme;
import com.accentrix.hku.repository.app.custom.HkuProgrammeRepositoryCustom;
import com.accentrix.hku.vo.app.HkuProgrammeVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午7:06:46
 * @version 1.0
 */

public class HkuProgrammeRepositoryImpl extends JpaDslQuery<HkuProgramme, QHkuProgramme>
        implements HkuProgrammeRepositoryCustom {

    @SuppressWarnings("unchecked")
    @Override
    public List<HkuProgrammeVo> findVos(String staffId) {
        String sql = "SELECT b.hku_programme_id,b.hku_programme_cd,b.hku_programme_desc,IF(ISNULL(a.staff_id),'0','1') FROM staff_prog a RIGHT JOIN app_hku_programme b ON a.hku_programme_id = b.hku_programme_id AND a.staff_id = ?1 ORDER BY b.hku_programme_cd";
        Query query = getEntityManager().createNativeQuery(sql);
        query.setParameter(1, staffId);
        List<Object> list = query.getResultList();
        List<HkuProgrammeVo> hkuProgrammeVos = new ArrayList<HkuProgrammeVo>();
        for (int i = 0; i < list.size(); i++) {
            Object[] objects = (Object[]) list.get(i);
            HkuProgrammeVo vo = new HkuProgrammeVo();
            vo.setId(objects[0].toString());
            vo.setHkuProgrammeCd(objects[1].toString());
            vo.setHkuProgrammeDesc(objects[2].toString());
            vo.setIsHkuProgrammeId(objects[3].toString().equals("1") ? true : false);
            hkuProgrammeVos.add(vo);
        }
        return hkuProgrammeVos;
    }

    @Override
    public List<String> findFacultyCd() {
        return createJPAQuery().distinct().select($.facultyCd).from($).fetch();
    }

    @Override
    public List<HkuProgramme> findByFacultyCd(String facultyCd) {
        eq($.facultyCd, facultyCd);
        return list();
    }

}
