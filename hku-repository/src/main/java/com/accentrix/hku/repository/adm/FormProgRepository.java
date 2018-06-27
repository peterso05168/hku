package com.accentrix.hku.repository.adm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.adm.FormProg;
import com.accentrix.hku.repository.adm.custom.FormProgRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午7:02:20
 * @version 1.0
 */

@Repository
public interface FormProgRepository extends JpaRepository<FormProg, String>, FormProgRepositoryCustom {

}
