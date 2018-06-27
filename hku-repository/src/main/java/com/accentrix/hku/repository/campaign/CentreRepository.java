package com.accentrix.hku.repository.campaign;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.campaign.Centre;
import com.accentrix.hku.repository.campaign.custom.CentreRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年4月9日 下午2:24:49
 * @version 1.0
 */

@Repository
public interface CentreRepository extends JpaRepository<Centre, String>, CentreRepositoryCustom {

}
