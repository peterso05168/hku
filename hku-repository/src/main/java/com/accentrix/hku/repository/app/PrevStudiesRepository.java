package com.accentrix.hku.repository.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.PrevStudies;
import com.accentrix.hku.repository.app.custom.PrevStudiesRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午7:08:28
 * @version 1.0
 */

@Repository
public interface PrevStudiesRepository extends JpaRepository<PrevStudies, String>, PrevStudiesRepositoryCustom {

}
