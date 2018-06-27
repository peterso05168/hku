package com.accentrix.hku.repository.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.Counselor;
import com.accentrix.hku.repository.app.custom.CounselorRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午7:05:14
 * @version 1.0
 */

@Repository
public interface CounselorRepository extends JpaRepository<Counselor, String>, CounselorRepositoryCustom {

}
