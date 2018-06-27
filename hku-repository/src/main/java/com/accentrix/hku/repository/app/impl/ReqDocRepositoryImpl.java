package com.accentrix.hku.repository.app.impl;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.constant.Constants;
import com.accentrix.hku.domain.app.QQualification;
import com.accentrix.hku.domain.app.QReqDoc;
import com.accentrix.hku.domain.app.QReqDocConf;
import com.accentrix.hku.domain.app.QUploadedDoc;
import com.accentrix.hku.domain.app.ReqDoc;
import com.accentrix.hku.repository.app.custom.ReqDocRepositoryCustom;
import com.accentrix.hku.vo.app.ReqDocVo;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 上午10:31:03
 * @version 1.0
 */

public class ReqDocRepositoryImpl extends JpaDslQuery<ReqDoc, QReqDoc> implements ReqDocRepositoryCustom {

    @Override
    public List<ReqDocVo> findByReqDocType(String applicationId, String reqDocType) {
        QReqDocConf reqDocConf = QReqDocConf.reqDocConf;
        QUploadedDoc uploadedDoc = QUploadedDoc.uploadedDoc;
        QQualification qualification = QQualification.qualification;
        List<Tuple> tuples = createJPAQuery()
                .select($.id, $.applicationId, $.statusCd, uploadedDoc.submissionDate, $.submissionDueDate,
                        uploadedDoc.remark, $.reqDocConfId, reqDocConf.reqDocName, $.qualificationId,
                        qualification.examTypeMonth, qualification.examTypeYear, reqDocConf.toolTipMsg, uploadedDoc.id,
                        uploadedDoc.fileName, uploadedDoc.displayFileName, uploadedDoc.filePath)
                .from($).join(reqDocConf).on($.reqDocConfId.eq(reqDocConf.id)).leftJoin(uploadedDoc)
                .on($.id.eq(uploadedDoc.reqDocId)).where(reqDocConf.reqDocType.eq(reqDocType)).leftJoin(qualification)
                .on($.qualificationId.eq(qualification.id)).where($.applicationId.eq(applicationId))
                .orderBy(new OrderSpecifier<>(Order.ASC, reqDocConf.reqDocName),
                        new OrderSpecifier<>(Order.ASC, uploadedDoc.submissionDate),
                        new OrderSpecifier<>(Order.ASC, uploadedDoc.displayFileName))
                .fetch();
        List<ReqDocVo> vos = new ArrayList<ReqDocVo>();
        for (Tuple tuple : tuples) {
            ReqDocVo vo = new ReqDocVo();
            vo.setId(tuple.get($.id));
            vo.setApplicationId(tuple.get($.applicationId));
            vo.setStatusCd(tuple.get($.statusCd));
            vo.setSubmissionDate(tuple.get(uploadedDoc.submissionDate));
            vo.setSubmissionDueDate(tuple.get($.submissionDueDate));
            vo.setRemark(tuple.get(uploadedDoc.remark));
            vo.setReqDocConfId(tuple.get($.reqDocConfId));
            vo.setQualificationId(tuple.get($.qualificationId));
            if (Constants.DOC_TYPE_OTHERS.equals(reqDocType) && StringUtils.isNotBlank(tuple.get($.qualificationId))) {
                String month = "";
                if (null != tuple.get(qualification.examTypeMonth) && 0 != tuple.get(qualification.examTypeMonth))
                    month = Month.of(Integer.valueOf(tuple.get(qualification.examTypeMonth)))
                            .getDisplayName(TextStyle.FULL, Constants.LOCALE_UK) + ", ";
                vo.setReqDocName(tuple.get(reqDocConf.reqDocName).replace(" Transcript",
                        " - (" + month + tuple.get(qualification.examTypeYear) + ") Transcript"));
            } else
                vo.setReqDocName(tuple.get(reqDocConf.reqDocName));
            vo.setToolTipMsg(tuple.get(reqDocConf.toolTipMsg));
            vo.setUploadedDocId(tuple.get(uploadedDoc.id));
            vo.setFileName(tuple.get(uploadedDoc.fileName));
            vo.setDisplayFileName(tuple.get(uploadedDoc.displayFileName));
            vo.setFilePath(tuple.get(uploadedDoc.filePath));
            vos.add(vo);
        }
        return vos;
    }

    @Override
    public ReqDoc getByApplicationIdAndReqDocConfIdAndQualificationId(String applicationId, String reqDocConfId,
            String qualificationId) {
        eq($.applicationId, applicationId);
        eq($.reqDocConfId, reqDocConfId);
        eq($.qualificationId, qualificationId);
        return unique();
    }

}
