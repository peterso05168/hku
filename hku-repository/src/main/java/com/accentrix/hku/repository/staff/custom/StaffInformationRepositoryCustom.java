package com.accentrix.hku.repository.staff.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.staff.StaffInformation;
import com.accentrix.hku.vo.staff.StaffInformationVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 上午10:42:55
 * @version 1.0
 */

@Repository
public interface StaffInformationRepositoryCustom {

    StaffInformation checkInformation(String email, String no);

    Page<StaffInformation> findPage(StaffInformationVo staffInformationVo, Pageable pageable);
}
