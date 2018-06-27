package com.accentrix.hku.repository.cpc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.cpc.City;
import com.accentrix.hku.repository.cpc.custom.CityRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月25日 上午11:57:32
 * @version 1.0
 */

@Repository
public interface CityRepository extends JpaRepository<City, String>, CityRepositoryCustom {

}
