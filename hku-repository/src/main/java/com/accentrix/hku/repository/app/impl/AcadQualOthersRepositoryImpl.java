package com.accentrix.hku.repository.app.impl;

import java.util.List;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.app.AcadQualOthers;
import com.accentrix.hku.domain.app.QAcadQualOthers;
import com.accentrix.hku.repository.app.custom.AcadQualOthersRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年2月9日 下午3:09:26
 * @version 1.0
 */

public class AcadQualOthersRepositoryImpl extends JpaDslQuery<AcadQualOthers, QAcadQualOthers>
        implements AcadQualOthersRepositoryCustom {

    @Override
    public List<AcadQualOthers> getByAcadQualHousingMgmtId(String acadQualHousingMgmtId) {
        eq($.acadQualHousingMgmtId, acadQualHousingMgmtId);
        return list();
    }
}
