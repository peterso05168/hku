package com.accentrix.hku.repository.adm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.adm.FormProgReq;
import com.accentrix.hku.repository.adm.custom.FormProgReqRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午7:02:49
 * @version 1.0
 */

@Repository
public interface FormProgReqRepository extends JpaRepository<FormProgReq, String>, FormProgReqRepositoryCustom {

}
