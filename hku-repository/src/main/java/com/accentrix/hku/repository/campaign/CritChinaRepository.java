package com.accentrix.hku.repository.campaign;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.campaign.CritChina;
import com.accentrix.hku.repository.campaign.custom.CritChinaRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年4月9日 下午2:25:41
 * @version 1.0
 */

@Repository
public interface CritChinaRepository extends JpaRepository<CritChina, String>, CritChinaRepositoryCustom {

}
