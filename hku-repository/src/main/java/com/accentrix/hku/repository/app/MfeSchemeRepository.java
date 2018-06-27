package com.accentrix.hku.repository.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.MfeScheme;
import com.accentrix.hku.repository.app.custom.MfeSchemeRepositoryCustom;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年4月3日 下午6:42:11
 */
@Repository
public interface MfeSchemeRepository extends JpaRepository<MfeScheme, String>, MfeSchemeRepositoryCustom {

}
