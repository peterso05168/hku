package com.accentrix.hku.repository.app.impl;

import java.util.List;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.app.AcadQualHousingMgmtPq;
import com.accentrix.hku.domain.app.QAcadQualHousingMgmtPq;
import com.accentrix.hku.repository.app.custom.AcadQualHousingMgmtPqRepositoryCustom;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年4月10日 下午2:25:08
 */
public class AcadQualHousingMgmtPqRepositoryImpl extends JpaDslQuery<AcadQualHousingMgmtPq, QAcadQualHousingMgmtPq>
        implements AcadQualHousingMgmtPqRepositoryCustom {

    @Override
    public List<AcadQualHousingMgmtPq> getByAppAcadQualHousingMgmtId(String appAcadQualHousingMgmtId) {
        eq($.appAcadQualHousingMgmtId, appAcadQualHousingMgmtId);
        return list();
    }

}
