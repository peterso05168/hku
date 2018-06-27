package com.accentrix.hku.repository.applicant.impl;

import com.accentrix.hku.common.JpaDslQuery;
import com.accentrix.hku.domain.applicant.Message;
import com.accentrix.hku.domain.applicant.QMessage;
import com.accentrix.hku.repository.applicant.custom.MessageRepositoryCustom;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 上午10:39:15
 * @version 1.0
 */

public class MessageRepositoryImpl extends JpaDslQuery<Message, QMessage> implements MessageRepositoryCustom {

}
