package com.accentrix.hku.repository.applicant.impl;

import java.util.List;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.applicant.ApplicantToTag;
import com.accentrix.hku.domain.applicant.QApplicantToTag;
import com.accentrix.hku.repository.applicant.custom.ApplicantToTagRepositoryCustom;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年4月27日 下午5:55:47 
 * @version 1.0 
 */

public class ApplicantToTagRepositoryImpl extends JpaDslQuery<ApplicantToTag, QApplicantToTag>
        implements ApplicantToTagRepositoryCustom {

    @Override
    public List<ApplicantToTag> findByApplicantId(String applicantId) {
        eq($.applicantInfoId, applicantId);
        return list();
    }

}
