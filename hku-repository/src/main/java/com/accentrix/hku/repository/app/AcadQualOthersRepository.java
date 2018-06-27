package com.accentrix.hku.repository.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.AcadQualOthers;
import com.accentrix.hku.repository.app.custom.AcadQualOthersRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年2月9日 下午3:09:09
 * @version 1.0
 */

@Repository
public interface AcadQualOthersRepository
        extends JpaRepository<AcadQualOthers, String>, AcadQualOthersRepositoryCustom {

}
