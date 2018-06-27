package com.accentrix.hku.repository.app.custom;

import java.util.List;

import com.accentrix.hku.domain.app.BestExamSubjRslt;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年5月29日 下午7:39:16 
 * @version 1.0 
 */

public interface BestExamSubjRsltRepositoryCustom {

    List<BestExamSubjRslt> getBestExamSubjects(String examCd, String applicationId, String calculateType);

    List<BestExamSubjRslt> findByBestExamSubjId(String bestExamSubjId);

}
