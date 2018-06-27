package com.accentrix.hku.repository.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.BestExamSubjRslt;
import com.accentrix.hku.repository.app.custom.BestExamSubjRsltRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年5月29日 下午7:38:14
 * @version 1.0
 */

@Repository
public interface BestExamSubjRsltRepository
        extends JpaRepository<BestExamSubjRslt, String>, BestExamSubjRsltRepositoryCustom {

}
