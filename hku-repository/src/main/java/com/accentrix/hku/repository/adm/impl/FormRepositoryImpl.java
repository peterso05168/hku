package com.accentrix.hku.repository.adm.impl;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.adm.Form;
import com.accentrix.hku.domain.adm.QForm;
import com.accentrix.hku.repository.adm.custom.FormRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午7:02:07
 * @version 1.0
 */

public class FormRepositoryImpl extends JpaDslQuery<Form, QForm> implements FormRepositoryCustom {

    @Override
    public Form getByAdmExeId(String admExeId) {
        eq($.admExeId, admExeId);
        return unique();
    }

}
