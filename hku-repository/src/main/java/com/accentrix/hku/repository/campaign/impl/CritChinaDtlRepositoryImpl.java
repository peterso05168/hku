package com.accentrix.hku.repository.campaign.impl;

import java.util.List;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.campaign.CritChinaDtl;
import com.accentrix.hku.domain.campaign.QCritChinaDtl;
import com.accentrix.hku.repository.campaign.custom.CritChinaDtlRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年4月9日 下午2:26:26
 * @version 1.0
 */

public class CritChinaDtlRepositoryImpl extends JpaDslQuery<CritChinaDtl, QCritChinaDtl>
        implements CritChinaDtlRepositoryCustom {

    @Override
    public List<CritChinaDtl> findByCritChinaId(String cpgnChinaId) {
        eq($.cpgnChinaId, cpgnChinaId);
        return list();
    }

}
