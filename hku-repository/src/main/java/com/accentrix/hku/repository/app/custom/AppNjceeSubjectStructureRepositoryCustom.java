package com.accentrix.hku.repository.app.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.AppNjceeSubjectStructure;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年4月25日 下午9:26:45
 */
@Repository
public interface AppNjceeSubjectStructureRepositoryCustom {
    List<AppNjceeSubjectStructure> getByAppNjceeScoringSystemId(String appNjceeScoringSystemId);

    List<AppNjceeSubjectStructure> getByAppNjceeScoringSystemIdAndType(String appNjceeScoringSystemId, String type);
}
