package com.accentrix.hku.repository.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.ReqDocConf;
import com.accentrix.hku.repository.app.custom.ReqDocConfRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 上午10:31:59
 * @version 1.0
 */

@Repository
public interface ReqDocConfRepository extends JpaRepository<ReqDocConf, String>, ReqDocConfRepositoryCustom {

}
