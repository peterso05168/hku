package com.accentrix.hku.repository.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.AppProgChoiceGpsRslt;
import com.accentrix.hku.repository.app.custom.AppProgChoiceGpsRsltRepositoryCustom;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年6月1日 下午1:34:55
 */
@Repository
public interface AppProgChoiceGpsRsltRepository
        extends JpaRepository<AppProgChoiceGpsRslt, String>, AppProgChoiceGpsRsltRepositoryCustom {

}
