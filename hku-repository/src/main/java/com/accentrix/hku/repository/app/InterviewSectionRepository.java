package com.accentrix.hku.repository.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.InterviewSection;
import com.accentrix.hku.repository.app.custom.InterviewSectionRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午7:07:04
 * @version 1.0
 */

@Repository
public interface InterviewSectionRepository
        extends JpaRepository<InterviewSection, String>, InterviewSectionRepositoryCustom {

}
