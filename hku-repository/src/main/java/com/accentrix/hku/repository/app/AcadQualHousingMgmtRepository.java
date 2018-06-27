package com.accentrix.hku.repository.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.AcadQualHousingMgmt;
import com.accentrix.hku.repository.app.custom.AcadQualHousingMgmtRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午7:04:05
 * @version 1.0
 */

@Repository
public interface AcadQualHousingMgmtRepository
        extends JpaRepository<AcadQualHousingMgmt, String>, AcadQualHousingMgmtRepositoryCustom {

}
