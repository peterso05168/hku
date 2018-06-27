package com.accentrix.hku.repository.app.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.Progress;
import com.accentrix.hku.vo.app.ProgressVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年3月1日 上午10:42:41 
 * @version 1.0 
 */

@Repository
public interface ProgressRepositoryCustom {

    List<Progress> findList(ProgressVo vo);

    Progress findByApplicationId(String applicationId);
}
