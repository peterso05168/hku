package com.accentrix.hku.repository.app.custom;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.AppProgChoiceGpsRslt;

/** 
* @author 作者lance.mao  
* @Email lance.mao@accentrix.com 
* @date 创建时间：2018年6月1日 下午1:34:13 
*/
@Repository
public interface AppProgChoiceGpsRsltRepositoryCustom {

    AppProgChoiceGpsRslt getOneByProgrammeChoiceId(String programmeChoiceId);

}
