package com.accentrix.hku.repository.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.UploadedDoc;
import com.accentrix.hku.repository.app.custom.UploadedDocRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 上午10:34:53
 * @version 1.0
 */

@Repository
public interface UploadedDocRepository extends JpaRepository<UploadedDoc, String>, UploadedDocRepositoryCustom {

}
