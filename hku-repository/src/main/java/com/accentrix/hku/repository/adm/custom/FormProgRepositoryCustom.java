package com.accentrix.hku.repository.adm.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.adm.FormProg;
import com.accentrix.hku.vo.adm.FormProgVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年1月30日 下午7:02:28 
 * @version 1.0 
 */

@Repository
public interface FormProgRepositoryCustom {
    List<FormProg> getByAdmFormId(String admFormId);

    List<FormProgVo> basicSearch(String criteria);

    List<FormProgVo> advanceSearch(FormProgVo searchVo);

    List<FormProgVo> findVos(Integer year);

    List<FormProg> findByHkuProgrammeId(String programmeId);
}
