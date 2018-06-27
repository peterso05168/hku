package com.accentrix.hku.repository.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.ProgrammeChoice;
import com.accentrix.hku.repository.app.custom.ProgrammeChoiceRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月30日 下午7:08:58
 * @version 1.0
 */

@Repository
public interface ProgrammeChoiceRepository
        extends JpaRepository<ProgrammeChoice, String>, ProgrammeChoiceRepositoryCustom {

}
