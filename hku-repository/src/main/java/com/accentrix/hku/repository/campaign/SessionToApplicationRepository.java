package com.accentrix.hku.repository.campaign;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.campaign.SessionToApplication;
import com.accentrix.hku.repository.campaign.custom.SessionToApplicationRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年4月9日 下午2:28:36
 * @version 1.0
 */

@Repository
public interface SessionToApplicationRepository
        extends JpaRepository<SessionToApplication, String>, SessionToApplicationRepositoryCustom {

}
