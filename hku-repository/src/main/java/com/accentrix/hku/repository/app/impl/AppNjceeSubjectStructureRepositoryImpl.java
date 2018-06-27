package com.accentrix.hku.repository.app.impl;

import java.util.List;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.app.AppNjceeSubjectStructure;
import com.accentrix.hku.domain.app.QAppNjceeSubjectStructure;
import com.accentrix.hku.repository.app.custom.AppNjceeSubjectStructureRepositoryCustom;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年4月25日 下午9:28:22
 */
public class AppNjceeSubjectStructureRepositoryImpl
        extends JpaDslQuery<AppNjceeSubjectStructure, QAppNjceeSubjectStructure>
        implements AppNjceeSubjectStructureRepositoryCustom {

    @Override
    public List<AppNjceeSubjectStructure> getByAppNjceeScoringSystemId(String appNjceeScoringSystemId) {
        eq($.appNjceeScoringSystemId, appNjceeScoringSystemId);
        return list();
    }

    @Override
    public List<AppNjceeSubjectStructure> getByAppNjceeScoringSystemIdAndType(String appNjceeScoringSystemId,
            String type) {
        eq($.appNjceeScoringSystemId, appNjceeScoringSystemId);
        eq($.structureType, type);
        return list();
    }

}
