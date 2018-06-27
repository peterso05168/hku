package com.accentrix.hku.repository.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.Referee;
import com.accentrix.hku.repository.app.custom.RefereeRepositoryCustom;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年1月30日 下午7:10:55 
 * @version 1.0 
 */

@Repository
public interface RefereeRepository extends JpaRepository<Referee, String>, RefereeRepositoryCustom {

}
