package com.accentrix.hku.repository.app.impl;

import java.util.List;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.app.AcadQualHousingMgmtRwe;
import com.accentrix.hku.domain.app.QAcadQualHousingMgmtRwe;
import com.accentrix.hku.repository.app.custom.AcadQualHousingMgmtRweRepositoryCustom;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年4月10日 下午2:49:26
 */
public class AcadQualHousingMgmtRweRepositoryImpl extends JpaDslQuery<AcadQualHousingMgmtRwe, QAcadQualHousingMgmtRwe>
        implements AcadQualHousingMgmtRweRepositoryCustom {

    @Override
    public List<AcadQualHousingMgmtRwe> getByAppAcadQualHousingMgmtId(String appAcadQualHousingMgmtId) {
        eq($.appAcadQualHousingMgmtId, appAcadQualHousingMgmtId);
        return list();
    }

}