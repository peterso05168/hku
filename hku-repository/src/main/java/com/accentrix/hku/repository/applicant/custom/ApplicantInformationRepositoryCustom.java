package com.accentrix.hku.repository.applicant.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.applicant.ApplicantInformation;
import com.accentrix.hku.vo.applicant.ApplicantInformationVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 上午10:38:16
 * @version 1.0
 */

@Repository
public interface ApplicantInformationRepositoryCustom {

    List<ApplicantInformation> findPage(ApplicantInformationVo vo, Integer offset, Integer pageSize);

    Integer countNumber(ApplicantInformationVo vo);
}
