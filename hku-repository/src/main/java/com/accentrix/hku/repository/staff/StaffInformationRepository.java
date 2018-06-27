package com.accentrix.hku.repository.staff;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.staff.StaffInformation;
import com.accentrix.hku.repository.staff.custom.StaffInformationRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 上午10:42:47
 * @version 1.0
 */

@Repository
public interface StaffInformationRepository
        extends JpaRepository<StaffInformation, String>, StaffInformationRepositoryCustom {

}
