package com.accentrix.hku.repository.app.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.adm.QForm;
import com.accentrix.hku.domain.adm.QFormProg;
import com.accentrix.hku.domain.app.ProgrammeChoice;
import com.accentrix.hku.domain.app.QHkuProgramme;
import com.accentrix.hku.domain.app.QPersonalParticulars;
import com.accentrix.hku.domain.app.QProgrammeChoice;
import com.accentrix.hku.domain.applicant.QApplication;
import com.accentrix.hku.repository.app.custom.ProgrammeChoiceRepositoryCustom;
import com.accentrix.hku.vo.app.ProgrammeChoiceVo;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午7:09:11
 * @version 1.0
 */

public class ProgrammeChoiceRepositoryImpl extends JpaDslQuery<ProgrammeChoice, QProgrammeChoice>
        implements ProgrammeChoiceRepositoryCustom {

    @Override
    public ProgrammeChoice getFirstChoiceByApplicationId(String applicationId) {
        eq($.firstChoice, true);
        eq($.isWithdrawnApproved, false);
        eq($.applicationId, applicationId);
        return unique();
    }

    @Override
    public List<ProgrammeChoice> getOtherChoiceByApplicationId(String applicationId) {
        eq($.firstChoice, false);
        eq($.isWithdrawnApproved, false);
        eq($.applicationId, applicationId);
        return list();
    }

    @Override
    public List<ProgrammeChoice> findByHkuProgrammeId(String programmeId) {
        QFormProg formProg = QFormProg.formProg;
        QHkuProgramme hkuProgramme = QHkuProgramme.hkuProgramme;
        return createJPAQuery().select($).from($).leftJoin(formProg).on($.admFormProgId.eq(formProg.id))
                .leftJoin(hkuProgramme).on(formProg.appHkuProgrammeId.eq(hkuProgramme.id))
                .where(hkuProgramme.id.eq(programmeId)).fetch();
    }

    @Override
    public List<ProgrammeChoice> findByFormProgId(String formProgId) {
        eq($.admFormProgId, formProgId);
        return list();
    }

    @Override
    public List<ProgrammeChoice> getChoiceByApplicationId(String applicationId) {
        eq($.isWithdrawnApproved, false);
        eq($.applicationId, applicationId);
        return list();
    }

    @Override
    public List<ProgrammeChoiceVo> findListVo(ProgrammeChoiceVo vo, Integer offset, Integer pageSize) {
        vo.setMeetReq(StringUtils.isNotBlank(vo.getMeetReqStr()) ? Boolean.parseBoolean(vo.getMeetReqStr()) : null);
        vo.setEngReq(StringUtils.isNotBlank(vo.getEngReqStr()) ? Boolean.parseBoolean(vo.getEngReqStr()) : null);
        QApplication application = QApplication.application;
        QForm form = QForm.form;
        QFormProg formProg = QFormProg.formProg;
        QHkuProgramme hkuProgramme = QHkuProgramme.hkuProgramme;
        QPersonalParticulars personalParticulars = QPersonalParticulars.personalParticulars;
        eq(hkuProgramme.id, vo.getHkuProgrammeId());
        eq($.offerStatusCd, vo.getOfferStatusCd());
        eq(application.status, vo.getApplicationStatus());
        eq(personalParticulars.region, vo.getRegion());
        eq(application.engReq, vo.getEngReq());
        eq($.meetReq, vo.getMeetReq());
        notBlank(application.applicationNo);
        BooleanBuilder builder = getCurrentBooleanBuilder();
        List<Tuple> tuples = createJPAQuery().distinct()
                .select(application.applicationNo, application.engReq, application.ccaigInterview,
                        application.ccaiiInterview, $.progInterviewScore, $.otherInterview, $.offerStatusCd, $.id,
                        $.offerType, $.conditionalType, application.id, personalParticulars.region)
                .from($).innerJoin(application).on($.applicationId.eq(application.id)).innerJoin(form)
                .on(application.admFormId.eq(form.id)).innerJoin(formProg)
                .on(formProg.admFormId.eq(form.id).and(formProg.id.eq($.admFormProgId))).innerJoin(hkuProgramme)
                .on(formProg.appHkuProgrammeId.eq(hkuProgramme.id)).innerJoin(personalParticulars)
                .on(application.personId.eq(personalParticulars.id)).where(builder).offset(offset).limit(pageSize)
                .fetch();
        List<ProgrammeChoiceVo> vos = new ArrayList<ProgrammeChoiceVo>();
        for (Tuple tuple : tuples) {
            ProgrammeChoiceVo programmeChoiceVo = new ProgrammeChoiceVo();
            programmeChoiceVo.setId(tuple.get($.id));
            programmeChoiceVo.setApplicationNo(tuple.get(application.applicationNo));
            programmeChoiceVo.setEngReq(tuple.get(application.engReq));
            programmeChoiceVo.setCcaigInterview(tuple.get(application.ccaigInterview));
            programmeChoiceVo.setCcaiiInterview(tuple.get(application.ccaiiInterview));
            programmeChoiceVo.setProgInterviewScore(tuple.get($.progInterviewScore));
            programmeChoiceVo.setOtherInterview(tuple.get($.otherInterview));
            programmeChoiceVo.setOfferStatusCd(tuple.get($.offerStatusCd));
            programmeChoiceVo.setOfferType(tuple.get($.offerType));
            programmeChoiceVo.setConditionalType(tuple.get($.conditionalType));
            programmeChoiceVo.setApplicationId(tuple.get(application.id));
            programmeChoiceVo.setRegion(tuple.get(personalParticulars.region));
            vos.add(programmeChoiceVo);
        }
        removeCurrentBooleanBuilder();
        return vos;
    }

    @Override
    public int findCount(ProgrammeChoiceVo vo) {
        vo.setMeetReq(StringUtils.isNotBlank(vo.getMeetReqStr()) ? Boolean.parseBoolean(vo.getMeetReqStr()) : null);
        vo.setEngReq(StringUtils.isNotBlank(vo.getEngReqStr()) ? Boolean.parseBoolean(vo.getEngReqStr()) : null);
        QApplication application = QApplication.application;
        QForm form = QForm.form;
        QFormProg formProg = QFormProg.formProg;
        QHkuProgramme hkuProgramme = QHkuProgramme.hkuProgramme;
        QPersonalParticulars personalParticulars = QPersonalParticulars.personalParticulars;
        eq(hkuProgramme.id, vo.getHkuProgrammeId());
        eq($.offerStatusCd, vo.getOfferStatusCd());
        eq(application.status, vo.getApplicationStatus());
        eq(personalParticulars.region, vo.getRegion());
        eq(application.engReq, vo.getEngReq());
        eq($.meetReq, vo.getMeetReq());
        notBlank(application.applicationNo);
        BooleanBuilder builder = getCurrentBooleanBuilder();
        long number = createJPAQuery().select($.id).from($).innerJoin(application)
                .on($.applicationId.eq(application.id)).innerJoin(form).on(application.admFormId.eq(form.id))
                .innerJoin(formProg).on(formProg.admFormId.eq(form.id).and(formProg.id.eq($.admFormProgId)))
                .innerJoin(hkuProgramme).on(formProg.appHkuProgrammeId.eq(hkuProgramme.id))
                .innerJoin(personalParticulars).on(application.personId.eq(personalParticulars.id)).where(builder)
                .fetchCount();
        return (int) number;
    }
}
