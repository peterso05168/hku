package com.accentrix.hku.repository.app.impl;

import java.util.ArrayList;
import java.util.List;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.constant.Constants;
import com.accentrix.hku.domain.adm.QFormProg;
import com.accentrix.hku.domain.adm.QFormProgReq;
import com.accentrix.hku.domain.app.QProgrammeChoice;
import com.accentrix.hku.domain.app.QRequirement;
import com.accentrix.hku.domain.app.QRequirementRelationship;
import com.accentrix.hku.domain.app.Requirement;
import com.accentrix.hku.repository.app.custom.RequirementRepositoryCustom;
import com.accentrix.hku.vo.app.ProgChoiceReqSubjectVo;
import com.accentrix.hku.vo.xml.ExaminationVo;
import com.accentrix.hku.vo.xml.GpsVo;
import com.accentrix.hku.vo.xml.SubjectRequirementVo;
import com.accentrix.hku.xml.XmlConverter;
import com.querydsl.core.Tuple;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 上午10:32:53
 * @version 1.0
 */

public class RequirementRepositoryImpl extends JpaDslQuery<Requirement, QRequirement>
        implements RequirementRepositoryCustom {

    @Override
    public List<Requirement> findList(String desc, String type, Boolean isPublished) {
        contains($.description, desc);
        eq($.type, type);
        eq($.isPublished, isPublished);
        return list();
    }

    @Override
    public List<Requirement> findByIdNotIn(List<String> ids) {
        return createJPAQuery().select($).from($).where($.id.notIn(ids)).fetch();
    }

    @Override
    public List<ProgChoiceReqSubjectVo> findProgrammeChoiceRequirementSubjects() {
        QProgrammeChoice progChoice = QProgrammeChoice.programmeChoice;
        QFormProg formProg = QFormProg.formProg;
        QFormProgReq formProgReq = QFormProgReq.formProgReq;
        List<Tuple> tuples = createJPAQuery().select(progChoice.id, progChoice.applicationId, $.id, $.type, $.objXml)
                .from(progChoice).innerJoin(formProg).on(progChoice.admFormProgId.eq(formProg.id))
                .innerJoin(formProgReq).on(formProg.id.eq(formProgReq.admFormProgId)).innerJoin($)
                .on(formProgReq.appRequirementId.eq($.id)).orderBy(progChoice.applicationId.asc(), progChoice.id.asc())
                .fetch();
        List<ProgChoiceReqSubjectVo> progChoiceReqSubjects = new ArrayList<ProgChoiceReqSubjectVo>();
        for (Tuple tuple : tuples) {
            if (Constants.PUBLICEXAM.equals(tuple.get($.type))) {
                ExaminationVo examinatioin = (ExaminationVo) XmlConverter.fromXml(tuple.get($.objXml));
                for (SubjectRequirementVo subjectReq : examinatioin.getSubjectRequirements()) {
                    ProgChoiceReqSubjectVo progChoiceReqSubject = new ProgChoiceReqSubjectVo();
                    progChoiceReqSubject.setProgrammeChoiceId(tuple.get(progChoice.id));
                    progChoiceReqSubject.setApplicationId(tuple.get(progChoice.applicationId));
                    progChoiceReqSubject.setRequirementId(tuple.get($.id));
                    progChoiceReqSubject.setRequirementType(tuple.get($.type));
                    progChoiceReqSubject.setExamTypeId(examinatioin.getExamination());
                    progChoiceReqSubject.setSubject(subjectReq.getSubject());
                    progChoiceReqSubject.setGrade(subjectReq.getGrade());
                    progChoiceReqSubject.setExamLevel(subjectReq.getExamLevel());
                    progChoiceReqSubjects.add(progChoiceReqSubject);
                }
            } else if (Constants.GPS.equals(tuple.get($.type))) {
                GpsVo gps = (GpsVo) XmlConverter.fromXml(tuple.get($.objXml));
                ProgChoiceReqSubjectVo progChoiceReqSubject = new ProgChoiceReqSubjectVo();
                progChoiceReqSubject.setProgrammeChoiceId(tuple.get(progChoice.id));
                progChoiceReqSubject.setApplicationId(tuple.get(progChoice.applicationId));
                progChoiceReqSubject.setRequirementId(tuple.get($.id));
                progChoiceReqSubject.setRequirementType(tuple.get($.type));
                progChoiceReqSubject.setMinScore(gps.getMinScore());
                progChoiceReqSubject.setExamTypeId(gps.getExamination());
                progChoiceReqSubject.setOutOf45(gps.isOutOf45());
                progChoiceReqSubjects.add(progChoiceReqSubject);
            } else if (Constants.COMPOSITE.equals(tuple.get($.type))) {
                ProgChoiceReqSubjectVo progChoiceReqSubject = new ProgChoiceReqSubjectVo();
                progChoiceReqSubject.setProgrammeChoiceId(tuple.get(progChoice.id));
                progChoiceReqSubject.setApplicationId(tuple.get(progChoice.applicationId));
                progChoiceReqSubject.setRequirementId(tuple.get($.id));
                progChoiceReqSubject.setRequirementType(tuple.get($.type));
                //                progChoiceReqSubjects.add(progChoiceReqSubject);
                progChoiceReqSubjects.addAll(findChildRequirementSubjectsForProgrammeChoice(progChoiceReqSubject,
                        initNewChildProgChoiceReqSubject()));
            }
        }
        return progChoiceReqSubjects;
    }

    @Override
    public List<ProgChoiceReqSubjectVo> findChildRequirementSubjectsForProgrammeChoice(
            ProgChoiceReqSubjectVo progChoiceReqSubject, List<ProgChoiceReqSubjectVo> progChoiceReqSubjects) {
        QRequirementRelationship relation = QRequirementRelationship.requirementRelationship;
        List<Tuple> tuples = createJPAQuery().select($.id, $.type, $.objXml).from(relation).innerJoin($)
                .on(relation.childRequirementId.eq($.id))
                .where(relation.parentRequirementId.eq(progChoiceReqSubject.getRequirementId())).fetch();
        for (Tuple tuple : tuples) {
            if (Constants.PUBLICEXAM.equals(tuple.get($.type))) {
                ExaminationVo examinatioin = (ExaminationVo) XmlConverter.fromXml(tuple.get($.objXml));
                for (SubjectRequirementVo subjectReq : examinatioin.getSubjectRequirements()) {
                    ProgChoiceReqSubjectVo childProgChoiceReqSubject = new ProgChoiceReqSubjectVo();
                    childProgChoiceReqSubject.setProgrammeChoiceId(progChoiceReqSubject.getProgrammeChoiceId());
                    childProgChoiceReqSubject.setApplicationId(progChoiceReqSubject.getApplicationId());
                    childProgChoiceReqSubject.setRequirementId(tuple.get($.id));
                    childProgChoiceReqSubject.setRequirementType(tuple.get($.type));
                    childProgChoiceReqSubject.setExamTypeId(examinatioin.getExamination());
                    childProgChoiceReqSubject.setSubject(subjectReq.getSubject());
                    childProgChoiceReqSubject.setGrade(subjectReq.getGrade());
                    childProgChoiceReqSubject.setExamLevel(subjectReq.getExamLevel());
                    progChoiceReqSubjects.add(childProgChoiceReqSubject);
                }
            } else if (Constants.GPS.equals(tuple.get($.type))) {
                GpsVo gps = (GpsVo) XmlConverter.fromXml(tuple.get($.objXml));
                ProgChoiceReqSubjectVo childProgChoiceReqSubject = new ProgChoiceReqSubjectVo();
                childProgChoiceReqSubject.setProgrammeChoiceId(progChoiceReqSubject.getProgrammeChoiceId());
                childProgChoiceReqSubject.setApplicationId(progChoiceReqSubject.getApplicationId());
                childProgChoiceReqSubject.setRequirementId(tuple.get($.id));
                childProgChoiceReqSubject.setRequirementType(tuple.get($.type));
                childProgChoiceReqSubject.setExamTypeId(gps.getExamination());
                childProgChoiceReqSubject.setMinScore(gps.getMinScore());
                childProgChoiceReqSubject.setOutOf45(gps.isOutOf45());
                progChoiceReqSubjects.add(childProgChoiceReqSubject);
            } else if (Constants.COMPOSITE.equals(tuple.get($.type))) {
                ProgChoiceReqSubjectVo childProgChoiceReqSubject = new ProgChoiceReqSubjectVo();
                childProgChoiceReqSubject.setProgrammeChoiceId(progChoiceReqSubject.getProgrammeChoiceId());
                childProgChoiceReqSubject.setApplicationId(progChoiceReqSubject.getApplicationId());
                childProgChoiceReqSubject.setRequirementId(tuple.get($.id));
                childProgChoiceReqSubject.setRequirementType(tuple.get($.type));
                progChoiceReqSubjects.addAll(findChildRequirementSubjectsForProgrammeChoice(childProgChoiceReqSubject,
                        initNewChildProgChoiceReqSubject()));
            }
        }
        return progChoiceReqSubjects;
    }

    private List<ProgChoiceReqSubjectVo> initNewChildProgChoiceReqSubject() {
        return new ArrayList<ProgChoiceReqSubjectVo>();
    }
}
