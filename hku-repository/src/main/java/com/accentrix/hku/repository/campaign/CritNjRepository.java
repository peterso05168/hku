package com.accentrix.hku.repository.campaign;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.campaign.CritNj;
import com.accentrix.hku.repository.campaign.custom.CritNjRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年4月9日 下午2:26:42
 * @version 1.0
 */

@Repository
public interface CritNjRepository extends JpaRepository<CritNj, String>, CritNjRepositoryCustom {

}
