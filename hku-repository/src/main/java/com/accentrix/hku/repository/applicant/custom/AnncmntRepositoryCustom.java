package com.accentrix.hku.repository.applicant.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.applicant.Anncmnt;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年1月31日 上午10:37:22 
 * @version 1.0 
 */

@Repository
public interface AnncmntRepositoryCustom {

    List<Anncmnt> findByApplicantAccountId(String applicantAccountId);

    Anncmnt getByTypeCdAndAccountIdAndApplicationNo(String typeCd, String accountId, String applicationNo);

}
