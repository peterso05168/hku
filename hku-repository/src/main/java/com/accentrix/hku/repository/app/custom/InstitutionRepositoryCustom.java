package com.accentrix.hku.repository.app.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.Institution;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月25日 下午12:00:54
 * @version 1.0
 */

@Repository
public interface InstitutionRepositoryCustom {

    public List<Institution> findInstitutions(String countryId, String provinceId, String cityId);
}
