package com.accentrix.hku.repository.app.impl;

import java.util.List;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.app.Progress;
import com.accentrix.hku.domain.app.QProgress;
import com.accentrix.hku.repository.app.custom.ProgressRepositoryCustom;
import com.accentrix.hku.vo.app.ProgressVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年3月1日 上午10:42:52
 * @version 1.0
 */

public class ProgressRepositoryImpl extends JpaDslQuery<Progress, QProgress> implements ProgressRepositoryCustom {

    @Override
    public List<Progress> findList(ProgressVo vo) {
        return list();
    }

    @Override
    public Progress findByApplicationId(String applicationId) {
        eq($.applicationId, applicationId);
        return unique();
    }

}
