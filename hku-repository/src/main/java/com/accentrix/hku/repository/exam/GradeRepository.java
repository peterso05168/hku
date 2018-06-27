package com.accentrix.hku.repository.exam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.exam.Grade;
import com.accentrix.hku.repository.exam.custom.GradeRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 上午10:39:44
 * @version 1.0
 */

@Repository
public interface GradeRepository extends JpaRepository<Grade, String>, GradeRepositoryCustom {

}
