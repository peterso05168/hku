package com.accentrix.hku.repository.app.custom;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.MfeScheme;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年4月3日 下午6:41:17
 */
@Repository
public interface MfeSchemeRepositoryCustom {
    MfeScheme getByQualificationIdAndYear(String qualificationId, Integer yearSemester);
}
