package com.accentrix.hku.service.app.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.constant.Constants;
import com.accentrix.hku.domain.app.ProgrammeChoice;
import com.accentrix.hku.repository.app.ProgrammeChoiceRepository;
import com.accentrix.hku.service.adm.FormProgService;
import com.accentrix.hku.service.app.BestExamSubjRsltService;
import com.accentrix.hku.service.app.IbdBestExamService;
import com.accentrix.hku.service.app.ProgrammeChoiceService;
import com.accentrix.hku.service.general.RefCdService;
import com.accentrix.hku.timer.annotation.Timer;
import com.accentrix.hku.vo.adm.FormProgVo;
import com.accentrix.hku.vo.app.BestExamSubjRsltVo;
import com.accentrix.hku.vo.app.IbdBestExamVo;
import com.accentrix.hku.vo.app.ProgrammeChoiceForm;
import com.accentrix.hku.vo.app.ProgrammeChoiceVo;
import com.accentrix.hku.vo.general.RefCdVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 下午2:23:52
 * @version 1.0
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("programmeChoice")
public class ProgrammeChoiceServiceImpl implements ProgrammeChoiceService {

    @Autowired
    private ProgrammeChoiceRepository programmeChoiceRepository;
    @Autowired
    private FormProgService formProgService;
    @Autowired
    private RefCdService refCdService;
    @Autowired
    private BestExamSubjRsltService bestExamSubjRsltService;
    @Autowired
    private IbdBestExamService ibdBestExamService;

    @Override
    public ProgrammeChoiceVo get(String id) {
        ProgrammeChoice programmeChoice = programmeChoiceRepository.findOne(id);
        return toVo(programmeChoice);
    }

