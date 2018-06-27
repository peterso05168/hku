package com.accentrix.hku.repository.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.HkuProgramme;
import com.accentrix.hku.repository.app.custom.HkuProgrammeRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午7:06:32
 * @version 1.0
 */

@Repository
public interface HkuProgrammeRepository extends JpaRepository<HkuProgramme, String>, HkuProgrammeRepositoryCustom {

}
