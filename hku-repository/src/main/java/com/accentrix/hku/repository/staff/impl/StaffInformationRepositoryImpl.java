package com.accentrix.hku.repository.staff.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.staff.QStaffInformation;
import com.accentrix.hku.domain.staff.StaffInformation;
import com.accentrix.hku.repository.staff.custom.StaffInformationRepositoryCustom;
import com.accentrix.hku.vo.staff.StaffInformationVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 上午10:43:01
 * @version 1.0
 */

public class StaffInformationRepositoryImpl extends JpaDslQuery<StaffInformation, QStaffInformation>
        implements StaffInformationRepositoryCustom {

    @Override
    public StaffInformation checkInformation(String email, String no) {
        eq($.isActive, true);
        eq($.email, email);
        eq($.staffHkuNo, no);
        return unique();
    }

    @Override
    public Page<StaffInformation> findPage(StaffInformationVo staffInformationVo, Pageable pageable) {
        if (StringUtils.isNotBlank(staffInformationVo.getStatus())) {
            eq($.isActive, Boolean.valueOf(staffInformationVo.getStatus()));
        }
        contains($.username, staffInformationVo.getUsername());
        contains($.email, staffInformationVo.getEmail());
        if (StringUtils.isNotBlank(staffInformationVo.getCombination())) {
            removeCurrentBooleanBuilder();
            orContains($.username, $.email, staffInformationVo.getCombination());
        }
        return findAll(pageable);
    }

}
