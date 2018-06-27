package com.accentrix.hku.repository.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.TelNo;
import com.accentrix.hku.repository.app.custom.TelNoRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 上午10:34:26
 * @version 1.0
 */

@Repository
public interface TelNoRepository extends JpaRepository<TelNo, String>, TelNoRepositoryCustom {

}
