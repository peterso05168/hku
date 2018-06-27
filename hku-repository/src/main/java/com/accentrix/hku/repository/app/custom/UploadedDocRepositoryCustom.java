package com.accentrix.hku.repository.app.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.UploadedDoc;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年1月31日 上午10:35:04 
 * @version 1.0 
 */

@Repository
public interface UploadedDocRepositoryCustom {

    List<UploadedDoc> findByReqDocId(String reqDocId);

}
