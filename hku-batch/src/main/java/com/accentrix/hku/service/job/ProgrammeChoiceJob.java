package com.accentrix.hku.service.job;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.accentrix.hku.constant.Constants;
import com.accentrix.hku.domain.app.ProgrammeChoice;
import com.accentrix.hku.repository.app.ProgrammeChoiceRepository;
import com.accentrix.nttca.dcms.common.interceptor.AuditorAware;

@Service
public class ProgrammeChoiceJob {

    @Autowired
    private ProgrammeChoiceRepository programmeChoiceRepository;
    @Autowired
    private AuditorAware auditorAware;

    // @Scheduled(cron = "* * 0 * * ?")
    @Scheduled(cron = "10 * * * * ?")
    // @Scheduled(cron = "* 59 * * * ?")
    public void offerExpired() {
        auditorAware.setCurrentAuditor("system");
        List<ProgrammeChoice> programmeChoices = programmeChoiceRepository.findAll();
        for (ProgrammeChoice programmeChoice : programmeChoices) {
            if (programmeChoice.getReplyDeadline() != null && programmeChoice.getReplyDeadline().before(new Date())) {
                programmeChoice.setOfferStatusCd(Constants.PROG_CHOICE_OE_CD);
                programmeChoiceRepository.save(programmeChoice);
            }
        }
    }

}
