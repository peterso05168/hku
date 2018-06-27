package com.accentrix.hku.repository.exam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.exam.Subject;
import com.accentrix.hku.repository.exam.custom.SubjectRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 上午10:40:09
 * @version 1.0
 */

@Repository
public interface SubjectRepository extends JpaRepository<Subject, String>, SubjectRepositoryCustom {

}
