package com.accentrix.hku.service.staff.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.staff.StaffInformation;
import com.accentrix.hku.repository.staff.StaffInformationRepository;
import com.accentrix.hku.service.staff.StaffInformationService;
import com.accentrix.hku.vo.staff.StaffInformationForm;
import com.accentrix.hku.vo.staff.StaffInformationVo;
import com.accentrix.hku.timer.annotation.Timer;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 下午2:55:57
 * @version 1.0
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("staffInformation")
public class StaffInformationServiceImpl implements StaffInformationService {

    @Autowired
    private StaffInformationRepository staffInformationRepository;

    @Override
    public StaffInformationVo get(String id) {
        StaffInformation information = staffInformationRepository.findOne(id);
        return toVo(information);
    }

    @Transactional
    @Override
    public StaffInformationVo save(StaffInformationVo vo) {
        StaffInformation staffInformation = toStaffInformation(vo);
        staffInformation = staffInformationRepository.save(staffInformation);
        vo.setId(staffInformation.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<StaffInformationVo> save(List<StaffInformationVo> vos) {
        List<StaffInformationVo> informationVos = new ArrayList<StaffInformationVo>();
        for (StaffInformationVo informationVo : informationVos) {
            StaffInformation information = toStaffInformation(informationVo);
            information = staffInformationRepository.save(information);
            informationVo.setId(information.getId());
            informationVos.add(informationVo);
        }
        return informationVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        staffInformationRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(StaffInformationVo vo) {
        StaffInformation staffInformation = toStaffInformation(vo);
        staffInformationRepository.delete(staffInformation);
    }

    @Override
    public List<StaffInformationVo> findList() {
        List<StaffInformationVo> informationVos = new ArrayList<StaffInformationVo>();
        List<StaffInformation> informations = staffInformationRepository.findAll();
        for (StaffInformation staffInformation : informations) {
            informationVos.add(toVo(staffInformation));
        }
        return informationVos;
    }

    private StaffInformation toStaffInformation(StaffInformationVo vo) {
        StaffInformation staffInformation = new StaffInformation();
        staffInformation.setId(vo.getId());
        staffInformation.setEmail(vo.getEmail());
        staffInformation.setUsername(vo.getUsername());
        staffInformation.setStaffHkuNo(vo.getStaffHkuNo());
        staffInformation.setStaffUid(vo.getStaffUid());
        staffInformation.setIsActive(vo.getIsActive());
        return staffInformation;
    }

    private StaffInformationVo toVo(StaffInformation staffInformation) {
        StaffInformationVo vo = new StaffInformationVo();
        vo.setId(staffInformation.getId());
        vo.setEmail(staffInformation.getEmail());
        vo.setUsername(staffInformation.getUsername());
        vo.setStaffHkuNo(staffInformation.getStaffHkuNo());
        vo.setStaffUid(staffInformation.getStaffUid());
        vo.setIsActive(staffInformation.getIsActive());
        return vo;
    }

    @Override
    public boolean checkInformation(String email, String no) {
        StaffInformation staffInformation = staffInformationRepository.checkInformation(email, no);
        if (staffInformation != null) {
            return true;
        }
        return false;
    }

    @Override
    public Page<StaffInformationVo> findPage(StaffInformationForm staffInformationForm) {
        Page<StaffInformation> page = staffInformationRepository.findPage(staffInformationForm.getStaffInformationVo(),
                staffInformationForm.getPageable());
        return page.map(s -> toVo(s));
    }

    @Override
    public StaffInformationVo findByEmailAndHkuNo(String email, String hkuno) {
        StaffInformation staffInformation = staffInformationRepository.checkInformation(email, hkuno);
        return toVo(staffInformation);
    }
}
