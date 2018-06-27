package com.accentrix.hku.repository.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.app.IbdBestExam;
import com.accentrix.hku.repository.app.custom.IbdBestExamRepositoryCustom;

@Repository
public interface IbdBestExamRepository extends JpaRepository<IbdBestExam, String>, IbdBestExamRepositoryCustom {

}
