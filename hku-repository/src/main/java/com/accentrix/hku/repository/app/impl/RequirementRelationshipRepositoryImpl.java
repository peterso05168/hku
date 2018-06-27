package com.accentrix.hku.repository.app.impl;

import java.util.List;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.app.QRequirementRelationship;
import com.accentrix.hku.domain.app.RequirementRelationship;
import com.accentrix.hku.repository.app.custom.RequirementRelationshipRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 上午10:33:43
 * @version 1.0
 */

public class RequirementRelationshipRepositoryImpl
        extends JpaDslQuery<RequirementRelationship, QRequirementRelationship>
        implements RequirementRelationshipRepositoryCustom {

    @Override
    public List<RequirementRelationship> findListByParentId(String parentRequirementId) {
        eq($.parentRequirementId, parentRequirementId);
        return list();
    }
}
