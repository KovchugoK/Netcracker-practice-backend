package com.netcrackerpractice.startup_social_network.service.impl;

import com.netcrackerpractice.startup_social_network.entity.Message;
import com.netcrackerpractice.startup_social_network.repository.MessageRepository;
import com.netcrackerpractice.startup_social_network.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Override
    public List<Message> getConversationMessagesById(UUID conversationId) {
        return messageRepository.getConversationMessagesById(conversationId);
    }

    @Override
    public void addMessage(Message message) {
        messageRepository.save(message);
    }
}
