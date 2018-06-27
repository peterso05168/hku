package com.accentrix.hku.repository.staff.impl;

import java.util.List;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.staff.Prog;
import com.accentrix.hku.domain.staff.QProg;
import com.accentrix.hku.repository.staff.custom.ProgRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 上午10:44:50
 * @version 1.0
 */

public class ProgRepositoryImpl extends JpaDslQuery<Prog, QProg> implements ProgRepositoryCustom {

    @Override
    public List<Prog> findByStaffId(String staffId) {
        eq($.staffId, staffId);
        return list();
    }

}
