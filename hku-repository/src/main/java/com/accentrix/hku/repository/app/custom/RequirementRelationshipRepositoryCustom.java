package com.accentrix.hku.repository.app.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.RequirementRelationship;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年1月31日 上午10:33:37 
 * @version 1.0 
 */

@Repository
public interface RequirementRelationshipRepositoryCustom {

    List<RequirementRelationship> findListByParentId(String parentRequirementId);
}
