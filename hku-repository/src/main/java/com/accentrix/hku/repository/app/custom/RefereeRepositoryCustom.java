package com.accentrix.hku.repository.app.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.Referee;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年1月30日 下午7:11:03 
 * @version 1.0 
 */

@Repository
public interface RefereeRepositoryCustom {

    public List<Referee> findListByReferenceId(String referenceId);
}
