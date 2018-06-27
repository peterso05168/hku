package com.accentrix.hku.repository.applicant.custom;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.applicant.Account;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年1月31日 上午10:36:55 
 * @version 1.0 
 */

@Repository
public interface AccountRepositoryCustom {

    Account getByEmail(String email);

    Account checkEmail(String email);

    Account getByApplicantInfoId(String applicantInfoId);
}
