package com.accentrix.hku.repository.scholar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.scholar.Scholar;
import com.accentrix.hku.repository.scholar.custom.ScholarRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年3月19日 上午11:34:54
 * @version 1.0
 */

@Repository
public interface ScholarRepository extends JpaRepository<Scholar, String>, ScholarRepositoryCustom {

}
