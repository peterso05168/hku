package com.accentrix.hku.repository.app.impl;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.app.IbdBestExam;
import com.accentrix.hku.domain.app.QIbdBestExam;
import com.accentrix.hku.repository.app.custom.IbdBestExamRepositoryCustom;

public class IbdBestExamRepositoryImpl extends JpaDslQuery<IbdBestExam, QIbdBestExam>
        implements IbdBestExamRepositoryCustom {

    @Override
    public IbdBestExam getBestIBD(String applicationId, String calculateType, String outOf) {
        eq($.applicationId, applicationId);
        eq($.calculateType, calculateType);
        eq($.outOf, outOf);
        return unique();
    }

}
