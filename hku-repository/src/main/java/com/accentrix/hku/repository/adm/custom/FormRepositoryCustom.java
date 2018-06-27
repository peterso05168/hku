package com.accentrix.hku.repository.adm.custom;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.adm.Form;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午7:02:02
 * @version 1.0
 */

@Repository
public interface FormRepositoryCustom {
    Form getByAdmExeId(String admExeId);
}
