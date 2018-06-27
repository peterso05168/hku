package com.accentrix.hku.service.applicant.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.applicant.Application;
import com.accentrix.hku.repository.applicant.ApplicationRepository;
import com.accentrix.hku.service.adm.FormService;
import com.accentrix.hku.service.app.ProgrammeChoiceService;
import com.accentrix.hku.service.applicant.ApplicationService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.adm.FormVo;
import com.accentrix.hku.vo.applicant.ApplicationVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 下午2:29:59
 * @version 1.0
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("application")
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private ProgrammeChoiceService programmeChoiceService;
    @Autowired
    private FormService formService;

    @Override
    public ApplicationVo get(String id) {
        Application application = applicationRepository.findOne(id);
        return toVo(application);
    }

    @Transactional
    @Override
    public ApplicationVo save(ApplicationVo vo) {
        Application application = toApplication(vo);
        application.setId(vo.getId());
        application = applicationRepository.save(application);
        vo.setId(application.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<ApplicationVo> save(List<ApplicationVo> vos) {
        List<ApplicationVo> applicationVos = new ArrayList<ApplicationVo>();
        for (ApplicationVo applicationVo : vos) {
            Application application = toApplication(applicationVo);
            application = applicationRepository.save(application);
            applicationVo.setId(application.getId());
            applicationVos.add(applicationVo);
        }
        return applicationVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        applicationRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(ApplicationVo applicationVo) {
        applicationRepository.delete(toApplication(applicationVo));
    }

    @Override
    public List<ApplicationVo> findList() {
        List<ApplicationVo> applicationVos = new ArrayList<ApplicationVo>();
        List<Application> applications = applicationRepository.findAll();
        for (Application application : applications) {
            applicationVos.add(toVo(application));
        }
        return applicationVos;
    }

    @Override
    public ApplicationVo findByAdmFormIdAndAccountId(String admFormId, String applicantAccountId) {
        Application application = applicationRepository.findByAdmFormIdAndAccountId(admFormId, applicantAccountId);
        if (application == null) {
            return null;
        }
        return toVo(application);
    }

    @Override
    public List<ApplicationVo> basicSearch(String criteria) {
        return applicationRepository.basicSearch(criteria);
    }

    @Override
    public List<ApplicationVo> advancedSearch(ApplicationVo searchVo) {
        return applicationRepository.advanceSearch(searchVo);
    }

    private Application toApplication(ApplicationVo vo) {
        Application application = new Application();
        application.setPersonId(vo.getPersonId());
        application.setAcadBgId(vo.getAcadBgId());
        application.setReferenceId(vo.getReferenceId());
        application.setOthersId(vo.getOthersId());
        application.setApplicantAccountId(vo.getApplicantAccountId());
        application.setAcadQualHousingMgmtId(vo.getAcadQualHousingMgmtId());
        application.setPredictedGrade(vo.getPredictedGrade());
        application.setActualGrade(vo.getActualGrade());
        application.setAdmFormId(vo.getAdmFormId());
        application.setScholarHeforshe(vo.getScholarHeforshe());
        application.setScholarUwc(vo.getScholarUwc());
        application.setScholarNigerian(vo.getScholarNigerian());
        application.setScholarVtp(vo.getScholarVtp());
        application.setScholarAfl(vo.getScholarAfl());
        application.setScholarHksarGsft(vo.getScholarHksarGsft());
        application.setScholarHksarGsftBrs(vo.getScholarHksarGsftBrs());
        application.setAcadQualNursingId(vo.getAcadQualNursingId());
        application.setApplicationNo(vo.getApplicationNo());
        application.setStatus(vo.getStatus());
        application.setEngReq(vo.getEngReq());
        application.setCcaigInterview(vo.getCcaigInterview());
        application.setCcaiiInterview(vo.getCcaiiInterview());
        return application;
    }

    private ApplicationVo toVo(Application application) {
        ApplicationVo vo = new ApplicationVo();
        if (application != null) {
            vo.setId(application.getId());
            vo.setPersonId(application.getPersonId());
            vo.setAcadBgId(application.getAcadBgId());
            vo.setReferenceId(application.getReferenceId());
            vo.setOthersId(application.getOthersId());
            vo.setApplicantAccountId(application.getApplicantAccountId());
            vo.setAcadQualHousingMgmtId(application.getAcadQualHousingMgmtId());
            vo.setPredictedGrade(application.getPredictedGrade());
            vo.setActualGrade(application.getActualGrade());
            vo.setAdmFormId(application.getAdmFormId());
            vo.setScholarHeforshe(application.getScholarHeforshe());
            vo.setScholarUwc(application.getScholarUwc());
            vo.setScholarNigerian(application.getScholarNigerian());
            vo.setScholarVtp(application.getScholarVtp());
            vo.setScholarAfl(application.getScholarAfl());
            vo.setScholarHksarGsft(application.getScholarHksarGsft());
            vo.setScholarHksarGsftBrs(application.getScholarHksarGsftBrs());
            vo.setAcadQualNursingId(application.getAcadQualNursingId());
            vo.setApplicationNo(application.getApplicationNo());
            vo.setStatus(application.getStatus());
            vo.setEngReq(application.getEngReq());
            vo.setCcaigInterview(application.getCcaigInterview());
            vo.setCcaiiInterview(application.getCcaiiInterview());
        }
        return vo;
    }

    @Override
    public List<ApplicationVo> findByAccountIds(List<String> applicantAccountIds) {
        List<ApplicationVo> applicationVos = new ArrayList<ApplicationVo>();
        List<Application> applications = applicationRepository.findByAccountIds(applicantAccountIds);
        for (Application application : applications) {
            applicationVos.add(toVo(application));
        }
        return applicationVos;
    }

    @Override
    public List<ApplicationVo> findByApplicantId(String applicantId) {
        return applicationRepository.findByApplicantId(applicantId);
    }

    @Override
    public List<ApplicationVo> findByAccountId(String applicantAccountId) {
        List<ApplicationVo> applicationVos = new ArrayList<ApplicationVo>();
        List<Application> applications = applicationRepository.findByAccountId(applicantAccountId);
        for (Application application : applications) {
            ApplicationVo vo = toVo(application);
            applicationVos.add(vo);
        }
        return applicationVos;
    }

    private String getYear(String formId) {
        FormVo formVo = formService.get(formId);
        return formVo.getDescChi().substring(formVo.getDescription().length() - 4, formVo.getDescription().length());
    }

    public void sortApplicationVos(List<ApplicationVo> applicationVos) {
        Collections.sort(applicationVos, new Comparator<ApplicationVo>() {
            @Override
            public int compare(ApplicationVo o1, ApplicationVo o2) {
                return o2.getYear().compareTo(o1.getYear());
            }
        });
    }

    @Override
    public ApplicationVo getByApplicationId(String applicationId) {
        return applicationRepository.getByApplicationId(applicationId);
    }

    @Override
    public List<ApplicationVo> findByAccountIdAndStatus(String applicantAccountId, String status) {
        List<ApplicationVo> applicationVos = new ArrayList<ApplicationVo>();
        List<Application> applications = applicationRepository.findByAccountIdAndStatus(applicantAccountId, status);
        for (Application application : applications) {
            ApplicationVo vo = toVo(application);
            vo.setProgrammeChoiceVos(programmeChoiceService.getChoiceByApplicationId(vo.getId()));
            vo.setYear(getYear(vo.getAdmFormId()));
            applicationVos.add(vo);
        }
        sortApplicationVos(applicationVos);
        return applicationVos;
    }
}
