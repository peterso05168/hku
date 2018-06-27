package com.accentrix.hku.repository.adm.impl;

import java.util.List;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.adm.FormProgReq;
import com.accentrix.hku.domain.adm.QFormProgReq;
import com.accentrix.hku.repository.adm.custom.FormProgReqRepositoryCustom;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年1月30日 下午7:03:03 
 * @version 1.0 
 */

public class FormProgReqRepositoryImpl extends JpaDslQuery<FormProgReq, QFormProgReq>
        implements FormProgReqRepositoryCustom {

    @Override
    public List<FormProgReq> getByFormProgId(String formProgId) {
        eq($.admFormProgId, formProgId);
        return list();
    }

    @Override
    public List<FormProgReq> getByRequirementId(String requirementId) {
        eq($.appRequirementId, requirementId);
        return list();
    }

}
