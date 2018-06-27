package com.accentrix.hku.repository.applicant.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.applicant.Application;
import com.accentrix.hku.vo.applicant.ApplicationVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 上午10:37:49
 * @version 1.0
 */

@Repository
public interface ApplicationRepositoryCustom {

    Application findByAdmFormIdAndAccountId(String admFormId, String applicantAccountId);

    List<Application> findByAccountIds(List<String> applicantAccountIds);

    List<ApplicationVo> basicSearch(String criteria);

    List<ApplicationVo> advanceSearch(ApplicationVo searchVo);

    List<ApplicationVo> findByApplicantId(String applicantId);

    List<Application> findByAccountId(String applicantAccountId);

    ApplicationVo getByApplicationId(String applicationId);

    List<Application> findByAccountIdAndStatus(String applicantAccountId, String status);
}
