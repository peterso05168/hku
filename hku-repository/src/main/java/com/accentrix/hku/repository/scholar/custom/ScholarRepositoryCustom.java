package com.accentrix.hku.repository.scholar.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.scholar.Scholar;
import com.accentrix.hku.vo.scholar.ScholarVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年3月19日 上午11:35:51
 * @version 1.0
 */

@Repository
public interface ScholarRepositoryCustom {

    Scholar getByName(String name);

    List<Scholar> basicSearch(String criteria);

    List<Scholar> advancedSearch(ScholarVo searchVo);

}
