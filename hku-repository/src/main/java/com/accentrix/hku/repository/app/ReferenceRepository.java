package com.accentrix.hku.repository.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.Reference;
import com.accentrix.hku.repository.app.custom.ReferenceRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 上午10:30:20
 * @version 1.0
 */

@Repository
public interface ReferenceRepository extends JpaRepository<Reference, String>, ReferenceRepositoryCustom {

}
