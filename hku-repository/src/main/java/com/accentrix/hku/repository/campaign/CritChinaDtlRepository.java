package com.accentrix.hku.repository.campaign;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.campaign.CritChinaDtl;
import com.accentrix.hku.repository.campaign.custom.CritChinaDtlRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年4月9日 下午2:26:12
 * @version 1.0
 */

@Repository
public interface CritChinaDtlRepository extends JpaRepository<CritChinaDtl, String>, CritChinaDtlRepositoryCustom {

}