    @Transactional
    @Override
    public ProgrammeChoiceVo save(ProgrammeChoiceVo vo) {
        ProgrammeChoice programmeChoice = toProgrammeChoice(vo);
        programmeChoice = programmeChoiceRepository.save(programmeChoice);
        vo.setId(programmeChoice.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<ProgrammeChoiceVo> save(List<ProgrammeChoiceVo> vos) {
        List<ProgrammeChoiceVo> programmeChoiceVos = new ArrayList<ProgrammeChoiceVo>();
        for (ProgrammeChoiceVo vo : vos) {
            ProgrammeChoice programmeChoice = toProgrammeChoice(vo);
            programmeChoice = programmeChoiceRepository.save(programmeChoice);
            vo.setId(programmeChoice.getId());
            programmeChoiceVos.add(vo);
        }
        return programmeChoiceVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        programmeChoiceRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(ProgrammeChoiceVo vo) {
        programmeChoiceRepository.delete(toProgrammeChoice(vo));
    }

    @Override
    public List<ProgrammeChoiceVo> findList() {
        List<ProgrammeChoice> programmeChoices = programmeChoiceRepository.findAll();
        List<ProgrammeChoiceVo> vos = new ArrayList<ProgrammeChoiceVo>();
        for (ProgrammeChoice programmeChoice : programmeChoices) {
            vos.add(toVo(programmeChoice));
        }
        return vos;
    }

    @Override
    public ProgrammeChoiceVo getFirstChoiceByApplicationId(String applicationId) {
        ProgrammeChoice programmeChoice = programmeChoiceRepository.getFirstChoiceByApplicationId(applicationId);
        return toVo(programmeChoice);
    }

    @Override
    public List<ProgrammeChoiceVo> getOtherChoiceByApplicationId(String applicationId) {
        List<ProgrammeChoice> programmeChoices = programmeChoiceRepository.getOtherChoiceByApplicationId(applicationId);
        List<ProgrammeChoiceVo> vos = new ArrayList<ProgrammeChoiceVo>();
        for (ProgrammeChoice programmeChoice : programmeChoices) {
            vos.add(toVo(programmeChoice));
        }
        return vos;
    }

    @Override
    public List<ProgrammeChoiceVo> getChoiceByApplicationId(String applicationId) {
        List<ProgrammeChoice> programmeChoices = programmeChoiceRepository.getChoiceByApplicationId(applicationId);
        List<ProgrammeChoiceVo> vos = new ArrayList<ProgrammeChoiceVo>();
        for (ProgrammeChoice programmeChoice : programmeChoices) {
            vos.add(toVo(programmeChoice));
        }
        return vos;
    }

    private ProgrammeChoice toProgrammeChoice(ProgrammeChoiceVo vo) {
        ProgrammeChoice programmeChoice = new ProgrammeChoice();
        programmeChoice.setId(vo.getId());
        programmeChoice.setAdmFormProgId(vo.getAdmFormProgId());
        programmeChoice.setFirstChoice(vo.getFirstChoice());
        programmeChoice.setApplicationId(vo.getApplicationId());
        programmeChoice.setOfferStatusCd(vo.getOfferStatusCd());
        programmeChoice.setIsWithdrawnApproved(vo.getIsWithdrawnApproved());
        programmeChoice.setCreateBy(vo.getCreateBy());
        programmeChoice.setUpdateBy(vo.getUpdateBy());
        programmeChoice.setCreateDate(vo.getCreateDate());
        programmeChoice.setUpdateDate(vo.getUpdateDate());
        programmeChoice.setVersion(vo.getVersion());
        programmeChoice.setIsWithdrawn(vo.getIsWithdrawn());
        programmeChoice.setMeetReq(vo.getMeetReq());
        programmeChoice.setProgReq(vo.getProgReq());
        programmeChoice.setSelectForInterview(vo.getSelectForInterview());
        programmeChoice.setProgInterviewScore(vo.getProgInterviewScore());
        programmeChoice.setOtherInterview(vo.getOtherInterview());
        programmeChoice.setReplyDeadline(vo.getReplyDeadline());
        programmeChoice.setRepliedOn(vo.getRepliedOn());
        programmeChoice.setWdraCd(vo.getWdraCd());
        programmeChoice.setSisNumber(vo.getSisNumber());
        programmeChoice.setLastUpdatedBy(vo.getLastUpdatedBy());
        programmeChoice.setLastUpdatedDate(vo.getLastUpdatedDate());
        programmeChoice.setAssignedQuotaLocal(vo.getAssignedQuotaLocal());
        programmeChoice.setAssignedQuotaMainland(vo.getAssignedQuotaMainland());
        programmeChoice.setAssignedQuotaOverseas(vo.getAssignedQuotaOverseas());
        programmeChoice.setAssignedTo(vo.getAssignedTo());
        programmeChoice.setUniCompReq(vo.getUniCompReq());
        programmeChoice.setOfferType(vo.getOfferType());
        programmeChoice.setConditionalType(vo.getConditionalType());
        return programmeChoice;
    }

    private ProgrammeChoiceVo toVo(ProgrammeChoice programmeChoice) {
        ProgrammeChoiceVo vo = new ProgrammeChoiceVo();
        if (programmeChoice != null) {
            vo.setId(programmeChoice.getId());
            vo.setAdmFormProgId(programmeChoice.getAdmFormProgId());
            if (StringUtils.isNotBlank(programmeChoice.getAdmFormProgId())) {
                FormProgVo formProg = formProgService.get(programmeChoice.getAdmFormProgId());
                vo.setHkuProgrammeCode(formProg.getProgrammeCode());
                vo.setHkuProgrammeDesc(formProg.getProgrammeTitle());
                vo.setRefCd(refCdService.getByTypeAndCd(Constants.FACULTY, formProg.getFaculty()));
            }
            vo.setFirstChoice(programmeChoice.getFirstChoice());
            vo.setApplicationId(programmeChoice.getApplicationId());
            vo.setOfferStatusCd(programmeChoice.getOfferStatusCd());
            if (StringUtils.isNotBlank(programmeChoice.getOfferStatusCd())) {
                RefCdVo refCdVo = refCdService.getByTypeAndCd(Constants.CHOICESTATUS,
                        programmeChoice.getOfferStatusCd());
                vo.setStatusValue(refCdVo.getValue());
                vo.setStatusValueChi(refCdVo.getValueChi());
            }
            vo.setIsWithdrawnApproved(programmeChoice.getIsWithdrawnApproved());
            vo.setCreateBy(programmeChoice.getCreateBy());
            vo.setUpdateBy(programmeChoice.getUpdateBy());
            vo.setCreateDate(programmeChoice.getCreateDate());
            vo.setUpdateDate(programmeChoice.getUpdateDate());
            vo.setVersion(programmeChoice.getVersion());
            vo.setIsWithdrawn(programmeChoice.getIsWithdrawn());
            vo.setMeetReq(programmeChoice.getMeetReq());
            vo.setProgReq(programmeChoice.getProgReq());
            vo.setSelectForInterview(programmeChoice.getSelectForInterview());
            vo.setProgInterviewScore(programmeChoice.getProgInterviewScore());
            vo.setOtherInterview(programmeChoice.getOtherInterview());
            vo.setReplyDeadline(programmeChoice.getReplyDeadline());
            vo.setRepliedOn(programmeChoice.getRepliedOn());
            vo.setWdraCd(programmeChoice.getWdraCd());
            vo.setSisNumber(programmeChoice.getSisNumber());
            vo.setLastUpdatedBy(programmeChoice.getLastUpdatedBy());
            vo.setLastUpdatedDate(programmeChoice.getLastUpdatedDate());
            vo.setAssignedQuotaLocal(programmeChoice.getAssignedQuotaLocal());
            vo.setAssignedQuotaMainland(programmeChoice.getAssignedQuotaMainland());
            vo.setAssignedQuotaOverseas(programmeChoice.getAssignedQuotaOverseas());
            vo.setAssignedTo(programmeChoice.getAssignedTo());
            vo.setUniCompReq(programmeChoice.getUniCompReq());
            vo.setOfferType(programmeChoice.getOfferType());
            vo.setConditionalType(programmeChoice.getConditionalType());
        }
        return vo;
    }

    @Override
    public List<ProgrammeChoiceVo> findByHkuProgrammeId(String programmeId) {
        List<ProgrammeChoice> programmeChoices = programmeChoiceRepository.findByHkuProgrammeId(programmeId);
        List<ProgrammeChoiceVo> vos = new ArrayList<ProgrammeChoiceVo>();
        for (ProgrammeChoice programmeChoice : programmeChoices) {
            vos.add(toVo(programmeChoice));
        }
        return vos;
    }

    @Override
    public List<ProgrammeChoiceVo> findByFormProgId(String formProgId) {
        List<ProgrammeChoice> programmeChoices = programmeChoiceRepository.findByFormProgId(formProgId);
        List<ProgrammeChoiceVo> vos = new ArrayList<ProgrammeChoiceVo>();
        for (ProgrammeChoice programmeChoice : programmeChoices) {
            vos.add(toVo(programmeChoice));
        }
        return vos;
    }

    @Override
    public Page<ProgrammeChoiceVo> findPage(ProgrammeChoiceForm programmeChoiceForm) {
        List<ProgrammeChoiceVo> list = programmeChoiceRepository.findListVo(programmeChoiceForm.getProgrammeChoiceVo(),
                programmeChoiceForm.getPageable().getOffset(), programmeChoiceForm.getPageable().getPageSize());
        for (ProgrammeChoiceVo programmeChoiceVo : list) {
            RefCdVo refCdVo = refCdService.getByTypeAndCd(Constants.CHOICESTATUS, programmeChoiceVo.getOfferStatusCd());
            programmeChoiceVo.setOfferStatusCd(refCdVo.getValue());
            List<BestExamSubjRsltVo> actualGCEs = bestExamSubjRsltService.getBestExamSubjects("GCE",
                    programmeChoiceVo.getApplicationId(), "Actual");
            List<BestExamSubjRsltVo> predictedActualGCEs = bestExamSubjRsltService.getBestExamSubjects("GCE",
                    programmeChoiceVo.getApplicationId(), "Predicted and Actual");
            IbdBestExamVo actualIbd42 = ibdBestExamService.getBestIBD(programmeChoiceVo.getApplicationId(), "Actual",
                    "42");
            IbdBestExamVo actualIbd45 = ibdBestExamService.getBestIBD(programmeChoiceVo.getApplicationId(), "Actual",
                    "45");
            IbdBestExamVo predictedActualIbd42 = ibdBestExamService.getBestIBD(programmeChoiceVo.getApplicationId(),
                    "Predicted and Actual", "42");
            IbdBestExamVo predictedActualIbd45 = ibdBestExamService.getBestIBD(programmeChoiceVo.getApplicationId(),
                    "Predicted and Actual", "45");
            programmeChoiceVo.setActualGCEs(actualGCEs);
            programmeChoiceVo.setPredictedActualGCEs(predictedActualGCEs);
            programmeChoiceVo.setActualIbd42(actualIbd42);
            programmeChoiceVo.setActualIbd45(actualIbd45);
            programmeChoiceVo.setPredictedActualIbd42(predictedActualIbd42);
            programmeChoiceVo.setPredictedActualIbd45(predictedActualIbd45);
        }
        int num = programmeChoiceRepository.findCount(programmeChoiceForm.getProgrammeChoiceVo());
        return new PageImpl<ProgrammeChoiceVo>(list, programmeChoiceForm.getPageable(), num);
    }

}
