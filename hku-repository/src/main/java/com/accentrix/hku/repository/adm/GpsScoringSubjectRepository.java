package com.accentrix.hku.repository.adm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.adm.GpsScoringSubject;

import com.accentrix.hku.repository.adm.custom.GpsScoringSubjectRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年3月19日 上午11:28:58
 * @version 1.0
 */

@Repository
public interface GpsScoringSubjectRepository
        extends JpaRepository<GpsScoringSubject, String>, GpsScoringSubjectRepositoryCustom {

}
