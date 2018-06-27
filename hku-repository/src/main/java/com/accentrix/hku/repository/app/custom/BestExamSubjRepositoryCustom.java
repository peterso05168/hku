package com.accentrix.hku.repository.app.custom;

import com.accentrix.hku.domain.app.BestExamSubj;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年5月29日 下午7:39:24 
 * @version 1.0 
 */

public interface BestExamSubjRepositoryCustom {

    BestExamSubj getByApplicationIdAndExamTypeIdAndCalculateType(String applicationId, String examTypeId,
            String calculateType);

}
