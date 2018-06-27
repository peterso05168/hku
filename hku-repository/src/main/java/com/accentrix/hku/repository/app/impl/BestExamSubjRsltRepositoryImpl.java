package com.accentrix.hku.repository.app.impl;

import java.util.List;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.app.BestExamSubjRslt;
import com.accentrix.hku.domain.app.QBestExamSubj;
import com.accentrix.hku.domain.app.QBestExamSubjRslt;
import com.accentrix.hku.domain.exam.QType;
import com.accentrix.hku.repository.app.custom.BestExamSubjRsltRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年5月29日 下午7:41:39
 * @version 1.0
 */

public class BestExamSubjRsltRepositoryImpl extends JpaDslQuery<BestExamSubjRslt, QBestExamSubjRslt>
        implements BestExamSubjRsltRepositoryCustom {

    @Override
    public List<BestExamSubjRslt> getBestExamSubjects(String examCd, String applicationId, String calculateType) {
        QBestExamSubj bestExamSubj = QBestExamSubj.bestExamSubj;
        QType type = QType.type;
        return createJPAQuery().select($).from(bestExamSubj).innerJoin($).on(bestExamSubj.id.eq($.bestExamSubjId))
                .innerJoin(type).on(bestExamSubj.examTypeId.eq(type.id))
                .where(type.examCd.eq(examCd).and(bestExamSubj.applicationId.eq(applicationId))
                        .and(bestExamSubj.calculateType.eq(calculateType)))
                .orderBy($.seqNo.desc()).fetch();
    }

    @Override
    public List<BestExamSubjRslt> findByBestExamSubjId(String bestExamSubjId) {
        eq($.bestExamSubjId, bestExamSubjId);
        return list();
    }

}
