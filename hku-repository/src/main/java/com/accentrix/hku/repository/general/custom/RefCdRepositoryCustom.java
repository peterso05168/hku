package com.accentrix.hku.repository.general.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.general.RefCd;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年1月31日 上午10:41:47 
 * @version 1.0 
 */

@Repository
public interface RefCdRepositoryCustom {

    public List<RefCd> findListByType(String type);

    RefCd getByTypeAndCd(String type, String cd);
}
