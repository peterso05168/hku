package com.accentrix.hku.repository.general;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.general.RefCd;
import com.accentrix.hku.repository.general.custom.RefCdRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 上午10:41:11
 * @version 1.0
 */

@Repository
public interface RefCdRepository extends JpaRepository<RefCd, String>, RefCdRepositoryCustom {

}
