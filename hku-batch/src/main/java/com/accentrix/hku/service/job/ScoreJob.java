package com.accentrix.hku.service.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.repository.app.QualificationRepository;

@Service
public class ScoreJob {

    @Autowired
    private QualificationRepository qualificationRepository;

    // @Scheduled(cron = "* * 0 * * ?")
    @Scheduled(cron = "10 * * * * ?")
    // @Scheduled(cron = "* 59 * * * ?")
    @Transactional
    public void calculate_actual_score_GCE() {
        qualificationRepository.calculate_actual_score_GCE();
        qualificationRepository.calculate_predicted_actual_score_GCE();
    }

    // @Scheduled(cron = "* * 0 * * ?")
    @Scheduled(cron = "10 * * * * ?")
    // @Scheduled(cron = "* 59 * * * ?")
    @Transactional
    public void calculate_predicted_actual_score_GCE() {
        System.out.println("calculate_predicted_actual_score_GCE()");
    }

    // @Scheduled(cron = "* * 0 * * ?")
    @Scheduled(cron = "10 * * * * ?")
    // @Scheduled(cron = "* 59 * * * ?")
    @Transactional
    public void calculate_actual_score_IBD_42() {
        qualificationRepository.calculate_actual_score_IBD_42();
    }

    // @Scheduled(cron = "* * 0 * * ?")
    @Scheduled(cron = "10 * * * * ?")
    // @Scheduled(cron = "* 59 * * * ?")
    @Transactional
    public void calculate_actual_score_IBD_45() {
        qualificationRepository.calculate_actual_score_IBD_45();
    }

    // @Scheduled(cron = "* * 0 * * ?")
    @Scheduled(cron = "10 * * * * ?")
    // @Scheduled(cron = "* 59 * * * ?")
    @Transactional
    public void calculate_predicted_actual_score_IBD_42() {
        qualificationRepository.calculate_predicted_actual_score_IBD_42();
    }

    // @Scheduled(cron = "* * 0 * * ?")
    @Scheduled(cron = "10 * * * * ?")
    // @Scheduled(cron = "* 59 * * * ?")
    @Transactional
    public void calculate_predicted_actual_score_IBD_45() {
        qualificationRepository.calculate_predicted_actual_score_IBD_45();
    }

    // @Scheduled(cron = "* * 0 * * ?")
    @Scheduled(cron = "10 * * * * ?")
    // @Scheduled(cron = "* 59 * * * ?")
    @Transactional
    public void calculate_IB_GPS() {
        qualificationRepository.calculate_IB_GPS();
    }

    // @Scheduled(cron = "* * 0 * * ?")
    @Scheduled(cron = "10 * * * * ?")
    // @Scheduled(cron = "* 59 * * * ?")
    @Transactional
    public void calculate_GCE_GPS() {
        qualificationRepository.calculate_GCE_GPS();
    }
}
