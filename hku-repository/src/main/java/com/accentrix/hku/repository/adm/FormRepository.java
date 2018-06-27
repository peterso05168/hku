package com.accentrix.hku.repository.adm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.adm.Form;
import com.accentrix.hku.repository.adm.custom.FormRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午7:01:54
 * @version 1.0
 */

@Repository
public interface FormRepository extends JpaRepository<Form, String>, FormRepositoryCustom {

}
