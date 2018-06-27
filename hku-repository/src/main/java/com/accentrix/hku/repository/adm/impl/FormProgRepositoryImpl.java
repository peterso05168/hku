package com.accentrix.hku.repository.adm.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.adm.FormProg;
import com.accentrix.hku.domain.adm.QExe;
import com.accentrix.hku.domain.adm.QForm;
import com.accentrix.hku.domain.adm.QFormProg;
import com.accentrix.hku.domain.app.QHkuProgramme;
import com.accentrix.hku.repository.adm.custom.FormProgRepositoryCustom;
import com.accentrix.hku.vo.adm.FormProgVo;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午7:02:38
 * @version 1.0
 */

public class FormProgRepositoryImpl extends JpaDslQuery<FormProg, QFormProg> implements FormProgRepositoryCustom {

    @Override
    public List<FormProg> getByAdmFormId(String admFormId) {
        if (StringUtils.isNotBlank(admFormId)) {
            eq($.admFormId, admFormId);
        } else {
            isNull($.admFormId);
        }
        return list();
    }

    @Override
    public List<FormProgVo> basicSearch(String criteria) {
        QForm form = QForm.form;
        QExe exe = QExe.exe;
        QHkuProgramme hkuProgramme = QHkuProgramme.hkuProgramme;
        JPAQuery<Tuple> query = createJPAQuery()
                .select($.id, $.admFormId, $.appHkuProgrammeId, $.quotaLocal, $.quotaOverseas, $.levelOfEntry,
                        exe.admissionYear, hkuProgramme.hkuProgrammeCd, hkuProgramme.hkuProgrammeDesc,
                        hkuProgramme.facultyCd)
                .from($).leftJoin(form).on($.admFormId.eq(form.id)).leftJoin(exe).on(form.admExeId.eq(exe.id))
                .leftJoin(hkuProgramme).on($.appHkuProgrammeId.eq(hkuProgramme.id));
        if (criteria != null) {
            query.where(hkuProgramme.hkuProgrammeCd.contains(criteria)
                    .or(hkuProgramme.hkuProgrammeDesc.contains(criteria)));
        }
        List<Tuple> tuples = query.fetch();
        List<FormProgVo> vos = new ArrayList<FormProgVo>();
        for (Tuple tuple : tuples) {
            FormProgVo vo = new FormProgVo();
            vo.setId(tuple.get($.id));
            vo.setAdmFormId(tuple.get($.admFormId));
            vo.setAppHkuProgrammeId(tuple.get($.appHkuProgrammeId));
            vo.setQuotaLocal(tuple.get($.quotaLocal));
            vo.setQuotaOverseas(tuple.get($.quotaOverseas));
            vo.setLevelOfEntry(tuple.get($.levelOfEntry));
            vo.setAdmissionYear(tuple.get(exe.admissionYear));
            vo.setProgrammeCode(tuple.get(hkuProgramme.hkuProgrammeCd));
            vo.setProgrammeTitle(tuple.get(hkuProgramme.hkuProgrammeDesc));
            vo.setFaculty(tuple.get(hkuProgramme.facultyCd));
            vos.add(vo);
        }
        return vos;
    }

