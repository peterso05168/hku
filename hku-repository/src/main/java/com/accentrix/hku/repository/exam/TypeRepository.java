package com.accentrix.hku.repository.exam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.exam.Type;
import com.accentrix.hku.repository.exam.custom.TypeRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 上午10:40:31
 * @version 1.0
 */

@Repository
public interface TypeRepository extends JpaRepository<Type, String>, TypeRepositoryCustom {

}
