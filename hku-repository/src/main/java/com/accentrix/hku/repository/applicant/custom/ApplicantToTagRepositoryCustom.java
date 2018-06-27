package com.accentrix.hku.repository.applicant.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.applicant.ApplicantToTag;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年4月27日 下午5:55:41 
 * @version 1.0 
 */

@Repository
public interface ApplicantToTagRepositoryCustom {

    List<ApplicantToTag> findByApplicantId(String applicantId);
}
