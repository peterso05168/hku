package com.accentrix.hku.repository.campaign.impl;

import java.util.List;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.campaign.CritToTag;
import com.accentrix.hku.domain.campaign.QCritToTag;
import com.accentrix.hku.repository.campaign.custom.CritToTagRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年4月9日 下午2:27:56
 * @version 1.0
 */

public class CritToTagRepositoryImpl extends JpaDslQuery<CritToTag, QCritToTag> implements CritToTagRepositoryCustom {

    @Override
    public List<String> findByCrit(String critId, String type) {
        return createJPAQuery().select($.tagId).from($).where($.type.eq(type).and($.cpgnCritId.eq(critId))).fetch();
    }

    @Override
    public List<CritToTag> findByCritToList(String critId, String type) {
        eq($.type, type);
        eq($.cpgnCritId, critId);
        return list();
    }

}
