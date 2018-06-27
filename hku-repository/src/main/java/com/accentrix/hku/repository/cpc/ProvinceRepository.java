package com.accentrix.hku.repository.cpc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.cpc.Province;
import com.accentrix.hku.repository.cpc.custom.ProvinceRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月25日 上午11:56:44
 * @version 1.0
 */

@Repository
public interface ProvinceRepository extends JpaRepository<Province, String>, ProvinceRepositoryCustom {

}
