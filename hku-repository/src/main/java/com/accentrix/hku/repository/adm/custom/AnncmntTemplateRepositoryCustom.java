package com.accentrix.hku.repository.adm.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.adm.AdmAnncmnt;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年1月31日 上午10:37:22 
 * @version 1.0 
 */

@Repository
public interface AnncmntTemplateRepositoryCustom {

    List<AdmAnncmnt> findByTemplateName(String templateName);

    AdmAnncmnt getByTypeCd(String typeCd);

}
