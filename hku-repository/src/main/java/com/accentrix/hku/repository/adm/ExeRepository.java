package com.accentrix.hku.repository.adm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.adm.Exe;
import com.accentrix.hku.repository.adm.custom.ExeRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午7:01:17
 * @version 1.0
 */

@Repository
public interface ExeRepository extends JpaRepository<Exe, String>, ExeRepositoryCustom {

}
