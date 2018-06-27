package com.accentrix.hku.repository.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.AcadQualHousingMgmtPq;
import com.accentrix.hku.repository.app.custom.AcadQualHousingMgmtPqRepositoryCustom;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年4月10日 下午2:23:56
 */
@Repository
public interface AcadQualHousingMgmtPqRepository
        extends JpaRepository<AcadQualHousingMgmtPq, String>, AcadQualHousingMgmtPqRepositoryCustom {

}
