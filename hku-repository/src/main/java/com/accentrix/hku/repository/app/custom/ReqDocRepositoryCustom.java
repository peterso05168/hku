package com.accentrix.hku.repository.app.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.ReqDoc;
import com.accentrix.hku.vo.app.ReqDocVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年1月31日 上午10:30:57 
 * @version 1.0 
 */

@Repository
public interface ReqDocRepositoryCustom {

    List<ReqDocVo> findByReqDocType(String applicationId, String reqDocType);

    ReqDoc getByApplicationIdAndReqDocConfIdAndQualificationId(String applicationId, String reqDocConfId,
            String qualificationId);

}
