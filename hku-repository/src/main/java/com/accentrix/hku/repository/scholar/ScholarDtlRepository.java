package com.accentrix.hku.repository.scholar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.scholar.ScholarDtl;
import com.accentrix.hku.repository.scholar.custom.ScholarDtlRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年3月19日 上午11:35:13
 * @version 1.0
 */

@Repository
public interface ScholarDtlRepository extends JpaRepository<ScholarDtl, String>, ScholarDtlRepositoryCustom {

}
