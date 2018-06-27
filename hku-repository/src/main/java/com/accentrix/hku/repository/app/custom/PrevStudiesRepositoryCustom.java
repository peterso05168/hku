package com.accentrix.hku.repository.app.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.PrevStudies;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年1月30日 下午7:08:35 
 * @version 1.0 
 */

@Repository
public interface PrevStudiesRepositoryCustom {

    public List<PrevStudies> findListByApplicationId(String applicationId);
}
