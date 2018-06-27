package com.accentrix.hku.repository.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.TransCredits;
import com.accentrix.hku.repository.app.custom.TransCreditsRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年2月9日 下午3:11:12
 * @version 1.0
 */

@Repository
public interface TransCreditsRepository extends JpaRepository<TransCredits, String>, TransCreditsRepositoryCustom {

}
