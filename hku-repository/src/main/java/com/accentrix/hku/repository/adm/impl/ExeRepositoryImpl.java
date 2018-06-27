package com.accentrix.hku.repository.adm.impl;

import java.util.List;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.adm.Exe;
import com.accentrix.hku.domain.adm.QExe;
import com.accentrix.hku.domain.adm.QForm;
import com.accentrix.hku.domain.applicant.QApplication;
import com.accentrix.hku.repository.adm.custom.ExeRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午7:01:33
 * @version 1.0
 */

public class ExeRepositoryImpl extends JpaDslQuery<Exe, QExe> implements ExeRepositoryCustom {

    @Override
    public Exe getByApplicationId(String applicationId) {
        QForm form = QForm.form;
        QApplication application = QApplication.application;
        return createJPAQuery().select($).from($).innerJoin(form).on($.id.eq(form.admExeId)).innerJoin(application)
                .on(form.id.eq(application.admFormId)).where(application.id.eq(applicationId)).fetchOne();
    }

    @Override
    public List<Integer> findAdmissionYear() {
        return createJPAQuery().distinct().select($.admissionYear).from($).orderBy($.admissionYear.desc()).fetch();
    }

}
