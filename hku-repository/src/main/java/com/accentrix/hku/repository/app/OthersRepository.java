package com.accentrix.hku.repository.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.Others;
import com.accentrix.hku.repository.app.custom.OthersRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午7:07:33
 * @version 1.0
 */

@Repository
public interface OthersRepository extends JpaRepository<Others, String>, OthersRepositoryCustom {

}
