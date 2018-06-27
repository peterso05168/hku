package com.accentrix.hku.repository.adm.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.adm.Exe;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午7:01:27
 * @version 1.0
 */

@Repository
public interface ExeRepositoryCustom {

    Exe getByApplicationId(String applicationId);

    List<Integer> findAdmissionYear();
}
