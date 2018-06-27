package com.accentrix.hku.repository.campaign.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.campaign.Centre;
import com.accentrix.hku.domain.campaign.QCentre;
import com.accentrix.hku.repository.campaign.custom.CentreRepositoryCustom;
import com.accentrix.hku.vo.campaign.CentreVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年4月9日 下午2:25:02 
 * @version 1.0 
 */

public class CentreRepositoryImpl extends JpaDslQuery<Centre, QCentre> implements CentreRepositoryCustom {

    private static final String PROVINCE = "Province";
    private static final String CITY = "City";

    @Override
    public List<Centre> findByCpgnId(String cpgnId) {
        eq($.cpgnId, cpgnId);
        return list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<CentreVo> findByCpgnIdAndProvinceOrCity(String cpgnId, String type) {
        String provinceSql = "SELECT b.`desc`,a.province_id,GROUP_CONCAT(IF (a.mapped = 1,a.centre_name,null)),GROUP_CONCAT(IF (a.mapped = 1,a.cpgn_centre_id,null)) FROM cpgn_centre a INNER JOIN cpc_province b ON a.province_id = b.id WHERE a.cpgn_id = ?1 GROUP BY a.province_id";
        String citySql = "SELECT b.`desc`,a.city_id,GROUP_CONCAT(IF (a.mapped = 1,a.centre_name,null)),GROUP_CONCAT(IF (a.mapped = 1,a.cpgn_centre_id,null)) FROM cpgn_centre a INNER JOIN cpc_city b ON a.city_id = b.id WHERE a.cpgn_id = ?1 GROUP BY a.city_id";
        List<Object> list = new ArrayList<Object>();
        List<CentreVo> centreVos = new ArrayList<CentreVo>();
        if (type.equals(PROVINCE)) {
            Query query = getEntityManager().createNativeQuery(provinceSql);
            query.setParameter(1, cpgnId);
            list = query.getResultList();
        } else if (type.equals(CITY)) {
            Query query = getEntityManager().createNativeQuery(citySql);
            query.setParameter(1, cpgnId);
            list = query.getResultList();
        }
        for (int i = 0; i < list.size(); i++) {
            Object[] objects = (Object[]) list.get(i);
            CentreVo vo = new CentreVo();
            vo.setCentreIds(new ArrayList<String>());
            vo.setProvinceOrCityName(objects[0].toString());
            vo.setProvinceOrCityId(objects[1].toString());
            if (objects[2] != null) {
                vo.setCentreNames(objects[2].toString());
            }
            if (objects[3] != null) {
                String[] centreIds = objects[3].toString().split(",");
                for (String id : centreIds) {
                    vo.getCentreIds().add(id);
                }
            }
            centreVos.add(vo);
        }
        return centreVos;
    }

    @Override
    public List<Centre> findByType(String type, String provinceOrCity, String cpgnId) {
        if (type.equals(PROVINCE)) {
            eq($.provinceId, provinceOrCity);
        } else if (type.equals(CITY)) {
            eq($.cityId, provinceOrCity);
        }
        eq($.cpgnId, cpgnId);
        return list();
    }

    @Override
    public List<Centre> findByIds(List<String> ids, String type, String provinceOrCity, String cpgnId) {
        List<Centre> list = new ArrayList<Centre>();
        if (type.equals(PROVINCE)) {
            list = createJPAQuery().select($).from($)
                    .where($.id.notIn(ids).and($.provinceId.eq(provinceOrCity)).and($.cpgnId.eq(cpgnId))).fetch();
        } else if (type.equals(CITY)) {
            list = createJPAQuery().select($).from($)
                    .where($.id.notIn(ids).and($.cityId.eq(provinceOrCity)).and($.cpgnId.eq(cpgnId))).fetch();
        }
        return list;
    }

    @Override
    public List<Centre> findByIdList(List<String> ids) {
        return createJPAQuery().select($).from($).where($.id.in(ids)).fetch();
    }

}
