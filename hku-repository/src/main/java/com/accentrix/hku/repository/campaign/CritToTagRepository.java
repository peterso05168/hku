package com.accentrix.hku.repository.campaign;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.campaign.CritToTag;
import com.accentrix.hku.repository.campaign.custom.CritToTagRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年4月9日 下午2:27:37
 * @version 1.0
 */

@Repository
public interface CritToTagRepository extends JpaRepository<CritToTag, String>, CritToTagRepositoryCustom {

}
