package com.accentrix.hku.repository.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.SpecialScheme;
import com.accentrix.hku.repository.app.custom.SpecialSchemeRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 上午10:33:57
 * @version 1.0
 */

@Repository
public interface SpecialSchemeRepository extends JpaRepository<SpecialScheme, String>, SpecialSchemeRepositoryCustom {

}
