package com.accentrix.hku.repository.app.impl;

import java.util.List;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.app.QReferee;
import com.accentrix.hku.domain.app.Referee;
import com.accentrix.hku.repository.app.custom.RefereeRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午7:11:09
 * @version 1.0
 */

public class RefereeRepositoryImpl extends JpaDslQuery<Referee, QReferee> implements RefereeRepositoryCustom {

    @Override
    public List<Referee> findListByReferenceId(String referenceId) {
        eq($.referenceId, referenceId);
        eq($.isDeleted, false);
        return list();
    }
}
