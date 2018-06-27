package com.accentrix.hku.repository.applicant.impl;

import java.util.List;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.applicant.Anncmnt;
import com.accentrix.hku.domain.applicant.QAnncmnt;
import com.accentrix.hku.repository.applicant.custom.AnncmntRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 上午10:37:28
 * @version 1.0
 */

public class AnncmntRepositoryImpl extends JpaDslQuery<Anncmnt, QAnncmnt> implements AnncmntRepositoryCustom {

    @Override
    public List<Anncmnt> findByApplicantAccountId(String applicantAccountId) {
        eq($.applicantAccountId, applicantAccountId);
        return list();
    }

    @Override
    public Anncmnt getByTypeCdAndAccountIdAndApplicationNo(String typeCd, String accountId, String applicationNo) {
        eq($.typeCd, typeCd);
        eq($.applicantAccountId, accountId);
        eq($.applicationNo, applicationNo);
        return unique();
    }

}
