package com.accentrix.hku.repository.campaign.impl;

import java.util.List;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.campaign.CritNjToProg;
import com.accentrix.hku.domain.campaign.QCritNjToProg;
import com.accentrix.hku.repository.campaign.custom.CritNjToProgRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年4月9日 下午2:27:23
 * @version 1.0
 */

public class CritNjToProgRepositoryImpl extends JpaDslQuery<CritNjToProg, QCritNjToProg>
        implements CritNjToProgRepositoryCustom {

    @Override
    public List<String> findByCritNjId(String critNjId) {
        return createJPAQuery().select($.admFormProgId).from($).where($.cpgnCritNjId.eq(critNjId)).fetch();
    }

    @Override
    public List<CritNjToProg> findByCritNjIdToList(String critNjId) {
        eq($.cpgnCritNjId, critNjId);
        return list();
    }

}
