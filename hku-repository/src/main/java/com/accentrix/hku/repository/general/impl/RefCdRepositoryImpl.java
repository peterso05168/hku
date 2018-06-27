package com.accentrix.hku.repository.general.impl;

import java.util.List;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.general.QRefCd;
import com.accentrix.hku.domain.general.RefCd;
import com.accentrix.hku.repository.general.custom.RefCdRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 上午10:41:52
 * @version 1.0
 */

public class RefCdRepositoryImpl extends JpaDslQuery<RefCd, QRefCd> implements RefCdRepositoryCustom {

    @Override
    public List<RefCd> findListByType(String type) {
        eq($.type, type);
        return list();
    }

    @Override
    public RefCd getByTypeAndCd(String type, String cd) {
        eq($.type, type);
        eq($.cd, cd);
        return unique();
    }
}
