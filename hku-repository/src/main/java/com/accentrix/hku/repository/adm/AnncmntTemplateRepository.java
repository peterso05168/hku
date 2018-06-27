package com.accentrix.hku.repository.adm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.adm.AdmAnncmnt;
import com.accentrix.hku.repository.adm.custom.AnncmntTemplateRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 上午10:37:12
 * @version 1.0
 */

@Repository
public interface AnncmntTemplateRepository extends JpaRepository<AdmAnncmnt, String>, AnncmntTemplateRepositoryCustom {

}
