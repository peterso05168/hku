package com.accentrix.hku.repository.adm.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.adm.FormProgReq;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年1月30日 下午7:02:57 
 * @version 1.0 
 */

@Repository
public interface FormProgReqRepositoryCustom {

    List<FormProgReq> getByFormProgId(String formProgId);

    List<FormProgReq> getByRequirementId(String requirementId);

}
