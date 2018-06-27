package com.accentrix.hku.repository.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.Progress;
import com.accentrix.hku.repository.app.custom.ProgressRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年3月1日 上午10:42:30
 * @version 1.0
 */

@Repository
public interface ProgressRepository extends JpaRepository<Progress, String>, ProgressRepositoryCustom {

}
