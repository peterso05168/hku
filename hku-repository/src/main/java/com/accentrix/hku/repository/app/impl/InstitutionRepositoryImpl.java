package com.accentrix.hku.repository.app.impl;

import java.util.List;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.app.Institution;
import com.accentrix.hku.domain.app.QInstitution;
import com.accentrix.hku.repository.app.custom.InstitutionRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月25日 下午12:01:00
 * @version 1.0
 */

public class InstitutionRepositoryImpl extends JpaDslQuery<Institution, QInstitution>
        implements InstitutionRepositoryCustom {

    @Override
    public List<Institution> findInstitutions(String countryId, String provinceId, String cityId) {
        eq($.countryId, countryId);
        eq($.provinceId, provinceId);
        eq($.cityId, cityId);
        return list();
    }

}
