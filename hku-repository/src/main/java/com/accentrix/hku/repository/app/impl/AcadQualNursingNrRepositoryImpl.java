package com.accentrix.hku.repository.app.impl;

import java.util.List;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.app.AcadQualNursingNr;
import com.accentrix.hku.domain.app.QAcadQualNursingNr;
import com.accentrix.hku.repository.app.custom.AcadQualNursingNrRepositoryCustom;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年4月10日 下午3:51:29
 */
public class AcadQualNursingNrRepositoryImpl extends JpaDslQuery<AcadQualNursingNr, QAcadQualNursingNr>
        implements AcadQualNursingNrRepositoryCustom {

    @Override
    public List<AcadQualNursingNr> getByAppAcadQualNursingId(String appAcadQualNursingId) {
        eq($.appAcadQualNursingId, appAcadQualNursingId);
        return list();
    }

}
