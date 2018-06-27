package com.accentrix.hku.repository.app.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.AcadQualHousingMgmtPq;

/** 
* @author 作者lance.mao  
* @Email lance.mao@accentrix.com 
* @date 创建时间：2018年4月10日 下午2:23:01 
*/
@Repository
public interface AcadQualHousingMgmtPqRepositoryCustom {
    List<AcadQualHousingMgmtPq> getByAppAcadQualHousingMgmtId(String appAcadQualHousingMgmtId);
}
