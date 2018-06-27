package com.accentrix.hku.repository.staff.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.staff.Prog;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年1月31日 上午10:44:45 
 * @version 1.0 
 */

@Repository
public interface ProgRepositoryCustom {

    List<Prog> findByStaffId(String staffId);
}
