package com.accentrix.hku.repository.adm.impl;

import java.util.List;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.constant.Constants;
import com.accentrix.hku.domain.adm.AdmAnncmnt;
import com.accentrix.hku.domain.adm.QAdmAnncmnt;
import com.accentrix.hku.domain.general.QRefCd;
import com.accentrix.hku.repository.adm.custom.AnncmntTemplateRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 上午10:37:28
 * @version 1.0
 */

public class AnncmntTemplateRepositoryImpl extends JpaDslQuery<AdmAnncmnt, QAdmAnncmnt>
        implements AnncmntTemplateRepositoryCustom {

    @Override
    public List<AdmAnncmnt> findByTemplateName(String templateName) {
        QRefCd refCd = QRefCd.refCd;
        List<AdmAnncmnt> admAnncmnts = createJPAQuery().select($).from($).leftJoin(refCd).on($.typeCd.eq(refCd.cd))
                .where(refCd.type.eq(Constants.ANNCMNT_TYPE), refCd.value.contains(templateName)).fetch();
        return admAnncmnts;
    }

    @Override
    public AdmAnncmnt getByTypeCd(String typeCd) {
        eq($.typeCd, typeCd);
        return unique();
    }

}
