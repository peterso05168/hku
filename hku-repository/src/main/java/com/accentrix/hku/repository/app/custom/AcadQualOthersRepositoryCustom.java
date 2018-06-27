package com.accentrix.hku.repository.app.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.AcadQualOthers;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年2月9日 下午3:09:20
 * @version 1.0
 */

@Repository
public interface AcadQualOthersRepositoryCustom {
    List<AcadQualOthers> getByAcadQualHousingMgmtId(String acadQualHousingMgmtId);
}
