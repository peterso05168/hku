package com.accentrix.hku.repository.exam.impl;

import java.util.List;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.exam.QType;
import com.accentrix.hku.domain.exam.Type;
import com.accentrix.hku.repository.exam.custom.TypeRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 上午10:40:44
 * @version 1.0
 */

public class TypeRepositoryImpl extends JpaDslQuery<Type, QType> implements TypeRepositoryCustom {

    @Override
    public List<Type> findByIdNotIn(List<String> ids) {
        notIn($.id, ids);
        return list();
    }

    @Override
    public Type getByExamCd(String examCd) {
        eq($.examCd, examCd);
        return unique();
    }

}
