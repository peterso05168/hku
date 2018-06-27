package com.accentrix.hku.repository.campaign.impl;

import java.util.List;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.campaign.CritChina;
import com.accentrix.hku.domain.campaign.QCritChina;
import com.accentrix.hku.repository.campaign.custom.CritChinaRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年4月9日 下午2:25:56
 * @version 1.0
 */

public class CritChinaRepositoryImpl extends JpaDslQuery<CritChina, QCritChina> implements CritChinaRepositoryCustom {

    @Override
    public List<CritChina> findByCpgnId(String cpgnId) {
        eq($.cpgnId, cpgnId);
        return list();
    }

}
