package com.accentrix.hku.repository.exam.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.exam.Type;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年1月31日 上午10:40:38 
 * @version 1.0 
 */

@Repository
public interface TypeRepositoryCustom {

    List<Type> findByIdNotIn(List<String> ids);

    Type getByExamCd(String examCd);

}
