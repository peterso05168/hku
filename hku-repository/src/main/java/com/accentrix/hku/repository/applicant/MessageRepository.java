package com.accentrix.hku.repository.applicant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accentrix.hku.domain.applicant.Message;
import com.accentrix.hku.repository.applicant.custom.MessageRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 上午10:39:02
 * @version 1.0
 */

@Repository
public interface MessageRepository extends JpaRepository<Message, String>, MessageRepositoryCustom {

}
