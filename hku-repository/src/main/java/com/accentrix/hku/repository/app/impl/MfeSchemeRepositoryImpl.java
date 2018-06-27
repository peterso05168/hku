package com.accentrix.hku.repository.app.impl;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.app.MfeScheme;
import com.accentrix.hku.domain.app.QMfeScheme;
import com.accentrix.hku.repository.app.custom.MfeSchemeRepositoryCustom;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年4月3日 下午6:44:32
 */
public class MfeSchemeRepositoryImpl extends JpaDslQuery<MfeScheme, QMfeScheme> implements MfeSchemeRepositoryCustom {

    @Override
    public MfeScheme getByQualificationIdAndYear(String qualificationId, Integer yearSemester) {
        eq($.qualificationsId, qualificationId);
        eq($.yearSemester, yearSemester);
        return unique();
    }

}
