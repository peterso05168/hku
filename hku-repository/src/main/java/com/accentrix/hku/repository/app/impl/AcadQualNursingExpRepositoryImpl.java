package com.accentrix.hku.repository.app.impl;

import java.util.List;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.app.AcadQualNursingExp;
import com.accentrix.hku.domain.app.QAcadQualNursingExp;
import com.accentrix.hku.repository.app.custom.AcadQualNursingExpRepositoryCustom;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年4月10日 下午3:17:55
 */
public class AcadQualNursingExpRepositoryImpl extends JpaDslQuery<AcadQualNursingExp, QAcadQualNursingExp>
        implements AcadQualNursingExpRepositoryCustom {

    @Override
    public List<AcadQualNursingExp> getByAppAcadQualNursingId(String appAcadQualNursingId) {
        eq($.appAcadQualNursingId, appAcadQualNursingId);
        return list();
    }

    @Override
    public List<AcadQualNursingExp> getByAppAcadQualNursingIdAndType(String appAcadQualNursingId, String type) {
        eq($.appAcadQualNursingId, appAcadQualNursingId);
        eq($.expType, type);
        return list();
    }
}
