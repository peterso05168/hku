package com.accentrix.hku.repository.applicant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.applicant.Application;
import com.accentrix.hku.repository.applicant.custom.ApplicationRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 上午10:37:39
 * @version 1.0
 */

@Repository
public interface ApplicationRepository extends JpaRepository<Application, String>, ApplicationRepositoryCustom {

}
