package com.accentrix.hku.repository.app.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.AcadQualHousingMgmtRwe;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年4月10日 下午2:47:52
 */
@Repository
public interface AcadQualHousingMgmtRweRepositoryCustom {
    List<AcadQualHousingMgmtRwe> getByAppAcadQualHousingMgmtId(String appAcadQualHousingMgmtId);
}
