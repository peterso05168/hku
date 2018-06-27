package com.accentrix.hku.repository.app.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.AcadQualNursingExp;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年4月10日 下午3:15:52
 */
@Repository
public interface AcadQualNursingExpRepositoryCustom {
    List<AcadQualNursingExp> getByAppAcadQualNursingId(String appAcadQualNursingId);

    List<AcadQualNursingExp> getByAppAcadQualNursingIdAndType(String appAcadQualNursingId, String type);
}
