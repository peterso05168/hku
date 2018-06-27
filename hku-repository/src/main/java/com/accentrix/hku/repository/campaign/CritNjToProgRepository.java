package com.accentrix.hku.repository.campaign;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.campaign.CritNjToProg;
import com.accentrix.hku.repository.campaign.custom.CritNjToProgRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年4月9日 下午2:27:09
 * @version 1.0
 */

@Repository
public interface CritNjToProgRepository extends JpaRepository<CritNjToProg, String>, CritNjToProgRepositoryCustom {

}
