package com.accentrix.hku.repository.applicant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.applicant.ApplicantInformation;
import com.accentrix.hku.repository.applicant.custom.ApplicantInformationRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 上午10:38:07
 * @version 1.0
 */

@Repository
public interface ApplicantInformationRepository
        extends JpaRepository<ApplicantInformation, String>, ApplicantInformationRepositoryCustom {

}
