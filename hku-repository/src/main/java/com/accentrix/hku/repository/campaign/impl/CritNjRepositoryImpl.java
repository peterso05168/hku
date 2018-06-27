package com.accentrix.hku.repository.campaign.impl;

import java.util.List;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.campaign.CritNj;
import com.accentrix.hku.domain.campaign.QCritNj;
import com.accentrix.hku.repository.campaign.custom.CritNjRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年4月9日 下午2:26:58
 * @version 1.0
 */

public class CritNjRepositoryImpl extends JpaDslQuery<CritNj, QCritNj> implements CritNjRepositoryCustom {

    @Override
    public List<CritNj> findByCpgnId(String cpgnId) {
        eq($.cpgnId, cpgnId);
        return list();
    }

}
