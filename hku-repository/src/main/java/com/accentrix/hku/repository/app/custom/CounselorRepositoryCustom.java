package com.accentrix.hku.repository.app.custom;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.Counselor;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年1月30日 下午7:05:22 
 * @version 1.0 
 */

@Repository
public interface CounselorRepositoryCustom {

    public Counselor findByFullName(String fullName);
}
