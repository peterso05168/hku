package com.accentrix.hku.repository.app.impl;

import java.util.List;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.app.QUploadedDoc;
import com.accentrix.hku.domain.app.UploadedDoc;
import com.accentrix.hku.repository.app.custom.UploadedDocRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 上午10:35:12
 * @version 1.0
 */

public class UploadedDocRepositoryImpl extends JpaDslQuery<UploadedDoc, QUploadedDoc>
        implements UploadedDocRepositoryCustom {

    @Override
    public List<UploadedDoc> findByReqDocId(String reqDocId) {
        eq($.reqDocId, reqDocId);
        return list();
    }

}
