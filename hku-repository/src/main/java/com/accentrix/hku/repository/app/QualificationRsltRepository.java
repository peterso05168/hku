package com.accentrix.hku.repository.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.QualificationRslt;
import com.accentrix.hku.repository.app.custom.QualificationRsltRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午7:10:25
 * @version 1.0
 */

@Repository
public interface QualificationRsltRepository
        extends JpaRepository<QualificationRslt, String>, QualificationRsltRepositoryCustom {

}
