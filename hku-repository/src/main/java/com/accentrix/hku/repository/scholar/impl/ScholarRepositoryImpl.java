package com.accentrix.hku.repository.scholar.impl;

import java.util.List;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.scholar.QScholar;
import com.accentrix.hku.domain.scholar.Scholar;
import com.accentrix.hku.repository.scholar.custom.ScholarRepositoryCustom;
import com.accentrix.hku.vo.scholar.ScholarVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年3月19日 上午11:36:15 
 * @version 1.0 
 */

public class ScholarRepositoryImpl extends JpaDslQuery<Scholar, QScholar> implements ScholarRepositoryCustom {

    @Override
    public Scholar getByName(String name) {
        eq($.name, name);
        return unique();
    }

    @Override
    public List<Scholar> basicSearch(String criteria) {
        if (criteria != null)
            contains($.name, criteria);
        return list();
    }

    @Override
    public List<Scholar> advancedSearch(ScholarVo searchVo) {
        if (searchVo.getName() != null)
            contains($.name, searchVo.getName());
        if (searchVo.getStatus() != null)
            eq($.status, searchVo.getStatus());
        return list();
    }
}
