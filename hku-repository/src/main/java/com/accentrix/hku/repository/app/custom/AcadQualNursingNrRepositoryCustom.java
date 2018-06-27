package com.accentrix.hku.repository.app.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.AcadQualNursingNr;

/** 
* @author 作者lance.mao  
* @Email lance.mao@accentrix.com 
* @date 创建时间：2018年4月10日 下午3:49:54 
*/
@Repository
public interface AcadQualNursingNrRepositoryCustom {
    List<AcadQualNursingNr> getByAppAcadQualNursingId(String appAcadQualNursingId);
}
