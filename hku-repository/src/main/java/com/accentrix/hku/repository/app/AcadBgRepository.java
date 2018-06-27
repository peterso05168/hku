package com.accentrix.hku.repository.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.AcadBg;
import com.accentrix.hku.repository.app.custom.AcadBgRepositoryCustom;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年1月30日 下午7:03:37 
 * @version 1.0 
 */

@Repository
public interface AcadBgRepository extends JpaRepository<AcadBg, String>, AcadBgRepositoryCustom {

}
