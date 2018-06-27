package com.accentrix.hku.repository.applicant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.applicant.ApplicantToTag;
import com.accentrix.hku.repository.applicant.custom.ApplicantToTagRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年4月27日 下午5:54:31
 * @version 1.0
 */

@Repository
public interface ApplicantToTagRepository
        extends JpaRepository<ApplicantToTag, String>, ApplicantToTagRepositoryCustom {

}
