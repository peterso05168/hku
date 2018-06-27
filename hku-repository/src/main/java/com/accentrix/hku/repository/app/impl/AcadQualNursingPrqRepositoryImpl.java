package com.accentrix.hku.repository.app.impl;

import java.util.List;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.app.AcadQualNursingPrq;
import com.accentrix.hku.domain.app.QAcadQualNursingPrq;
import com.accentrix.hku.repository.app.custom.AcadQualNursingPrqRepositoryCustom;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年4月10日 下午4:15:38
 */
public class AcadQualNursingPrqRepositoryImpl extends JpaDslQuery<AcadQualNursingPrq, QAcadQualNursingPrq>
        implements AcadQualNursingPrqRepositoryCustom {

    @Override
    public List<AcadQualNursingPrq> getByAppAcadQualNursingId(String appAcadQualNursingId) {
        eq($.appAcadQualNursingId, appAcadQualNursingId);
        return list();
    }

}
