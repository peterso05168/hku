package com.accentrix.hku.repository.campaign.impl;

import java.util.List;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.campaign.CpgnSession;
import com.accentrix.hku.domain.campaign.QCpgnSession;
import com.accentrix.hku.repository.campaign.custom.CpgnSessionRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年4月9日 下午2:25:27
 * @version 1.0
 */

public class CpgnSessionRepositoryImpl extends JpaDslQuery<CpgnSession, QCpgnSession>
        implements CpgnSessionRepositoryCustom {

    @Override
    public List<CpgnSession> findByCentreId(String centreId) {
        eq($.cpgnCentreId, centreId);
        return list();
    }

}
