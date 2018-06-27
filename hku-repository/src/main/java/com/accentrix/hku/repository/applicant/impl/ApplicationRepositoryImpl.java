package com.accentrix.hku.repository.applicant.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.constant.Constants;
import com.accentrix.hku.domain.adm.QExe;
import com.accentrix.hku.domain.adm.QForm;
import com.accentrix.hku.domain.app.QPersonalParticulars;
import com.accentrix.hku.domain.app.QProgrammeChoice;
import com.accentrix.hku.domain.app.QQualification;
import com.accentrix.hku.domain.applicant.Application;
import com.accentrix.hku.domain.applicant.QAccount;
import com.accentrix.hku.domain.applicant.QApplication;
import com.accentrix.hku.repository.applicant.custom.ApplicationRepositoryCustom;
import com.accentrix.hku.vo.applicant.ApplicationVo;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 上午10:37:56
 * @version 1.0
 */

public class ApplicationRepositoryImpl extends JpaDslQuery<Application, QApplication>
        implements ApplicationRepositoryCustom {

    @Override
    public Application findByAdmFormIdAndAccountId(String admFormId, String applicantAccountId) {
        eq($.applicantAccountId, applicantAccountId);
        eq($.admFormId, admFormId);
        return unique();
    }

    @Override
    public List<Application> findByAccountIds(List<String> applicantAccountIds) {
        in($.applicantAccountId, applicantAccountIds);
        return list();
    }

    @Override
    public List<ApplicationVo> basicSearch(String criteria) {
        QPersonalParticulars personalParticulars = QPersonalParticulars.personalParticulars;
        QQualification qualification = QQualification.qualification;
        QProgrammeChoice programmeChoice = QProgrammeChoice.programmeChoice;

        JPAQuery<Tuple> queryQualification = createJPAQuery()
                .select($.id, $.applicationNo, personalParticulars.surname, personalParticulars.givenName,
                        qualification.id)
                .from($).leftJoin(personalParticulars).on(personalParticulars.id.eq($.personId)).leftJoin(qualification)
                .on(qualification.applicationId.eq($.id))
                .where(qualification.isDeleted.eq(true).and(qualification.isDeleteApproved.eq(false)));

        JPAQuery<Tuple> queryProgrammeChoice = createJPAQuery()
                .select($.id, $.applicationNo, personalParticulars.surname, personalParticulars.givenName,
                        programmeChoice.id)
                .from($).leftJoin(personalParticulars).on(personalParticulars.id.eq($.personId))
                .leftJoin(programmeChoice).on(programmeChoice.applicationId.eq($.id))
                .where(programmeChoice.isWithdrawn.eq(true).and(programmeChoice.isWithdrawnApproved.eq(false)));

        if (StringUtils.isNotBlank(criteria)) {
            queryQualification.where($.applicationNo.contains(criteria)
                    .or(personalParticulars.surname.contains(criteria)
                            .or(personalParticulars.givenName.contains(criteria)))
                    .or(personalParticulars.givenName.concat(" ").concat(personalParticulars.surname)
                            .contains(criteria)));
            queryProgrammeChoice.where($.applicationNo.contains(criteria)
                    .or(personalParticulars.surname.contains(criteria)
                            .or(personalParticulars.givenName.contains(criteria)))
                    .or(personalParticulars.givenName.concat(" ").concat(personalParticulars.surname)
                            .contains(criteria)));
        }

        List<ApplicationVo> vos = new ArrayList<ApplicationVo>();
        List<Tuple> qualificationTuples = queryQualification.fetch();
        for (Tuple tuple : qualificationTuples) {
            ApplicationVo vo = new ApplicationVo();
            vo.setId(tuple.get($.id));
            vo.setApplicationNo(tuple.get($.applicationNo));
            vo.setApplicantName(
                    tuple.get(personalParticulars.givenName) + " " + tuple.get(personalParticulars.surname));
            vo.setApprovalId(tuple.get(qualification.id));
            vo.setIsRemoveQualification(true);
            vo.setType(Constants.REMOVE_QUALIFICATION);
            vos.add(vo);
        }
        List<Tuple> programmeChoiceTuples = queryProgrammeChoice.fetch();
        for (Tuple tuple : programmeChoiceTuples) {
            ApplicationVo vo = new ApplicationVo();
            vo.setId(tuple.get($.id));
            vo.setApplicationNo(tuple.get($.applicationNo));
            vo.setApplicantName(
                    tuple.get(personalParticulars.givenName) + " " + tuple.get(personalParticulars.surname));
            vo.setApprovalId(tuple.get(programmeChoice.id));
            vo.setIsRemoveProgChoice(true);
            vo.setType(Constants.REMOVE_PROGRAMME_CHOICE);
            vos.add(vo);
        }
        return vos;
    }

    @Override
    public List<ApplicationVo> advanceSearch(ApplicationVo searchVo) {
        QPersonalParticulars personalParticulars = QPersonalParticulars.personalParticulars;
        QQualification qualification = QQualification.qualification;
        QProgrammeChoice programmeChoice = QProgrammeChoice.programmeChoice;

        JPAQuery<Tuple> queryQualification = createJPAQuery()
                .select($.id, $.applicationNo, personalParticulars.surname, personalParticulars.givenName,
                        qualification.id)
                .from($).leftJoin(personalParticulars).on(personalParticulars.id.eq($.personId)).leftJoin(qualification)
                .on(qualification.applicationId.eq($.id))
                .where(qualification.isDeleted.eq(true).and(qualification.isDeleteApproved.eq(false)));

        JPAQuery<Tuple> queryProgrammeChoice = createJPAQuery()
                .select($.id, $.applicationNo, personalParticulars.surname, personalParticulars.givenName,
                        programmeChoice.id)
                .from($).leftJoin(personalParticulars).on(personalParticulars.id.eq($.personId))
                .leftJoin(programmeChoice).on(programmeChoice.applicationId.eq($.id))
                .where(programmeChoice.isWithdrawn.eq(true).and(programmeChoice.isWithdrawnApproved.eq(false)));

        if (StringUtils.isNotBlank(searchVo.getApplicationNo())) {
            queryQualification.where($.applicationNo.contains(searchVo.getApplicationNo()));
            queryProgrammeChoice.where($.applicationNo.contains(searchVo.getApplicationNo()));
        }

        if (StringUtils.isNotBlank(searchVo.getApplicantName())) {
            queryQualification.where(personalParticulars.surname.contains(searchVo.getApplicantName())
                    .or(personalParticulars.givenName.contains(searchVo.getApplicantName()))
                    .or(personalParticulars.givenName.concat(" ").concat(personalParticulars.surname)
                            .contains(searchVo.getApplicantName())));
            queryProgrammeChoice.where(personalParticulars.surname.contains(searchVo.getApplicantName())
                    .or(personalParticulars.givenName.contains(searchVo.getApplicantName()))
                    .or(personalParticulars.givenName.concat(" ").concat(personalParticulars.surname)
                            .contains(searchVo.getApplicantName())));
        }

        List<ApplicationVo> qualiVos = new ArrayList<ApplicationVo>();
        List<Tuple> qualificationTuples = queryQualification.fetch();
        for (Tuple tuple : qualificationTuples) {
            ApplicationVo vo = new ApplicationVo();
            vo.setId(tuple.get($.id));
            vo.setApplicationNo(tuple.get($.applicationNo));
            vo.setApplicantName(
                    tuple.get(personalParticulars.givenName) + " " + tuple.get(personalParticulars.surname));
            vo.setApprovalId(tuple.get(qualification.id));
            vo.setIsRemoveQualification(true);
            vo.setType(Constants.REMOVE_QUALIFICATION);
            qualiVos.add(vo);
        }

        List<ApplicationVo> progVos = new ArrayList<ApplicationVo>();
        List<Tuple> programmeChoiceTuples = queryProgrammeChoice.fetch();
        for (Tuple tuple : programmeChoiceTuples) {
            ApplicationVo vo = new ApplicationVo();
            vo.setId(tuple.get($.id));
            vo.setApplicationNo(tuple.get($.applicationNo));
            vo.setApplicantName(
                    tuple.get(personalParticulars.givenName) + " " + tuple.get(personalParticulars.surname));
            vo.setApprovalId(tuple.get(programmeChoice.id));
            vo.setIsRemoveProgChoice(true);
            vo.setType(Constants.REMOVE_PROGRAMME_CHOICE);
            progVos.add(vo);
        }

        if (StringUtils.isBlank(searchVo.getType())) {
            qualiVos.addAll(progVos);
            return qualiVos;
        } else {
            if (Constants.REMOVE_QUALIFICATION.equals(searchVo.getType())) {
                return qualiVos;
            } else {
                return progVos;
            }
        }
    }

    @Override
    public List<ApplicationVo> findByApplicantId(String applicantId) {
        QAccount account = QAccount.account;
        QForm form = QForm.form;
        QExe exe = QExe.exe;
        List<Tuple> tuples = createJPAQuery()
                .select(exe.admissionYear, $.applicationNo, $.status, account.userInfoId, $.id).from($)
                .innerJoin(account).on($.applicantAccountId.eq(account.id)).leftJoin(form).on(form.id.eq($.admFormId))
                .leftJoin(exe).on(exe.id.eq(form.admExeId)).where(account.userInfoId.eq(applicantId)).fetch();
        List<ApplicationVo> applicationVos = new ArrayList<ApplicationVo>();
        for (Tuple tuple : tuples) {
            ApplicationVo vo = new ApplicationVo();
            vo.setAdmissionYear(tuple.get(exe.admissionYear).toString());
            vo.setApplicationNo(tuple.get($.applicationNo));
            vo.setStatus(tuple.get($.status));
            vo.setApplicantId(tuple.get(account.userInfoId));
            vo.setId(tuple.get($.id));
            applicationVos.add(vo);
        }
        return applicationVos;
    }

    @Override
    public List<Application> findByAccountId(String applicantAccountId) {
        eq($.applicantAccountId, applicantAccountId);
        return list();
    }

    @Override
    public ApplicationVo getByApplicationId(String applicationId) {
        String sql = "SELECT a.application_no,a.`status`,((b.acad_bg+b.prsnal_part+b.other_quali+b.choice_of_curri+b.exp_and_achi+b.reference+b.others)/7)*100,b.acad_bg+b.prsnal_part+b.other_quali+b.choice_of_curri+b.exp_and_achi+b.reference+b.others FROM applicant_application a INNER JOIN app_progress b ON a.application_id = b.application_id WHERE b.application_id = ?1";
        Query query = getEntityManager().createNativeQuery(sql);
        query.setParameter(1, applicationId);
        Object object = query.getSingleResult();
        ApplicationVo vo = new ApplicationVo();
        Object[] objects = (Object[]) object;
        vo.setApplicationNo(objects[0].toString());
        vo.setStatus(objects[1].toString());
        vo.setProgress(Math.round(Double.parseDouble(objects[2].toString())));
        vo.setNum(objects[3].toString());
        return vo;
    }

    @Override
    public List<Application> findByAccountIdAndStatus(String applicantAccountId, String status) {
        eq($.applicantAccountId, applicantAccountId);
        eq($.status, status);
        return list();
    }
}
