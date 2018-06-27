package com.accentrix.hku.repository.cpc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.cpc.Country;
import com.accentrix.hku.repository.cpc.custom.CountryRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月25日 上午11:53:58
 * @version 1.0
 */

@Repository
public interface CountryRepository extends JpaRepository<Country, String>, CountryRepositoryCustom {

}
