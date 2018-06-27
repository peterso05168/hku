package com.accentrix.hku.repository.applicant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.applicant.Account;
import com.accentrix.hku.repository.applicant.custom.AccountRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 上午10:36:47
 * @version 1.0
 */

@Repository
public interface AccountRepository extends JpaRepository<Account, String>, AccountRepositoryCustom {

}
