package com.accentrix.hku.repository.app.impl;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.app.QReqDocConf;
import com.accentrix.hku.domain.app.ReqDocConf;
import com.accentrix.hku.repository.app.custom.ReqDocConfRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 上午10:32:15
 * @version 1.0
 */

public class ReqDocConfRepositoryImpl extends JpaDslQuery<ReqDocConf, QReqDocConf>
        implements ReqDocConfRepositoryCustom {

    @Override
    public ReqDocConf getByTypeAndCdAndName(String type, String cd, String desc) {
        eq($.reqDocType, type);
        eq($.reqDocCd, cd);
        eq($.reqDocName, desc);
        return unique();
    }

}
