package com.accentrix.hku.repository.app.impl;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.app.BestExamSubj;
import com.accentrix.hku.domain.app.QBestExamSubj;
import com.accentrix.hku.repository.app.custom.BestExamSubjRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年5月29日 下午7:41:30
 * @version 1.0
 */

public class BestExamSubjRepositoryImpl extends JpaDslQuery<BestExamSubj, QBestExamSubj>
        implements BestExamSubjRepositoryCustom {

    @Override
    public BestExamSubj getByApplicationIdAndExamTypeIdAndCalculateType(String applicationId, String examTypeId,
            String calculateType) {
        eq($.applicationId, applicationId);
        eq($.examTypeId, examTypeId);
        eq($.calculateType, calculateType);
        return unique();
    }

}
