package com.accentrix.hku.service.applicant.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accentrix.hku.domain.applicant.Message;
import com.accentrix.hku.repository.applicant.MessageRepository;
import com.accentrix.hku.service.applicant.MessageService;
import com.accentrix.hku.vo.applicant.MessageVo;
import com.accentrix.hku.timer.annotation.Timer;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年1月31日 下午2:30:35 
 * @version 1.0 
 */

@Service
@Transactional(readOnly = true)
@Timer
@Path("message")
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public MessageVo get(String id) {
        Message message = messageRepository.findOne(id);
        return toVo(message);
    }

    @Transactional
    @Override
    public MessageVo save(MessageVo vo) {
        Message message = toMessage(vo);
        message = messageRepository.save(message);
        vo.setId(message.getId());
        return vo;
    }

    @Transactional
    @Override
    public List<MessageVo> save(List<MessageVo> vos) {
        List<MessageVo> messageVos = new ArrayList<MessageVo>();
        for (MessageVo messageVo : vos) {
            Message message = toMessage(messageVo);
            message = messageRepository.save(message);
            messageVo.setId(message.getId());
            messageVos.add(messageVo);
        }
        return messageVos;
    }

    @Transactional
    @Override
    public void delete(String id) {
        messageRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(MessageVo messageVo) {
        messageRepository.delete(toMessage(messageVo));
    }

    @Override
    public List<MessageVo> findList() {
        List<MessageVo> messageVos = new ArrayList<MessageVo>();
        List<Message> messages = messageRepository.findAll();
        for (Message message : messages) {
            messageVos.add(toVo(message));
        }
        return messageVos;
    }

    private Message toMessage(MessageVo vo) {
        Message message = new Message();
        message.setFrom(vo.getFrom());
        message.setTo(vo.getTo());
        message.setMsgContent(vo.getMsgContent());
        message.setTimestamp(vo.getTimestamp());
        message.setApplicantAccountId(vo.getApplicantAccountId());
        return message;
    }

    private MessageVo toVo(Message message) {
        MessageVo vo = new MessageVo();
        vo.setId(message.getId());
        vo.setFrom(message.getFrom());
        vo.setTo(message.getTo());
        vo.setMsgContent(message.getMsgContent());
        vo.setTimestamp(message.getTimestamp());
        vo.setApplicantAccountId(message.getApplicantAccountId());
        return vo;
    }
}
