package com.accentrix.hku.repository.applicant.impl;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.applicant.Account;
import com.accentrix.hku.domain.applicant.QAccount;
import com.accentrix.hku.repository.applicant.custom.AccountRepositoryCustom;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年1月31日 上午10:37:01 
 * @version 1.0 
 */

public class AccountRepositoryImpl extends JpaDslQuery<Account, QAccount> implements AccountRepositoryCustom {

    @Override
    public Account getByEmail(String email) {
        isNotNull($.activateCode);
        isNotNull($.activateDate);
        eq($.personEmail, email);
        return unique();
    }

    @Override
    public Account checkEmail(String email) {
        eq($.personEmail, email);
        return unique();
    }

    @Override
    public Account getByApplicantInfoId(String applicantInfoId) {
        eq($.userInfoId, applicantInfoId);
        return unique();
    }

}
