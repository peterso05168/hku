package com.accentrix.hku.repository.staff;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.staff.Prog;
import com.accentrix.hku.repository.staff.custom.ProgRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 上午10:44:37
 * @version 1.0
 */

@Repository
public interface ProgRepository extends JpaRepository<Prog, String>, ProgRepositoryCustom {

}
