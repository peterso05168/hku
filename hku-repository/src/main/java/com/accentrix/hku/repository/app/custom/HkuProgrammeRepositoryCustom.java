package com.accentrix.hku.repository.app.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.HkuProgramme;
import com.accentrix.hku.vo.app.HkuProgrammeVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午7:06:41
 * @version 1.0
 */

@Repository
public interface HkuProgrammeRepositoryCustom {

    List<HkuProgrammeVo> findVos(String staffId);

    List<String> findFacultyCd();

    List<HkuProgramme> findByFacultyCd(String facultyCd);
}
