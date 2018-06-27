package com.accentrix.hku.repository.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.AcadQualHousingMgmtRwe;
import com.accentrix.hku.repository.app.custom.AcadQualHousingMgmtRweRepositoryCustom;

/** 
* @author 作者lance.mao  
* @Email lance.mao@accentrix.com 
* @date 创建时间：2018年4月10日 下午2:48:22 
*/
@Repository
public interface AcadQualHousingMgmtRweRepository
        extends JpaRepository<AcadQualHousingMgmtRwe, String>, AcadQualHousingMgmtRweRepositoryCustom {

}
