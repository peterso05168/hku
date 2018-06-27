package com.accentrix.hku.repository.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.AppNjceeScoringSystem;
import com.accentrix.hku.repository.app.custom.AppNjceeScoringSystemRepositoryCustom;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年4月25日 下午7:50:30
 */
@Repository
public interface AppNjceeScoringSystemRepository
        extends JpaRepository<AppNjceeScoringSystem, String>, AppNjceeScoringSystemRepositoryCustom {

}
