package com.accentrix.hku.repository.app.custom;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.ReqDocConf;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年1月31日 上午10:32:09 
 * @version 1.0 
 */

@Repository
public interface ReqDocConfRepositoryCustom {

    ReqDocConf getByTypeAndCdAndName(String type, String cd, String desc);

}
