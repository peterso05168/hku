package com.accentrix.hku.repository.app.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.AcadQualNursingPrq;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年4月10日 下午4:09:40
 */
@Repository
public interface AcadQualNursingPrqRepositoryCustom {
    List<AcadQualNursingPrq> getByAppAcadQualNursingId(String appAcadQualNursingId);
}
