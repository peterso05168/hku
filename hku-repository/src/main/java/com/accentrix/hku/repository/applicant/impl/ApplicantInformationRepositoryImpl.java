package com.accentrix.hku.repository.applicant.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.adm.QExe;
import com.accentrix.hku.domain.adm.QForm;
import com.accentrix.hku.domain.app.QTelNo;
import com.accentrix.hku.domain.applicant.ApplicantInformation;
import com.accentrix.hku.domain.applicant.QAccount;
import com.accentrix.hku.domain.applicant.QApplicantInformation;
import com.accentrix.hku.domain.applicant.QApplicantToTag;
import com.accentrix.hku.domain.applicant.QApplication;
import com.accentrix.hku.domain.tag.QTag;
import com.accentrix.hku.repository.applicant.custom.ApplicantInformationRepositoryCustom;
import com.accentrix.hku.vo.applicant.ApplicantInformationVo;
import com.querydsl.core.BooleanBuilder;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 上午10:38:22
 * @version 1.0
 */

public class ApplicantInformationRepositoryImpl extends JpaDslQuery<ApplicantInformation, QApplicantInformation>
        implements ApplicantInformationRepositoryCustom {

    @Override
    public List<ApplicantInformation> findPage(ApplicantInformationVo vo, Integer offset, Integer pageSize) {
        QExe exe = QExe.exe;
        QForm form = QForm.form;
        QAccount account = QAccount.account;
        QApplication application = QApplication.application;
        QTelNo telNo = QTelNo.telNo;
        QTag tag = QTag.tag;
        QApplicantToTag applicantToTag = QApplicantToTag.applicantToTag;
        orContains($.surname, $.givenName, vo.getName());
        eq(exe.admissionYear, vo.getAdmissionYear());
        contains(application.applicationNo, vo.getApplicationNo());
        contains(account.personEmail, vo.getRegisteredEmail());
        eq($.nationalityCountryId, vo.getNationalityCountryId());
        if (StringUtils.isNotBlank(vo.getNationalityProvinceId())) {
            eq($.nationalityProvinceId, vo.getNationalityProvinceId());
        } else if (StringUtils.isNotBlank(vo.getNationalityCityId())) {
            eq($.nationalityCityId, vo.getNationalityCityId());
        }
        contains(telNo.number, vo.getMobile());
        contains(tag.desc, vo.getIncludeTag());
        BooleanBuilder builder = getCurrentBooleanBuilder();
        List<ApplicantInformation> list = new ArrayList<ApplicantInformation>();
        if (StringUtils.isNotBlank(vo.getSearchName())) {
            list = createJPAQuery().distinct().select($).from($).innerJoin(account).on($.id.eq(account.userInfoId))
                    .leftJoin(telNo).on(telNo.id.eq($.mobileTelId)).leftJoin(application)
                    .on(account.id.eq(application.applicantAccountId)).leftJoin(form)
                    .on(form.id.eq(application.admFormId)).leftJoin(exe).on(exe.id.eq(form.admExeId))
                    .leftJoin(applicantToTag).on(applicantToTag.applicantInfoId.eq($.id)).leftJoin(tag)
                    .on(tag.id.eq(applicantToTag.tagId))
                    .where(account.personEmail.contains(vo.getSearchName())
                            .or(($.surname.contains(vo.getSearchName()).or($.givenName.contains(vo.getSearchName())))
                                    .or(application.applicationNo.contains(vo.getSearchName())).or(
                                            $.hkid.contains(vo.getSearchName()))))
                    .offset(offset).limit(pageSize).fetch();
        } else {
            if (StringUtils.isNotBlank(vo.getExcludeTag())) {
                builder = builder.and(tag.desc.notLike(vo.getExcludeTag()));
            }
            list = createJPAQuery().distinct().select($).from($).innerJoin(account).on($.id.eq(account.userInfoId))
                    .leftJoin(telNo).on(telNo.id.eq($.mobileTelId)).leftJoin(application)
                    .on(account.id.eq(application.applicantAccountId)).leftJoin(form)
                    .on(form.id.eq(application.admFormId)).leftJoin(exe).on(exe.id.eq(form.admExeId))
                    .leftJoin(applicantToTag).on(applicantToTag.applicantInfoId.eq($.id)).leftJoin(tag)
                    .on(tag.id.eq(applicantToTag.tagId)).where(builder).offset(offset).limit(pageSize).fetch();
        }
        removeCurrentBooleanBuilder();
        return list;
    }

    @Override
    public Integer countNumber(ApplicantInformationVo vo) {
        QExe exe = QExe.exe;
        QForm form = QForm.form;
        QAccount account = QAccount.account;
        QApplication application = QApplication.application;
        QTelNo telNo = QTelNo.telNo;
        QTag tag = QTag.tag;
        QApplicantToTag applicantToTag = QApplicantToTag.applicantToTag;
        orContains($.surname, $.givenName, vo.getName());
        eq(exe.admissionYear, vo.getAdmissionYear());
        contains(application.applicationNo, vo.getApplicationNo());
        contains(account.personEmail, vo.getRegisteredEmail());
        eq($.nationalityCountryId, vo.getNationalityCountryId());
        if (StringUtils.isNotBlank(vo.getNationalityProvinceId())) {
            eq($.nationalityProvinceId, vo.getNationalityProvinceId());
        } else if (StringUtils.isNotBlank(vo.getNationalityCityId())) {
            eq($.nationalityCityId, vo.getNationalityCityId());
        }
        contains(telNo.number, vo.getMobile());
        BooleanBuilder builder = getCurrentBooleanBuilder();
        Long num = 0L;
        if (StringUtils.isNotBlank(vo.getSearchName())) {
            num = createJPAQuery().distinct().select($).from($).innerJoin(account).on($.id.eq(account.userInfoId))
                    .leftJoin(telNo).on(telNo.id.eq($.mobileTelId)).leftJoin(application)
                    .on(account.id.eq(application.applicantAccountId)).leftJoin(form)
                    .on(form.id.eq(application.admFormId)).leftJoin(exe).on(exe.id.eq(form.admExeId))
                    .leftJoin(applicantToTag).on(applicantToTag.applicantInfoId.eq($.id)).leftJoin(tag)
                    .on(tag.id.eq(applicantToTag.tagId))
                    .where(account.personEmail.contains(vo.getSearchName())
                            .or(($.surname.contains(vo.getSearchName()).or($.givenName.contains(vo.getSearchName())))
                                    .or(application.applicationNo.contains(vo.getSearchName())).or(
                                            $.hkid.contains(vo.getSearchName()))))
                    .fetchCount();
        } else {
            if (StringUtils.isNotBlank(vo.getExcludeTag())) {
                builder = builder.and(tag.desc.notLike(vo.getExcludeTag()));
            }
            num = createJPAQuery().distinct().select($.id).from($).innerJoin(account).on($.id.eq(account.userInfoId))
                    .leftJoin(telNo).on(telNo.id.eq($.mobileTelId)).leftJoin(application)
                    .on(account.id.eq(application.applicantAccountId)).leftJoin(form)
                    .on(form.id.eq(application.admFormId)).leftJoin(exe).on(exe.id.eq(form.admExeId))
                    .leftJoin(applicantToTag).on(applicantToTag.applicantInfoId.eq($.id)).leftJoin(tag)
                    .on(tag.id.eq(applicantToTag.tagId)).where(builder).fetchCount();
        }
        return num.intValue();
    }

}
