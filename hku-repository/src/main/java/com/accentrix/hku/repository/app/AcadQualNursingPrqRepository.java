package com.accentrix.hku.repository.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.AcadQualNursingPrq;
import com.accentrix.hku.repository.app.custom.AcadQualNursingPrqRepositoryCustom;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年4月10日 下午4:10:19
 */
@Repository
public interface AcadQualNursingPrqRepository
        extends JpaRepository<AcadQualNursingPrq, String>, AcadQualNursingPrqRepositoryCustom {

}
