package com.accentrix.hku.repository.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.Faculty;
import com.accentrix.hku.repository.app.custom.FacultyRepositoryCustom;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年1月30日 下午7:06:05 
 * @version 1.0 
 */

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, String>, FacultyRepositoryCustom {

}
