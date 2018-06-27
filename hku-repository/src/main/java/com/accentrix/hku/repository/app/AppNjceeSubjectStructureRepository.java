package com.accentrix.hku.repository.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.AppNjceeSubjectStructure;
import com.accentrix.hku.repository.app.custom.AppNjceeSubjectStructureRepositoryCustom;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年4月25日 下午9:27:18
 */
@Repository
public interface AppNjceeSubjectStructureRepository
        extends JpaRepository<AppNjceeSubjectStructure, String>, AppNjceeSubjectStructureRepositoryCustom {

}