    @Override
    public List<FormProgVo> advanceSearch(FormProgVo searchVo) {
        QForm form = QForm.form;
        QExe exe = QExe.exe;
        QHkuProgramme hkuProgramme = QHkuProgramme.hkuProgramme;

        BooleanExpression whereClause = null;
        BooleanExpression subClause = null;
        if (searchVo.getAdmissionYear() != null && searchVo.getAdmissionYear() > 0) {
            whereClause = exe.admissionYear.like("%" + searchVo.getAdmissionYear().toString() + "%");
        }
        if (StringUtils.isNotBlank(searchVo.getProgrammeCode())) {
            subClause = hkuProgramme.hkuProgrammeCd.contains(searchVo.getProgrammeCode());
            whereClause = whereClause != null ? whereClause.and(subClause) : subClause;
        }
        if (StringUtils.isNotBlank(searchVo.getProgrammeTitle())) {
            subClause = hkuProgramme.hkuProgrammeDesc.contains(searchVo.getProgrammeTitle());
            whereClause = whereClause != null ? whereClause.and(subClause) : subClause;
        }
        if (StringUtils.isNotBlank(searchVo.getFaculty())) {
            subClause = hkuProgramme.facultyCd.eq(searchVo.getFaculty());
            whereClause = whereClause != null ? whereClause.and(subClause) : subClause;
        }
        if (searchVo.getLevelOfEntry() != null && searchVo.getLevelOfEntry() > 0) {
            subClause = $.levelOfEntry.eq(searchVo.getLevelOfEntry());
            whereClause = whereClause != null ? whereClause.and(subClause) : subClause;
        }

        JPAQuery<Tuple> jpaQuery = createJPAQuery()
                .select($.id, $.admFormId, $.appHkuProgrammeId, $.quotaLocal, $.quotaOverseas, $.levelOfEntry,
                        exe.admissionYear, hkuProgramme.hkuProgrammeCd, hkuProgramme.hkuProgrammeDesc,
                        hkuProgramme.facultyCd)
                .from($).leftJoin(form).on($.admFormId.eq(form.id)).leftJoin(exe).on(form.admExeId.eq(exe.id))
                .leftJoin(hkuProgramme).on($.appHkuProgrammeId.eq(hkuProgramme.id));
        if (whereClause != null) {
            jpaQuery = jpaQuery.where(whereClause);
        }

        List<Tuple> tuples = jpaQuery.fetch();
        List<FormProgVo> vos = new ArrayList<FormProgVo>();
        for (Tuple tuple : tuples) {
            FormProgVo vo = new FormProgVo();
            vo.setId(tuple.get($.id));
            vo.setAdmFormId(tuple.get($.admFormId));
            vo.setAppHkuProgrammeId(tuple.get($.appHkuProgrammeId));
            vo.setQuotaLocal(tuple.get($.quotaLocal));
            vo.setQuotaOverseas(tuple.get($.quotaOverseas));
            vo.setLevelOfEntry(tuple.get($.levelOfEntry));
            vo.setAdmissionYear(tuple.get(exe.admissionYear));
            vo.setProgrammeCode(tuple.get(hkuProgramme.hkuProgrammeCd));
            vo.setProgrammeTitle(tuple.get(hkuProgramme.hkuProgrammeDesc));
            vo.setFaculty(tuple.get(hkuProgramme.facultyCd));
            vos.add(vo);
        }
        return vos;
    }

    @Override
    public List<FormProgVo> findVos(Integer year) {
        QForm form = QForm.form;
        QExe exe = QExe.exe;
        QHkuProgramme hkuProgramme = QHkuProgramme.hkuProgramme;
        List<FormProgVo> vos = new ArrayList<FormProgVo>();
        List<Tuple> tuples = createJPAQuery().select($.id, hkuProgramme.hkuProgrammeDesc).from($).innerJoin(form)
                .on($.admFormId.eq(form.id)).innerJoin(exe).on(form.admExeId.eq(exe.id)).innerJoin(hkuProgramme)
                .on($.appHkuProgrammeId.eq(hkuProgramme.id)).where(exe.admissionYear.eq(year)).fetch();
        for (Tuple tuple : tuples) {
            FormProgVo vo = new FormProgVo();
            vo.setId(tuple.get($.id));
            vo.setAppHkuProgrammeName(tuple.get(hkuProgramme.hkuProgrammeDesc));
            vos.add(vo);
        }
        return vos;
    }

    @Override
    public List<FormProg> findByHkuProgrammeId(String programmeId) {
        eq($.appHkuProgrammeId, programmeId);
        return list();
    }

}
