package com.accentrix.hku.repository.app.impl;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.app.Counselor;
import com.accentrix.hku.domain.app.QCounselor;
import com.accentrix.hku.repository.app.custom.CounselorRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午7:05:29
 * @version 1.0
 */

public class CounselorRepositoryImpl extends JpaDslQuery<Counselor, QCounselor> implements CounselorRepositoryCustom {

    @Override
    public Counselor findByFullName(String fullName) {
        eq($.fullName, fullName);
        return unique();
    }
}
