package com.accentrix.hku.repository.app.custom;

import com.accentrix.hku.domain.app.IbdBestExam;

public interface IbdBestExamRepositoryCustom {

    IbdBestExam getBestIBD(String applicationId, String calculateType, String outOf);

}
