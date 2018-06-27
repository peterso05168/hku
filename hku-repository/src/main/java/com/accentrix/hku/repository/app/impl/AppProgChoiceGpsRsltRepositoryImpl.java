package com.accentrix.hku.repository.app.impl;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.app.AppProgChoiceGpsRslt;
import com.accentrix.hku.domain.app.QAppProgChoiceGpsRslt;
import com.accentrix.hku.repository.app.custom.AppProgChoiceGpsRsltRepositoryCustom;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年6月1日 下午1:36:01
 */
public class AppProgChoiceGpsRsltRepositoryImpl extends JpaDslQuery<AppProgChoiceGpsRslt, QAppProgChoiceGpsRslt>
        implements AppProgChoiceGpsRsltRepositoryCustom {

    @Override
    public AppProgChoiceGpsRslt getOneByProgrammeChoiceId(String programmeChoiceId) {
        eq($.appProgrammeChoiceId, programmeChoiceId);
        return unique();
    }

}
