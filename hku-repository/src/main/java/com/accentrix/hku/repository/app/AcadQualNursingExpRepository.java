package com.accentrix.hku.repository.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.AcadQualNursingExp;
import com.accentrix.hku.repository.app.custom.AcadQualNursingExpRepositoryCustom;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年4月10日 下午3:16:12
 */
@Repository
public interface AcadQualNursingExpRepository
        extends JpaRepository<AcadQualNursingExp, String>, AcadQualNursingExpRepositoryCustom {

}
